package gui;

import generation.CardinalDirection;
import generation.Maze;

/*Class: 
 * 	ReliableRobot
 *Responsibilities:
 *	Get info from its controller (likely once)
 *	Add up to four distance sensors 
 *	Returns position in maze, as well as direction facing in maze
 *	Has a battery level that changes with operations, can be adjusted & returned
 *Collaborators
 *	Control (robot's controller)
 *	ReliableSensor (gets distance to wall in specific direction)
 *	Wizard/WallFollower (drives/operates the robot)
 *
 *Note:
 *Moving North = [0, -1]
 *Moving East = [+1, 0]
 *Moving South = [0, +1]
 *Moving West = [-1, 0]
 *
 * S
 *W E
 * N
 * 
 * @author Evan Hellersund
 * 
 */

public class ReliableRobot implements Robot {

	Control RobotController = null;
	float[] Battery = {3500};
	int Odometer = 0;
	boolean stopped = false;
	DistanceSensor FrontSensor = null;
	DistanceSensor LeftSensor = null;
	DistanceSensor RightSensor = null;
	DistanceSensor BackSensor = null;

	@Override
	public void setController(Control controller) throws IllegalArgumentException {
		if (controller == null || !(controller.currentState instanceof StatePlaying) || controller.getMaze() == null) {
			throw new IllegalArgumentException("Unable to set controller, it might be null, wrong playstate, or maze does not exist");
			}
		else {
			RobotController = controller;
		}
	}

	@Override
	public void addDistanceSensor(DistanceSensor sensor, Direction mountedDirection) {
		sensor.setSensorDirection(mountedDirection);
		switch(mountedDirection) {
		case FORWARD:
			FrontSensor = sensor;
		case LEFT:
			LeftSensor = sensor;
		case RIGHT:
			RightSensor = sensor;
		case BACKWARD:
			BackSensor = sensor;
		}
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		int position[] = RobotController.getCurrentPosition();
		if (RobotController.getMaze().isValidPosition(position[0], position[1]) == false) {
			throw new Exception("Position is invalid");
		}
		return position;

	}

	@Override
	public CardinalDirection getCurrentDirection() {
		return RobotController.getCurrentDirection();
	}

	@Override
	public float getBatteryLevel() {
		return Battery[0];
	}

	@Override
	public void setBatteryLevel(float level) {
		Battery[0] = level;
	}

	@Override
	public float getEnergyForFullRotation() {
		return 12;
	}

	@Override
	public float getEnergyForStepForward() {
		return 6;
	}

	@Override
	public int getOdometerReading() {
		return Odometer;
	}

	@Override
	public void resetOdometer() {
		Odometer = 0;
	}

	@Override
	public void rotate(Turn turn) {
		switch(turn) {
		case LEFT:
			Battery[0] -= 3;
			if (hasStopped() == true) {
				loser();
			}
			else {
				RobotController.robotTurn(Turn.LEFT);
				break;
			}
		case RIGHT:
			Battery[0] -= 3;
			if (hasStopped() == true) {
				loser();
			}
			else {
				RobotController.robotTurn(Turn.RIGHT);
				break;
			}
		case AROUND:
			Battery[0] -= 3;
			if (hasStopped() == true) {
				loser();
			}
			else {
				RobotController.robotTurn(Turn.RIGHT);
				Battery[0] -= 3;
				if (hasStopped() == true) {
					System.out.println("Could only rotate 90 degrees");
					loser();
				}
				else {
					RobotController.robotTurn(Turn.RIGHT);
					break;
				}
			}
		}
	}

	@Override
	public void move(int distance) throws Exception, UnsupportedOperationException {
		int stepsLeft = distance;
		int wallDist = distanceToObstacle(Direction.FORWARD);
		
		while (stepsLeft > 0) {
			wallDist -= 1;
			if (wallDist < 0) {
				stopped = true;
				stepsLeft = 0;
				loser();
				}
			else {
				RobotController.robotMove();
				Odometer += 1;
				Battery[0] -= 6;
				if (hasStopped() == true) {
					loser();
				}
				stepsLeft -= 1;
			}
		}
	}

	@Override
	public void jump() {
		Battery[0] -= 40;
		RobotController.robotJump();
		this.Odometer += 1;
		if (stopped == true) {
			loser();
		}
	}

	@Override
	public boolean isAtExit() {
		int exit[] = RobotController.getMaze().getExitPosition();
		return ((RobotController.getCurrentPosition()[0] == exit[0] && RobotController.getCurrentPosition()[1] == exit[1]) ? true : false);
	}

	@Override
	public boolean isInsideRoom() {
		return RobotController.getMaze().isInRoom(RobotController.getCurrentPosition()[0], RobotController.getCurrentPosition()[1]);
	}

	@Override
	public boolean hasStopped() {
		if (Battery[0] <= 0) {
			stopped = true; }
		return stopped;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws Exception, UnsupportedOperationException {
		switch(direction) {
		case FORWARD:
			if (FrontSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in FORWARD direction"); }
			else {
				return FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
		case LEFT:
			if (LeftSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in LEFT direction"); }
			else {
				return LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
		case RIGHT:
			if (RightSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in RIGHT direction"); }
			else {
				return RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
		case BACKWARD:
			if (BackSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in BACKWARD direction"); }
			else {
				return BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
		}
		return -2; //Should not happen
	}

	@Override
	public boolean canSeeThroughTheExitIntoEternity(Direction direction) throws Exception, UnsupportedOperationException {
		switch(direction) {
		case FORWARD:
			if (FrontSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in FORWARD direction"); }
			if (FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == Integer.MAX_VALUE) {
				return true; }
			break;
		case LEFT:
			if (LeftSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in LEFT direction"); }
			if (LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == Integer.MAX_VALUE) {
				return true; }
			break;
		case RIGHT:
			if (RightSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in RIGHT direction"); }
			if (RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == Integer.MAX_VALUE) {
				return true; }
			break;
		case BACKWARD:
			if (BackSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in BACKWARD direction"); }
			if (BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == Integer.MAX_VALUE) {
				return true; }
			break;
		}
		return false;
	}

	@Override
	public void startFailureAndRepairProcess(Direction direction, int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported for ReliableRobot");
	}

	@Override
	public void stopFailureAndRepairProcess(Direction direction) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported for ReliableRobot");
	}
	
	//Helper method that converts the requested direction in relation to the robot into a cardinal direction
	public CardinalDirection dToCD(Direction direction) {
		CardinalDirection facing = getCurrentDirection();
		switch(direction) {
		case FORWARD:
			return facing;
		case LEFT:
			facing = facing.rotateCounterClockwise();
			break;
		case RIGHT:
			facing = facing.rotateClockwise();
			break;
		case BACKWARD:
			facing = facing.rotateClockwise().rotateClockwise();
			break;
		}
		return facing;
	}
	
	//Method that returns the robot's maze, used by Wizard
	public Maze giveMaze() {
		return RobotController.getMaze();
	}
	
	//Method that will be called to implement game loss
	public void loser() {
		System.out.println("Game lost"); 
	}
}

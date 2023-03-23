package gui;

import java.awt.event.KeyEvent;

import generation.CardinalDirection;

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
 * @author Evan Hellersund
 * 
 */

public class ReliableRobot implements Robot {
	/*
	Control RobotController = new Control
	float Battery = 3500
	int Odometer = 0
	boolean stopped = false
	ReliableSensor FrontSensor = null
	ReliableSensor LeftSensor = null
	ReliableSensor RightSensor = null
	ReliableSensor BackSensor = null
	*/

	@Override
	public void setController(Control controller) {
		/*
		Control controller = new Control
		if controller == null or controller.currentState != StatePlaying or controller.getMaze() == null
			throw IllegalArgumentException
		else
			RobotController = controller
		*/
		
	}

	@Override
	public void addDistanceSensor(DistanceSensor sensor, Direction mountedDirection) {
		/*
		DistanceSensor sensor = new ReliableSensor()
		sensor.setSensorDirection(mountedDirected)
		*/
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		/*
		return RobotController.getCurrentPosition()
		*/
		return null;
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		/*
		return controller.getCurrentDirection()
		*/
		return null;
	}

	@Override
	public float getBatteryLevel() {
		// return Battery
		return 0;
	}

	@Override
	public void setBatteryLevel(float level) {
		// Battery = level

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
		// return Odometer
		return 0;
	}

	@Override
	public void resetOdometer() {
		// Odometer = 0
	}

	@Override
	public void rotate(Turn turn) {
		/*
		switch(turn)
		case LEFT:
			Battery -= 3
			if hasStopped() == true:
				Implement game loss
			else
				RobotController.robotTurn(LEFT)
				break
		case RIGHT:
			Battery -= 3
			if hasStopped() == true:
				Implement game loss
			else
				RobotController.robotTurn(RIGHT)
				break
		case AROUND:
			Battery -= 3
			if hasStopped() == true:
				Implement game loss
			else
				RobotController.robotTurn(RIGHT)
				Battery -= 3
				if hasStopped() == true:
					print("Could only rotate 90 degrees")
					Implement game loss
				else
					RobotController.robotTurn(RIGHT)
					break
		*/

	}

	@Override
	public void move(int distance) {
		/*
		int stepsLeft = distance
		int wallDist = this.distanceToObstacle(FORWARD)
		Maze maze = RobotController.getMaze()
		
		while stepsLeft > 0
			wallDist -= 1
			if wallDist < 0
				stopped = true
				Implement game loss
			else
				RobotController.robotMove()
				this.Odometer += 1
				Battery -= 6
				if hasStopped() == true:
					Implement game loss
				stepsLeft -= 1
		*/
	}

	@Override
	public void jump() {
		/*
		Battery -= 40
		RobotController.robotJump()
		this.Odometer += 1
		if hasStopped == true:
			Implement game loss
		*/
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAtExit() {
		/*
		exit = RobotController.getMaze().getExitPosition()
		if this.getCurrentPosition == exit
			return true
		return false
		*/
		return false;
	}

	@Override
	public boolean isInsideRoom() {
		/*
		return RobotController.getMaze().isInRoom(this.getCurrentPosition)
		*/
		return false;
	}

	@Override
	public boolean hasStopped() {
		/*
		if battery <= 0
			stopped = true
		return stopped
		*/
		return false;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		/*
		switch(direction)
		case FORWARD:
			if FrontSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in FORWARD direction");
			else
				return FrontSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery)
		case LEFT:
			if LeftSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in LEFT direction");
			else
				return LeftSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery)
		case RIGHT:
			if RightSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in RIGHT direction");
			else
				return RightSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery)
		case BACKWARD:
			if BackSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in BACKWARD direction");
			else
				return BackSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery)
		*/
		return 0;
	}

	@Override
	public boolean canSeeThroughTheExitIntoEternity(Direction direction) throws UnsupportedOperationException {
		/*
		Maze maze = RobotController.getMaze()
		
		switch(direction)
		case FORWARD:
			if FrontSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in FORWARD direction");
			if FrontSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery) == -1
				return true
			break
		case LEFT:
			if LeftSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in LEFT direction");
			if LeftSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery) == -1
				return true
			break
		case RIGHT:
			if RightSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in RIGHT direction");
			if RightSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery) == -1
				return true
			break
		case BACKWARD:
			if BackSensor == null
				throw new UnsupportedOperationException("Sensor does not exist in BACKWARD direction");
			if BackSensor.distanceToObstacle(this.getCurrentPosition(), direction, Battery) == -1
				return true
			break
		return false
		*/
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
	
	public void loser() {
		//Will be used in game loss implementation
	}
}

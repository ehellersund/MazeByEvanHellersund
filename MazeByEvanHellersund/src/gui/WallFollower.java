package gui;

import generation.Maze;
import gui.Robot.Direction;
import gui.Robot.Turn;

/*Class: 
 * 	WallFollower
 *Responsibilities:
 *	Navigates a robot to escape a maze
 *	Does so by looking at walls around it using sensors + incorporates a maze solving algorithm
 *	Is not like wizard, cannot "cheat" by checking the nearest tile to exit & just moving there
 *	Tells robot where to go, how much battery its operations are taking
 *	Stops robot in the event that it drives it into a wall
 *Collaborators
 *	RobotDriver (inherits from)
 *	Robot (drives it around, tells it what to do, etc.)
 *	UnreliableSensor (has to use this sensor to look around and tell where it is)
 *
 *Design plan:
 *	Follow walls to the left (obviously)
 *	If there is no wall to the left, rotate left, then forward
 *	If there is a wall left and in front, rotate right. If still wall in front, right again (backwards)
 *@author Evan Hellersund
 *
 */

public class WallFollower implements RobotDriver {
	Robot robot;
	Maze useMaze;
	int[] sensorConfig;

	@Override
	public void setRobot(Robot r) {
		//Front sensor
		if (sensorConfig[0] == 0) {
			UnreliableSensor front = new UnreliableSensor();
			front.setSensorDirection(Direction.FORWARD);
			front.setMaze(useMaze);
			//front.powerButton();
			r.addDistanceSensor(front, Direction.FORWARD);
		}
		else if (sensorConfig[0] == 1) {
			ReliableSensor front = new ReliableSensor();
			front.setSensorDirection(Direction.FORWARD);
			front.setMaze(useMaze);
			r.addDistanceSensor(front, Direction.FORWARD);
		}
		//Left sensor
		if (sensorConfig[1] == 0) {
			UnreliableSensor left = new UnreliableSensor();
			left.setSensorDirection(Direction.LEFT);
			left.setMaze(useMaze);
			//left.powerButton();
			r.addDistanceSensor(left, Direction.LEFT);
		}
		else if (sensorConfig[1] == 1) {
			ReliableSensor left = new ReliableSensor();
			left.setSensorDirection(Direction.LEFT);
			left.setMaze(useMaze);
			r.addDistanceSensor(left, Direction.LEFT);
		}
		//Right sensor
		if (sensorConfig[2] == 0) {
			UnreliableSensor right = new UnreliableSensor();
			right.setSensorDirection(Direction.RIGHT);
			right.setMaze(useMaze);
			//right.powerButton();
			r.addDistanceSensor(right, Direction.RIGHT);
		}
		else if (sensorConfig[2] == 1) {
			ReliableSensor right = new ReliableSensor();
			right.setSensorDirection(Direction.RIGHT);
			right.setMaze(useMaze);
			r.addDistanceSensor(right, Direction.RIGHT);
		}
		//Back sensor
		if (sensorConfig[3] == 0) {
			UnreliableSensor back = new UnreliableSensor();
			back.setSensorDirection(Direction.BACKWARD);
			back.setMaze(useMaze);
			//back.powerButton();
			r.addDistanceSensor(back, Direction.BACKWARD);
		}
		else if (sensorConfig[3] == 1) {
			ReliableSensor back = new ReliableSensor();
			back.setSensorDirection(Direction.BACKWARD);
			back.setMaze(useMaze);
			r.addDistanceSensor(back, Direction.BACKWARD);
		}
		//Now assigning the robot with its sensors
		robot = r;
	}

	@Override
	public void setMaze(Maze maze) {
		useMaze = maze;

	}

	@Override
	public boolean drive2Exit() throws Exception {
		
		while (robot.isAtExit() == false) {
			drive1Step2Exit();
		}
		return (useMaze.getDistanceToExit(robot.getCurrentPosition()[0], robot.getCurrentPosition()[1]) == 0 && useMaze.getExitPosition() == robot.getCurrentPosition()) ? true : false;
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
	//Follow walls to the left (obviously)
	//If there is no wall to the left, rotate left, then forward
	//If there is a wall left and in front, rotate right. If still wall in front, right again (backwards)
		
		if (robot.distanceToObstacle(Direction.FORWARD) == -1 && robot.distanceToObstacle(Direction.LEFT) == -1 && robot.distanceToObstacle(Direction.RIGHT) == -1 && robot.distanceToObstacle(Direction.BACKWARD) == -1) {
			System.out.println("All sensors are off");
			return false;
		}
		
		System.out.println("Left" + robot.distanceToObstacle(Direction.LEFT));
		if (robot.distanceToObstacle(Direction.LEFT) > 0) {
			robot.rotate(Turn.LEFT);
			robot.move(1);
		}
		else {
			System.out.println("Forward" + robot.distanceToObstacle(Direction.FORWARD));
			if (robot.distanceToObstacle(Direction.FORWARD) > 0) {
				robot.move(1);
			}
			else {
				System.out.println("Right" + robot.distanceToObstacle(Direction.RIGHT));
				if (robot.distanceToObstacle(Direction.RIGHT) > 0) {
					robot.rotate(Turn.RIGHT);
					robot.move(1);
				}
				else {
					robot.rotate(Turn.AROUND);
					robot.move(1);
				}
			}
		}
		return true;
	}

	@Override
	public float getEnergyConsumption() {
		return 3500 - robot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		return robot.getOdometerReading();
	}
	
	public void receiveConfig(String config) throws Exception {
		sensorConfig = new int[4];
		
		for (int i = 0; i < config.length(); i++) {
			if (Character.getNumericValue(config.charAt(i)) == 0 || Character.getNumericValue(config.charAt(i)) == 1) {
				sensorConfig[i] = Character.getNumericValue(config.charAt(i));
			}
			else {
			throw new Exception("Tried to set a sensor to a non-binary value"); 
			}
		}
	}
}

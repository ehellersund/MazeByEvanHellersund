package gui;

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Direction;
import gui.Robot.Turn;

/*Class: 
 * 	Wizard
 *Responsibilities:
 *	Uses maze info to find the shortest way out of a maze
 *	Uses sensors to CONFIRM locations of walls
 *	Tells robot where to go, how much battery its operations are taking
 *	Stops robot in the event that it drives it into a wall
 *Collaborators
 *	RobotDriver (inherits from)
 *	Robot (operates this robot, drives it to exit)
 *	Maze (uses the info here to find the exit)
 *	ReliableSensor (confirm locations of walls)
 *
 *@author Evan Hellersund
 *
 */

public class Wizard implements RobotDriver {
	Robot robot;
	Maze cheatMaze = null;

	@Override
	public void setRobot(Robot r) {
		ReliableSensor front = new ReliableSensor();
		front.setSensorDirection(Direction.FORWARD);
		front.setMaze(cheatMaze);
		r.addDistanceSensor(front, Direction.FORWARD);
		
		ReliableSensor left = new ReliableSensor();
		left.setSensorDirection(Direction.LEFT);
		left.setMaze(cheatMaze);
		r.addDistanceSensor(left, Direction.LEFT);
		
		ReliableSensor right = new ReliableSensor();
		right.setSensorDirection(Direction.RIGHT);
		right.setMaze(cheatMaze);
		r.addDistanceSensor(right, Direction.RIGHT);
		
		ReliableSensor back = new ReliableSensor();
		back.setSensorDirection(Direction.BACKWARD);
		back.setMaze(cheatMaze);
		r.addDistanceSensor(back, Direction.BACKWARD);
		
		robot = r;
	}

	@Override
	public void setMaze(Maze maze) {
		cheatMaze = maze;
	}

	@Override
	public boolean drive2Exit() throws Exception {
		int distance = cheatMaze.getDistanceToExit(robot.getCurrentPosition()[0], robot.getCurrentPosition()[1]);
		
		while (distance > 0) { 
			this.drive1Step2Exit();
			distance -= 1; }
			//Thread.sleep(1000);  //Delay to robot's movements
		return (distance == 0 && cheatMaze.getExitPosition() == robot.getCurrentPosition()) ? true : false;
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		int start[] = robot.getCurrentPosition();
		int dest[] = cheatMaze.getNeighborCloserToExit(start[0], start[1]);
		CardinalDirection dir = robot.getCurrentDirection();
		
		if (start == cheatMaze.getExitPosition() || dest == null) { //Should indicate that we are at exit position
			//Rotate to face exit
			if (cheatMaze.hasWall(start[0], start[1], CardinalDirection.North ) == false && cheatMaze.isValidPosition(start[0], start[1] - 1) == false) {
				switch(dir) {
				case North:
					break;
				case East:
					robot.rotate(Turn.RIGHT);
					break;
				case West:
					robot.rotate(Turn.LEFT);
					break;
				case South:
					robot.rotate(Turn.AROUND);
					break;
				}
			}
			if (cheatMaze.hasWall(start[0], start[1], CardinalDirection.East) == false && cheatMaze.isValidPosition(start[0] + 1, start[1]) == false) {
				switch(dir) {
				case North:
					robot.rotate(Turn.LEFT);
					break;
				case East:
					break;
				case West:
					robot.rotate(Turn.AROUND);
					break;
				case South:
					robot.rotate(Turn.RIGHT);
					break;
				}
			}
			if (cheatMaze.hasWall(start[0], start[1], CardinalDirection.West) == false && cheatMaze.isValidPosition(start[0] - 1, start[1]) == false) {
				switch(dir) {
				case North:
					robot.rotate(Turn.RIGHT);
					break;
				case East:
					robot.rotate(Turn.AROUND);
					break;
				case West:
					break;
				case South:
					robot.rotate(Turn.LEFT);
					break;
				}
			}
			if (cheatMaze.hasWall(start[0], start[1], CardinalDirection.South) == false && cheatMaze.isValidPosition(start[0], start[1] + 1) == false) {
				switch(dir) {
				case North:
					robot.rotate(Turn.AROUND);
					break;
				case East:
					robot.rotate(Turn.LEFT);
					break;
				case West:
					robot.rotate(Turn.RIGHT);
					break;
				case South:
					break;
				}
			}
			//robot.move(1);
			return false;
		}
		
		if (start[0] > dest[0]) {	//West
			switch(dir) {
			case North:
				robot.rotate(Turn.RIGHT);
				robot.move(1);
				break;
			case East:
				robot.rotate(Turn.AROUND);
				robot.move(1);
				break;
			case West:
				robot.move(1);
				break;
			case South:
				robot.rotate(Turn.LEFT);
				robot.move(1);
				break;
			}
		}
		if (start[0] < dest[0]) {	//East
			switch(dir) {
			case North:
				robot.rotate(Turn.LEFT);
				robot.move(1);
				break;
			case East:
				robot.move(1);
				break;
			case West:
				robot.rotate(Turn.AROUND);
				robot.move(1);
				break;
			case South:
				robot.rotate(Turn.RIGHT);
				robot.move(1); 
				break;
			}
		}
		if (start[1] > dest[1]) {	//North
			switch(dir) {
			case North:
				robot.move(1);
				break;
			case East:
				robot.rotate(Turn.RIGHT);
				robot.move(1);
				break;
			case West:
				robot.rotate(Turn.LEFT);
				robot.move(1);
				break;
			case South:
				robot.rotate(Turn.AROUND);
				robot.move(1);
				break;
			}
		}
		if (start[1] < dest[1]) {	//South
			switch(dir) {
			case North:
				robot.rotate(Turn.AROUND);
				robot.move(1);
				break;
			case East:
				robot.rotate(Turn.LEFT);
				robot.move(1);
				break;
			case West:
				robot.rotate(Turn.RIGHT);
				robot.move(1);
				break;
			case South:
				robot.move(1);
				break;
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
}

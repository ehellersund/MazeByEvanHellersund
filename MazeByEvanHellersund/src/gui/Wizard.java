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
	ReliableRobot robot = null;
	Maze cheatMaze = null;

	@Override
	public void setRobot(Robot r) {
		r = new ReliableRobot();
		
		ReliableSensor front = new ReliableSensor();
		front.setSensorDirection(Direction.FORWARD);
		r.addDistanceSensor(front, Direction.FORWARD);
		
		ReliableSensor left = new ReliableSensor();
		left.setSensorDirection(Direction.LEFT);
		r.addDistanceSensor(left, Direction.LEFT);
		
		ReliableSensor right = new ReliableSensor();
		right.setSensorDirection(Direction.RIGHT);
		r.addDistanceSensor(right, Direction.RIGHT);
		
		ReliableSensor back = new ReliableSensor();
		back.setSensorDirection(Direction.BACKWARD);
		r.addDistanceSensor(back, Direction.BACKWARD);
		
		robot = (ReliableRobot) r;
	}

	@Override
	public void setMaze(Maze maze) {
		cheatMaze = robot.giveMaze();
	}

	@Override
	public boolean drive2Exit() throws Exception {
		int distance = cheatMaze.getDistanceToExit(robot.getCurrentPosition()[0], robot.getCurrentPosition()[1]);
		
		while (distance > 0) { 
			this.drive1Step2Exit();
			distance -= 1; }
		return (distance == 0 && cheatMaze.getExitPosition() == robot.getCurrentPosition()) ? true : false;
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		int start[] = robot.getCurrentPosition();
		int dest[] = cheatMaze.getNeighborCloserToExit(start[0], start[1]);
		CardinalDirection dir = robot.getCurrentDirection();
		
		if (start == cheatMaze.getExitPosition()) {
			return false;
		}
		if (start[0] > dest[0]) {	//West
			switch(dir) {
			case North:
				robot.rotate(Turn.LEFT);
				robot.move(1);
			case East:
				robot.rotate(Turn.AROUND);
				robot.move(1);
			case West:
				robot.move(1);
			case South:
				robot.rotate(Turn.RIGHT);
				robot.move(1);
			}
		}
		if (start[0] < dest[0]) {	//East
			switch(dir) {
			case North:
				robot.rotate(Turn.RIGHT);
				robot.move(1);
			case East:
				robot.move(1);
			case West:
				robot.rotate(Turn.AROUND);
				robot.move(1);
			case South:
				robot.rotate(Turn.LEFT);
				robot.move(1); 
			}
		}
		if (start[1] > dest[1]) {	//North
			switch(dir) {
			case North:
				robot.move(1);
			case East:
				robot.rotate(Turn.RIGHT);
				robot.move(1);
			case West:
				robot.rotate(Turn.LEFT);
				robot.move(1);
			case South:
				robot.rotate(Turn.AROUND);
				robot.move(1);
			}
		}
		if (start[1] < dest[1]) {	//South
			switch(dir) {
			case North:
				robot.rotate(Turn.AROUND);
				robot.move(1);
			case East:
				robot.rotate(Turn.RIGHT);
				robot.move(1);
			case West:
				robot.rotate(Turn.LEFT);
				robot.move(1);
			case South:
				robot.move(1);
			}
		}
		return true;
	}

	@Override
	public float getEnergyConsumption() {
		return 3500 - robot.Battery[0];
	}

	@Override
	public int getPathLength() {
		return robot.Odometer;
	}
}

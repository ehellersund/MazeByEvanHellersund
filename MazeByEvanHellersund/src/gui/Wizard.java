package gui;

import generation.Maze;

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
	/*
	ReliableRobot robot 
	Maze cheatMaze
	*/

	@Override
	public void setRobot(Robot r) {
		/*
		Robot r = new ReliableRobot
		
		ReliableSensor front = new ReliableSensor
		front.setSensorDirection(FORWARD)
		r.FrontSensor = front
		
		ReliableSensor left = new ReliableSensor
		left.setSensorDirection(LEFT)
		r.LeftSensor = left
		
		ReliableSensor right = new ReliableSensor
		right.setSensorDirection(RIGHT)
		r.RightSensor = right
		
		ReliableSensor back = new ReliableSensor
		back.setSensorDirection(BACKWARD)
		r.BackSensor = back
		
		robot = r
		*/
	}

	@Override
	public void setMaze(Maze maze) {
		/*
		cheatMaze = robot.RobotController.getMaze()
		*/
	}

	@Override
	public boolean drive2Exit() throws Exception {
		/*
		int distance = cheatMaze.getDistanceToExit()
		
		while distance > 0 
			this.drive1Step2Exit()
			distance -= 1
		*/
		return false;
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		/*
		int start[] = robot.getCurrentPosition()
		int dest[] = cheatMaze.getNeighborCloserToExit(pos[0], pos[1])
		CardinalDirection dir = robot.getCurrentDirection()
		
		if start[0] > dest[0]	//West
			switch(dir)
			case NORTH:
				robot.rotate(LEFT)
				robot.move(1)
			case EAST:
				robot.rotate(AROUND)
				robot.move(1)
			case WEST:
				robot.move(1)
			case SOUTH:
				robot.rotate(RIGHT)
				robot.move(1)				
		if start[0] < dest[0]	//East
			switch(dir)
			case NORTH:
				robot.rotate(RIGHT)
				robot.move(1)
			case EAST:
				robot.move(1)
			case WEST:
				robot.rotate(AROUND)
				robot.move(1)
			case SOUTH:
				robot.rotate(LEFT)
				robot.move(1)	
		if start[1] > dest[1]	//North
			switch(dir)
			case NORTH:
				robot.move(1)
			case EAST:
				robot.rotate(RIGHT)
				robot.move(1)
			case WEST:
				robot.rotate(LEFT)
				robot.move(1)
			case SOUTH:
				robot.rotate(AROUND)
				robot.move(1)	
		if start[1] < dest[1]	//South
			switch(dir)
			case NORTH:
				robot.rotate(AROUND)
				robot.move(1)
			case EAST:
				robot.rotate(RIGHT)
				robot.move(1)
			case WEST:
				robot.rotate(LEFT)
				robot.move(1)
			case SOUTH:
				robot.move(1)	
		*/
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		/*
		return 3500 - robot.Battery
		*/
		return 0;
	}

	@Override
	public int getPathLength() {
		/*
		return robot.Odometer
		*/
		return 0;
	}
}

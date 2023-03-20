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
 */

public class Wizard implements RobotDriver {

	@Override
	public void setRobot(Robot r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaze(Maze maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean drive2Exit() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drive1Step2Exit() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPathLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

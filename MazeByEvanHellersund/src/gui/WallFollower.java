package gui;

import generation.Maze;

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
 *@author Evan Hellersund
 *
 */

public class WallFollower implements RobotDriver {

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

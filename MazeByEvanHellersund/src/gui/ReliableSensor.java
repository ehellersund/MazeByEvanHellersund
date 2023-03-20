package gui;

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Direction;

/*Class: 
 * 	ReliableSensor
 *Responsibilities:
 *	Looks at maze to figure out how far away from a wall a sensor is
 *	Passes this information to whatever RobotDriver is being used so that it can navigate the maze
 *	Also passes what direction it is looking, and how much energy it cost to use the sensor
 *	Does NOT implement failing & repairing because it is reliable
 *Collaborators
 *	Maze (uses it so that it can get the necessary info for distance from walls + "see" them)
 *	Wizard (used by it to confirm locations of walls)
 *	WallFollower (sort of, since this implements UnreliableSensor)
 *
 *@author Evan Hellersund
 *
 */

public class ReliableSensor implements DistanceSensor {

	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaze(Maze maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSensorDirection(Direction mountedDirection) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getEnergyConsumptionForSensing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

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
	
	Maze useMaze = null;
	Direction orientation = null;

	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		int distance = 0;
		int checkPos[] = currentPosition;
		
		if (useMaze == null) {
			throw new IllegalArgumentException("Tried to calculate distance for a null maze"); 
		}
		
		while (true) {
			if (checkPos[0] == useMaze.getExitPosition()[0] && checkPos[1] == useMaze.getExitPosition()[1]) {
				return Integer.MAX_VALUE;
			}
			if (useMaze.isValidPosition(checkPos[0], checkPos[1]) == false) {
				throw new IllegalArgumentException("Distance sensor somehow ended up outside maze without passing the exit"); }
			
			if (useMaze.hasWall(checkPos[0], checkPos[1], currentDirection)) {
				return distance; }
			
			distance += 1;
			switch(currentDirection) {
			case North : 
				checkPos[1] -= 1;
				break;
			case East : 
				checkPos[0] += 1;
				break;
			case South : 
				checkPos[1] += 1;
				break;
			case West : 
				checkPos[0] -= 1;
				break;
			}
		}
	}

	@Override
	public void setMaze(Maze maze) {
		useMaze = maze;
	}

	@Override
	public void setSensorDirection(Direction mountedDirection) {
		orientation = mountedDirection;

	}

	@Override
	public float getEnergyConsumptionForSensing() {
		return 3;
	}

	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported for ReliableSensor");
	}

	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported for ReliableSensor");
	}
}

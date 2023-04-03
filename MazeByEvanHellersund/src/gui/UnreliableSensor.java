package gui;

import generation.CardinalDirection;
import generation.Maze;
import gui.Robot.Direction;

/*Class: 
 * 	UnreliableSensor
 *Responsibilities:
 *	Looks at maze to figure out how far away from a wall a sensor is
 *	Passes this information to WallFollower so that it can navigate the maze
 *	Also passes what direction it is looking, and how much energy it cost to use the sensor
 *	Implements failing & repairing 
 *Collaborators
 *	Maze (uses it so that it can get the necessary info for distance from walls + "see" them)
 *	WallFollower (it is its sensor)
 *
 *@author Evan Hellersund
 *
 */

public class UnreliableSensor extends ReliableSensor {
	
	Maze useMaze = null;
	Direction orientation = null;
	boolean off = false;

	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		int distance = 0;
		int checkPos[] = currentPosition;
		
		if (useMaze == null) {
			throw new IllegalArgumentException("Tried to calculate distance for a null maze"); 
		}
		
		if (powersupply[0] - 6 <= 0) { //Not enough power
			throw new Exception("Not enough power for operation");
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
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported for ReliableSensor");
	}

	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported for ReliableSensor");
	}
	
	public boolean isOff() {
		return off;
	}
	
	public void powerButton() {
		off = ! off;
	}

}

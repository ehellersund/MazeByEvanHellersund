
package gui;

import gui.Robot.Direction;

//@author Evan Hellersund
public class UnreliableRobot extends ReliableRobot {
	boolean paused = false;
	
	@Override
	public void startFailureAndRepairProcess(Direction direction, int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
	}
	
	@Override
	public void stopFailureAndRepairProcess(Direction direction) throws UnsupportedOperationException {
		
	}
	
	


}

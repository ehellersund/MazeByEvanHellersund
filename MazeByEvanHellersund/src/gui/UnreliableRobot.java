
package gui;

import gui.Robot.Direction;
import generation.Maze;

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
	//It cheats a little to make sure you aren't going to make an illegal move, and implements loss if you do
	@Override
	public void move(int distance) throws Exception, UnsupportedOperationException {
		int stepsLeft = distance;
		
		while (stepsLeft > 0) {
			if (RobotController.getMaze().hasWall(getCurrentPosition()[0], getCurrentPosition()[1], getCurrentDirection())) {
				stopped = true;
				stepsLeft = 0;
				loser();
				}
			else {
				Battery[0] -= 6;
				if (hasStopped() == true) {
					loser();
				}
				RobotController.robotMove();
				Odometer += 1;
				stepsLeft -= 1;
			}
		}
	}
	
	@Override
	public int distanceToObstacle(Direction direction) throws Exception, UnsupportedOperationException {
		int distance = -20;
		
		switch(direction) {
		//Wants to know forward distance
		case FORWARD:
			if (FrontSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in FORWARD direction"); }
			else {
				if (FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
					System.out.println("Front sensor is off");
					if (LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.LEFT), Battery) == -1) {
						System.out.println("Left sensor is off");
					}
					else {
						rotate(Turn.RIGHT);
						distance = LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.LEFT), Battery);
						rotate(Turn.LEFT);
						break;
					}
				}
				else {
					return FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
			}
		//--------------------------------------------------
		case LEFT:
			if (LeftSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in LEFT direction"); }
			else {
				return LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
		case RIGHT:
			if (RightSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in RIGHT direction"); }
			else {
				return RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
		case BACKWARD:
			if (BackSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in BACKWARD direction"); }
			else {
				return BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
		}
		return distance;
	}
	


}

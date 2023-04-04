
package gui;

import gui.Robot.Direction;
import gui.Robot.Turn;
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
		int distance = 0;
		
		switcher:
		switch(direction) {
		//Wants to know forward distance
		case FORWARD:
			if (FrontSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in FORWARD direction"); }
			else {
				if (FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
					System.out.println("Front sensor is off");
					if (LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
						System.out.println("Left sensor is off");
						if (RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
							System.out.println("Right sensor is off");
							if (BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
								System.out.println("All sensors are off");
								//Insert code waiting for repair
							}
							else {
								rotate(Turn.LEFT);
								rotate(Turn.LEFT);
								distance = BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.BACKWARD), Battery);
								rotate(Turn.RIGHT);
								rotate(Turn.RIGHT);
								break switcher;
							}
						}
						else {
							rotate(Turn.LEFT);
							distance = RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.RIGHT), Battery);
							rotate(Turn.RIGHT);
							break switcher;
						}
					}
					else {
						rotate(Turn.RIGHT);
						distance = LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.LEFT), Battery);
						rotate(Turn.LEFT);
						break switcher;
					}
				}
				else {
					return FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
			}
			break switcher;
		//--------------------------------------------------
		case LEFT:
			if (LeftSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in LEFT direction"); }
			else {
				if (LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
					System.out.println("Left sensor is off"); 
					if (FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
						System.out.println("Front sensor is off");
						if (BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
							System.out.println("Back sensor is off");
							if (RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
								System.out.println("All sensors are off"); 
								//Insert code waiting for repair
							}
							else {
								rotate(Turn.LEFT);
								rotate(Turn.LEFT);
								distance = RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.RIGHT), Battery);
								rotate(Turn.RIGHT);
								rotate(Turn.RIGHT);
								break switcher;
							}
						}
						else {
							rotate(Turn.RIGHT);
							distance = BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.BACKWARD), Battery);
							rotate(Turn.LEFT);
							break switcher;
						}
					}
					else {
						rotate(Turn.LEFT);
						distance = FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.FORWARD), Battery);
						rotate(Turn.RIGHT);
						break switcher;
					}
				}
				else {
				return LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
			}
			break switcher;
		//--------------------------------------------------
		case RIGHT:
			if (RightSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in RIGHT direction"); }
			else {
				if (RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
					System.out.println("Right sensor is off");
					if (FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
						System.out.println("Front sensor is off");
						if (BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
							System.out.println("Back sensor is off");
							if (LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
								System.out.println("All sensors are off");
								//Insert code waiting for repair
							}
							else {
								rotate(Turn.RIGHT);
								rotate(Turn.RIGHT);
								distance = LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.LEFT), Battery);
								rotate(Turn.LEFT);
								rotate(Turn.LEFT);
								break switcher;
							}
						}
						else {
							rotate(Turn.LEFT);
							distance = BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.BACKWARD), Battery);
							rotate(Turn.RIGHT);
							break switcher;
						}
					}
					else {
						rotate(Turn.RIGHT);
						distance = FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.FORWARD), Battery);
						rotate(Turn.LEFT);
						break switcher;
					}
				}
				else {
					return RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
			}
			break switcher;
		//--------------------------------------------------
		case BACKWARD:
			if (BackSensor == null) {
				throw new UnsupportedOperationException("Sensor does not exist in BACKWARD direction"); }
			else {
				if (BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
					System.out.println("Back sensor is off");
					if (LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
						System.out.println("Left sensor is off");
						if (RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
							System.out.println("Right sensor is off");
							if (FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery) == -1) {
								System.out.println("All sensors are off");
								//Insert code waiting for repair
							}
							else {
								rotate(Turn.LEFT);
								rotate(Turn.LEFT);
								distance = FrontSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.FORWARD), Battery);
								rotate(Turn.RIGHT);
								rotate(Turn.RIGHT);
								break switcher;
							}
						}
						else {
							rotate(Turn.RIGHT);
							distance = RightSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.RIGHT), Battery);
							rotate(Turn.LEFT);
							break switcher;
						}
					}
					else {
						rotate(Turn.LEFT);
						distance = LeftSensor.distanceToObstacle(getCurrentPosition(), dToCD(Direction.LEFT), Battery);
						rotate(Turn.RIGHT);
						break switcher;
					}
				}
				else {
					return BackSensor.distanceToObstacle(getCurrentPosition(), dToCD(direction), Battery); }
			}
			break switcher;
		}
		return distance;
	}
}

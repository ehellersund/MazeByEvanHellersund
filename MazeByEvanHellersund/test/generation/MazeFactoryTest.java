package generation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeFactoryTest {
	public MazeFactory mazefac;
	public DefaultOrder order;

	//Maze output;
	
	@BeforeEach
	public void setUp () {
		mazefac = new MazeFactory();
		order = new DefaultOrder();
		mazefac.order(order);
		mazefac.waitTillDelivered();
		order.deliver(order.maze);
	}
	
	@Test
	public void hasValidExit() {
		//This test will make sure that there is a reachable exit in the maze
		/*start = maze.getStartPosition
		 *distance = maze.getDistanceToEnd[start]
		 *assertNotNull(distance)
		*/

		// Some code I had just been working on
		//int[] spawn = order.maze.getStartingPosition();
		//int dist = order.maze.getDistanceToExit(spawn[0], spawn[1]);
		//System.out.println(dist);	
		//Floorplan floor = order.maze.getFloorplan();
		
	}
	@Test
	public void singleExit() {
		/*This test will make sure the maze generated with only one exit possible
		 * floor = maze.getFloorplan
		 * int size = maze.getHeight
		 * int numExits = 0
		 * for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (floor.isExitPosition(i, j) {
            	numExits++
            }
            assertEquals(numExits, 1)
		 * 
		 */
		
	}
	public void maxDistFromExit() {
		/*This test will ensure that the start of the maze is the furthest position from the exit
		 * and also that the starting point allows you to reach the exit (somewhat redundant)
		 * start[] = maze.getStartPosition
		 * int distance = maze.getDistanceToEnd[start]
		 * int size = maze.getHeight
		 * newdist = 0
		 * for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (maze.getDistanceToEnd(i, j)>newdist) {
            	newdist = maze.GetDistanceToEnd(i, j)
            }
            assertTrue(maze.getDistanceToEnd[start] > newdist)
		 */
	}
	public void noLoops() {
		
	}
	public void noBadSpots() {
		
	}
	public void wallTest() {
		
	}
	public static void main() {
	}
}
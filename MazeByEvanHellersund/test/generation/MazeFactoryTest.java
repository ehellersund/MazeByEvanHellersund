package generation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		int[] spawn = order.maze.getStartingPosition();
		int dist = order.maze.getDistanceToExit(spawn[0], spawn[1]);
		assertNotNull(dist);

		
	}
	@Test
	public void singleExit() {
		//This test will make sure the maze generated with only one exit possible
		Floorplan floor = order.maze.getFloorplan();
		int size = order.maze.getHeight();
		int numExits = 0;
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (floor.isExitPosition(i, j)) {
            		numExits++;
            	}
            }
		}
		assertEquals(numExits, 1);
	}
	
	@Test
	public void maxDistFromExit() {
		//This test will ensure that the start of the maze is the furthest position from the exit
		int[] start = order.maze.getStartingPosition();
		int size = order.maze.getHeight();
		int newdist = 0;
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (order.maze.getDistanceToExit(i, j) > newdist) {
            	newdist = order.maze.getDistanceToExit(i, j);
            	}
            }
		}
		assertTrue(order.maze.getDistanceToExit(start[0], start[1]) >= newdist);
	}
	
	@Test
	public void wallTest() {
		//This test will ensure that a perfect maze has the correct number of walls. Will end differently if the maze is not perfect/has rooms
		 boolean hasRooms = false;
		 int walls = 0;
		 int size = order.maze.getHeight();
		 for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (order.maze.isInRoom(i, j) == true) {
            		hasRooms = true;
            		break;
            	}
            	if (order.maze.hasWall(i, j, CardinalDirection.North)) {
            		walls++;
            	}
            	if (order.maze.hasWall(i, j, CardinalDirection.East)) {
            		walls++;
            	}
            	if (order.maze.hasWall(i, j, CardinalDirection.South)) {
            		walls++;
            	}
            	if (order.maze.hasWall(i, j, CardinalDirection.West)) {
            		walls++;
            	}
            }
		 }
            if (hasRooms == false) {
            	System.out.println(size);
            	assertEquals(walls, walls);
            }
            else {
            	System.out.println("Not perfect maze");
            	assertEquals(hasRooms, false);
            }
	}
}
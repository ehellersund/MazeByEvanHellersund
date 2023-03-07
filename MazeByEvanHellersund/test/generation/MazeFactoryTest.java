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
	public void hasOneExit() {
		int height = order.maze.getHeight();
		//System.out.println(height);	
		assertEquals(height, 12);
		//Floorplan floor = order.maze.getFloorplan();
		
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
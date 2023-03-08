package generation;

import java.util.HashMap;
import java.util.Random;

public class MazeBuilderBoruvka extends MazeBuilder {
	public HashMap<Wallboard,Integer> edgeWeights = new HashMap<Wallboard,Integer>();
	public Floorplan floor = new Floorplan(width, height);
	int[][] dog;
	int cat;
	
	public MazeBuilderBoruvka(){
		super();
	}
	
	public int getEdgeWeight(int x, int y, CardinalDirection cd) {
		Wallboard wall = new Wallboard(x, y, cd);
		if (edgeWeights.containsKey(wall)) {
			return edgeWeights.get(wall);
		}
		Wallboard opposite = new Wallboard(wall.getNeighborX(), wall.getNeighborY(), cd.oppositeDirection());
		if (edgeWeights.containsKey(opposite)) {
			return edgeWeights.get(opposite);
		}
		else {
			Random random = new Random();
			int edgeWeight = random.nextInt(100000);
			edgeWeights.put(opposite, edgeWeight);
			return edgeWeight;
		}
	}
	
	public void mergeComponents() {
		int[][] dog = new int[width][height];
		for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
            	cat++;
            }
		}
		
		
	}
	
	@Override protected void generatePathways() {
		//Boruvka's Pseudocode
		/* 	X	Step 1: Initialize maze of all wallboards
		 * 	X	Step 2: Assign (distinct) weights to all internal wallboards
		 * 		Step 3: Create a forest for each 1-tree vertex (each square)
		 * 		Step 4: For each square, check its wallboards and add the cheaper wallboard to the forest
		 * 		Step 5: Then for each new component find the cheapest connecting wallboard
		 * 		Step 6: Repeat until the full minimum tree is made
		 * 		Step 7: Remove the walls 
		 */
		
		
		
		
		
	}

}

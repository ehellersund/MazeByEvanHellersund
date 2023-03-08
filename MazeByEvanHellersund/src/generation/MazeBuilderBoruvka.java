package generation;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Random;

public class MazeBuilderBoruvka extends MazeBuilder {
	public HashMap<Wallboard,Integer> edgeWeights = new HashMap<Wallboard,Integer>();
	
	public MazeBuilderBoruvka(){
		super();
	}
	
	public int getEdgeWeight(int x, int y, CardinalDirection cd) {
		Wallboard wall = new Wallboard(x, y, cd);
		if (edgeWeights.containsKey(wall)) {
			return edgeWeights.get(wall);
		}
		Wallboard opposite = new Wallboard(x, y, cd.oppositeDirection());
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
	
	@Override protected void generatePathways() {
		//Boruvka's Pseudocode
		/* Step 1: Initialize maze of all wallboards
		 * Step 2: Assign (distinct) weights to all internal wallboards
		 * Step 3: Create a forest for each 1-tree vertex (each square)
		 * Step 4: For each square, check its wallboards and add the cheaper wallboard to the forest
		 * Step 5: Then for each new component find the cheapest connecting wallboard
		 * Step 6: Repeat until the full minimum tree is made
		 * Step 7: Remove the walls 
		 */
	}

}

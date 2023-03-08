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
		/* Find minimum weight edge
		 * Mark it as visited
		 * 
		 * 
		 */
	}

}

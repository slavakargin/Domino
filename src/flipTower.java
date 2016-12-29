/**
 * 
 */
import edu.princeton.cs.algs4.Stack;


/**
 * @author vladislavkargin
 *
 */
public class flipTower {
   Stack<Tile> tower;
   Stack<Integer> directions;
   Tiling tiling;
   Tile tile;
   int direction;
	/**
	 * 
	 */
	public flipTower(Tiling tiling, Tile tile, int direction) {
		//We will assume that this initial tile belongs to the tiling
		// and that the direction is appropriate (for example, the vertical tile 
		//never comes with the top or bottom directions.)
		tower = new Stack<Tile>();
		this.tiling = tiling;
		this.tile = tile;
		this.direction = direction;
	}
	
	private void flip() {
		Tile tile0;
		Integer dir0;
		tower.push(tile);
		directions.push(direction);
		while (!tower.isEmpty()){
			tile0 = tower.pop(); // Current tile and direction on the stack.
			dir0 = directions.pop();
			switch (tile0.type()) {
			   case 0: // tile is vertical 
				   switch (dir0) {
			           case 1: // direction is to the left
			        	   break;
			           case 3: // direction is to the right
			        	   
			       }
			   break;
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

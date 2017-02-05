/**
 * 
 */
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


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
		directions = new Stack<Integer>();
		this.tiling = tiling;
		this.tile = tile;
		this.direction = direction;
	}
	
	public void flip() {
		Tile tile0;
		Integer dir0;
		tower.push(tile);
		directions.push(direction);
		while (!tower.isEmpty()){
  		  //StdOut.println("Tile Stack:" + tower);
  		  //StdOut.println("Direction Stack:" + directions);
			tile0 = tower.peek(); // Current tile and direction on the stack.
			dir0 = directions.peek();
			switch (tile0.type()) {
			   case 0: // tile is vertical 
				   switch (dir0) {
				       case 0: //direction is down, we cannot do any flip
				    	   return;
			           case 1: // direction is to the left
			        	   if (tile0.xmin() == 0.){ //the tile is on the left border and flip is impossible
			        		   return;
			        	   }
			        	   Tile tile1 = new Tile(tile0.xmin() - 1, tile0.ymin(), tile0.xmin(), tile0.ymax());
			        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top from the stack, 
			        		                             //and continue to the next tile in the stack.
			        		   tiling.flip(tile0, tile1, true);
			        		   tile0 = tower.pop();
			        		   dir0 = directions.pop();
			        		  // StdOut.println("Tile Stack:" + tower);
			        		  // StdOut.println("Direction Stack:" + tower);
			        		   continue;
			        	   } else {
			        		   tile1 = tiling.findTile(tile0.xmin() - .5, tile0.ymin() + .5);
			        		   switch (tile1.type()) {
			        		   case 0: //the next tile on the left is vertical
			        			   tower.push(tile1);
			        			   directions.push(1);
			        			  // StdOut.println("Stack:" + tower);
			        			   continue;
			        		   case 1: //the next tile on the left is square  
			        			   if (tile1.ymin() < tile0.ymin()) {
			        				   tower.push(tile1);
			        				   directions.push(1);
			        				//   StdOut.println("Stack:" + tower);
			        			   } else {
			        				   tower.push(tile1);
			        				   directions.push(2); //go up
			        			   }
			        			   break;
			        		   case 2: //the tile is horizontal
			        			   tower.push(tile1);
			        			   directions.push(2); //go up
			        			   break;
			        		   }
			        	   }
			        	   break;
			           case 2: //direction is up, no flip possible
			        	   return;
			           case 3: // direction is to the right
			        	   if (tile0.xmax() == tiling.N){ //the tile is on the right border and flip is impossible
			        		   return;
			        	   }
			        	   tile1 = new Tile(tile0.xmax(), tile0.ymin(), tile0.xmax() + 1, tile0.ymax());
			        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top tile from the stack, 
			        		                             //and continue to the next tile in the stack.
			        		   tiling.flip(tile0, tile1, true);
			        		   tile0 = tower.pop();
			        		   dir0 = directions.pop();
			        		   continue;
			        	   } else {
			        		   tile1 = tiling.findTile(tile0.xmax() + .5, tile0.ymin() + .5);
			        		   switch (tile1.type()) {
			        		   case 0: //the next tile on the right is vertical
			        			   tower.push(tile1);
			        			   directions.push(3);
			        			   continue;
			        		   case 1: //the next tile on the left is square  
			        			   if (tile1.ymin() < tile0.ymin()) {
			        				   tower.push(tile1);
			        				   directions.push(3);
			        			   } else {
			        				   tower.push(tile1);
			        				   directions.push(2); //go up
			        			   }
			        			   continue;
			        		   case 2: //the tile is horizontal
			        			   tower.push(tile1);
			        			   directions.push(2); //go up
			        			   continue;
			        		   }
			        	   }
			        	   break;
			        	   
			       }
			   break;
			   case 1: //the tile is square;
				   switch (dir0) {
				   case 0: //direction down
		        	   if (tile0.ymin() == 0){ //the tile is on the bottom border and any flip is impossible
		        		   return;
		        	   }
		        	  Tile tile1 = new Tile(tile0.xmin(), tile0.ymin() - 2, tile0.xmax(), tile0.ymin());
		        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top tile from the stack, 
		        		                             //and continue to the next tile in the stack.
		        		   tiling.flip(tile0, tile1, false);
		        		   tile0 = tower.pop();
		        		   dir0 = directions.pop();
		        		   continue;
		        	   }  else {
		        		   tile1 = tiling.findTile(tile0.xmin() + .5, tile0.ymin() - .5);
		        		   switch (tile1.type()) {
		        		   case 0: //the next tile at the bottom is vertical
		        			   tower.push(tile1);
		        			   directions.push(3);
		        			   continue;
		        		   case 1: //the next tile at the bottom is square  
		        				   tower.push(tile1);
		        				   directions.push(0);
		        			   continue;
		        		   case 2: //the tile is horizontal
		        			   tower.push(tile1);
		        			   directions.push(0); //go down
		        			   continue;
		        		   }   
		        	   }
				   case 1: //direction to the left 
					   if (tile0.xmin() == 0){ //the tile is on the left border and any flip is impossible
		        		   return;
		        	   }
		        	   tile1 = new Tile(tile0.xmin() - 2, tile0.ymin(), tile0.xmin(), tile0.ymax());
		        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top tile from the stack, 
		        		                             //and continue to the next tile in the stack.
		        		   tiling.flip(tile0, tile1, true);
		        		   tile0 = tower.pop();
		        		   dir0 = directions.pop();
		        		   continue;
		        	   }  else {
		        		   tile1 = tiling.findTile(tile0.xmin() - .5, tile0.ymin() + .5);
		        		   switch (tile1.type()) {
		        		   case 0: //the next tile at the left is vertical
		        			   tower.push(tile1);
		        			   directions.push(1);
		        			   continue;
		        		   case 1: //the next tile at the bottom is square  
		        				   tower.push(tile1);
		        				   directions.push(1);
		        			   continue;
		        		   case 2: //the tile is horizontal
		        			   tower.push(tile1);
		        			   directions.push(2); //go up
		        			   continue;
		        		   }   
		        	   }
					   break;
				   case 2: //direction is up
					   if (tile0.ymax() == tiling.N){ //the tile is on the top border and any flip is impossible
		        		   return;
		        	   }
		        	   tile1 = new Tile(tile0.xmin(), tile0.ymax(), tile0.xmax(), tile0.ymax() + 2);
		        	   //StdOut.println("New tile: " + tile1);
		        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top tile from the stack, 
		        		                             //and continue to the next tile in the stack.
		        		   tiling.flip(tile0, tile1, false);
		        		   tile0 = tower.pop();
		        		   dir0 = directions.pop();
		        		   //StdOut.println("New tile was in the tiling, flip performed, tile0 removed from stack");
		        		   continue;
		        	   }  else {
		        		   tile1 = tiling.findTile(tile0.xmin() + .5, tile0.ymax() + .5);
		        		   switch (tile1.type()) {
		        		   case 0: //the next tile is vertical
		        			   tower.push(tile1);
		        			   directions.push(3);
		        			   continue;
		        		   case 1: //the next tile is square  
		        				   tower.push(tile1);
		        				   directions.push(2);
		        			   continue;
		        		   case 2: //the tile is horizontal
		        			   tower.push(tile1);
		        			   directions.push(2); //go up
		        			   continue;
		        		   }   
		        	   }
		        	   break;
				   case 3: //direction is to the right
					   if (tile0.xmax() == tiling.N){ //the tile is on the right border and any flip is impossible
		        		   return;
		        	   }
		        	   tile1 = new Tile(tile0.xmax(), tile0.ymin(), tile0.xmax() + 2, tile0.ymax());
		        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top tile from the stack, 
		        		                             //and continue to the next tile in the stack.
		        		   tiling.flip(tile0, tile1, true);
		        		   tile0 = tower.pop();
		        		   dir0 = directions.pop();
		        		   continue;
		        	   }  else {
		        		   tile1 = tiling.findTile(tile0.xmax() + .5, tile0.ymin() + .5);
		        		   switch (tile1.type()) {
		        		   case 0: //the next tile is vertical
		        			   tower.push(tile1);
		        			   directions.push(3);
		        			   continue;
		        		   case 1: //the next tile is square  
		        				   tower.push(tile1);
		        				   directions.push(3);
		        			   continue;
		        		   case 2: //the tile is horizontal
		        			   tower.push(tile1);
		        			   directions.push(2); //go up
		        			   continue;
		        		   }   
		        	   }
		        	   break;
				   }
				   break;
			   case 2: //the tile is horizontal
				   switch (dir0) {
				   case 0: //direction down
		        	   if (tile0.ymin() == 0){ //the tile is on the bottom border and any flip is impossible
		        		   return;
		        	   }
		        	 Tile  tile1 = new Tile(tile0.xmin(), tile0.ymin() - 1, tile0.xmax(), tile0.ymin());
		        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top tile from the stack, 
		        		                             //and continue to the next tile in the stack.
		        		   tiling.flip(tile0, tile1, false);
		        		   tile0 = tower.pop();
		        		   dir0 = directions.pop();
		        		   continue;
		        	   }  else {
		        		   tile1 = tiling.findTile(tile0.xmin() + .5, tile0.ymin() - .5);
		        		   //StdOut.println("Type of the next tile is " + tile1.type());
		        		   switch (tile1.type()) {
		        		   case 0: //the next tile is vertical
		        			   tower.push(tile1);
		        			   directions.push(3);
		        			   continue;
		        		   case 1: //the next tile is square  
		        			   if (tile1.xmin() < tile0.xmin()) {
		        				   tower.push(tile1);
		        				   directions.push(0);
		        			   } else {
		        				   tower.push(tile1);
		        				   directions.push(3); //go right
		        				  // StdOut.println("Going right!");
		        			   }
		        			   continue;
		        		   case 2: //the tile is horizontal
		        			   tower.push(tile1);
		        			   directions.push(0); //go down
		        			   continue;
		        		   }   
		        	   }
		        	   break;
				   case 1: //direction is to the left, no flip possible
					   return;
				   case 2: //direction up 
		        	   if (tile0.ymax() == tiling.N){ //the tile is on the top border and any flip is impossible
		        		   return;
		        	   }
		        	   tile1 = new Tile(tile0.xmin(), tile0.ymax(), tile0.xmax(), tile0.ymax() + 1);
		        	   //StdOut.println(tile1);
		        	   if (tiling.contains(tile1)) { //we can perform the flip, remove the top tile from the stack, 
		        		                             //and continue to the next tile in the stack.
		        		   tiling.flip(tile0, tile1, false);
		        		   tile0 = tower.pop();
		        		   dir0 = directions.pop();
		        		   continue;
		        	   }  else {
		        		   tile1 = tiling.findTile(tile0.xmin() + .5, tile0.ymax() + .5);
		        		   //StdOut.println("Type of the next tile is " + tile1.type());
		        		   switch (tile1.type()) {
		        		   case 0: //the next tile is vertical
		        			   tower.push(tile1);
		        			   directions.push(3);
		        			   continue;
		        		   case 1: //the next tile is square  
		        			   if (tile1.xmin() < tile0.xmin()) {
		        				   tower.push(tile1);
		        				   directions.push(2);
		        			   } else {
		        				   tower.push(tile1);
		        				   directions.push(3); //go right
		        			   }
		        			   continue;
		        		   case 2: //the tile is horizontal
		        			   tower.push(tile1);
		        			   directions.push(2); //go up
		        			   continue;
		        		   }   
		        	   }
		        	   break;
				   case 3: //direction is to the right, no flip possible
					   return;
				   }
				   
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

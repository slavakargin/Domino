import java.util.*;
//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.RectHV;
//import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Tiling {
	private HashSet<Tile> tiling;
	//int NT; //number of tiles in the tiling;

	public Tiling(int N, String direction) {
		// N is the size of the square that we are going to tile
		// This constructor creates the tiling with either vertical or horizontal tiles.
		//NT = (int) Math.pow(2,2 * N - 2);
		Tile rect;
		tiling = new HashSet<Tile>();
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N; j += 4){
				if (direction.equalsIgnoreCase("vertical")) {
	        	    rect = new Tile(i,j,i+1,j+4);
				} else {
				    rect = new Tile(j,i,j + 4,i+1);
				}
	        	tiling.add(rect);
			}
		}	
	} //end of constructor.
	/*
	public void draw() { //displays the tiling in the StdDraw window.
		for (Tile tile : tiling) {
			tile.drawFilled();
		}
	}*/
	
	public void draw(Draw dr) { //displays the tiling in a specified window
		for (Tile tile : tiling) {
			tile.drawFilled(dr);
		}
	}
	
	public void flip(Tile tile1, Tile tile2, Boolean type) { //takes two rectangles (which are supposed to be 
		                                           // flippable), remove them from the tiling 
		                                          // and substitute instead two new, flipped, rectangles.
		                                          //If the rectangles are not in the tiling then terminates
		                                          // with an error message.
		                                          // Variable "type" determines whether the flip is vertical or horizontal.
		                                          // If type is 'true' then flip is horizontal (i.e., instead of two vertical tiles
		                                         //we get two horizontal tiles
		Tile tile3, tile4;
		//check if rectangles are in the tiling
		if (!tiling.contains(tile1)||!tiling.contains(tile2)) {
			StdOut.println("Error when trying to flip: "
					+ "the supplied tiles are not in the tiling");	
			return;
		}
		tiling.remove(tile1);
		tiling.remove(tile2);
		if (type) {
			 tile3 = new Tile(Math.min(tile1.xmin(), tile2.xmin()),tile1.ymin(),
					Math.max(tile1.xmax(), tile2.xmax()),(tile1.ymax()+tile1.ymin())/2);
			 tile4 = new Tile(Math.min(tile1.xmin(), tile2.xmin()), (tile1.ymax()+tile1.ymin())/2,
					Math.max(tile1.xmax(), tile2.xmax()),tile1.ymax());
		} else {
			  tile3 = new Tile(tile1.xmin(),Math.min(tile1.ymin(), tile2.ymin()),
					(tile1.xmax()+tile1.xmin())/2, Math.max(tile1.ymax(), tile2.ymax()));
			  tile4 = new Tile((tile1.xmax()+tile1.xmin())/2, Math.min(tile1.ymin(), tile2.ymin()),
					tile1.xmax(),Math.max(tile1.ymax(), tile2.ymax()));
		}
		// check the size of the new tiles
		if (tile3.height() * tile3.width() != 4 || tile3.height() * tile3.width() != 4) {
			StdOut.println("Flip is incorrect.");
			StdOut.println("Tile1 is " + tile1);
			StdOut.println("Tile2 is " + tile2);
			StdOut.println("Tile3 is " + tile3);
			StdOut.println("Tile4 is " + tile4);
			StdOut.println("Type is " + type);
		}
		
		tiling.add(tile3);
		tiling.add(tile4);
		return;
	}

	public Tile findTile(double x, double y) { //finds a tile that contains point (x,y)
		for (int i = 0; i < 4; i++ ) { //checking horizontal tiles
			Tile tile = new Tile((int) x - i, (int) y, (int) x + 4 - i, (int) y + 1);
			if (tiling.contains(tile)) {
				return tile;
			}
		}
		for (int i = 0; i < 4; i++ ) { //checking vertical tiles
			Tile tile = new Tile((int) x, (int) y - i, (int) x + 1, (int) y + 4 - i);
			if (tiling.contains(tile)) {
				return tile;
			}
		}
		for (int i = 0; i < 2; i++ ) { //checking square tiles
			for (int j = 0; j < 2; j++) {
			  Tile tile = new Tile((int) x - i, (int) y - j, (int) x + 2 - i, (int) y + 2 - j);
			  if (tiling.contains(tile)) {
				  return tile;
			  }
			}
		}
		StdOut.println("have not found tile covering the point.");
		for (Tile t : tiling) {
			StdOut.println(t);
		}
		return null;
	}
	
	/**
	 * looks for a flip around point (x,y) in the direction determined by choice,
	 * and if finds a valid flip in this direction then perform the flip by invoking
	 * function flip(tile1, tile2, type)
	 * 
	 * Variable "type" determines whether the flip is vertical or horizontal.
	 * If type is 'true' then flip is horizontal (i.e., instead of two vertical tiles
	 * we get two horizontal tiles
	 * 
	 * @param x :the x-coordinate of the point around which we are looking for flip 
	 * @param y :the y-coordinate of the point
	 * @param choice :the direction in which we are looking for a flip: top, or bottom, or left
	 * or right (so it should be an integer from 0 to 3)
	 */
	

public void tryFlip(double x, double y, int choice) {                                           
    	Tile tile2;
    	Tile tile = findTile(x, y);
    	if (tile.type() == 2) { // a horizontal tile
          switch (choice) {
          case 0: tile2 = new Tile(tile.xmin(), tile.ymin() - 1, tile.xmax(), tile.ymin()); //bottom
    	          if (tiling.contains(tile2)) {
			          flip(tile, tile2, false); 
		          }
    	          break;
          case 1: //left - do nothing
        	      break;
          case 2: tile2 = new Tile(tile.xmin(), tile.ymax(), tile.xmax(), tile.ymax() + 1); //top 	
    	          if (tiling.contains(tile2)) {
			          flip(tile, tile2, false);  		  
	              }
          case 3: //right - do nothing
        	      break;
          }
    	}
        if (tile.type() == 0) { //a vertical tile
        	switch (choice) {
        	case 0: //bottom - do nothing
        		break;
        	case 1: //left
        		  tile2 = new Tile(tile.xmin() - 1, tile.ymin(), tile.xmin(), tile.ymax());
          		  if (tiling.contains(tile2)) {
          			  flip(tile, tile2, true); 
          		  }
        	case 2: //top - do nothing 
        		break;
        	case 3: // right
        		 tile2 = new Tile(tile.xmin() + 1, tile.ymin(), tile.xmax() + 1, tile.ymax());
             	 if (tiling.contains(tile2)) {
             			  flip(tile, tile2, true);  		  
         		  }
        	}
        }
        if (tile.type() == 1)  { // a square tile
    		switch (choice) {
    		case 0 :   tile2 = new Tile(tile.xmin(), tile.ymin() - 2, tile.xmax(), tile.ymin()); //bottom
  		               if (tiling.contains(tile2)) {
			           flip(tile, tile2, false);
  		               }
			           break;
		    case 1 :   tile2 = new Tile(tile.xmin() - 2, tile.ymin(), tile.xmin(), tile.ymax()); //left
   		               if (tiling.contains(tile2)) {
  			           flip(tile, tile2, true);
   		               }
   		               break;
		    case 2 :    tile2 = new Tile(tile.xmin(), tile.ymax(), tile.xmax(), tile.ymax() + 2); //top
                        if (tiling.contains(tile2)) {
	                     flip(tile, tile2, false);
                         }
                       break;    
		    case 3 :      tile2 = new Tile(tile.xmax(), tile.ymin(), tile.xmax() + 2, tile.ymax()); //right
                          if (tiling.contains(tile2)) {
                          flip(tile, tile2, true);
                          }
                       break;   
  		     }
    	  }
      }


@Override
public boolean equals(Object o) {
    // self check
    if (this == o)
        return true;
    // null check
    if (o == null)
        return false;
    // type check and cast
    if (getClass() != o.getClass())
        return false;
    Tiling other = (Tiling) o;
    // Content comparison
    	if (!other.tiling.containsAll(tiling)) {
    		return false;
    	}
    	if (!tiling.containsAll(other.tiling)) {
    		return false;
    	}
    return true;	

}
}

import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.Point2D;



public class DominoVisualizer {
	
	public static void visualizeFlip(double x, double y, int dir, Tiling tiling, Draw draw, boolean NoDrawing) {
		Tile tile = tiling.findTile(x, y);
		if (!NoDrawing){
		  tile.drawSpecial(draw);
		  draw.show(40);
		}
		//tiling.tryFlip(x, y, choice);	
		flipTower tower = new flipTower(tiling, tile, dir);
		tower.flip();
		if (!NoDrawing){
		  tiling.draw(draw);
		  draw.show(40);
		}		
		//return tiling;
	}
	
	public static void main(String[] args) {
		double x;
		double y;		int count = 0;
		int dir;
		int N = 20; //size of the square to tile
			
		//Setting up first tiling
		Draw draw1 = new Draw("Tiling 1");
		draw1.setXscale(-0.5, N + 0.5);
		draw1.setYscale(-0.5, N + 0.5);
		draw1.clear(Draw.LIGHT_GRAY);
		draw1.setPenRadius(0.005);
		
		Draw draw2 = new Draw("Tiling 2");
		draw2.setLocationOnScreen(512, 1);
		draw2.setXscale(-0.5, N + 0.5);
		draw2.setYscale(-0.5, N + 0.5);
		draw2.clear(Draw.LIGHT_GRAY);
		draw2.setPenRadius(0.005);
		
		Tiling tiling = new Tiling(N,"horizontal");
		/*
		 String line = "Y";
		
		while (count < 1000) {
		//if (line.equals("Y")) {	
		tiling.draw(draw1);	
		draw1.show(40);	
		
		
		x = StdRandom.uniform(0.,(double) N);
		y = StdRandom.uniform(0.,(double) N);
		Tile tile = tiling.findTile(x, y);
		tile.drawSpecial(draw1);
		draw1.show(40);	
		
		dir = StdRandom.uniform(4);
		switch (tile.type()) {
		case 0: //tile is vertical
			dir = 2 * (dir % 2) + 1;
			break;
		case 2: //tile is horizontal
			dir = 2 * (dir % 2);
			break;
		}	
		//StdOut.println("Direction is " + dir);
		flipTower tower = new flipTower(tiling, tile, dir);
		tower.flip();		
		tiling.draw(draw2);
		//StdOut.println("Continue? (Input Y to continue.)");
		//line = StdIn.readString();
		//}
		count++;
		}
		*/
		//setting up second tiling
		Tiling tiling2 = new Tiling(N, "vertical");
		tiling2.draw(draw2);	
		draw2.show(40);
		
		
		while (true) {
		x = StdRandom.uniform(0.,(double) N);
		y = StdRandom.uniform(0.,(double) N);
		dir = StdRandom.uniform(4);
		visualizeFlip(x, y, dir, tiling, draw1, false);
		visualizeFlip(x, y, dir, tiling2, draw2, false);
		count++;
		if (tiling.equals(tiling2)) {
			StdOut.println("Coupling occurred after "+ count + " steps.");
			tiling.draw(draw1);
			tiling2.draw(draw2);
			draw1.show(40);
			draw2.show(40);
			return;
		}
		}
		
	}
}

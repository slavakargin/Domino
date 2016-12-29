
import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Point2D;



public class DominoVisualizer {
	
	public static void visualizeFlip(double x, double y, int choice, Tiling tiling, Draw draw, boolean NoDrawing) {
		Tile tile = tiling.findTile(x, y);
		if (!NoDrawing){
		  tile.drawSpecial(draw);
		  draw.show(40);
		}
		tiling.tryFlip(x, y, choice);	
		if (!NoDrawing){
		  tiling.draw(draw);
		  draw.show(40);
		}		
		//return tiling;
	}
	
	public static void main(String[] args) {
		double x;
		double y;
		int count = 0;
		int choice;
		int N = 20; //size of the square to tile
			
		//Setting up first tiling
		Draw draw1 = new Draw("Tiling 1");
		draw1.setXscale(-0.5, N + 0.5);
		draw1.setYscale(-0.5, N + 0.5);
		//StdDraw.show(0);
		draw1.clear(Draw.LIGHT_GRAY);
		draw1.setPenRadius(0.005);
		Tiling tiling = new Tiling(N,"vertical");
		tiling.draw(draw1);	
		draw1.show(40);	
		//setting up second tiling
		Draw draw2 = new Draw("Tiling 2");
		draw2.setLocationOnScreen(512, 1);
		draw2.setXscale(-0.5, N + 0.5);
		draw2.setYscale(-0.5, N + 0.5);
		draw2.clear(Draw.LIGHT_GRAY);
		draw2.setPenRadius(0.005);
		Tiling tiling2 = new Tiling(N, "horisontal");
		tiling2.draw(draw2);	
		draw2.show(40);
		
		
		while (true) {
		x = StdRandom.uniform(0.,(double) N);
		y = StdRandom.uniform(0.,(double) N);
		choice = StdRandom.uniform(4);
		visualizeFlip(x, y, choice, tiling, draw1, true);
		visualizeFlip(x, y, choice, tiling2, draw2, true);
		count++;
		if (tiling.equals(tiling2)) {
			StdOut.println("Coupling occured after "+ count + " steps.");
			tiling.draw(draw1);
			tiling2.draw(draw2);
			draw1.show(40);
			draw2.show(40);
			return;
		}
		}
	}
}

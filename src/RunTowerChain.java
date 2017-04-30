import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;
import org.apache.commons.math3.stat.descriptive.*;

/**
 * 
 */

/**
 * @author vladislavkargin
 *
 */
public class RunTowerChain {

	/**
	 * 
	 */
	public static void flip(double x, double y, int dir, Tiling tiling) {
		Tile tile = tiling.findTile(x, y);
		flipTower tower = new flipTower(tiling, tile, dir);
		tower.flip();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x;
		double y;
		int count;
		double[] counts;
		int dir;
		double[] Means;
		double[] STDs;
		int MaxSize = 33;
		Out outMeans = new Out("Means.txt");
		Out outSTDs = new Out("STDs.txt");
		for (int N = 8; N < MaxSize; N += 4) { //size of the square to tile
		int ITER = 100;
		counts = new double[ITER];
		for (int k = 0; k < ITER; k++) {
		   count = 0;
		   Tiling tiling = new Tiling(N,"horizontal");
		   Tiling tiling2 = new Tiling(N, "vertical");
		   while (true) {
		       x = StdRandom.uniform(0.,(double) N);
		       y = StdRandom.uniform(0.,(double) N);
		       dir = StdRandom.uniform(4);
		       flip(x, y, dir, tiling);
		       flip(x, y, dir, tiling2);
		       count++;
		       if (tiling.equals(tiling2)) {
			      counts[k] = count;
			      break;
		        }
	        }//end of while(true) loop
		}//end of for loop
		/*for (int k = 0; k < ITER; k ++) {
		     StdOut.print(counts[k] + ", ");
		}
		StdOut.println();*/
		DescriptiveStatistics ds = new DescriptiveStatistics(counts);
		double meanCount = ds.getMean();
		double stdCount = ds.getStandardDeviation();
		outMeans.println(meanCount);
		outSTDs.println(stdCount);
		
		StdOut.println("The mean number of flips for N = " + N + " is " + meanCount);
		StdOut.println("The STD for N = " + N + " is " + stdCount);		
     }//end of for on N (size of the square) 
		outMeans.close();
		outSTDs.close();
	}// end of main
}

import java.util.List;
import java.util.Random;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static  Grid genGrid(){
		
		int mnMax=15;
		Random random = new Random();
		int m = random.nextInt(mnMax) + 1;
		System.out.println("m: " + m);
		int n=  random.nextInt(mnMax) + 1;
		System.out.println("n: " +n);
		int rocksPressureMax= (m*n-2);//-2 for teleportal and blank for R2-D2
		System.out.println("rpM: " +rocksPressureMax);
		int rocksAndPressure= random.nextInt(rocksPressureMax/2) + 1;
		System.out.println("rp: " +rocksAndPressure);
		int obstaclesMax= rocksPressureMax-rocksAndPressure*2;
		System.out.println("oM: " +obstaclesMax);
		int obstacles= random.nextInt(obstaclesMax) + 1;
		System.out.println("o: " +obstacles);
		Grid intialGrid= new Grid(m, n, rocksAndPressure,rocksAndPressure, obstacles);
		System.out.println("b: "+ (obstaclesMax-obstacles));
		System.out.println("m: "+ m + " n: " + n + " rp: " + rocksAndPressure + " o: " + obstacles);
		return intialGrid;
	}
	public List search(Grid grid, String strategy, boolean visualize){
		return null;
	}
	public static void main (String[]args){
		
		System.out.println(genGrid().toString());
		
	}

}

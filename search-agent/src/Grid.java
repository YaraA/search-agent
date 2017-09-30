import java.util.ArrayList;
import java.util.Random;


public class Grid {
	Cell[][] grid;
	int m;
	int n;
	
	public Grid(int m, int n, int pressurePads, int rocks, int obstacles){
		grid= new Cell[m][n];
		this.m =m;
		this.n= n; 
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				grid[i][j]= new Blank(i,j);
			}
		}
		createCells(1,"T");
		createCells(pressurePads,"P");
		createCells(rocks,"R"); 
		createCells(obstacles,"O");
	}
	
	public void createCells(int noItems, String type){
		for(int k=0;k<noItems; k++){
			Random random = new Random();
			int i,j;
			do{
				i = random.nextInt(m);
				j = random.nextInt(n);
			}
			while(!(grid[i][j] instanceof Blank));
			switch(type){
				case "T": grid[i][j]= new Teleportal(i,j);break;
				case "R": grid[i][j]= new Rocks(i,j);break;
				case "P": grid[i][j]= new PressurePads(i,j);break;
				case "O": grid[i][j]= new Obstacles(i,j);break;
			}	
		}
	}
	
	public boolean canMove(Cell currCell, Operator o){ 
		//checks if cell lies on the edges of the grid
		return false;
		
		
	}
    public String toString(){
    	String result="";
    	String seperator= "";
    	for(int j=0; j<n*6+1;j++)
    		seperator+="-";
    	result+= "  " + seperator + "\n";
    	for(int i=0; i<m; i++){
    		for(int j=0; j<n; j++){
    			result+= "  |  " + grid[i][j].toString();
    			
    		}
    		result+= "  |  " + "\n";
    		result+= "  " +seperator + "\n";
    		
    	}
      
           
    	return result;
    }

}

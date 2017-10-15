
public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void set(Position p){
		this.x = p.x;
		this.y = p.y;
	}
	public boolean equals(int x, int y){
		return (this.x==x) && (this.y==y);
	}
	public boolean equals(Position p){
		return (this.x==p.x) && (this.y==p.y);
	}
	public int cityBlockDistanceTo(Position p){
		/*
		 * Returns the difference in x + difference in y.
		 */
		return Math.abs(p.x - this.x) + Math.abs(p.y - this.y);
	}
}

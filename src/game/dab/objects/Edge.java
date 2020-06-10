package game.dab.objects;
public class Edge {

	private int x, y;
	private boolean horizontal;
	private int colorOfEdge;

	public Edge() {
		x = y = -1;
		horizontal = false;
		colorOfEdge = ColorTeam.BLANK;
	}

	public Edge(int x, int y, boolean horizontal) {
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.colorOfEdge = ColorTeam.BLANK;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return ((horizontal ? "H " : "V ") + x + " " + y);
	}

	public int getColorOfEdge() {
		return colorOfEdge;
	}

	public void setColorOfEdge(int colorOfEdge) {
		this.colorOfEdge = colorOfEdge;
	}

}

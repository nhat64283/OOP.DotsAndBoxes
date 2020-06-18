package game.dab.objects;

import java.awt.Point;
import java.util.ArrayList;

public class Board implements Cloneable {

	private ArrayList<Edge> hEdges;
	private ArrayList<Edge> vEdges;
	private ArrayList<Box> box;
	private int n, redScore, blueScore;

	public Board(int n) {
		int count = 0;
		hEdges = new ArrayList<Edge>();
		vEdges = new ArrayList<Edge>();
		box = new ArrayList<Box>();

		for (int i = 0; i < (n - 1) * (n - 1); i++) {
			box.add(new Box());
		}

		for (int i = 0; i < (n - 1); i++) {
			for (int j = 0; j < n; j++) {
				hEdges.add(new Edge(i, j, true));
				count++;
				if (j == 0) {
					box.get(i).sethTEdge(hEdges.get(count - 1));
				} else if (j < n - 1) {
					box.get(i + (j - 1) * (n - 1)).sethDEdge(hEdges.get(count - 1));
					box.get(i + j * (n - 1)).sethTEdge(hEdges.get(count - 1));
				} else {
					box.get(i + (j - 1) * (n - 1)).sethDEdge(hEdges.get(count - 1));
				}

			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (n - 1); j++) {
				Edge v = new Edge(i, j, false);
				vEdges.add(v);
				if (i == 0)
					box.get(j * (n - 1)).setvLEdge(v);
				else if (i < (n - 1)) {
					box.get(i - 1 + j * (n - 1)).setvREdge(v);
					box.get(i + j * (n - 1)).setvLEdge(v);
				} else {
					box.get(i - 1 + j * (n - 1)).setvREdge(v);
				}
			}
		}
		this.n = n;
		redScore = blueScore = 0;
	}

	public Board clone() {
		Board cloned = new Board(n);

		for (int i = 0; i < (n - 1); i++) {
			Edge h = new Edge();
			h = hEdges.get(i);
			cloned.hEdges.add(h);
		}

		for (int i = 0; i < n; i++) {
			Edge v = new Edge();
			v = vEdges.get(i);
			cloned.vEdges.add(v);
		}
		for (Box box3 : box) {
			Box b = new Box();
			b = box3;
			cloned.box.add(b);
		}

		cloned.redScore = redScore;
		cloned.blueScore = blueScore;

		return cloned;
	}
	
	public int getSize() {
		return n;
	}

	public int getRedScore() {
		return redScore;
	}

	public int getBlueScore() {
		return blueScore;
	}

	public int getScore(int color) {
		if (color == ColorTeam.RED)
			return redScore;
		else
			return blueScore;
	}

	public static int toggleColor(int color) {
		if (color == ColorTeam.RED)
			return ColorTeam.BLUE;
		else
			return ColorTeam.RED;
	}

	public ArrayList<Edge> getAvailableMoves() {
		ArrayList<Edge> ret = new ArrayList<Edge>();
		for (int i = 0; i < (n - 1) * n; i++)
			if (hEdges.get(i).getColorOfEdge() == ColorTeam.BLANK)
				ret.add(new Edge(hEdges.get(i).getX(), hEdges.get(i).getY(), true));
		for (int i = 0; i < n * (n - 1); i++)
			if (vEdges.get(i).getColorOfEdge() == ColorTeam.BLANK)
				ret.add(new Edge(vEdges.get(i).getX(), vEdges.get(i).getY(), false));
		return ret;
	}


	public ArrayList<Point> setHEdge(int x, int y, int color) {
		int numb = x + y * (n - 1);
		hEdges.get(x * n + y).setColorOfEdge(ColorTeam.BLACK);
		ArrayList<Point> ret = new ArrayList<Point>();
		if (y < (n - 1) && box.get(numb).checkBox() == true) {
			box.get(numb).setTrangthai(color);
			ret.add(new Point(x, y));
			if (color == ColorTeam.RED)
				redScore++;
			else
				blueScore++;
		}
		if (y > 0 && box.get(numb - n + 1).checkBox() == true) {
			box.get(numb - n + 1).setTrangthai(color);
			ret.add(new Point(x, y - 1));
			if (color == ColorTeam.RED)
				redScore++;
			else
				blueScore++;
		}
		return ret;
	}

	public ArrayList<Point> setVEdge(int x, int y, int color) {
		int numb = x + y * (n - 1);
		vEdges.get(x * (n-1) + y).setColorOfEdge(ColorTeam.BLACK);
		ArrayList<Point> ret = new ArrayList<Point>();
		if (x < (n - 1) &&  box.get(numb).checkBox() == true) {
			box.get(numb).setTrangthai(color);
			ret.add(new Point(x, y));
			if (color == ColorTeam.RED)
				redScore++;
			else
				blueScore++;
		}
		if (x > 0 &&  box.get(numb -1 ).checkBox() == true) {
			box.get(numb- 1).setTrangthai(color);
			ret.add(new Point(x - 1, y));
			if (color == ColorTeam.RED)
				redScore++;
			else
				blueScore++;
		}
		return ret;
	}

	public boolean isComplete() {
		return (redScore + blueScore) == (n - 1) * (n - 1);
	}

	public int getWinner() {
		if (redScore > blueScore)
			return ColorTeam.RED;
		else if (redScore < blueScore)
			return ColorTeam.BLUE;
		else
			return ColorTeam.BLANK;
	}

	public Board getNewBoard(Edge edge, int color) {
		Board ret = clone();
		if (edge.isHorizontal())
			ret.setHEdge(edge.getX(), edge.getY(), color);
		else
			ret.setVEdge(edge.getX(), edge.getY(), color);
		return ret;
	}

	public int getBoxCount(int nSides) {
		int count = 0;
		for (Box box: box) {
			if (box.countEdgeBlack() == nSides)
				count++;
		}
		return count;
	}
	
	public void clear() {
		box.clear();
		hEdges.clear();
		vEdges.clear();
		redScore = 0;
		blueScore = 0;
	}

}

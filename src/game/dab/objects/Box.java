package game.dab.objects;

public class Box {
	private Edge hTEdge;
	private Edge hDEdge;
	private Edge vREdge;
	private Edge vLEdge;
	private int trangthai;

	public Edge gethTEdge() {
		return hTEdge;
	}

	public void sethTEdge(Edge hTEdge) {
		this.hTEdge = hTEdge;
	}

	public Edge gethDEdge() {
		return hDEdge;
	}

	public void sethDEdge(Edge hDEdge) {
		this.hDEdge = hDEdge;
	}

	public Edge getvREdge() {
		return vREdge;
	}

	public void setvREdge(Edge vREdge) {
		this.vREdge = vREdge;
	}

	public Edge getvLEdge() {
		return vLEdge;
	}

	public void setvLEdge(Edge vLEdge) {
		this.vLEdge = vLEdge;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public Box() {
		this.trangthai = ColorTeam.BLANK;
	}
	
	public boolean checkBox() {
		if( hTEdge.getColorOfEdge() == ColorTeam.BLACK && hDEdge.getColorOfEdge() == ColorTeam.BLACK 
				&& vREdge.getColorOfEdge() == ColorTeam.BLACK && vLEdge.getColorOfEdge() == ColorTeam.BLACK) 
			return true;
		return false;
	}
	
	public int countEdgeBlack() {
		int count = 0;
		if(hTEdge.getColorOfEdge() == ColorTeam.BLACK) count++;
		if(hDEdge.getColorOfEdge() == ColorTeam.BLACK) count++;
		if(vREdge.getColorOfEdge() == ColorTeam.BLACK) count++;
		if(vLEdge.getColorOfEdge() == ColorTeam.BLACK) count++;
		return count;
	}

}

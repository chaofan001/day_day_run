package game;

public class Rect {
	private int left, top, bottom, right;
	
	public Rect(){
		
	}
	public Rect(int left, int top, int bottom, int right) {
		super();
		this.left = left;
		this.top = top;
		this.bottom = bottom;
		this.right = right;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	
	
	
}

package Game;


import javax.swing.JPanel;

public class Horse extends JPanel {
	String imageName;
	String flag;
	int x, y;

	public Horse(String imageName, String flag) {
		this.imageName = imageName;
		this.flag = flag;
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
	
}

package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Red implements ImageTile{
	Point2D position;
	public Red (Point2D position) {
		this.position = position;
	}
	

	public String getName() {
		// TODO Auto-generated method stub
		return "Red";
	}

public Point2D getPosition() {
	return position;
	}

public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}
}

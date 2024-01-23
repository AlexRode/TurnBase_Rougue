package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Green implements ImageTile{
Point2D position;
	
	public Green (Point2D position) {
	this.position= position;	;
	}
	

	public String getName() {
		// TODO Auto-generated method stub
		return "Green";
	}

public Point2D getPosition() {
	return position;
	}

@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

}

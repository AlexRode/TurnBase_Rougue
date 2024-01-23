package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class DoorOpen extends GameElement{

	private String destroom;
	Point2D destposition;

	public DoorOpen(Point2D position, String destroom,Point2D destposition) {
		super(position);
		this.destroom=destroom;
		this.destposition=destposition;
	}

	@Override
	public String getName() {
		return "DoorOpen";
	}

	@Override
	public Point2D getPosition() {
		return super.getPosition();
	}
	public String getdestroom() {
		return destroom;
	}
	public Point2D getdestposition() {
		return destposition;
	}

	@Override
	public int getLayer() {
		return 0;
	}
	public boolean isPassable(){
		return true;
	}

}

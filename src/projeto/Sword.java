package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;


public class Sword extends Item{	


	public Sword(Point2D position) {
		super (position);
	}

	@Override
	public String getName() {
		return "Sword";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}
	 public boolean isDrinkable() {
		 return false;
	 }
	

}

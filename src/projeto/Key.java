package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Key extends Item {


	private String keycode;
	
	public Key(Point2D position, String keycode) {
		super(position);
		this.keycode=keycode;
		
	}

	@Override
	public String getName() {
		return "Key";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	public String getKeycode() {
		return keycode;
	}
	
	 public boolean isDrinkable() {
		 return false;
	 }
	 

}

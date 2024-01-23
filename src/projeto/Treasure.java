package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement{


	public Treasure(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Treasure";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	public boolean isPassable() {
		return true;
	}
}

package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends Item{
	

	public HealingPotion(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "HealingPotion";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	} public boolean isDrinkable() {
		 return true;
	 }
	

}

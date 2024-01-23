package projeto;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Door extends GameElement {

	private String destroom;
	private Point2D  destposition;
	private String keycode;
	private boolean passable;

	public Door(Point2D position, String destroom, Point2D destposition,String keycode){//,boolean passable) {
		super(position);
		this.destroom=destroom;
		this.destposition = destposition;
		this.keycode = keycode;
	//	this.passable=passable;
		
	}

	@Override
	public String getName() {
		return "DoorClosed";
	}
	public void setName(String name){
		super.setName(name);
	}

	@Override
	public Point2D getPosition() {
		return super.getPosition();
	}

	@Override
	public int getLayer() {
		return 0;
	}
	public boolean isPassable() {
	List<GameElement> lge=GameEngine.getInstance().getRoom().getRoomelements();
		for(GameElement ge:lge) {
		if(ge instanceof Door) {
			if(ge.getName().equals("DoorOpen")) {
				return true;
			}
		}
	}
		
		return super.isPassable();
		
	}
	
	public String getdestroom() {
		return destroom;
	}
	public Point2D getdestposition() {
		return destposition;
	}
	public String getKeycode() {
		return keycode;
	}
	public void open() {
	GameEngine.getInstance().removeImageFromGui(this);
    passable = true;
	setName("DoorOpen");
    GameEngine.getInstance().addGrenToGui(this);
}


	
}

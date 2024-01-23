package projeto;

import java.util.ArrayList;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile{

	Point2D position;
	private String name;
	
	public GameElement(Point2D position) {
		this.position = position;
	}

public boolean isPassable(){
	return false;
}
public boolean isDrinkable(){
	return false;
}

@Override
public String getName() {
	
	return null;
}
@Override
public Point2D getPosition() {
//this.position  = position;
	return position;
}
@Override
public int getLayer() {
	
	return 0;
}

public void setName(String n){
	name=n;
}
public String getKeycode() {
	return null;
	}
public String getdestroom() {
	return null;
}
public Point2D getdestposition() {
	return null;
}

public void move() {
	
}

public static GameElement create(String objt) {
	String[] objt_v = objt.split(",");
	//String numberOfWords = objt.split(",");
	if(objt_v.length > 1) {
	String objtName = objt_v[0];
	int x = Integer.parseInt(objt_v[1]);
	int y = Integer.parseInt(objt_v[2]);
	
	
	switch(objtName) {
	case "Skeleton":
		return new Skeleton(new Point2D(x,y));
		
	case "Bat":
		return new Bat(new Point2D(x,y));
	
	case "Thug":
		return new Thug(new Point2D(x,y));
	
	case "Scorpio":
		return new Scorpio(new Point2D(x,y));
	
	case"Thief":
		return new Thief(new Point2D(x,y));
	
	case"Key":
		return new Key(new Point2D(x,y),objt_v[3]);
	
	case"Armor":
	return new Armor(new Point2D(x,y));
	
	case"HealingPotion":
		return new HealingPotion(new Point2D(x,y));
		
	case "Sword":
		return new Sword(new Point2D(x,y));
	
	case"Treasure":
		return new Treasure(new Point2D(x,y));
	
	case"Door":	
		
		if(objt_v.length > 6 ) {
		
		return new Door(new Point2D(x,y),objt_v[3],new Point2D(Integer.parseInt(objt_v[4]),Integer.parseInt(objt_v[5])),objt_v[6]);
		}else{
			return new DoorOpen(new Point2D(x,y),objt_v[3],new Point2D(Integer.parseInt(objt_v[4]),Integer.parseInt(objt_v[5])));
		//return new Door(new Point2D(x,y),objt_v[3],new Point2D(Integer.parseInt(objt_v[4]),Integer.parseInt(objt_v[5])),"Key-1",true);
		
		}
	}
	}
return null;
	
}

public int getHp() {
	return 0;
}

public void setHp(int value) {
}


public static void opendoor() {
	Hero h =GameEngine.getInstance().getRoom().getHero();
 
	ArrayList<GameElement> aux= new ArrayList<>();
	if(h.haskey()) {
	for(GameElement d : GameEngine.getInstance().getRoom().getRoomelements()) {
		if(d instanceof Door) {
		
			if(h.getkeycode().equals(d.getKeycode()) ) {
		
			
			DoorOpen opendoor = new DoorOpen(new Point2D(d.getPosition().getX(),d.getPosition().getY()),d.getdestroom(),d.getdestposition());
			
			aux.add(d);
			GameEngine.getInstance().removeImageFromGui(d);
				
			GameEngine.getInstance().addImageToGui(opendoor);			
			GameEngine.getInstance().getRoom().getRoomelements().add(opendoor);
			
			}else {
				
			}
			
		}
		GameEngine.getInstance().getRoom().getRoomelements().removeAll(aux);
	}
	}
		
		
	

	
	}
public void setDoorOpen(Point2D p) {
	if(GameEngine.getInstance().getHero().getPosition() == p) {
		
	}
}


public void getItemsRobed() {
	// TODO Auto-generated method stub
	
}


}
package projeto;

import pt.iscte.poo.utils.Point2D;

public class Scorpio extends Enemy implements Movable{

	private int hp = 2;
	
	public Scorpio(Point2D position) {
		super(position);
		
	}
	public String getName() {
		return "Scorpio";
	}
	
	public Point2D getPosition() {
		return super.getPosition();
	}
	
	@Override
	public int getLayer() {
		return 0;
	}
	
	public void attack(Hero h) {
	
	  h.setpoisoned(true); 
	}

	
	public void move() {

		
		Point2D  point = getMovePoint();
		
		if( GameEngine.getInstance().canMoveTo(point)) { 
		
			super.move();
		}else{
	Hero h = GameEngine.getInstance().getRoom().getHero();
			if (h.getPosition().equals(point)) {
		
					attack(h);
			}			
		
		}
	}
	public void setHp(int value) {
		hp = value;
	}
	
	public int getHp() {
		return hp;
	}
}

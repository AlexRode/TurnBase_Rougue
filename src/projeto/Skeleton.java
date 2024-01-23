package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends Enemy {

	public int hp = 5;
	private final int damage = 1;
	boolean move= true;

	public Skeleton (Point2D position) {
		super(position);
	
		
	}
	
	public String getName() {
		return "Skeleton";
	}
	
	public Point2D getPosition() {
		return super.getPosition();
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	public void attack(Hero h) {
		h.setHp(h.getHp() - damage);
	}
	
	public void move() {	
		
		Point2D  point = getMovePoint();
		if(canMove()) 

		
		if( GameEngine.getInstance().canMoveTo(point)) { 
			
			super.move();
		}else {
		Hero h = GameEngine.getInstance().getHero();
		
		if (GameEngine.getInstance().getHeroPosition().equals(point)) {
		if(h.hasArmor() == true) {
			if(Math.random()>= 0.5) {
				
				attack(h);
			}
			
		}else {
		
			attack(h);
		}
			
		//System.out.println( h.getHp() );
		}
		
	}
	
		move = !move;
	
	}
	public boolean canMove() {		
			return !move;
					
	}
	
	public void setHp(int value) {
		hp = value;
	}
	
	public int getHp() {
		return hp;
	}
	
		
}

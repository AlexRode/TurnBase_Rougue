package projeto;

import pt.iscte.poo.gui.ImageTile;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thug extends Enemy implements Movable{
	
 private int hp = 10;
 private int damage = 3;

	public Thug (Point2D position) {
		super(position);
		
	}
	
	public String getName() {
		return "Thug";
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
		
		if( GameEngine.getInstance().canMoveTo(point)) { 
		
			super.move();
		}else{
			Hero h = GameEngine.getInstance().getHero();
			if (GameEngine.getInstance().getHeroPosition().equals(point)) {
				if(Math.random()>=0.7) {
					if(h.hasArmor() == true) {
						if(Math.random()>= 0.5) {
							attack(h);
						}
						
					}else {
						attack(h);
					}
					
				}
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

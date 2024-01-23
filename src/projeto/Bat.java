package projeto;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends Enemy implements Movable{

	private final int damage = 1;
	public int hp  = 3;
	
	public Bat(Point2D position) {	
		
		super(position);
	}
	
	public String getName() {
		return "Bat";
	}

	public Point2D getPosition() {
		return super.getPosition();
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	public void attack(Hero h) {
		h.setHp(h.getHp() - 1);
	}
	
	public void move() {

		 Point2D  point = getMovePoint();
		if(Math.random() >= 0.5) {
			if( GameEngine.getInstance().canMoveTo(point)) { 
				super.move();
			}
			 
		}else {
			Direction randDirection = Direction.random();
			Vector2D randVector = randDirection.asVector(); 
			Point2D pfinal2 = position.plus(randVector);

		
		
			if( GameEngine.getInstance().canMoveTo(pfinal2)) {
				position = pfinal2;
			}else {
				Hero h = GameEngine.getInstance().getHero();
				if (GameEngine.getInstance().getHeroPosition().equals(point)) {
				
					if(Math.random()>=0.5) {
						if(h.hasArmor() == true) {
							if(Math.random()>= 0.5) {
								attack(h);
								setHp(getHp() + 1);
							}
							
						}else {
							attack(h);
							setHp(getHp() + 1);
						}
					
						
			}
		}
	
				}
			}
		
	
	}
	public void attack() {
		if(Math.random() >= 0.5) {
			
		}
	}
	
	public void setHp(int value) {
		hp = value;
	
	}
	
	public int getHp() {
		return hp;
	}
	

}

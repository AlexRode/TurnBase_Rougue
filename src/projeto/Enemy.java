package projeto;

import java.util.ArrayList;
import java.util.List;
import pt.iscte.poo.gui.ImageTile;
import javax.swing.text.Position;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Enemy extends GameElement implements Movable{

	 private int hp;
	
	public Enemy (Point2D position) {
		super(position);
		
		
	}

	
	public Point2D getMovePoint() {

		
		Vector2D toHero = GameEngine.getInstance().vectorToHero(getPosition());
		Point2D point ;
		
		int xHero = toHero.getX();
		int yHero = toHero.getY();	
		
			if( Math.abs(xHero) > Math.abs(yHero)){
			if(xHero > 0) {
				point = position.plus(new Vector2D(1,0));
			
			}else {
				point = position.plus(new Vector2D(-1,0));
			}
		}else {
			if(yHero> 0) {
				point = position.plus(new Vector2D(0,1));
			}else {
				point = position.plus(new Vector2D(0,-1));
			}
		}
			return point;
	}
	
	
	public Point2D opositeMovePoint() {
		Point2D positionhero = GameEngine.getInstance().getHero().getPosition();
		
		Vector2D toHero = GameEngine.getInstance().vectorToHero(getPosition());
		
		Point2D point ;
		
		int xHero = toHero.getX();
		int yHero = toHero.getY();	
		
			if( Math.abs(xHero) > Math.abs(yHero)){
			if(xHero > 0) {
				point = position.plus(new Vector2D(-1,0));
			
			}else {
				point = position.plus(new Vector2D(1,0));
			}
		}else {
			if(yHero> 0) {
				point = position.plus(new Vector2D(0,-1));
			}else {
				point = position.plus(new Vector2D(0,1));
			}
		}
			return point;
	}
	
public void move() {
	position = getMovePoint();
	}

public void moveoposite() {
	position = opositeMovePoint();
}



	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
	}
	
	public void setHp(int value) {
		hp = value;
	}
	
	public int getHp() {
		return hp;
	}
	public void hpEnemytracker(GameElement e) {
		ArrayList<GameElement> aux = new ArrayList<>(); 
		
			if(e.getHp() == 0) {
				GameEngine.getInstance().removeImageFromGui(e);
				aux.add(e);
				
		}
		GameEngine.getInstance().getRoom().getRoomelements().removeAll(aux);
	}
	
	
}

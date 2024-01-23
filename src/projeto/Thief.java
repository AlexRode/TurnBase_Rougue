package projeto;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.utils.Point2D;

public class Thief extends Enemy implements Movable{

	private List<Item> itemsRobed = new ArrayList<>();
	int hp = 5;
	
	public Thief(Point2D position) {
		super(position);
		
	}
	public String getName() {
		return "Thief";
	}
	
	public Point2D getPosition() {
		return super.getPosition();
	}
	
	@Override
	public int getLayer() {
		return 0;
	}
	public void setHp(int value) {
		hp = value;
	
	}
	
	public int getHp() {
		return hp;
	}

	public boolean IsDead() {
		if(hp <=0) {
			return true ;
		}
		return false;
	}
	
	public int randItemposition() {
		return (int)(Math.random()*(9-7+1)+7);
	}
	
	
	public void dropItems() {
		ArrayList<GameElement> aux= new ArrayList<>(); 
		ArrayList<GameElement> aux2= new ArrayList<>();
		
		GameEngine g =GameEngine.getInstance();
		if(IsDead()) {
			if(itemsRobed.get(0).getName() == "Key") {
				String key = "Key"+","+position.getX()+","+position.getY()+","+itemsRobed.get(0).getKeycode();
				GameElement keyge =GameElement.create(key);
				g.removeImageFromGui(itemsRobed.get(0));
				g.addImageToGui(keyge);
				
			g.addImageToGui(keyge);
				g.getRoom().getRoomelements().add(keyge);
				
				aux.add(itemsRobed.get(0));
			aux2.add(GameEngine.getInstance().getRoom().getThief());
			}else{
				String a= itemsRobed.get(0).getName()+","+position.getX()+","+position.getY();
				
				GameElement item = GameElement.create(a);
				g.removeImageFromGui(itemsRobed.get(0));
				g.addImageToGui(item);
				aux.add(itemsRobed.get(0));
				g.getRoom().getRoomelements().add(item);
				aux2.add(GameEngine.getInstance().getRoom().getThief());

				
			}		
		}
		itemsRobed.removeAll(aux);
		g.getRoom().getRoomelements().removeAll(aux2);
		

}
	

	public void attack(Hero h){
		ArrayList<Item> aux=new ArrayList<>(); 
		Item i = h.randposition();
		if(i == null) {
		aux.add(i);
		}else {
			itemsRobed.add(i);
		 }
		itemsRobed.removeAll(aux);
		}
	
	public void move() {

		
		Point2D  point = getMovePoint();
		Point2D   opositpoint = opositeMovePoint();	
		if(itemsRobed.size() == 0) {
	
		if( GameEngine.getInstance().canMoveTo(point)) { 
		
			super.move();
		
			
		}else{
			Hero h = GameEngine.getInstance().getHero();
			if (GameEngine.getInstance().getHeroPosition().equals(point)) {
				
					attack(h);	
		
		}
	}
	
  }else{
	  if( GameEngine.getInstance().canMoveTo(opositpoint)) { 
		  System.out.println(itemsRobed.size());
			super.moveoposite();
		
			
		}
   }
		
		
		dropItems();
		
  }
	
}

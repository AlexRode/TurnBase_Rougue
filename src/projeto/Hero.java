package projeto;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;


public class Hero extends GameElement

{
	
	private List<Item> itemBar = new ArrayList<>();

	private boolean poisoned;
	private int hp = 10;
	private int damage = 1;

	public Hero(Point2D position) {
		super(position);

	
	}
	public boolean isPassable() {
		return false;
	}

	@Override
	public String getName() {
		return "Hero";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 3;
	}

	//public List<Item> getItemsHero() {
	//	return items;
	//}

	public void setHp(int value) {
		hp = value;
	}

	public int getHp() {
		return hp;
	}

	public boolean healthtracker() {
		Hero h = GameEngine.getInstance().getHero();
		if (h.getHp() <= 0) {
			return true;
		}
		return false;
	}

	// public void poisoned2() {
	// poisoned = true;
	// }
	public void setpoisoned(boolean b) {
		poisoned = b;
	}

	public List<Item> itemBar() {
		return itemBar;
	}

	public boolean hasSword() {
		for (GameElement i : itemBar) {
			if (i.getName() == "Sword") {
				return true;
			}

		}
		return false;
	}

	public boolean hasArmor() {
		for (Item i : itemBar) {
			if (i.getName() == "Armor") {
				setDamage(2);
				return true;
			}

		}
		return false;
	}

	public void setDamage(int value) {
		damage = value;
	}

	public int Damage() {
		return damage;
	}

	public void attack(GameElement e) {
		e.setHp(e.getHp() - Damage());
	}

	public void attackEnemy(GameElement e) {

		String EnemyName = e.getName();

		switch (EnemyName) {

		case "Skeleton":

			attack(e);

			break;

		case "Bat":

			attack(e);

			break;

		case "Thug":

			attack(e);

			break;

		case "Scorpio":

			attack(e);

			break;

		case "Thief":

			attack(e);

			break;
		}

	}
	public Point2D getEnemyPositiontoattcak(Point2D p) {

		for (GameElement en: GameEngine.getInstance().getRoom().getRoomelements()) {
			if(en instanceof Enemy) {
			if (p.equals(en.getPosition()) ) { 
				attack(en);
			}
		}
		}
	return p;	
	}
	public Point2D setDoorPosition(Point2D p) {

		for (GameElement d: GameEngine.getInstance().getRoom().getRoomelements()) {
			if(d instanceof Door) {
			if (p.equals(d.getPosition()) ||GameEngine.getInstance().getRoom().getHero().getPosition().equals(p) ) { 
		
				GameElement.opendoor();
				
			}
		}
		}
	return p;	
	}
	
	

	public void move(int KeyCode) {

		Direction direction = Direction.directionFor(KeyCode);

		Vector2D vector = direction.asVector();

		Point2D pfinal = position.plus(vector);

		if (hasSword()) {
			setDamage(2);
		}

		if (GameEngine.getInstance().canMoveTo(pfinal)) {
			position = pfinal;

		} else {

			getEnemyPositiontoattcak(pfinal);
			setDoorPosition(pfinal);

		}
		if (poisoned) {
			GameEngine.getInstance().getHero().setHp(getHp() - 1);
		}

	}

	public boolean VeifyItemPosition(Point2D a) {
		for(GameElement i : GameEngine.getInstance().getRoom().getRoomelements()) {
		if(i instanceof Item) {
			if (i.getPosition().equals(a)) {
				return true;
			}
		 }
		}
		return false;
	}
	
	public boolean barposition(int n) {
		for (Item i : itemBar) {
			if (i.getPosition().getX() == n) {
				return false;
			}
		}
		return true;
	}


	public Point2D positionfree() {
		Point2D p = null;
		if (VerifytBarisFree()) {
			if (barposition(7)) {
				
				p = new Point2D(7, 11);
			} else {
				if (barposition(8)) {
					
					p = new Point2D(8, 11);
				} else {
					if (barposition(9)) {
						
						p = new Point2D(9, 11);
					}
				}
			}
		} else {
		}
		return p;

	}

	public void addItemtoArrayAux(Point2D a) {

		ArrayList<GameElement> aux = new ArrayList<>();

		for(GameElement i : GameEngine.getInstance().getRoom().getRoomelements()) {
			if(i instanceof Item) {
			
					if (VerifytBarisFree()) {

				if (i.getPosition().equals(a)) {

					aux.add(i);

					String objtname = i.getName();
					int point = positionfree().getX();
				
					if(objtname =="Key") {
						GameEngine.getInstance().removeImageFromGui(i);
						String key = objtname +","+ point+","+ 11+","+i.getKeycode();
						GameElement keyge = GameElement.create(key);
						itemBar.add((Item)keyge);
						if(keyge != null) {
						GameEngine.getInstance().addImageToGui(keyge);
					}
						
					}else {
				
						GameEngine.getInstance().removeImageFromGui(i);
					String item = objtname +","+ point+","+ 11;
					GameElement ge = GameElement.create(item);
					itemBar.add((Item)ge);
					if(ge != null) {
					GameEngine.getInstance().addImageToGui(ge);

					}

				}

			}

		}
		}
		}
		

		GameEngine.getInstance().getRoom().getRoomelements().removeAll(aux);

	}

	public boolean VerifytBarisFree() {
		if (itemBar.size() > 2) {
			//System.out.println(itemBar.size());
			return false;

		}
		return true;
	}


	public List<Item> getItemBar() {
		return itemBar;
	}


	public void drinkOruse(Item i, int x, int y) {
		
		Hero h = GameEngine.getInstance().getHero();
	
			if (i.isDrinkable()) {
				if (i.getName() == "HealingPotion") {
					GameEngine.getInstance().removeImageFromGui(i);
					if (h.getHp() <= 5) {
						h.setHp(h.getHp() + 5);

					} else {
						h.setHp(10);
					}
					if(poisoned) {
						GameEngine.getInstance().getRoom().getHero().setpoisoned(false);
					}
				}
			

		}else {
			remove(i, x, y);
		}
	}


	public void remove(Item i, int x, int y) {

		GameEngine.getInstance().removeImageFromGui(i);

		if (i.getName() == "Key") {
			Item key = new Key(new Point2D(x, y), i.getKeycode());
			GameEngine.getInstance().addImageToGui(key);
			GameEngine.getInstance().getRoom().getRoomelements().add(key);
		} else {
      String item =i.getName()+","+x+","+y;
			GameElement ge = GameElement.create(item);
			GameEngine.getInstance().addImageToGui(ge);
			GameEngine.getInstance().getRoom().getRoomelements().add(ge);
		}

	}

	public void removeOrdrink(int key) {
		ArrayList<Item> aux2 = new ArrayList<>();
		int x = GameEngine.getInstance().getHero().getPosition().getX();
		int y = GameEngine.getInstance().getHero().getPosition().getY();

		switch (key) {

		case KeyEvent.VK_1:
			for (Item i : itemBar) {
				Point2D point = i.getPosition();

				Point2D point_1 = new Point2D(7, 11);

				if (point.equals(point_1)) {

					aux2.add(i);

						drinkOruse(i, x, y);
					
						
					

				}
			}

			itemBar.removeAll(aux2);

			break;

		case KeyEvent.VK_2:
			for (Item i : itemBar) {
				Point2D point = i.getPosition();

				Point2D point_1 = new Point2D(8, 11);

				if (point.equals(point_1)) {

					aux2.add(i);

					
					drinkOruse(i, x, y);
					
						
					
				}

			}
			itemBar.removeAll(aux2);
			break;

		case KeyEvent.VK_3:
			for (Item i : itemBar) {
				Point2D point = i.getPosition();

				Point2D point_1 = new Point2D(9, 11);

				if (point.equals(point_1)) {

					aux2.add(i);

					
					drinkOruse(i, x, y);
					
						
					

				}

			}
			itemBar.removeAll(aux2);
			break;

	

		}

	}
	
	public int randItemposition() {
		return (int) (Math.random() * (9 - 7 + 1) + 7);
	}

	public boolean itemBarSize() {
		if (itemBar.size() > 1) {
			return true;
		}
		return false;
	}

	public Item removeRandItem() {

		int rand = (int) (Math.random() * (2 - 0 + 1) + 0);
		Item i = itemBar.get(rand);
		GameEngine.getInstance().removeImageFromGui(i);
		itemBar().remove(rand);
		return i;
	}

	public Item randposition() {
		Item i = null;
		int rand1 = (int) (Math.random() * (2 - 0 + 1) + 0);
		int rand2 = (int) (Math.random() * (1 - 0 + 1) + 0);
		if (itemBar.size() == 3) {
			i = itemBar.get(rand1);
			GameEngine.getInstance().removeImageFromGui(i);
			itemBar().remove(rand1);
		} else {
			if (itemBar.size() == 2) {

				i = itemBar.get(rand2);
				GameEngine.getInstance().removeImageFromGui(i);
				itemBar().remove(rand2);
			} else {
				if (itemBar.size() == 1) {

					i = itemBar.get(0);
					GameEngine.getInstance().removeImageFromGui(i);
					itemBar().remove(0);
				}
			}
		}

		return i;

	}

	public void additems() {
		if (VeifyItemPosition(GameEngine.getInstance().getHeroPosition())) {
			addItemtoArrayAux(GameEngine.getInstance().getHeroPosition());

		}
	}

	public boolean haskey() {
		for (Item i : itemBar) {
			if (i.getName() == "Key") {

				return true;

			}
		}
		return false;
	}

	public String getkeycode() {
		String key = null;
		if (haskey()) {
			for (Item i : itemBar) {
				if (i.getName() == "Key") {
					key = i.getKeycode();
				}
			}
		}
		if (key == null) {

		}
		return key;
	}

	public void setposition(Point2D p) {
		position = p;
	}

}

package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {
	
    private int level;
	private  ArrayList<GameElement> elements; 
	private Hero hero;
	
	public Room(ArrayList<GameElement> elements, int level,Hero hero) {
	
		this.elements = elements;
		this.hero = hero;
		this.level = level;
				
	}
	
	public  List<GameElement> getRoomelements() {
		
		return elements;
		
	}
	public Hero getHero() {
		return hero;
	}
	public int getlevel() {
		return level;
	}
	public void setLevel(int l) {
		level = l;
	}
	
	public static Room createroom(int level,Hero hero) {
		 ArrayList<GameElement> aux = new ArrayList<>();
	
			 

			int y = 0;
		try {
			Scanner s = new Scanner(new File("rooms/room" + level + ".txt"));
			while (s.hasNextLine()) {
				
				char[] objtmap = s.nextLine().toCharArray();
				for (int x = 0; x != objtmap.length; x++) {
				
					switch (objtmap[x]) {
					case ' ' :
						Floor f = new Floor(new Point2D(x,y));
						GameEngine.getInstance().addImageToGui(f);
						aux.add(f);
						break;
						
					case '#':
						Wall w = new Wall(new Point2D(x,y));
						GameEngine.getInstance().addImageToGui(w);
						//walls.add(w);
						aux.add(w);
						break;
						default:
							break;
					}
					
				}
				y++;		
		
			}
			
			} catch (FileNotFoundException e) {
			System.err.println("Ficheiro " +  level + ".txt" + " nao encontrado");
			 System.exit(0);
			}
		
		
			try {
			Scanner sc = new Scanner(new File("rooms/room" + level + ".txt"));
			
			while (sc.hasNextLine()) {
				GameElement ge =GameElement.create(sc.nextLine());
				if(ge != null) {
					aux.add(ge);
					GameEngine.getInstance().addImageToGui(ge);
				}
			}
			}catch (FileNotFoundException e) {
				System.err.println("Ficheiro " + level + ".txt" + " nao encontrado");
				}

		
		
		GameEngine.getInstance().addImageToGui(hero);
		
		
		Room r = new Room(aux,level, hero );
		return r;
	}
	public GameElement getThief(){
		GameElement thief = null;
			for(GameElement e: elements) {
				if(e instanceof Enemy) {
					if(e.getName()=="Thief") {
						thief	= e;
					}
				}
			}
				return thief;
		}

	public void setHero(Hero h) {
		// TODO Auto-generated method stub
		hero = h;;
	}

	public void opendoor(Point2D p) {
		GameEngine g= GameEngine.getInstance();
		ArrayList<GameElement> aux = new ArrayList<>();
		for(GameElement ge :g.getRoom().getRoomelements()) {
			if(ge instanceof Door) {
				if(p.equals(ge.getPosition())) {
					g.removeImageFromGui(ge);
					aux.add(ge);
					g.addImageToGui(new DoorOpen(ge.getPosition(),ge.getdestroom(),ge.getdestposition()));
				}
			}
		}
	}


}

package projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.Position;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class GameEngine implements Observer {

	public static final int GRID_HEIGHT = 12;
	public static final int GRID_WIDTH = 10;

	private static GameEngine INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	

	private List<ImageTile> hpredbar = new ArrayList<>();
 	private List<Room>rooms =new ArrayList<>();
 	
 	private String player;
	private int roomlevelnow ;
	//private int roomlevelthen;
	public boolean victory = false;
	
	private Room room;
 	 	
	private int turns;

	
	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	public Hero getHero() {
		return room.getHero();
		
	}
	public Room getRoom() {

		return room;
	}

	public String getplayer() {
		return player;
	}

	
	public Point2D getHeroPosition() {
		return room.getHero().getPosition();
		
	}

	private GameEngine() {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
	}

	public void start() {
	   Hero hero = new Hero(new Point2D (1,1));

	 room = Room.createroom(0,hero);
	   
	 // room = Room.createroom(2,hero);
	   rooms.add(room);
	 	  	  
	   player = gui.askUser("Insert your user name");
	   if(player == null) {
		   player="GUEST";	
	   }
	    
		addBlackBar();
		addHealthBar();
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
		
		
	}

	
	public void removeFromGui(List<GameElement> ge) {

		for (GameElement e : ge) {
	    
		gui.removeImage(e);
		}
	
		gui.update();
	}
	public void addImageToGui(GameElement ge) {
		gui.addImage(ge);
	}
	public void addGrenToGui(ImageTile ge) {
		gui.addImage(ge);
	}
	
	public void addImagesToGui(List<ImageTile> l) {
		gui.addImages(l);
	}
	public void addImagesToGuige(List<GameElement> l) {
		for (GameElement e : l) {
            gui.addImage(e);
		}
		gui.update();
	}
	
	
	public void removeImageFromGui(GameElement ge) {
		gui.removeImage(ge);
		
	}
	
	
	public void removeImagesFromGui(List<ImageTile> ge) {
		gui.removeImages(ge);
		
	}
	
	
	public void setroomlevel(int n) {
		this.roomlevelnow = n;
	}
	
	
  private void addBlackBar() {
	 List<ImageTile> tileList = new ArrayList<>();
	  for (int i = 0; i != GRID_WIDTH; i++)
		  for(int j = 10; j != 12 ; j++)
			  tileList.add(new Black(new Point2D(i,j)));
	  gui.addImages(tileList);
		   
  }
  private void addHealthBar() {
		 List<ImageTile> hpbar1 = new ArrayList<>();
		
		  for (int i = 0; i <=  10; i++) {
		  for(int j = 10; j != 11 ; j++) {
			 hpbar1.add(new Green(new Point2D(i,j)));
				
		
	  gui.addImages(hpbar1);
	

		  }
  }
		  			   
 }


public void addredbar(int hp) {
	 List<ImageTile> aux = new ArrayList<>();

	 if(hp< 10) {
	 for(int i = 10; i>=hp;i--)
		 hpredbar.add(new Red (new Point2D(i,10)));
		  gui.addImages(hpredbar);
	 }		
					for(ImageTile h : hpredbar) {
					if(h.getPosition().getX() < hp)
						aux.add(h);
					   gui.removeImages(aux);
					}
	
			hpredbar.removeAll(aux);
			gui.removeImages(aux);
}
  
 
	
	public Vector2D vectorToHero(Point2D from) {

		return new Vector2D(room.getHero().getPosition().getX() - from.getX(), room.getHero().getPosition().getY() - from.getY());
	}
	
	public Vector2D directionToHero(Point2D position) {
		return vectorToHero(position);
	}
	
	public boolean canMoveTo(Point2D a) {
	
		for(GameElement ge: room.getRoomelements()) {
			if(ge.getPosition().equals(a) && !ge.isPassable() || getHero().getPosition().equals(a)) {
				return false;
			}
		}		
		
		return true;
	}
	public void restart(int key) {
		List<Room> aux = new ArrayList<>();
		switch(key) {
		case KeyEvent.VK_R:
			gui.clearImages();
			for(Room r: rooms) {
				aux.add(r);
				}
				System.out.println("jogo recomeçado");
				start();
			}
			rooms.removeAll(aux);
	}
	

	
			public  boolean verifykeypressed(int KeyCode){
				
				switch(KeyCode) {
				
				case KeyEvent.VK_DOWN:
				return true;
				
				case KeyEvent.VK_UP:
				
					return true;
				case KeyEvent.VK_LEFT:
					
					return true;
				case KeyEvent.VK_RIGHT:
				
					return true;
	
				
				}
				return false;
				
			}
			
			public void moveEnemies(List<GameElement> en) {
				for(GameElement ge: en) {
					if(ge instanceof Enemy) {
						if(ge.getHp() <= 0) {
							
						}else {
						ge.move();
						}
					}
				}
			}
	
		
			public void deadEenemy( List<GameElement> el) {
				ArrayList<GameElement> aux= new ArrayList<>(); 
				for(GameElement e: el) {
					if(e instanceof Enemy) {
	
						
						if(e.getHp() <= 0 ) {
						
						aux.add(e);
						gui.removeImage(e);
					
					}	
					}
				}
				el.removeAll(aux);
			}
	
	

			public void createroom(ArrayList<GameElement> list,int level,Hero hero) {
				Room r = new Room(list,level,hero);
			    rooms.add(r);
			
			}
	

public void changeroom() {
	for(GameElement e: room.getRoomelements()) {
		if(e instanceof DoorOpen) {
		if(e.getPosition().equals(room.getHero().getPosition())) {
		char a = e.getdestroom().charAt(4);
		
	int	level = Character.getNumericValue(a);
		
		Point2D nesposition = e.getdestposition();
		
		nextroom(level,nesposition);
	
		}
		}
	}
}


	public void nextroom(int level, Point2D p) {
		
			
			removeFromGui(room.getRoomelements());
			removeImageFromGui(room.getHero());
			
			room.getHero().setposition(p);
			Hero hero = room.getHero();
			room.setHero(null);		
			boolean found = false;
	        int i= 0;
	        
	        if(rooms.size()==1) {
	        	roomlevelnow++;
	        	room = Room.createroom(level,hero);
	        	rooms.add(room);
	        	room.opendoor(p);
	     
	        }else {
	        //	roomlevelthen = roomlevelnow;
	        	for(int j = 0; j<rooms.size();j++) {
	        		if(rooms.get(j).getlevel() == level) {
	        			found = true;
	        			i=j;
	        			
	        		}
	        	        		
	        		if(found) {        		
	        			room = rooms.get(i);
	        			room.setHero(hero);
	       			    addImagesToGuige(room.getRoomelements());
	        	        gui.addImage(room.getHero());
	        	    
	        	     break;
	        		}else {
	        			roomlevelnow++;
	        			room = Room.createroom(level, hero);
	        			rooms.add(room);
	        		room.opendoor(p);
	        			break;
	        			
	        			
	        		}
	        	}
	        }
	        
	        setroomlevel(level);
	     
	        gui.update();
			}	

	public void herodead()  {
		List<Room> aux = new ArrayList<>(); 
		if(room .getHero().getHp() <=0) {
			
			
			gui.clearImages();
			for(Room r: rooms) {
			aux.add(r);
			}
			System.out.println("you died the game was restarted");
			start();
			try {
				HighScore.write(victory+" "+ turns+" "+ player);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HighScore.top5();
		}
		rooms.removeAll(aux);
	}
	
	public void showscores() {
		if(getRoom().getHero().getHp()<= 0 ) {
			try {
				HighScore.write(victory+" "+ turns+" "+ player);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void GameOver( ) {
	 		
	for(GameElement ge: getRoom().getRoomelements() ) {
		if(ge instanceof Treasure) {
			if(getRoom().getHero().getPosition().equals(ge.getPosition())) {
			victory = true;
			if(victory) {
				
				gui.clearImages();
			
			for (int i = 0; i != GRID_WIDTH; i++)
		            for (int j = 0; j != GRID_HEIGHT; j++)
		                gui.addImage(new Floor(new Point2D(i, j)));
			System.out.println("Parabens Ganhou O Jogo");
			System.out.println("Se quiser recomeçar carregue R");
			try {
				HighScore.write(victory+" "+ turns+" "+ player);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HighScore.top5();
			}
			}	
			

		}
	}
	}

	@Override

	public void update(Observed source) {
		
		
		if (ImageMatrixGUI.getInstance().wasWindowClosed()) {
			System.out.println("Ending");
			HighScore.top5();	
		}
	
		int key = ((ImageMatrixGUI) source).keyPressed();
						
		if(verifykeypressed(key)){
			room.getHero().move(key);
			room.getHero().additems();
		
		}
		
		restart(key);
		room.getHero().removeOrdrink( key);
				
		turns++;
	
		deadEenemy(getRoom().getRoomelements());
	
		moveEnemies(getRoom().getRoomelements());
	 
		addredbar(room.getHero().getHp());
		showscores();
		herodead();
        changeroom();
    
			GameOver();
		
		
		if(gui.wasWindowClosed()) {
				
			try {
				HighScore.write(victory+" "+ turns+" "+ player);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
				
		
	}		
	
}

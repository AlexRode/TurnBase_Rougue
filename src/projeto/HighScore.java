package projeto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.utils.Point2D;

public class HighScore {

	private String player;
	private int turns;
	private boolean victory;
	static List<HighScore> list = new ArrayList<HighScore>();

	
	
	public HighScore(String player, int turns, boolean victory) {
		this.player = player;
		this.turns = turns;
		this.victory = victory;
	}


public String getPlayer() {
	return player;
}

public int getturns() {
	return turns;
}

public boolean victory() {
	return victory;
}



@Override
public String toString() {
	return"HighScore player-"+ player + "- turns-" + turns + "- victory-" + victory +"\r\n";
}
public static List<HighScore> read() {
//	List<HighScore> list = new ArrayList<HighScore>();
	Scanner s;
	try {
		s = new Scanner(new File("scores/scores.txt"));
		while (s.hasNextLine()) {
			try {
				boolean won = s.nextBoolean();
				int movements = s.nextInt();
				String name = s.nextLine();
				list.add(new HighScore(name, movements, won));
			} catch (NumberFormatException e) {

			}

		}

	} catch (FileNotFoundException e) {
		
	}
	Collections.sort(list, new CompartorHighScore());
	return list;
}

public static void createFile(String name) {
	try {
	File scores = new File("scores/"+name + ".txt");
	if(scores.createNewFile()) {
		System.out.println("File created: " + scores.getName());
	}else {
		System.out.println("File already exists.");
	}
} catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}


public static void write(String args) 
	 throws IOException {
	File f = new File("scores/scores.txt");
	if(f.isFile() && !f.isDirectory()) { 

	Writer output;
	output = new BufferedWriter(new FileWriter("scores/scores.txt",true));
	output.append(args+"\r\n");
	output.close();
	}else {
		createFile("scores");
		}			
}

public static void top5() {
ArrayList<HighScore> aux = new ArrayList<>();
	read();
	if(list.size()>5) {
	for (int i = 0; i < 5; i++) {
	
	    System.out.println(list.get(i));
	  aux.add(list.get(i));
	}
	}else {
		System.out.println(list);
	}
}


}
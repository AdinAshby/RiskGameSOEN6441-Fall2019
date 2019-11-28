package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.Game;
import controller.Game.Phase;

public class GameDirector {

	private GameBuilder gameBuilder;
	final File mapFolder = new File("./MapFiles");
	public void setBuilder(GameBuilder newGameBuilder) {
		gameBuilder=newGameBuilder;
	}
	 
	
	
	public void saveGame(String mapFileName) {
		gameBuilder.createNewGame();
		gameBuilder.buildMapGeo();
		gameBuilder.buildTurnPlayer();
		gameBuilder.buildPhase();
		try {
	System.out.println("Save Game by Game Director");
            FileOutputStream fileOut = new FileOutputStream(mapFolder + "/" + mapFileName + ".ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(gameBuilder.getGame());
            objectOut.close();
            objectOut.flush();
            System.out.println("The game  was succesfully written to "+mapFileName+" file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public Game loadGame(String mapFileName) {
		 Game game = null;
		gameBuilder.createNewGame();
		ObjectInputStream objectIn = null;
		try {
		FileInputStream fileIn = new FileInputStream(mapFolder + "/" + mapFileName + ".ser");
        objectIn = new ObjectInputStream(fileIn);
        game = (Game) objectIn.readObject();
      //  game=gameBuilder.getGame();
        objectIn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	return game;
	}
	
	public Game getGame() {
	    return gameBuilder.getGame();
	  }

	
	
}

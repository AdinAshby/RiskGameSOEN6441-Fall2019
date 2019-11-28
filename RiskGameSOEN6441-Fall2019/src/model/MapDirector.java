package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MapDirector {

	private MapBuilder mapBuilder;
	
	public void setBuilder(MapBuilder newMapBuilder) {
		mapBuilder=newMapBuilder;
	}
	
	public void saveGame(String mapFileName) {
		mapBuilder.createNewMapGeo();
		mapBuilder.buildContinents();
		mapBuilder.buildPlayers();
		mapBuilder.buildGamePhase();
		try {
			final File mapFolder = mapBuilder.getMapGeo().mapFolder;
            FileOutputStream fileOut = new FileOutputStream(mapFolder + "/" + mapFileName + ".ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(mapBuilder);
            objectOut.close();
            objectOut.flush();
            System.out.println("The game  was succesfully written to "+mapFileName+" file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void loadGame(String mapFileName) {
		mapBuilder.createNewMapGeo();
		final File mapFolder = mapBuilder.getMapGeo().mapFolder;
		ObjectInputStream objectIn = null;
		try {
		FileInputStream fileIn = new FileInputStream(mapFolder + "/" + mapFileName + ".ser");
        objectIn = new ObjectInputStream(fileIn);
        mapBuilder = (MapBuilder) objectIn.readObject();
        objectIn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	
	}
	
	public MapGeo getMapGeo() {
	    return mapBuilder.getMapGeo();
	  }

	
	
}

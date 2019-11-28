package model;

import java.io.File;
import java.io.FileOutputStream;
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
	public MapGeo getMapGeo() {
	    return mapBuilder.getMapGeo();
	  }

	
	
}

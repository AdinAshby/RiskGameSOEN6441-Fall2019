package model;

public class MapDirector {

	private MapBuilder mapBuilder;
	
	public void setBuilder(MapBuilder newMapBuilder) {
		mapBuilder=newMapBuilder;
	}
	
	public void constructMapGeo() {
		mapBuilder.createNewMapGeo();
		mapBuilder.buildContinents();
		mapBuilder.buildPlayers();
		mapBuilder.buildGamePhase();
	}
	public MapGeo getMapGeo() {
	    return mapBuilder.getMapGeo();
	  }

	
	
}

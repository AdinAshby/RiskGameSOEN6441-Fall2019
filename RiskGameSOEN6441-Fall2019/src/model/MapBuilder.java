package model;

public abstract class MapBuilder {

		  /**
		   * Map to be constructed by the builder
		   */
		  protected MapGeo mapGeo;
		  /**
		   * Get the constructed Map from the Builder
		   */
		  public MapGeo getMapGeo(){
		    return mapGeo;
		  }
		  /**
		   * Create a new unspecified Map that 
		   * will be eventually build by calling the 
		   * following abstract methods in a concrete 
		   * class derived from the MapGeo class
		   */
		  public void createNewMapGeo(){
			  mapGeo = new MapGeo();
		  }
		  abstract void buildContinents();
		  abstract void buildPlayers();
		  abstract void buildGamePhase();
		}

package model;

public class MapAdapter extends MapDomination {
	/**
	 * private mapConquest
	 */
	private MapConquest mapConquest;

	/**
	 * 
	 * @param mapType
	 */
	public MapAdapter(MapConquest mapConquest) {
		this.mapConquest = mapConquest;

	}

	/**
	 * This method is for read the map
	 * 
	 * @param mapName
	 */
	public boolean read(String mapName) {
		mapConquest.readConquest(mapName);
		// this.continentList=mapConquest.continentList;
		this.mapAdapterCopy(mapConquest);
		return true;

	}

	/**
	 * This method is for write the map
	 * 
	 * @param mapName
	 * @throws Exception
	 */
	public boolean write(String mapName) throws Exception {
		mapConquest.writeConquest(mapName);
		return true;

	}

	/**
	 * 
	 * @param mapConquest
	 */
	public void mapAdapterCopy(MapConquest mapConquest) {
		this.continentList = mapConquest.continentList;
		this.theMapView = mapConquest.theMapView;
		this.countryAdjacency = mapConquest.countryAdjacency;
		this.players = mapConquest.players;
		this.random = mapConquest.random;
	}

	/**
	 * mapConquestCopy
	 */
	public void mapConquestCopy() {
		mapConquest.continentList = this.continentList;
		mapConquest.theMapView = this.theMapView;
		mapConquest.countryAdjacency = this.countryAdjacency;
		mapConquest.players = this.players;
		mapConquest.random = this.random;
	}

}
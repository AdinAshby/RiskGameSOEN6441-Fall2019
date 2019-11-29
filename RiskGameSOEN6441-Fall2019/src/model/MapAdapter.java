package model;
/**
 * 
 * This is MapAdapter class which extends MapDomination
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 * @author Babita kaur
 */
public class MapAdapter extends MapDomination {
	/**
	 * private mapConquest
	 */
	private MapConquest mapConquest;

	/**
	 * This is constructor for initialising Maptype
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
	 * This method makes copy of mapconquest
	 * @param mapConquest
	 */
	public void mapAdapterCopy(MapConquest mapConquest) {
		this.continentList = mapConquest.continentList;
		this.theMapView = mapConquest.theMapView;
		this.countryAdjacency = mapConquest.countryAdjacency;
		this.players = mapConquest.players;
		this.random = mapConquest.random;
	}

	


}
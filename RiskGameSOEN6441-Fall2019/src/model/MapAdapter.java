package model;

public class MapAdapter extends MapGeo {
	/**
	 * private MapGeo
	 */
	private MapGeo mapGeo;
	/**
	 * private mapType
	 */
	private String mapType;

	/**
	 * 
	 * @param mapType
	 */
	public MapAdapter(String mapType) {

		if (mapType.equalsIgnoreCase("d")) {
			mapGeo = new MapDomination();
		} else if (mapType.equalsIgnoreCase("c")) {
			mapGeo = new MapConquest();
		}
		this.mapType = mapType;

	}

	/**
	 * This method is for read the map
	 * 
	 * @param mapName
	 */
	public boolean read(String mapName) {
		if (mapType.equalsIgnoreCase("d")) {
			mapGeo.readDomination(this, mapName);
		} else if (mapType.equalsIgnoreCase("c")) {
			mapGeo.readConquest(mapName);
		}
		return true;

	}

	/**
	 * This method is for write the map
	 * 
	 * @param mapName
	 */
	public boolean write(String mapName) {
		if (mapType.equalsIgnoreCase("d")) {
			mapGeo.write(mapName);
		} else if (mapType.equalsIgnoreCase("c")) {
			mapGeo.writeConquest(mapName);
		}
		return true;

	}

}

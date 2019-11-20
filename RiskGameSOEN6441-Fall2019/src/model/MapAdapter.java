package model;

public class MapAdapter extends MapDomination {
	
	private MapConquest mapConquest;
	
	public MapAdapter(MapConquest mapConquest) {
		this.mapConquest=mapConquest;
	}
	
	public void read() {
		mapConquest.readConquest();
	}
	
	public void write() {
		mapConquest.writeConquest();
	}

}

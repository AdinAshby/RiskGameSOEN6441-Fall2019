package model;

import java.io.Serializable;

public class MapDominationBuilder extends MapBuilder implements Serializable{
	//default serialVersion id
    private static final long serialVersionUID = 1L;
	private MapDomination mapDomination;

	public MapDominationBuilder(MapDomination mapDomination) {
		this.mapDomination=mapDomination;
	}

	@Override
	void buildContinents() {
		mapGeo.continentList=mapDomination.continentList;
		
	}

	@Override
	void buildPlayers() {
		mapGeo.players=mapDomination.players;
		
	}

	@Override
	void buildGamePhase() {
		// TODO Auto-generated method stub
		
	}

}

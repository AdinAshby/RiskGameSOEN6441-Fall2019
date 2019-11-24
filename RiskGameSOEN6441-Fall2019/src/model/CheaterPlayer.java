package model;

import java.util.ArrayList;

public class CheaterPlayer extends Player implements Strategy {

	public CheaterPlayer(String playerName, ArrayList<Integer> countriesIDs, MapGeo mapBuild) {
		super(playerName, countriesIDs, mapBuild);
		
	}
	
	@Override
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, MapGeo mapBuild, int attackAllout){
		
	}

	@Override
	public boolean reinforce(MapGeo mapBuild, String countryName, int num, boolean finished) {
		mapBuild.getCountryByName(countryName)
		.setArmies(mapBuild.playerContinentValuesOwnership(this.getPlayerName()) + num * 2);
		// mapBuild.reinforce(getPlayerName(), countryName, num);
		calculateWorldDominationView();
		
		return true;
	}

	@Override
	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapGeo mapBuild) {
		
		int oldArmies = mapBuild.getCountryByName(fromCountry).getArmies();
		mapBuild.getCountryByName(fromCountry).setArmies(oldArmies * 2);

		calculateWorldDominationView();
	}

}

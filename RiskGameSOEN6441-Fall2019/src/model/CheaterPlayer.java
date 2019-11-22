package model;

public class CheaterPlayer extends Player implements Strategy {

	public CheaterPlayer(String playerName, int[] countryID, MapBuilder mapBuild) {
		super(playerName, countryID, mapBuild);
		
	}
	
	@Override
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, MapBuilder mapBuild){
		
	}

	@Override
	public boolean reinforce(MapBuilder mapBuild, String countryName, int num, boolean finished) {
		return false;
	}

	@Override
	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapBuilder mapBuild) {
		
	}

}

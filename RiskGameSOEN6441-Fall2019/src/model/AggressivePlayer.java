package model;

public class AggressivePlayer extends Player implements Strategy {

	public AggressivePlayer(String playerName, int[] countryID, MapGeo mapBuild) {
		super(playerName, countryID, mapBuild);
	}
	
	@Override
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, MapGeo mapBuild){
		
	}

	@Override
	public boolean reinforce(MapGeo mapBuild, String countryName, int num, boolean finished) {
		int temporaryArmies = 0;
		
		if (reinforceIsValid(mapBuild, countryName, num) == true) {
			int oldArmies = mapBuild.getCountryByName(countryName).getArmies();
			mapBuild.getCountryByName(countryName)
					.setArmies(oldArmies + mapBuild.playerContinentValuesOwnership(this.getPlayerName()) + num);
			// mapBuild.reinforce(getPlayerName(), countryName, num);
			calculateWorldDominationView();
			temporaryArmies -= num;

			if (temporaryArmies <= 0) {
				finished = true;
			}

		} else {
			System.out.println("Reinforce is not valid");
		}
		return finished;
		
	}

	@Override
	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapGeo mapBuild) {
		
		
	}


}

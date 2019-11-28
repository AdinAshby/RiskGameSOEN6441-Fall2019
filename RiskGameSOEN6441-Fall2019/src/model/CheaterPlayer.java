package model;

import java.util.ArrayList;
/**
 * This class is made to implement Cheater Player Strategy
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 * @author Babita kaur
 * 
 */
public class CheaterPlayer extends Player implements Strategy {
	/**
	 * This is  constructor for initializing Cheater Player
	 * 
	 * @param playerName
	 * @param countriesIDs
	 * @param mapBuild
	
	 */
	public CheaterPlayer(String playerName, ArrayList<Integer> countriesIDs, MapGeo mapBuild) {
		super(playerName, countriesIDs, mapBuild);
		setStrategy(this);
	}
	/**
	 * This is attack method for CheaterPlayer
	 * 
	 * @param attackerCountry
	 * @param attackingCountry
	 * @param attackerNumDice
	 * @param defendNumDice
	 * @param attackAllout
	 */
	@Override
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, int attackAllout){
		
		attackingCountry.setPlayer(attackerCountry.getPlayerName());
		addCountryIdToPlayer(attackingCountry.getCountryId());
		int NoOfContinentsControlled = getContinentsControlled().size();
		
		if (NoOfContinentsControlled == mapGeo
				.getNoOfContinentsControlled()) {
			System.out.println(attackerCountry.getPlayerName()
					+ " is winner. Game over!");
			this.setWon(true);
			//System.exit(0);
		}
		mapGeo.showMap();
	}
	/**
	 * This is reinforce method for CheaterPlayer
	 * 
	 * @param mapBuild
	 * @param countryName
	 * @param num
	 * @param finished
	 
	 */

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

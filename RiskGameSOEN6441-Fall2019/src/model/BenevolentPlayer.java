package model;

import java.util.ArrayList;
/**
 * This class is made to implement Benevolent Player Strategy
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 * @author Babita kaur
 * 
 */
public class BenevolentPlayer extends Player implements Strategy {
	/**
	 * This is  constructor for initializing Benevolent Player
	 * 
	 * @param playerName
	 * @param countriesIDs
	 * @param mapBuild
	
	 */
	public BenevolentPlayer(String playerName, ArrayList<Integer> countriesIDs, MapGeo mapBuild) {
		super(playerName, countriesIDs, mapBuild);
		setStrategy(this);
	}
	/**
	 * This is attack method for BenevolentPlayer
	 * 
	 * @param attackerCountry
	 * @param attackingCountry
	 * @param attackerNumDice
	 * @param defendNumDice
	 * @param attackAllout
	 */
	@Override
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, int attackAllout){
		
	}
	/**
	 * This is reinforce method for BenevolentPlayer
	 * 
	 * @param mapBuild
	 * @param countryName
	 * @param num
	 * @param finished
	 
	 */

	@Override
	public boolean reinforce(MapGeo mapBuild, String countryName, int num, boolean finished) {
		int oldArmies = mapBuild.getCountryByName(countryName).getArmies();
		mapBuild.getCountryByName(countryName)
		.setArmies(oldArmies + mapBuild.playerContinentValuesOwnership(this.getPlayerName()) + num);
		// mapBuild.reinforce(getPlayerName(), countryName, num);
		calculateWorldDominationView();
		
		return true;
	}
	/**
	 * This is fortify method for BenevolentPlayer
	 * 
	 * @param fromCountry
	 * @param toCountry
	 * @param armiesToMove
	 * @param mapBuild
	 
	 */
	@Override
	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapGeo mapBuild) {
		int oldArmiesFromCountry = mapBuild.getCountryByName(fromCountry).getArmies();
		mapBuild.getCountryByName(fromCountry).setArmies(oldArmiesFromCountry - armiesToMove);

		int oldArmiesToCountry = mapBuild.getCountryByName(toCountry).getArmies();
		mapBuild.getCountryByName(toCountry).setArmies(oldArmiesToCountry + armiesToMove);
		calculateWorldDominationView();	
	}
	

}

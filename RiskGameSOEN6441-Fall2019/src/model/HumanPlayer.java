package model;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is made to implement Human Player  Strategy
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 * @author Babita kaur
 * 
 */
public class HumanPlayer extends Player implements Strategy {
	/**
	 * This is  constructor for initializing HumanPlayer
	 * 
	 * @param playerName
	 * @param countriesIDs
	 * @param mapBuild
	
	 */
	public HumanPlayer(String playerName, ArrayList<Integer> countriesIDs, MapGeo mapGeo) {
		
		super(playerName, countriesIDs, mapGeo);
		setStrategy(this);
	} 
	/**
	 * This is attack method for HumanPlayer
	 * 
	 * @param attackerCountry
	 * @param attackingCountry
	 * @param attackerNumDice
	 * @param defendNumDice
	 * @param attackAllout
	 */
	@Override
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, int attackAllout){

		System.out.println("Own Contries by Human Player" + this.countryIDs);

		Dice attackDice = new Dice(attackerNumDice);
		int[] attackDiceArray = attackDice.getDiceArray();
	
		Dice defendDice = new Dice(defendNumDice);
		int[] defendDiceArray = defendDice.getDiceArray();
		

		boolean[] winner = attackDice.isWinner(defendDice);
		

		for (int i = 0; i < winner.length; i++) {
			int diceNo = i + 1;
			if (winner[i]) {
				System.out.println("Attacker win dice " + diceNo );
				attackingCountry.setArmies(attackingCountry.getArmies() - 1);
				calculateWorldDominationView();
				if (attackingCountry.getArmies() == 0) {
					System.out.println("\n*****"+
							attackingCountry.getCountryName() + " is conquered"+"*****\n");
					mapGeo.setContinentNamesOfPlayer(this);
					Card card = new Card();
					addCard(card);
					System.out.println("You have the following cards now :");
					System.out.println(getCardNames());
					attackingCountry.setPlayer(attackerCountry.getPlayerName());
					addCountryIdToPlayer(attackingCountry.getCountryId());
					int NoOfContinentsControlled = getContinentsControlled().size();
					if (NoOfContinentsControlled == mapGeo
							.getNoOfContinentsControlled()) {
						System.out.println(attackerCountry.getPlayerName()
								+ " is winner. Game over!");
						this.setWon(true);
						
					}
					mapGeo.showMap();
					
					attackMoveCommand(attackerCountry, attackingCountry, attackAllout);

				}

			} else {
				System.out.println("Defender win dice " + diceNo );
				attackerCountry.setArmies(attackerCountry.getArmies() - 1);
				calculateWorldDominationView();
				if (attackerCountry.getArmies() == 0) {
					System.out.println(
							attackerCountry.getCountryName() + " is conquered");
					attackerCountry.setPlayer(attackingCountry.getPlayerName());
				}
			}

		}
	}

	/**
	 * This is reinforce method for HumanPlayer
	 * 
	 * @param mapBuild
	 * @param countryName
	 * @param num
	 * @param finished
	 
	 */

	@Override
	public boolean reinforce(MapGeo mapGeo, String countryName, int num, boolean finished) {

		if (reinforceIsValid(mapGeo, countryName, num) == true) {
			int oldArmies = mapGeo.getCountryByName(countryName).getArmies();
			mapGeo.getCountryByName(countryName)
			.setArmies(oldArmies + mapGeo.playerContinentValuesOwnership(super.getPlayerName()) + num);
		
			calculateWorldDominationView();
			
			super.setTemporaryArmies(super.getTemporaryArmies() - num);

			if (super.getTemporaryArmies() <= 0) {
				finished = true;
			}

		} else {
			System.out.println("Reinforce is not valid");
		}
		return finished;
		
	}
	/**
	 * This is fortify method for HumanPlayer
	 * 
	 * @param fromCountry
	 * @param toCountry
	 * @param armiesToMove
	 * @param mapBuild
	 
	 */

	@Override
	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapGeo mapGeo) {
		if (fortifyIsValid(fromCountry, toCountry, armiesToMove, mapGeo) == true) {
			int oldArmiesFromCountry = mapGeo.getCountryByName(fromCountry).getArmies();
			mapGeo.getCountryByName(fromCountry).setArmies(oldArmiesFromCountry - armiesToMove);

			int oldArmiesToCountry = mapGeo.getCountryByName(toCountry).getArmies();
			mapGeo.getCountryByName(toCountry).setArmies(oldArmiesToCountry + armiesToMove);
			calculateWorldDominationView();

		}
	}

}

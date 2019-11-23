package model;

public class HumanPlayer extends Player implements Strategy {

	public HumanPlayer(String playerName, int[] countryID, MapGeo mapBuild) {
		super(playerName, countryID, mapBuild);
	}
	
	@Override
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, MapGeo mapBuild, int attackAllout){
		Dice attackDice = new Dice(attackerNumDice);
		int[] attackDiceArray = attackDice.getDiceArray();
		//attackDice.showDice();

		Dice defendDice = new Dice(defendNumDice);
		int[] defendDiceArray = defendDice.getDiceArray();
		//defendDice.showDice();

		boolean[] winner = attackDice.isWinner(defendDice);


		for (int i = 0; i < winner.length; i++) {
			int diceNo = i + 1;
			if (winner[i]) {
				System.out.println("Attacker win dice " + diceNo);
				attackingCountry.setArmies(attackingCountry.getArmies() - 1);
				calculateWorldDominationView();
				if (attackingCountry.getArmies() == 0) {
					System.out.println(
							attackingCountry.getCountryName() + " is conquered");
					mapBuild.setContinentNamesOfPlayer(this);
					Card card = new Card();
					addCard(card);
					System.out.println("You have the following cards now :");
					System.out.println(getCardNames());
					attackingCountry.setPlayer(attackerCountry.getPlayerName());
					int NoOfContinentsControlled = getContinentsControlled().size();
					if (NoOfContinentsControlled == mapBuild
							.getNoOfContinentsControlled()) {
						System.out.println(attackerCountry.getPlayerName()
								+ " is winner. Game over!");
						System.exit(0);
					}

					
					attackMoveCommand(attackerCountry, attackingCountry, attackAllout);

				}

			} else {
				System.out.println("Defender win dice " + diceNo);
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

	@Override
	public boolean reinforce(MapGeo mapBuild, String countryName, int num, boolean finished) {

		if (reinforceIsValid(mapBuild, countryName, num) == true) {
			int oldArmies = mapBuild.getCountryByName(countryName).getArmies();
			mapBuild.getCountryByName(countryName)
			.setArmies(oldArmies + mapBuild.playerContinentValuesOwnership(super.getPlayerName()) + num);
			// mapBuild.reinforce(getPlayerName(), countryName, num);
			calculateWorldDominationView();
			//temporaryArmies -= num;
			super.setTemporaryArmies(super.getTemporaryArmies() - num);

			if (super.getTemporaryArmies() <= 0) {
				finished = true;
			}

		} else {
			System.out.println("Reinforce is not valid");
		}
		return finished;
		
	}

	@Override
	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapGeo mapBuild) {
		if (fortifyIsValid(fromCountry, toCountry, armiesToMove, mapBuild) == true) {
			int oldArmiesFromCountry = mapBuild.getCountryByName(fromCountry).getArmies();
			mapBuild.getCountryByName(fromCountry).setArmies(oldArmiesFromCountry - armiesToMove);

			int oldArmiesToCountry = mapBuild.getCountryByName(toCountry).getArmies();
			mapBuild.getCountryByName(toCountry).setArmies(oldArmiesToCountry + armiesToMove);
			calculateWorldDominationView();

		}
	}

}

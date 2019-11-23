package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import view.MapView;

/**
 * 
 * This is Player class, in which we define our attributes of a Player.
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 */
public class Player implements Subject {

	/**
	 * PlayerName is name of the player corresponding to the ID.
	 */
	private String playerName;

	/**
	 * List of all countryIDs that the player owns.
	 */
	private int[] countryIDs;
	/**
	 * private percentage Controlled 
	 */

	private double percentageControlled = 0.00;
/**
 * private continentsControlled
 */
	private ArrayList<String> continentsControlled = new ArrayList<String>();
	/**
	 * private int total Number Of Armies
	 */
	private int totalNumberOfArmies = 0;
	/**
	 * private cards
	 */
	private ArrayList<Card> cards;
	/**
	 * private player Count For Card 
	 */
	private int playerCountForCard;
	/**
	 * private number Of Armies Each Player Gets
	 */
	private int numberOfArmiesEachPlayerGets;
	/**
	 * private allow To Get Card
	 */
	private boolean allowToGetCard;
	/**
	 * private counter For Phases
	 */

	private int counterForPhases;
/**
 * private temporary Armies
 */
	private int temporaryArmies;
/**
 * private observers For Phases
 */
	private ArrayList<Observer> observersForPhases = new ArrayList<Observer>();
	/**
	 * private observers For World Domination
	 */
	private ArrayList<Observer> observersForWorldDomination = new ArrayList<Observer>();
/**
 * private mapBuild
 */
	private MapGeo mapBuild;
	
	/**
	 * private strategy
	 */

	private Strategy strategy;

	/**
	 * private scanner
	 */
	private Scanner scanner = new Scanner(System.in);
	/**
	 * private input
	 */
	private String input;
	/**
	 * private regex
	 */
	private String regex;
	/**
	 * private pattern
	 */
	private Pattern pattern;
	/**
	 * private matcher
	 */
	private Matcher matcher;
	/**
	 * private reinforceRequestingMessage
	 */
	private String reinforceRequestingMessage = "Now, it's time to reinforce!\n";
	/**
	 * private fortifyRequestingMessage
	 */
	private String fortifyRequestingMessage = "Let's fortify! or type in \"fortify none\" if you don't want to.\n";

	private String attackRequestingMessage = "Let's Attack\n";

	/**
	 * This is Player constructor method for initializing PlayerName and countryId
	 * 
	 * @param playerID   A unique ID for each player.
	 * @param playerName Corresponding player name.
	 * @param countryID  List of all countries (IDs) that the player owns.
	 */
	public Player(String playerName, int[] countryID, MapGeo mapBuild) {
		this.playerName = playerName;
		this.countryIDs = countryID;
		this.mapBuild = mapBuild;
		cards = new ArrayList<Card>();
		playerCountForCard = 0;
	}
/**
 * private set strategy
 * @param strategy
 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
/**
 * private add One To Card Counter
 */
	public void addOneToCardCounter() {
		playerCountForCard++;
	}

	/**
	 * This is Getter method for PlayerName.
	 * 
	 * @return playerName Returns playerName as a String.
	 */
	public String getPlayerName() {
		return playerName;
	}
/**
 * This method get player Count For Card
 * 
 * @return player Count For Card
 */
	public int getplayerCountForCard() {

		return playerCountForCard;

	}
/**
 * 
 * @return cardsList
 */
	public String getCardNames() {
		String cardsList = "Cards: -";
		for (Card c : cards) {
			cardsList = c.getCardType();
		}
		return cardsList;

	}
/**
 * 
 * @return cards
 */
	public ArrayList<Card> getCards() {

		return cards;

	}
/**
 * 
 * @param cardList
 */
	public void setCards(ArrayList<Card> cardList) {

		for (int i = 0; i < cardList.size(); i++) {

			cardList.remove(i);

		}

		for (Card cardItem : cardList) {

			this.cards.add(cardItem);

		}

	}
/**
 * 
 * @param cardToAdd
 */
	public void addCard(Card cardToAdd) {

		this.cards.add(cardToAdd);

	}

	public boolean isMoreThanFive() {

		if (cards.size() >= 5)

			return true;

		else

			return false;

	}
/**
 * 
 * @return
 */
	public boolean getAllowingCardStatus() {

		return this.allowToGetCard;

	}
/**
 * 
 * @param status
 */
	public void setAllowingStatus(boolean status) {

		this.allowToGetCard = status;

	}

	/**
	 * This is Getter Method for CountryIDs.
	 * 
	 * @return getCountryID Returns CountryIDs as an array of integers.
	 */
	public int[] getCountryIDs() {
		return countryIDs;
	}

	/**
	 * This is Setter Method for CountryIDs.
	 * 
	 * @param countriesIDs Sets a list of countriesIDs to the corresponding field.
	 */
	public void setCountryIDs(int[] countriesIDs) {
		this.countryIDs = countriesIDs;
	}
/**
 * 
 * @return percentageControlled
 */
	public double getPercentageControlled() {
		return percentageControlled;
	}
/**
 * 
 * @return continentsControlled
 */
	public ArrayList<String> getContinentsControlled() {
		return continentsControlled;
	}
/**
 * 
 * @param continentsControlled
 */
	public void setContinentsControlled(ArrayList<String> continentsControlled) {
		this.continentsControlled = continentsControlled;
	}
/**
 * 
 * @return totalNumberOfArmies
 */
	public int getTotalNumberOfArmies() {
		return totalNumberOfArmies;
	}
/**
 * 
 * @param mapBuild
 * @param attackerNumDice
 * @param attackerCountry
 * @param attackingCountry
 * @param enablePrint
 * @return isValid
 */
	public boolean isAttackValid(MapGeo mapBuild, int attackerNumDice, Country attackerCountry,
			Country attackingCountry, boolean enablePrint) {
		boolean isValid = true;
		if (attackingCountry.getPlayerName().equals(getPlayerName())) {
			System.out.println("You can not attack to your own country");
			isValid = false;
		}

		if (attackerCountry.getArmies() <= 1) {
			System.out.println("Attacker Country should have more than 1 army");
			isValid = false;
		}
		if (attackerCountry == null || attackingCountry == null) {
			System.out.println("Enter Attacker and Attacking Country");
			isValid = false;
		} else {

			if (attackerNumDice > attackingCountry.getArmies()) {
				if (enablePrint)
					System.out.println("attacking dice (" + attackerNumDice
							+ ") should not be more than the number of armies contained in the attacking country");
				isValid = false;
			} else if (attackerNumDice > 3) {
				if (enablePrint)
					System.out.println("attacking dice (" + attackerNumDice + ") should not be more than 3");
				isValid = false;
			} else if (!mapBuild.isAdjacentCountry(attackerCountry.getCountryId(), attackingCountry.getCountryId())) {
				if (enablePrint)
					System.out.println("Countries are not adjacent");
				isValid = false;

				/// Country should be owned and attacking country for opponent
			}
		}
		return isValid;
	}
/**
 * 
 * @param mapBuild
 * @return isAttackPossible
 */
	public boolean isAttackPossible(MapGeo mapBuild) {
		boolean isAttackPossible = false;
		for (int countyId : countryIDs) {
			// Country country=mapBuild.getCountryById(countyId);
			// System.out.println("Checking " + countyId);
			ArrayList<Integer> CountryAdjList = mapBuild.getCountryAdjacency().getVertexAdjacency(countyId);
			// System.out.println(CountryAdjList); //Arrays.toString(

			for (int adjCountry : CountryAdjList) {
				if (!contains(countryIDs, adjCountry)) {
					for (int i = 1; i < 4; i++) {
						if (isAttackValid(mapBuild, i, mapBuild.getCountryById(countyId),
								mapBuild.getCountryById(adjCountry), false)) {
							isAttackPossible = true;
						}
					}
				}
			}

		}

		return isAttackPossible;
	}
/**
 * 
 * @param array
 * @param v
 * @return result
 */
	public static boolean contains(final int[] array, final int v) {

		boolean result = false;

		for (int i : array) {
			if (i == v) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * This method check whether is defend possible
	 * 
	 * @return isDefendPossible
	 */

	public boolean isDefendPossible(MapGeo mapBuild, int defendCountry, int numDice) {

		boolean isDefendPossible = true;
		if (!contains(this.getCountryIDs(), defendCountry)) {
			isDefendPossible = false;
		}
		if (mapBuild.getCountryById(defendCountry).getArmies() < numDice) {
			isDefendPossible = false;
		}

		return isDefendPossible;
	}
/**
 * 
 * @return counterForPhases
 */
	public int getCounterForPhases() {
		return counterForPhases;
	}
/**
 * 
 * @param counterForPhases
 */
	public void setCounterForPhases(int counterForPhases) {
		this.counterForPhases = counterForPhases;
		notifyObserverForPhases();
	}
/**
 * @param addObserver
 */
	@Override
	public void registerPhaseObserver(Observer addObserver) {
		observersForPhases.add(addObserver);
	}

	@Override
	public void registerWorldDominationObserver(Observer addObserver) {
		observersForWorldDomination.add(addObserver);
	}

	@Override
	public void unregisterPhaseObserver(Observer removeObserver) {
		observersForPhases.remove(removeObserver);
	}

	@Override
	public void unregisterWorldDomination(Observer removeObserver) {
		observersForWorldDomination.remove(removeObserver);
	}

	@Override
	public void notifyObserverForWorldDomination() {
		// System.out.println("Ob No.="+observersForWorldDomination.size());
		for (Observer observer : observersForWorldDomination) {
			observer.update(percentageControlled, totalNumberOfArmies, continentsControlled, mapBuild);
		}
	}

	@Override
	public void notifyObserverForPhases() {
		for (Observer observer : observersForPhases) {
			observer.update(counterForPhases, playerName);
		}
	}

	public ArrayList<Observer> getObserversForPhases() {
		return observersForPhases;
	}

	public ArrayList<Observer> getObserversForWorldDomination() {
		return observersForWorldDomination;
	}
/**
 * This method calculate World Domination View
 */

	public void calculateWorldDominationView() {
		totalNumberOfArmies = 0;

		for (Integer each : countryIDs) {
			totalNumberOfArmies += mapBuild.getCountryById(each).getArmies();
		}

		mapBuild.continentsOwnedByPlayer(playerName);
		percentageControlled = getCountryIDs().length * 100 / mapBuild.getAllCountries().size();

		notifyObserverForWorldDomination();
	}

	/**
	 *  
	 * @return number Of Armies Each Player Gets
	 */
	public int getNumberOfArmiesEachPlayerGets() {
		return numberOfArmiesEachPlayerGets;
	}

	/**
	 * This method is for calculate Number Of Armies Each Player Gets
	 * 
	 * @param playerName
	 */

	public void calculateNumberOfArmiesEachPlayerGets() {
		numberOfArmiesEachPlayerGets = (getCountryIDs().length / 3 > 3) ? getCountryIDs().length / 3 : 3;
	}
/**
 * 
 * @param mapBuild
 * @param countryName
 * @param armiesAdded
 * @return false
 */
	public boolean reinforceIsValid(MapGeo mapBuild, String countryName, int armiesAdded) {
		calculateNumberOfArmiesEachPlayerGets();

		if (armiesAdded < 0 || armiesAdded > numberOfArmiesEachPlayerGets) {
			System.out.println("Armies Added (" + armiesAdded
					+ ") is less than 0 or Armies added are more than player armies " + numberOfArmiesEachPlayerGets);
			return false;
		}

		int[] playerCountries = getCountryIDs();

		for (int countryID : playerCountries) {
			if (mapBuild.getCountryByName(countryName) == mapBuild.getCountryById(countryID)) {
				return true;
			}
		}

		return false;
	}
/**
 * 
 * @param mapBuild
 * @param countryName
 * @param num
 * @param finished
 * @return
 */
	public boolean reinforce(MapGeo mapBuild, String countryName, int num, boolean finished) {
		return this.strategy.reinforce(mapBuild, countryName, num, finished);
	}
/**
 * 
 * @param mapBuild
 * @param mapView
 * @return
 */
	public boolean reinforceCommand(MapGeo mapBuild, MapView mapView) {
		calculateNumberOfArmiesEachPlayerGets();

		boolean finished = false;
		boolean isValidCommand = false;

		if (this.strategy instanceof HumanPlayer) {
			String addText = "";
			temporaryArmies = numberOfArmiesEachPlayerGets;

			while (!finished) {// && debug == false

				isValidCommand = false;

				System.out.println("Player " + getPlayerName() + ":");
				if (temporaryArmies != 0) {
					System.out.println("You have -" + temporaryArmies + "- armies left for reinforcement.");
				}
				// Card card=new Card();
				// player.addCard(card);
				System.out.println("You have following cards: ");
				System.out.println(getCardNames());
				readInput();

				// showmap
				regex = "showmap";
				setPattern(regex);
				setMatcher(input);
				if (getMatcher().find()) {
					isValidCommand = true;
					mapView.showMap(mapBuild);
				}

				// reinforce
				regex = "(?<=reinforce)(.*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					addText = matcher.group(1);

					regex = "(([\\w*\\_\\-]*) (\\d*))+";
					setPattern(regex);
					setMatcher(addText);
					if (matcher.find()) {
						String countryName = matcher.group(2);
						int num;
						try {
							num = Integer.parseInt(matcher.group(3));

							finished = reinforce(mapBuild, countryName, num, finished);

							isValidCommand = true;

						} catch (NumberFormatException e) {
							System.out.println("Enter number of armies");
						}

					}
				} // Match Find Reinforce
					//// __________________________________________///////
					// __________________________________________///////
					// __________________________________________///////
				regex = "(?<=exchangecards)(.*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					addText = matcher.group(1);
					regex = "(\\d+) (\\d+) (\\d+)";
					setPattern(regex);
					setMatcher(addText);
					if (matcher.find()) {

						int num1 = Integer.parseInt(matcher.group(1));
						int num2 = Integer.parseInt(matcher.group(2));
						int num3 = Integer.parseInt(matcher.group(3));

						if (mapBuild.exchangeCardsIsValid(this, num1, num2, num3) == true) {

							int cardarmies = mapBuild.exchangeCards(this, num1, num2, num3);
							finished = true;
						} else {
							System.out.println("exchangecards is not valid");
						}
					}

					isValidCommand = true;
				}
				if (!isValidCommand) {
					System.out.println("Correct command not found");
				}
			} // while reinforce
		}

		if (this.strategy instanceof AggressivePlayer) {

			int[] playerCountries = getCountryIDs();
			int maxArmies = 0;
			String countryName = "";

			for (int countryID : playerCountries) {
				if (mapBuild.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapBuild.getCountryById(countryID).getArmies();
					countryName = mapBuild.getCountryById(countryID).getCountryName();
				}
			}

			finished = reinforce(mapBuild, countryName, numberOfArmiesEachPlayerGets, true);
			isValidCommand = true;
		}

		if (this.strategy instanceof BenevolentPlayer) {

			int[] playerCountries = getCountryIDs();
			int minArmies = 999;
			String countryName = "";

			for (int countryID : playerCountries) {
				if (mapBuild.getCountryById(countryID).getArmies() < minArmies) {
					minArmies = mapBuild.getCountryById(countryID).getArmies();
					countryName = mapBuild.getCountryById(countryID).getCountryName();
				}
			}

			finished = reinforce(mapBuild, countryName, numberOfArmiesEachPlayerGets, true);
			isValidCommand = true;
		}

		if (this.strategy instanceof RandomPlayer) {
			Random random = new Random();
			int[] playerCountries = getCountryIDs();

			int randomCountryID = random.nextInt(playerCountries.length);
			String countryName = mapBuild.getCountryById(randomCountryID).getCountryName();

			finished = reinforce(mapBuild, countryName, numberOfArmiesEachPlayerGets, true);
			isValidCommand = true;
		}

		if (this.strategy instanceof CheaterPlayer) {

			int[] playerCountries = getCountryIDs();

			for (int countryID : playerCountries) {
				String countryName = mapBuild.getCountryById(countryID).getCountryName();
				int oldArmies = mapBuild.getCountryById(countryID).getArmies();
				finished = reinforce(mapBuild, countryName, oldArmies, true);
			}

			isValidCommand = true;
		}

		return isValidCommand;
	}

	public void attackMove(Country attackerCountry, Country attackingCountry, int numAttack) {
		System.out.println("attackmove : " + numAttack);
		attackerCountry.setArmies(attackerCountry.getArmies() - numAttack);
		attackingCountry.setArmies(numAttack);
		calculateWorldDominationView();

	}

	public void attackMoveCommand(Country attackerCountry, Country attackingCountry, int attackAllout) {
		boolean isValidCommand = false;
		// attackmove num
		System.out.println("Ready for attackmove");
		if (attackAllout == 0) {
			readInput();
			regex = "(?<=attackmove) (\\d+)";
			setPattern(regex);
			setMatcher(input);

			if (matcher.find()) {
				try {
					int numAttack = Integer.parseInt(matcher.group(1));
					if (numAttack > (attackerCountry.getArmies() - 1)) {
						System.out.println(numAttack + " can not move because you have only : "
								+ attackerCountry.getArmies() + " armies and one should left there");
					} else {
						isValidCommand = true;
						attackMove(attackerCountry, attackingCountry, numAttack);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.out.println("NumDice should be integer");
				}
				isValidCommand = true;

			} else {
				isValidCommand = false;
				System.out.println("Please follow the correct command rules");
			}
		} else {
			attackMove(attackerCountry, attackingCountry, 1);
		}

	}

	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice,
			MapGeo mapBuild, int attackAllout) {
		this.strategy.attack(attackerCountry, attackingCountry, attackerNumDice, defendNumDice, mapBuild, attackAllout);
	}

	public void attackAllout(MapGeo mapBuild) {
		System.out
				.println("\n------------------------------\nAttack All out Started\n------------------------------\n");
		boolean isConqueredNewCountry;
		do {
			isConqueredNewCountry = false;
			int[] playerCountries = getCountryIDs();
			int attackerNumDice = 1;
			int defendNumDice = 1;
			System.out.println("You own " + playerCountries.length + " countries ("+Arrays.toString(countryIDs)+"), let's attack");
			
			for (int i = 0; i < playerCountries.length; i++) {
				int countryId = playerCountries[i];
				Country attackerCountry = mapBuild.getCountryById(countryId);
				ArrayList<Integer> adjCountries = mapBuild.getCountryAdjacency(countryId);
				for (int attackingCountryId : adjCountries) {
					Country attackingCountry = mapBuild.getCountryById(attackingCountryId);

					System.out.println("\n-----------------------------------------------------");
					System.out.println(
							"Attack From " + attackerCountry.getCountryName() + "=" + attackerCountry.getArmies() + " ("
									+ getPlayerName() + ") To: " + attackingCountry.getCountryName() + "="
									+ attackingCountry.getArmies() + " (" + attackingCountry.getPlayerName() + ")");

					boolean thisCountryCanAttack = true;
					while (thisCountryCanAttack) {
						if (isAttackValid(mapBuild, attackerNumDice, attackerCountry, attackingCountry, true) == true) {
							attack(attackerCountry, attackingCountry, attackerNumDice, defendNumDice, mapBuild, 1);
							System.out.println(attackerCountry.getCountryName() + "=" + attackerCountry.getArmies()
									+ " -> " + attackingCountry.getCountryName() + "=" + attackingCountry.getArmies());
						} else {
							thisCountryCanAttack = false;
						}
					}

				} // For Adj Countries

			} // For player countries
			System.out.println("No. of Countries before this round attack="+playerCountries.length+" and after attack="+ getCountryIDs().length);
			System.out.println("You own now " + playerCountries.length + " countries ("+Arrays.toString(countryIDs)+")");
			if (playerCountries.length != getCountryIDs().length) {
				isConqueredNewCountry = true;
			}

		} while (isConqueredNewCountry == true);
		System.out.println(
				"\n------------------------------\nNo More attack is possible\n------------------------------\n");
	}

	public boolean attackCommand(MapGeo mapBuild, MapView mapView) {
		boolean finished = false;
		boolean isValidCommand = false;
		String addText = "";
		/*
		 ************************************************
		 ************* Attack **********************
		 ************************************************
		 ************************************************
		 */
		// attack
		// System.out.println(attackRequestingMessage);

		finished = false;
		while (!finished) {// && debug == false
			System.out.println(getPlayerName() + " you may attack or finish your turn");
			isValidCommand = false;
			// Check there is any available attack
			if (isAttackPossible(mapBuild) == false) {
				System.out.println("Attack is not possible.");
				isValidCommand = true;
				finished = true;
			}
			readInput();

			// showmap
			regex = "showmap";
			setPattern(regex);
			setMatcher(input);
			if (getMatcher().find()) {
				isValidCommand = true;
				mapView.showMap(mapBuild);
			}

			regex = "(?<=attack)(.*)";
			setPattern(regex);
			setMatcher(input);
			if (matcher.find()) {
				addText = matcher.group(1);
				regex = "(([\\w*\\_\\-]*) ([\\w*\\_\\-]*) ((\\d+)|(-allout)))|(-noattack)";// )+
				setPattern(regex);
				setMatcher(addText);
				if (matcher.find()) {

					// for (int j = 0; j <= matcher.groupCount(); j++) {
					// System.out.println("------------------------------------");
					// System.out.println("Group " + j + ": ***" + matcher.group(j)+"***");
					//
					// }

					if (matcher.group(7) != null && matcher.group(7).equals("-noattack")) {
						System.out.println("No attack selected");
						isValidCommand = true;
						finished = true;

					} else {
						int attackerNumDice = 0;
						String attackerCountryName = matcher.group(2);
						String attackingCountryName = matcher.group(3);

						if (matcher.group(6) != null && matcher.group(6).equals("-allout")) {
							System.out.println("Allout selected");
							attackAllout(mapBuild);
							attackerNumDice = 3;
						} else {
							attackerNumDice = Integer.parseInt(matcher.group(4));
						}

						// Country attacker=;
						Country attackerCountry = mapBuild.getCountryByName(attackerCountryName);
						Country attackingCountry = mapBuild.getCountryByName(attackingCountryName);
						// System.out.println("Armies in Attacking " + attackingCountryName + " is "+
						// attackingCountry.getArmies());

						System.out.println("Attack from " + attackerCountryName + " To " + attackingCountryName + " by "
								+ attackerNumDice + " dice");

						if (isAttackValid(mapBuild, attackerNumDice, attackerCountry, attackingCountry, true) == true) {

							/**** Start Defend *******/

							isValidCommand = true;
							// Show name of player of defend country and ask him/her to roll dice by DEFEND

							boolean finishedDefend = false;
							while (!finishedDefend) {
								// defend numdice
								readInput();
								regex = "(?<=defend) (\\d+)";
								setPattern(regex);
								setMatcher(input);

								if (matcher.find()) {
									try {
										int defendNumDice = Integer.parseInt(matcher.group(1));
										attack(attackerCountry, attackingCountry, attackerNumDice, defendNumDice,
												mapBuild, 0);

										// if(numDice>attackingCountry.getArmies() || numDice>3) {
										// System.out.println("defending dice should not be more than the number of
										// armies contained in the attacking country or more than 3");
										// isValidCommand = false;
										// }else {
										// isValidCommand = true;
										// System.out.println("Defend by: " + numDice);
										// }
										isValidCommand = true;
									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										System.out.println("NumDice should be integer");
									}
									finishedDefend = true;

								} else {
									isValidCommand = false;
									System.out.println("Please enter the defence command");
								}
							} // while(!finishedDefend) {

						}

						/*
						 * String allOut = matcher.group(5); String noAttack = matcher.group(6);
						 */
					}
				} else {
					isValidCommand = false;
				}

			}

			if (!isValidCommand) {
				System.out.println("Correct command not found");
			}

			/// end of the attack phase

		} // End of While(!finished)
		return isValidCommand;
	}

	/**
	 * This method is for checking whether the fortify is valid
	 * 
	 * @param player
	 * @param fromCountry
	 * @param toCountry
	 * @param num
	 * @return false
	 */
	public boolean fortifyIsValid(String fromCountry, String toCountry, int num, MapGeo mapBuild) {
		boolean fromCountryCheck = false;
		boolean toCountryCheck = false;

		if (num < 0 || num > mapBuild.getCountryByName(fromCountry).getArmies())
			return false;

		for (int countryID : getCountryIDs()) {
			if (mapBuild.getCountryByName(fromCountry) == mapBuild.getCountryById(countryID)) {
				fromCountryCheck = true;
			}
		}

		for (int countryID : getCountryIDs()) {
			if (mapBuild.getCountryByName(toCountry) == mapBuild.getCountryById(countryID)) {
				toCountryCheck = true;
			}
		}

		if (fromCountryCheck && toCountryCheck)
			return true;

		return false;
	}

	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapGeo mapBuild) {
		this.strategy.fortify(fromCountry, toCountry, armiesToMove, mapBuild);
	}

	/**
	 * 
	 * @param mapBuild
	 * @param mapView
	 * @return isValidCommand
	 */

	public boolean fortifyCommand(MapGeo mapBuild, MapView mapView) {
		System.out.println(fortifyRequestingMessage);

		boolean finished = false;
		boolean isValidCommand = false;

		if (this.strategy instanceof HumanPlayer) {

			String addText = "";

			while (!finished) {

				isValidCommand = false;

				System.out.println("Player " + getPlayerName() + ":");
				readInput();

				// showmap
				regex = "showmap";
				setPattern(regex);
				setMatcher(input);
				if (getMatcher().find()) {
					isValidCommand = true;
					mapView.showMap(mapBuild);
				}

				// fortify -none
				regex = "fortify -none";

				if (input.equalsIgnoreCase(regex)) {
					isValidCommand = true;
					finished = true;
				}

				// fortify
				regex = "(?<=fortify)(.*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					addText = matcher.group(1);
					regex = "(([\\w*\\_\\-]*) ([\\w*\\_\\-]*) (\\d+))+";
					setPattern(regex);
					setMatcher(addText);
					if (matcher.find()) {

						String fromCountry = matcher.group(2);
						String toCountry = matcher.group(3);
						int num = Integer.parseInt(matcher.group(4));
						fortify(fromCountry, toCountry, num, mapBuild);
						finished = true;

					}

					isValidCommand = true;
				}
			}
		}

		if (this.strategy instanceof AggressivePlayer) {
			int[] playerCountries = getCountryIDs();
			int maxArmies = 0;
			String toCountry = "";
			int toCountryID = 0;

			for (int countryID : playerCountries) {
				if (mapBuild.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapBuild.getCountryById(countryID).getArmies();
					toCountry = mapBuild.getCountryById(countryID).getCountryName();
					toCountryID = countryID;
				}
			}

			maxArmies = 0;
			String fromCountry = "";

			for (int countryID : mapBuild.getCountryAdjacency(toCountryID)) {
				if (mapBuild.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapBuild.getCountryById(countryID).getArmies();
					fromCountry = mapBuild.getCountryById(countryID).getCountryName();
				}
			}

			int numberOfArmiesToMove = mapBuild.getCountryByName(fromCountry).getArmies() - 1;

			fortify(fromCountry, toCountry, numberOfArmiesToMove, mapBuild);
		}

		if (this.strategy instanceof BenevolentPlayer) {

			int[] playerCountries = getCountryIDs();
			int maxArmies = 0;
			String fromCountry = "";
			int fromCountryID = 0;

			for (int countryID : playerCountries) {
				if (mapBuild.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapBuild.getCountryById(countryID).getArmies();
					fromCountry = mapBuild.getCountryById(countryID).getCountryName();
					fromCountryID = countryID;
				}
			}

			int minArmies = 999;
			String toCountry = "";

			for (int countryID : mapBuild.getCountryAdjacency(fromCountryID)) {
				if (mapBuild.getCountryById(countryID).getArmies() < minArmies) {
					minArmies = mapBuild.getCountryById(countryID).getArmies();
					toCountry = mapBuild.getCountryById(countryID).getCountryName();
				}
			}

			int numberOfArmiesToMove = mapBuild.getCountryByName(fromCountry).getArmies() / 2;

			fortify(fromCountry, toCountry, numberOfArmiesToMove, mapBuild);
		}

		if (this.strategy instanceof RandomPlayer) {
			Random random = new Random();
			int[] playerCountries = getCountryIDs();

			int randomFromCountryID = random.nextInt(playerCountries.length);
			String fromCountry = mapBuild.getCountryById(randomFromCountryID).getCountryName();

			int randomToCountryID = random.nextInt(mapBuild.getCountryAdjacency(randomFromCountryID).size());

			while (!(mapBuild.getCountryById(randomToCountryID).getArmies() > 1)) {
				randomToCountryID = random.nextInt(mapBuild.getCountryAdjacency(randomFromCountryID).size());
			}

			String toCountry = mapBuild.getCountryById(randomToCountryID).getCountryName();

			int numberOfArmiesToMove = random.nextInt(mapBuild.getCountryByName(fromCountry).getArmies());

			fortify(fromCountry, toCountry, numberOfArmiesToMove, mapBuild);
		}

		if (this.strategy instanceof CheaterPlayer) {
			int[] playerCountries = getCountryIDs();

			for (int countryID : playerCountries) {
				for (int neighborCountryID : mapBuild.getCountryAdjacency(countryID)) {
					if (mapBuild.getCountryById(neighborCountryID).getPlayerName() != mapBuild.getCountryById(countryID)
							.getPlayerName()) {
						fortify(mapBuild.getCountryById(countryID).getCountryName(), "", 0, mapBuild);
						break;
					}
				}
			}
		}

		return isValidCommand;
	}
	/**
	 * 
	 * @param countryId
	 */
	protected void addCountryIdToPlayer(int countryId) {
		System.out.println("Adding "+countryId+" to the list of "+Arrays.toString(countryIDs));
		int[] array = Arrays.copyOf(countryIDs, countryIDs.length + 1); //create new array from old array and allocate one more element
		array[array.length - 1] = countryId;
		countryIDs=array;
		System.out.println("New Array="+Arrays.toString(countryIDs));
		
	}
/**
 * 
 * @return temporaryArmies
 */
	public int getTemporaryArmies() {
		return temporaryArmies;
	}
/**
 * 
 * @param temporaryArmies
 */
	public void setTemporaryArmies(int temporaryArmies) {
		this.temporaryArmies = temporaryArmies;
	}

	/**
	 * This method is for reading the input from console
	 */
	public void readInput() {
		this.input = scanner.nextLine();
	}

	/**
	 * This method is for setting the pattern
	 * 
	 * @param regex
	 */
	public void setPattern(String regex) {
		this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}

	/**
	 * This method is for setting the matcher
	 * 
	 * @param input
	 */

	public void setMatcher(String input) {
		this.matcher = pattern.matcher(input);
	}

	/**
	 * This method is for fetching the matcher
	 * 
	 * @return Matcher
	 */
	public Matcher getMatcher() {
		return this.matcher;
	}

}
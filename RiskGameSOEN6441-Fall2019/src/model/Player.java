package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Game;
import controller.Game.Phase;
import view.MapView;

/**
 * 
 * This is Player class, in which we define our attributes of a Player.
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 */
public class Player implements Subject, Serializable {

	/**
	 * PlayerName is name of the player corresponding to the ID.
	 */
	protected String playerName;

	/**
	 * List of all countryIDs that the player owns.
	 */
	protected ArrayList<Integer> countryIDs;
	/**
	 * protected percentage Controlled 
	 */

	protected double percentageControlled = 0.00;
	/**
	 * protected continentsControlled
	 */
	protected ArrayList<String> continentsControlled = new ArrayList<String>();
	/**
	 * protected int total Number Of Armies
	 */
	protected int totalNumberOfArmies = 0;
	/**
	 * protected cards
	 */
	protected ArrayList<Card> cards;
	/**
	 * protected player Count For Card 
	 */
	protected int playerCountForCard;
	/**
	 * protected number Of Armies Each Player Gets
	 */
	protected int numberOfArmiesEachPlayerGets;
	/**
	 * protected allow To Get Card
	 */
	protected boolean allowToGetCard;
	/**
	 * protected counter For Phases
	 */

	protected int counterForPhases;
	/**
	 * protected temporary Armies
	 */
	protected int temporaryArmies;
	/**
	 * protected observers For Phases
	 */
	protected ArrayList<Observer> observersForPhases = new ArrayList<Observer>();
	/**
	 * protected observers For World Domination
	 */
	protected ArrayList<Observer> observersForWorldDomination = new ArrayList<Observer>();
	/**
	 * protected mapGeo
	 */
	protected MapGeo mapGeo;

	/**
	 * protected strategy
	 */

	protected Strategy strategy;
	
	protected boolean won = false;

	/**
	 * protected scanner
	 */
	protected transient Scanner scanner = new Scanner(System.in); 
	
	/**
	 * protected input
	 */
	protected String input;
	/**
	 * protected regex
	 */
	protected String regex;
	/**
	 * protected pattern
	 */
	protected Pattern pattern;
	/**
	 * protected matcher
	 */
	protected Matcher matcher;
	/**
	 * protected reinforceRequestingMessage
	 */
	protected String reinforceRequestingMessage = "Now, it's time to reinforce!\n";
	/**
	 * protected fortifyRequestingMessage
	 */
	protected String fortifyRequestingMessage = "Let's fortify! or type in \"fortify none\" if you don't want to.\n";

	protected String attackRequestingMessage = "Let's Attack\n";

	/**
	 * This is Player constructor method for initializing PlayerName and countryId
	 * 
	 * @param playerID   A unique ID for each player.
	 * @param playerName Corresponding player name.
	 * @param countryID  List of all countries (IDs) that the player owns.
	 */
	public Player(String playerName, ArrayList<Integer> countryID, MapGeo mapGeo) {
		this.playerName = playerName;
		this.countryIDs = countryID;
		this.mapGeo = mapGeo;
		cards = new ArrayList<Card>();
		playerCountForCard = 0;
	}
	/**
	 * protected set strategy
	 * @param strategy
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public Strategy getStrategy() {
		return strategy;
	}
	/**
	 * protected add One To Card Counter
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
	public  ArrayList<Integer> getCountryIDs() {
		return countryIDs;
	}

	/**
	 * This is Setter Method for CountryIDs.
	 * 
	 * @param countriesIDs Sets a list of countriesIDs to the corresponding field.
	 */
	public void setCountryIDs( ArrayList<Integer> countriesIDs) {
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
	 * @param mapGeo
	 * @param attackerNumDice
	 * @param attackerCountry
	 * @param attackingCountry
	 * @param enablePrint
	 * @return isValid
	 */
	public boolean isAttackValid(int attackerNumDice, Country attackerCountry,
			Country attackingCountry, boolean enablePrint) {
		
		boolean isValid = true;
		
		if (attackingCountry==null) {
			if (enablePrint)
				System.out.println("There is no attacking country");
			isValid = false;
		}else if (attackingCountry.getPlayerName().equals(getPlayerName())) {
			if (enablePrint)
				System.out.println("You can not attack to your own country");
			isValid = false;
		}else if (attackerCountry.getArmies() <= 1) {
			if (enablePrint)
				System.out.println("Attacker Country should have more than 1 army");
			isValid = false;
		}else if (attackerCountry == null || attackingCountry == null) {
			if (enablePrint)
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
			} else if (!mapGeo.isAdjacentCountry(attackerCountry.getCountryId(), attackingCountry.getCountryId())) {
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
	 * @param mapGeo
	 * @return isAttackPossible
	 */
	public boolean isAttackPossible() {
		boolean isAttackPossible = false;
		for (int countyId : countryIDs) {
			// Country country=mapGeo.getCountryById(countyId);
			// System.out.println("Checking " + countyId);
			ArrayList<Integer> CountryAdjList = mapGeo.getCountryAdjacency().getVertexAdjacency(countyId);
			// System.out.println(CountryAdjList); //Arrays.toString(

			for (int adjCountry : CountryAdjList) {
				if (!countryIDs.contains(adjCountry)) {
					for (int i = 1; i < 4; i++) {
						if (isAttackValid(i, mapGeo.getCountryById(countyId),
								mapGeo.getCountryById(adjCountry), false)) {
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
	public boolean isDefendPossible(int defendCountry, int numDice) {

		boolean isDefendPossible = true;
		if (getCountryIDs().contains(defendCountry)) {
			System.out.println(this.playerName+", Defend Country "+mapGeo.getCountryNameById(defendCountry)+" ("+defendCountry+") should be for other players"+getCountryIDs());
			isDefendPossible = false;
		}
		if (mapGeo.getCountryById(defendCountry).getArmies() <= numDice) {
			System.out.println("Army of Country"+mapGeo.getCountryNameById(defendCountry)+" should be more than number of dice "+numDice);
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
			observer.update(percentageControlled, totalNumberOfArmies, continentsControlled, mapGeo);
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
			totalNumberOfArmies += mapGeo.getCountryById(each).getArmies();
		}

		mapGeo.continentsOwnedByPlayer(playerName);
		percentageControlled = countryIDs.size() * 100 / mapGeo.getAllCountries().size();

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
		numberOfArmiesEachPlayerGets = (countryIDs.size() / 3 > 3) ? countryIDs.size() / 3 : 3;
	}
	
	public void setWon(boolean won) {
		this.won = won;
	}
	
	public boolean getWon() {
		return won;
	}
	
	/**
	 * 
	 * @param mapGeo
	 * @param countryName
	 * @param armiesAdded
	 * @return false
	 */
	public boolean reinforceIsValid(MapGeo mapGeo, String countryName, int armiesAdded) {
		calculateNumberOfArmiesEachPlayerGets();

		if (armiesAdded < 0 || armiesAdded > numberOfArmiesEachPlayerGets) {
			System.out.println("Armies Added (" + armiesAdded
					+ ") is less than 0 or Armies added are more than player armies " + numberOfArmiesEachPlayerGets);
			return false;
		}

		ArrayList<Integer> playerCountries = getCountryIDs();

		for (int countryID : playerCountries) {
			if (mapGeo.getCountryByName(countryName) == mapGeo.getCountryById(countryID)) {
				return true;
			}
		}

		return false;
	}
	/**
	 * 
	 * @param mapGeo
	 * @param countryName
	 * @param num
	 * @param finished
	 * @return
	 */
	public boolean reinforce(MapGeo mapGeo, String countryName, int num, boolean finished) {
		return this.strategy.reinforce(mapGeo, countryName, num, finished);
	}
	/**
	 * 
	 * @param mapGeo
	 * @param mapView
	 * @return
	 */
	public boolean reinforceCommand(Game game, MapGeo mapGeo, MapView mapView) {
		calculateNumberOfArmiesEachPlayerGets();

		boolean finished = false;
		boolean isValidCommand = false;

		if (this.strategy instanceof HumanPlayer) {
			temporaryArmies = numberOfArmiesEachPlayerGets;
			String addText = "";

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
					mapView.showMap(mapGeo);
				}
				
				// savegame filename
				regex = "savegame ([\\w*\\_\\-]*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
				String	mapFileName = matcher.group(1);
					isValidCommand = true;
					game.saveGame(mapFileName, this.mapGeo, this, Phase.REINFORCEMENT);
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

							finished = reinforce(mapGeo, countryName, num, finished);

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

						if (mapGeo.exchangeCardsIsValid(this, num1, num2, num3) == true) {

							int cardarmies = mapGeo.exchangeCards(this, num1, num2, num3);
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

			ArrayList<Integer> playerCountries = getCountryIDs();
			int maxArmies = 0;
			String countryName = "";

			for (int countryID : playerCountries) {
				if (mapGeo.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapGeo.getCountryById(countryID).getArmies();
					countryName = mapGeo.getCountryById(countryID).getCountryName();
				}
			}

			finished = reinforce(mapGeo, countryName, numberOfArmiesEachPlayerGets, true);
			isValidCommand = true;
		}

		if (this.strategy instanceof BenevolentPlayer) {

			ArrayList<Integer> playerCountries = countryIDs;
			int minArmies = 999;
			String countryName = "";

			for (int countryID : playerCountries) {
				if (mapGeo.getCountryById(countryID).getArmies() < minArmies) {
					minArmies = mapGeo.getCountryById(countryID).getArmies();
					countryName = mapGeo.getCountryById(countryID).getCountryName();
				}
			}

			finished = reinforce(mapGeo, countryName, numberOfArmiesEachPlayerGets, true);
			isValidCommand = true;
		}

		if (this.strategy instanceof RandomPlayer) {
			Random random = new Random();
			ArrayList<Integer> playerCountries = countryIDs;

			int randomCountryID = random.nextInt(playerCountries.size());
			String countryName = mapGeo.getCountryById(randomCountryID).getCountryName();

			finished = reinforce(mapGeo, countryName, numberOfArmiesEachPlayerGets, true);
			isValidCommand = true;
		}

		if (this.strategy instanceof CheaterPlayer) {

			ArrayList<Integer> playerCountries = countryIDs;

			for (int countryID : playerCountries) {
				String countryName = mapGeo.getCountryById(countryID).getCountryName();
				int oldArmies = mapGeo.getCountryById(countryID).getArmies();
				finished = reinforce(mapGeo, countryName, oldArmies, true);
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
			int attackAllout) {

		this.strategy.attack(attackerCountry, attackingCountry, attackerNumDice, defendNumDice, attackAllout);
	}

	public void attackAllout() {
		System.out
		.println("\n------------------------------\nAttack All out Started\n------------------------------\n");
		boolean isConqueredNewCountry;
		do {
			isConqueredNewCountry = false;
			//ArrayList<Integer> playerCountries = getCountryIDs();
			int attackerNumDice = 1;
			int defendNumDice = 1;

			for (int i = 0; i < countryIDs.size(); i++) {
				int countryId = countryIDs.get(i);
				Country attackerCountry = mapGeo.getCountryById(countryId);
				ArrayList<Integer> adjCountries = mapGeo.getCountryAdjacency(countryId);
				for (int attackingCountryId : adjCountries) {
					Country attackingCountry = mapGeo.getCountryById(attackingCountryId);

					System.out.println("\n-----------------------------------------------------");
					System.out.println(
							"Attack From " + attackerCountry.getCountryName() + "=" + attackerCountry.getArmies() + " ("
									+ getPlayerName() + ") To: " + attackingCountry.getCountryName() + "="
									+ attackingCountry.getArmies() + " (" + attackingCountry.getPlayerName() + ")"+this.countryIDs);

					boolean thisCountryCanAttack = true;
					while (thisCountryCanAttack) {
						if (isAttackValid(attackerNumDice, attackerCountry, attackingCountry, true) == true) {
							attack(attackerCountry, attackingCountry, attackerNumDice, defendNumDice, 1);
							//							System.out.println(attackerCountry.getCountryName() + "=" + attackerCountry.getArmies()
							//									+ " -> " + attackingCountry.getCountryName() + "=" + attackingCountry.getArmies());

						} else {
							thisCountryCanAttack = false;
						}
					}

				} // For Adj Countries

			} // For player countries
			System.out.println("No. of Countries before this round attack="+countryIDs.size()+" and after attack="+ countryIDs.size());
			System.out.println("You own now " + countryIDs.size() + " countries");
			if (countryIDs.size() != getCountryIDs().size()) {
				isConqueredNewCountry = true;
			}

		} while (isConqueredNewCountry == true);
		System.out.println(
				"\n------------------------------\nNo More attack is possible\n------------------------------\n");
	}

	public boolean attackCommand(Game game, MapView mapView) {
		boolean finished = false;
		boolean isValidCommand = false;
		String addText = "";

		if(this.strategy instanceof HumanPlayer) {
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
				if (isAttackPossible() == false) {
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
					mapView.showMap(mapGeo);
				}

				// savegame filename
				regex = "savegame ([\\w*\\_\\-]*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
				String	mapFileName = matcher.group(1);
					isValidCommand = true;
					game.saveGame(mapFileName, this.mapGeo, this, Phase.ATTACK);
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
								attackAllout();
								attackerNumDice = 3;
							} else {
								attackerNumDice = Integer.parseInt(matcher.group(4));
							}

							// Country attacker=;
							Country attackerCountry = mapGeo.getCountryByName(attackerCountryName);
							Country attackingCountry = mapGeo.getCountryByName(attackingCountryName);
							// System.out.println("Armies in Attacking " + attackingCountryName + " is "+
							// attackingCountry.getArmies());

							System.out.println("Attack from " + attackerCountryName + " To " + attackingCountryName + " by "
									+ attackerNumDice + " dice");

							if (isAttackValid(attackerNumDice, attackerCountry, attackingCountry, true) == true) {

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
													0);

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
			}
				
			if (!isValidCommand) {
				System.out.println("Correct command not found");
			}

			/// end of the attack phase

		} // End of While(!finished)
		
		if(this.strategy instanceof AggressivePlayer) {		
			
			ArrayList<Integer> playerCountries = getCountryIDs();
			int maxArmies = 0;
			Country attackerCountry = null;
			int attackerCountryID = 0;
			
			Country attackingCountry = null;
			
			int attackerNumDice = 0;
			int defendNumDice = 0;

			for (int countryID : playerCountries) {
				if (mapGeo.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapGeo.getCountryById(countryID).getArmies();
					attackerCountry = mapGeo.getCountryById(countryID);
					attackerCountryID = countryID;
					attackerNumDice = attackerCountry.getArmies();
				}
			}
			
			
			
			for (int countryID : mapGeo.getCountryAdjacency(attackerCountryID)) {
				if (!mapGeo.getCountryById(countryID).getPlayerName().equalsIgnoreCase(mapGeo.getCountryById(attackerCountryID).getPlayerName()) ) {
					attackingCountry = mapGeo.getCountryById(countryID);
					defendNumDice = attackingCountry.getArmies();
					break;
				}
			}
			if (isAttackValid(attackerNumDice, attackerCountry, attackingCountry, true) == true) {
			attack(attackerCountry, attackingCountry, attackerNumDice, defendNumDice, 0);
			}
		}
		
		if(this.strategy instanceof BenevolentPlayer) {

		}
		
		if(this.strategy instanceof RandomPlayer) {
			
			Random random = new Random();
			ArrayList<Integer> playerCountries = countryIDs;

			int randomAttackerCountryID = random.nextInt(playerCountries.size());
			Country attackerCountry = mapGeo.getCountryById(randomAttackerCountryID);

			int randomAttackingCountryID = random.nextInt(mapGeo.getCountryAdjacency(randomAttackerCountryID).size());

			while (mapGeo.getCountryById(randomAttackingCountryID).getPlayerName().equalsIgnoreCase(mapGeo.getCountryById(randomAttackerCountryID).getPlayerName())) {
				randomAttackingCountryID = random.nextInt(mapGeo.getCountryAdjacency(randomAttackerCountryID).size());
			}

			Country attackingCountry = mapGeo.getCountryById(randomAttackingCountryID);

			int numberOfArmiesToAttack = random.nextInt(attackerCountry.getArmies());
			int numberOfArmiesToDefend = random.nextInt(attackingCountry.getArmies());
			
			attack(attackerCountry, attackingCountry, numberOfArmiesToAttack, numberOfArmiesToDefend, 0);	
		}
		
		if(this.strategy instanceof CheaterPlayer) {
			
			ArrayList<Integer> playerCountries = countryIDs;

			for (int countryID : playerCountries) {
				for (int neighborCountryID : mapGeo.getCountryAdjacency(countryID)) {
					if (mapGeo.getCountryById(neighborCountryID).getPlayerName() != mapGeo.getCountryById(countryID)
							.getPlayerName()) {
						attack(mapGeo.getCountryById(countryID), mapGeo.getCountryById(neighborCountryID), 0, 0, 0);
						break;
					}
				}
			}
			
		}
		
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
	public boolean fortifyIsValid(String fromCountry, String toCountry, int num, MapGeo mapGeo) {
		boolean fromCountryCheck = false;
		boolean toCountryCheck = false;

		if (num < 0 || num > mapGeo.getCountryByName(fromCountry).getArmies())
			return false;

		for (int countryID : countryIDs) {
			if (mapGeo.getCountryByName(fromCountry) == mapGeo.getCountryById(countryID)) {
				fromCountryCheck = true;
			}
		}

		for (int countryID : countryIDs) {
			if (mapGeo.getCountryByName(toCountry) == mapGeo.getCountryById(countryID)) {
				toCountryCheck = true;
			}
		}

		if (fromCountryCheck && toCountryCheck)
			return true;

		return false;
	}

	public void fortify(String fromCountry, String toCountry, int armiesToMove, MapGeo mapGeo) {
		this.strategy.fortify(fromCountry, toCountry, armiesToMove, mapGeo);
	}

	/**
	 * 
	 * @param mapGeo
	 * @param mapView
	 * @return isValidCommand
	 */

	public boolean fortifyCommand(Game game, MapGeo mapGeo, MapView mapView) {
		
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
					mapView.showMap(mapGeo);
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
						fortify(fromCountry, toCountry, num, mapGeo);
						finished = true;

					}

					isValidCommand = true;
				}
			}
		}

		if (this.strategy instanceof AggressivePlayer) {
			ArrayList<Integer> playerCountries = countryIDs;
			int maxArmies = 0;
			String toCountry = "";
			int toCountryID = 0;

			for (int countryID : playerCountries) {
				if (mapGeo.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapGeo.getCountryById(countryID).getArmies();
					toCountry = mapGeo.getCountryById(countryID).getCountryName();
					toCountryID = countryID;
				}
			}

			maxArmies = 0;
			String fromCountry = "";

			for (int countryID : mapGeo.getCountryAdjacency(toCountryID)) {
				if (mapGeo.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapGeo.getCountryById(countryID).getArmies();
					fromCountry = mapGeo.getCountryById(countryID).getCountryName();
				}
			}

			int numberOfArmiesToMove = mapGeo.getCountryByName(fromCountry).getArmies() - 1;

			fortify(fromCountry, toCountry, numberOfArmiesToMove, mapGeo);
		}

		if (this.strategy instanceof BenevolentPlayer) {

			ArrayList<Integer> playerCountries = countryIDs;
			int maxArmies = 0;
			String fromCountry = "";
			int fromCountryID = 0;

			for (int countryID : playerCountries) {
				if (mapGeo.getCountryById(countryID).getArmies() > maxArmies) {
					maxArmies = mapGeo.getCountryById(countryID).getArmies();
					fromCountry = mapGeo.getCountryById(countryID).getCountryName();
					fromCountryID = countryID;
				}
			}

			int minArmies = 999;
			String toCountry = "";

			for (int countryID : mapGeo.getCountryAdjacency(fromCountryID)) {
				if (mapGeo.getCountryById(countryID).getArmies() < minArmies) {
					minArmies = mapGeo.getCountryById(countryID).getArmies();
					toCountry = mapGeo.getCountryById(countryID).getCountryName();
				}
			}

			int numberOfArmiesToMove = mapGeo.getCountryByName(fromCountry).getArmies() / 2;

			fortify(fromCountry, toCountry, numberOfArmiesToMove, mapGeo);
		}

		if (this.strategy instanceof RandomPlayer) {
			Random random = new Random();
			ArrayList<Integer> playerCountries = countryIDs;

			int randomFromCountryID = random.nextInt(playerCountries.size());
			String fromCountry = mapGeo.getCountryById(randomFromCountryID).getCountryName();

			int randomToCountryID = random.nextInt(mapGeo.getCountryAdjacency(randomFromCountryID).size());

			while (!(mapGeo.getCountryById(randomToCountryID).getArmies() > 1)) {
				randomToCountryID = random.nextInt(mapGeo.getCountryAdjacency(randomFromCountryID).size());
			}

			String toCountry = mapGeo.getCountryById(randomToCountryID).getCountryName();

			int numberOfArmiesToMove = random.nextInt(mapGeo.getCountryByName(fromCountry).getArmies());

			fortify(fromCountry, toCountry, numberOfArmiesToMove, mapGeo);
		}

		if (this.strategy instanceof CheaterPlayer) {
			ArrayList<Integer> playerCountries = countryIDs;

			for (int countryID : playerCountries) {
				for (int neighborCountryID : mapGeo.getCountryAdjacency(countryID)) {
					if (mapGeo.getCountryById(neighborCountryID).getPlayerName() != mapGeo.getCountryById(countryID)
							.getPlayerName()) {
						fortify(mapGeo.getCountryById(countryID).getCountryName(), "", 0, mapGeo);
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
		//		System.out.println("Adding "+countryId+" to the list of "+Arrays.toString(countryIDs.toArray()));

		countryIDs.add(countryId);
		//		System.out.println("New Array="+Arrays.toString(countryIDs.toArray()));

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
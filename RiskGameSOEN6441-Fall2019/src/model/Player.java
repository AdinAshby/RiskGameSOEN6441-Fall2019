package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import view.MapView;

/**
 * 
 * This is Player class, in which we define our attributes of a Player.
 * 
 * @author f_yazdan
 * 
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

	private double percentageControlled = 0.00;
	
	private String[] continentsControlled= new String[50];
	private int totalNumberOfArmies = 0;
	private ArrayList<Card> cards;
	private int playerCountForCard;

	private boolean allowToGetCard;

	private int counterForPhases;

	private ArrayList<Observer> observersForPhases = new ArrayList<Observer>();
	private ArrayList<Observer> observersForWorldDomination = new ArrayList<Observer>();


	/**
	 * private scanner
	 */
	private Scanner scanner= new Scanner(System.in);
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
	public Player(String playerName, int[] countryID) {
		this.playerName = playerName;
		this.countryIDs = countryID;
		cards = new ArrayList<Card>();
		playerCountForCard = 0;
	}

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

	public int getplayerCountForCard() {

		return playerCountForCard;

	}

	public String getCardNames() {
		String cardsList="Cards: -";
		for(Card c:cards) {
			cardsList=c.getCardType();
		}
		return cardsList;

	}

	public ArrayList<Card> getCards() {

		return cards;

	}

	public void setCards(ArrayList<Card> cardList) {

		for (int i = 0; i < cardList.size(); i++) {

			cardList.remove(i);

		}

		for (Card cardItem : cardList) {

			this.cards.add(cardItem);

		}

	}

	public void addCard(Card cardToAdd) {

		this.cards.add(cardToAdd);

	}

	public boolean isMoreThanFive() {

		if (cards.size() >= 5)

			return true;

		else

			return false;

	}

	public boolean getAllowingCardStatus() {

		return this.allowToGetCard;

	}

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

	public double getPercentageControlled() {
		return percentageControlled;
	}

	public String[] getContinentsControlled() {
		return continentsControlled;
	}

	public int getTotalNumberOfArmies() {
		return totalNumberOfArmies;
	}

	public boolean isAttackValid(MapBuilder mapBuild, int attackerNumDice, Country attackerCountry,
			Country attackingCountry, boolean enablePrint) {
		boolean isValid = true;

		if(attackerCountry==null || attackingCountry==null) {
			System.out.println("Enter Attacker and Attacking Country");
			isValid = false;
		}else {

			if (attackerNumDice > attackingCountry.getArmies()) {
				if(enablePrint)
					System.out.println(
							"attacking dice ("+attackerNumDice+") should not be more than the number of armies contained in the attacking country");
				isValid = false;
			}else if (attackerNumDice > 3) {
				if(enablePrint)
					System.out.println(
							"attacking dice ("+attackerNumDice+") should not be more than 3");
				isValid = false;
			} else if (!mapBuild.isAdjacentCountry(attackerCountry.getCountryId(), attackingCountry.getCountryId())) {
				if(enablePrint)
					System.out.println("Countries are not adjacent");
				isValid = false;

				/// Country should be owned and attacking country for opponent
			}
		}
		return isValid;
	}

	public boolean isAttackPossible(MapBuilder mapBuild) {
		boolean isAttackPossible = false;
		for (int countyId : countryIDs) {
			// Country country=mapBuild.getCountryById(countyId);
			//			System.out.println("Checking " + countyId);
			ArrayList<Integer> CountryAdjList = mapBuild.getCountryAdjacency().getVertexAdjacency(countyId);
			//	System.out.println(CountryAdjList); //Arrays.toString(

			for (int adjCountry : CountryAdjList) {
				if (!contains(countryIDs, adjCountry)) {
					for(int i=1;i<4;i++) {
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

	public int getCounterForPhases() {
		return counterForPhases;
	}

	public void setCounterForPhases(int counterForPhases) {
		this.counterForPhases = counterForPhases;
		notifyObserverForPhases();
	}

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
		//System.out.println("Ob No.="+observersForWorldDomination.size());
		for(Observer observer : observersForWorldDomination) {
			observer.update(percentageControlled, totalNumberOfArmies, continentsControlled);
		}
	}

	@Override
	public void notifyObserverForPhases() {
		for(Observer observer : observersForPhases) {
			observer.update(counterForPhases, playerName);
		}
	}

	public ArrayList<Observer> getObserversForPhases() {
		return observersForPhases;
	}

	public ArrayList<Observer> getObserversForWorldDomination() {
		return observersForWorldDomination;
	}

	/*public void calculateTotalNumberOfArmies() {
		for(Integer each : countryIDs) {
			totalNumberOfArmies += MapBuilder.getInstance().getCountryById(each).getArmies();
		}
		notifyObserverForWorldDomination();
	}

	public void calculateContinentControlled() {
		MapBuilder.getInstance().continentsOwnedByPlayer(playerName);
		notifyObserverForWorldDomination();
	}

	public void calculatePercentageControlled() {
		percentageControlled = getCountryIDs().length * 100 / MapBuilder.getInstance().getAllCountries().size();
		notifyObserverForWorldDomination();
	} */

	public void calculateWorldDominationView() {
		totalNumberOfArmies = 0;
		
		for(Integer each : countryIDs) {
			totalNumberOfArmies += MapBuilder.getInstance().getCountryById(each).getArmies();
		}

		MapBuilder.getInstance().continentsOwnedByPlayer(playerName);
		percentageControlled = getCountryIDs().length * 100 / MapBuilder.getInstance().getAllCountries().size();

		notifyObserverForWorldDomination();
	}

	public boolean reinforce(int temporaryArmies, MapBuilder mapBuild, MapView mapView) {
		boolean	finished = false;
		boolean isValidCommand=false;
		String addText="";
		while (!finished) {// && debug == false

			isValidCommand = false;

			System.out.println("Player " + getPlayerName() + ":");
			if (temporaryArmies != 0) {
				System.out.println("You have -" + temporaryArmies + "- armies left for reinforcement.");
			}
			//Card card=new Card();
			//player.addCard(card);
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

						if (mapBuild.reinforceIsValid(getPlayerName(), countryName, num) == true) {
							mapBuild.reinforce(getPlayerName(), countryName, num);
							calculateWorldDominationView();
							temporaryArmies -= num;
							
							if (temporaryArmies <= 0) {
								finished = true;
							}

						} else {
							System.out.println("Reinforce is not valid");
						}

						isValidCommand = true;

					} catch (NumberFormatException e) {
						System.out.println("Enter number of armies");
					}

				}
			} // Match Find Reinforce

			
			if (!isValidCommand) {
				System.out.println("Correct command not found");
			}
		} // while reinforce
		return isValidCommand;
	}

	public boolean attack(MapBuilder mapBuild, MapView mapView) {	
		boolean	finished = false;
		boolean isValidCommand=false;
		String addText="";
		/*
		 ************************************************
		 ************* Attack **********************
		 ************************************************
		 ************************************************
		  */
		// attack
		//			System.out.println(attackRequestingMessage);
		
		finished = false;
		while (!finished) {// && debug == false
			System.out.println(getPlayerName() + " you may attack or fortify or finish your turn");
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

					//							  for (int j = 0; j <= matcher.groupCount(); j++) {
					//							  System.out.println("------------------------------------");
					//							  System.out.println("Group " + j + ": ***" + matcher.group(j)+"***");
					//							  
					//							  }

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
							attackerNumDice = 3;
						} else {
							attackerNumDice = Integer.parseInt(matcher.group(4));
						}

						System.out.println("Attack from " + attackerCountryName + " To " + attackingCountryName + " by "
								+ attackerNumDice+ " dice");
						//Country attacker=;
						Country attackerCountry = mapBuild.getCountryByName(attackerCountryName);
						Country attackingCountry = mapBuild.getCountryByName(attackingCountryName);
						//System.out.println("Armies in Attacking " + attackingCountryName + " is "+ attackingCountry.getArmies());



						if (isAttackValid(mapBuild, attackerNumDice, attackerCountry, attackingCountry,
								true) == true) {

							Dice attackDice = new Dice(attackerNumDice);
							int[] attackDiceArray = attackDice.getDiceArray();
							attackDice.showDice();

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
										Dice defendDice = new Dice(defendNumDice);
										int[] defendDiceArray = defendDice.getDiceArray();
										defendDice.showDice();

										boolean[] winner = attackDice.isWinner(defendDice);
										for (int i = 0; i < winner.length; i++) {
											int diceNo=i+1;
											if (winner[i]) {
												System.out.println("Attacker win dice " + diceNo);
												attackingCountry.setArmies(attackingCountry.getArmies() - 1);
												calculateWorldDominationView();
												if (attackingCountry.getArmies() == 0) {
													System.out.println(attackingCountry.getCountryName()
															+ " is conquered");
													Card card=new Card();
													addCard(card);
													System.out.println("You have the following cards now :");
													System.out.println(getCardNames());
													attackingCountry.setPlayer(attackerCountry.getPlayerName());
													int NoOfContinentsControlled = getContinentsControlled().length;
													if (NoOfContinentsControlled == mapBuild
															.getNoOfContinentsControlled()) {
														System.out.println(attackerCountry.getPlayerName()
																+ " is winner. Game over!");
														System.exit(0);
													}

													// attackmove num
													System.out.println("Ready for attackmove");
													readInput();
													regex = "(?<=attackmove) (\\d+)";
													setPattern(regex);
													setMatcher(input);

													if (matcher.find()) {
														try {
															int numAttack = Integer.parseInt(matcher.group(1));
															if (numAttack < attackerCountry.getArmies() - 1) {
																System.out.println(numAttack
																		+ " can not move because you have only : "
																		+ attackerCountry.getArmies()
																		+ " armies and one should left there");
															} else {
																isValidCommand = true;
																System.out.println("attackmove : " + numAttack);
																attackerCountry
																.setArmies(attackerCountry.getArmies()
																		- numAttack);
																attackingCountry.setArmies(numAttack);
																calculateWorldDominationView();

															}
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															System.out.println("NumDice should be integer");
														}
														isValidCommand = true;

													} else {
														isValidCommand = false;
													}

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

										//								if(numDice>attackingCountry.getArmies() || numDice>3) {
										//									System.out.println("defending dice should not be more than the number of armies contained in the attacking country or more than 3");
										//									isValidCommand = false;
										//								}else {
										//								isValidCommand = true;
										//									System.out.println("Defend by: " + numDice);
										//								}
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
	 * 
	 * @param mapBuild
	 * @param mapView
	 * @return
	 */

	public boolean fortify(MapBuilder mapBuild, MapView mapView) {
		System.out.println(fortifyRequestingMessage);

		boolean finished = false;
		boolean isValidCommand = false;
		String addText="";

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

			// fortify none
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

					if (mapBuild.fortifyIsValid(this, fromCountry, toCountry, num) == true) {
						mapBuild.fortify(fromCountry, toCountry, num);
						calculateWorldDominationView();
						finished = true;
					} else {
						System.out.println("Fortify is not valid");
					}

					isValidCommand = true;
				}
			}


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

						int cardarmies=mapBuild.exchangeCards(this, num1, num2,num3);
						finished = true;
					} else {
						System.out.println("exchangecards is not valid");
					}
				}

				isValidCommand = true;
			}
		}
		return isValidCommand;
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
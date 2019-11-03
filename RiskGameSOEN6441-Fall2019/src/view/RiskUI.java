package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Country;
import model.Dice;
import model.MapBuilder;
import model.Player;

/**
 * This is a Risk UI class
 * 
 * 
 *
 */

public class RiskUI {
	/**
	 * private mapBuild
	 */

	private MapBuilder mapBuild = new MapBuilder();
	/**
	 * private mapView
	 */

	private MapView mapView = new MapView();
	/**
	 * private welcomeMessage
	 */

	private String welcomeMessage = "\t\t*****Risk Game*****";
	/**
	 * private editMapYesOrNoMessage
	 */

	private String editMapYesOrNoMessage = "Do you want to create/edit map? (Y/N)\n";
	/**
	 * private editMapRequestingMessage
	 */

	private String editMapRequestingMessage = "Enter corresponding commands for creating/editing a map.\n"
			+ "Whenever you are happy with the result, enter \"finishediting\".\n";
	/**
	 * private loadMapRequestingMessage
	 */

	private String loadMapRequestingMessage = "Load the map you with to play by using \"loadmap\" command:\n";
	/**
	 * private addOrRemovePlayersRequestingMessage
	 */
	private String addOrRemovePlayersRequestingMessage = "Add/remove players and at the end, enter \"populatecountries\":\n";
	/**
	 * private startupRequestingMessage
	 */
	private String startupRequestingMessage = "Place your armies on your selected country or use \"placeall\" command:\n";
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
	 * private scanner
	 */
	private Scanner scanner;
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
	 * private playerNames
	 */
	private ArrayList<String> playerNames = new ArrayList<String>();

	/**
	 * This is RiskUI constructor
	 */
	public RiskUI() {
		scanner = new Scanner(System.in);
	}

	/**
	 * This method is to start the game
	 * 
	 * @throws Exception
	 */
	public void RiskUIStartTheGame() throws Exception {

		boolean isValidCommand = false;
		String mapFileName = null;

		String editMapAnswer;

		boolean finished = false;
		boolean placeAllFlag = false;

		System.out.println(welcomeMessage);
		System.out.println(editMapYesOrNoMessage);

		editMapAnswer = scanner.nextLine();
		String addText = "";

		boolean debug = true;
		if (debug == true) {
			mapBuild.loadMap("test");// ameroki
			playerNames.add("Aval");
			playerNames.add("Dovom");

			mapBuild.assigningPlayersToCountries(playerNames);
			mapBuild.placeAllArmies();
			mapBuild.showMap();
			editMapAnswer = "N";
		}

		if (editMapAnswer.equalsIgnoreCase("Y")) {
			while (!finished) {
				System.out.println(editMapRequestingMessage);

				isValidCommand = false;
				readInput();

				// editmap filename
				regex = "editmap ([\\w*\\_\\-]*)";
				setPattern(regex);
				setMatcher(input);

				if (getMatcher().find()) {
					mapFileName = getMatcher().group(1);
					try {
						mapBuild.loadMap(mapFileName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					isValidCommand = true;
				}

				// added multiple time editcontinent -add aa 1 -add bb 2
				// add continent done
				regex = "(?<=editcontinent)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-add ([\\w*\\_\\-]*) (\\d*))+";
				setPattern(regex);
				setMatcher(addText);
				while (matcher.find()) {
					String continentName = matcher.group(2);
					int continentValue = Integer.parseInt(matcher.group(3));
					mapBuild.addContinent(continentName, continentValue);
					isValidCommand = true;

				}

				// remove continent done
				regex = "(?<=editcontinent)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-remove ([\\w*\\_\\-]*))+";
				setPattern(regex);
				setMatcher(addText);
				while (matcher.find()) {
					String continentName = matcher.group(2);
					mapBuild.removeContinent(continentName);
					isValidCommand = true;

				}

				// add country done
				regex = "(?<=editcountry)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-add ([\\w*\\_\\-]*) ([\\w*\\_\\-]*))+";
				setPattern(regex);
				setMatcher(addText);
				while (matcher.find()) {
					String countryName = matcher.group(2);
					String continentName = matcher.group(3);
					mapBuild.addCountry(countryName, continentName);
					isValidCommand = true;

				}

				// remove country done
				regex = "(?<=editcountry)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-remove ([\\w*\\_\\-]*))+";
				pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(addText);
				while (matcher.find()) {
					String countryName = matcher.group(2);
					mapBuild.removeCountry(countryName);
					isValidCommand = true;

				}

				// add neighbor done
				regex = "(?<=editneighbor)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-add ([\\w*\\_\\-]*) ([\\w*\\_\\-]*))+";
				setPattern(regex);
				setMatcher(addText);
				while (matcher.find()) {
					String countryName = matcher.group(2);
					String neighborCountryName = matcher.group(3);
					mapBuild.addCountryAdjacency(countryName, neighborCountryName);
					isValidCommand = true;

				}

				// remove neighbor done

				regex = "(?<=editneighbor)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-remove ([\\w*\\_\\-]*) ([\\w*\\_\\-]*))+";
				setPattern(regex);
				setMatcher(addText);
				while (matcher.find()) {
					String countryName = matcher.group(2);
					String neighborCountryName = matcher.group(3);
					mapBuild.removeCountryAdjacency(countryName, neighborCountryName);
					isValidCommand = true;

				}

				// showmap done
				regex = "showmap";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					mapBuild.showMap();
					isValidCommand = true;
				}

				// savemap filename done
				regex = "savemap ([\\w*\\_\\-]*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					mapFileName = matcher.group(1);
					isValidCommand = true;

					try {
						mapBuild.saveMap(mapFileName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// validatemap done
				regex = "validatemap";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					isValidCommand = true;
					mapBuild.validateMap();

				}

				// showadjacencymap countryname
				regex = "showadjacencymap ([\\w*\\_\\-]*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					mapFileName = matcher.group(1);
					// call adjacency
					isValidCommand = true;
				}

				// finishediting
				regex = "finishediting";
				if (input.equalsIgnoreCase(regex)) {
					finished = true;
					isValidCommand = true;
				}

				if (!isValidCommand) {
					System.out.println("Correct command not found");
				}
			}
		} else if (editMapAnswer.equalsIgnoreCase("N")) {
			System.out.println(loadMapRequestingMessage);
			finished = false;

			while (!finished && debug == false) {
				isValidCommand = false;
				readInput();

				// loadmap filename done
				regex = "loadmap ([\\w*\\_\\-]*)";
				setPattern(regex);
				setMatcher(input);

				if (matcher.find()) {
					mapFileName = matcher.group(1);
					isValidCommand = true;
					boolean isLoaded = false;
					try {
						isLoaded = mapBuild.loadMap(mapFileName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (isLoaded == true) {
						finished = true;
					}

				}

				if (!isValidCommand) {
					System.out.println("Correct command not found");
				}
			}

			System.out.println(addOrRemovePlayersRequestingMessage);
			finished = false;

			while (!finished && debug == false) {

				isValidCommand = false;
				readInput();
				// savemap filename done
				regex = "savemap ([\\w*\\_\\-]*)";
				setPattern(regex);
				setMatcher(input);
				if (matcher.find()) {
					mapFileName = matcher.group(1);
					isValidCommand = true;

					try {
						mapBuild.saveMap(mapFileName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				// gameplayer -add
				regex = "(?<=gameplayer)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-add ([\\w*\\_\\-]*))+";
				setPattern(regex);
				setMatcher(addText);
				while (matcher.find()) {
					String playerName = getMatcher().group(2);
					playerNames.add(playerName);
					isValidCommand = true;
				}

				// gameplayer -remove
				regex = "(?<=gameplayer)(.*)";
				setPattern(regex);
				setMatcher(input);
				addText = "";
				if (matcher.find()) {
					addText = matcher.group(1);
				}
				regex = "(-remove ([\\w*\\_\\-]*))+";
				setPattern(regex);
				setMatcher(addText);
				while (matcher.find()) {
					String playerName = getMatcher().group(2);
					playerNames.remove(playerName);
					isValidCommand = true;
				}

				// populatecountries
				regex = "populatecountries";
				setPattern(regex);
				setMatcher(input);
				if (getMatcher().find()) {
					isValidCommand = true;
					finished = true;
					mapBuild.assigningPlayersToCountries(playerNames);

				}

				// showmap
				regex = "showmap";
				setPattern(regex);
				setMatcher(input);
				if (getMatcher().find()) {
					isValidCommand = true;
					mapView.showMap(mapBuild);

				}

				if (!isValidCommand) {
					System.out.println("Correct command not found");
				}
			}

			System.out.println(startupRequestingMessage);
			finished = false;

			for (Player player : mapBuild.getPlayers()) {
				mapBuild.calculateNumberOfArmiesEachPlayerGets(player.getPlayerName());

				finished = false;

				while (!finished && debug == false) {

					isValidCommand = false;

					System.out.println("Player " + player.getPlayerName() + ":");
					System.out.println("You get -" + mapBuild.getNumberOfArmiesEachPlayerGets() + "- armies.");
					readInput();

					// placeall
					regex = "placeall";
					setPattern(regex);
					setMatcher(input);
					if (matcher.find()) {
						isValidCommand = true;
						mapBuild.placeAllArmies();
						finished = true;
						placeAllFlag = true;

					}

					// showmap
					regex = "showmap";
					setPattern(regex);
					setMatcher(input);
					if (getMatcher().find()) {
						isValidCommand = true;
						mapView.showMap(mapBuild);
					}

					// placearmy countryname

					regex = "(?<=placearmy )(.*)";
					setPattern(regex);
					setMatcher(input);
					addText = "";

					if (getMatcher().find()) {
						String countryName = matcher.group(1);

						if (mapBuild.placearmyIsValid(player, countryName) == true) {
							mapBuild.assignInitialsArmiesToSpecificCountry(countryName,
									mapBuild.getNumberOfArmiesEachPlayerGets());
							finished = true;
						} else {
							System.out.println("placearmy is not valid");
						}

						isValidCommand = true;
					}

					if (!isValidCommand) {
						System.out.println("Correct command not found");
					}
				}

				if (placeAllFlag == true) {
					break;
				}

			}

			/******** START GAME PHASE **********************/

			// System.out.println(reinforceRequestingMessage);

			for (Player player : mapBuild.getPlayers()) {
				mapBuild.calculateNumberOfArmiesEachPlayerGets(player.getPlayerName());
				System.out.println(player.getPlayerName() + " is your turn to reinforce");
				finished = false;

				int temporaryArmies = mapBuild.getNumberOfArmiesEachPlayerGets();
				finished = false;
				while (!finished) {// && debug == false

					isValidCommand = false;

					System.out.println("Player " + player.getPlayerName() + ":");
					if (temporaryArmies != 0) {
						System.out.println("You have -" + temporaryArmies + "- armies left for reinforcement.");
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
								if (num <= mapBuild.getNumberOfArmiesEachPlayerGets()) {
									if (mapBuild.reinforceIsValid(player.getPlayerName(), countryName, num) == true) {
										mapBuild.reinforce(player.getPlayerName(), countryName, num);
										temporaryArmies -= num;

										if (temporaryArmies <= 0) {
											finished = true;
										}

									} else {
										System.out.println("Reinforce is not valid");
									}

									isValidCommand = true;
								}
							} catch (NumberFormatException e) {
								System.out.println("Enter number of armies");
							}

						}
					} // Match Find Reinforce

				} // while reinforce

				

				/*
				 ************************************************
				 ************************************************
				 ************************************************
				 ************* Attack **********************
				 ************************************************
				 ************************************************
				 ************************************************
				 *
				 */
				// attack
//			System.out.println(attackRequestingMessage);
				/*
				 * int maxDic=0; Player maxPlayer=null; for(Player player :
				 * mapBuild.getPlayers()) { int diceNumber=dice.getNumDice();
				 * System.out.println(player.getPlayerName()+" rolled: "+diceNumber);
				 * if(diceNumber>maxDic) { maxDic=diceNumber; maxPlayer=player; }
				 * 
				 * } System.out.println("Player "+maxPlayer.getPlayerName()+" start to attack");
				 */
				finished = false;
				while (!finished) {// && debug == false
					System.out.println(player.getPlayerName() + " you may attack or fortify or finish your turn");
					isValidCommand = false;
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
						regex = "([\\w*\\_\\-]*) ([\\w*\\_\\-]*) (\\d+)";// (-allout)(-noattack))+
						setPattern(regex);
						setMatcher(addText);
						if (matcher.find()) {
							String attackerCountryName = matcher.group(1);
							String attackingCountryName = matcher.group(2);
							int attackerNumDice = Integer.parseInt(matcher.group(3));

							System.out.println("Attack from country name: " + attackerCountryName + "\n"
									+ "Attack To country name: " + attackingCountryName + "\n" + "number of dice  "
									+ attackerNumDice);
//Country attacker=;
							Country attackerCountry = mapBuild.getCountryByName(attackerCountryName);
							Country attackingCountry = mapBuild.getCountryByName(attackingCountryName);
							System.out.println("Armies in Attacking " + attackingCountryName + " is "
									+ attackingCountry.getArmies());
							if (attackerNumDice > attackingCountry.getArmies() || attackerNumDice > 3) {
								System.out.println(
										"attacking dice should not be more than the number of armies contained in the attacking country or more than 3");
								isValidCommand = false;
							} else if (!mapBuild.isAdjacentCountry(attackerCountry.getCountryId(),
									attackingCountry.getCountryId())) {
								System.out.println("Countries are not adjacent");
								isValidCommand = false;

								/// Country shoule be owned and atacking country for opponent
							} else {
								Dice attackDice=new Dice(attackerNumDice);
								int[] attackDiceArray = attackDice.getDiceArray();
								attackDice.showDice();
								
								/**** Start Defend *******/
								
								finished = true;
								isValidCommand = true;
								// Show name of player of defend country and ask him/her to roll dice by DEFEND
								// command
								
								boolean finishedDefend=false;
								while(!finishedDefend) {
								// defend numdice
									readInput();
								regex = "(?<=defend) (\\d+)";
								setPattern(regex);
								setMatcher(input);

								if (matcher.find()) {
									try {
										int defendNumDice = Integer.parseInt(matcher.group(1));
										Dice defendDice=new Dice(defendNumDice);
										int[] defendDiceArray = defendDice.getDiceArray();
										defendDice.showDice();
										
										
									boolean[] winner=attackDice.isWinner(defendDice);
									for(int i=0;i<winner.length;i++) {
										if(winner[i]) {
											System.out.println("Attacker win dice "+i);
											attackingCountry.setArmies(attackingCountry.getArmies()-1);
											if(attackingCountry.getArmies()==0) {
												System.out.println(attackingCountry.getCountryName()+" is conquered");
												attackingCountry.setPlayer(attackerCountry.getPlayerName());
											}
										
									}else {
										System.out.println("Defender win dice "+i);
										attackerCountry.setArmies(attackerCountry.getArmies()-1);
										if(attackerCountry.getArmies()==0) {
											System.out.println(attackerCountry.getCountryName()+" is conquered");
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
									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										System.out.println("NumDice should be integer");
									}
									finishedDefend=true;
								} else {
									isValidCommand = false;
									System.out.println("Please enter the defence command");
								}
								} //while(!finishedDefend) {
								
								
								
								
								
								
								
							}

							/*
							 * String allOut = matcher.group(5); String noAttack = matcher.group(6);
							 */

						} else {
							isValidCommand = false;
						}

					}

					

					// attackmove num

					regex = "(?<=attackmove) (\\d+)";
					setPattern(regex);
					setMatcher(input);

					if (matcher.find()) {
						try {
							int numAttack = Integer.parseInt(matcher.group(1));
							isValidCommand = true;
							System.out.println("attackmove : " + numAttack);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							System.out.println("NumDice should be integer");
						}
						isValidCommand = true;
					} else {
						isValidCommand = false;
					}

					if (!isValidCommand) {
						System.out.println("Correct command not found");
					}

					// mapBuild.showMap();

					//////////

					///////

					/// end of the attack phase

					
				} // End of While(!finished)

				System.out.println(fortifyRequestingMessage);

				finished = false;
				isValidCommand = false;

				while (!finished) {

					isValidCommand = false;

					System.out.println("Player " + player.getPlayerName() + ":");
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
					regex = "fortify none";

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

							if (mapBuild.fortifyIsValid(player, fromCountry, toCountry, num) == true) {
								mapBuild.fortify(fromCountry, toCountry, num);
								finished = true;
							} else {
								System.out.println("Fortify is not valid");
							}

							isValidCommand = true;
						}
					}
				}

				if (!isValidCommand) {
					System.out.println("Correct command not found");
				}
			} // Endof For Player
			mapBuild.showMap();
		}
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

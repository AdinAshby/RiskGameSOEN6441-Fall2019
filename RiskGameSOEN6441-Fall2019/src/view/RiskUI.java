package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Card;
import model.Country;
import model.Dice;
import model.MapAdapter;
import model.MapBuilder;
import model.MapConquest;
import model.MapDomination;
import model.Player;

/**
 * This is a Risk UI class
 * 
 * @author f_yazdan
 * @author AdinAshby
 * @author s_shehna
 */

public class RiskUI {
	/**
	 * private mapDomination
	 */

	private MapBuilder mapBuild = MapBuilder.getInstance();
	
	
	/**
	 * private mapDomination
	 */

	private MapDomination mapDomination = new MapDomination();
	
	
	/**
	 * private mapConquest
	 */

	private MapConquest mapConquest = new MapConquest();	
	
	
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

	private int counterForPhases;

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

		/**
		 * boolean isValidCommand
		 */
		boolean isValidCommand = false;
		/**
		 * String mapFileName
		 */
		String mapFileName = null;

		/**
		 * String editMapAnswer
		 */
		String editMapAnswer = null;
		/**
		 * boolean finished
		 */

		boolean finished = false;
		/**
		 * boolean placeAllFlag
		 */
		boolean placeAllFlag = false;
		/**
		 * boolean editMapWhile
		 */

		boolean editMapWhile = false;

		boolean finishedEditing=false;
		
		
		System.out.println(welcomeMessage);
		System.out.println(editMapYesOrNoMessage);



		/**
		 * String addText
		 */

		String addText = "";

		/**
		 * This part is hard code to test the project defined by the boolean debug
		 * attribute
		 */

		boolean debug = false;
		if (debug == true) {
			mapDomination.read("test");// ameroki
		//	MapAdapter mapAdapter=new MapAdapter(mapConquest);
		//	mapAdapter.read("testC");
			
			playerNames.add("Aval");
			playerNames.add("Dovom");

			mapDomination.assigningPlayersToCountries(playerNames);
			
			mapDomination.placeAllArmies();
			
			mapDomination.showMap();
			Player[] players = mapDomination.getPlayers();
			Player player1=players[0];
			Player player2=players[1];
			player1.calculateNumberOfArmiesEachPlayerGets();
			player2.calculateNumberOfArmiesEachPlayerGets();
			System.out.println(player1.getPlayerName() + " is your turn to reinforce");
			player1.getNumberOfArmiesEachPlayerGets();
			player2.getNumberOfArmiesEachPlayerGets();
			System.out.println("Player 1="+player1.getPlayerName()+" Player2="+player2.getPlayerName());
			int attackerCountryId=player1.getCountryIDs()[0];
			int fortifyCountryId=player1.getCountryIDs()[1];
			int attackingCountryId=player2.getCountryIDs()[0];
			String attackerCountryName=mapDomination.getCountryNameById(attackerCountryId);
			String fortifyCountryName=mapDomination.getCountryNameById(fortifyCountryId);
			String attackingCountryName=mapDomination.getCountryNameById(attackingCountryId);
			Country attackerCountry=mapDomination.getCountryById(attackerCountryId);
			Country fortifyCountry=mapDomination.getCountryById(fortifyCountryId);
			Country attackingCountry=mapDomination.getCountryById(attackingCountryId);
			isValidCommand = player1.reinforce(mapDomination,attackerCountryName , 3, false);
			mapDomination.showMap();
			System.out.println("\n-------------------\nAttack Scenario from "+attackerCountryName+" to "+attackingCountryName);
			player1.attack(attackerCountry, attackingCountry, 3, 2, mapDomination);
			
			mapDomination.showMap();
			System.out.println("\n-------------------\nFortify Scenario from "+attackerCountryName+" to "+fortifyCountryName);
			player1.fortify(attackerCountryName, fortifyCountryName, 5,  mapDomination);
			mapDomination.showMap();
			// System.out.println("NCC="+mapDomination.getNoOfContinentsControlled());
			editMapAnswer = "N";
		}

		editMapAnswer = scanner.nextLine();
		
		while(!editMapWhile) {
			
			if (editMapAnswer.equalsIgnoreCase("Y")) {
				System.out.println(editMapRequestingMessage);
				editMapWhile = true;
				while (!finished) {
			//		System.out.println(editMapRequestingMessage);
					isValidCommand = false;
					readInput();

					// editmap filename
					regex = "editmap ([\\w*\\_\\-]*)";
					setPattern(regex);
					setMatcher(input);

					if (getMatcher().find()) {
						mapFileName = getMatcher().group(1);
						try {
							mapDomination.read(mapFileName);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						isValidCommand = true;
					}
					
						// add continent
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
							mapDomination.addContinent(continentName, continentValue);
							isValidCommand = true;

						}

						// remove continent 
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
							mapDomination.removeContinent(continentName);
							isValidCommand = true;

						}

						// add country
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
							mapDomination.addCountry(countryName, continentName);
							isValidCommand = true;

						}

						// remove country 
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
							mapDomination.removeCountry(countryName);
							isValidCommand = true;

						}

						// add neighbor 
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
							mapDomination.addCountryAdjacency(countryName, neighborCountryName);
							isValidCommand = true;

						}

						// remove neighbor

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
							mapDomination.removeCountryAdjacency(countryName, neighborCountryName);
							isValidCommand = true;

						}

						// showmap
						regex = "showmap";
						setPattern(regex);
						setMatcher(input);
						if (matcher.find()) {
							mapDomination.showMap();
							isValidCommand = true;
						}

						// savemap filename 
						regex = "savemap ([\\w*\\_\\-]*)";
						setPattern(regex);
						setMatcher(input);
						if (matcher.find()) {
							mapFileName = matcher.group(1);
							isValidCommand = true;

							try {
								mapDomination.saveMap(mapFileName);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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

								// validatemap 
								regex = "validatemap";
								setPattern(regex);
								setMatcher(input);
								if (matcher.find()) {
									isValidCommand = true;
									mapDomination.validateMap();

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
									finishedEditing=true;
									isValidCommand = true;
								}

								if (!isValidCommand) {
									System.out.println("Please follow the correct command rules");
//									System.out.println(editMapRequestingMessage);
								}
							}
						
					
				
			}

			
			if (editMapAnswer.equalsIgnoreCase("N") || finishedEditing==true) {
				editMapWhile = true;

				counterForPhases = 0;
				mapView.showPhaseView(counterForPhases, "");

				
				finished = false;

				while (!finished && finishedEditing==false && debug == false) {
					System.out.println(loadMapRequestingMessage);
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
							isLoaded = mapDomination.read(mapFileName);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (isLoaded == true) {
							finished = true;
						}

					}

					if (!isValidCommand) {
						System.out.println("Please follow the correct command rules");
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
							mapDomination.saveMap(mapFileName);
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
						mapDomination.assigningPlayersToCountries(playerNames);

					}

					// showmap
					regex = "showmap";
					setPattern(regex);
					setMatcher(input);
					if (getMatcher().find()) {
						isValidCommand = true;
						mapView.showMap(mapDomination);

					}

					if (!isValidCommand) {
						System.out.println("Please follow the correct command rules");
					}
				}


				System.out.println(startupRequestingMessage);

				if (placeAllFlag == true) {
					break;
				}


				/******** START GAME PHASE **********************/

				// System.out.println(reinforceRequestingMessage);

				for (Player player : mapDomination.getPlayers()) {

					player.setCounterForPhases(1);
					// counterForPhases = 1;
					// mapView.showPhaseView(counterForPhases, player.getPlayerName());

					player.calculateNumberOfArmiesEachPlayerGets();
//					System.out.println(player.getPlayerName() + " is your turn to reinforce");
					finished = false;

					//for (Player player : mapDomination.getPlayers()) {

					

					finished = false;

					while (!finished && debug == false) {

						isValidCommand = false;

						System.out.println("Player " + player.getPlayerName() + ":");
						System.out.println("You get -" + mapDomination.calculateNumberOfInitialArmies() + "- armies.");
						readInput();

						// placeall
						regex = "placeall";
						setPattern(regex);
						setMatcher(input);
						if (matcher.find()) {
							isValidCommand = true;
							mapDomination.placeAllArmies();
							finished = true;
							placeAllFlag = true;

						}

						// showmap
						regex = "showmap";
						setPattern(regex);
						setMatcher(input);
						if (getMatcher().find()) {
							isValidCommand = true;
							mapView.showMap(mapDomination);
						}

						// placearmy countryname

						regex = "(?<=placearmy )(.*)";
						setPattern(regex);
						setMatcher(input);
						addText = "";

						if (getMatcher().find()) {
							String countryName = matcher.group(1);

							if (mapDomination.placearmyIsValid(player, countryName) == true) {
								mapDomination.assignInitialsArmiesToSpecificCountry(countryName,
										mapDomination.calculateNumberOfInitialArmies());
								finished = true;
							} else {
								System.out.println("placearmy is not valid");
							}

							isValidCommand = true;
						}

						if (!isValidCommand) {
							System.out.println("Please follow the correct command rules");
						}
						//}

						if (placeAllFlag == true) {
							break;
						}
					}
				}


				/******** START GAME PHASE **********************/

				
				for (Player player : mapDomination.getPlayers()) {

					player.setCounterForPhases(1);
					

					
					System.out.println(player.getPlayerName() + " is your turn to reinforce");
					finished = false;

					

					isValidCommand = player.reinforceCommand(mapDomination, mapView);

					player.setCounterForPhases(2);
					isValidCommand = player.attackCommand(mapDomination, mapView);

					player.setCounterForPhases(3);
					isValidCommand = player.fortifyCommand(mapDomination, mapView);

					if (!isValidCommand) {
						System.out.println("Please follow the correct command rules");
					}
				} // Endof For Player
				mapDomination.showMap();
			} else {
				System.out.println("Please answer by Y or N");
				editMapAnswer = scanner.nextLine();
			}
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

	/**
	 * This method is for getting the counter for phases
	 * 
	 * @return counterForPhases
	 */
	public int getCounterForPhases() {
		return counterForPhases;
	}

}

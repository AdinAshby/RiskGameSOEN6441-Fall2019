package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Card;
import model.Country;
import model.Dice;
import model.MapAdapter;
import model.MapGeo;
import model.MapConquest;
import model.MapDomination;
import model.Player;
import model.Tournament;

/**
 * This is a Risk UI class
 * 
 * @author f_yazdan
 * @author AdinAshby
 * @author s_shehna
 */

public class RiskUI {
	/**
	 * private mapAdapter
	 */

	//	private MapGeo mapBuild = MapGeo.getInstance();



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
	/**
	 * private playerStrategies
	 */
	private ArrayList<String> playerStrategies = new ArrayList<String>();

	private Tournament theTournament;

	private int counterForPhases;
	private MapDomination mapAdapter;
	private MapDomination mapDomination = new MapDomination();
	private MapConquest mapConquest= new MapConquest();


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
		/**
		 * boolean finishedEditing
		 */
		boolean finishedEditing = false;

		/**
		 * print welcome message
		 */
		System.out.println(welcomeMessage);
		/**
		 * print edit map
		 */
		System.out.println(editMapYesOrNoMessage);

		/**
		 * String addText
		 */

		String addText = "";

		/**
		 * This part is hard code to test the project defined by the boolean debug
		 * attribute
		 */



		boolean debug = true;
		if (debug == true) {
			mapFileName="Aden";

			isDominationMap(mapFileName);



			// mapAdapter mapAdapter=new MapAdapter(mapAdapter);
			//mapAdapter.read("test");

			playerNames.add("Aval");
			playerNames.add("Dovom");

			playerStrategies.add("human");
			playerStrategies.add("human");

			mapAdapter.assigningPlayersToCountries(playerNames, playerStrategies);
			mapAdapter.showMap();
			mapAdapter.placeAllArmies();

			mapAdapter.showMap();
			Player[] players = mapAdapter.getPlayers();
			Player player1 = players[0];
			Player player2 = players[1];
			player1.calculateNumberOfArmiesEachPlayerGets();
			player2.calculateNumberOfArmiesEachPlayerGets();
			System.out.println(player1.getPlayerName() + " is your turn to reinforce");
			player1.getNumberOfArmiesEachPlayerGets();
			player2.getNumberOfArmiesEachPlayerGets();
			System.out.println("Player 1=" + player1.getPlayerName() + " Player2=" + player2.getPlayerName());

			//player1.attackAllout(mapAdapter);
			//	System.exit(0);
			int attackerCountryId = player1.getCountryIDs().get(0);
			int fortifyCountryId = player1.getCountryIDs().get(1);
			int attackingCountryId = player2.getCountryIDs().get(0);
			String attackerCountryName = mapAdapter.getCountryNameById(attackerCountryId);
			String fortifyCountryName = mapAdapter.getCountryNameById(fortifyCountryId);
			String attackingCountryName = mapAdapter.getCountryNameById(attackingCountryId);
			Country attackerCountry = mapAdapter.getCountryById(attackerCountryId);
			Country fortifyCountry = mapAdapter.getCountryById(fortifyCountryId);
			Country attackingCountry = mapAdapter.getCountryById(attackingCountryId);
			isValidCommand = player1.reinforce(mapAdapter, attackerCountryName, 3, false);
			mapAdapter.showMap();
			System.out.println("\n-------------------\nAttack Scenario from " + attackerCountryName + " to "
					+ attackingCountryName);
			player1.attack(attackerCountry, attackingCountry, 3, 2, mapAdapter, 0);

			mapAdapter.showMap();
			System.out.println("\n-------------------\nFortify Scenario from " + attackerCountryName + " to "
					+ fortifyCountryName);
			player1.fortify(attackerCountryName, fortifyCountryName, 5, mapAdapter);
			mapAdapter.showMap();
			// System.out.println("NCC="+mapAdapter.getNoOfContinentsControlled());
			editMapAnswer = "N";
		}

		editMapAnswer = scanner.nextLine();

		while (!editMapWhile) {

			if (editMapAnswer.equalsIgnoreCase("Y")) {
				System.out.println(editMapRequestingMessage);
				editMapWhile = true;
				while (!finished) {
					// System.out.println(editMapRequestingMessage);
					isValidCommand = false;
					readInput();

					// editmap filename
					regex = "editmap ([\\w*\\_\\-]*)";
					setPattern(regex);
					setMatcher(input);

					if (getMatcher().find()) {
						mapFileName = getMatcher().group(1);
						try {
							MapConquest mapConquest=new MapConquest();
							mapAdapter=new MapAdapter(mapConquest);
							mapAdapter.read(mapFileName);
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
						mapAdapter.addContinent(continentName, continentValue);
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
						mapAdapter.removeContinent(continentName);
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
						mapAdapter.addCountry(countryName, continentName);
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
						mapAdapter.removeCountry(countryName);
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
						mapAdapter.addCountryAdjacency(countryName, neighborCountryName);
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
						mapAdapter.removeCountryAdjacency(countryName, neighborCountryName);
						isValidCommand = true;

					}

					// showmap
					regex = "showmap";
					setPattern(regex);
					setMatcher(input);
					if (matcher.find()) {
						mapAdapter.showMap();
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
							mapAdapter.write(mapFileName);
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
						mapAdapter.validateMap();

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
						finishedEditing = true;
						isValidCommand = true;
					}

					if (!isValidCommand) {
						System.out.println("Please follow the correct command rules");
						// System.out.println(editMapRequestingMessage);
					}
				}

			}

			if (editMapAnswer.equalsIgnoreCase("N") || finishedEditing == true) {
				editMapWhile = true;

				counterForPhases = 0;
				mapView.showPhaseView(counterForPhases, "");

				finished = false;

				while (!finished && finishedEditing == false && debug == false) {
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
							isDominationMap(mapFileName);
							isLoaded = mapAdapter.read(mapFileName);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (isLoaded == true) {
							finished = true;
						}

					}

					// tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns

					ArrayList<String> listOfMapFiles = new ArrayList<String>();
					ArrayList<String> listOfPlayerStrategies = new ArrayList<String>();
					int numberOfGames = 0;
					int maxNumberOfTurns = 0;

					regex = "(?<=tournament)(.*)";
					setPattern(regex);
					setMatcher(input);

					addText = "";
					if (matcher.find()) {
						addText = getMatcher().group(1);
					}

					regex = "-M (([\\w*\\_\\-]*))+";
					setPattern(regex);
					setMatcher(addText);

					while (matcher.find()) {
						listOfMapFiles.add(getMatcher().group(2));
					}

					regex = "(-P ([\\w*\\_\\-]*))+";
					setPattern(regex);
					setMatcher(addText);

					while (matcher.find()) {
						listOfPlayerStrategies.add(getMatcher().group(2));
					}

					regex = "-G \\d+";
					setPattern(regex);
					setMatcher(addText);

					while (matcher.find()) {
						numberOfGames = Integer.parseInt(getMatcher().group(2));
					}

					regex = "-D \\d+";
					setPattern(regex);
					setMatcher(addText);

					while (matcher.find()) {
						maxNumberOfTurns = Integer.parseInt(getMatcher().group(2));
					}

					if((listOfMapFiles.size() > 0 && listOfMapFiles.size() < 6) && (listOfPlayerStrategies.size() > 1 && listOfPlayerStrategies.size() < 5) && (numberOfGames > 0 && numberOfGames < 6) && (maxNumberOfTurns > 9 && maxNumberOfTurns < 51)) {

						isValidCommand = true;

						for(String each : listOfMapFiles) {
							if(isDominationMap(each) == false) {
								isValidCommand = false;
								break;
							}
						}

						for(String each : listOfPlayerStrategies) {
							if(!(each.equalsIgnoreCase("aggressive") || each.equalsIgnoreCase("benevolent") || each.equalsIgnoreCase("random") || each.equalsIgnoreCase("cheater"))) {
								isValidCommand = false;
								break;
							}
						}

						if(isValidCommand)
							theTournament = new Tournament(listOfMapFiles, listOfPlayerStrategies, numberOfGames, maxNumberOfTurns);
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
							mapAdapter.write(mapFileName);
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
					regex = "(-add ([\\w*\\_\\-]*) ([\\w*\\_\\-]*))+";
					setPattern(regex);
					setMatcher(addText);

					while (matcher.find()) {
						String playerName = getMatcher().group(2);
						String playerStrategy = getMatcher().group(3);

						playerNames.add(playerName);
						playerStrategies.add(playerStrategy);
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
						int indexOfPlayerName = playerNames.indexOf(playerName);
						playerNames.remove(playerName);
						playerStrategies.remove(indexOfPlayerName);
						isValidCommand = true;
					}

					// populatecountries
					regex = "populatecountries";
					setPattern(regex);
					setMatcher(input);
					if (getMatcher().find()) {
						isValidCommand = true;
						finished = true;
						mapAdapter.assigningPlayersToCountries(playerNames, playerStrategies);

					}

					// showmap
					regex = "showmap";
					setPattern(regex);
					setMatcher(input);
					if (getMatcher().find()) {
						isValidCommand = true;
						mapView.showMap(mapAdapter);

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

				for (Player player : mapAdapter.getPlayers()) {
					if (placeAllFlag == true) {
						break;
					}
					// player.setCounterForPhases(1);
					// /*********************************************/
					// counterForPhases = 1;
					// mapView.showPhaseView(counterForPhases, player.getPlayerName());

					player.calculateNumberOfArmiesEachPlayerGets();
					// System.out.println(player.getPlayerName() + " is your turn to reinforce");
					finished = false;

					// for (Player player : mapAdapter.getPlayers()) {

					finished = false;

					while (!finished && debug == false) {

						isValidCommand = false;

						System.out.println("Player " + player.getPlayerName() + ":");
						System.out.println("You get -" + mapAdapter.calculateNumberOfInitialArmies() + "- armies.");
						readInput();

						// placeall
						regex = "placeall";
						setPattern(regex);
						setMatcher(input);
						if (matcher.find()) {
							isValidCommand = true;
							mapAdapter.placeAllArmies();
							finished = true;
							placeAllFlag = true;
							break;

						}

						// showmap
						regex = "showmap";
						setPattern(regex);
						setMatcher(input);
						if (getMatcher().find()) {
							isValidCommand = true;
							mapView.showMap(mapAdapter);
						}

						// placearmy countryname

						regex = "(?<=placearmy )(.*)";
						setPattern(regex);
						setMatcher(input);
						addText = "";

						if (getMatcher().find()) {
							String countryName = matcher.group(1);

							if (mapAdapter.placearmyIsValid(player, countryName) == true) {
								mapAdapter.assignInitialsArmiesToSpecificCountry(countryName,
										mapAdapter.calculateNumberOfInitialArmies());
								finished = true;
							} else {
								System.out.println("placearmy is not valid");
							}

							isValidCommand = true;
						}

						if (!isValidCommand) {
							System.out.println("Please follow the correct command rules");
						}
						// }

						if (placeAllFlag == true) {
							break;
						}
					}
				}

				/******** START GAME PHASE **********************/

				for (Player player : mapAdapter.getPlayers()) {

					player.setCounterForPhases(1);

					System.out.println(player.getPlayerName() + " is your turn to reinforce");
					finished = false;

					isValidCommand = player.reinforceCommand(mapAdapter, mapView);

					player.setCounterForPhases(2);
					isValidCommand = player.attackCommand(mapAdapter, mapView);

					player.setCounterForPhases(3);
					isValidCommand = player.fortifyCommand(mapAdapter, mapView);

					if (!isValidCommand) {
						System.out.println("Please follow the correct command rules");
					}
				} // Endof For Player
				mapAdapter.showMap();
			} else {
				System.out.println("Please answer by Y or N");
				editMapAnswer = scanner.nextLine();
			}
		}
	}

	private boolean isDominationMap(String mapFileName) {
		File mapFolder=mapDomination.getMapFolder();
		File file = new File(mapFolder + "/" + mapFileName + ".map");
		if (!file.exists()) {
			System.out.println(mapFileName + " map file not found. Please try again");
			return false;
		}else {
			System.out.println("Read File: "+mapFolder + "/" + mapFileName + ".map");
		}

		BufferedReader bufferedReader = null;
		try {

			bufferedReader = new BufferedReader(new FileReader(file));

			StringBuffer stringBuffer = new StringBuffer();
			String fileContent = "";
			String line = null;

			line = bufferedReader.readLine();
			if(!line.equals("[Map]")) {
				mapDomination.read(mapFileName);
				mapAdapter=mapDomination;
			}else {
				MapDomination mapAdapterFromDomination = new MapAdapter(mapConquest);
				mapAdapterFromDomination.read(mapFileName);
				mapAdapter=mapAdapterFromDomination;

			}
		}catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			System.out.println("Error in reading Map"+e);
		}


		return true;

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

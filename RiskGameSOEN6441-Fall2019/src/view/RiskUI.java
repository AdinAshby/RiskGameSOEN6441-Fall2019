package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.BoldAction;

import model.MapBuilder;

public class RiskUI {
	private String welcomeMessage = "\t\t*****Risk Game*****";
	private String editMapYesOrNoMessage = "Do you want to create/edit map? (Y/N)\n";
	private String editMapRequestingMessage = "Enter corresponding commands for creating/editing a map.\n"
			+ "Whenever you are happy with the result, enter \"finishediting\".\n";
	private String loadMapRequestingMessage = "Load the map you with to play by using \"loadmap\" command:\n";
	private String addOrRemovePlayersRequestingMessage = "Add/remove players and at the end, enter \"populatecountries\":\n";
	private String startupRequestingMessage = "Place your armies on your selected country or use \"placeall\" command:\n";
	private String reinforceRequestingMessage = "Now, it's time to reinforce!\n";
	private String fortifyRequestingMessage = "Let's fortify! or type in \"fortify none\" if you don't want to.\n";

	private Scanner scanner;
	private String input;
	
	private String regex;
	private Pattern pattern;
	private Matcher matcher;
	

	public RiskUI() {
		scanner = new Scanner(System.in);
	}

	public void RiskUIStartTheGame() {


		MapBuilder mapBuild = new MapBuilder();
		
		boolean isValidCommand = false;
		String mapFileName = null;

		String editMapAnswer;

		boolean finished = false;

		System.out.println(welcomeMessage);
		System.out.println(editMapYesOrNoMessage);

		editMapAnswer = scanner.next();

		while (true) {
			if(editMapAnswer.equalsIgnoreCase("Y")) {
				while(!finished) {
					System.out.println(editMapRequestingMessage);


					isValidCommand = false;
					readInput();

					// editmap filename
					regex = "editmap ([\\w*\\_\\-]*)";
					setPattern(regex);
					setMatcher(input);
	
					if (getMatcher().find()) {
						mapFileName = getMatcher().group(1);
						mapBuild.loadMap(mapFileName);
						isValidCommand = true;
					}

					// continent -add
					regex = "editcontinent -add ([\\w*\\_\\-]*) (\\d*)";
					setPattern(regex);
					setMatcher(input);
					
					if (getMatcher().find()) {
						String continentName = matcher.group(1);
						int continentValue = Integer.parseInt(matcher.group(2));
						mapBuild.addContinent(continentName, continentValue);
						isValidCommand = true;
					}

					// continent -remove
					regex = "editcontinent -remove ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String continentName = matcher.group(1);
						mapBuild.removeContinent(continentName);
						isValidCommand = true;
					}

					// editcountry -add
					regex = "editcountry -add ([\\w*\\_\\-]*) ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String countryName = matcher.group(1);
						String continentName = matcher.group(2);
						mapBuild.addCountry(countryName, continentName);
						isValidCommand = true;
					}

					// editcountry -remove
					regex = "editcountry -remove ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String countryName = matcher.group(1);
						mapBuild.removeCountry(countryName);
						isValidCommand = true;
					}

					// editneighbor -add
					regex = "editneighbor -add ([\\w*\\_\\-]*) ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String countryName = matcher.group(1);
						String neighborCountryName = matcher.group(2);
						mapBuild.addCountryAdjacency(countryName, neighborCountryName);
						isValidCommand = true;
					}

					// editneighbor -remove 
					regex = "editneighbor -remove ([\\w*\\_\\-]*) ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String countryName = matcher.group(1);
						String neighborCountryName = matcher.group(2);
						mapBuild.removeCountryAdjacency(countryName, neighborCountryName);
						isValidCommand = true;
					}

					// showmap
					regex = "showmap";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						mapBuild.showMap();
						isValidCommand = true;
					}

					// savemap filename
					regex = "savemap ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						mapFileName = matcher.group(1);
						isValidCommand = true;

						mapBuild.saveMap(mapFileName);

					}

					// validatemap
					regex = "validatemap";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						isValidCommand = true;
						mapBuild.validateMap();

					}

					// showadjacencymap countryname
					regex = "showadjacencymap ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
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

				while (!finished) {
					isValidCommand = false;
					readInput();

					// loadmap filename
					regex = "loadmap ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);

					if (matcher.find()) {
						mapFileName = matcher.group(1);
						isValidCommand = true;
						finished = true;
						mapBuild.loadMap(mapFileName);
					}

					if (!isValidCommand) {
						System.out.println("Correct command not found");
					}
				}

				System.out.println(addOrRemovePlayersRequestingMessage);
				finished = false;

				while(!finished) {

					isValidCommand = false;
					readInput();

					// gameplayer -add
					regex = "gameplayer -add ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String continentName = matcher.group(1);
						mapBuild.removeContinent(continentName);
						isValidCommand = true;
					}

					// gameplayer -remove
					regex = "gameplayer -remove ([\\w*\\_\\-]*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String continentName = matcher.group(1);
						mapBuild.removeContinent(continentName);
						isValidCommand = true;
					}

					// populatecountries
					regex = "populatecountries";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						isValidCommand = true;
						finished = true;
						mapBuild.validateMap();

					}

					if (!isValidCommand) {
						System.out.println("Correct command not found");
					}
				}

				System.out.println(startupRequestingMessage);

				while(!finished) {

					isValidCommand = false;
					readInput();

					// placearmy
					regex = "placearmy ([\\w*\\_\\-]*) (\\d*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String continentName = matcher.group(1);
						int continentValue = Integer.parseInt(matcher.group(2));
						mapBuild.addContinent(continentName, continentValue);
						isValidCommand = true;
					}

					// placeall
					regex = "placeall";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						isValidCommand = true;
						finished = true;
						mapBuild.validateMap();
					}

					if (!isValidCommand) {
						System.out.println("Correct command not found");
					}
				}

				System.out.println(reinforceRequestingMessage);
				finished = false;

				while(!finished) {

					isValidCommand = false;
					readInput();

					// reinforce countryname num
					regex = "reinforce ([\\w*\\_\\-]*) (\\d*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String continentName = matcher.group(1);
						int continentValue = Integer.parseInt(matcher.group(2));
						mapBuild.addContinent(continentName, continentValue);
						isValidCommand = true;
					}

					if (!isValidCommand) {
						System.out.println("Correct command not found");
					}
				}

				System.out.println(fortifyRequestingMessage);
				finished = false;
				
				while(!finished) {

					isValidCommand = false;
					readInput();

					// fortify fromcountry tocountry num
					regex = "fortify ([\\w*\\_\\-]*) ([\\w*\\_\\-]*) (\\d*)";
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						String continentName = matcher.group(1);
						int continentValue = Integer.parseInt(matcher.group(2));
						mapBuild.addContinent(continentName, continentValue);
						isValidCommand = true;
					}

					// fortify none
					regex = "fortify none";

					if (input.equalsIgnoreCase(regex)) {
						isValidCommand = true;
						finished = true;
					}

					if (!isValidCommand) {
						System.out.println("Correct command not found");
					}
				}
			}
		}
	}

	public void readInput() {
		this.input = scanner.nextLine();
	}
	
	public void setPattern(String regex) {
		this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}
	
	public void setMatcher(String input) {
		this.matcher = pattern.matcher(input);
	}
	
	public Matcher getMatcher() {
		return this.matcher;
	}
	

	/*String editMapYesOrNoAnswer;
		boolean editMapWhileStatus = true;

		String loadMapCommands;
		String loadMapFilePath;
		boolean loadMapWhileStatus = true;

		String playersCommands;
		String addOrRemovePlayerCommand;
		String playerName;
		boolean playersWhileStatus = true;

		ArrayList<String> playerNames = new ArrayList<String>();

		String startupCommands;
		String countryNamePlaceArmyCommand;
		int numberPlaceArmyCommand;
		boolean startupWhileStatus = true;

		String reinforceCommand;
		String countryNameReinforce;
		int numberReinforce;
		boolean reinforceWhileStatus = true;

		String fortifyCommands;
		String fromCountryFortify;
		String toCountryFortify;
		String noneFortify;
		int numberFortify;
		boolean fortifyWhileStatus = true;

		while(editMapWhileStatus) {

			System.out.println(welcomeMessage);
			System.out.println(editMapRequestingMessage);
			editMapYesOrNoAnswer = scanner.next();

			switch (editMapYesOrNoAnswer) {
			case "Y":
			case "y":


				editMapWhileStatus = false;
				break;

			case "N":
			case "n":

				editMapWhileStatus = false;
				break;

			default:
				System.out.println("Your answer is not valid! Try again...\n");
				break;
			}
		}

		System.out.println(loadMapRequestingMessage);
		loadMapCommands = scanner.next();


		while(loadMapWhileStatus) {
			switch (loadMapCommands) {

			case "loadmap":
			case "LOADMAP":
				loadMapFilePath = scanner.next();
				loadMapWhileStatus = false;
				break;

			default:
				System.out.println("Your command is not valid! Try again...\\n");
				break;
			}
		}

		System.out.println(addOrRemovePlayersRequestingMessage);

		while(playersWhileStatus) {
			playersCommands = scanner.next();

			switch (playersCommands) {
			case "gameplayer":
			case "GAMEPLAYER":
				if (scanner.hasNext()) 
					while (scanner.hasNext()) {
						addOrRemovePlayerCommand = scanner.next();
						playerName = scanner.next();

						switch (addOrRemovePlayerCommand) {
						case "-add":
						case "-ADD":
							playerNames.add(playerName);
							break;

						case "-remove":
						case "-REMOVE":
							playerNames.remove(playerName);
							break;
						}
					}
				else 
					System.out.println("Your command is not valid! Try again...\\n");

				break;

			case "populatecountries":
			case "POPULATECOUNTRIES":

				playersWhileStatus = false;
				break;

			default:
				System.out.println("Your command is not valid! Try again...\\n");
				break;
			}
		}

		System.out.println(startupRequestingMessage);

		for(int i = 0; i < ...; i++) {

			System.out.println("Player" + ...);
			startupWhileStatus = true;

			while(startupWhileStatus) {
				System.out.println("You have -" + ... + "- army(ies) to place!");
				startupCommands = scanner.next();

				switch (startupCommands) {
				case "placearmy":
				case "PLACEARMY":
					if (scanner.hasNext()) 
						while (scanner.hasNext()) {
							countryNamePlaceArmyCommand = scanner.next();
							numberPlaceArmyCommand = scanner.nextInt();

						}
					else 
						System.out.println("Your command is not valid! Try again...\\n");


					break;

				case "placeall":
				case "PLACEALL":

					startupWhileStatus = false;
					break;

				default:
					System.out.println("Your command is not valid! Try again...\\n");
					break;
				}	
			}
		}

		System.out.println(reinforceRequestingMessage);

		for(int i = 0; i < ...; i++) {

			System.out.println("Player" + ...);
			reinforceWhileStatus = true;

			while(reinforceWhileStatus) {
				System.out.println("You have -" + ... + "- army(ies) to place!");
				reinforceCommand = scanner.next();

				if(reinforceCommand.equalsIgnoreCase("reinforce")) {
					if (scanner.hasNext()) 
						while (scanner.hasNext()) {
							countryNameReinforce = scanner.next();
							numberReinforce= scanner.nextInt();

						}
				} else 
					System.out.println("Your command is not valid! Try again...\\n");
			}	
		}

		System.out.println(fortifyRequestingMessage);

		for(int i = 0; i < ...; i++) {

			System.out.println("Player" + ...);
			fortifyWhileStatus = true;

			while(fortifyWhileStatus) {
				fortifyCommands = scanner.next();

				if (fortifyCommands.equalsIgnoreCase("fortify")) {
					noneFortify = scanner.next();
					if (noneFortify.equalsIgnoreCase("none")) {
						fortifyWhileStatus = false;
					} else {
						fromCountryFortify = noneFortify;
						toCountryFortify = scanner.next();
						numberFortify = scanner.nextInt();	
					}
				}
			}
		} */
} 
}

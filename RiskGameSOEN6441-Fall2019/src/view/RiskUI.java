package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.BoldAction;

import model.MapBuilder;

public class RiskUI {
	private MapBuilder mapBuild = new MapBuilder();
	private MapView mapView = new MapView();
	
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
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	private int numberOfArmiesEachPlayerGets;

	public RiskUI() {
		scanner = new Scanner(System.in);
	}

	public void RiskUIStartTheGame() {
		
		boolean isValidCommand = false;
		String mapFileName = null;

		String editMapAnswer;

		boolean finished = false;
		
		
		System.out.println(welcomeMessage);
		System.out.println(editMapYesOrNoMessage);

		editMapAnswer = scanner.next();
		String addText="";
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
						mapBuild.addCountry(continentName, continentName);
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
					regex = "(-remove ([\\w*\\_\\-]*) ([\\w*\\_\\-]*))+";
					setPattern(regex);
					setMatcher(addText);
					while (matcher.find()) {
						String countryName = matcher.group(2);
						String neighborCountryName = matcher.group(3);
						///////
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
						String countryName = matcher.group(1);
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

				while (!finished) {
					isValidCommand = false;
					readInput();

					// loadmap filename done
					regex = "loadmap ([\\w*\\_\\-]*)";
					setPattern(regex);
					setMatcher(input);

					if (matcher.find()) {
						mapFileName = matcher.group(1);
						isValidCommand = true;
						finished = true;
						try {
							mapBuild.loadMap(mapFileName);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						mapBuild.assigningPlayersToCountries(playerNames);;

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

				while(!finished) {

					isValidCommand = false;
					readInput();

					// placearmy done
					regex = "(?<=placearmy)(.*)";
					setPattern(regex);
					setMatcher(input);
					addText = "";
					if (matcher.find()) {
						addText = matcher.group(1);
					}
					regex = "([\\w*\\_\\-]*)+";
					setPattern(regex);
					setMatcher(addText);
					while (matcher.find()) {
						 String countryName = matcher.group(1);
					//// fun....(countryName);
						isValidCommand = true;
					}
					
					
					// placeall
					regex = "placeall";
					setPattern(regex);
					setMatcher(input);
					if (matcher.find()) {
						isValidCommand = true;
						finished = true;
						mapBuild.validateMap(); // function to be added
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

				System.out.println(reinforceRequestingMessage);
				finished = false;

				while(!finished) {

					isValidCommand = false;
					readInput();

					//reinforce
					regex = "(?<=reinforce)(.*)";
					setPattern(regex);
					setMatcher(input);
					if (matcher.find()) {
						addText = matcher.group(1);
					}
					regex = "(([\\w*\\_\\-]*) (\\d*))+";
					setPattern(regex);
					setMatcher(addText);
					while (matcher.find()) {
						String countryName = matcher.group(2);
						int num = Integer.parseInt(matcher.group(3));
						///fun ....(countryName, num);
						isValidCommand = true;

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

				System.out.println(fortifyRequestingMessage);
				finished = false;
				
				while(!finished) {

					isValidCommand = false;
					readInput();

					// fortify fromcountry tocountry num
					regex = "(?<=fortify)(.*)";
					setPattern(regex);
					setMatcher(input);
					String fortify = "";
					if (matcher.find()) {
						addText = matcher.group(1);
					}
					regex = "(([\\w*\\_\\-]*) ([\\w*\\_\\-]*) (\\d*))+";
					setPattern(regex);
					setMatcher(addText);
					while (matcher.find()) {
						String fromCountry = matcher.group(2);
						String toCountry = matcher.group(3);
						int num = Integer.parseInt(matcher.group(4));

						///fun....
						isValidCommand = true;

					}

					// fortify none
					regex = "fortify none";

					if (input.equalsIgnoreCase(regex)) {
						isValidCommand = true;
						finished = true;
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
	
	public void calculateNumberOfArmiesEachPlayerGets(String playerName) {
		numberOfArmiesEachPlayerGets = (mapBuild.getPlayerByName(playerName).getCountryIDs().length / 3 > 3) ? mapBuild.getPlayerByName(playerName).getCountryIDs().length / 3 : 3;
	}
	
	public int getNumberOfArmiesEachPlayerGets() {
		return numberOfArmiesEachPlayerGets;
	}
}
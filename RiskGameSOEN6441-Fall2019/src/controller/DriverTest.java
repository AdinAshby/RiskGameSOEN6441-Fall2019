package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.AdjacencyList;
import model.Continent;
import model.Country;
import model.MapBuilder;

public class DriverTest {
	// editcontinent -add continentname continentvalue -remove continentname
	// editcountry -add countryname continentname -remove countryname
	// editneighbor -add countryname neighborcountryname -remove countryname
	// neighborcountryname

	public static void main(String[] args) throws Exception {
		final File mapFolder = new File("./MapFiles");
		Scanner in = new Scanner(System.in);

		System.out.println("		SOEN 6441_Project" + "\n" + "		    Domination");
		System.out.println("__.__.__.__.__.__.__.__.__.__.__.__.__.__.__" + "\n");

//		System.out.println("	Stage 0" + "\n");

		MapBuilder mapBuild = new MapBuilder();
		String regex;
		Pattern pattern;
		Matcher matcher;
		boolean isValidCommand = false;
		String mapFileName = null;

		while (true) {
			isValidCommand = false;
			String input = in.nextLine();

// load map
			regex = "loadmap ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);

			if (matcher.find()) {
				mapFileName = matcher.group(1);
				isValidCommand = true;
				mapBuild.loadMap(mapFileName);
			}

			// edit map
			regex = "editmap ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				mapFileName = matcher.group(1);
				mapBuild.loadMap(mapFileName);
				isValidCommand = true;
			}

			// add continent
			regex = "editcontinent -add ([\\w*\\_\\-]*) (\\d*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				String continentName = matcher.group(1);
				int continentValue = Integer.parseInt(matcher.group(2));
				mapBuild.addContinent(continentName, continentValue);
				isValidCommand = true;
			}

			// remove continent
			regex = "editcontinent -remove ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				String continentName = matcher.group(1);
				mapBuild.removeContinent(continentName);
				isValidCommand = true;
			}

			// add country
			regex = "editcountry -add ([\\w*\\_\\-]*) ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				String countryName = matcher.group(1);
				String continentName = matcher.group(2);
				mapBuild.addCountry(countryName, continentName);
				isValidCommand = true;
			}

			// remove country
			regex = "editcountry -remove ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				String countryName = matcher.group(1);
				mapBuild.removeCountry(countryName);
				isValidCommand = true;
			}

			// add neighbor
			regex = "editneighbor -add ([\\w*\\_\\-]*) ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				String countryName = matcher.group(1);
				String neighborCountryName = matcher.group(2);
				mapBuild.addCountryAdjacency(countryName, neighborCountryName);
				isValidCommand = true;
			}

			// remove neighbor
			regex = "editneighbor -remove ([\\w*\\_\\-]*) ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				String countryName = matcher.group(1);
				String neighborCountryName = matcher.group(2);
				mapBuild.removeCountryAdjacency(countryName, neighborCountryName);
				isValidCommand = true;
			}

			// show map
			regex = "showmap";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				mapBuild.showMap();
				isValidCommand = true;
			}

			// save map
			regex = "savemap ([\\w*\\_\\-]*)";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				mapFileName = matcher.group(1);
				isValidCommand = true;

				mapBuild.saveMap(mapFileName);

			}
			
			// validate map
			regex = "validatemap";
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(input);
			if (matcher.find()) {
				isValidCommand = true;
				mapBuild.validateMap();

			}

			if (!isValidCommand) {
				System.out.println("Correct command not found");
			}

		} // while

	}
}
/*
 * // edit map mapNotFound = false; regex = "editmap ([\\w*\\_\\-]*)"; pattern =
 * Pattern.compile(regex); while (mapNotFound == false) { String inputLoadMap =
 * in.nextLine();
 * 
 * Matcher matcher = pattern.matcher(inputLoadMap); String mapFileName = null;
 * if (matcher.find()) { mapFileName = matcher.group(1); } if (mapFileName !=
 * null) { File file = new File(mapFolder + "/" + mapFileName + ".map"); if
 * (file.exists()) { System.out.println(mapFileName); mapNotFound = true; } else
 * { System.out.println("map file not found"); } } else {
 * System.out.println("Please use 'editmap filename' format "); } }
 * 
 * System.out.println("Start Edit Map");
 */
//		 ameroki
/*
 * System.exit(0);
 * 
 * System.out.println("Please choose an Item below : " + "\n");
 * 
 * System.out.println("	1- Edit Continent" + "\n");
 * System.out.println("	2- Edit Country" + "\n");
 * System.out.println("	3- Edit Neighbor" + "\n");
 * System.out.println("	4- Show Map");
 * 
 * int input = in.nextInt(); for (int i = 1; i <= input; i++) { if (input == 1)
 * { //// System.out.println("Do you want to edit the Map" + "\n"); String
 * replyToeditMap = in.next(); replyToeditMap.toUpperCase();
 * 
 * try { //// if ((replyToeditMap.equals("yes")) ||
 * (replyToeditMap.equals("y"))) { ////
 * System.out.println("	Please choose an Item below " + "\n");
 * System.out.println("	1- Edit Continent" + "\n");
 * System.out.println("	2- Edit Country" + "\n");
 * System.out.println("	3- Edit Neighbor" + "\n");
 * System.out.println("	4- Show Map"); } // BufferedReader br = new
 * BufferedReader(new InputStreamReader(System.in)); // String inputEditString =
 * br.readLine();
 * 
 * inputEditString.toLowerCase();
 * 
 * int inputEditInt = Integer.parseInt(br.readLine()); // int inputEditInt =
 * in.nextInt(); ||( inputEditInt==2))
 * 
 * // while ( br.readLine()!= null) {
 * 
 * if (inputEditString.equals("editcontinent")) {
 * System.out.println("wait fo -add");
 * 
 * String editContinentInput = in.nextLine();
 * 
 * // String regex = "-add"; // Pattern pattern = Pattern.compile(regex); //
 * Matcher matcher = pattern.matcher(editContinentInput); // System.out.println(
 * matcher.pattern()); } // //pattern regex // String patternString =
 * "(\\-add\\]\\s)"; // Pattern pattern = Pattern.compile(patternString); //
 * Matcher matcher = pattern.matcher(editContinentInput); // String
 * continentLines = ""; // if (matcher.find()) { // continentLines =
 * matcher.group(0); // }System.out.println("something");
 * 
 * // // // // else if (inputEditString.equals("editcountry")) { //// // } else
 * if (inputEditString.equals("editneighbor")) { //// // } else if
 * (inputEditString.equals("showmap")) { //
 * 
 * // // switch (inputEditString) { // case "editcontinent" : // break;case
 * "editcountry" : // break;case "editneighbor" : // break;case "showmap" :} }
 * 
 * catch (Exception e) { System.out.
 * println(" you have to let us know whether you want to edit or not by ' YES ' or ' Y '"
 * ); }
 * 
 * }
 * 
 * if (input == 2) {
 * 
 * }
 * 
 * if (input == 3) { System.out.println("***Thank you***"); System.exit(0); }
 * 
 * }
 * 
 * System.exit(0); // List<Continent> continets = new LinkedList<Continent>();
 * // Scanner in = new Scanner(System.in); mapBuild = new MapBuilder();
 * 
 * mapBuild.loadMap("ameroki");
 * 
 * mapBuild.printContinentList(); System.exit(0);
 * 
 * Continent continent1 = new Continent("NorthAmerica", 55); Continent
 * continent2 = new Continent("SouthAmerica", 222); Continent continent3 = new
 * Continent("ASIA", 233); Continent continent4 = new Continent("Europ",
 * 898989);
 * 
 * Country country1 = new Country("Iran"); Country country2 = new
 * Country("China");
 * 
 * mapBuild.addContinent(continent1); mapBuild.addContinent(continent2);
 * mapBuild.addContinent(continent3); mapBuild.addContinent(continent4);
 * 
 * // mapBuild.printContinentList();
 * 
 * // continent1.addContinentAdjacency(233); //
 * continent1.addContinentAdjacency(222);
 * 
 * // continent1.addCountry(country1); // continent2.addCountry(country2);
 * mapBuild.printContinentList();
 * 
 * mapBuild.writeMap("test");
 * 
 * // System.out.println("-------" + "\n" +
 * "which  continent do u wanna remove? "); // String name = in.nextLine();
 * 
 * // mapBuild.removeContinent("NorthAmerica");
 * 
 * // mapBuild.removeContinent(name);
 * 
 * // mapBuild.printContinentList();
 * 
 * System.exit(0);
 * 
 * // String input = in.nextLine(); // String[] arr = input.split(" "); //
 * System.out.println(arr[1]);
 * 
 * // switch (input) { // case "-add continentname" : // { // // } // case "
 * 
 * // switch (arr[0]) { // // case "editcontinent": // if
 * (arr[1].equals("-add")) { // Continent continent = new Continent(arr[2],
 * Integer.parseInt(arr[3])); // mapBuild.addContinent(continent); // } else if
 * (arr[4].equals("-remove")) { // // mapBuild.removeContinent(arr[4]); // // }
 * // break; // // } // switch (arr[1]) { // // case "editcountry": // // if
 * (arr[1].equals("-add")) { // Country country = new Country(arr[2]); //
 * mapBuild.addCountry(country); // } // // if (arr[4].equals("-remove")) { //
 * // mapBuild.removeCountry(arr[4]); // } // break; // // }
 * mapBuild.printContinentList(); // System.exit(0);
 * 
 * // List<Country> countries = new ArrayList<>(); // Set<String>
 * adjacentCountries = new HashSet<>(); mapBuild.getListOfMaps();
 * 
 * // mapBuild.readMap("risk"); String mapContent = "name " + "mapname" +
 * "Map\n"; // "name test Map\r\n" + // "\r\n" + // "[files]\r\n" + //
 * "pic risk_pic.png\r\n" + // "map risk_map.gif\r\n" + // "crd risk.cards\r\n"
 * + // "prv risk.jpg\r\n" + // "\r\n" + // "[continents]\r\n" + //
 * "North-America 5 yellow\r\n" + // "South-America 2 green\r\n" + //
 * "Europe 5 blue\r\n" + // "Africa 3 orange\r\n" + // "Asia 7 pink\r\n" + //
 * "Oceania 2 red";
 * 
 * System.out.println("How many Continents you wanna add?");
 * 
 * int numberOfContinents = in.nextInt();
 * 
 * List<Continent> continentList = new LinkedList<Continent>();
 * 
 * System.out.println("Add the name of the continents"); for (int i = 0; i <
 * numberOfContinents; i++) { String a = in.nextLine();
 * 
 * // String pattern = "[a-zA-Z\\s]*"; String continentName = in.next(pattern);
 * int continentId = in.nextInt();
 * 
 * Continent cont = new Continent(continentName, continentId);
 * continentList.add(cont); } in.nextLine();
 * 
 * // continetsList.addAll(nameOfTheContinents[i]);
 * 
 * // System.out.println(c1.setContinents(nameOfTheContinents));
 * 
 * // c1.getContinents().add(countrytest);
 * 
 * //////////////////////////////////////////
 * 
 * // System.out.println("How many Countries you want to add?"); // int
 * numberOfCountries = in.nextInt(); // String nameOfTheCountries[] = null ;
 * 
 * // System.out.println("Add the name of the Countries"); // for (int i = 0; i
 * <= numberOfCountries; i++) {
 * 
 * // nameOfTheCountries[i] = in.nextLine();
 * 
 * // countrytest.getLinkedCountries().add(nameOfTheCountries); // }
 * 
 * // countrytest.getAdjacentCountries().add("test"); //
 * System.out.println(countrytest.toString());
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 */

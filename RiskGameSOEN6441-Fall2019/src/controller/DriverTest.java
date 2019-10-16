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


/**
* this is driver to check the functionality of the code
*
*/
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
		/* Test of new Functions */
		mapBuild.loadMap("test");
		System.out.println("\n-----------------------------\nNew Getter Functions\n-----------------------------");
		System.out.println(mapBuild.getCountryListNames("asia")); //Get CountryListNames
		System.out.println(mapBuild.getContinentOfCountry("iran"));
		System.out.println(mapBuild.getListOfBorders());
		/* End of Test of new Functions */
		
		
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

			
			// added multiple time editcontinent -add aa 1 -add bb 2
		      // add continent..
		      regex = "(?<=editcontinent)(.*)";
		      pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		      matcher = pattern.matcher(input);
		      String addText="";
		      if (matcher.find()) {
		        addText = matcher.group(1);
		      }
		      regex = "(-add ([\\w*\\_\\-]*) (\\d*))+";
		      pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		      matcher = pattern.matcher(addText);
		      while (matcher.find()) {
		        String continentName = matcher.group(2);
		        int continentValue = Integer.parseInt(matcher.group(3));
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


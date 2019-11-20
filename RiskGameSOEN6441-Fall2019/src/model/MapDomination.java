package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapDomination extends MapBuilder{

		/**
		 * This method reads the map files
		 */
		public boolean read(String fileName) throws Exception {

			File file = new File(mapFolder + "/" + fileName + ".map");
			if (!file.exists()) {
				System.out.println(fileName + " map file not found. Please try again");
				return false;
			}

			continentList = new HashMap<Integer, Continent>();
			setCountryAdjacency(new AdjacencyList());
			Continent.setContinentsCounter(0);

			BufferedReader bufferedReader = null;
			try {

				bufferedReader = new BufferedReader(new FileReader(file));

				StringBuffer stringBuffer = new StringBuffer();
				String fileContent = "";
				String line = null;

				while ((line = bufferedReader.readLine()) != null) {
					stringBuffer.append(line).append("\n");
					fileContent += line + "\n";
				}
				bufferedReader.close();
				String patternString = "(?<=\\[continents\\]\\s)([\\w\\_\\-]*\\s(\\d)*\\s(\\#\\w{6}|\\w*)\\s)*";
				Pattern pattern = Pattern.compile(patternString);
				Matcher matcher = pattern.matcher(fileContent);
				String continentLines = "";
				if (matcher.find()) {
					continentLines = matcher.group(0);
				}

				// Start getting continents
				System.out.println("---------Loading Continent-------------");
				patternString = "((([\\w\\_\\-]*)\\s(\\d{1,2})\\s((\\#\\w{6}|\\w*)))*\\s)";
				pattern = Pattern.compile(patternString);
				matcher = pattern.matcher(continentLines);
				int count = 0;
				String continentDetail = "";
				String continentName = "";
				String continentColor = "";
				String continentValue = "";

				while (matcher.find()) {
					count++;
					continentDetail = matcher.group();
					continentName = matcher.group(3);
					continentValue = matcher.group(4);
					continentColor = matcher.group(5);

					Continent continent = new Continent(continentName, Integer.parseInt(continentValue));
					addContinent(continent);
					System.out.println("Found Continent " + continent.getContinentId() + " " + continentName + "  Value="
							+ continentValue + " Color:" + continentColor);
				}

				// Start getting countries
				System.out.println("\n---------Loading Country-------------");
				patternString = "(?<=\\[countries\\]\\s)((\\d)*\\s([\\w\\_\\-])*\\s(\\d)*\\s(\\d)*\\s(\\d)*\\s)*";
				pattern = Pattern.compile(patternString);
				matcher = pattern.matcher(fileContent);
				String countryLines = "";
				if (matcher.find()) {
					countryLines = matcher.group(0);
				}
				// System.out.println(countryLines);
				patternString = "(((\\d)*)\\s(([\\w\\_\\-])*)\\s((\\d)*)\\s((\\d)*)\\s((\\d)*)\\s)";
				pattern = Pattern.compile(patternString);
				matcher = pattern.matcher(countryLines);
				count = 0;
				String countryDetail = "";
				int countryId = 0;
				String countryName = "";
				int continentId = 0;
				int countryL1 = 0;
				int countryL2 = 0;
				// System.out.println("Found "+matcher.groupCount()+" group for
				// countries\n-------------\n");
				while (matcher.find()) {
					count++;
					countryDetail = matcher.group();
					countryId = Integer.parseInt(matcher.group(2));
					countryName = matcher.group(4);
					continentId = Integer.parseInt(matcher.group(6));
					countryL1 = Integer.parseInt(matcher.group(8));
					countryL2 = Integer.parseInt(matcher.group(10));
					System.out.println("Found country countryId=" + countryId + " Name=" + countryName + "  in continentId="
							+ continentId + " L1=" + countryL1 + ", L2=" + countryL2);
					Country country = new Country(countryName, countryId);
					getContinent(continentId).addCountry(country);
					getCountryAdjacency().addVertex(countryId);
				}
				System.out.println("-------------------------------");

				// Start getting borders
				System.out.println("\n---------Loading Borders-------------");
				patternString = "(?<=\\[borders\\]\\s)(.*)[\\s\\S]*";
				pattern = Pattern.compile(patternString);
				matcher = pattern.matcher(fileContent);
				String borders = "";
				if (matcher.find()) {
					borders = matcher.group(0);
				}
				// System.out.println("\n--"+borders+"--\n");
				patternString = "((\\d+) (([\\d ])+))";
				pattern = Pattern.compile(patternString);
				matcher = pattern.matcher(borders);
				count = 0;
				countryId = 0;
				String adjCountries = "";
				// System.out.println("Found "+matcher.groupCount()+" group for
				// countries\n-------------\n");
				while (matcher.find()) {
					count++;
					countryDetail = matcher.group();
					countryId = Integer.parseInt(matcher.group(2));
					String adjCountriesContent = matcher.group(3);
					// System.out.println("\nFound countryId=" + countryId + " Adj=" +
					// adjCountriesContent);
					Country c = getCountryById(countryId);
					System.out.println("Add Adj for " + c.getCountryName() + " Adj=" + adjCountriesContent);
					String[] arrOfAdj = adjCountriesContent.split(" ");
					for (String adj : arrOfAdj)
						addCountryAdjacency(countryId, Integer.parseInt(adj));
				}

				System.out.println("-------------------------------");

			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
			} catch (Exception e) {
				System.out.println("Map is invalid");
			}
			return validateMap();
		}
		

	public void write() {
		System.out.println("Write Dom");
		
	}

}

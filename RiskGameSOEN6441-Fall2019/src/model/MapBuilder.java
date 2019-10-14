package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is MapBuilder Class to Edit Map
 * 
 * @author f_yazdan
 *
 */
public class MapBuilder {

	final File mapFolder = new File("./MapFiles");
	/**
	 * continentList
	 */
	private Map<Integer, Continent> continentList = new HashMap<Integer, Continent>(); // continentId=> continentName,
																						// continentValue,
																						// continentColor
	/**
	 * countryAdjacency
	 */
	private AdjacencyList countryAdjacency = new AdjacencyList();

	/**
	 * This method will return list of continents
	 * 
	 * @return continentList
	 */
	public Map<Integer, Continent> getContinentList() {
		return continentList;
	}

	/**
	 * 
	 * @param continentList
	 */
	public void setContinentList(Map<Integer, Continent> continentList) {
		this.continentList = continentList;
	}

	/**
	 * This method reads continent Id
	 */
	public String getContinentName(int continentId) {

		Continent continent = continentList.get(continentId);
		String continentName = continent.getContinentName();
		return continentName;
	}

	public Continent getContinent(int continentId) {

		Continent continent = continentList.get(continentId);
		return continent;
	}

	/**
	 * This method adds a new continent
	 * 
	 * @param continent object of Conintent class
	 */

	public void addContinent(Continent continent) {
		continentList.put(continent.getContinentId(), continent);
	}

	public void addContinent(String continentName, int continentValue) {
		Continent continent = new Continent(continentName, continentValue);
		continentList.put(continent.getContinentId(), continent);
	}

	/**
	 * This method removes a continent
	 * 
	 * @param continentid
	 */
	public void removeContinent(int continentId) {
		continentList.remove(continentId);
	}
	
	public void removeContinent(String continentName) {
		Continent continent=getContinent(continentName);
		continentList.remove(continent.getContinentId());
	}
	
	
	public Continent getContinent(String continentName) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			
		if(continent.getContinentName().equals(continentName)) {
			return continent;
			
		}
		
	}
		return null;
	}

	/**
	 * This method will print List of Continents
	 */

	public void showMap() {
		System.out.println("\nPrint Continent:\n------------------------");
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			System.out.println("continent name is " + continent.getContinentName() + ", continent Id is: "
					+ continent.getContinentId() + " , continent value is " + continent.getContinentControlValue());
			// continent.showContinentAdjacency();
			continent.printCountryList();
		}
		showCountryAdjacency();
		System.out.println("------------------------\n");

	}

	/**
	 * This method will print list of maps
	 */
	public void getListOfMaps() {

		for (final File fileEntry : mapFolder.listFiles()) {
			System.out.println(fileEntry.getName());
		}
	}

	/**
	 * 
	 * @param countryId
	 * @return
	 */
	public Country getCountryById(int countryId) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			Country country = c.getCountry(countryId);
			if (country != null) {
				return country;
			}
		}
		return null;
	}

	
	public Country getCountryByName(String countryName) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			Country country = c.getCountry(countryName);
			if (country != null) {
				return country;
			}
		}
		return null;
	}
	
	
	public Continent getContinentByCountryName(String countryName) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			System.out.println("Searching in "+continent.getContinentName());
			Country country = continent.getCountry(countryName);
			
			if (country != null) {
				return continent;
			}
		}
		return null;
	}
	/**
	 * This method will add a new country in adjacency list of country
	 * 
	 * @param countryId
	 * @param targetCountryId
	 */
	public void addCountryAdjacency(int countryId, int targetCountryId) {
		countryAdjacency.addEdge(countryId, targetCountryId);
	}

	/**
	 * This method shows country adjacency list
	 */
	public void showCountryAdjacency() {
		countryAdjacency.showListEdges();
	}

	/**
	 * This method reads the map files
	 */
	public boolean loadMap(String fileName) throws Exception {

		File file = new File(mapFolder + "/" + fileName + ".map");
		if (!file.exists()) {
			System.out.println(fileName + " map file not found. Please try again");
			return false;
		}

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
				countryAdjacency.addVertex(countryId);
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
				System.out.println("\nFound countryId=" + countryId + " Adj=" + adjCountriesContent);
				Country c = getCountryById(countryId);
				System.out.println("Add Adj for " + c.getCountryName());
				String[] arrOfAdj = adjCountriesContent.split(" ");
				for (String adj : arrOfAdj)
					addCountryAdjacency(countryId, Integer.parseInt(adj));
			}

			System.out.println("-------------------------------");

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		return true;
	}

	public String mapFormat(String fileName) {
		String mapCountries = "";
		String mapContent = "name " + fileName + " Map\r\n" + "\r\n" + "[files]\r\n" + "\r\n" + "[continents]\r\n";
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			mapContent += c.getContinentName() + " " + c.getContinentControlValue() + "\r\n";
			List<Country> countryList = c.getCountriesList();
			for (Country co : countryList) {
				mapCountries += co.getCountryId() + " " + co.getCountryName() + "\r\n";
			}
		}
		mapContent += "\r\n[countries]\r\n" + mapCountries;

		return mapContent;
	}

	public void writeMap(String fileName) throws Exception {

		File file = new File(mapFolder + "/" + fileName + ".map");

		BufferedWriter br = new BufferedWriter(new FileWriter(file));

		br.write(mapFormat(fileName));

		br.close();
	}

	public void addCountry(String countryName, String continentName) {
		Continent continent=getContinent(continentName);
		Country country=new Country(countryName); // Search if already exist , show error
		continent.addCountry(country);
		
	}

	public void removeCountry(String countryName) {
		Continent continent=getContinentByCountryName(countryName);
		
		if (continent != null) {
			continent.removeCountry(countryName);
			System.out.println("Country removed from "+continent.getContinentName());
		}else {
			System.out.println("Country "+countryName+" not found");
		}
		
	}

}

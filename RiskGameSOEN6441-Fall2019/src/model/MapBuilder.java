package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * MapBuilder Class to Edit Map
 * 
 * @author f_yazdan
 *
 */
public class MapBuilder {

	final File mapFolder = new File("./MapFiles");

	private List<Continent> continentList = new LinkedList<Continent>();
	private List<Country> countryList = new LinkedList<Country>();

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	/**
	 * 
	 * @return continentList
	 */
	public List<Continent> getContinentList() {
		return continentList;
	}

	/**
	 * 
	 * @param continentList
	 */
	public void setContinentList(List<Continent> continentList) {
		this.continentList = continentList;
	}

	public String getCountryName(int countryId) {
		String countryName = "";
		ListIterator list_Iter = countryList.listIterator();

		while (list_Iter.hasNext()) {

			Country country = (Country) list_Iter.next();
			if (countryId == country.getCountryId())
				countryName = country.getCountryName();

		}
		return countryName;
	}

	public int getCountryId(String countryName) {
		int countryId = 0;

		ListIterator list_Iter = countryList.listIterator();
		while (list_Iter.hasNext()) {
			Country country = (Country) list_Iter.next();
			if (countryName == country.getCountryName())
				countryId = country.getCountryId();
		}
		return countryId;
	}

	public String getContinentName(int continentId) {

		ListIterator list_Iter = continentList.listIterator();
		String continentName = "";
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();
			if (continentId == continent.getContinentId())
				continentName = continent.getContinentName();
		}
		return continentName;

	}

	public int getContinentId(String continentName) {

		ListIterator list_Iter = continentList.listIterator();
		int continentId = 0;
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();
			if (continentName == continent.getContinentName())
				continentId = continent.getContinentId();
		}
		return continentId;

	}

	public void addContinent(Continent c) {
		continentList.add(c);
	}

	public void removeContinent(String continentName) {

		ListIterator list_Iter = continentList.listIterator();
		int continentId = 0;
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();
			if (continentName == continent.getContinentName())
				continentList.remove(continent);
		}
	}

	public void addCountry(Country c) {
		countryList.add(c);
		}
	
	public void removeCountry(String countryName) {
		if (countryList.contains(countryName)) {
			countryList.remove(countryName);
		}
	}
	
	
	/**
	 * Print List of Continents
	 */

	public void printContinentList() {

		ListIterator list_Iter = continentList.listIterator();
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();

			System.out.println("continent name is" + continent.getContinentName() + ", continent value is: "
					+ continent.getContinentId());
			continent.showContinentAdjacency();
		}
		
		System.out.println("------------------------");

	}

	public void getListOfMaps() {

		for (final File fileEntry : mapFolder.listFiles()) {
			// if (fileEntry.isDirectory()) {
			// getListOfMaps(fileEntry);
			// } else {
			System.out.println(fileEntry.getName());
		}
	}

	public void readMap(String fileName) throws Exception {

		File file = new File(mapFolder + "/" + fileName + ".map");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		while ((line = br.readLine()) != null) {
			// process the line
			System.out.println(line);
		}
		br.close();
	}

	public void writeMap(String fileName, String mapContent) throws Exception {

		File file = new File(mapFolder + "/" + fileName + ".map");

		BufferedWriter br = new BufferedWriter(new FileWriter(file));

		br.write(mapContent);

		br.close();
	}

}
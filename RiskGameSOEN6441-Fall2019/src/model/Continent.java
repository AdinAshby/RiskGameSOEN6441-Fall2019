package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 *
 */
public class Continent {
	
	/**
	 * private name attribute
	 */
	private String name;
	/**
	 * private continent Id
	 */
	private int continentId = 1;
	/**
	 * private continent Control Value
	 */
	private int continentControlValue;
	/**
	 * private static continents Counter
	 */
	private static int continentsCounter = 0;
	/**
	 * private countryList_list of the countries
	 */
	private List<Country> countryList = new LinkedList<Country>();

	/**
	 * This method returns the continent counter
	 * 
	 * @return continents Counter
	 */
	public static int getContinentsCounter() {
		return continentsCounter;
	}

	/**
	 * This method sets continents Counter
	 * 
	 * @param continentsCounter
	 */

	public static void setContinentsCounter(int continentsCounter) {
		Continent.continentsCounter = continentsCounter;
	}

	/**
	 * This is parameterized constructor for setting name and continentControlValue
	 * 
	 * @param name
	 * @param continentControlValue
	 */
	public Continent(String name, int continentControlValue) {
		super();
		this.name = name;
		this.continentControlValue = continentControlValue;
		continentsCounter++;
		this.continentId = continentsCounter;

		// continentAdjacency.addVertex(continentId);
	}

	/**
	 * This method returns the continent Control Value
	 * 
	 * @return continentControlValue
	 */
	public int getContinentControlValue() {
		return continentControlValue;
	}

	/**
	 * This method set the continent Control Value
	 * 
	 * @param continentControlValue
	 */

	public void setContinentControlValue(int continentControlValue) {
		this.continentControlValue = continentControlValue;
	}

	/**
	 * This method return the list of the countries
	 * 
	 * @return countryList
	 */
	public List<Country> getCountriesList() {
		return countryList;
	}

	/**
	 * This method sets country list
	 * 
	 * @param countriesList
	 */

	public void setCountriesList(LinkedList<Country> countriesList) {
		this.countryList = countriesList;
	}

	/**
	 * This return name of the continent
	 * 
	 * @return name
	 */
	public String getContinentName() {
		return name;
	}

	/**
	 * This method sets the ContinentName
	 * 
	 * @param name
	 */
	public void setContinentName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the ContinentId
	 * 
	 * @return continentId
	 */
	public int getContinentId() {
		return this.continentId;
	}

	/**
	 * this method sets the continentId
	 * 
	 * @param continentId
	 */
	public void setContinentId(int continentId) {
		this.continentId = continentId;
	}

	/**
	 * This method returns the country of specific CountryId
	 * 
	 * @param countryId
	 * @return country if there is any otherwise return false
	 */
	public Country getCountry(int countryId) {
		for (Country country : countryList) {
			if (country.getCountryId() == countryId) {
				return country;
			}
		}
		return null;
	}

	/**
	 * This method returns the country
	 * 
	 * @param countryName
	 * @return country if the name of the country we pass is the same as country
	 *         name otherwise return null
	 */
	public Country getCountry(String countryName) {
		for (Country country : countryList) {
			if (country.getCountryName().equals(countryName)) {
				return country;
			}
		}
		return null;
	}

	/**
	 * This methods adds a new country in the existing country list
	 * 
	 * @param c
	 *            as Country
	 */
	public void addCountry(Country c) {
		countryList.add(c);
	}

	/**
	 * This method removes an existing country
	 * 
	 * @param countryName
	 *            name of country
	 * @Type String
	 */
	public void removeCountry(String countryName) {

		ListIterator list_Iter = countryList.listIterator();
		while (list_Iter.hasNext()) {

			Country country = (Country) list_Iter.next();

			if (country.getCountryName().equals(countryName)) {
				countryList.remove(country);
				break;
			}

		}
	}

	/**
	 * This method displays the list of countries
	 */
	public void printCountryList() {

		ListIterator list_Iter = countryList.listIterator();
		while (list_Iter.hasNext()) {

			Country country = (Country) list_Iter.next();

			System.out.println(
					"   Country name is " + country.getCountryName() + ", Country Id is: " + country.getCountryId());

		}

		System.out.println("------------------------");

	}

	/**
	 * This method return list containing the Countries names
	 * 
	 * @return countries
	 */
	public ArrayList<String> getCountryListNames() {

		ArrayList<String> countries = new ArrayList<String>();

		ListIterator list_Iter = countryList.listIterator();
		while (list_Iter.hasNext()) {

			Country country = (Country) list_Iter.next();
			countries.add(country.getCountryName());

		}

		return countries;

	}

}

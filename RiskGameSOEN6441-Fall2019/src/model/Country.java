package model;

import java.io.Serializable;

/**
 * This class defines countries in the map
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 */
public class Country implements Serializable{
	/**
	 * private name
	 */
	private String name;
	/**
	 * private country Id
	 */
	private int countryId;

	/**
	 * private continent Name
	 */
	private String continentName;
	/**
	 * private number of armies
	 */
	private int armies;
	/**
	 * private static countries Counter
	 */
	private static int countriesCounter = 0;
	/**
	 * private player name
	 */
	private String playerName;
	/**
	 * private adjacent Country ID
	 */
	private int[] adjacentCountriesID;

	
	/**
	 *This is  constructor for initializing Country name
	 * 
	 * @param name
	 */
	public Country(String name) {
		this.countryId = ++countriesCounter;
		this.name = name;
		this.playerName = null;
		this.adjacentCountriesID = null;
		this.continentName = null;
		this.armies = 0;
	}

	/**
	 * This is constructor for Country name and countryId
	 * 
	 * @param name
	 * @param countryId
	 */

	public Country(String name, int countryId) {
		++countriesCounter;
		this.countryId = countryId;
		this.name = name;
		this.playerName = null;
		this.adjacentCountriesID = null;
		this.continentName = null;
		this.armies = 0;
	}

	/**
	 *This static method  resets Counter Country
	 */
	public static void resetCountryCounter() {
		countriesCounter = 0;
	}

	/**
	 * This methods returns the contryId
	 * @return countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 *  This method  set the country id
	 * 
	 * @param countryId
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * This method gets the continent Name
	 * @return continent Name
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * This method set the name of the Continent
	 * 
	 * @param continentName
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * This method returns number of armies in a country
	 * 
	 * @return integer
	 */
	public int getArmies() {
		return armies;
	}

	/**
	 * This set the number of armies
	 * 
	 * @param armies
	 */

	public void setArmies(int armies) {
		this.armies = armies;
	}

	/**
	 * This method returns name of the country
	 * 
	 * @return name
	 */
	public String getCountryName() {
		return name;
	}

	/**
	 * This method return name of the player
	 * 
	 * @return playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * This method set the name of the player
	 * 
	 * @param playerName
	 */
	public void setPlayer(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * This method returns the id of the adjacent Country
	 * 
	 * @return adjacentCountriesID
	 */
	public int[] getAdjacentCountriesID() {
		return adjacentCountriesID;
	}

	
	
	
	/**
	 * This method sets the id of the adjacent Country
	 * 
	 * @param listOfAdjacencyLists
	 */
	public void setAdjacentCountriesID(int[] listOfAdjacencyLists) {
		this.adjacentCountriesID = listOfAdjacencyLists;
	}

	

}

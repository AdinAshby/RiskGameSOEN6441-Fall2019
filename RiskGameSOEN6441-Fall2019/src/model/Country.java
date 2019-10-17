package model;

/**
 * This class defines countries in the map
 */
public class Country {
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
	private int armies = 0;
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

	//	public Set<String> getAdjacentCountries() {
	//		return countryAdjacency;
	//	}
	//
	//	public void setAdjacentCountries(Set<String> adjacentCountries) {
	//		this.countryAdjacency = adjacentCountries;
	//	}
/**
 * constructor Country
 * @param name
 */
	public Country(String name) {
		this.countryId = ++countriesCounter;
		this.name = name;
		this.playerName = null;
		this.adjacentCountriesID = null;
		this.continentName = null;
	}
/**
 * constructor Country
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
	}
/**
 * static reset Counter Country
 */
	public static void resetCountryCounter() {
		countriesCounter = 0;
	}
/**
 * 
 * @return countryId
 */
	public int getCountryId() {
		return countryId;
	}
/**
 * set the country id
 * @param countryId
 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	/**
	 * 
	 * @return continent Name
	 */
	public String getContinentName() {
		return continentName;
	}
/**
 * set the name of the Continent
 * @param continentName
 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	
	/**
	 * It return number of armies in a country
	 *@return integer
	 */
	public int getArmies() {
		return armies;
	}
	/**
	 * it set the number of armies
	 * @param armies
	 */

	public void setArmies(int armies) {
		this.armies = armies;
	}
/**
 * return name of the country
 * @return name
 */
	public String getCountryName() {
		return name;
	}
/**
 * return name of the player
 * @return playerName
 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * set the name of the player
	 * @param playerName
	 */
	public void setPlayer(String playerName) {
		this.playerName = playerName;
	}
	/**
	 * return the id of the adjacent Country
	 * @return adjacentCountriesID
	 */
	public int[] getAdjacentCountriesID() {
		return adjacentCountriesID;
	}
	
	/**
	 * 	set the id of the adjacent Country

	 * @param listOfAdjacencyLists
	 */
	public void setAdjacentCountriesID(int[] listOfAdjacencyLists) {
		this.adjacentCountriesID = listOfAdjacencyLists;
	}
	

	//	  public void addCountryLink(String countryName) {
	//		  countryAdjacency.add(countryName);
	//	  }
	//
	//	  public List<String> getLinkedCountries() {
	//	    List<String> linkedCountriesList = new ArrayList<>(countryAdjacency);
	//
	//	    return linkedCountriesList;
	//	  }




}

package model;

/**
 * This class defines countries in the map
 */
public class Country {
	private String name;
	private int countryId;
	private String continentName;
	private int armies = 0;
	private static int countriesCounter = 0;
	private String playerName;
	private int[] adjacentCountriesID;

	//	public Set<String> getAdjacentCountries() {
	//		return countryAdjacency;
	//	}
	//
	//	public void setAdjacentCountries(Set<String> adjacentCountries) {
	//		this.countryAdjacency = adjacentCountries;
	//	}

	public Country(String name) {
		this.countryId = ++countriesCounter;
		this.name = name;
		this.playerName = null;
		this.adjacentCountriesID = null;
		this.continentName = null;
	}

	public Country(String name, int countryId) {
		++countriesCounter;
		this.countryId = countryId;
		this.name = name;
		this.playerName = null;
		this.adjacentCountriesID = null;
		this.continentName = null;
	}

	public static void resetCountryCounter() {
		countriesCounter = 0;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	
	/**
	 * This method will return number of armies a country have
	 *@Type integer
	 */
	public int getArmies() {
		return armies;
	}

	public void setArmies(int armies) {
		this.armies = armies;
	}

	public String getCountryName() {
		return name;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayer(String playerName) {
		this.playerName = playerName;
	}
	
	public int[] getAdjacentCountriesID() {
		return adjacentCountriesID;
	}
	
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

package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
* This class defines countries in the map
*/
public class Country {
	private String name;
	private int countryId;
	private int armies = 0;
	private static int countriesCounter = 0;

	
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
	  }
	public Country(String name, int countryId) {
		++countriesCounter;
	    this.countryId = countryId;
	    this.name = name;
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

	  public void setCountryName(String name) {
	    this.name = name;
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

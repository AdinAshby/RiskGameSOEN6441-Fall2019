package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Country {
	private String name;
	private int countryId;
	private int armies = 0;
	private static int countriesCounter = 0;
	private Set<String> adjacentCountries = new HashSet<>();
	
	public Set<String> getAdjacentCountries() {
		return adjacentCountries;
	}

	public void setAdjacentCountries(Set<String> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}

	public Country(String name) {
	    this.countryId = ++countriesCounter;
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

	  public int getArmies() {
	    return armies;
	  }

	  public void setArmies(int armies) {
	    this.armies = armies;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public void addLink(String countryName) {
		  adjacentCountries.add(countryName);
	  }

	  public List<String> getLinkedCountries() {
	    List<String> linkedCountriesList = new ArrayList<>(adjacentCountries);

	    return linkedCountriesList;
	  }
	 
}
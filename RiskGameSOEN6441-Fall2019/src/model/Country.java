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
	private AdjacencyList countryAdjacency = new AdjacencyList();
	
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

	  
		public void showCountryAdjacency() {
			countryAdjacency.showListEdges();
			
		}
}
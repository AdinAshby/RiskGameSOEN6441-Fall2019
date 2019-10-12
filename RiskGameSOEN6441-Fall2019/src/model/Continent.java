package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Continent {
	private String name;
	private int continentId=1;
	private int continentControlValue;
	private static int continentsCounter = 0;
	private List<Country> countryList = new LinkedList<Country>();

	
		public Continent(String name, int continentControlValue) {
		super();
		this.name = name;
		this.continentControlValue= continentControlValue;
		continentsCounter++;
		this.continentId =continentsCounter;
		
//		continentAdjacency.addVertex(continentId);
	}

	public int getContinentControlValue() {
			return continentControlValue;
		}

		public void setContinentControlValue(int continentControlValue) {
			this.continentControlValue = continentControlValue;
		}

	public List<Country> getCountriesList() {
		return countryList;
	}

	public void setCountriesList(LinkedList<Country> countriesList) {
		this.countryList = countriesList;
	}

	public String getContinentName() {
		return name;
	}

	public void setContinentName(String name) {
		this.name = name;
	}

	public int getContinentId() {
		return this.continentId;
	}

	public void setContinentId(int continentId) {
		this.continentId = continentId;
	}

	public Country getCountry(int countryId) {
		for (Country country : countryList) {
	        if (country.getCountryId()==countryId) {
	            return country;
	        }
	    }
	    return null;
	}
	
	
	
/*	
	public void addContinentAdjacency( int end) {
		continentAdjacency.addEdge(continentId, end);
	}

	
	public void showContinentAdjacency() {
		
		continentAdjacency.showListEdges();
	}
*/	
	/**
	 * 
	 * @param c as Country
	 */
	public void addCountry(Country c) {
		countryList.add(c);
	}


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
		
	public void printCountryList() {

		ListIterator list_Iter = countryList.listIterator();
		while (list_Iter.hasNext()) {

			Country country = (Country) list_Iter.next();

			System.out.println("   Country name is " + country.getCountryName() + ", Country Id is: "
					+ country.getCountryId());
			
		}

		System.out.println("------------------------");

	}


}




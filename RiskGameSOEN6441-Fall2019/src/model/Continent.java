package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
         /**
	 * This class is made for defining continents
	 */
public class Continent {
	/**
	 * private name attribute
	 */
	private String name;
	/**
	 * private continentId 
	 */
	private int continentId=1;
	private int continentControlValue;
	private static int continentsCounter = 0;
	private List<Country> countryList = new LinkedList<Country>();

	
		public static int getContinentsCounter() {
		return continentsCounter;
	}

	public static void setContinentsCounter(int continentsCounter) {
		Continent.continentsCounter = continentsCounter;
	}

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
/**
 * 
 * @param name
 */
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
	
	public Country getCountry(String countryName) {
		for (Country country : countryList) {
	        if (country.getCountryName().equals(countryName)) {
	            return country;
	        }
	    }
	    return null;
	}
	
	
	/**
	 * This methods adds a new country in the exisiting countery list
	 * @param c as Country
	 */
	public void addCountry(Country c) {
		countryList.add(c);
	}

       /**
	 * This method removes an existing country
	 * @param countryName name of country
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

			System.out.println("   Country name is " + country.getCountryName() + ", Country Id is: "
					+ country.getCountryId());
			
		}

		System.out.println("------------------------");

	}
	
	public ArrayList<String> getCountryListNames() {

		ArrayList<String> countries=new ArrayList<String>();
		
		ListIterator list_Iter = countryList.listIterator();
		while (list_Iter.hasNext()) {

			Country country = (Country) list_Iter.next();
			countries.add(country.getCountryName());
			
			
		}

		return countries;

	}


}




package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Continent {
	private String name;
	private int continentId;
	private static int continentsCounter = 0;
	private List<Country> countryList = new LinkedList<Country>();
	private enum color {
		RED, BLUE, YELLOW, GREEN
	};

	public Continent(String name, int continentId) {
		super();
		this.name = name;
		this.continentId = continentId;
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
		return continentId;
	}

	public void setContinentId(int continentId) {
		this.continentId = continentId;
	}


}




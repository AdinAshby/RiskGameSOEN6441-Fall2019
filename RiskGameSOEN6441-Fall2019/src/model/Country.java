package model;

import java.util.HashSet;
import java.util.Set;

public class Country {
	private String name;
	private int countryId;
	private int armies = 0;
	private int continentId;
	private Set<String> adjacentCountries = new HashSet<>();
}
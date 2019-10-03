package controller;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.AdjacencyList;
import model.Continent;
import model.Country;
import model.MapBuilder;

public class DriverTest {
	// editcontinent -add continentname continentvalue -remove continentname
	// editcountry -add countryname continentname -remove countryname
	// editneighbor -add countryname neighborcountryname -remove countryname
	// neighborcountryname

	public static void main(String[] args) throws Exception {

		// List<Continent> continets = new LinkedList<Continent>();
		Scanner in = new Scanner(System.in);
		MapBuilder mapBuild = new MapBuilder();

		

		Continent continent1 = new Continent("NorthAmerica", 55);
		Continent continent2 = new Continent("SouthAmerica", 222);
		Continent continent3 = new Continent("ASIA", 233);
		Continent continent4 = new Continent("Europ", 898989);
		
		Country country1 = new Country("Iran");
		Country country2 = new Country("China");
		

		mapBuild.addContinent(continent1);
		mapBuild.addContinent(continent2);
		mapBuild.addContinent(continent3);
		mapBuild.addContinent(continent4);
		mapBuild.printContinentList();
		continent1.addContinentAdjacency(233);
		continent1.addContinentAdjacency(222);
		mapBuild.printContinentList();
		
		
		mapBuild.addCountry(country1);
		mapBuild.addCountry(country2);
		mapBuild.printCountryList();

//		System.out.println("-------" + "\n" + "which  continent do u wanna remove? ");
//		String name = in.nextLine();

//		mapBuild.removeContinent("NorthAmerica");

//		mapBuild.removeContinent(name);

//		mapBuild.printContinentList();

		System.exit(0);

		String input = in.nextLine();
		String[] arr = input.split(" ");
		// System.out.println(arr[1]);

//		switch (arr[0]) {
//
//		case "editcontinent":
//			if (arr[1].equals("-add")) {
//				Continent continent = new Continent(arr[2], Integer.parseInt(arr[3]));
//				mapBuild.addContinent(continent);
//			} else if (arr[4].equals("-remove")) {
//
//				mapBuild.removeContinent(arr[4]);
//
//			}
//			break;
//
//		}
//		switch (arr[1]) {
//
//		case "editcountry":
//
//			if (arr[1].equals("-add")) {
//				Country country = new Country(arr[2]);
//				mapBuild.addCountry(country);
//			}
//
//			if (arr[4].equals("-remove")) {
//
//				mapBuild.removeCountry(arr[4]);
//			}
//			break;
//
//		}
		mapBuild.printContinentList();
		// System.exit(0);

		// List<Country> countries = new ArrayList<>();
		// Set<String> adjacentCountries = new HashSet<>();
		mapBuild.getListOfMaps();

		// mapBuild.readMap("risk");
		String mapContent = "name " + "mapname" + "Map\n";
		// "name test Map\r\n" +
		// "\r\n" +
		// "[files]\r\n" +
		// "pic risk_pic.png\r\n" +
		// "map risk_map.gif\r\n" +
		// "crd risk.cards\r\n" +
		// "prv risk.jpg\r\n" +
		// "\r\n" +
		// "[continents]\r\n" +
		// "North-America 5 yellow\r\n" +
		// "South-America 2 green\r\n" +
		// "Europe 5 blue\r\n" +
		// "Africa 3 orange\r\n" +
		// "Asia 7 pink\r\n" +
		// "Oceania 2 red";
		mapBuild.writeMap("test", mapContent);

		System.out.println("How many Continents you wanna add?");

		int numberOfContinents = in.nextInt();

		List<Continent> continentList = new LinkedList<Continent>();

		System.out.println("Add the name of the continents");
		for (int i = 0; i < numberOfContinents; i++) {
			String a = in.nextLine();

			String pattern = "[a-zA-Z\\s]*";
			String continentName = in.next(pattern);
			int continentId = in.nextInt();

			Continent cont = new Continent(continentName, continentId);
			continentList.add(cont);
		}
		in.nextLine();

		// continetsList.addAll(nameOfTheContinents[i]);

		// System.out.println(c1.setContinents(nameOfTheContinents));

		// c1.getContinents().add(countrytest);

		//////////////////////////////////////////

		// System.out.println("How many Countries you want to add?");
		// int numberOfCountries = in.nextInt();
		// String nameOfTheCountries[] = null ;

		// System.out.println("Add the name of the Countries");
		// for (int i = 0; i <= numberOfCountries; i++) {

		// nameOfTheCountries[i] = in.nextLine();

		// countrytest.getLinkedCountries().add(nameOfTheCountries);
		// }

		// countrytest.getAdjacentCountries().add("test");
		// System.out.println(countrytest.toString());

	}

}

/*
 * 
 * System.out.println("SOEN 6441_Project"+"\n" + "Domination");
 * System.out.println("__.__.__.__.__.__.__.__.__.__.__.__.__.__.__"+"\n");
 * System.out.println("Please choose an Item : "+"\n");
 * System.out.println("1- Edit Continent"+"\n");
 * System.out.println("2- Edit Country"+"\n");
 * System.out.println("3- Edit Neighbor"+"\n");
 * System.out.println("4- Show Map");
 * 
 * System.out.println("__.__.__.__.__.__.__.__.__.__.__.__.__.__.__"+"\n");
 * System.out.println("i- Load Map"); System.out.println("ii- Save Map");
 * System.out.println("iii- Edit Map"); System.out.println("iv- Validate Map");
 * System.out.println("v- gameplayer"+"\n");
 * System.out.println("__.__.__.__.__.__.__.__.__.__.__.__.__.__.__"+"\n");
 * 
 */

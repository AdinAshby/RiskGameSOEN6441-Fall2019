package test;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import model.*;
import controller.*;

import java.util.ArrayList;

import org.junit.Test;
/**
 * This testcase class tests start up phase 
 * @author s_shehna
 *
 */
   public class TestStartUp {

	MapGeo mapBuild = MapGeo.getInstance();
	MapDomination mapDomination = new MapDomination();
	MapConquest mapConquest = new MapConquest(mapDomination);

	ArrayList<String> playerNames = new ArrayList<String>();
	ArrayList<String> strategy = new ArrayList<String>();
	ArrayList<ArrayList<Integer>> countryList = new ArrayList<>();

	ArrayList<Integer> countryListOne = new ArrayList<>();
	ArrayList<Integer> countryListTwo = new ArrayList<>();
	ArrayList<Integer> countryListThree = new ArrayList<>();
	ArrayList<Integer> countryListFour = new ArrayList<>();
	ArrayList<Integer> countryListFive = new ArrayList<>();
	ArrayList<Integer> countryListSix = new ArrayList<>();
	ArrayList<Integer> countryListSeven = new ArrayList<>();

	Player playerOne = new Player("a", countryListOne, mapBuild);
	Player playerTwo = new Player("b", countryListTwo, mapBuild);
	Player playerThree = new Player("c", countryListThree, mapBuild);
	Player playerFour = new Player("d", countryListThree, mapBuild);
	Player playerFive = new Player("e", countryListFive, mapBuild);
	Player playerSix = new Player("f", countryListSix, mapBuild);
	Player playerSeven = new Player("g", countryListSeven, mapBuild);

	@Test
	public void initialArmiesForTwoPlayers() {

		Player twoPlayers[] = { playerOne, playerTwo };

		mapBuild.setPlayers(twoPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(40, armiesGot);

	}

	@Test
	public void initialArmiesForThreePlayers() {

		Player threePlayers[] = { playerOne, playerTwo, playerThree };

		mapBuild.setPlayers(threePlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(35, armiesGot);

	}

	@Test
	public void initialArmiesForFourPlayers() {

		Player fourPlayers[] = { playerOne, playerTwo, playerThree, playerFour };

		mapBuild.setPlayers(fourPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(30, armiesGot);

	}

	@Test
	public void initialArmiesForFivePlayers() {

		Player fivePlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive };

		mapBuild.setPlayers(fivePlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(25, armiesGot);

	}

	@Test
	public void initialArmiesForSixPlayers() {

		Player sixPlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix };

		mapBuild.setPlayers(sixPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(20, armiesGot);

	}

	@Test
	public void initialArmiesForSevenPlayers() {

		Player sevenPlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven };

		mapBuild.setPlayers(sevenPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(20, armiesGot);

	}

	@Test
	public void InvalidInitialArmies() {

		Player sevenPlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven };

		mapBuild.setPlayers(sevenPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertNotEquals(0, armiesGot);

	}

	@Test
	public void randomArmyAssignment() {
		playerNames.add("a");
		playerNames.add("b");

		// mapDomination.isDominationMap("test");
		mapDomination.read("valid_map");
		strategy.add("human");
		strategy.add("human");

		mapDomination.assigningPlayersToCountries(playerNames, strategy);
		mapBuild.placeAllArmies();

		for (int i = 0; i <= 1; i++) {
			ArrayList<Integer> countryList = mapBuild.getCountriesByPlayerName(playerNames.get(i));

			for (int c : countryList) {
				String countryName = mapBuild.getCountryNameById(c);
				Country country = new Country(countryName);

				Assert.assertNotEquals(0, country.getArmies());
			}
		}

	}
}

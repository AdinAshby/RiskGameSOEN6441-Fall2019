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

	MapGeo mapBuild;
	MapDomination mapDomination;
	MapConquest mapConquest;

	ArrayList<String> playerNames ;
	ArrayList<String> strategy;
	ArrayList<ArrayList<Integer>> countryList;

	ArrayList<Integer> countryListOne;
	ArrayList<Integer> countryListTwo ;
	ArrayList<Integer> countryListThree;
	ArrayList<Integer> countryListFour;
	ArrayList<Integer> countryListFive ;
	ArrayList<Integer> countryListSix ;
	ArrayList<Integer> countryListSeven;

	Player playerOne;
	Player playerTwo ;
	Player playerThree;
	Player playerFour;
	Player playerFive ;
	Player playerSix;
	Player playerSeven;
	@Before
	public void setup()
	{
		mapBuild = MapGeo.getInstance();
		mapDomination = new MapDomination();
		mapConquest = new MapConquest(mapDomination);
		playerNames = new ArrayList<String>();
		strategy = new ArrayList<String>();
		 countryList = new ArrayList<>();
		 countryListOne = new ArrayList<>();
		 countryListTwo = new ArrayList<>();
			countryListThree = new ArrayList<>();
			countryListFour = new ArrayList<>();
			countryListFive = new ArrayList<>();
			 countryListSix = new ArrayList<>();
		 countryListSeven = new ArrayList<>();
		 playerOne = new Player("a", countryListOne, mapBuild);
			 playerTwo = new Player("b", countryListTwo, mapBuild);
			playerThree = new Player("c", countryListThree, mapBuild);
		 playerFour = new Player("d", countryListThree, mapBuild);
			 playerFive = new Player("e", countryListFive, mapBuild);
			 playerSix = new Player("f", countryListSix, mapBuild);
			 playerSeven = new Player("g", countryListSeven, mapBuild);

	}
	/**
	 * This testcase tests number of initial armies assigned when there are 2 players
	 * @author s_shehna
	 *
	 */
	@Test
	public void initialArmiesForTwoPlayers() {

		Player twoPlayers[] = { playerOne, playerTwo };

		mapBuild.setPlayers(twoPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(40, armiesGot);

	}
	/**
	 * This testcase tests number of initial armies assigned when there are 3 players
	 * @author s_shehna
	 *
	 */
	@Test
	public void initialArmiesForThreePlayers() {

		Player threePlayers[] = { playerOne, playerTwo, playerThree };

		mapBuild.setPlayers(threePlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(35, armiesGot);

	}
	/**
	 * This testcase tests number of initial armies assigned when there are 4 players
	 * @author s_shehna
	 *
	 */
	@Test
	public void initialArmiesForFourPlayers() {

		Player fourPlayers[] = { playerOne, playerTwo, playerThree, playerFour };

		mapBuild.setPlayers(fourPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(30, armiesGot);

	}
	/**
	 * This testcase tests number of initial armies assigned when there are 5 players
	 * @author s_shehna
	 *
	 */
	@Test
	public void initialArmiesForFivePlayers() {

		Player fivePlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive };

		mapBuild.setPlayers(fivePlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(25, armiesGot);

	}
	/**
	 * This testcase tests number of initial armies assigned when there are 6 players
	 * @author s_shehna
	 *
	 */
	@Test
	public void initialArmiesForSixPlayers() {

		Player sixPlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix };

		mapBuild.setPlayers(sixPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(20, armiesGot);

	}
	/**
	 * This testcase tests number of initial armies assigned when there are more than 6 players
	 * @author s_shehna
	 *
	 */
	@Test
	public void initialArmiesForSevenPlayers() {

		Player sevenPlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven };

		mapBuild.setPlayers(sevenPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertEquals(20, armiesGot);

	}
	/**
	 * This testcase tests coorect assignment of initial armies with invalid values
	 * @author s_shehna
	 *
	 */
	@Test
	public void InvalidInitialArmies() {

		Player sevenPlayers[] = { playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven };

		mapBuild.setPlayers(sevenPlayers);

		Player[] players = mapDomination.getPlayers();

		int armiesGot = mapBuild.calculateNumberOfInitialArmies();

		Assert.assertNotEquals(0, armiesGot);

	}
	/**
	 * This testcase tests random assignment of armies
	 * @author s_shehna
	 *
	 */
//	@Test
//	public void randomArmyAssignment() {
//		playerNames.add("a");
//		playerNames.add("b");
//
//		
//		mapDomination.read("test");
//		strategy.add("human");
//		strategy.add("human");
//
//		mapDomination.assigningPlayersToCountries(playerNames, strategy);
//		mapBuild.placeAllArmies();
//
//		for (int i = 0; i <= 1; i++) {
//			ArrayList<Integer> countryList = mapBuild.getCountriesByPlayerName(playerNames.get(i));
//
//			for (int c : countryList) {
//				String countryName = mapBuild.getCountryNameById(c);
//				Country country = new Country(countryName);
//
//				Assert.assertNotEquals(0, country.getArmies());
//			}
//		}
//
//	}
}

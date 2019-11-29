package test;
import java.util.*;
import org.junit.Assert.*;
import org.junit.Before;

import model.*;

import org.junit.Assert;
import org.junit.Test;
/**
 * This testcase class test the calculation of armies given to players
 * @author s_shehna
 *
 */
public class TestArmies {

	ArrayList<String> players = new ArrayList<String>();
	ArrayList<String> strategy = new ArrayList<String>();
	MapGeo mapBuild = MapGeo.getInstance();
	MapDomination mapDomination = new MapDomination();
	MapConquest mapConquest = new MapConquest(mapDomination);
	MapAdapter mapAdapter = new MapAdapter(mapConquest);
	ArrayList<Integer> countryListOne = new ArrayList<>();
	ArrayList<Integer> countryListTwo = new ArrayList<>();
	ArrayList<Integer> countryListThree = new ArrayList<>();
	ArrayList<Integer> countryListfour = new ArrayList<>();
/**
 * This testcase tests the number of armies given to only player in game
 *
 */
	
	
	@Test
	public void testArmiesOwnedByPlayerOne() throws Exception {
		mapDomination.read("test");
		players.add("a");
		strategy.add("human");
		mapBuild.assigningPlayersToCountries(players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
			
		myPlayers[0].calculateNumberOfArmiesEachPlayerGets();
		int armies_owned_by_playerOne = myPlayers[0].getNumberOfArmiesEachPlayerGets();
	
		Assert.assertEquals(3, armies_owned_by_playerOne);	 
}
	/**
	 * This testcase tests the number of armies given to two players in game
	 * 
	 */
	@Test
	public void testArmiesOwnedByPlayerTwo() throws Exception {
		mapDomination.read("ameroki"); 
		players.add("a");
		players.add("b");
		strategy.add("human");
		strategy.add("human");
		mapBuild.assigningPlayersToCountries(players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryTwo = myPlayers[1].getCountryIDs();
		Player playerOne = new Player("a", countryOne , mapBuild);
		Player playerTwo = new Player("b", countryTwo , mapBuild);
		
	    playerOne.calculateNumberOfArmiesEachPlayerGets();
		playerTwo.calculateNumberOfArmiesEachPlayerGets();
		int armiesOwnedByPlayerOne = playerOne.getNumberOfArmiesEachPlayerGets();
		int armiesOwnedByPlayerTwo = playerTwo.getNumberOfArmiesEachPlayerGets();
		
		Assert.assertEquals(3, armiesOwnedByPlayerOne);	
		Assert.assertEquals(3, armiesOwnedByPlayerTwo);	
	}
//	/**
//	 * This testcase tests the calculation for number of armies when there are 3 players
//	 * 
//	 */
//	@Test
//	public void testArmiesOwnedByPlayer3() throws Exception {
//		mapConquest.readConquest("ameroki");
//		players.add("a");
//		players.add("b");
//		players.add("c");
//      strategy.add("human");
//	    strategy.add("human");
//      strategy.add("human");
	
//		mapBuild.assigningPlayersToCountries(players, strategy);
//		mapBuild.calculateNumberOfArmiesEachPlayerGets();
//		int armiesOwnedByPlayerOne = mapBuild.getNumberOfArmiesEachPlayerGets();
//		mapBuild.calculateNumberOfArmiesEachPlayerGets();
//		int armiesOwnedByPlayerTwo = mapBuild.getNumberOfArmiesEachPlayerGets();
//		mapBuild.calculateNumberOfArmiesEachPlayerGets();
//		int armiesOwnedByPlayerThree = mapBuild.getNumberOfArmiesEachPlayerGets();
//		Assert.assertEquals(4, armiesOwnedByPlayerOne);	
//		Assert.assertEquals(4, armiesOwnedByPlayerTwo);	
//		Assert.assertEquals(4, armiesOwnedByPlayerThree);	
//	}

}


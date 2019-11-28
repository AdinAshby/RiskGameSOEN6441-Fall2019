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
//		countryListOne.add(1);
//		countryListOne.add(2);
//		countryListOne.add(3);
//		countryListOne.add(4);
//		countryListOne.add(5);
//		countryListOne.add(6);
		//Player player = new Player("a", countryListOne , mapBuild);
		//Player onePlayer[] = {player};
		//mapBuild.setPlayers(onePlayer);
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
	public void testArmiesOwnedByPlayer2() throws Exception {
		mapDomination.read("ameroki"); // total 42 countries in map
		players.add("a");
		players.add("b");
		strategy.add("human");
		strategy.add("human");
//		countryListOne.add(1);
//		countryListOne.add(2);
//		countryListOne.add(3);
//		countryListOne.add(4);
//		countryListOne.add(5);
//		countryListOne.add(6);
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
		
		Assert.assertEquals(7, armiesOwnedByPlayerOne);	
		Assert.assertEquals(7, armiesOwnedByPlayerTwo);	
	}
//	/**
//	 * This testcase tests the calculation for number of armies when there are 3 players
//	 * 
//	 */
//	@Test
//	public void testArmiesOwnedByPlayer3() throws Exception {
//		mapConquest.readConquest("ameroki");
//		players.add("Shehnaz");
//		players.add("Golnoosh");
//		players.add("Aiden");
//		mapBuild.assigningPlayersToCountries(players);
//		mapBuild.calculateNumberOfArmiesEachPlayerGets("Shehnaz");
//		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
//		mapBuild.calculateNumberOfArmiesEachPlayerGets("Golnoosh");
//		int armies_owned_by_player2 = mapBuild.getNumberOfArmiesEachPlayerGets();
//		mapBuild.calculateNumberOfArmiesEachPlayerGets("Aiden");
//		int armies_owned_by_player3 = mapBuild.getNumberOfArmiesEachPlayerGets();
//		Assert.assertEquals(4, armies_owned_by_player1);	
//		Assert.assertEquals(4, armies_owned_by_player2);	
//		Assert.assertEquals(4, armies_owned_by_player3);	
//	}
//	/**
//	 * This testcase tests the calculation for number of armies when there are 4 players
//	 * 
//	 */
//	@Test
//	public void testArmiesOwnedByPlayer4() throws Exception {
//		mapConquest.readConquest("ameroki");
//		players.add("Shehnaz");
//		players.add("Golnoosh");
//		players.add("Aiden");
//		players.add("Babita");
//		mapBuild.assigningPlayersToCountries(players);
//		mapBuild.calculateNumberOfArmiesEachPlayerGets("Shehnaz");
//		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
//		mapBuild.calculateNumberOfArmiesEachPlayerGets("Golnoosh");
//		int armies_owned_by_player2 = mapBuild.getNumberOfArmiesEachPlayerGets();
//		mapBuild.calculateNumberOfArmiesEachPlayerGets("Aiden");
//		int armies_owned_by_player3 = mapBuild.getNumberOfArmiesEachPlayerGets();
//		mapBuild.calculateNumberOfArmiesEachPlayerGets("Babita");
//		int armies_owned_by_player4 = mapBuild.getNumberOfArmiesEachPlayerGets();
//		Assert.assertEquals(3, armies_owned_by_player1);	
//		Assert.assertEquals(3, armies_owned_by_player2);	
//		Assert.assertEquals(3, armies_owned_by_player3);
//		Assert.assertEquals(3, armies_owned_by_player4);
//	}
}

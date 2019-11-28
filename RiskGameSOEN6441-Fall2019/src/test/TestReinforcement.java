package test;

import java.util.ArrayList;
import java.util.Random;


import org.junit.Assert;

import org.junit.Test;

import model.MapConquest;
import model.MapDomination;
import model.MapGeo;

import model.Player;
/**
 * This testcase class test the reinforcement phase
 * @s_shehna
 */
public class TestReinforcement {
	
	ArrayList<String> players = new ArrayList<String>();
	ArrayList<String> strategy = new ArrayList<String>();
	MapGeo mapBuild = MapGeo.getInstance();
	MapDomination mapDomination = new MapDomination();
	MapConquest mapConquest = new MapConquest(mapDomination);
	/**
 * This testcase tests valid number of armies owned by players for reinforcement 
 *
 */
	@Test
	public void testReinforceArmiesValid() throws Exception {
		mapConquest.readConquest("ameroki");
		players.add("Shehnaz");
		players.add("Golnoosh");
		strategy.add("human");
		strategy.add("human");
		mapBuild.assigningPlayersToCountries(players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryListPlayerOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryListPlayerTwo = myPlayers[1].getCountryIDs();
		Player playerOne = new Player("Shehnaz", countryListPlayerOne, mapBuild);
		Player playerTwo = new Player("Golnoosh", countryListPlayerTwo, mapBuild);
		Random random = new Random();
		int RandomIdPlayerOne = random.nextInt(myPlayers[0].getCountryIDs().size());
		int RandomIdPlayerTwo = random.nextInt(myPlayers[1].getCountryIDs().size());
		String CountryOwnedByPlayerOne = mapBuild.getCountryNameById(myPlayers[0].getCountryIDs().get(RandomIdPlayerOne));
		String CountryOwnedByPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs().get(RandomIdPlayerTwo));
		Assert.assertEquals(true, playerOne.reinforceIsValid(mapBuild, CountryOwnedByPlayerOne, 6));
		Assert.assertEquals(true, playerTwo.reinforceIsValid(mapBuild, CountryOwnedByPlayerTwo, 6));	 
	}
	/**
	 * This testcase tests the reinforcement method for invalid number of armies owned by players
	 * @throws Exception
	 */
	@Test
	public void testReinforceArmiesInvalid() throws Exception {
		mapConquest.readConquest("ameroki");
		players.add("Shehnaz");
		players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryListPlayerOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryListPlayerTwo = myPlayers[1].getCountryIDs();
		Player playerOne = new Player("Shehnaz", countryListPlayerOne, mapBuild);
		Player playerTwo = new Player("Golnoosh", countryListPlayerTwo, mapBuild);
		Random random = new Random();
		int RandomIdPlayerOne = random.nextInt(myPlayers[0].getCountryIDs().size());
		int RandomIdPlayerTwo = random.nextInt(myPlayers[1].getCountryIDs().size());
		String CountryOwnedByPlayerOne = mapBuild.getCountryNameById(myPlayers[0].getCountryIDs().get(RandomIdPlayerOne));
		String CountryOwnedByPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs().get(RandomIdPlayerTwo));
		Assert.assertEquals(true, playerOne.reinforceIsValid(mapBuild, CountryOwnedByPlayerOne, 40));
		Assert.assertEquals(true, playerTwo.reinforceIsValid(mapBuild, CountryOwnedByPlayerTwo, 20));	 
	}
}

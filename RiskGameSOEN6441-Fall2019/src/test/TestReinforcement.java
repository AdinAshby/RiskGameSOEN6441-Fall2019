package test;

import java.util.ArrayList;
import java.util.Random;

import javax.activation.MailcapCommandMap;

import org.junit.Assert;
import org.junit.Before;
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
	
	ArrayList<String> players;
	ArrayList<String> strategy;
	MapGeo mapBuild;
	MapDomination mapDomination;
	MapConquest mapConquest;
	@Before
	public void setup()
	{
		players = new ArrayList<String>();
		strategy = new ArrayList<String>();
		mapBuild = MapGeo.getInstance();
		mapDomination = new MapDomination();
		mapConquest = new MapConquest(mapDomination);
		players.add("Shehnaz");
		players.add("Golnoosh");
		strategy.add("human");
		strategy.add("human");
	}
	/**
 * This testcase tests valid number of armies owned by players for reinforcement 
 *
 */
	@Test
	public void testReinforceArmiesValid() throws Exception {
		mapDomination.read("ameroki");
	
		mapBuild.assigningPlayersToCountries(players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryListPlayerOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryListPlayerTwo = myPlayers[1].getCountryIDs();
		Random random = new Random();
		int RandomIdPlayerOne = random.nextInt(myPlayers[0].getCountryIDs().size());
		//System.out.print("**********************"+myPlayers[0].getCountryIDs().size()+" ****"+myPlayers[0].getPlayerName());
		int RandomIdPlayerTwo = random.nextInt(myPlayers[1].getCountryIDs().size());
		String CountryOwnedByPlayerOne = mapBuild.getCountryNameById(myPlayers[0].getCountryIDs().get(RandomIdPlayerOne));
		String CountryOwnedByPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs().get(RandomIdPlayerTwo));
		Assert.assertEquals(true, myPlayers[0].reinforceIsValid(mapBuild, CountryOwnedByPlayerOne, 6));
		Assert.assertEquals(true, myPlayers[1].reinforceIsValid(mapBuild, CountryOwnedByPlayerTwo, 6));	 
	}
	/**
	 * This testcase tests the reinforcement method for invalid number of armies owned by players
	 * @throws Exception
	 */
	@Test
	public void testReinforceArmiesInvalid() throws Exception {
		mapDomination.read("ameroki");
		mapBuild.assigningPlayersToCountries(players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryListPlayerOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryListPlayerTwo = myPlayers[1].getCountryIDs();
		Random random = new Random();
		int RandomIdPlayerOne = random.nextInt(myPlayers[0].getCountryIDs().size());
		int RandomIdPlayerTwo = random.nextInt(myPlayers[1].getCountryIDs().size());
		String CountryOwnedByPlayerOne = mapBuild.getCountryNameById(myPlayers[0].getCountryIDs().get(RandomIdPlayerOne));
		String CountryOwnedByPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs().get(RandomIdPlayerTwo));
		Assert.assertEquals(true, myPlayers[0].reinforceIsValid(mapBuild, CountryOwnedByPlayerOne, 40));
		Assert.assertEquals(true, myPlayers[1].reinforceIsValid(mapBuild, CountryOwnedByPlayerTwo, 20));	 
	}
}

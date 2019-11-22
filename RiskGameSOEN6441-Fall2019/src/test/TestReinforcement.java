package test;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;

import org.junit.Test;

import model.MapGeo;

import model.Player;
/**
 * This testcase class test the reinforcement phase
 * @s_shehna
 */
public class TestReinforcement {
	
	ArrayList<String> players = new ArrayList<String>();
	MapGeo mapBuild = MapGeo.getInstance();
/**
 * This testcase tests valid number of armies owned by players for reinforcement 
 *
 */
	@Test
	public void testReinforceArmiesValid() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(players);
		Player[] myPlayers = mapBuild.getPlayers();
		
		Random random = new Random();
		
		int RandomID_Player1 = random.nextInt(myPlayers[0].getCountryIDs().length);
		int RandomID_Player2 = random.nextInt(myPlayers[1].getCountryIDs().length);
		String Country_Owned_By_Player1 = mapBuild.getCountryNameById(myPlayers[0].getCountryIDs()[RandomID_Player1]);
		String Country_Owned_By_Player2 = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs()[RandomID_Player2]);
		Assert.assertEquals(true, mapBuild.reinforceIsValid("shehnaz", Country_Owned_By_Player1, 6));
		Assert.assertEquals(true, mapBuild.reinforceIsValid("Golnoosh", Country_Owned_By_Player2, 6));	 
	}
	/**
	 * This testcase tests the reinforcement method for invalid number of armies owned by players
	 * @throws Exception
	 */
	@Test
	public void testReinforceArmiesInvalid() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(players);
		Player[] myPlayers = mapBuild.getPlayers();
		
		Random random = new Random();

		int RandomID_Player1 = random.nextInt(myPlayers[0].getCountryIDs().length);
		int RandomID_Player2 = random.nextInt(myPlayers[1].getCountryIDs().length);
		String Country_Owned_By_Player1 = mapBuild.getCountryNameById(myPlayers[0].getCountryIDs()[RandomID_Player1]);
		String Country_Owned_By_Player2 = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs()[RandomID_Player2]);
		Assert.assertEquals(false, mapBuild.reinforceIsValid("shehnaz", Country_Owned_By_Player1, 40));
		Assert.assertEquals(false, mapBuild.reinforceIsValid("Golnoosh", Country_Owned_By_Player2, 20));
	}
}

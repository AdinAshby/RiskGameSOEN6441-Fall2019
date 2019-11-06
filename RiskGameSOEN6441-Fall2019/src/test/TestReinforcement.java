package test;

import java.util.ArrayList;

import org.junit.Assert;

import org.junit.Test;

import model.MapBuilder;

import model.Player;
/**
 * This testcase class test the reinforcement phase
 * @s_shehna
 */
public class TestReinforcement {
	
	ArrayList<String> players = new ArrayList<String>();
	MapBuilder mapBuild = new MapBuilder();

	@Test
	public void testReinforceArmiesValid() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		mapBuild.assigningPlayersToCountries(players);
		Assert.assertEquals(true, mapBuild.reinforceIsValid("shehnaz","india", 12));	 
	}
	@Test
	public void testReinforceArmiesInvalid() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		players.add("golnoosh");
		mapBuild.assigningPlayersToCountries(players);
		Assert.assertEquals(true, mapBuild.reinforceIsValid("shehnaz","india", 19));	 
	}
}

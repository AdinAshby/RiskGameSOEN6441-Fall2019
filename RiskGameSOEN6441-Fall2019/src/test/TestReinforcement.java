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
	//int countryID[] = {1,2,3,4,5,6,7,8,9};
	//Player player1 = new Player("shehnaz", countryID);
	ArrayList<String> players = new ArrayList<String>();
	MapBuilder mapBuild = new MapBuilder();

	@Test
	public void testArmies() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		//players.add("Adin");
		mapBuild.assigningPlayersToCountries(players);
		mapBuild.calculateNumberOfArmiesEachPlayerGets("shehnaz");
		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
		System.out.print(armies_owned_by_player1);
		Assert.assertEquals(14, armies_owned_by_player1);	 
	}

}

package test;

import org.junit.Assert.*;
import org.junit.BeforeClass;

import model.MapConquest;
import model.MapGeo;
import model.Player;

import java.util.ArrayList;

import javax.naming.InitialContext;

import org.junit.Test;
import org.junit.Assert;
/**
 * This testcase class tests the attack phase
 * @author s_shehna
 *
 */
public class TestAttack {
	MapGeo mapBuild = MapGeo.getInstance();
	MapConquest mapConquest = new MapConquest();
       /**
        * This testcase tests if attack is possible for two players with ameroki map
        * @throws Exception
        */
	
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<String> strategy = new ArrayList<String>();
		
	@Test
	public void testAttackValid() throws Exception {
		players.add("Shehnaz");
		players.add("Golnoosh");
		mapConquest.readConquest("ameroki");
		mapBuild.assigningPlayersToCountries(players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryListPlayerOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryListForPlayerTwo = myPlayers[1].getCountryIDs();
		Player playerOne = new Player("Shehnaz", countryListPlayerOne, mapBuild);
		Player playerTwo = new Player("Golnoosh", countryListForPlayerTwo, mapBuild);
		Assert.assertEquals(true, playerOne.isAttackPossible(mapBuild));
		Assert.assertEquals(true, playerTwo.isAttackPossible(mapBuild));
	}
}

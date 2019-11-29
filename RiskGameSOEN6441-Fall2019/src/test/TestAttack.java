package test;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

import model.Country;
import model.MapConquest;
import model.MapDomination;
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
	MapDomination mapDomination = new MapDomination();
	MapConquest mapConquest = new MapConquest(mapDomination);
	ArrayList<String> playerStrategies = new ArrayList<String>();
      
	
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<String> strategy = new ArrayList<String>();
	 /**
        * intializes the data for tests
        * @throws Exception
        */
		@Before
		public void setup()
		{
			playerNames.add("a");
			playerNames.add("b");

			playerStrategies.add("human");
			playerStrategies.add("human");
		}
		 /**
        * This testcase tests if attack is possible for two players 
        * @throws Exception
        */
	@Test
	public void testAttackValid() throws Exception {
		mapDomination.isDominationMap("test");

		mapDomination.assigningPlayersToCountries(playerNames, playerStrategies);
		mapDomination.showMap();
		mapDomination.placeAllArmies();

		mapDomination.showMap();
		Player[] players = mapDomination.getPlayers();
		Player playerOne = players[0];
		Player playerTwo = players[1];
		playerOne.calculateNumberOfArmiesEachPlayerGets();
		playerTwo.calculateNumberOfArmiesEachPlayerGets();
		playerOne.getNumberOfArmiesEachPlayerGets();
		playerTwo.getNumberOfArmiesEachPlayerGets();
		int attackerCountryId = playerOne.getCountryIDs().get(0);
		int fortifyCountryId = playerOne.getCountryIDs().get(1);
		int attackingCountryId = playerTwo.getCountryIDs().get(0);
		String attackerCountryName = mapDomination.getCountryNameById(attackerCountryId);
		String fortifyCountryName = mapDomination.getCountryNameById(fortifyCountryId);
		String attackingCountryName = mapDomination.getCountryNameById(attackingCountryId);
		Country attackerCountry = mapDomination.getCountryById(attackerCountryId);
		Country fortifyCountry = mapDomination.getCountryById(fortifyCountryId);
		Country attackingCountry = mapDomination.getCountryById(attackingCountryId);
		playerOne.reinforce(mapDomination, attackerCountryName, 3, false);
		
		if(attackerCountry.getArmies()>1 && mapDomination.isAdjacentCountry(attackerCountry.getCountryId(), attackingCountry.getCountryId())) {
		Assert.assertTrue(playerOne.isAttackValid(1, attackerCountry, attackingCountry, true));
		}else {
			Assert.assertFalse(playerOne.isAttackValid(1, attackerCountry, attackingCountry, true));	
		}
	}
}

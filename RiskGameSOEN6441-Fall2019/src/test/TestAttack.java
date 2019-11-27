package test;

import org.junit.Assert.*;
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
       /**
        * This testcase tests if attack is possible for two players with ameroki map
        * @throws Exception
        */
	
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<String> strategy = new ArrayList<String>();
		
	@Test
	public void testAttackValid() throws Exception {
		mapDomination.isDominationMap("test");

		playerNames.add("Shehnaz");
		playerNames.add("Golnoosh");

		playerStrategies.add("human");
		playerStrategies.add("human");

		mapDomination.assigningPlayersToCountries(playerNames, playerStrategies);
		mapDomination.showMap();
		mapDomination.placeAllArmies();

		mapDomination.showMap();
		Player[] players = mapDomination.getPlayers();
		Player player1 = players[0];
		Player player2 = players[1];
		player1.calculateNumberOfArmiesEachPlayerGets();
		player2.calculateNumberOfArmiesEachPlayerGets();
		player1.getNumberOfArmiesEachPlayerGets();
		player2.getNumberOfArmiesEachPlayerGets();
		int attackerCountryId = player1.getCountryIDs().get(0);
		int fortifyCountryId = player1.getCountryIDs().get(1);
		int attackingCountryId = player2.getCountryIDs().get(0);
		String attackerCountryName = mapDomination.getCountryNameById(attackerCountryId);
		String fortifyCountryName = mapDomination.getCountryNameById(fortifyCountryId);
		String attackingCountryName = mapDomination.getCountryNameById(attackingCountryId);
		Country attackerCountry = mapDomination.getCountryById(attackerCountryId);
		Country fortifyCountry = mapDomination.getCountryById(fortifyCountryId);
		Country attackingCountry = mapDomination.getCountryById(attackingCountryId);
		player1.reinforce(mapDomination, attackerCountryName, 3, false);
		//player1.attack(attackerCountry, attackingCountry, 3, 2, 0);
		if(attackerCountry.getArmies()>1 && mapDomination.isAdjacentCountry(attackerCountry.getCountryId(), attackingCountry.getCountryId())) {
		Assert.assertTrue(player1.isAttackValid(1, attackerCountry, attackingCountry, true));
		}else {
			Assert.assertFalse(player1.isAttackValid(1, attackerCountry, attackingCountry, true));	
		}
	}
}



package test;

import org.junit.Assert.*;

import model.Country;
import model.MapConquest;
import model.MapDomination;
import model.MapGeo;
import model.Player;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;
/**
 * This testcase class tests the attack phase
 * @author s_shehna
 *
 */
public class TestDefend {
        MapGeo mapBuild = MapGeo.getInstance();
        ArrayList<String> players = new ArrayList<String>();
        MapDomination mapDomination = new MapDomination();
    	MapConquest mapConquest = new MapConquest(mapDomination);
    	ArrayList<String> playerStrategies = new ArrayList<String>();
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<String> strategy = new ArrayList<String>();
       /**
        * This testcase tests if defend is possible for two players with ameroki map
        * @throws Exception
        */
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
    		int numDice=1;
    		System.out.println("Defend by: "+attackingCountry.getCountryName()+" Num Dice="+numDice);
    		if(attackingCountry.getArmies()>numDice || player1.getCountryIDs().contains(attackingCountry.getCountryId())) {
    		Assert.assertTrue(player1.isDefendPossible(attackingCountry.getCountryId(), numDice));
    		}else {
    			Assert.assertFalse(player1.isDefendPossible(attackingCountry.getCountryId(), numDice));	
    		}
        	
        	
        }
}  
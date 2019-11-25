

package test;

import org.junit.Assert.*;

import model.MapConquest;
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
        ArrayList<String> strategy = new ArrayList<String>();
MapConquest mapConquest = new MapConquest();
       /**
        * This testcase tests if defend is possible for two players with ameroki map
        * @throws Exception
        */
        @Test
        public void testAttackValid() throws Exception {
                mapConquest.readConquest("ameroki");
                players.add("Shehnaz");
                players.add("Golnoosh");
                mapBuild.assigningPlayersToCountries(players , strategy);
                Player[] myPlayers = mapBuild.getPlayers();
               ArrayList<Integer> countryListForPlayerOne = myPlayers[0].getCountryIDs();
               ArrayList<Integer> countryListForPlayerTwo = myPlayers[1].getCountryIDs();
                Player player1 = new Player("Shehnaz", countryListForPlayerOne, mapBuild);
                Player player2 = new Player("Golnoosh", countryListForPlayerTwo, mapBuild);
                Assert.assertEquals(false, player1.isDefendPossible(mapBuild, countryListForPlayerOne.get(0), 50));
                Assert.assertEquals(false, player2.isDefendPossible(mapBuild, countryListForPlayerOne.get(0), 1));
        }
}  
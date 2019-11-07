

package test;

import org.junit.Assert.*;

import model.MapBuilder;
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
        MapBuilder mapBuild = MapBuilder.getInstance();
        ArrayList<String> players = new ArrayList<String>();

       /**
        * This testcase tests if defend is possible for two players with ameroki map
        * @throws Exception
        */
        @Test
        public void testAttackValid() throws Exception {
                mapBuild.loadMap("ameroki");
                players.add("Shehnaz");
                players.add("Golnoosh");
                mapBuild.assigningPlayersToCountries(players);
                Player[] myPlayers = mapBuild.getPlayers();
                int[] countryListForPlayerOne = myPlayers[0].getCountryIDs();
                int[] countryListForPlayerTwo = myPlayers[1].getCountryIDs();
                Player player1 = new Player("Shehnaz",countryListForPlayerOne);
                Player player2 = new Player("Golnoosh", countryListForPlayerTwo);
                Assert.assertEquals(false, player1.isDefendPossible(mapBuild, countryListForPlayerOne[0], 50));
                Assert.assertEquals(false, player2.isDefendPossible(mapBuild, countryListForPlayerOne[0], 1));
        }
}  
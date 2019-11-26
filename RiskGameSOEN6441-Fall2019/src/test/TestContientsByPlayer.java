package test;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;
import model.MapConquest;
import model.MapGeo;
import model.Player;

public class TestContientsByPlayer {
	
	MapGeo mapBuild = MapGeo.getInstance();
	ArrayList<String> Players = new ArrayList<String>();
	ArrayList<String> playerContinents = new ArrayList<String>();
	ArrayList<String> mapContinents = new ArrayList<String>();
	ArrayList<String> strategy = new ArrayList<String>();
	MapConquest mapConquest = new MapConquest();
	@Test
	public void testContinents() throws Exception {
		mapContinents.add("azio");
		mapContinents.add("ameroki");
		mapContinents.add("utropa");
		mapContinents.add("amerpoll");
		mapContinents.add("afrori");
		mapContinents.add("ulstrailia");
		
		
		mapConquest.readConquest("ameroki");
		Players.add("Shehnaz");
		mapBuild.assigningPlayersToCountries(Players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		
		for(Player player : myPlayers) {
			mapBuild.setContinentNamesOfPlayer(player);
			playerContinents = player.getContinentsControlled();
			
			Assert.assertEquals(mapContinents, playerContinents);
		}
	}
}

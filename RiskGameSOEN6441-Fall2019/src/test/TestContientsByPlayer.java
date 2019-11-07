package test;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;
import model.MapBuilder;
import model.Player;

public class TestContientsByPlayer {
	
	MapBuilder mapBuild = MapBuilder.getInstance();
	ArrayList<String> Players = new ArrayList<String>();
	ArrayList<String> playerContinents = new ArrayList<String>();
	ArrayList<String> mapContinents = new ArrayList<String>();
	
	@Test
	public void test() throws Exception {
		mapContinents.add("azio");
		mapContinents.add("ameroki");
		mapContinents.add("utropa");
		mapContinents.add("amerpoll");
		mapContinents.add("afrori");
		mapContinents.add("ulstrailia");
		
		
		mapBuild.loadMap("ameroki");
		Players.add("Shehnaz");
		mapBuild.assigningPlayersToCountries(Players);
		Player[] myPlayers = mapBuild.getPlayers();
		
		for(Player player : myPlayers) {
			mapBuild.setContinentNamesOfPlayer(player);
			playerContinents = player.getContinentsControlled();
			
			Assert.assertEquals(mapContinents, playerContinents);
		}
	}
}

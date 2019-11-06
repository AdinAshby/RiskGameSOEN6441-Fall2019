package test;
import java.util.*;
import org.junit.Assert.*;
import model.*;

import org.junit.Assert;
import org.junit.Test;

public class TestArmies {

	ArrayList<String> players = new ArrayList<String>();
	MapBuilder mapBuild = new MapBuilder();

	@Test
	public void testArmiesOwnedByPlayer1() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		mapBuild.assigningPlayersToCountries(players);
		mapBuild.calculateNumberOfArmiesEachPlayerGets("shehnaz");
		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
		System.out.print(armies_owned_by_player1);
		Assert.assertEquals(14, armies_owned_by_player1);	 
}
	@Test
	public void testArmiesOwnedByPlayer2() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		players.add("golnoosh");
		mapBuild.assigningPlayersToCountries(players);
		mapBuild.calculateNumberOfArmiesEachPlayerGets("shehnaz");
		mapBuild.calculateNumberOfArmiesEachPlayerGets("golnoosh");
		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
		int armies_owned_by_player2 = mapBuild.getNumberOfArmiesEachPlayerGets();
		System.out.print(armies_owned_by_player1);
		Assert.assertEquals(7, armies_owned_by_player1);	
		Assert.assertEquals(7, armies_owned_by_player2);	
	}
}

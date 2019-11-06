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
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Shehnaz");
		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
	
		Assert.assertEquals(14, armies_owned_by_player1);	 
}
	@Test
	public void testArmiesOwnedByPlayer2() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(players);
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Shehnaz");
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Golnoosh");
		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
		int armies_owned_by_player2 = mapBuild.getNumberOfArmiesEachPlayerGets();
		
		Assert.assertEquals(7, armies_owned_by_player1);	
		Assert.assertEquals(7, armies_owned_by_player2);	
	}
	@Test
	public void testArmiesOwnedByPlayer3() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		players.add("Golnoosh");
		players.add("Aiden");
		mapBuild.assigningPlayersToCountries(players);
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Shehnaz");
		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Golnoosh");
		int armies_owned_by_player2 = mapBuild.getNumberOfArmiesEachPlayerGets();
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Aiden");
		int armies_owned_by_player3 = mapBuild.getNumberOfArmiesEachPlayerGets();
		Assert.assertEquals(4, armies_owned_by_player1);	
		Assert.assertEquals(4, armies_owned_by_player2);	
		Assert.assertEquals(4, armies_owned_by_player3);	
	}
	@Test
	public void testArmiesOwnedByPlayer4() throws Exception {
		mapBuild.loadMap("ameroki");
		players.add("Shehnaz");
		players.add("Golnoosh");
		players.add("Aiden");
		players.add("Babita");
		mapBuild.assigningPlayersToCountries(players);
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Shehnaz");
		int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Golnoosh");
		int armies_owned_by_player2 = mapBuild.getNumberOfArmiesEachPlayerGets();
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Aiden");
		int armies_owned_by_player3 = mapBuild.getNumberOfArmiesEachPlayerGets();
		mapBuild.calculateNumberOfArmiesEachPlayerGets("Babita");
		int armies_owned_by_player4 = mapBuild.getNumberOfArmiesEachPlayerGets();
		Assert.assertEquals(3, armies_owned_by_player1);	
		Assert.assertEquals(3, armies_owned_by_player2);	
		Assert.assertEquals(3, armies_owned_by_player3);
		Assert.assertEquals(3, armies_owned_by_player4);
	}
}

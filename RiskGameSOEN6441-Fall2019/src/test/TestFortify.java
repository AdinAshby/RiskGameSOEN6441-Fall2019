package test;

import java.util.ArrayList;

import org.junit.Assert.*;

import model.MapBuilder;
import model.Player;

import org.junit.Assert;
import org.junit.Test;

public class TestFortify {
	MapBuilder mapBuild = new MapBuilder();
	int[] CountryID = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
			27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42 };
	Player player1 = new Player("Shehnaz", CountryID);
	ArrayList<String> Players = new ArrayList<>();

	@Test
	public void testfortification() throws Exception {
		mapBuild.loadMap("ameroki");
		Players.add("Shehnaz");
		mapBuild.assigningPlayersToCountries(Players);
		mapBuild.reinforce("Shehnaz", "western_ulstrailia", 10);
		boolean result = mapBuild.fortifyIsValid(player1, "western_ulstrailia", "eastern_ulstarilia", 4);
		Assert.assertEquals(true, result);
	}

}

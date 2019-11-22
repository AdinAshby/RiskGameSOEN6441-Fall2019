package test;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert.*;

import model.MapGeo;
import model.Player;

import org.junit.Assert;
import org.junit.Test;
/**
 * This testcase class tests the fortification phase
 * @author s_shehna
 *
 */
public class TestFortify {
	MapGeo mapBuild = MapGeo.getInstance();
	ArrayList<String> Players = new ArrayList<>();
/**
 * This testcase test the fortify method for valid input
 * @throws Exception
 */
	@Test
	public void testfortificationValid() throws Exception {
		mapBuild.loadMap("ameroki");
		Players.add("Shehnaz");
		Players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(Players);
		Player[] myPlayers = mapBuild.getPlayers();
		int[] Country_List_Player1 = myPlayers[0].getCountryIDs();
		int[] Country_List_Player2 = myPlayers[1].getCountryIDs();
		Player player1 = new Player("Shehnaz", Country_List_Player1);
		Player player2 = new Player("Golnoosh", Country_List_Player2);
        ArrayList<String> countryList = new ArrayList<>();
        for(int i=0;i<Country_List_Player1.length;i++)
        {
        	countryList.add(mapBuild.getCountryNameById(Country_List_Player1[i]));
        }
		if(countryList.contains("western_ulstrailia") && countryList.contains("eastern_ulstarilia")  )
		{
			mapBuild.reinforce("Shehnaz", "western_ulstrailia" , 10);
			Assert.assertEquals(true, mapBuild.fortifyIsValid(player1, "western_ulstrailia" , "eastern_ulstarilia", 4));
		}
		else
		{
			mapBuild.reinforce("Golnoosh", "western_ulstrailia" , 10);
			Assert.assertEquals(true, mapBuild.fortifyIsValid(player1, "western_ulstrailia" , "eastern_ulstarilia", 4));
		}
		
	
	}
/**
 * This testcase tests the fortification method for invalid input
 * @throws Exception
 */


	@Test
	public void testfortificationInValid() throws Exception {
		mapBuild.loadMap("Test");
		Players.add("Shehnaz");
		Players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(Players);
		Player[] myPlayers = mapBuild.getPlayers();
		int[] countryListPlayerOne = myPlayers[0].getCountryIDs();
		int[] countryListPlayerTwo = myPlayers[1].getCountryIDs();
		Player player1 = new Player("Shehnaz", countryListPlayerOne);
		Player player2 = new Player("Golnoosh", countryListPlayerTwo);

		Random random = new Random();

		
		int randomIdPlayerTwoFromCountry = random.nextInt(myPlayers[1].getCountryIDs().length);
		int randomIdPlayerTwoToCountry = random.nextInt(myPlayers[1].getCountryIDs().length);
		String FromCountryPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs()[randomIdPlayerTwoFromCountry]);
		String ToCountryPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs()[randomIdPlayerTwoToCountry]); 
		Assert.assertEquals(false, mapBuild.fortifyIsValid(player1, FromCountryPlayerTwo,ToCountryPlayerTwo, 4));
	}


}

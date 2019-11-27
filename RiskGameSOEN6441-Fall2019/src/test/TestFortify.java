package test;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert.*;

import model.MapConquest;
import model.MapDomination;
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
	ArrayList<String> strategy = new ArrayList<>();
	MapDomination mapDomination = new MapDomination();
	MapConquest mapConquest = new MapConquest(mapDomination);
/**
 * This testcase test the fortify method for valid input
 * @throws Exception
 */
	@Test
	public void testfortificationValid() throws Exception {
		mapConquest.readConquest("ameroki");
		Players.add("Shehnaz");
		Players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(Players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryListPlayerOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryListPlayerTwo = myPlayers[1].getCountryIDs();
		Player playerOne = new Player("Shehnaz", countryListPlayerOne, mapBuild);
		Player playerTwo = new Player("Golnoosh", countryListPlayerTwo, mapBuild);
        ArrayList<String> countryList = new ArrayList<>();
        for(int i=0;i<countryListPlayerOne.size();i++)
        {
        	countryList.add(mapBuild.getCountryNameById(countryListPlayerOne.get(i)));
        }
		if(countryList.contains("western_ulstrailia") && countryList.contains("eastern_ulstarilia")  )
		{
			playerOne.reinforce(mapBuild, "western_ulstrailia" , 10, true);
			Assert.assertEquals(true, playerOne.fortifyIsValid("western_ulstrailia" , "eastern_ulstarilia", 4, mapBuild));
		}
		else
		{
			playerTwo.reinforce(mapBuild, "western_ulstrailia" , 10, true);
			Assert.assertEquals(true, playerTwo.fortifyIsValid( "western_ulstrailia" , "eastern_ulstarilia", 4, mapBuild));
		}
		
	
	}
/**
 * This testcase tests the fortification method for invalid input
 * @throws Exception
 */


	@Test
	public void testfortificationInValid() throws Exception {
		mapConquest.readConquest("Test");
		Players.add("Shehnaz");
		Players.add("Golnoosh");
		mapBuild.assigningPlayersToCountries(Players, strategy);
		Player[] myPlayers = mapBuild.getPlayers();
		ArrayList<Integer> countryListPlayerOne = myPlayers[0].getCountryIDs();
		ArrayList<Integer> countryListPlayerTwo = myPlayers[1].getCountryIDs();
		Player playerOne = new Player("Shehnaz", countryListPlayerOne, mapBuild);
		Player playerTwo = new Player("Golnoosh", countryListPlayerTwo, mapBuild);

		Random random = new Random();

		
		int randomIdPlayerTwoFromCountry = random.nextInt(myPlayers[1].getCountryIDs().size());
		int randomIdPlayerTwoToCountry = random.nextInt(myPlayers[1].getCountryIDs().size());
		String FromCountryPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs().get(randomIdPlayerTwoToCountry));
		String ToCountryPlayerTwo = mapBuild.getCountryNameById(myPlayers[1].getCountryIDs().get(randomIdPlayerTwoFromCountry)); 
		Assert.assertEquals(false,playerTwo.fortifyIsValid( FromCountryPlayerTwo,ToCountryPlayerTwo, 4, mapBuild));
	}


}

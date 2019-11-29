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
		mapDomination.read("ameroki");
		Players.add("Shehnaz");
		Players.add("Golnoosh");
		strategy.add("human");
		strategy.add("human");
		mapDomination.assigningPlayersToCountries(Players, strategy);
		Player[] myPlayers = mapDomination.getPlayers();
		ArrayList<Integer> countryListPlayerOne = mapDomination.getCountriesByPlayerName(myPlayers[0].getPlayerName());
		ArrayList<Integer> countryListPlayerTwo = mapDomination.getCountriesByPlayerName(myPlayers[1].getPlayerName());
		//Player playerOne = new Player("Shehnaz", countryListPlayerOne, mapDomination);
		//Player playerTwo = new Player("Golnoosh", countryListPlayerTwo, mapDomination);
        ArrayList<String> countryList = new ArrayList<>();
        for(int i=0;i<countryListPlayerOne.size();i++)
        {
        	countryList.add(mapDomination.getCountryNameById(countryListPlayerOne.get(i)));
        }
		if(countryList.contains("western_ulstrailia") && countryList.contains("eastern_ulstarilia")  )
		{
			myPlayers[0].reinforce(mapDomination, "western_ulstrailia" , 2, true);
			Assert.assertEquals(true, myPlayers[0].fortifyIsValid("western_ulstrailia" , "eastern_ulstarilia", 2, mapDomination));
		}
		else
		{
			myPlayers[1].reinforce(mapDomination, "western_ulstrailia" , 2, true);
			Assert.assertEquals(true, myPlayers[1].fortifyIsValid( "western_ulstrailia" , "eastern_ulstarilia", 2, mapDomination));
		}
		
	
	}
/**
 * This testcase tests the fortification method for invalid input
 * @throws Exception
 */


	@Test
	public void testfortificationInValid() throws Exception {
		mapDomination.read("ameroki");
		Players.add("Shehnaz");
		Players.add("Golnoosh");
		strategy.add("human");
		strategy.add("human");
		mapDomination.assigningPlayersToCountries(Players, strategy);
		Player[] myPlayers = mapDomination.getPlayers();
		ArrayList<Integer> countryListPlayerOne = mapDomination.getCountriesByPlayerName(myPlayers[0].getPlayerName());
		ArrayList<Integer> countryListPlayerTwo = mapDomination.getCountriesByPlayerName(myPlayers[1].getPlayerName());
		//Player playerOne = new Player("Shehnaz", countryListPlayerOne, mapBuild);
		//Player playerTwo = new Player("Golnoosh", countryListPlayerTwo, mapBuild);

		Random random = new Random();

		
		int randomIdPlayerTwoFromCountry = countryListPlayerTwo.get(random.nextInt(countryListPlayerTwo.size()));
		int randomIdPlayerTwoToCountry = countryListPlayerTwo.get(random.nextInt(countryListPlayerTwo.size()));
		String FromCountryPlayerTwo = mapDomination.getCountryNameById(countryListPlayerTwo.get(randomIdPlayerTwoToCountry));
		String ToCountryPlayerTwo = mapDomination.getCountryNameById(countryListPlayerTwo.get(randomIdPlayerTwoFromCountry)); 
		Assert.assertEquals(false,myPlayers[1].fortifyIsValid( FromCountryPlayerTwo,ToCountryPlayerTwo, 2, mapDomination));
	}


}

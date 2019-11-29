package test;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert.*;

import model.MapConquest;
import model.MapDomination;
import model.MapGeo;
import model.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * This testcase class tests the fortification phase
 * @author s_shehna
 *
 */
public class TestFortify {
	MapGeo mapBuild;
	ArrayList<String> Players;
	ArrayList<String> strategy;
	MapDomination mapDomination;
	MapConquest mapConquest;
	/**
	 * intializes data before testcases
	 */
	@Before
	public void setup()
	{
		mapBuild = MapGeo.getInstance();
		Players = new ArrayList<>();
		strategy = new ArrayList<>();
		mapDomination = new MapDomination();
		mapConquest = new MapConquest(mapDomination);
		Players.add("a");
		Players.add("b");
		strategy.add("human");
		strategy.add("human");
	}
/**
 * This testcase test the fortify method for valid input
 * @throws Exception
 */
	@Test
	public void testfortificationValid() throws Exception {
		mapDomination.read("ameroki");
		
		mapDomination.assigningPlayersToCountries(Players, strategy);
		Player[] myPlayers = mapDomination.getPlayers();
		ArrayList<Integer> countryListPlayerOne = mapDomination.getCountriesByPlayerName(myPlayers[0].getPlayerName());
		ArrayList<Integer> countryListPlayerTwo = mapDomination.getCountriesByPlayerName(myPlayers[1].getPlayerName());
	
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
		mapDomination.assigningPlayersToCountries(Players, strategy);
		Player[] myPlayers = mapDomination.getPlayers();
		ArrayList<Integer> countryListPlayerOne = mapDomination.getCountriesByPlayerName(myPlayers[0].getPlayerName());
		ArrayList<Integer> countryListPlayerTwo = mapDomination.getCountriesByPlayerName(myPlayers[1].getPlayerName());
		
		Random random = new Random();

		
		int randomIdPlayerTwoFromCountry = countryListPlayerTwo.get(random.nextInt(countryListPlayerTwo.size()));
		int randomIdPlayerTwoToCountry = countryListPlayerTwo.get(random.nextInt(countryListPlayerTwo.size()));
		String FromCountryPlayerTwo = mapDomination.getCountryNameById(countryListPlayerTwo.get(randomIdPlayerTwoToCountry));
		String ToCountryPlayerTwo = mapDomination.getCountryNameById(countryListPlayerTwo.get(randomIdPlayerTwoFromCountry)); 
		Assert.assertEquals(false,myPlayers[1].fortifyIsValid( FromCountryPlayerTwo,ToCountryPlayerTwo, 2, mapDomination));
	}


}

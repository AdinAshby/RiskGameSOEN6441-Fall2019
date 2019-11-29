package test;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;

import java.util.*;
import org.junit.Assert;
import org.junit.Before;

import model.MapGeo;
import model.Player;

/**
 * This test class tests the player class methods
 * @author s_shehna
 */
public class TestPlayer {
	/**
	 * int CountryId with correct initialization
	 */
	ArrayList<Integer> countryIdCorrect = new ArrayList<Integer>();
	
	/**
	 * int CountryId_incorrect with incorrect initialization
	 */

	ArrayList<Integer> counrtyIdInCorrect = new ArrayList<Integer>();
	
	/**
	 * object of the player
	 */
	MapGeo mapBuild = MapGeo.getInstance();
	
	/**
	 * initializes the data for player class testcases
	 */
	
	@Before
	public void setup()
	{
		countryIdCorrect.add(1);
		countryIdCorrect.add(2);
		countryIdCorrect.add(3);
		countryIdCorrect.add(4);
		counrtyIdInCorrect.add(12);
		counrtyIdInCorrect.add(42);
		counrtyIdInCorrect.add(32);
	}


	/**
	 * This is the test method for checking the valid playerName
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerNameValid() {
	Player playerOne = new Player("shehnaz", counrtyIdInCorrect, mapBuild);
	Assert.assertEquals("shehnaz", playerOne.getPlayerName());
		
	}
	/**
	 * This is the test method for checking the playerName for invalid name
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerNameInvalid() {

		Player playerOne = new Player("shehnaz", counrtyIdInCorrect, mapBuild);
	      Assert.assertNotEquals("golnoosh", playerOne.getPlayerName());
	}
	/**
	 * This is the test method for checking the valid CountryId of Player
	 * 
	 */
	@Test
	public void testgetCountryIdValid() {
		
		Player playerOne = new Player("shehnaz", counrtyIdInCorrect, mapBuild);
	     
	Assert.assertEquals("country Ids", counrtyIdInCorrect, playerOne.getCountryIDs());
		
	}
	/**
	 * This is the test method for checking the invalid CountryId for Player 
	 * 
	 */
	@Test
	public void testgetCountryIdInValid() {
	
		
		Player playerOne = new Player("shehnaz", countryIdCorrect, mapBuild);
		Assert.assertNotEquals(counrtyIdInCorrect, playerOne.getCountryIDs());
	}


}

package test;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;

import java.util.*;
import org.junit.Assert;


import model.MapGeo;
import model.Player;

/**
 * This test class tests the player class methods
 * @author s_shehna
 */
public class TestPlayer {
	/**
	 * int CountryId
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
	 * This is the test method for checking the valid playerName
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerNameValid() {
	countryIdCorrect.add(1);
	countryIdCorrect.add(2);
	countryIdCorrect.add(3);
	countryIdCorrect.add(4);
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
		countryIdCorrect.add(1);
		countryIdCorrect.add(2);
		countryIdCorrect.add(3);
		countryIdCorrect.add(4);
		Player playerOne = new Player("shehnaz", counrtyIdInCorrect, mapBuild);
	      Assert.assertNotEquals("golnoosh", playerOne.getPlayerName());
	}
	/**
	 * This is the test method for checking the valid CountryId of Player
	 * 
	 */
	@Test
	public void testgetCountryIdValid() {
		countryIdCorrect.add(1);
		countryIdCorrect.add(2);
		countryIdCorrect.add(3);
		countryIdCorrect.add(4);
		Player playerOne = new Player("shehnaz", counrtyIdInCorrect, mapBuild);
	     
	Assert.assertEquals("country Ids", counrtyIdInCorrect, playerOne.getCountryIDs());
		
	}
	/**
	 * This is the test method for checking the invalid CountryId for Player 
	 * 
	 */
	@Test
	public void testgetCountryIdInValid() {
		countryIdCorrect.add(1);
		countryIdCorrect.add(2);
		countryIdCorrect.add(3);
		countryIdCorrect.add(4);
		counrtyIdInCorrect.add(12);
		counrtyIdInCorrect.add(42);
		counrtyIdInCorrect.add(32);
		
		Player playerOne = new Player("shehnaz", counrtyIdInCorrect, mapBuild);
		Assert.assertEquals("Country Ids not same", counrtyIdInCorrect, playerOne.getCountryIDs());
	}


}

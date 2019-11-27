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
//int COUNTRYID_INCORRECT[] = { 1, 2, 3, 4, 35 };
	ArrayList<Integer> counrtyIdInCorrect = new ArrayList<Integer>();
	
	/**
	 * object of the player
	 */
	MapGeo mapBuild = MapGeo.getInstance();
//	Player playerOne = new Player("shehnaz", COUNTRYID_CORRECT, mapBuild);
	/**
	 * This is the test method for checking the valid playerName
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerNameValid() {
	
//		Assert.assertEquals("shehnaz", playerOne.getPlayerName());
		
	}
	/**
	 * This is the test method for checking the playerName for invalid name
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerNameInvalid() {
	//	Assert.assertNotEquals("golnoosh", player1.getPlayerName());
	}
	/**
	 * This is the test method for checking the valid CountryId of Player
	 * 
	 */
	@Test
	public void testgetCountryIdValid() {
	//	Assert.assertArrayEquals("country Ids", COUNTRYID_CORRECT, player1.getCountryIDs());
		
	}
	/**
	 * This is the test method for checking the invalid CountryId for Player 
	 * 
	 */
	@Test
	public void testgetCountryIdInValid() {
		
	//	Assert.assertNotSame("Country Ids not same", COUNTRYID_INCORRECT, player1.getCountryIDs());
	}


}

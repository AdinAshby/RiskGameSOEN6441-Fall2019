package test;

import org.junit.Test;
import org.junit.Assert;
import model.Player;

/**
 * This test class tests the player class methods
 * @author s_shehna
 */
public class TestPlayer {
	/**
	 * int CountryId
	 */
	int COUNTRYID_CORRECT[] = { 1, 2, 3, 4 };
	/**
	 * int CountryId_incorrect with incorrect initialization
	 */
	int COUNTRYID_INCORRECT[] = { 1, 2, 3, 4, 35 };
	/**
	 * object of the player
	 */
	Player player1 = new Player("shehnaz", COUNTRYID_CORRECT);
	/**
	 * This is the test method for checking the valid playerName
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerNameValid() {
		Assert.assertEquals("shehnaz", player1.getPlayerName());
		
	}
	/**
	 * This is the test method for checking the playerName for invalid name
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerNameInvalid() {
		Assert.assertNotEquals("golnoosh", player1.getPlayerName());
	}
	/**
	 * This is the test method for checking the valid CountryId of Player
	 * 
	 */
	@Test
	public void testgetCountryIdValid() {
		Assert.assertArrayEquals("country Ids", COUNTRYID_CORRECT, player1.getCountryIDs());
		
	}
	/**
	 * This is the test method for checking the invalid CountryId for Player 
	 * 
	 */
	@Test
	public void testgetCountryIdInValid() {
		
		Assert.assertNotSame("Country Ids not same", COUNTRYID_INCORRECT, player1.getCountryIDs());
	}


}

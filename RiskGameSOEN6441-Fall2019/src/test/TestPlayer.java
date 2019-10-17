package test;

import org.junit.Test;
import org.junit.Assert;
import model.Player;

/**
 * This test class tests the player class methods
 * 
 * @author s_shehna
 * @author Babita kaur
 *
 */
public class TestPlayer {
	/**
	 * int CountryId
	 */
	int CountryId[] = { 1, 2, 3, 4 };
	/**
	 * int CountryId_incorrect with incorrect initialization
	 */
	int CountryId_incorrect[] = { 1, 2, 3, 4, 35 };
	/**
	 * object of the player
	 */
	Player player1 = new Player("shehnaz", CountryId);
	/**
	 * This is the test method for checking the playerName
	 * 
	 * 
	 */
	@Test
	public void testgetPlayerName() {
		Assert.assertEquals("shehnaz", player1.getPlayerName());
		Assert.assertNotEquals("golnoosh", player1.getPlayerName());
	}
	/**
	 * This is the test method for checking the CountryId of Player
	 * 
	 * 
	 */
	@Test
	public void testgetCountryId() {
		Assert.assertArrayEquals("country Ids", CountryId, player1.getCountryIDs());
		Assert.assertNotSame("Country Ids not same", CountryId_incorrect, player1.getCountryIDs());
	}

}

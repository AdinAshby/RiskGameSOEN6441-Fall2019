package test;

import org.junit.Test;
import org.junit.Assert;
import model.Player;

/**
 * This test class tests the player class methods
 * 
 * @author s_shehna
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

	@Test
	public void testgetPlayerName() {
		Assert.assertEquals("shehnaz", player1.getPlayerName());
		Assert.assertNotEquals("golnoosh", player1.getPlayerName());
	}

	@Test
	public void testgetCountryId() {
		Assert.assertArrayEquals("country Ids", CountryId, player1.getCountryIDs());
		Assert.assertNotSame("Country Ids not same", CountryId_incorrect, player1.getCountryIDs());
	}

}

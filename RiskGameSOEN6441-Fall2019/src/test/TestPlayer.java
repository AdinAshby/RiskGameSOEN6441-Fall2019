package test;
import org.junit.Test;
import org.junit.Assert;
import model.Player;
/**
 * This test class tests the player class methods
 * @author s_shehna
 *
 */
public class TestPlayer {
	int CountryId[] = {1,2,3,4};
	int Country_Id[] = {1,2,3,4,35};
    Player player1 = new Player(1, "shehnaz", CountryId);
	@Test
	public void testgetPlayerId() {
		Assert.assertEquals(1, player1.getPlayerID());
		Assert.assertNotEquals(2, player1.getPlayerID());
	}
	@Test
	public void testgetPlayerName()
	{
		Assert.assertEquals("shehnaz", player1.getPlayerName());
		Assert.assertNotEquals("golnoosh", player1.getPlayerName());
	}
	@Test
	public void testgetCountryId()
	{
		Assert.assertArrayEquals("country Ids", CountryId, player1.getCountryID());
		Assert.assertNotSame("Country Ids not same",Country_Id , player1.getCountryID());
	}

}

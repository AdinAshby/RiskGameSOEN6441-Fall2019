package test;

import org.junit.Assert;

import org.junit.Test;

import model.MapBuilder;

import model.Player;
/**
 * This testcase class test the reinforcement phase
 * @s_shehna
 */
public class TestReinforcement {
 int countryID[] = {1,2,3,4,5,6,7,8,9};
	Player player1 = new Player("shehnaz", countryID);
	MapBuilder mapBuild = new MapBuilder();
	@Test
	public void testArmies() {
	 mapBuild.calculateNumberOfArmiesEachPlayerGets("shehnaz");
	 int armies_owned_by_player1 = mapBuild.getNumberOfArmiesEachPlayerGets();
	 System.out.print(armies_owned_by_player1);
	 Assert.assertEquals(3, armies_owned_by_player1);	 
	}

}

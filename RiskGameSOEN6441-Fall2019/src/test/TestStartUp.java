package test;

import org.junit.Assert;
import org.junit.Assert.*;
import model.*;
import controller.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestStartUp {

	MapGeo mapBuild = MapGeo.getInstance();
	MapDomination mapDomination = new MapDomination();
	
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<String> strategy = new ArrayList<String>();
		
		ArrayList<Integer> countryListOne = new ArrayList<>();
		ArrayList<Integer> countryListTwo = new ArrayList<>();
		ArrayList<Integer> countryListThree = new ArrayList<>();
		ArrayList<Integer> countryListFour = new ArrayList<>();
		ArrayList<Integer> countryListFive = new ArrayList<>();
		ArrayList<Integer> countryListSix = new ArrayList<>();
		ArrayList<Integer> countryListSeven = new ArrayList<>();
		
	@Test
	public void initialArmiesForTwoPlayers() {
		
Player playerOne = new Player("a", countryListOne, mapBuild);
Player playerTwo = new Player("b", countryListTwo, mapBuild);
//Player playerThree = new Player("c", countryListThree, mapBuild);
		
 Player twoPlayers[] = {playerOne, playerTwo};
 
 mapBuild.setPlayers(twoPlayers);

		Player[] players = mapDomination.getPlayers();
	
	
     	int armiesGot = mapBuild.calculateNumberOfInitialArmies();
		

        Assert.assertEquals(40, armiesGot);
        
	}
	@Test
	public void initialArmiesForThreePlayers() {
		
Player playerOne = new Player("a", countryListOne, mapBuild);
Player playerTwo = new Player("b", countryListTwo, mapBuild);
Player playerThree = new Player("c", countryListThree, mapBuild);
		
 Player threePlayers[] = {playerOne, playerTwo, playerThree};
 
 mapBuild.setPlayers(threePlayers);

		Player[] players = mapDomination.getPlayers();
	
	
     	int armiesGot = mapBuild.calculateNumberOfInitialArmies();
		

        Assert.assertEquals(35, armiesGot);
        
	}
	@Test
	public void initialArmiesForFourPlayers() {
		
Player playerOne = new Player("a", countryListOne, mapBuild);
Player playerTwo = new Player("b", countryListTwo, mapBuild);
Player playerThree = new Player("c", countryListThree, mapBuild);
Player playerFour = new Player("d", countryListFour, mapBuild);
		
 Player fourPlayers[] = {playerOne, playerTwo, playerThree, playerFour};
 
 mapBuild.setPlayers(fourPlayers);

		Player[] players = mapDomination.getPlayers();
	
	
     	int armiesGot = mapBuild.calculateNumberOfInitialArmies();
		

        Assert.assertEquals(30, armiesGot);
        
	}
	@Test
	public void initialArmiesForFivePlayers() {
		
Player playerOne = new Player("a", countryListOne, mapBuild);
Player playerTwo = new Player("b", countryListTwo, mapBuild);
Player playerThree = new Player("c", countryListThree, mapBuild);
Player playerFour = new Player("d", countryListFour, mapBuild);
Player playerFive = new Player("e", countryListFive, mapBuild);
		
 Player fivePlayers[] = {playerOne, playerTwo, playerThree, playerFour, playerFive};
 
 mapBuild.setPlayers(fivePlayers);

		Player[] players = mapDomination.getPlayers();
	
	
     	int armiesGot = mapBuild.calculateNumberOfInitialArmies();
		

        Assert.assertEquals(25, armiesGot);
        
	}
	@Test
	public void initialArmiesForSixPlayers() {
		
Player playerOne = new Player("a", countryListOne, mapBuild);
Player playerTwo = new Player("b", countryListTwo, mapBuild);
Player playerThree = new Player("c", countryListThree, mapBuild);
Player playerFour = new Player("d", countryListThree, mapBuild);
Player playerFive = new Player("e", countryListFive, mapBuild);
Player playerSix = new Player("f", countryListSix, mapBuild);
		
 Player sixPlayers[] = {playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix};
 
 mapBuild.setPlayers(sixPlayers);

		Player[] players = mapDomination.getPlayers();
	
	
     	int armiesGot = mapBuild.calculateNumberOfInitialArmies();
		

        Assert.assertEquals(20, armiesGot);
        
	}
	@Test
	public void initialArmiesForSevenPlayers() {
		
Player playerOne = new Player("a", countryListOne, mapBuild);
Player playerTwo = new Player("b", countryListTwo, mapBuild);
Player playerThree = new Player("c", countryListThree, mapBuild);
Player playerFour = new Player("d", countryListThree, mapBuild);
Player playerFive = new Player("e", countryListFive, mapBuild);
Player playerSix = new Player("f", countryListSix, mapBuild);
Player playerSeven = new Player("g", countryListSeven, mapBuild);
		
 Player sevenPlayers[] = {playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix, playerSeven};
 
 mapBuild.setPlayers(sevenPlayers);

		Player[] players = mapDomination.getPlayers();
	
	
     	int armiesGot = mapBuild.calculateNumberOfInitialArmies();
		

        Assert.assertEquals(20, armiesGot);
        
	}
	//@Test
//public void randomCountryAssignment()
//{
//	playerNames.add("a");
//	playerNames.add("b");
//	playerNames.add("c");
//	mapDomination.isDominationMap("test");
//
//
//
//	strategy.add("human");
//	strategy.add("human");
//	strategy.add("human");
//	
//	mapDomination.assigningPlayersToCountries(playerNames, strategy);
//	for(int i=0; i<=2; i++) {
//		
//		for(int j=0;j<=2;j++) {
//					
//	assertNotNull(playerNames.get(i).);
//
//	
//}
	
//}
//}
}
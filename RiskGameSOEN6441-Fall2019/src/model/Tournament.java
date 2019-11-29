package model;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Game;
import controller.Game.Phase;
import view.MapView;

 
/**
 * private listlistOfMapFiles 
 */

public class Tournament implements Serializable {


	private ArrayList<String> listOfMapFiles;
	/**
	 * private listOfPlayerStrategies
	 */
	private ArrayList<String> listOfPlayerStrategies;
	/**
	 * private numberOfGames
	 */
	private int numberOfGames;
	/**
	 * private maxNumberOfTurns
	 */
	private int maxNumberOfTurns;
	/**
	 * private mapView
	 */
	private MapView mapView = new MapView();
	/**
	 * private MapDomination
	 */
	private MapDomination mapDomination = new MapDomination();
	/**
	 * This is  constructor for initializing Tournament mode
	 * 
	 * @param listOfMapFiles
	 * @param listOfPlayerStrategies
	 * @param numberOfGames
	 * @param maxNumberOfTurns
	 */

	public Tournament(ArrayList<String> listOfMapFiles, ArrayList<String> listOfPlayerStrategies, int numberOfGames, int maxNumberOfTurns) {
		this.listOfMapFiles = listOfMapFiles;
		this.listOfPlayerStrategies = listOfPlayerStrategies;
		this.numberOfGames = numberOfGames;
		this.maxNumberOfTurns = maxNumberOfTurns;
	}
	/**
	 * This method for starting the game in tournament mode
	 * 
	 */
	public void startTheTournament() {
		ArrayList<String> results = new ArrayList<String>();
		Game[] games=new Game[numberOfGames]; 
		boolean somebodyWon = false;


		for (int mapCounter = 0; mapCounter < listOfMapFiles.size(); mapCounter++) {
			mapDomination.isDominationMap(listOfMapFiles.get(mapCounter));

			for (int gameCounter = 0; gameCounter < numberOfGames; gameCounter++) {
				Game game=new Game();
				games[gameCounter]=game;
				game.setMapGeo(mapDomination);
				
				mapDomination.assigningPlayersToCountries(listOfPlayerStrategies, listOfPlayerStrategies);

				for (int turnCounter = 0; turnCounter < maxNumberOfTurns; turnCounter++) {

					for(Player player : mapDomination.getPlayers()) {
						game.setTurnPlayer(player);
						player.calculateWorldDominationView();
						
						player.setCounterForPhases(1);
						game.setPhase(Phase.REINFORCEMENT);
						player.reinforceCommand(game, mapDomination, mapView);
						
						player.setCounterForPhases(2);
						game.setPhase(Phase.ATTACK);
						player.attackCommand(game, mapView);
						
						player.setCounterForPhases(3);
						game.setPhase(Phase.FORTIFICATION);
						player.fortifyCommand(game, mapDomination, mapView);
						
						mapDomination.showMap();
					}

				}

				for(Player player : mapDomination.getPlayers()) {
					if(player.getWon()) {
						somebodyWon = true;
						results.add(player.getPlayerName());
						game.setWinner(player);
					}
				}
				
				if(!somebodyWon) {
					results.add("Draw");
					somebodyWon = false;
				}
			}
		}

		mapView.showTournamentResult(results, numberOfGames, games, listOfMapFiles, listOfPlayerStrategies, maxNumberOfTurns);
		
	}

}

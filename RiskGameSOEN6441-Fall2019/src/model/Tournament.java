package model;

import java.io.Serializable;
import java.util.ArrayList;

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
		boolean somebodyWon = false;


		for (int mapCounter = 0; mapCounter < listOfMapFiles.size(); mapCounter++) {
			mapDomination.isDominationMap(listOfMapFiles.get(mapCounter));

			for (int gameCounter = 0; gameCounter < numberOfGames; gameCounter++) {
				mapDomination.assigningPlayersToCountries(listOfPlayerStrategies, listOfPlayerStrategies);

				for (int turnCounter = 0; turnCounter < maxNumberOfTurns; turnCounter++) {

					for(Player player : mapDomination.getPlayers()) {
						
						player.calculateWorldDominationView();
						
						player.setCounterForPhases(1);
						player.reinforceCommand(mapDomination, mapView);
						
						player.setCounterForPhases(2);
						player.attackCommand(mapView);
						
						player.setCounterForPhases(3);
						player.fortifyCommand(mapDomination, mapView);
						
						mapDomination.showMap();
					}

				}

				for(Player player : mapDomination.getPlayers()) {
					if(player.getWon()) {
						somebodyWon = true;
						results.add(player.getPlayerName());
					}
				}
				
				if(!somebodyWon) {
					results.add("Draw");
					somebodyWon = false;
				}
			}
		}

		mapView.showTournamentResult(results, numberOfGames);
	}

}

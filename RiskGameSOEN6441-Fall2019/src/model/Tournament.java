package model;

import java.util.ArrayList;

import view.MapView;

public class Tournament {

	private ArrayList<String> listOfMapFiles;
	private ArrayList<String> listOfPlayerStrategies;
	private int numberOfGames;
	private int maxNumberOfTurns;

	private MapView mapView = new MapView();
	private MapDomination mapDomination = new MapDomination();

	public Tournament(ArrayList<String> listOfMapFiles, ArrayList<String> listOfPlayerStrategies, int numberOfGames, int maxNumberOfTurns) {
		this.listOfMapFiles = listOfMapFiles;
		this.listOfPlayerStrategies = listOfPlayerStrategies;
		this.numberOfGames = numberOfGames;
		this.maxNumberOfTurns = maxNumberOfTurns;
	}

	public void startTheTournament() {
		ArrayList<String> results = new ArrayList<String>();
		boolean somebodyWon = false;


		for (int mapCounter = 0; mapCounter < listOfMapFiles.size(); mapCounter++) {
			mapDomination.isDominationMap(listOfMapFiles.get(mapCounter));

			for (int gameCounter = 0; gameCounter < numberOfGames; gameCounter++) {
				mapDomination.assigningPlayersToCountries(listOfPlayerStrategies, listOfPlayerStrategies);

				for (int turnCounter = 0; turnCounter < maxNumberOfTurns; turnCounter++) {

					for(Player player : mapDomination.getPlayers()) {


						player.reinforceCommand(mapDomination, mapView);

						player.attackCommand(mapView);

						player.fortifyCommand(mapDomination, mapView);
					}

				}

				for(Player player : mapDomination.getPlayers()) {
					if(!player.getWon()) {
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

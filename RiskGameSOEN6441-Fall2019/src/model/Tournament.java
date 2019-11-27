package model;

import java.util.ArrayList;

import view.MapView;

public class Tournament {

	private ArrayList<String> listOfMapFiles;
	private ArrayList<String> listOfPlayerStrategies;
	private int numberOfGames;
	private int maxNumberOfTurns;
	
	private MapView mapView = new MapView();
	private MapGeo mapBuilder = new MapGeo();
	private MapDomination mapDomination = new MapDomination();

	public Tournament(ArrayList<String> listOfMapFiles, ArrayList<String> listOfPlayerStrategies, int numberOfGames, int maxNumberOfTurns) {
		this.listOfMapFiles = listOfMapFiles;
		this.listOfPlayerStrategies = listOfPlayerStrategies;
		this.numberOfGames = numberOfGames;
		this.maxNumberOfTurns = maxNumberOfTurns;
	}

	public void startTheTournament() {
		ArrayList<String> results = new ArrayList<String>();
		String result = "";
		
		
		for (int mapCounter = 0; mapCounter < listOfMapFiles.size(); mapCounter++) {
			mapDomination.isDominationMap(listOfMapFiles.get(mapCounter));
			
			for (int gameCounter = 0; gameCounter < numberOfGames; gameCounter++) {
				mapBuilder.assigningPlayersToCountries(listOfPlayerStrategies, listOfPlayerStrategies);
				
				for (int turnCounter = 0; turnCounter < maxNumberOfTurns; turnCounter++) {
					
					for(Player each : mapBuilder.getPlayers()) {
						each.reinforceCommand(mapBuilder, mapView);
						
						each.attackCommand(mapView);
						
						System.out.println(each.fortifyRequestingMessage);
						
						each.fortifyCommand(mapBuilder, mapView);
					}
					
				}
				
				results.add(result);
			}
			
		}
		
		mapView.showTournamentResult(results, numberOfGames);
	}

}

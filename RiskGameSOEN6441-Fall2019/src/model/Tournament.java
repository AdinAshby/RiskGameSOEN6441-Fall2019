package model;

import java.util.ArrayList;

public class Tournament {

	private ArrayList<String> listOfMapFiles;
	private ArrayList<String> listOfPlayerStrategies;
	private int numberOfGames;
	private int maxNumberOfTurns;

	public Tournament(ArrayList<String> listOfMapFiles, ArrayList<String> listOfPlayerStrategies, int numberOfGames, int maxNumberOfTurns) {
		this.listOfMapFiles = listOfMapFiles;
		this.listOfPlayerStrategies = listOfPlayerStrategies;
		this.numberOfGames = numberOfGames;
		this.maxNumberOfTurns = maxNumberOfTurns;
	}

	public void startTheTournament() {
		for (int mapCounter = 0; mapCounter < listOfMapFiles.size(); mapCounter++) {

			for (int gameCounter = 0; gameCounter < numberOfGames; gameCounter++) {
				for (int turnCounter = 0; turnCounter < maxNumberOfTurns; turnCounter++) {

				}
			}
		}
	}

}

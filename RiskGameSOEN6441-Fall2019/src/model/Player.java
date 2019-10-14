package model;

public class Player {
	
	private int playerID;
	private String playerName;
	private int[] countryID;
	
	public Player(int playerID, String playerName, int[] countryID) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.countryID = countryID;
	}
	
	public int getPlayerID() {
		return playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int[] getCountryID() {
		return countryID;
	}

	public void setCountryID(int[] countriesIds) {
		this.countryID = countriesIds;
	}

}

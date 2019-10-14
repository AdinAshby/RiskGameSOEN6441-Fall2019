package model;

/**
 * 
 * This is Player class, in which we define our attributes of a Player.
 *  
 *
 */
public class Player {
	 
	/**
	 * PlayerID is a unique ID for each player.
	 */
	private int playerID;
	
	/**
	 * PlayerName is name of the player corresponding to the ID.
	 */
	private String playerName;
	
	/**
	 * List of all countryIDs that the player owns.
	 */
	private int[] countryID;
	
	/**
	 * Player constructor method.
	 * 
	 * @param playerID A unique ID for each player.
	 * @param playerName Corresponding player name.
	 * @param countryID List of all countries (IDs) that the player owns.
	 */
	public Player(int playerID, String playerName, int[] countryID) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.countryID = countryID;
	}
	
	/**
	 * Getter for PlayerID.
	 * 
	 * @return playerID Returns playerID as an integer.
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * Getter for PlayerName.
	 * 
	 * @return playerName Returns playerName as a String.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Getter for CountryIDs.
	 * 
	 * @return getCountryID Returns CountryIDs as an array of integers.
	 */
	public int[] getCountryID() {
		return countryID;
	}

	/**
	 * Setter for CountryIDs.
	 * 
	 * @param countriesIDs Sets a list of countriesIDs to the corresponding field.
	 */
	public void setCountryID(int[] countriesIDs) {
		this.countryID = countriesIDs;
	}

}

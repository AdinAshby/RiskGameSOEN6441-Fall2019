package model;

/**
 * 
 * This is Player class, in which we define our attributes of a Player.
 *  
 */
public class Player {
	
	/**
	 * PlayerName is name of the player corresponding to the ID.
	 */
	private String playerName;
	
	/**
	 * List of all countryIDs that the player owns.
	 */
	private int[] countryIDs;
	
	/**
	 * Player constructor method.
	 * 
	 * @param playerID A unique ID for each player.
	 * @param playerName Corresponding player name.
	 * @param countryID List of all countries (IDs) that the player owns.
	 */
	public Player(String playerName, int[] countryID) {
		this.playerName = playerName;
		this.countryIDs = countryID;
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
	public int[] getCountryIDs() {
		return countryIDs;
	}

	/**
	 * Setter for CountryIDs.
	 * 
	 * @param countriesIDs Sets a list of countriesIDs to the corresponding field.
	 */
	public void setCountryIDs(int[] countriesIDs) {
		this.countryIDs = countriesIDs;
	}
	
}

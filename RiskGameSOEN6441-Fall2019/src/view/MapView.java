package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import controller.RiskController;
import model.Continent;
import model.Country;
import model.MapGeo;
import model.Player;

/**
 * This a Map View Class containing all the styles of the showing map
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 *
 */

public class MapView {
	
	private final String[] phases = {"Startup", "Reinforcement", "Attack", "Fortification"};
	private final String[] phasesDescription = {
			"Loadmap, gameplayer -add playername -remove playername and populatecountries are valid in this phase",
			"Reinforce countryname num and exchangecards num num num or -none are valid commands in this phase", 
			"Attack countrynamefrom countrynameto numdice or -allout or -noattack, defend numedice and attackmove num are valid commands in this phase", 
			"Fortify fromcountry tocountry num or -none are valid commands in this phase"};

	/**
	 * This method show the map
	 * 
	 * @param theMapBuilder
	 */
	public void showMap(MapGeo theMapBuilder) {

		Iterator<Entry<Integer, Continent>> iteratorForContinents = theMapBuilder.getContinentList().entrySet()
				.iterator();

		String table = "|%-16d|%-18s|%n";

		System.out.format("+----------------+------------------+%n");
		System.out.format("| Continent's ID | Continent's name |%n");
		System.out.format("+----------------+------------------+%n");

		while (iteratorForContinents.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) iteratorForContinents.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = theMapBuilder.getContinentList().get(continentId);

			System.out.format(table, continent.getContinentId(), continent.getContinentName());
		}

		System.out.format("+----------------+------------------+%n");

		Iterator<Entry<Integer, Continent>> iteratorForContinent = theMapBuilder.getContinentList().entrySet()
				.iterator();

		table = "|%-14d|%-23s|%-18s|%-28s|%-15s|%-15s|%n";

		System.out.format(
				"+--------------+-----------------------+------------------+----------------------------+---------------+---------------+%n");
		System.out.format(
				"| Country's ID |     Country's name    | Continent's Name |   Adjacent countries' ID   | No. of armies | Player's name |%n");
		System.out.format(
				"+--------------+-----------------------+------------------+----------------------------+---------------+---------------+%n");

		while (iteratorForContinent.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) iteratorForContinent.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = theMapBuilder.getContinentList().get(continentId);
			ListIterator<Country> listIterator = continent.getCountriesList().listIterator();

			while (listIterator.hasNext()) {

				Country country = (Country) listIterator.next();

				System.out.format(table, country.getCountryId(), country.getCountryName(), continent.getContinentName(),
						theMapBuilder.getCountryAdjacency().getVertexAdjacencyString(country.getCountryId()),
						country.getArmies(), country.getPlayerName());

			}
		}

		System.out.format(
				"+--------------+-----------------------+------------------+----------------------------+---------------+---------------+%n");
	}

	public void showPlayersWorldDomination(Player[] players) {

		String table = "|%-15s|%-23f|%-30s|%-21d|%n";

		System.out.format(
				"+---------------+-----------------------+------------------------------+---------------------+%n");
		System.out.format(
				"| Player's name | Percentage Controlled |    Continent's Controlled    | No. of Armies Owned |%n");
		System.out.format(
				"+---------------+-----------------------+------------------------------+---------------------+%n");

		for(Player player : players) {

			System.out.format(table, player.getPlayerName(), player.getPercentageControlled(), player.getContinentsControlled(),
					player.getTotalNumberOfArmies());
		}

		System.out.format(
				"+---------------+-----------------------+------------------------------+---------------------+%n");

	}

	public void showPhaseView(int counterForPhase, String playerName) {

		String table = "|%-13s|%-15s|%-137s|%n";
		

		System.out.format(
				"+-------------+---------------+-----------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format(
				"|    Phase    | Player's name |                                                            Phase description                                                            |%n");
		System.out.format(
				"+-------------+---------------+-----------------------------------------------------------------------------------------------------------------------------------------+%n");

		System.out.format(table, phases[counterForPhase], playerName,  phasesDescription[counterForPhase]);

		System.out.format(
				"+-------------+---------------+-----------------------------------------------------------------------------------------------------------------------------------------+%n");
	}

	/**
	 * This method  show Player Map
	 * 
	 * @param player
	 * @param countriesList
	 */
	public void showPlayerMap(Player player, List<Country> countriesList) {

		String table = "|%-14d|%-16s|%-28d|%-18d|%-15s|%-15s|%n";

		System.out.format(
				"+--------------+----------------+----------------------------+------------------+---------------+---------------+%n");
		System.out.format(
				"| Country's ID | Country's name |   Adjacent countries' ID   | Continent's Name | No. of armies | Player's name |%n");
		System.out.format(
				"+--------------+----------------+----------------------------+------------------+---------------+---------------+%n");

		LinkedList<Country> playerCountries = getPlayerCorrespondingCountries(player.getPlayerName(), countriesList);

		for (int i = 0; i < playerCountries.size(); i++)
			System.out.format(table, returnCountryID(playerCountries, i), returnCountryName(playerCountries, i),
					returnArmy(playerCountries, i), returnContinentName(playerCountries, i),
					Arrays.toString(returnAdjacentCountriesID(playerCountries, i)),
					returnPlayerName(playerCountries, i));

		System.out.format(
				"+--------------+----------------+----------------------------+------------------+---------------+---------------+%n");
	}

	/**
	 * This method show the Adjacency Map
	 * 
	 * @param myCountry
	 * @param countriesList
	 */
	public void showAdjacencyMap(Country myCountry, List<Country> countriesList) {

		String table = "|%-14d|%-16s|%-28d|%-18d|%-15s|%-15s|%n";

		System.out.format("+--------------+----------------+------------------+---------------+---------------+%n");
		System.out.format("| Country's ID | Country's name | Continent's Name | No. of armies | Player's name |%n");
		System.out.format("+--------------+----------------+------------------+---------------+---------------+%n");

		LinkedList<Country> adjacentCountries = getPlayerCorrespondingCountries(myCountry.getCountryName(),
				countriesList);

		for (int i = 0; i < adjacentCountries.size(); i++)
			System.out.format(table, returnCountryID(adjacentCountries, i), returnCountryName(adjacentCountries, i),
					returnContinentName(adjacentCountries, i), returnArmy(adjacentCountries, i),
					returnPlayerName(adjacentCountries, i));

		System.out.format("+--------------+----------------+------------------+---------------+---------------+%n");
	}

	/**
	 *This method  show the Fortify Map
	 * 
	 * @param player
	 * @param country
	 * @param countriesList
	 */
	public void showFortifyMap(Player player, Country country, List<Country> countriesList) {

		String table = "|%-14d|%-16s|%-18d|%-28d|%-15s|%-15s|%n";

		System.out.format(
				"+--------------+----------------+------------------+----------------------------+---------------+---------------+%n");
		System.out.format(
				"| Country's ID | Country's name | Continent's Name |   Adjacent countries' ID   | No. of armies | Player's name |%n");
		System.out.format(
				"+--------------+----------------+------------------+----------------------------+---------------+---------------+%n");

		LinkedList<Country> specificCountryAdjacentsForFortification = getSpecificCountryAdjacentsForFortification(
				player.getPlayerName(), country.getCountryId(), countriesList);

		for (int i = 0; i < specificCountryAdjacentsForFortification.size(); i++)
			System.out.format(table, returnCountryID(specificCountryAdjacentsForFortification, i),
					returnCountryName(specificCountryAdjacentsForFortification, i),
					returnArmy(specificCountryAdjacentsForFortification, i),
					returnContinentName(specificCountryAdjacentsForFortification, i),
					Arrays.toString(returnAdjacentCountriesID(specificCountryAdjacentsForFortification, i)),
					returnPlayerName(specificCountryAdjacentsForFortification, i));

		System.out.format(
				"+--------------+----------------+---------------+----------------+----------------------------+---------------+%n");
	}

	/**
	 * This method returns the list of Countries by Id
	 * @param listOfCountries
	 * @param counter
	 * @return list Of the Countries by Id
	 */
	public int returnCountryID(List<Country> listOfCountries, int counter) {
		return listOfCountries.get(counter).getCountryId();
	}

	/**
	 * This method returns the list of countries by name
	 * @param listOfCountries
	 * @param counter
	 * @return list Of the Countries by name
	 */

	public String returnCountryName(List<Country> listOfCountries, int counter) {
		return listOfCountries.get(counter).getCountryName();
	}

	/**
	 * This method  return armies 
	 * @param listOfCountries
	 * @param counter
	 * @return Army
	 */

	public int returnArmy(List<Country> listOfCountries, int counter) {
		return listOfCountries.get(counter).getArmies();
	}

	/**
	 * This method return continentName of the list of countries
	 * @param listOfCountries
	 * @param counter
	 * @return Continent Name
	 */
	public String returnContinentName(List<Country> listOfCountries, int counter) {
		return listOfCountries.get(counter).getContinentName();
	}

	/**
	 * This method return countries Id of adjacent countries
	 * @param listOfCountries
	 * @param counter
	 * @return Adjacent Countries ID
	 */
	public int[] returnAdjacentCountriesID(List<Country> listOfCountries, int counter) {
		return listOfCountries.get(counter).getAdjacentCountriesID();
	}

	/**
	 * This method return playerName of country
	 * @param listOfCountries
	 * @param counter
	 * @return Player Name
	 */
	public String returnPlayerName(List<Country> listOfCountries, int counter) {

		return listOfCountries.get(counter).getPlayerName();
	}

	/**
	 * This method returns list of player countries
	 * @param playerName
	 * @param countriesList
	 * @return player Countries
	 */
	public LinkedList<Country> getPlayerCorrespondingCountries(String playerName, List<Country> countriesList) {
		LinkedList<Country> playerCountries = new LinkedList<Country>();

		for (int i = 0; i < countriesList.size(); i++)
			if (countriesList.get(i).getPlayerName().equalsIgnoreCase(playerName))
				playerCountries.add(countriesList.get(i));

		return playerCountries;
	}

	/**
	 * This method return adjacent countries of country
	 * @param countryName
	 * @param countriesList
	 * @return adjacent Countries
	 */
	public LinkedList<Country> getCorrespondingAdjacentCountries(String countryName, List<Country> countriesList) {
		LinkedList<Country> adjacentCountries = new LinkedList<Country>();

		for (int i = 0; i < countriesList.size(); i++)
			if (countriesList.get(i).getCountryName().equalsIgnoreCase(countryName))
				adjacentCountries.add(countriesList.get(i));

		return adjacentCountries;
	}

	/**
	 * This method return specific adjacent country for fortification for player
	 * @param playerName
	 * @param specificCountryID
	 * @param countriesList
	 * @return Specific Country Adjacent For Fortification
	 */
	public LinkedList<Country> getSpecificCountryAdjacentsForFortification(String playerName, int specificCountryID,
			List<Country> countriesList) {

		Player[] allPlayers = RiskController.getInstance().getPlayers();
		ArrayList<Integer> playerCountries = null;

		for (int i = 0; i < allPlayers.length; i++) {
			if (allPlayers[i].getPlayerName().equalsIgnoreCase(playerName)) {
				playerCountries = allPlayers[i].getCountryIDs();
				break;
			}
		}
		/**
		 * specific CountryAdjacentsForFortification object of the Country
		 */
		LinkedList<Country> specificCountryAdjacentsForFortification = new LinkedList<Country>();
		/**
		 * myCountries object of the Country
		 */
		LinkedList<Country> myCountries = new LinkedList<Country>();

		for (int i = 0; i < countriesList.size(); i++)
			for (int j = 0; j < returnAdjacentCountriesID(countriesList, i).length; j++)
				if (returnAdjacentCountriesID(countriesList, i)[j] == specificCountryID)
					specificCountryAdjacentsForFortification.add(countriesList.get(i));

		for (int i = 0; i < specificCountryAdjacentsForFortification.size(); i++)
			for (int j = 0; j < playerCountries.size(); j++)
				if (returnCountryID(specificCountryAdjacentsForFortification, i) == playerCountries.get(j))
					myCountries.add(specificCountryAdjacentsForFortification.get(i));

		return myCountries;
	}
}

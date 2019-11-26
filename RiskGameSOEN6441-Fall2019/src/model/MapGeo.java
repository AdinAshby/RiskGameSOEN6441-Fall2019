package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.RiskController;
import view.MapView;

/**
 * This is MapBuilder Class to Edit Map
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 */
public class MapGeo {

	/**
	 * file map
	 */
	final File mapFolder = new File("./MapFiles");

	public File getMapFolder() {
		return mapFolder;
	}

	/**
	 * private continentList
	 */
	protected Map<Integer, Continent> continentList = new HashMap<Integer, Continent>(); // continentId=> continentName,
	// continentValue,
	protected MapView theMapView = new MapView(); // continentColor
	/**
	 * countryAdjacency
	 */
	protected AdjacencyList countryAdjacency = new AdjacencyList();
	/**
	 * private player
	 */
	protected Player[] players;

	/**
	 * private random
	 */
	protected Random random = new Random();

	/**
	 * private number Of Armies Each Player Gets
	 */
	private static MapGeo instance;

	/**
	 * This is a constructor
	 */
	protected MapGeo() {

	}

	/**
	 * 
	 * @return instance
	 */
	public static MapGeo getInstance() {
		if (instance == null) {
			instance = new MapGeo();
			return instance;
		} else {
			return instance;
		}
	}

	/**
	 * This method check whether is exchange Cards Valid
	 * 
	 * @param player
	 * @param num1
	 * @param num2
	 * @param num3
	 * @return false
	 */
	public boolean exchangeCardsIsValid(Player player, int num1, int num2, int num3) {
		ArrayList<Card> cardsForPlayer = player.getCards();

		if (cardsForPlayer.size() < 3)
			return false;

		Card A = cardsForPlayer.get(num1);
		Card B = cardsForPlayer.get(num2);
		Card C = cardsForPlayer.get(num3);

		if ((A.equals(B)) && (B.equals(C)))
			return true;

		if (!(A.equals(B)) && !(B.equals(C) && !(A.equals(C))))
			return true;

		return false;
	}

	/**
	 * This method is exchange the card
	 * 
	 * @param player
	 * @param num1
	 * @param num2
	 * @param num3
	 * @return
	 */
	public int exchangeCards(Player player, int num1, int num2, int num3) {
		int cardarmies;

		ArrayList<Card> cardsforplayerArrayList = player.getCards();
		Card A = cardsforplayerArrayList.get(num1);
		Card B = cardsforplayerArrayList.get(num2);
		Card C = cardsforplayerArrayList.get(num3);

		player.addOneToCardCounter();

		return player.getplayerCountForCard() * 5;
	}

	/**
	 * This will return list of continents
	 * 
	 * @return continentList
	 */
	public Map<Integer, Continent> getContinentList() {
		return continentList;
	}

	/**
	 * 
	 * @return
	 */
	public int getNoOfContinentsControlled() {
		return continentList.size();
	}

	/**
	 * This method sets the continentList
	 * 
	 * @param continentList
	 */
	public void setContinentList(Map<Integer, Continent> continentList) {
		this.continentList = continentList;
	}

	/**
	 * This method returns size of continentList
	 * 
	 * @return size of the continentList
	 */

	public int getContinentListSize() {
		return continentList.size();

	}

	/**
	 * This method size of country adjacency
	 * 
	 * @return size of the Country Adjacency
	 */
	public int getCountryListSize() {
		// System.out.println("Size:" + countryAdjacency.getSize());
		return getCountryAdjacency().getSize();

	}

	/**
	 * This method reads continent Id
	 */
	public String getContinentName(int continentId) {

		Continent continent = continentList.get(continentId);
		String continentName = continent.getContinentName();
		return continentName;
	}

	/**
	 * This method get the continent by passing the continent Id
	 * 
	 * @return continent
	 */
	public Continent getContinent(int continentId) {

		Continent continent = continentList.get(continentId);
		return continent;
	}

	/**
	 * This method adds a new continent
	 * 
	 * @param continent
	 *            object of Conintent class
	 */

	public void addContinent(Continent continent) {
		continentList.put(continent.getContinentId(), continent);
	}

	/**
	 * This adds a new continent and show is added
	 * 
	 * @param continentName
	 * @param continentValue
	 */
	public void addContinent(String continentName, int continentValue) {
		Continent continent = new Continent(continentName, continentValue);
		if (continent == null) {
			System.out.println("continent is not found");
		} else {
			continentList.put(continent.getContinentId(), continent);

			System.out.println(continentName.toUpperCase() + " added ");
		}
	}

	/**
	 * This method removes a continent
	 * 
	 * @param continentid
	 */
	public void removeContinent(int continentId) {
		Continent continent = getContinent(continentId);
		List<Country> countries = continent.getCountriesList();
		for (Country c : countries) {
			System.out.println("Country " + c.getCountryName() + "  removed");
			getCountryAdjacency().removeVertex(c.getCountryId());
		}
		System.out.println("CONTINENT " + continent.getContinentName().toUpperCase() + " removed ");
		continentList.remove(continentId);

	}

	/**
	 * This method remove a continent and show is removed
	 * 
	 * @param continentName
	 */

	public void removeContinent(String continentName) {
		Continent continent = getContinent(continentName);
		if (continent == null) {
			System.out.println("Continent not found");
		} else {

			List<Country> countries = continent.getCountriesList();
			for (Country c : countries) {
				System.out.println("Country " + c.getCountryName() + "  removed");
				getCountryAdjacency().removeVertex(c.getCountryId());
			}
			continentList.remove(continent.getContinentId());
			System.out.println("CONTINENT " + continentName.toUpperCase() + " removed ");
		}
	}

	/**
	 * This method get a continent
	 * 
	 * @return continent if there is any otherwise null
	 */
	public Continent getContinent(String continentName) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);

			if (continent.getContinentName().equals(continentName)) {
				return continent;

			}

		}
		return null;
	}

	/**
	 * This method will print List of Continents
	 */

	public void showMap() {

		theMapView.showMap(this);

	}

	/**
	 * This method will print list of maps
	 */
	public void getListOfMaps() {

		for (final File fileEntry : mapFolder.listFiles()) {
			System.out.println(fileEntry.getName());
		}
	}

	/**
	 * This method returns country by countryId
	 * 
	 * @param countryId
	 * @return
	 */
	public Country getCountryById(int countryId) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			Country country = c.getCountry(countryId);
			if (country != null) {
				return country;
			}
		}
		return null;
	}

	/**
	 * This method returns countryName by countryId
	 * 
	 * @param countryId
	 * @return Country Name if passed the country id
	 */
	public String getCountryNameById(int countryId) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			Country country = c.getCountry(countryId);
			if (country != null) {
				return country.getCountryName();
			}
		}
		System.out.println("Country Not Found " + countryId);
		return null;
	}

	/**
	 * This method returns countryName by countryName
	 * 
	 * @param countryName
	 * @return
	 */

	public Country getCountryByName(String countryName) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			Country country = c.getCountry(countryName);
			if (country != null) {
				return country;
			}
		}
		return null;
	}

	public int getCountryIdByName(String countryName) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			Country country = c.getCountry(countryName);
			if (country != null) {
				return country.getCountryId();
			}
		}
		return 0;
	}
	
	/**
	 * This method returns continent by countryName
	 * 
	 * @param countryName
	 * @return Continent if passed the CountryName
	 */
	public Continent getContinentByCountryName(String countryName) {
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			// System.out.println("Searching in " + continent.getContinentName());
			Country country = continent.getCountry(countryName);

			if (country != null) {
				return continent;
			}
		}
		return null;
	}

	/**
	 * This method checks if map is connected subgraph
	 * 
	 * @return false if map is not a connected subgraph
	 */
	public boolean isMapSubGraph() {
		boolean isSubGraph = true;
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			boolean isThisContinentConnected = false;
			List<Country> countriesList = continent.getCountriesList();
			ArrayList<Integer> countriesIdList = new ArrayList<Integer>();
			for (Country c : countriesList) {
				countriesIdList.add(c.getCountryId());
			}

			if (countriesList.size() == 0) {
				return false;
			}

			for (Country c : countriesList) {
				int countryId = c.getCountryId();
				// System.out.println("Check Country "+countryId+" from countries List");
				ArrayList<Integer> listAdj = getCountryAdjacency().getVertexAdjacency(countryId);
				for (int neighbor : listAdj) {
					// System.out.println("Check Neighber="+neighbor);
					if (!countriesIdList.contains(neighbor)) {
						isThisContinentConnected = true;
						// System.out.println("Connected "+continentId);
					}
				}
				// System.out.println("------------");

			}
			if (!isThisContinentConnected) {
				isSubGraph = false;
				System.out.println("Not Sub Graph for Continent Id=" + continentId);
			}
			// End of Loop of Continent
		}

		return isSubGraph;
	}

	/**
	 * This method returns will add a new country in adjacency list of country
	 * 
	 * @param countryId
	 * @param targetCountryId
	 */
	public void addCountryAdjacency(int countryId, int neighborCountryId) {
		getCountryAdjacency().addEdge(countryId, neighborCountryId);

	}

	/**
	 * This method addCountryAdjacency
	 * 
	 * @param countryName
	 * @param neighborCountryName
	 */
	public void addCountryAdjacency(String countryName, String neighborCountryName) {
		Country country = getCountryByName(countryName);
		Country neighborCountry = getCountryByName(neighborCountryName);
		if (country == null) {
			System.out.println("country is not defined");
		} else if (neighborCountry == null) {
			System.out.println("neighborCountry is not defined");
		} else {
			getCountryAdjacency().addEdge(country.getCountryId(), neighborCountry.getCountryId());
			System.out.println(neighborCountryName.toLowerCase() + " added to the " + countryName);
		}
	}

	/**
	 * This method remove the Country Adjacency
	 * 
	 * @param countryName
	 * @param neighborCountryName
	 */
	public void removeCountryAdjacency(String countryName, String neighborCountryName) {
		Country country = getCountryByName(countryName);
		Country neighborCountry = getCountryByName(neighborCountryName);
		if (country == null) {
			System.out.println("country is not defined");
		} else if (neighborCountry == null) {
			System.out.println("neighborCountry is not defined");
		} else {
			getCountryAdjacency().removeEdge(country.getCountryId(), neighborCountry.getCountryId());
			System.out.println(neighborCountryName.toLowerCase() + " removed from the " + countryName);
		}
	}

	/**
	 * This method shows country adjacency list
	 */
	public String showCountryAdjacency() {
		return getCountryAdjacency().showListEdges();
	}

	

	/**
	 * This method add country to corresponding continent
	 * 
	 * @param countryName
	 * @param continentName
	 */
	public void addCountry(String countryName, String continentName) {
		System.out.println(countryName + " " + continentName);
		Continent continent = getContinent(continentName);
		Country country = new Country(countryName);
		if (continent == null) {
			System.out.println("continent not found");
		} else if (country == null) {
			System.out.println("country not found");
		} else {
			continent.addCountry(country);

			System.out.println(countryName.toLowerCase() + " with Id=" + country.getCountryId() + " added to  "
					+ continentName.toUpperCase());
		}
	}

	/**
	 * This method remove the Country
	 * 
	 * @param countryName
	 */
	public void removeCountry(String countryName) {
		Continent continent = getContinentByCountryName(countryName);

		if (continent != null) {
			continent.removeCountry(countryName);
			System.out.println("Country removed from " + continent.getContinentName());
		} else {
			System.out.println("Country " + countryName + " not found");
		}

	}

	/**
	 * This method check the map whether is validate
	 * 
	 * @return isValid
	 */

	public boolean validateMap() {
		
		boolean isValid = true;
		if (getContinentListSize() < 2) {
			isValid = false;
			System.out.println("Continents are less than 2");
		}
		if (getCountryListSize() < 2) {
			isValid = false;
			System.out.println("Countries with no adjacency or Countries are less than 2");
		}
		if (!getCountryAdjacency().isConnected()) {
			isValid = false;
			System.out.println("Is not a connected graph");
		}

		if (isMapSubGraph() == false) {
			isValid = false;
			System.out.println("Is not a connected sub graph");
		}

		if (isValid) {
			System.out.println("Map is valid");
		} else {
			System.out.println("Map is invalid");
		}
		return isValid;
	}

	/**
	 * This method return countryList of the continent
	 * 
	 * @param continentName
	 * @return country name list
	 */
	public ArrayList<String> getCountryListNames(String continentName) {
		Continent continent = getContinent(continentName);
		ArrayList<String> countries = new ArrayList<String>();
		countries = continent.getCountryListNames();
		return countries;
	}

	/**
	 * This method return continent of country
	 * 
	 * @param countryName
	 * @return name of the continent
	 */
	public String getContinentOfCountry(String countryName) {
		Continent continent = getContinentByCountryName(countryName);
		return continent.getContinentName();
	}

	/**
	 * This method return list of borders
	 * 
	 * @return list of borders
	 */
	public HashMap<String, ArrayList<String>> getListOfBorders() {

		HashMap<String, ArrayList<String>> borders = new HashMap<String, ArrayList<String>>();
		ArrayList<Integer> keys = getCountryAdjacency().getKeys();
		for (int key : keys) {
			String countryName = getCountryNameById(key);
			ArrayList<Integer> countries = getCountryAdjacency().getValues(key);
			ArrayList<String> borderNames = new ArrayList<String>();
			for (int countryId : countries) {
				borderNames.add(getCountryNameById(countryId));
			}
			borders.put(countryName, borderNames);
		}
		return borders;
	}

	/**
	 * This method list of all countries
	 * 
	 * @return allCountries
	 */
	public LinkedList<Country> getAllCountries() {

		LinkedList<Country> allCountries = new LinkedList<Country>();
		Iterator<Entry<Integer, Continent>> iteratorForContinent = continentList.entrySet().iterator();

		while (iteratorForContinent.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) iteratorForContinent.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			ListIterator<Country> listIterator = continent.getCountriesList().listIterator();

			while (listIterator.hasNext()) {

				Country country = (Country) listIterator.next();

				allCountries.add(country);
			}
		}

		return allCountries;
	}

	/**
	 * This method assigns Players To the Countries
	 * 
	 * @param playerNames
	 */
	public void assigningPlayersToCountries(ArrayList<String> playerNames, ArrayList<String> playerStrategies) {

		players = new Player[playerNames.size()];

		ArrayList<Country> temporaryCountries = new ArrayList<Country>();

		for (Country country : getAllCountries()) {
			temporaryCountries.add(country);
		}

		int numberOfCountriesEachPlayerGet = temporaryCountries.size() / players.length;
		int leftOverCountries = temporaryCountries.size() % players.length;

		for (int i = 0; i < players.length; i++) {
			ArrayList<Integer> countriesIDs = new ArrayList<Integer>(numberOfCountriesEachPlayerGet);

			for (int j = 0; j < numberOfCountriesEachPlayerGet; j++) {
				int randomCountryID = random.nextInt(temporaryCountries.size());
				//countriesIDs.add(j, temporaryCountries.get(randomCountryID).getCountryId());
				countriesIDs.add(temporaryCountries.get(randomCountryID).getCountryId());
//				System.out.println("Rand="+randomCountryID+" Country="+temporaryCountries.get(randomCountryID).getCountryId());
				temporaryCountries.remove(randomCountryID);
				// updateCountryById(i, countriesIds[j]);
			}

			players[i] = new Player(playerNames.get(i), countriesIDs, this);

			if (playerStrategies.get(i).equalsIgnoreCase("human"))
				players[i].setStrategy(new HumanPlayer(playerNames.get(i), countriesIDs, this));

			if (playerStrategies.get(i).equalsIgnoreCase("aggressive"))
				players[i].setStrategy(new AggressivePlayer(playerNames.get(i), countriesIDs, this));

			if (playerStrategies.get(i).equalsIgnoreCase("benevolent"))
				players[i].setStrategy(new BenevolentPlayer(playerNames.get(i), countriesIDs, this));

			if (playerStrategies.get(i).equalsIgnoreCase("random"))
				players[i].setStrategy(new RandomPlayer(playerNames.get(i), countriesIDs, this));

			if (playerStrategies.get(i).equalsIgnoreCase("cheater"))
				players[i].setStrategy(new CheaterPlayer(playerNames.get(i), countriesIDs, this));

			for (int m = 0; m < countriesIDs.size(); m++) {
				updateCountryIDs(playerNames.get(i), countriesIDs.get(m));
			}

		}

		if (leftOverCountries > 0) {
			for (Player giveMoreCountriesToPlayer : players) {
				int randomCountryID = random.nextInt(temporaryCountries.size());
				int playerCountryCount = giveMoreCountriesToPlayer.getCountryIDs().size() + 1;
				ArrayList<Integer> currentPlayerCountries = giveMoreCountriesToPlayer.getCountryIDs();
				ArrayList<Integer> receivedMoreCountriesPlayerCountriesList = new ArrayList<Integer>(playerCountryCount);

				for (int i = 0; i < giveMoreCountriesToPlayer.getCountryIDs().size(); i++) {
					receivedMoreCountriesPlayerCountriesList.add(currentPlayerCountries.get(i)); //i, 
				}

				int newCountryIDForPlayer = temporaryCountries.get(randomCountryID).getCountryId();
				receivedMoreCountriesPlayerCountriesList.add(newCountryIDForPlayer); //playerCountryCount - 1, 
				giveMoreCountriesToPlayer.setCountryIDs(receivedMoreCountriesPlayerCountriesList);

				updateCountryIDs(giveMoreCountriesToPlayer.getPlayerName(), newCountryIDForPlayer);

				temporaryCountries.remove(randomCountryID);

				if (temporaryCountries.size() == 0) {
					break;
				}
			}
		}

		for (Player player : players) {
			player.registerPhaseObserver(new PlayerObserver(player));
			player.registerWorldDominationObserver(new PlayerObserver(player));
		}

	}

	/**
	 * This method update the Country IDs
	 * 
	 * @param playerName
	 * @param countryID
	 */
	private void updateCountryIDs(String playerName, int countryID) {

		for (Country eachCountry : getAllCountries()) {
			int eachCountryID = eachCountry.getCountryId();
			if (eachCountryID == countryID) {
				eachCountry.setPlayer(playerName);
				eachCountry.setArmies(1);
			}
		}
	}

	/**
	 * This method assign Initials Armies To The Specific Country
	 * 
	 * @param countryName
	 * @param armiesAdded
	 */
	public void assignInitialsArmiesToSpecificCountry(String countryName, int armiesAdded) {
		Country country = getCountryByName(countryName);
		if (country != null) {
			int oldArmies = country.getArmies();
			getCountryByName(countryName).setArmies(armiesAdded + oldArmies);
		} else {
			System.out.println("Country not found");
		}

		getPlayerByName(country.getPlayerName()).calculateWorldDominationView();
	}

	/**
	 * This method check whether the placearmy Valid
	 * 
	 * @param player
	 * @param countryName
	 * @return false
	 */
	public boolean placearmyIsValid(Player player, String countryName) {

		ArrayList<Integer> playerCountries = player.getCountryIDs();

		for (int countryID : playerCountries) {
			if (getCountryByName(countryName) == getCountryById(countryID)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * This method place All the Armies
	 * 
	 * @param armiesEachPlayerGets
	 */
	public void placeAllArmies() {

		for (Player player : players) {
			int armiesForEach = calculateNumberOfInitialArmies();

			while (armiesForEach > 0) {
				int randomPlayerCountryID = random.nextInt(player.getCountryIDs().size());
				int randomArmy = random.nextInt(armiesForEach) + 1;
				armiesForEach -= randomArmy;
				ArrayList<Integer> p = player.getCountryIDs();
				int randomID = p.get(randomPlayerCountryID);
				int oldArmies = getCountryById(randomID).getArmies();
				getCountryById(randomID).setArmies(oldArmies + armiesForEach);// Check if atleast 25 army should be
																				// assigned

			}

			player.calculateWorldDominationView();

			// player.calculateTotalNumberOfArmies();
			// player.notifyObserverForWorldDomination();
		}

	}

	

	/**
	 * This method is for getContinentNamesOfPlayer
	 * 
	 * @param playerName
	 * @return getContinentNamesOfPlayer
	 */

	public void setContinentNamesOfPlayer(Player player) {

		ArrayList<Integer> playerCountries = player.getCountryIDs();
		ArrayList<String> ContinentList = new ArrayList<String>(continentList.size());

		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			List<Country> countriesList = c.getCountriesList();
			int[] countriesIdList = new int[countriesList.size()];

			int counter = 0;
			boolean isThisContinentForPlayer = true;
			for (Country cId : countriesList) {
				countriesIdList[counter] = cId.getCountryId();
				int countryId = cId.getCountryId();
				counter++;
				if (!playerCountries.contains(countryId)) {
					isThisContinentForPlayer = false;
				}
			}

			if (isThisContinentForPlayer == true) {
				ContinentList.add(c.getContinentName());
			}

		}

		player.setContinentsControlled(ContinentList);
	}

	/**
	 * 
	 * @param array
	 * @param v
	 * @return result
	 */
	public static boolean contains(final int[] array, final int v) {

		boolean result = false;

		for (int i : array) {
			if (i == v) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * This method check whether the reinforce is valid
	 * 
	 * @param playerName
	 * @param countryName
	 * @param armiesAdded
	 * @return false
	 */

	/**
	 * This method implements the fortification phase
	 * 
	 * @param fromCountry
	 * @param toCountry
	 * @param armiesToMove
	 */

	/**
	 * In This method The player Continent Values Ownership
	 * 
	 * @param playerName
	 * @return
	 */
	public int playerContinentValuesOwnership(String playerName) {

		int continentValuesPlayerGets = 0;

		Iterator<Entry<Integer, Continent>> iteratorForContinent = continentList.entrySet().iterator();

		while (iteratorForContinent.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) iteratorForContinent.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			ListIterator<Country> listIterator = continent.getCountriesList().listIterator();

			int counterForPlayerCountriesInContinent = 0;

			while (listIterator.hasNext()) {

				if (listIterator.next().getPlayerName().equalsIgnoreCase(playerName))
					++counterForPlayerCountriesInContinent;
			}

			if (counterForPlayerCountriesInContinent == continent.getCountriesList().size())
				continentValuesPlayerGets += continent.getContinentControlValue();
		}

		return continentValuesPlayerGets;
	}

	/**
	 * This method is for continents Owned By Player
	 * 
	 * @param playerName
	 * @return continentsOfPlayerArray
	 */
	public String[] continentsOwnedByPlayer(String playerName) {

		LinkedList<String> continentsOfPlayer = new LinkedList<String>();

		Iterator<Entry<Integer, Continent>> iteratorForContinent = continentList.entrySet().iterator();

		while (iteratorForContinent.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) iteratorForContinent.next();
			int continentId = (int) continentMap.getKey();
			Continent continent = continentList.get(continentId);
			ListIterator<Country> listIterator = continent.getCountriesList().listIterator();

			int counterForPlayerCountriesInContinent = 0;

			while (listIterator.hasNext()) {

				if (listIterator.next().getPlayerName().equalsIgnoreCase(playerName))
					++counterForPlayerCountriesInContinent;
			}

			if (counterForPlayerCountriesInContinent == continent.getCountriesList().size())
				continentsOfPlayer.add(continent.getContinentName());
		}

		String[] continentsOfPlayerArray = new String[continentsOfPlayer.size()];

		for (int i = 0; i < continentsOfPlayerArray.length; i++) {
			continentsOfPlayerArray[i] = continentsOfPlayer.get(i);
		}

		return continentsOfPlayerArray;
	}

	/**
	 * This method is for calculate Number Of Initial Armies
	 * 
	 * @return 20
	 */
	public int calculateNumberOfInitialArmies() {
		if (players.length <= 6)
			return (50 - (5 * players.length));
		else
			return 20;
	}

	/**
	 * This method get the number of Armies Each Player Gets
	 * 
	 * @return numberOfArmiesEachPlayerGets
	 */
	// public int getNumberOfArmiesEachPlayerGets() {
	// return numberOfArmiesEachPlayerGets;
	// }

	/**
	 * This method return all the players
	 * 
	 * @return players
	 */
	public Player[] getPlayers() {
		return (this.players);
	}

	/**
	 * This method return all the country adjacency
	 * 
	 * @return country Adjacency
	 */
	public AdjacencyList getCountryAdjacency() {
		return countryAdjacency;
	}

	/**
	 * 
	 * @param countryId
	 * @return
	 */
	public ArrayList<Integer> getCountryAdjacency(int countryId) {
		return countryAdjacency.getVertexAdjacency(countryId);
	}

	/**
	 * This method Country Adjacency
	 * 
	 * @param countryAdjacency
	 */
	public void setCountryAdjacency(AdjacencyList countryAdjacency) {
		this.countryAdjacency = countryAdjacency;
	}

	/**
	 * 
	 * @param countryId1
	 * @param countryId2
	 * @return
	 */
	public boolean isAdjacentCountry(int countryId1, int countryId2) {
		return countryAdjacency.isAdjacent(countryId1, countryId2);
	}

	/**
	 * This method gets the player by playerName
	 * 
	 * @param playerName
	 * @return name of the player if there is any otherwise it return numm
	 */
	public Player getPlayerByName(String playerName) {
		for (Player player : players) {
			if (player.getPlayerName().equalsIgnoreCase(playerName))
				return player;
		}
		return null;
	}

	/**
	 * This method return the total armies a player has
	 * 
	 * @param playerName
	 * @return
	 */
	public int totalArmiesAPlayerHas(String playerName) {

		int totalArmies = 0;

		for (Country country : getAllCountries()) {
			if (country.getPlayerName().equalsIgnoreCase(playerName))
				totalArmies += country.getArmies();
		}

		return totalArmies;
	}

	

	

}

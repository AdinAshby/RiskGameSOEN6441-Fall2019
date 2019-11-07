package model;

import java.util.ArrayList;

/**
 * 
 * This is Player class, in which we define our attributes of a Player.
 * 
 * @author
 * 
 */
public class Player implements Subject {

	/**
	 * PlayerName is name of the player corresponding to the ID.
	 */
	private String playerName;

	/**
	 * List of all countryIDs that the player owns.
	 */
	private int[] countryIDs;

	private double percentageControlled = 0.00;
	private String[] continentsControlled;
	private int totalNumberOfArmies = 0;
	private ArrayList<Card> cards;
	private int playerCountForCard;

	private boolean allowToGetCard;

	private int counterForPhases;

	private ArrayList<Observer> observersForPhases = new ArrayList<Observer>();
	private ArrayList<Observer> observersForWorldDomination = new ArrayList<Observer>();

	/**
	 * This is Player constructor method for initializing PlayerName and countryId
	 * 
	 * @param playerID   A unique ID for each player.
	 * @param playerName Corresponding player name.
	 * @param countryID  List of all countries (IDs) that the player owns.
	 */
	public Player(String playerName, int[] countryID) {
		this.playerName = playerName;
		this.countryIDs = countryID;
		cards = new ArrayList<Card>();
		playerCountForCard = 0;
	}

	public void addOneToCardCounter() {
		playerCountForCard++;
	}

	/**
	 * This is Getter method for PlayerName.
	 * 
	 * @return playerName Returns playerName as a String.
	 */
	public String getPlayerName() {
		return playerName;
	}

	public int getplayerCountForCard() {

		return playerCountForCard;

	}

	public String getCardNames() {
		String cardsList="Cards: -";
		for(Card c:cards) {
			cardsList=c.getCardType();
		}
		return cardsList;

	}

	public ArrayList<Card> getCards() {

		return cards;

	}

	public void setCards(ArrayList<Card> cardList) {

		for (int i = 0; i < cardList.size(); i++) {

			cardList.remove(i);

		}

		for (Card cardItem : cardList) {

			this.cards.add(cardItem);

		}

	}

	public void addCard(Card cardToAdd) {

		this.cards.add(cardToAdd);

	}

	public boolean isMoreThanFive() {

		if (cards.size() >= 5)

			return true;

		else

			return false;

	}

	public boolean getAllowingCardStatus() {

		return this.allowToGetCard;

	}

	public void setAllowingStatus(boolean status) {

		this.allowToGetCard = status;

	}

	/**
	 * This is Getter Method for CountryIDs.
	 * 
	 * @return getCountryID Returns CountryIDs as an array of integers.
	 */
	public int[] getCountryIDs() {
		return countryIDs;
	}

	/**
	 * This is Setter Method for CountryIDs.
	 * 
	 * @param countriesIDs Sets a list of countriesIDs to the corresponding field.
	 */
	public void setCountryIDs(int[] countriesIDs) {
		this.countryIDs = countriesIDs;
	}

	public double getPercentageControlled() {
		return percentageControlled;
	}

	public String[] getContinentsControlled() {
		return continentsControlled;
	}

	public int getTotalNumberOfArmies() {
		return totalNumberOfArmies;
	}

	public boolean isAttackValid(MapBuilder mapBuild, int attackerNumDice, Country attackerCountry,
			Country attackingCountry, boolean enablePrint) {
		boolean isValid = true;
		if (attackerNumDice > attackingCountry.getArmies()) {
			if(enablePrint)
				System.out.println(
						"attacking dice ("+attackerNumDice+") should not be more than the number of armies contained in the attacking country");
			isValid = false;
		}else if (attackerNumDice > 3) {
			if(enablePrint)
				System.out.println(
						"attacking dice ("+attackerNumDice+") should not be more than 3");
			isValid = false;
		} else if (!mapBuild.isAdjacentCountry(attackerCountry.getCountryId(), attackingCountry.getCountryId())) {
			if(enablePrint)
				System.out.println("Countries are not adjacent");
			isValid = false;

			/// Country should be owned and attacking country for opponent
		}
		return isValid;
	}

	public boolean isAttackPossible(MapBuilder mapBuild) {
		boolean isAttackPossible = false;
		for (int countyId : countryIDs) {
			// Country country=mapBuild.getCountryById(countyId);
			//			System.out.println("Checking " + countyId);
			ArrayList<Integer> CountryAdjList = mapBuild.getCountryAdjacency().getVertexAdjacency(countyId);
			//	System.out.println(CountryAdjList); //Arrays.toString(

			for (int adjCountry : CountryAdjList) {
				if (!contains(countryIDs, adjCountry)) {
					for(int i=1;i<4;i++) {
						if (isAttackValid(mapBuild, i, mapBuild.getCountryById(countyId),
								mapBuild.getCountryById(adjCountry), false)) {
							isAttackPossible = true;
						}
					}
				}
			}

		}

		return isAttackPossible;
	}

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

	public int getCounterForPhases() {
		return counterForPhases;
	}

	public void setCounterForPhases(int counterForPhases) {
		this.counterForPhases = counterForPhases;
		notifyObserverForPhases();
	}

	@Override
	public void registerPhaseObserver(Observer addObserver) {
		observersForPhases.add(addObserver);
	}

	@Override
	public void registerWorldDominationObserver(Observer addObserver) {
		observersForWorldDomination.add(addObserver);
	}

	@Override
	public void unregisterPhaseObserver(Observer removeObserver) {
		observersForPhases.remove(removeObserver);
	}

	@Override
	public void unregisterWorldDomination(Observer removeObserver) {
		observersForWorldDomination.remove(removeObserver);
	}

	@Override
	public void notifyObserverForWorldDomination() {
		for(Observer observer : observersForWorldDomination) {
			observer.update(percentageControlled, totalNumberOfArmies, continentsControlled);
		}
	}

	@Override
	public void notifyObserverForPhases() {
		for(Observer observer : observersForPhases) {
			observer.update(counterForPhases, playerName);
		}
	}

	public ArrayList<Observer> getObserversForPhases() {
		return observersForPhases;
	}

	public ArrayList<Observer> getObserversForWorldDomination() {
		return observersForWorldDomination;
	}

	public void calculateTotalNumberOfArmies() {
	
		for(Integer each : countryIDs) {
			totalNumberOfArmies += MapBuilder.getInstance().getCountryById(each).getArmies();
		}
		
		notifyObserverForWorldDomination();
	}
	
	public void calculateContinentControlled() {
		MapBuilder.getInstance().continentsOwnedByPlayer(playerName);
		notifyObserverForWorldDomination();
	}
	
	public void calculatePercentageControlled() {
		percentageControlled = getCountryIDs().length * 100 / MapBuilder.getInstance().getAllCountries().size();
		notifyObserverForWorldDomination();
	}
}
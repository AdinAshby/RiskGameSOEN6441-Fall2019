package controller;

import model.Player;
import view.RiskUI;

/**
 * This is a risk controller class
 * 
 * @author f_yazdan
 *
 */
public class RiskController {

	/**
	 * static instance of the RiskController
	 */
	private static RiskController instance;
	/**
	 * private riskUI
	 */
	private RiskUI riskUI;
	/**
	 * private players
	 */
	private Player[] players;

	/**
	 * private RiskController
	 */
	private RiskController() {

	}

	/**
	 * This method gets the instance of RiskController class
	 * @return instance
	 */
	public static RiskController getInstance() {
		if (instance == null) {
			instance = new RiskController();
			return instance;
		} else {
			return instance;
		}
	}

	/**
	 * This method gets all the players
	 * @return players
	 */
	public Player[] getPlayers() {
		return this.players;
	}

}

package controller;

import model.Player;
import view.RiskUI;

public class RiskController {

	private static RiskController instance;
	private RiskUI riskUI;
	private Player[] players;
	
	private RiskController() {
		
	}
	
	public static RiskController getInstance() {
		if (instance == null) {
			instance = new RiskController();
			return instance;
		} else {
			return instance;
		}
	}
	
	public Player[] getPlayers() {
		return this.players;
	}
	
	
	
	
	
}

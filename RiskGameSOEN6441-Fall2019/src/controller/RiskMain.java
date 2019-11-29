package controller;

import view.RiskUI;

/**
 * This is a Risk Main Driver
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 */
public class RiskMain {
	
	/**
	 * Create an object of the UI risk to start the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RiskUI rui = new RiskUI();
		try { 
			rui.RiskUIStartTheGame();
		} catch (Exception e) {
		
			e.printStackTrace();
		}

	}

}

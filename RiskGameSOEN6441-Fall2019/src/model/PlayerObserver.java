package model;

import java.util.ArrayList;

import view.MapView;
/**
 * This class is one of observer classes implementing the observer interface
 * @author s_shehna
 *
 */
public class PlayerObserver implements Observer {
	
	MapView theMapView = new MapView();
	
	private Subject player;
	
	public PlayerObserver(Subject player) {
		//this.player = player;
		//this.player.registerPhaseObserver(this);
		//this.player.registerWorldDominationObserver(this);
	}
/** 
 * This method is the update method for world domination view
 */
	@Override
	public void update(double percentageControlled, int totalNumberOfArmies,
			ArrayList<String> continentsControlled, MapBuilder mapBuild) {
		//System.out.println(mapBuild.getPlayers());
		theMapView.showPlayersWorldDomination(mapBuild.getPlayers());
		
	}
	/**
	 * This method is update method for game phases view
	 */
	@Override
	public void update(int counterForPhases, String playerName) {

		theMapView.showPhaseView(counterForPhases, playerName);
	}

}

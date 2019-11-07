package model;

import view.MapView;

public class PlayerObserver implements Observer {
	
	MapView theMapView = new MapView();
	
	private Subject player;
	
	public PlayerObserver(Subject player) {
		//this.player = player;
		//this.player.registerPhaseObserver(this);
		//this.player.registerWorldDominationObserver(this);
	}

	@Override
	public void update(double percentageControlled, int totalNumberOfArmies,
			String[] continentsControlled) {
		
		theMapView.showPlayersWorldDomination(MapBuilder.getInstance().getPlayers());
	}
	
	@Override
	public void update(int counterForPhases, String playerName) {

		theMapView.showPhaseView(counterForPhases, playerName);
	}

}

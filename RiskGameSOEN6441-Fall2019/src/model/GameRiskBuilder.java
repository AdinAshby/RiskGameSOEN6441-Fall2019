package model;

import java.io.Serializable;

import controller.Game.Phase;

public class GameRiskBuilder extends GameBuilder implements Serializable{
	//default serialVersion id
    private static final long serialVersionUID = 1L;
	private MapGeo mapGeo;
	private Player turnPlayer;
	private Phase phase;

    
	public GameRiskBuilder() {
		
	}
	
	public GameRiskBuilder(MapGeo mapGeo, Player turnPlayer, Phase phase) {
		game.setMapGeo(mapGeo);
		game.setTurnPlayer(turnPlayer);
		game.setPhase(phase);
	}

	public MapGeo getMapGeo() {
		return this.mapGeo;
	}

	public void setMapGeo(MapGeo mapGeo) {
		this.mapGeo=mapGeo;
	}

	public Player getTurnPlayer() {
		return this.turnPlayer;
	}

	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer=turnPlayer;
	}

	public Phase getPhase() {
		return this.phase;
	}

	public void setPhase(Phase phase) {
		this.phase=phase;
	}

	@Override
	void buildTurnPlayer() {
		game.setTurnPlayer(turnPlayer);
		
	}

	@Override
	void buildPhase() {
		game.setPhase(phase);
		
	}

	@Override
	void buildMapGeo() {
		game.setMapGeo(mapGeo);
		
	}

}

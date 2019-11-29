package model;

import java.io.Serializable;

import controller.Game.Phase;
/**
 * This class is a concrete builder class extending GameBuilder class
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 *
 */
public class GameRiskBuilder extends GameBuilder implements Serializable {
	// default serialVersion id
	private static final long serialVersionUID = 1L;
	private MapGeo mapGeo;
	private Player turnPlayer;
	private Phase phase;

	/**
	 * This is a default constructor
	 */
	public GameRiskBuilder() {

	}

	/**
	 * This is a parameterized constructor
	 * 
	 * @param mapGeo
	 * @param turnPlayer
	 * @param phase
	 */
	public GameRiskBuilder(MapGeo mapGeo, Player turnPlayer, Phase phase) {
		game.setMapGeo(mapGeo);
		game.setTurnPlayer(turnPlayer);
		game.setPhase(phase);
	}

	/**
	 * This is for getting a mapGeo
	 */
	public MapGeo getMapGeo() {
		return this.mapGeo;
	}

	/**
	 * This is for setting mapGeo
	 */
	public void setMapGeo(MapGeo mapGeo) {
		this.mapGeo = mapGeo;
	}

	/**
	 * This is for getting player turn
	 */
	public Player getTurnPlayer() {
		return this.turnPlayer;
	}

	/**
	 * This is for setting player turn
	 */
	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}

	/**
	 * This is for getting game phase
	 */
	public Phase getPhase() {
		return this.phase;
	}

	/**
	 * This is for setting a game phase
	 */
	public void setPhase(Phase phase) {
		this.phase = phase;
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

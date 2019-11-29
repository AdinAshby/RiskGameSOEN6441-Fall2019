package controller;

import java.io.Serializable;

import model.GameBuilder;
import model.GameDirector;
import model.GameRiskBuilder;
import model.MapGeo;
import model.Player;

/**
 * This class implements the serializable 
 * @author s_shehna
 * @author f_yazdan
 */

public class Game implements Serializable {

	public enum Phase {
		GAME_START, MAP_LOAD, ADD_PLAYERS, PLACE_ARMIES, REINFORCEMENT, ATTACK, FORTIFICATION, GAME_OVER, TOURNAMENT
	}

	private Phase phase;
	private Player turnPlayer;
	private MapGeo mapGeo;
	private Player winner;
/**
 * This method returns the winner of game
 * @return player object
 */
	public Player getWinner() {
		return winner;
	}
/**
 * This method sets the winner 
 * @param winner
 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}
/**
 * This method gets the turn of player
 * @return player object
 */
	public Player getTurnPlayer() {
		return turnPlayer;
	}
/**
 * This method sets the turn of player
 * @param turnPlayer 
 */
	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}
/**
 * This method gets the mapGeo 
 * @return mapGeo object
 */
	public MapGeo getMapGeo() {
		return mapGeo;
	}
/**
 * This method sets the mapGeo object
 * @param mapGeo
 */
	public void setMapGeo(MapGeo mapGeo) {
		this.mapGeo = mapGeo;
	}
/**
 * constructor of game class
 */
	public Game() {
		this.phase = Phase.GAME_START;
	}
/**
 * This method gets the phase of game
 * @return
 */
	public Phase getPhase() {
		return phase;
	}
/**
 * This method sets the phase of game
 * @param phase
 */
	public void setPhase(Phase phase) {
		this.phase = phase;
	}
/**
 * This method saves a  game
 * @author s_shehna
 * @author f_yazdan
 */
	public void saveGame(String mapFileName, MapGeo mapGeo, Player player, Phase phase) {
		try {

			GameBuilder gameBuilder = new GameRiskBuilder();
			gameBuilder.setMapGeo(mapGeo);
			gameBuilder.setTurnPlayer(player);
			gameBuilder.setPhase(phase);

			GameDirector gameDirector = new GameDirector();
			gameDirector.setBuilder(gameBuilder);
			gameDirector.saveGame(mapFileName);

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}
/**
 * This method loads a saved game
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 *
 */
	public boolean loadGame(String mapFileName) {
		/**
		 * boolean isvalid
		 */
		boolean isvalid = false;
		/**
		 * gamebuilder object
		 */
		GameBuilder gameBuilder = new GameRiskBuilder();
		/**
		 * gamedirectore object
		 */
		GameDirector gameDirector = new GameDirector();
		
		gameDirector.setBuilder(gameBuilder);
		Game game = gameDirector.loadGame(mapFileName);
		if (game != null) {
			this.setMapGeo(game.getMapGeo());
			this.setPhase(game.getPhase());
			this.setTurnPlayer(game.getTurnPlayer());
			isvalid = true;
		} else {
			System.out.println("File could not be loaded");
			isvalid = false;
		}

		return isvalid;
	}

}

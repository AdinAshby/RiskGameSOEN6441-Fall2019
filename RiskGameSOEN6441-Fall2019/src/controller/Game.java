package controller;

import java.io.Serializable;

import model.GameBuilder;
import model.GameDirector;
import model.GameRiskBuilder;
import model.MapGeo;
import model.Player;

public class Game implements Serializable {
	 public Player getTurnPlayer() {
		return turnPlayer;
	}

	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}

	public MapGeo getMapGeo() {
		return mapGeo;
	}

	public void setMapGeo(MapGeo mapGeo) {
		this.mapGeo = mapGeo;
	}
	public enum Phase {
	     GAME_START,   
		 MAP_LOAD,
	        ADD_PLAYERS,
	        PLACE_ARMIES,
	        REINFORCEMENT,
	        ATTACK,
	        FORTIFICATION,
	        GAME_OVER,
	        TOURNAMENT
	    }
	 
	 private Phase phase;
	 private Player turnPlayer;
	 private MapGeo mapGeo;
	 
	 public Game(){
		 this.phase = Phase.GAME_START;
	 }
	 
	 public Phase getPhase() {
	        return phase;
	    }
	 public void setPhase(Phase phase) {
	        this.phase = phase;
	    }

	public void saveGame(String mapFileName, MapGeo mapGeo, Player player, Phase phase) {
			try {
						GameBuilder gameBuilder=new GameRiskBuilder();
						gameBuilder.setMapGeo(mapGeo);
						gameBuilder.setTurnPlayer(player);
						gameBuilder.setPhase(phase);
						GameDirector gameDirector= new GameDirector();
						gameDirector.setBuilder(gameBuilder);
						gameDirector.saveGame(mapFileName);
						//mapDomination2=mapDirector.getMapGeo();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	public void loadGame(String mapFileName) {
		GameBuilder gameBuilder=new GameRiskBuilder();
		GameDirector gameDirector= new GameDirector();
		gameDirector.setBuilder(gameBuilder);
		Game game=gameDirector.loadGame(mapFileName);
		this.setMapGeo(game.getMapGeo());
		this.setPhase(game.getPhase());
		this.setTurnPlayer(game.getTurnPlayer());
//		mapGeo.mapDominationCopy(mapGeo);
		
	}	
	 
}

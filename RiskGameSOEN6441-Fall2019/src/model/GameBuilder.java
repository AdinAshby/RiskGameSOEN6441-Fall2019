package model;

import controller.Game;
import controller.Game.Phase;

public abstract class GameBuilder {

		  /**
		   * Map to be constructed by the builder
		   */
		  protected Game game;
		  /**
		   * Get the constructed Map from the Builder
		   */
		  public Game getGame(){
		    return game;
		  }
		  
		  /**
		   * Create a new unspecified Map that 
		   * will be eventually build by calling the 
		   * following abstract methods in a concrete 
		   * class derived from the MapGeo class
		   */
		  public void createNewGame(){
			  game = new Game();
		  }
		  abstract void buildTurnPlayer();
		  abstract void buildPhase();
		  abstract void buildMapGeo();
		  public abstract MapGeo getMapGeo() ;
		  public abstract void setMapGeo(MapGeo mapGeo);
		  public abstract Player getTurnPlayer();
		  public abstract void setTurnPlayer(Player turnPlayer) ;
		  public abstract Phase getPhase() ;
		  public abstract void setPhase(Phase phase) ;

		}
 

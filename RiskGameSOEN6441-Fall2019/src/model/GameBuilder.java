package model;

import controller.Game;

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
		}
 

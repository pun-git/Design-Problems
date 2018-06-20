package com.tw.bt.api;

/**
 * Interface for Game
 * @author puagarwa
 *
 */
public interface Game {
	
	//To initialize Game
	public void init() throws Exception;
	//To start game
	public void startGame();
	//to post clean up
	public void clean();

}

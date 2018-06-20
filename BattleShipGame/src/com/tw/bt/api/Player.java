package com.tw.bt.api;

import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;
import com.tw.bt.model.Target;

/**
 * Interface for Player
 * @author puagarwa
 *
 */
public interface Player {
	//To place ship
	public void placeShip(Ship ship,Location location,Dimension dimension);
	//To play
	public boolean play(Target target) ;
	//To notify target player-ship
	public boolean notify(Location l);
}

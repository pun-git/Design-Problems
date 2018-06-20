package com.tw.bt.api;

import java.util.Map;

import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;

/**
 * Interface for Battle Area
 * @author puagarwa
 *
 */
public interface BattleArea {
	
	void init(Map<String, Object> initProp);
	void placeShip(Ship ship,Location location,Dimension dimension);
	boolean isOccupied(Location location,Dimension dimension);
	void show();
	void clean();
}

package com.tw.bt.factory;

import com.tw.bt.api.BattleArea;
import com.tw.bt.api.Ship;
import com.tw.bt.api.ShipStrength;
import com.tw.bt.bp.BattleShipImpl;
import com.tw.bt.bp.ShipType;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;

/**
 * Factory to create Ship Object 's
 * @author puagarwa
 *
 */
public class ShipFactory {
	
	/**
	 * Get the ship based on the type and strength
	 * @param shipType
	 * @param strength
	 * @return
	 */
	public static Ship buildShip(ShipType shipType,ShipStrength strength,Dimension dimension,Location location,BattleArea bettleArea,int missileCount){
		if(shipType==ShipType.BETTLESHIP){
			return new BattleShipImpl(strength,dimension,location,bettleArea,missileCount);
		}else{
			throw new RuntimeException("Invalid Ship !");
		}
	}

}

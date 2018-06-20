package com.tw.bt.bp;

import com.tw.bt.api.ShipStrength;
import com.tw.bt.constant.GameConstant;

/**
 * To provide implementation of Q type of Battle Ship
 * @author puagarwa
 *
 */
public class QShipStrength implements ShipStrength{
	
	/**
	 * get ship strength
	 */
	@Override
	public Integer getShipStrength() {
		return GameConstant.qCelStrength;
	}
	
	
	@Override
	public String toString() {
		return "Q";
	}
	
}

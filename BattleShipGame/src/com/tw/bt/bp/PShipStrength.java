package com.tw.bt.bp;

import com.tw.bt.api.ShipStrength;
import com.tw.bt.constant.GameConstant;

/**
 * Strength of P Type Ship
 * @author puagarwa
 *
 */
public class PShipStrength implements ShipStrength{

	/**
	 * Get Strength of P Type Ship
	 */
	@Override
	public Integer getShipStrength() {
		return GameConstant.pCelStrength;
	}
	
	
	@Override
	public String toString() {
		return "P";
	}
}

package com.tw.bt.bp;

import com.tw.bt.api.ShipStrength;

/**
 * Ship Strength Type Enum
 * @author puagarwa
 *
 */
public enum ShipStrengthType {
	P(PShipStrength.class),Q(QShipStrength.class);
	
	private Class<ShipStrength> _class;
	ShipStrengthType(Class _class){
		this._class=_class;
	}
	
	
	public Class<ShipStrength> getStrengthType(){
		return this._class;
	}
}

package com.sj.model;

public class Bulb extends Equipment{
	
	public Bulb(Double perUnitPower, EquipmentStatus status) {
		this.perUnitPower = perUnitPower;
		this.status = status;
	}

	@Override
	public String getName() {
		return "Bulb";
	}
	
	@Override
	public void switchOn() {
		this.status = EquipmentStatus.ON;
	}
	
	@Override
	public void switchOff() {
		this.status = EquipmentStatus.OFF;
	}
	
	@Override
	public void reset() {
		switchOff();
	}
}

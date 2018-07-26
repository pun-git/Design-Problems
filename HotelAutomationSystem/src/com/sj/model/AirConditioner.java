package com.sj.model;

public class AirConditioner extends Equipment{

	
	public AirConditioner(Double perUnitPower, EquipmentStatus status) {
		this.perUnitPower = perUnitPower;
		this.status = status;
	}
	
	@Override
	public String getName() {
		return "AC";
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

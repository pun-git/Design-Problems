package com.sj.model;

public abstract class Equipment {
	
	protected double perUnitPower;
	protected EquipmentStatus status = EquipmentStatus.OFF;
	
	public abstract void switchOn();
	public abstract void switchOff();
	public abstract void reset();
	public abstract String getName();
	
	public EquipmentStatus getStatus(){
		return status;
	}
}

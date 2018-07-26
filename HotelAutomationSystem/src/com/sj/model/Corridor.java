package com.sj.model;

import java.util.ArrayList;
import java.util.List;

import com.sj.factory.EquipmentFactory;

abstract public class Corridor {

	protected Integer corridorId;
	protected List<Equipment> listOfEquipment;
	protected double powerConsumed;
	protected long lastMovement;
	
	public Corridor(Integer corridorId) {
		this.corridorId = corridorId;
		this.listOfEquipment = new ArrayList<>();
	}

	public void addEquipment(Equipment e) {
		listOfEquipment.add(e);
	}
	
	public Integer getCorridorId() {
		return corridorId;
	}
	
	protected static Equipment buildEquipment(String equipmentType, Double equiptmentPerUnit, EquipmentStatus status) {
		return EquipmentFactory.getEquipment(equipmentType, equiptmentPerUnit, status);
	}
	
	public List<Equipment> getListOfEquipment(){
		return listOfEquipment;
	}
	public double getPowerConsumed() {
		return powerConsumed;
	}

	public void setPowerConsumed(double powerConsumed) {
		this.powerConsumed = powerConsumed;
	}

	public long getLastMovement() {
		return lastMovement;
	}

	public void setLastMovement(long lastMovement) {
		this.lastMovement = lastMovement;
	}
	
	public double calculatePowerConsumption(Equipment equipment) {
		powerConsumed+=equipment.perUnitPower;
		return powerConsumed;
	}
	
	abstract public String getName();
	
	@Override
	public boolean equals(Object obj) {
		return this.getCorridorId().equals(((Corridor)obj).getCorridorId());
	}
	
	@Override
	public int hashCode() {
		return corridorId;
	}
}

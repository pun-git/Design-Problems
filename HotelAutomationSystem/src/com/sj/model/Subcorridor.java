package com.sj.model;

public class Subcorridor extends Corridor{
	
	final String name = "Subcorridor";
	
	public Subcorridor(Integer subCorridorId) {
		super(subCorridorId);
		listOfEquipment.add(buildEquipment("Bulb",5d,EquipmentStatus.OFF));
		listOfEquipment.add(buildEquipment("AirConditioner",10d, EquipmentStatus.OFF));
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return corridorId;
	}
}

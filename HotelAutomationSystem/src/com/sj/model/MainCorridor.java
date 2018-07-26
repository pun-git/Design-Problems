package com.sj.model;

public class MainCorridor extends Corridor {

	final String name = "MainCorridor";
	
	public MainCorridor(Integer subCorridorId) {
		super(subCorridorId);
		listOfEquipment.add(buildEquipment("Bulb",5d,EquipmentStatus.OFF));
		listOfEquipment.add(buildEquipment("AirConditioner",10d, EquipmentStatus.OFF));
	}
	
	@Override
	public String getName() {
		return name;
	}
}

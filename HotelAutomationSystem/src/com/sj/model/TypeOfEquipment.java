package com.sj.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TypeOfEquipment {
	
	private static Map<String, Class<?>> mapOfImplementation = new ConcurrentHashMap<>();
	static {
		mapOfImplementation.put("Bulb", Bulb.class);
		mapOfImplementation.put("AirConditioner", AirConditioner.class);
	}
	public static Equipment getEquipment(String equipment, double perUnitPower, EquipmentStatus status) {
		try {
			return (Equipment)(mapOfImplementation.get(equipment)).getConstructor(Double.class, EquipmentStatus.class).newInstance(perUnitPower,status);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		
	}
	
}

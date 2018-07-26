package com.sj.model;

import java.util.List;

public class Hotel {
	
	private String name;
	private List<Floor> listOfFloor;
	
	public Hotel(String name, List<Floor> listOfFloor) {
		this.name = name;
		this.listOfFloor = listOfFloor;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Floor> getListOfFloor() {
		return listOfFloor;
	}

}

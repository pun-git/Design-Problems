package com.sj.model;

import java.util.ArrayList;
import java.util.List;

public class Floor {

	final private Integer floorId;
	private List<Corridor> listOfCorridor = new ArrayList<>();
	
	public Floor(Integer floorId) {
		this.floorId = floorId; 
	}
	
	public void addCorridor(Corridor corridor) {
		listOfCorridor.add(corridor);
	}	
	
	public Integer getFloorId() {
		return floorId;
	}
	
	public List<Corridor> getListOfCorridor() {
		return listOfCorridor;
	}
	
	public double getMaxPower() {
		double main = 0;
		double sub = 0;
		for(Corridor corridor : listOfCorridor) {
			if(corridor instanceof Subcorridor) {
				sub++;
			}else {
				main++;
			}
		}
		return main*15 + sub*10;
	}
}

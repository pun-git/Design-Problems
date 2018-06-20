package com.tw.bt.model;


import java.util.ArrayList;
import java.util.List;

import com.tw.bt.api.Ship;

/**
 * Fleet is set of ships for a Player
 * @author puagarwa
 *
 */
public class Fleet {
	private List<Ship> ships;
	private boolean isMissileExauseted=false;

	public Fleet() {
		ships=new ArrayList<>();
	}
	
	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public Fleet(List<Ship> ships){
		this.ships=ships;
	}
	
	public Fleet(Ship ship){
		this.ships=new ArrayList<>();
		this.ships.add(ship);
	}
	
	
	public void addShip(Ship ship){
		this.ships.add(ship);
	}
	
	public void addShip(List<Ship> ships){
		this.ships.addAll(ships);
	}
	
	public List<Ship> getShips() {
		return ships;
	}
	

	public boolean isMissileExauseted() {
		return isMissileExauseted;
	}

	public void setMissileExauseted(boolean isMissileExauseted) {
		this.isMissileExauseted = isMissileExauseted;
	}
	
}

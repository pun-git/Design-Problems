package com.tw.bt.model;

import com.tw.bt.api.Ship;

/**
 * Holds cell object
 * @author puagarwa
 *
 */
public class Cell {
	private Location location;
	private Ship ship;
	
	public Cell(int rowId,int colId) {
		this.location=new Location(rowId, colId);
	}
	
	
	public Location getLocation() {
		return location;
	}
	
	
	public void markForShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return this.location.equals(((Cell)obj).getLocation());
	}
	
	@Override
	public int hashCode() {
		return location.hashCode();
	}
	
	@Override
	public String toString() {
		return (ship!=null)?ship.toString():"*";
	}
}

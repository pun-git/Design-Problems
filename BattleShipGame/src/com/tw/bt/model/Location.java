package com.tw.bt.model;

/**
 * Location of Ship Cell
 * @author puagarwa
 *
 */
public class Location {
	
	private int x;
	private int y;
	
	
	public Location(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Location loc=(Location)obj;
		return (this.x==loc.getX() && this.y==loc.getY());
	}
	
	@Override
	public int hashCode() {
		return x<<y+x;
	}
	
	@Override
	public String toString() {
		return this.x+","+this.y;
	}

}

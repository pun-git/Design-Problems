package com.tw.bt.model;

/**
 * Represent dimension of ship
 * @author puagarwa
 *
 */
public class Dimension {
	
	private int row;
	private int col;
	
	
	public Dimension(int row,int col) {
		this.row=row;
		this.col=col;
	}

	
	public int getRow() {
		return row;
	}
	
	
	public int getCol() {
		return col;
	}
}

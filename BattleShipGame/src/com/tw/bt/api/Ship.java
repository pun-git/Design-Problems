package com.tw.bt.api;


import java.util.List;

import com.tw.bt.model.Cell;

/**
 * Interface for Ship
 * @author puagarwa
 *
 */
public interface Ship {
	//To build Ship
	public void buildShip(List<Cell> listCell);
	//Missile Hit the ship
	public boolean isAHit(int x,int y);
	//To show Ship on Battle Area
	public void show();
	//To Fire missile
	public void fire(Cell cell,Player player);
	//Ship is sunk
	public boolean isSunk();
	//No more Missile
	public boolean isMissibleExausted();
}

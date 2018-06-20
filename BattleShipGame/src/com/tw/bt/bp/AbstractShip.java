package com.tw.bt.bp;

import java.util.HashMap;
import java.util.Map;

import com.tw.bt.api.BattleArea;
import com.tw.bt.api.Ship;
import com.tw.bt.api.ShipStrength;
import com.tw.bt.model.Cell;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;

/**
 * Generic ship implementation
 * @author puagarwa
 *
 */
public abstract class AbstractShip implements Ship{
	
	//to hold the cells BattleShip has on the Battle Space
	protected Map<Cell,Integer> cells=new HashMap<>();
	protected boolean isSunk=false;
	protected ShipStrength shipStrength;
	protected Dimension dimension;
	protected Location location;
	protected BattleArea battleArea;
	protected int missile;
	
	
	/**
	 * Check whether the ship has been hit
	 */
	@Override
	public boolean isAHit(int x,int y) {
		Cell c=new Cell(x, y);
		//if this is a hit then remove the cell from ship
		Integer strength=this.cells.get(c);
		
		
		if(strength!=null){
			--strength;
			if(strength==0){
				this.cells.remove(c);
			}else{
				this.cells.put(c,strength);
			}
			//if all squares been hit then declare the ship sunk
			if(this.cells.isEmpty()){
				this.isSunk=true;
			}
			return true;
		}
		
		return false;
		
	}
	
	
	/**
	 * Ship has sunk
	 */
	@Override
	public boolean isSunk() {
		return isSunk;
	}
	
}

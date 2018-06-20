package com.tw.bt.bp;

import java.util.List;

import com.tw.bt.api.BattleArea;
import com.tw.bt.api.Player;
import com.tw.bt.api.ShipStrength;
import com.tw.bt.exception.NoMoreMissileException;
import com.tw.bt.model.Cell;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;

/**
 * Battle Ship Implementation
 * @author puagarwa
 *
 */
public class BattleShipImpl extends AbstractShip {

	public BattleShipImpl(ShipStrength shipStrength,Dimension dimension,Location location,BattleArea battleArea,int missileCount) {
		this.shipStrength=shipStrength;
		this.dimension=dimension;
		this.location=location;
		this.battleArea=battleArea;
		this.missile=missileCount;
	}
	
	/**
	 * Build the ship using the squares
	 */
	@Override
	public void buildShip(List<Cell> listCells) {
		for(Cell cell:listCells){
			this.cells.put(cell,(this.shipStrength.getShipStrength()));
		}
	}
	
	/**
	 * No more missile then return
	 */
	@Override
	public boolean isMissibleExausted() {
		return (missile==0)?true:false;
	}

	/**
	 * Ship fire the missile
	 */
	@Override
	public void fire(Cell cell,Player player) {
		if(this.missile==0) throw new NoMoreMissileException("No more missiles left");
		this.missile--;
	}
	
	/**
	 * display the ship
	 */
	@Override
	public void show() {
		
	}

	@Override
	public String toString() {
		return this.shipStrength.toString();
	}
}

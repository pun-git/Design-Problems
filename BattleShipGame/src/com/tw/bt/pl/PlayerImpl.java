package com.tw.bt.pl;

import com.tw.bt.api.BattleArea;
import com.tw.bt.api.Player;
import com.tw.bt.api.Ship;
import com.tw.bt.exception.LocationAlreadyOccupiedException;
import com.tw.bt.exception.NoMoreMissileException;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Fleet;
import com.tw.bt.model.Location;
import com.tw.bt.model.Target;

/**
 * Implementation of Player
 * @author puagarwa
 *
 */
public class PlayerImpl implements Player{

	private Integer playerId;
	private BattleArea battleArea;
	private Fleet fleet=new Fleet();
	private int shipMissile=0;
	public PlayerImpl(BattleArea battleArea,int playerId) {
		this.battleArea=battleArea;
		this.playerId=playerId;
	}
	
	
	@Override
	public boolean play(Target target) {
		getShipForFiring().fire(target.getCell(), target.getPlayer());
		return target.getPlayer().notify(new Location(target.getCell().getLocation().getX(),target.getCell().getLocation().getY()));
	}
	
	/**
	 * Ge the ship who will fire missile
	 * @return
	 */
	private Ship getShipForFiring(){
		if(fleet.getShips().get(shipMissile).isMissibleExausted()){
			shipMissile++;
			if(fleet.getShips().size()==shipMissile){
				throw new NoMoreMissileException("no more missiles left");
			}
		}
		return fleet.getShips().get(shipMissile);
	}
	
	@Override
	public void placeShip(Ship ship,Location location,Dimension dimension) {
		if(this.battleArea.isOccupied(location, dimension)) {
			throw new LocationAlreadyOccupiedException("Location already occupied, canot place ship - ["+ship.toString()+"] at location - ["+location.toString()+"]");
		}
		this.battleArea.placeShip(ship,location,dimension);
		this.fleet.addShip(ship);
	}
	
	
	@Override
	public boolean notify(Location l) {
		for(Ship ship:this.fleet.getShips()){
			if(ship.isAHit(l.getX(), l.getY())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.playerId.equals(((PlayerImpl)obj).playerId);
	}
	
	@Override
	public int hashCode() {
		return this.playerId;
	}
	
}

package com.tw.bt.model;

import com.tw.bt.api.Player;

/**
 * Hold Target Object
 * @author puagarwa
 *
 */
public class Target {

	private Player player;
	private Cell cell;

	public Target(Player player,int x,int y) {
		this.player=player;
		this.cell=new Cell(x, y);
	}

	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}

	
	
}

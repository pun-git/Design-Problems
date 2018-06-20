package com.tw.bt.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tw.bt.api.BattleArea;
import com.tw.bt.api.Ship;
import com.tw.bt.constant.GameConstant;
import com.tw.bt.model.Cell;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;

/**
 * Implementation of battle area
 * @author puagarwa
 *
 */
public class BattleAreaImpl implements BattleArea{

	private Cell cells[][];
	
	/**
	 * To Initialize Battle Area
	 */
	@Override
	public void init(Map<String, Object> initProp) {
		Dimension bounteries=(Dimension)initProp.get(GameConstant.GAMEAREABOUNDERIE);
		int noOfRow=bounteries.getRow();
		int noOfCol=bounteries.getCol();
		this.cells=new Cell[noOfRow][noOfCol];
		for(int i=0;i<noOfRow;i++){
			for(int j=0;j<noOfCol;j++){
				this.cells[i][j]=new Cell(i, j);
			}
		}
	}
	
	/**
	 * Identify the cell based on the dimension and location of ship .
	 */
	@Override
	public void placeShip(Ship ship,Location location,Dimension dimension) {
		
		List<Cell> listCells=new ArrayList<>();
		for(int i=location.getX(),k=dimension.getRow();k>0;k--,i++){
			for(int j=location.getY(),l=dimension.getCol();l>0;j++,l--){
				this.cells[i][j].markForShip(ship);
				listCells.add(this.cells[i][j]);
			}
		}
		ship.buildShip(listCells);
	}
	
	/**
	 * Check whether the are required to place the ship is occupied
	 */
	@Override
	public boolean isOccupied(Location location,Dimension dimension){
		int row=dimension.getRow()+location.getX();
		int col=dimension.getCol()+location.getY();
		for(int i=location.getX();i<row;i++){
			for(int j=location.getY();j<col;j++){
				if(this.cells[i][j].getShip()!=null)return true;
			}
		}

		return false;
	}
	
	/**
	 * Post clean up
	 */
	@Override
	public void clean() {
		this.cells=null;
	}
	
	/**
	 * To show Battle Area
	 */
	@Override
	public void show() {
		for(Cell cell[]:this.cells){
			for(Cell cel:cell){
				System.out.print(cel);
			}
			System.out.println();
		}
		
	}
}

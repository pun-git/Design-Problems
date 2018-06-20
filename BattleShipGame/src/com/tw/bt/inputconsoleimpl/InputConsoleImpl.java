package com.tw.bt.inputconsoleimpl;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.tw.bt.api.InputConsole;
import com.tw.bt.bp.ShipStrengthType;
import com.tw.bt.bp.ShipType;
import com.tw.bt.constant.GameConstant;
import com.tw.bt.exception.InvalidBattleAreaDimensionException;
import com.tw.bt.exception.InvalidLocationOfBattleShipException;
import com.tw.bt.exception.InvalidShipStrengthException;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;
import com.tw.bt.utilities.Utility;

/**
 * Implementation of input console
 * @author puagarwa
 *
 */
public class InputConsoleImpl implements InputConsole{

	private Scanner scanner;
	private PrintStream print;
	
	public InputConsoleImpl(Scanner scanner, PrintStream print) {
		this.scanner=scanner;
		this.print=print;
	}
	
	/**
	 * Initialize the input console
	 */
	@Override
	public void init() {
		//this can be used in case we want some ui console
	}
	
	@Override
	public Map<String, Object> getInput() {
		try{
			Map<String, Object> property=new HashMap<>();
			property.put(GameConstant.NOPLAYERS, GameConstant.NOOFPLAYERS);
			print.println("Enter area Boundries !");
			//battle area dimension
			String battleAreaDimensionInput=scanner.nextLine();
			print.println("Area Boundry entered is - "+"["+battleAreaDimensionInput+"]");
			Dimension battleAreaDimension=Utility.getDimensionForArea(battleAreaDimensionInput);
			isValidBattleAreaDimension(battleAreaDimension); //validation of battle area dimension
			property.put(GameConstant.GAMEAREABOUNDERIE, battleAreaDimension);
			
			//no of players
			int noOfPlayers=Integer.parseInt(GameConstant.NOOFPLAYERS);				//hard coded the no of players
			//no of battle ships
			int noOfBettleShips=Integer.parseInt(GameConstant.NOOFBETTLESHIP);		//hard coded the no of battleships
			//battle ship type
			property.put(GameConstant.GAMETYPEOFSHIP, ShipType.BETTLESHIP);	//hard coded the ship type, can consume from input as well .	
			property.put(GameConstant.NOBETTLESHIP, noOfBettleShips); //hard coded the no of battle ships, can consume from input
			
			for(int i=1;i<=noOfBettleShips;i++){
					//input for battle ship strength type
					print.println("Strength Type of Battle Ship "+i+" !");
					String strengthType=scanner.nextLine();
					print.println("Ship Strength type entered is - "+strengthType);
					isValidStrengthType(strengthType);	//input strength validation
					property.put(GameConstant.GAMETYPEOFSHIPSTRENGTH+i, strengthType.toUpperCase()); 
					
					//input for battle ship dimension
					print.println("Dimension of Battle Ship "+i+" !");
					String battleShipDimensionInput=scanner.nextLine();
					print.println("Dimension of Battle Ship entered is - ["+battleShipDimensionInput+"]");		
					Dimension battleShipDimension=Utility.getDimension(battleShipDimensionInput);
					
					isValidShipDimension(battleShipDimension, battleAreaDimension); //validation for ship dimension
					property.put(GameConstant.DIMENSION+i, battleShipDimension);
					
					for(int j=1;j<=noOfPlayers;j++){
						//input for location of battle ship on battle area
						print.println("Location Of Battle Ship "+i+" For Player "+j+" !");
						String shipLocationInput= scanner.nextLine();
						print.println("Location of Battle Ship enetered is - "+shipLocationInput);
						Location shipLocation=Utility.getLocation(shipLocationInput);
						isValidShipLocation(shipLocation, battleAreaDimension,battleShipDimension);		
						property.put(GameConstant.LOCATION+j+i, shipLocation); //validation of ship location
						//input for count of missile per battle ship currently hard coded to 2
						property.put(GameConstant.COUNTOFMISSILE+j+i, GameConstant.DEFAULTMISSILECOUNT);
					}
			}
			
			return property;
		}finally {
		}
	}
	
	/**
	 * Is Battle Area dimension valid .
	 * @param dimension
	 * @return
	 */
	private boolean isValidBattleAreaDimension(Dimension dimension){
		//validate Battle Area
		//width validation
		if(dimension.getCol()>9  || dimension.getCol()<1){
			throw new InvalidBattleAreaDimensionException("Invalid dimension for Battle Area ! Width must be between 1 and 9");
		}
		//height validation
		if(dimension.getRow()<GameConstant.MINHEIGHTFORBA || dimension.getRow()>GameConstant.MAXHEIGHTFORBA){
			throw new InvalidBattleAreaDimensionException("Invalid dimension for Battle Area ! Height must be between  "+GameConstant.MAXHEIGHTFORBA+" and "+GameConstant.MINHEIGHTFORBA);
		}
				
		return true;
	}
	

	/**
	 * Is strength Type valid
	 * @param input
	 * @return
	 */
	private boolean isValidStrengthType(String input){
		ShipStrengthType[] type=ShipStrengthType.values();
		String str="";
		for(ShipStrengthType t:type){
			str+=t.name()+",";
		}
		try{
			if(input== null || input.isEmpty()){
				throw new InvalidShipStrengthException("Ship strength must be [ "+str+"]");
			}
			ShipStrengthType.valueOf(input);
		}catch(Exception e){
			throw new InvalidShipStrengthException("Ship strength must be [ "+str+"]");
		}
		
		return true;
	}
	
	/**
	 * Is ship dimension valid
	 * @param bShipDimension
	 * @param battleAreaDimension
	 * @return
	 */
	private boolean isValidShipDimension(Dimension bShipDimension,Dimension battleAreaDimension){
		//width validation
		if(bShipDimension.getCol()<=0 || bShipDimension.getCol()>battleAreaDimension.getCol()){
			throw new InvalidBattleAreaDimensionException("Width of Bettle Ship must be 1 to "+battleAreaDimension.getCol());
		}
		//height validation
		if(bShipDimension.getRow()<GameConstant.MINHEIGHTFORBA || bShipDimension.getRow()>battleAreaDimension.getRow()){
			throw new InvalidBattleAreaDimensionException("Height of Bettle Ship must be "+GameConstant.MINHEIGHTFORBA+" to "+battleAreaDimension.getRow());
		}
		return true;
	}
	
	/**
	 * Is ship location valid
	 * @param location
	 * @param dimension
	 * @return
	 */
	private boolean isValidShipLocation(Location location,Dimension dimension,Dimension battleShipDimension){
		//height validation
		if(location.getX()<0 || location.getX()>dimension.getCol()){
			throw new InvalidLocationOfBattleShipException("Location of Battle Ship must be 1 to "+dimension.getCol());
		}
		
		//width validation
		if(location.getY()<0 || location.getY()>dimension.getRow()){
			throw new InvalidLocationOfBattleShipException("Location of Battle Ship must be 1 to "+dimension.getRow());
		}
		
		//validate with the battle area
		if((location.getX()+battleShipDimension.getRow())> dimension.getRow()){
			throw new InvalidLocationOfBattleShipException("Location ["+location.getX()+","+location.getY()+"] of Battle Ship must be with in the Battle Area Limits");		
		}
		
		if((location.getY()+battleShipDimension.getCol())>dimension.getCol()){
			throw new InvalidLocationOfBattleShipException("Location ["+location.getX()+","+location.getY()+"] of Battle Ship must be with in the Battle Area Limits");		
		}
		return true;
	}
	
	@Override
	public void clean() {
		this.scanner.close();
	}
	
}

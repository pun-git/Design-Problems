package com.tw.bt.utilities;

import java.lang.reflect.InvocationTargetException;

import com.tw.bt.exception.InvalidBattleAreaDimensionException;
import com.tw.bt.exception.InvalidLocationOfBattleShipException;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Location;

/**
 * Utility Methods
 * @author puagarwa
 *
 */
public class Utility {
	
	/**
	 * To get the index of the character
	 * @param chat
	 * @return
	 */
	public static int getIndexOfChar(String chat){
		return ((int)(chat.toLowerCase().toCharArray()[0])-97);
	}

	
	/**
	 * build dimension object from string .
	 * @param dimension
	 * @return
	 */
	public static Dimension getDimension(String dimension){
		validateFor2InputArray(dimension, InvalidBattleAreaDimensionException.class, "Battle area dimension must have two valid Integer values ["+dimension+"]");
		String[] dimn=dimension.split("\\s+");
		if(!isValidNumber(dimn[0]))new InvalidBattleAreaDimensionException("Battle area dimension must have two valid Integer values ["+dimension+"]");
		if(!isValidNumber(dimn[1]))new InvalidBattleAreaDimensionException("Battle area dimension must have two valid Integer values  ["+dimension+"]");
		
		return new Dimension(Integer.parseInt(dimn[1]),Integer.parseInt(dimn[0])); // in sample they have considered first as column count and second as row count so using it here
	}
	
	/**
	 * build location object from string
	 * @param location
	 * @return
	 */
	public static Location getLocation(String location){
		if(location== null || location.isEmpty()||location.length()<=1)throw new InvalidLocationOfBattleShipException("Battle Location must a valid Integer column value ["+location+"]");
		if(!isValidNumber(location.substring(1)))throw new InvalidLocationOfBattleShipException("Battle Location must a valid Integer column value ["+location.substring(1)+"]");
		return new Location(getIndexOfChar(((Character)location.charAt(0)).toString()), Integer.parseInt(location.substring(1))-1);
	}
	
	/**
	 * Is a valid number
	 * @param input
	 * @return
	 */
	public static boolean isValidNumber(String input){
		try{
			Integer.parseInt(input);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
	/**
	 * To validate 2 input array with some specific exception
	 * @param input
	 * @param exceptionClass
	 * @param msg
	 * @return
	 */
	public static  boolean validateFor2InputArray(String input,Class<? extends RuntimeException> exceptionClass, String msg){
		
		try{
			if(input==null || input.isEmpty() || input.split("\\s+").length!=2){ 
				throw exceptionClass.getConstructor(String.class).newInstance(msg);
			}
		}catch(NoSuchMethodException|InvocationTargetException|IllegalAccessException|InstantiationException e){
			new RuntimeException(e.getMessage());
		}
		return true;
	}
	
	
	/**
	 * build Dimension object from string for Area
	 * @param location
	 * @return
	 */
	public static Dimension getDimensionForArea(String dimension){
		validateFor2InputArray(dimension, InvalidBattleAreaDimensionException.class, "Battle Area must have a valid non null values  ["+dimension+"]");
		String[] d=dimension.split("\\s+");
		if(!isValidNumber(d[0]))throw new InvalidBattleAreaDimensionException("Battle Area row must have a valid Integer ["+d[0]+"]");
		return new Dimension(Integer.parseInt(d[0]),getIndexOfChar(d[1])+1); //used in like we did in dimension of ship
	}
	
	
}

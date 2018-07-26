package com.sj.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.sj.model.Corridor;
import com.sj.model.Floor;
import com.sj.model.Hotel;
import com.sj.model.MainCorridor;
import com.sj.model.Subcorridor;

public class HotelBuilder {


	/**
	 * NoOfFloor
	 * NoOfMainCorridor NoOfSubCorridor
	 * 
	 * @param file
	 */
	
	public static Hotel build(String file) {
		List<Floor> listOfFloor = new ArrayList<>();
		
		try(Scanner reader = new Scanner(HotelBuilder.class.getClassLoader().getResource(file).openStream())){
			Integer noOfFloors = Integer.parseInt(reader.nextLine());
			int floorId = 1;
			while(noOfFloors > 0) {
				Integer numberOfMainCorridors  = reader.nextInt(); 
				Integer numberOfSubCorridors  = reader.nextInt();
				listOfFloor.add(buildFloor(numberOfMainCorridors,numberOfSubCorridors,floorId++));
				noOfFloors--;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Hotel("", listOfFloor);
	}
	
	
	private static Floor buildFloor(Integer numberOfMainCorridors, Integer numberOfSubCorridors, Integer floorId) {
		Floor floor = new Floor(floorId);
		int i = 1;
		for( ; i <= numberOfMainCorridors ; i++) {
			floor.addCorridor(buildCorridor(i+10*floorId, MainCorridor.class));
		}
		i = 1;
		for( ; i <= numberOfSubCorridors ; i++) {
			floor.addCorridor(buildCorridor(i+10*floorId, Subcorridor.class));
		}
		return floor;
	}
	
	private static Corridor buildCorridor(Integer corridorId, Class<?> class_) {
		try {
			Corridor corridor = (Corridor)class_.getConstructor(Integer.class).newInstance(corridorId);
			return corridor;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}

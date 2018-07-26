package com.sj.controller.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.sj.builder.HotelBuilder;
import com.sj.constant.Constant;
import com.sj.controller.api.IMotionController;
import com.sj.model.Corridor;
import com.sj.model.Equipment;
import com.sj.model.Floor;
import com.sj.model.Hotel;
import com.sj.model.Subcorridor;
import com.sj.rule.EquipmentRule;

public class MotionControllerImpl implements IMotionController {

	final private Map<Integer, Corridor> subCorridorLastStatus = new ConcurrentHashMap<>();
	
	private Hotel hotel = null;
	
	private Thread statusUpdateWorker;
	
	@Override
	public void init(String file) {
		System.out.println("Initializing Motion contoller");
		
		hotel = HotelBuilder.build(file);
		
		intializeEquipment();
		
		updateStatusOnTime();
		
		System.out.println("Motion contoller initialized");
	}
	
	private void intializeEquipment() {
		for(Floor floor : hotel.getListOfFloor()) {
			for(Corridor corridor : floor.getListOfCorridor()) {
				if(corridor instanceof Subcorridor) {
					subCorridorLastStatus.put(corridor.getCorridorId(), corridor);
				}
				for(Equipment equipment : corridor.getListOfEquipment()) {
					EquipmentRule.applyOnInit(equipment, corridor);
				}
			}
		}
		
		printEquipmentStatus(hotel);
	}
	
	@Override
	public void detectMotionAndUpdate(Integer subCoridorId) {
		Corridor subCoridor = this.subCorridorLastStatus.get(subCoridorId);
		for(Equipment equipment : subCoridor.getListOfEquipment()) {
			EquipmentRule.applyOnMotion(equipment, subCoridor);
		}
		subCorridorLastStatus.put(subCoridor.getCorridorId(), subCoridor);
		printEquipmentStatus(hotel);
	}
	
	public void updateStatusOnTime(){
		statusUpdateWorker = new Thread(() ->{
				while(true) {
					boolean print = false;
					try {
					Thread.sleep(Constant.RESCANINTERVAL);
					for(Floor floor : hotel.getListOfFloor()) {
						boolean doPrint  = EquipmentRule.applyOnTotalPower(floor);
						if(doPrint) {
							print = doPrint;
						}
					}
					}catch(InterruptedException e) {
						System.out.println("Shutting down worker thread");
					}
					
					if(print) {
						System.out.println("===================================Change detected and update by controller==============================================");
						printEquipmentStatus(hotel);
						System.out.println("===================================########################################==============================================");
					}
				}
			}
		);
		
		statusUpdateWorker.start();
	}
	
	public void printEquipmentStatus(Hotel hotel) {
		for(Floor floor : hotel.getListOfFloor()) {
			System.out.println("==================Floor - "+floor.getFloorId()+" =========================");
			for(Corridor corridor : floor.getListOfCorridor()) {
				System.out.print(corridor.getName()+" "+corridor.getCorridorId()+" ");
				for(Equipment equipment : corridor.getListOfEquipment()) {
					System.out.print(equipment.getName() + " : " +equipment.getStatus().name()+" ");
				}
				System.out.println();
			}
		}
	}
	
	@Override
	public void deInit() {
		System.out.println("Shutting down monitoring Contoller");
		statusUpdateWorker.interrupt();
		subCorridorLastStatus.clear();
		for(Floor floor : hotel.getListOfFloor()) {
			for(Corridor corridor : floor.getListOfCorridor()) {
				for(Equipment equipment : corridor.getListOfEquipment()) {
					equipment.reset();
				}
			}
		}
		System.out.println("Monitoring Contoller has stopped");
	}

}

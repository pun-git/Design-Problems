package com.sj.rule;

import java.time.LocalDateTime;

import com.sj.constant.Constant;
import com.sj.model.AirConditioner;
import com.sj.model.Corridor;
import com.sj.model.Equipment;
import com.sj.model.EquipmentStatus;
import com.sj.model.Floor;
import com.sj.model.MainCorridor;
import com.sj.model.Subcorridor;
import com.sj.util.Util;

public class EquipmentRule {
	
	public static boolean applyOnInit(Equipment equipment, Corridor corridor) {
		boolean isChanged = false;;
		
		if(equipment instanceof AirConditioner) {
			equipment.switchOn();
			corridor.setLastMovement(System.currentTimeMillis());
			isChanged = true;
		}
		
		if(corridor instanceof MainCorridor) {
			if(Util.isNightTime(LocalDateTime.now())) {
				equipment.switchOn();
				corridor.setLastMovement(System.currentTimeMillis());
				isChanged = true;
			}
		}
		
		return isChanged;
	}
	
	public static boolean applyOnMotion(Equipment equipment, Corridor corridor) {
		boolean isChanged = false;;
		
		if(equipment instanceof AirConditioner) {
			equipment.switchOn();
			isChanged = true;
		}
		if(corridor instanceof Subcorridor) {
			if(Util.isNightTime(LocalDateTime.now())) {
				equipment.switchOn();
				corridor.setLastMovement(System.currentTimeMillis());
				isChanged = true;
			}
		}
		return isChanged;
	}

	public static boolean applyOnTimeOut(Equipment equipment, Corridor corridor) {
		
		boolean isChanged = false;;
		
		if((System.currentTimeMillis()-corridor.getLastMovement())/1000 > Constant.TIME_OUT_OFF  && equipment.getStatus() == EquipmentStatus.ON) {
			equipment.switchOff();
			corridor.calculatePowerConsumption(equipment);
			isChanged = true;
		}
		
		return isChanged;
	}
	
	public static boolean applyOnTotalPower(Floor floor) {
		
		boolean isChanged = false;;
		
		int max = 0;
		for(Corridor c : floor.getListOfCorridor()) {
			max += c.getPowerConsumed();
		}
		boolean maxPowerExceeded = (max > floor.getMaxPower())?true : false;
		
		for(Corridor c : floor.getListOfCorridor()) {
			for(Equipment e : c.getListOfEquipment()) {
				if((maxPowerExceeded || (System.currentTimeMillis()-c.getLastMovement())/1000 > Constant.TIME_OUT_OFF) && e.getStatus() == EquipmentStatus.ON) {
					e.switchOff();
					c.calculatePowerConsumption(e);
					c.setLastMovement(System.currentTimeMillis());
					isChanged = true;
				}
			}
		}
		
		return isChanged;
	}
}

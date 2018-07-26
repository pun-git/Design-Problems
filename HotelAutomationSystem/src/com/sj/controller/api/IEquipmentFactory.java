package com.sj.controller.api;

import com.sj.model.Equipment;
import com.sj.model.TypeOfEquipment;

public interface IEquipmentFactory {
	
	public Equipment getEquipment(TypeOfEquipment type);
	public void unRegisterEquipment();

}

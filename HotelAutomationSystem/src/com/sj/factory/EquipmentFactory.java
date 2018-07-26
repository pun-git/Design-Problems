package com.sj.factory;

import com.sj.model.Equipment;
import com.sj.model.EquipmentStatus;
import com.sj.model.TypeOfEquipment;

public class EquipmentFactory {

	public static Equipment getEquipment(String quipmentType, double powerPerUnit, EquipmentStatus status) {
		return TypeOfEquipment.getEquipment(quipmentType,powerPerUnit, status);
	}
}

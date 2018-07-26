package com.sj.util;

import java.time.LocalDateTime;

import com.sj.constant.Constant;

public class Util {

	public static boolean isNightTime(LocalDateTime time) {
		int hr = time.getHour();
		if(hr >= Constant.NIGHTSTART_TIME || hr < Constant.NIGHTEND_TIME) {
			return true;
		}
		return false;
	}
	
}

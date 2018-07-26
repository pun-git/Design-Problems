package com.sj.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sj.controller.api.IMotionController;
import com.sj.controller.impl.MotionControllerImpl;

public class TestDefaultState {

	private IMotionController iMotionController = null;
	
	@Before
	public void init() {
		iMotionController = new MotionControllerImpl();
		iMotionController.init("HotelDimentions.txt");
	}
	
	@Test
	public void tesControllerWithDefaultState() {
		
	}
	
	@After
	public void deInit() {
		try {
			Thread.sleep(900000000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		iMotionController.deInit();
	}
	
}

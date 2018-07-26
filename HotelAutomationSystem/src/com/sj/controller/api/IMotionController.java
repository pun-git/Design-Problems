package com.sj.controller.api;


public interface IMotionController {
	
	public void init(String file);
	public void detectMotionAndUpdate(Integer corridor);
	public void deInit();
	
}

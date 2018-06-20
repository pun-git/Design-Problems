package com.tw.bt.api;

import java.util.Map;

/**
 * Interface for Input console
 * @author puagarwa
 *
 */
public interface InputConsole {
	//initialize Input console
	public void init();
	//To get input
	public Map<String, Object> getInput();
	//Post clean up
	public void clean();

}

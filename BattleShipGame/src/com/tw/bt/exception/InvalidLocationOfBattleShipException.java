package com.tw.bt.exception;

/**
 * Exception for invalid ship location
 * @author puagarwa
 *
 */
public class InvalidLocationOfBattleShipException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLocationOfBattleShipException(String msg) {
		super(msg);
	}
}

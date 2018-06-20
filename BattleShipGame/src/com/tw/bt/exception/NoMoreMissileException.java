package com.tw.bt.exception;

/**
 * Exception occur once the ships for a player consumed all the missiles
 * @author puagarwa
 *
 */
public class NoMoreMissileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMoreMissileException(String message) {
		super(message);
	}
}

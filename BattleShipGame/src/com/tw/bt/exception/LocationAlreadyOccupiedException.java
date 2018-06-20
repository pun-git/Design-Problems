package com.tw.bt.exception;

/**
 * Location already occupied
 * @author puagarwa
 *
 */
public class LocationAlreadyOccupiedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocationAlreadyOccupiedException(String msg) {
		super(msg);
	}
}

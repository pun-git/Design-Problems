package com.tw.bt.exception;

/**
 * Exception for Invalid count of BettleShips
 * @author puagarwa
 *
 */
public class InvalidCountOfBattleShipException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2192279757477959858L;

	public InvalidCountOfBattleShipException(String msg) {
		super(msg);
	}
}

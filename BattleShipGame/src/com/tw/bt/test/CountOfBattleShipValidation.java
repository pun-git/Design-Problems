package com.tw.bt.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tw.bt.api.InputConsole;
import com.tw.bt.inputconsoleimpl.InputConsoleImpl;

/**
 * Validate Dimension of Battle Area
 * @author puagarwa
 *
 */
public class CountOfBattleShipValidation {


	InputConsole input;
	Scanner scanner;
	PrintStream writer;
	
	@Before
	public void init(){
		try{
			this.writer=new PrintStream(new ByteArrayOutputStream());
			this.scanner= new Scanner(AllTests.class.getClassLoader().getResource("resource/CountOfBattleShipValidation.txt").openStream());
			System.setOut(writer);
			this.input=new InputConsoleImpl(this.scanner,this.writer);
		}catch(Exception e){
		e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		this.input.init();
		this.input.getInput();
	}
	
	@After
	public void clean(){
		this.scanner.close();
		this.writer.close();
	}
}

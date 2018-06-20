package com.tw.bt.test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tw.bt.api.Game;
import com.tw.bt.game.GameImpl;

/**
 * Sample Use Case - its a draw
 * @author puagarwa
 *
 */
public class Draw {


	InputStream inputStream;
	Scanner scanner;
	Game game;
	PrintStream writer;

	@Before
	public void init(){
		try{
			this.writer=System.out;
			this.scanner= new Scanner(AllTests.class.getClassLoader().getResource("resource/Draw.txt").openStream());
			System.setOut(writer);
			this.game=new GameImpl(scanner,writer);
			this.game.init();
		}catch(Exception e){
		e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		play();
	}
	
	void play(){
		this.game.startGame();
	}
	
	@After
	public void clean(){
		this.game.clean();
	}
	
}

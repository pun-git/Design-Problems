package com.tw.bt.run;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.tw.bt.api.Game;
import com.tw.bt.game.GameImpl;

/**
 * Main class to run game .
 * @author puagarwa
 *
 */
public class Run {
	
	
	public static void main(String[] args) {
		try{
			InputStream inputStream=Run.class.getClassLoader().getResource("resource/Input.txt").openStream();
			Scanner scanner=new Scanner(inputStream);
			ByteArrayOutputStream outStream=new ByteArrayOutputStream();
			PrintStream writer=new PrintStream(outStream);
			writer=System.out;
			System.setOut(writer);
			Game game=new GameImpl(scanner,writer);
			game.init();
			game.startGame();
			game.clean();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}

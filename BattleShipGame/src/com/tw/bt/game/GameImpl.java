package com.tw.bt.game;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import com.tw.bt.api.BattleArea;
import com.tw.bt.api.BattleSea;
import com.tw.bt.api.Game;
import com.tw.bt.api.InputConsole;
import com.tw.bt.api.Player;
import com.tw.bt.api.Ship;
import com.tw.bt.api.ShipStrength;
import com.tw.bt.bp.ShipStrengthType;
import com.tw.bt.bp.ShipType;
import com.tw.bt.bs.BattleSeaImpl;
import com.tw.bt.constant.GameConstant;
import com.tw.bt.exception.NoMoreMissileException;
import com.tw.bt.factory.ShipFactory;
import com.tw.bt.inputconsoleimpl.InputConsoleImpl;
import com.tw.bt.model.Dimension;
import com.tw.bt.model.Fleet;
import com.tw.bt.model.Location;
import com.tw.bt.model.Target;
import com.tw.bt.pl.PlayerImpl;
import com.tw.bt.utilities.Utility;

/**
 * Implementation of Game
 * @author puagarwa
 *
 */
public class GameImpl implements Game {

	private BattleSea battleSea;
	private InputConsole inputConsole;
	private Map<String, Object> input;
	private Map<Player, Fleet> mapPlayer2Fleet=new HashMap<>();
	private Map<Player, BattleArea> mapPlayer2BattleArea=new HashMap<>();
	private Scanner reader;
	private PrintStream writer;
	
	public GameImpl(Scanner reader,PrintStream writer) {
		this.reader=reader;
		this.writer=writer;
	}
	
	/**
	 * Initialize the game
	 */
	@Override
	public void init() throws Exception{
		
		//Input from input console
		this.inputConsole=new InputConsoleImpl(reader,writer);
		this.inputConsole.init();
		this.input=this.inputConsole.getInput();
		
		//Initialize Battle Sea
		this.battleSea=new BattleSeaImpl();
		this.battleSea.init(input);
		
		int noOfPlayers=Integer.parseInt(GameConstant.NOOFPLAYERS); //no of players playing the game
		int noOfBattleShips=Integer.parseInt(GameConstant.NOOFBETTLESHIP);//no of battle ships
		List<BattleArea> battleAreas=this.battleSea.buildBattleSea();//build battle sea
		
		
		ShipType shipType=(ShipType)this.input.get(GameConstant.GAMETYPEOFSHIP);
		
		//initialize battle area
		initializeBattleArea(noOfPlayers, battleAreas, noOfBattleShips,shipType);
		showBattleAreas();
	}
	
	/**
	 * Initialize Players,Battle Ships
	 * @param noOfPlayers
	 * @param bettleAreas
	 * @param noOfBettleShips
	 */
	private void initializeBattleArea(int noOfPlayers,List<BattleArea> battleAreas,int noOfBattleShips,ShipType shipType) throws InstantiationException,IllegalAccessException{

		for(int i=1;i<=noOfPlayers;i++){
			BattleArea battleArea=battleAreas.get(i-1);
			Player player=new PlayerImpl(battleArea,i);
			
			for(int j=1;j<=noOfBattleShips;j++){
				String shipStrengthType= (String)this.input.get(GameConstant.GAMETYPEOFSHIPSTRENGTH+j);
				ShipStrength strength=ShipStrengthType.valueOf(shipStrengthType).getStrengthType().newInstance();
				Dimension dimension=(Dimension)this.input.get(GameConstant.DIMENSION+j);
				
				Location location=(Location)this.input.get(GameConstant.LOCATION+i+j);
				int noOfMissiles=(Integer)this.input.get(GameConstant.COUNTOFMISSILE+i+j);
				Ship ship=ShipFactory.buildShip(shipType, strength,dimension,location,battleArea,noOfMissiles);
				player.placeShip(ship, location, dimension);
				Fleet fleet=null;
				if(mapPlayer2Fleet.containsKey(player)){
					fleet=mapPlayer2Fleet.get(player);
					fleet.addShip(ship);
				}else{
					fleet=new Fleet();
					fleet.addShip(ship);
				}
				this.mapPlayer2Fleet.put(player, fleet);
				this.mapPlayer2BattleArea.put(player,battleArea);
			}
		}
	}
	
	/**
	 * Method to show battle area for all players
	 */
	private void showBattleAreas(){
		//show bettle areas
				int i=1;
				for(Player player:this.mapPlayer2BattleArea.keySet()){
					this.writer.println("Player "+i+"Bettle Area");
					this.mapPlayer2BattleArea.get(player).show();
					this.writer.println();
					i++;
				}
	}
	

	/**
	 * start the game
	 */
	@Override
	public void startGame() {
		
		Set<Player> playerSet=this.mapPlayer2BattleArea.keySet();
		Iterator<Player> itr=playerSet.iterator();
		Player player1=itr.next();
		Player player2=itr.next();
		try{
			while(true){
						if(play(player1, player2,1))return;
						if(play(player2, player1,2))return;
				}
		}catch(Exception e){
			this.writer.println(e.getMessage());
		}
	}
	
	
	
	private boolean play(Player player,Player target,int i){
		boolean isHit=false;
		boolean isSunk=false;
		do{
			if(!checkForMissileCountPerPlayer(player)){
				String readLine=null;
				try{
					readLine=reader.nextLine();
					if(readLine==null || readLine.isEmpty()) throw new RuntimeException("Invalid input found  - '"+readLine+"'");
				}catch(NoSuchElementException|IllegalStateException  e){
					this.writer.println("No more Input left, Its a draw");
					return true;
				}
				this.writer.println("Hitting - "+readLine);
				Location l=Utility.getLocation(readLine);
				int x=l.getX();
				int y=l.getY();
				try{
					isHit=player.play(new Target(target, x,y));
				}catch(NoMoreMissileException e){
					this.writer.println(e.getMessage());
					isHit=false;
					break;
				}
				if(isHit)this.writer.println("Player "+i+" fires a missile with target "+readLine+" which hit");
				else this.writer.println("Player "+i+" fires a missile with target "+readLine+" which missed");
			}else{
				this.writer.println("Player-"+i+" has no more missiles left");
				break;
			}
			if(checkforDraw()){this.writer.println("Players have no more missiles left, Its a draw !");return true;};
			isSunk=checkForWin(target);
			if(isSunk){this.writer.println("Player-"+i+" won the battle");return true;}
		}while(isHit);
		return false;
	}
	

	/**
	 * Check for Draw
	 * @return
	 */
	private boolean checkforDraw(){
		return checkForMissileCount();
				
	}
	
	/**
	 * Check for missile count for all bettle areas
	 * @return
	 */
	private boolean checkForMissileCount(){
		//check when is no more missile
				for(Player player:this.mapPlayer2Fleet.keySet()){
					for(Ship ship:this.mapPlayer2Fleet.get(player).getShips()){
						if(!ship.isMissibleExausted()){
							return false;
						}
					}
				}
				return true;
	}
	
	/**
	 * Check for missile count per player
	 * @param player
	 * @return
	 */
	private boolean checkForMissileCountPerPlayer(Player player){
		//check when missile exhausted
					for(Ship ship:this.mapPlayer2Fleet.get(player).getShips()){
						if(!ship.isMissibleExausted()){
							return false;
						}
					}
				return true;
	}
	
	/**
	 * check for won the match
	 * @param player
	 * @return
	 */
	private boolean checkForWin(Player player){
		//if ship of the player sunk
		for(Ship ship:this.mapPlayer2Fleet.get(player).getShips()){
			if(!ship.isSunk()){
				return false;
			}
		}
		return true;
	}

	/**
	 * Post Cleanup
	 */
	@Override
	public void clean() {
		this.inputConsole.clean();
		this.mapPlayer2BattleArea.clear();
		this.mapPlayer2Fleet.clear();
		this.battleSea.clean();
		this.reader.close();
		this.writer.close();
	}
	
}

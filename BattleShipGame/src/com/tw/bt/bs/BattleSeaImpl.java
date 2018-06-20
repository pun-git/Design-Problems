package com.tw.bt.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tw.bt.api.BattleArea;
import com.tw.bt.api.BattleSea;
import com.tw.bt.api.Player;
import com.tw.bt.constant.GameConstant;
import com.tw.bt.model.Fleet;

/**
 * Implementation of Battle Space
 * @author puagarwa
 *
 */
public class BattleSeaImpl implements BattleSea{

	private Map<String, Object> properties;
	private List<BattleArea> battleAreas=new ArrayList<>();
	private Map<Player, BattleArea> mapPlayer2BattleArea=new HashMap<>();
	private Map<Player, Fleet> mapPlayer2Fleet=new HashMap<>();
	
	@Override
	public void init(Map<String, Object> gameProperty) {
		this.properties=gameProperty;
		
	}
	
	@Override
	public List<BattleArea> buildBattleSea() {
		int noOfBattleArea=Integer.parseInt((String)this.properties.get(GameConstant.NOPLAYERS));
		
		for(int i=1;i<=noOfBattleArea;i++){
			BattleArea battleArea=new BattleAreaImpl();
			battleArea.init(properties);
			this.battleAreas.add(battleArea);
		}
		return this.battleAreas;	
	}
	
	
	
	@Override
	public void clean() {
		this.mapPlayer2BattleArea.clear();
		this.mapPlayer2Fleet.clear();
	}
	
	
}

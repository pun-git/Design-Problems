package com.tw.bt.api;

import java.util.List;
import java.util.Map;

/**
 * Interface for Battle Sea
 * @author puagarwa
 *
 */
public interface BattleSea {
	
	public void init(Map<String, Object> gameProperty);
	public List<BattleArea> buildBattleSea();
	public void clean();
	

}

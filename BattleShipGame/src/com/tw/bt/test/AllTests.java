package com.tw.bt.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	BattleAreaDimensionValidation.class,
	BattleShipLocationValidation.class,
	CountOfBattleShipValidation.class,
	BattleShipLocationValidation.class,
	Draw.class,
	Player2won.class,
	BattleShipDimensionValidation.class})
public class AllTests {

}

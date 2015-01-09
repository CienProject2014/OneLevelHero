package com.mygdx.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Game;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.game.OneLevelHero;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.ScreenManager;

public class GoToVillageScreenTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Before
	public void initialize() {
		Game game = new OneLevelHero();
		LoadManager.getInstance();
		ScreenManager.getInstance().initialize(game);
	}

	//GoMenuScreenTest를 따로 만들어줘서 이곳에 연결할 것인가?
	//아니면 한 클래스에서 풀 로직을 구동할 것인가에대한 고민
	public void goMenuScreen() {
		new ScreenController(ScreenEnum.MENU);
	}

}

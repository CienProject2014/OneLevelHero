package com.mygdx.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.assets.Assets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.MonsterPickManager;
import com.mygdx.manager.PositionManager;

public class OneLevelTest extends Game {
	private ApplicationContext context;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		// 디버그용 로그도 보이도록 설정
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		StaticAssets.loadAll();
		// context = RoboSpring.getContext(); 안드로이드에서 실행시
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		gameLoad();

		// goWorldMapScreen();
		// context.getBean(PositionManager.class).setCurrentNodeName("oberon");
		// goVillageScreen();
		// goEncounterScreen();
		// goVillageScreen();
		// goWorldMapScreen();
		goDungeonEntranceScreen();
	}

	public void gameLoad() {
		context.getBean(Assets.class).initialize();
		context.getBean(ScreenFactory.class).setGame(this);
		context.getBean(LoadManager.class).loadNewGame();
	}

	// 이하 게임에 곧장 진입하고자 하는 경우
	private void goStatusScreen() {
		context.getBean(ScreenFactory.class).show(ScreenEnum.STATUS);
	}

	private void goVillageScreen() {
		context.getBean(ScreenFactory.class).show(ScreenEnum.VILLAGE);
	}

	private void goWorldMapScreen() {
		context.getBean(ScreenFactory.class).show(ScreenEnum.WORLD_MAP);
	}

	private void goEncounterScreen() {
		context.getBean(MonsterPickManager.class).createMonster();
		context.getBean(ScreenFactory.class).show(ScreenEnum.ENCOUNTER);
	}

	private void goDungeonEntranceScreen() {
		context.getBean(PositionInfo.class).setCurrentNodeName(
				WorldNodeEnum.BLACKWOOD_FOREST.toString());
		context.getBean(ScreenFactory.class).show(ScreenEnum.DUNGEON_ENTRANCE);
	}

	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			// Do back button handling (show pause menu?)
			// This will exit the app but you can add other
			// options here as well
		}
		return false;
	}
}

package com.mygdx.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.MonsterManager;
import com.mygdx.state.StaticAssets;

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
		
		goEncounterScreen();
	}

	public void gameLoad() {
		context.getBean(ScreenFactory.class).setGame(this);
		// 가고자 하는 스크린의 메서드를 선택하자.
		goDungeonEntranceScreen();
	}

	// 메뉴스크린은 게임을 로드할 필요가 없다.
	private void goMenuScreen() {
		context.getBean(ScreenFactory.class).show(ScreenEnum.MENU);
	}

	// 이하 게임에 곧장 진입하고자 하는 경우
	private void goVillageScreen() {
		context.getBean(LoadManager.class).loadNewGame();
		context.getBean(ScreenFactory.class).show(ScreenEnum.VILLAGE);
	}

	private void goWorldMapScreen() {
		context.getBean(LoadManager.class).loadNewGame();
		context.getBean(ScreenFactory.class).show(ScreenEnum.WORLD_MAP);
	}

	private void goEncounterScreen() {
		context.getBean(LoadManager.class).loadNewGame();
		context.getBean(MonsterManager.class).createMonster();
		context.getBean(ScreenFactory.class).show(ScreenEnum.ENCOUNTER);
	}

	private void goDungeonEntranceScreen() {
		context.getBean(LoadManager.class).loadNewGame();
		context.getBean(PositionInfo.class).setCurrentNode(
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

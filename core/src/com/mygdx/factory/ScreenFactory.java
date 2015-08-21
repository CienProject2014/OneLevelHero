package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.screen.BGMScreen;
import com.mygdx.screen.BattleScreen;
import com.mygdx.screen.BonusPointScreen;
import com.mygdx.screen.BuildingScreen;
import com.mygdx.screen.CGScreen;
import com.mygdx.screen.CollectionScreen;
import com.mygdx.screen.CreditScreen;
import com.mygdx.screen.DungeonEntranceScreen;
import com.mygdx.screen.DungeonScreen;
import com.mygdx.screen.EncounterScreen;
import com.mygdx.screen.EndingScreen;
import com.mygdx.screen.EventScreen;
import com.mygdx.screen.FieldScreen;
import com.mygdx.screen.GameObjectScreen;
import com.mygdx.screen.GreetingScreen;
import com.mygdx.screen.InventoryScreen;
import com.mygdx.screen.LoadScreen;
import com.mygdx.screen.LogScreen;
import com.mygdx.screen.MenuScreen;
import com.mygdx.screen.OptionScreen;
import com.mygdx.screen.SaveScreen;
import com.mygdx.screen.StatusScreen;
import com.mygdx.screen.VillageScreen;
import com.mygdx.screen.WorldMapScreen;

public class ScreenFactory {
	@Autowired
	private ApplicationContext context;
	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		Gdx.app.debug("ScreenFactory", "screenFactory.setGame(game)");
		this.game = game;
	}

	private IntMap<Screen> screens;

	public ScreenFactory() {
		screens = new IntMap<Screen>();
	}

	private Screen getScreenInstance(ScreenEnum screenEnum) {
		Gdx.app.log("ScreenFactory", screenEnum.toString() + "Screen 호출");
		switch (screenEnum) {
		case BATTLE:
			return context.getBean(BattleScreen.class);
		case BGM:
			return context.getBean(BGMScreen.class);
		case BONUS_POINT:
			return context.getBean(BonusPointScreen.class);
		case BUILDING:
			return context.getBean(BuildingScreen.class);
		case CG:
			return context.getBean(CGScreen.class);
		case COLLECTION:
			return context.getBean(CollectionScreen.class);
		case CREDIT:
			return context.getBean(CreditScreen.class);
		case DUNGEON:
			return context.getBean(DungeonScreen.class);
		case DUNGEON_ENTRANCE:
			return context.getBean(DungeonEntranceScreen.class);
		case ENCOUNTER:
			return context.getBean(EncounterScreen.class);
		case ENDING:
			return context.getBean(EndingScreen.class);
		case EVENT:
			return context.getBean(EventScreen.class);
		case GAME_OBJECT:
			return context.getBean(GameObjectScreen.class);
		case GREETING:
			return context.getBean(GreetingScreen.class);
		case INVENTORY:
			return context.getBean(InventoryScreen.class);
		case LOAD:
			return context.getBean(LoadScreen.class);
		case LOG:
			return context.getBean(LogScreen.class);
		case MENU:
			return context.getBean(MenuScreen.class);
		case FIELD:
			return context.getBean(FieldScreen.class);
		case OPTION:
			return context.getBean(OptionScreen.class);
		case SAVE:
			return context.getBean(SaveScreen.class);
		case STATUS:
			return context.getBean(StatusScreen.class);
		case VILLAGE:
			return context.getBean(VillageScreen.class);
		case WORLD_MAP:
			return context.getBean(WorldMapScreen.class);
		default:
			return context.getBean(VillageScreen.class); // FIXME
		}
	}

	public void show(ScreenEnum screenEnum) {
		Gdx.app.debug("ScreenFactory", "show(" + screenEnum.toString() + ")");
		if (game == null) {
			Gdx.app.debug("ScreenFactory", "game is null");
			return;
		}
		if (!screens.containsKey(screenEnum.ordinal()))
			screens.put(screenEnum.ordinal(), getScreenInstance(screenEnum));
		game.setScreen(screens.get(screenEnum.ordinal()));
	}

	public void dispose(ScreenEnum screen) {
		if (!screens.containsKey(screen.ordinal()))
			return;
		screens.remove(screen.ordinal()).dispose();
	}

	public void dispose() {
		for (com.badlogic.gdx.Screen screen : screens.values())
			screen.dispose();
		screens.clear();
	}
}

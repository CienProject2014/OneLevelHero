package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.model.EventScene;
import com.mygdx.stage.BattleStage;
import com.mygdx.stage.BuildingStage;
import com.mygdx.stage.CharacterUiStage;
import com.mygdx.stage.DungeonEntranceStage;
import com.mygdx.stage.DungeonStage;
import com.mygdx.stage.EncounterStage;
import com.mygdx.stage.EventStage;
import com.mygdx.stage.GameUiStage;
import com.mygdx.stage.MenuStage;
import com.mygdx.stage.MonsterStage;
import com.mygdx.stage.MovingStage;
import com.mygdx.stage.SelectButtonStage;
import com.mygdx.stage.StatusStage;
import com.mygdx.stage.VillageStage;
import com.mygdx.stage.WorldMapStage;

public class StageFactory {
	@Autowired
	private ApplicationContext context;

	public Stage makeStage(StageEnum stageEnum) {
		Gdx.app.log("StageFactory", "makeStage(" + stageEnum.toString() + ")");
		switch (stageEnum) {
		case BUILDING:
			return context.getBean(BuildingStage.class).makeStage();
		case CHARACTER_UI:
			return context.getBean(CharacterUiStage.class).makeStage();
		case DUNGEON:
			return context.getBean(DungeonStage.class).makeStage();
		case DUNGEON_ENTRANCE:
			return context.getBean(DungeonEntranceStage.class).makeStage();
		case ENCOUNTER:
			return context.getBean(EncounterStage.class).makeStage();
		case GAME_UI:
			return context.getBean(GameUiStage.class).makeStage();
		case MENU:
			return context.getBean(MenuStage.class).makeStage();
		case MONSTER:
			return context.getBean(MonsterStage.class).makeStage();
		case MOVING:
			return context.getBean(MovingStage.class).makeStage();
		case SELECT_BUTTON:
			return context.getBean(SelectButtonStage.class).makeStage();
		case STATUS:
			return context.getBean(StatusStage.class).makeStage();
		case VILLAGE:
			return context.getBean(VillageStage.class).makeStage();
		case WORLD_MAP:
			return context.getBean(WorldMapStage.class).makeStage();
		default:
			Gdx.app.debug("StageFactory", "StageEnum 주입 에러");
			return context.getBean(VillageStage.class).makeStage(); // FIXME
		}
	}

	public Stage makeBattleStage() {
		return context.getBean(BattleStage.class).makeStage();
	}

	public Stage makeEventStage(EventScene eventScene) {
		return context.getBean(EventStage.class).makeStage(eventScene);
	}
}

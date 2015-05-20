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
import com.mygdx.stage.SelectButtonStage;
import com.mygdx.stage.StatusStage;
import com.mygdx.stage.VillageStage;
import com.mygdx.stage.WorldMapStage;

public class StageFactory {
	@Autowired
	private ApplicationContext context;

	public Stage makeStage(StageEnum stageEnum) {
		// 매회 makeStage가 동작하며 매모리 할당이 문제가 발생한 주원인이 아닐까 생각합니다.
		switch (stageEnum) {
			case BUILDING:
				Gdx.app.log("StageFactory", "makeStage : BUILDING");
				return context.getBean(BuildingStage.class).makeStage();
			case CHARACTER_UI:
				Gdx.app.log("StageFactory", "makeStage : CHARACTER_UI");
				return context.getBean(CharacterUiStage.class).makeStage();
			case DUNGEON:
				Gdx.app.log("StageFactory", "makeStage : DUNGEON");
				return context.getBean(DungeonStage.class).makeStage();
			case DUNGEON_ENTRANCE:
				Gdx.app.log("StageFactory", "makeStage : DUNGEON_ENTRANCE");
				return context.getBean(DungeonEntranceStage.class).makeStage();
			case ENCOUNTER:
				Gdx.app.log("StageFactory", "makeStage : ENCOUNTER");
				return context.getBean(EncounterStage.class).makeStage();
			case GAME_UI:
				Gdx.app.log("StageFactory", "makeStage : GAME_UI");
				return context.getBean(GameUiStage.class).makeStage();
			case MENU:
				Gdx.app.log("StageFactory", "makeStage : MENU");
				return context.getBean(MenuStage.class).makeStage();
			case MONSTER:
				Gdx.app.log("StageFactory", "makeStage : MONSTER");
				return context.getBean(MonsterStage.class).makeStage();
			case SELECT_BUTTON:
				Gdx.app.log("StageFactory", "makeStage : SELECT_BUTTON");
				return context.getBean(SelectButtonStage.class).makeStage();
			case STATUS:
				Gdx.app.log("StageFactory", "makeStage : STATUS");
				return context.getBean(StatusStage.class).makeStage();
			case VILLAGE:
				Gdx.app.log("StageFactory", "makeStage : VILLAGE");	// 여기 매우 느리네요.
				return context.getBean(VillageStage.class).makeStage();
			case WORLD_MAP:
				Gdx.app.log("StageFactory", "makeStage : WORLD_MAP");
				return context.getBean(WorldMapStage.class).makeStage();
			default:
				Gdx.app.log("StageFactory", "StageEnum 주입 에러");
				return context.getBean(VillageStage.class).makeStage(); //FIXME
		}
	}

	public Stage makeBattleStage() {
		return context.getBean(BattleStage.class).makeStage();
	}

	public Stage makeEventStage(EventScene eventScene) {
		return context.getBean(EventStage.class).makeStage(eventScene);
	}
}

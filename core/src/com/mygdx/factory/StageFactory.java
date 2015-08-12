package com.mygdx.factory;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.model.event.EventScene;
import com.mygdx.stage.BattleStage;
import com.mygdx.stage.BuildingStage;
import com.mygdx.stage.CharacterUiStage;
import com.mygdx.stage.DungeonEntranceStage;
import com.mygdx.stage.DungeonStage;
import com.mygdx.stage.EncounterStage;
import com.mygdx.stage.EventStage;
import com.mygdx.stage.FieldStage;
import com.mygdx.stage.GameObjectStage;
import com.mygdx.stage.GameUiStage;
import com.mygdx.stage.InventoryStage;
import com.mygdx.stage.MenuStage;
import com.mygdx.stage.MonsterStage;
import com.mygdx.stage.SelectComponentStage;
import com.mygdx.stage.SelectEventStage;
import com.mygdx.stage.SkillStage;
import com.mygdx.stage.StatusStage;
import com.mygdx.stage.VillageStage;
import com.mygdx.stage.WorldMapStage;

public class StageFactory {
	@Autowired
	private ApplicationContext context;

	public Stage makeStage(StageEnum stageEnum) {
		Gdx.app.log("StageFactory", "Make " + stageEnum.toString() + "Stage");
		switch (stageEnum) {
			case BUILDING :
				return context.getBean(BuildingStage.class).makeStage();
			case CHARACTER_UI :
				return context.getBean(CharacterUiStage.class).makeStage();
			case DUNGEON :
				return context.getBean(DungeonStage.class).makeStage();
			case DUNGEON_ENTRANCE :
				return context.getBean(DungeonEntranceStage.class).makeStage();
			case ENCOUNTER :
				return context.getBean(EncounterStage.class).makeStage();
			case GAME_UI :
				return context.getBean(GameUiStage.class).makeStage();
			case GAME_OBJECT :
				return context.getBean(GameObjectStage.class).makeStage();
			case MENU :
				return context.getBean(MenuStage.class).makeStage();
			case MONSTER :
				return context.getBean(MonsterStage.class).makeStage();
			case FIELD :
				return context.getBean(FieldStage.class).makeStage();
			case INVENTORY :
				return context.getBean(InventoryStage.class).makeStage();
			case SELECT_EVENT :
				return context.getBean(SelectEventStage.class).makeStage();
			case SELECT_COMPONENT :
				return context.getBean(SelectComponentStage.class).makeStage();
			case SKILL :
				return context.getBean(SkillStage.class).makeStage();
			case STATUS :
				return context.getBean(StatusStage.class).makeStage();
			case VILLAGE :
				return context.getBean(VillageStage.class).makeStage();
			case WORLD_MAP :
				return context.getBean(WorldMapStage.class).makeStage();
			default :
				Gdx.app.debug("StageFactory", "StageEnum 주입 에러");
				return context.getBean(VillageStage.class).makeStage(); // FIXME
		}
	}

	public Stage makeBattleStage() {
		return context.getBean(BattleStage.class).makeStage();
	}

	public Stage makeEventStage(Iterator<EventScene> eventSceneIterator) {
		return context.getBean(EventStage.class).makeStage(eventSceneIterator);
	}

	public Stage makeEventStage(EventScene eventScene) {
		return context.getBean(EventStage.class).makeStage(eventScene);
	}
}

package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.PlatformResourceManager;
import com.mygdx.model.EventScene;
import com.mygdx.stage.BattleStage;
import com.mygdx.stage.CharacterUiStage;
import com.mygdx.stage.EncounterStage;
import com.mygdx.stage.EventStage;
import com.mygdx.stage.GameUiStage;
import com.mygdx.stage.MenuStage;
import com.mygdx.stage.MonsterStage;
import com.mygdx.stage.SelectButtonStage;
import com.mygdx.stage.StatusStage;
import com.mygdx.stage.TouchPadStage;
import com.mygdx.stage.VillageStage;
import com.mygdx.stage.WorldMapStage;

@Component
public class StageFactory {
	@Autowired
	private BattleStage battleStage;
	@Autowired
	private CharacterUiStage characterUiStage;
	@Autowired
	private EncounterStage encounterStage;
	@Autowired
	private EventStage eventStage;
	@Autowired
	private GameUiStage gameUiStage;
	@Autowired
	private MenuStage menuStage;
	@Autowired
	private MonsterStage monsterStage;
	@Autowired
	private SelectButtonStage selectButtonStage;
	@Autowired
	private StatusStage statusStage;
	@Autowired
	private TouchPadStage touchPadStage;
	@Autowired
	private VillageStage villageStage;
	@Autowired
	private WorldMapStage worldMapStage;

	public Stage makeStage(StageEnum stageEnum) {
		PlatformResourceManager rm = new PlatformResourceManager();
		rm.initPlatformerResources();

		switch (stageEnum) {
			case CHARACTER_UI:
				return characterUiStage.init();
			case ENCOUNTER:
				return encounterStage.makeStage();
			case GAME_UI:
				return gameUiStage.makeStage();
			case MENU:
				return menuStage.makeStage();
			case MONSTER:
				return monsterStage.makeStage();
			case SELECT_BUTTON:
				return selectButtonStage.makeStage();
			case STATUS:
				return statusStage.makeStage();
			case TOUCH_PAD:
				return touchPadStage.makeStage();
			case VILLAGE:
				return villageStage.makeStage();
			case WORLD_MAP:
				return worldMapStage.makeStage();
			default:
				return villageStage.makeStage(); //FIXME
		}
	}

	public Stage makeBattleStage(PlatformResourceManager rm) {
		return battleStage.init(rm);
	}

	public Stage makeEventStage(EventScene eventScene) {
		return eventStage.makeStage(eventScene);
	}

}

package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.stage.BattleStage;
import com.mygdx.stage.BuildingStage;
import com.mygdx.stage.CharacterChangeStage;
import com.mygdx.stage.CharacterUiStage;
import com.mygdx.stage.ChatEventStage;
import com.mygdx.stage.ChoiceNpcEventStage;
import com.mygdx.stage.ChoiceOptionStage;
import com.mygdx.stage.CreditStage;
import com.mygdx.stage.DungeonEntranceStage;
import com.mygdx.stage.DungeonMinimapStage;
import com.mygdx.stage.DungeonStage;
import com.mygdx.stage.EncounterStage;
import com.mygdx.stage.FieldStage;
import com.mygdx.stage.ForkStage;
import com.mygdx.stage.GameOverStage;
import com.mygdx.stage.GameUiStage;
import com.mygdx.stage.GreetingStage;
import com.mygdx.stage.InventoryStage;
import com.mygdx.stage.ItemStage;
import com.mygdx.stage.LoadPopupStage;
import com.mygdx.stage.LoadingBarStage;
import com.mygdx.stage.MenuStage;
import com.mygdx.stage.MonsterStage;
import com.mygdx.stage.SavePopupStage;
import com.mygdx.stage.SkillStage;
import com.mygdx.stage.StatusStage;
import com.mygdx.stage.VillageStage;
import com.mygdx.stage.WorldMapStage;

public class StageFactory {
	@Autowired
	private ApplicationContext context;

	public Stage makeStage(StageEnum stageEnum) {
		switch (stageEnum) {
			case BATTLE :
				return context.getBean(BattleStage.class).makeStage();
			case BUILDING :
				return context.getBean(BuildingStage.class).makeStage();
			case CHARACTER_CHANGE :
				return context.getBean(CharacterChangeStage.class).makeStage();
			case CHARACTER_UI :
				return context.getBean(CharacterUiStage.class).makeStage();
			case CHAT_EVENT :
				return context.getBean(ChatEventStage.class).makeStage();
			case CHOICE_NPC_EVENT :
				return context.getBean(ChoiceNpcEventStage.class).makeStage();
			case CHOICE_OPTION :
				return context.getBean(ChoiceOptionStage.class).makeStage();
			case CREDIT :
				return context.getBean(CreditStage.class).makeStage();
			case DUNGEON :
				return context.getBean(DungeonStage.class).makeStage();
			case DUNGEON_ENTRANCE :
				return context.getBean(DungeonEntranceStage.class).makeStage();
			case DUNGEON_MINIMAP :
				return context.getBean(DungeonMinimapStage.class).makeStage();
			case ENCOUNTER :
				return context.getBean(EncounterStage.class).makeStage();
			case FORK :
				return context.getBean(ForkStage.class).makeStage();
			case GAME_UI :
				return context.getBean(GameUiStage.class).makeStage();
			case GAME_OVER :
				return context.getBean(GameOverStage.class).makeStage();
			case GREETING :
				return context.getBean(GreetingStage.class).makeStage();
			case LOADING_BAR :
				return context.getBean(LoadingBarStage.class).makeStage();
			case LOAD_POPUP :
				return context.getBean(LoadPopupStage.class).makeStage();
			case MENU :
				return context.getBean(MenuStage.class).makeStage();
			case MONSTER :
				return context.getBean(MonsterStage.class).makeStage();
			case FIELD :
				return context.getBean(FieldStage.class).makeStage();
			case INVENTORY :
				return context.getBean(InventoryStage.class).makeStage();
			case SAVE :
				return context.getBean(SavePopupStage.class).makeStage();
			case SKILL :
				return context.getBean(SkillStage.class).makeStage();
			case STATUS :
				return context.getBean(StatusStage.class).makeStage();
			case VILLAGE :
				return context.getBean(VillageStage.class).makeStage();
			case WORLD_MAP :
				return context.getBean(WorldMapStage.class).makeStage();
			case ITEM :
				return context.getBean(ItemStage.class).makeStage();
			default :
				Gdx.app.debug("StageFactory", "StageEnum 주입 에러");
				return context.getBean(VillageStage.class).makeStage(); // FIXME
		}
	}
}

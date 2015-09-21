package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.nextSectionChecker.BattleCommandChecker;
import com.mygdx.nextSectionChecker.BattleEndChecker;
import com.mygdx.nextSectionChecker.ChoiceOptionChecker;
import com.mygdx.nextSectionChecker.MoveDungeonRoomInTargetTimeChecker;
import com.mygdx.nextSectionChecker.MoveFieldChecker;
import com.mygdx.nextSectionChecker.MoveNodeChecker;
import com.mygdx.nextSectionChecker.MoveSubNodeAfterAbsoluteTimeChecker;
import com.mygdx.nextSectionChecker.MoveSubNodeBeforeAbsoluteTimeChecker;
import com.mygdx.nextSectionChecker.MoveSubNodeChecker;
import com.mygdx.nextSectionChecker.NextSectionChecker;

public class NextSectionCheckerFactory {
	@Autowired
	private ApplicationContext context;

	public NextSectionChecker getNextSectionChecker(EventTypeEnum eventType) {
		switch (eventType) {
			case BATTLE_COMMAND :
				return context.getBean(BattleCommandChecker.class);
			case END_BATTLE :
				return context.getBean(BattleEndChecker.class);
			case CHOICE_OPTION :
				return context.getBean(ChoiceOptionChecker.class);
			case MOVE_DUNGEON_ROOM_IN_TARGET_TIME :
				return context.getBean(MoveDungeonRoomInTargetTimeChecker.class);
			case MOVE_FIELD :
				return context.getBean(MoveFieldChecker.class);
			case MOVE_NODE :
				return context.getBean(MoveNodeChecker.class);
			case MOVE_SUB_NODE :
				return context.getBean(MoveSubNodeChecker.class);
			case MOVE_SUB_NODE_AFTER_ABSOLUTE_TIME :
				return context.getBean(MoveSubNodeAfterAbsoluteTimeChecker.class);
			case MOVE_SUB_NODE_BEFORE_ABSOLUTE_TIME :
				return context.getBean(MoveSubNodeBeforeAbsoluteTimeChecker.class);
			default :
				Gdx.app.log("NextSectionCheckerFactory", "eventType 정보 오류");
				return null;
		}
	}
}

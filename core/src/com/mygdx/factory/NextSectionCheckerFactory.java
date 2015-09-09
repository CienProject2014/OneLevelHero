package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.nextSectionChecker.BattleCommandChecker;
import com.mygdx.nextSectionChecker.BattleEndSectionChecker;
import com.mygdx.nextSectionChecker.ChoiceOptionSectionChecker;
import com.mygdx.nextSectionChecker.MoveFieldSectionChecker;
import com.mygdx.nextSectionChecker.MoveNodeSectionChecker;
import com.mygdx.nextSectionChecker.MoveSubNodeSectionChecker;
import com.mygdx.nextSectionChecker.NextSectionChecker;

public class NextSectionCheckerFactory {
	@Autowired
	private ApplicationContext context;

	public NextSectionChecker getNextSectionChecker(EventTypeEnum eventType) {
		switch (eventType) {
			case BATTLE_COMMAND :
				return context.getBean(BattleCommandChecker.class);
			case BATTLE_END :
				return context.getBean(BattleEndSectionChecker.class);
			case CHOICE_OPTION :
				return context.getBean(ChoiceOptionSectionChecker.class);
			case MOVE_NODE :
				return context.getBean(MoveNodeSectionChecker.class);
			case MOVE_SUB_NODE :
				return context.getBean(MoveSubNodeSectionChecker.class);
			case MOVE_FIELD :
				return context.getBean(MoveFieldSectionChecker.class);
			default :
				Gdx.app.log("NextSectionCheckerFactory", "eventType 정보 오류");
				return null;
		}
	}
}

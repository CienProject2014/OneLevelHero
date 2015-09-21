package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.eventTrigger.ChatEventTrigger;
import com.mygdx.eventTrigger.ChoiceOptionEventTrigger;
import com.mygdx.eventTrigger.EndBattleEventTrigger;
import com.mygdx.eventTrigger.EventTrigger;
import com.mygdx.eventTrigger.GameOverEventTrigger;
import com.mygdx.eventTrigger.GoSubNodeEventTrigger;
import com.mygdx.eventTrigger.JoinPartyEventTrigger;
import com.mygdx.eventTrigger.MoveNodeEventTrigger;
import com.mygdx.eventTrigger.MoveSubNodeEventTrigger;
import com.mygdx.eventTrigger.NextSectionEventTrigger;
import com.mygdx.eventTrigger.PassTimeEventTrigger;
import com.mygdx.eventTrigger.PlayMusicEventTrigger;
import com.mygdx.eventTrigger.QuitPartyEventTrigger;
import com.mygdx.eventTrigger.StartBattleEventTrigger;

public class EventTriggerFactory {
	@Autowired
	private ApplicationContext context;

	public EventTrigger getEventTrigger(EventTypeEnum eventType) {
		switch (eventType) {
			case END_BATTLE :
				return context.getBean(EndBattleEventTrigger.class);
			case START_BATTLE :
				return context.getBean(StartBattleEventTrigger.class);
			case CHAT :
				return context.getBean(ChatEventTrigger.class);
			case CHOICE_OPTION :
				return context.getBean(ChoiceOptionEventTrigger.class);
			case GAME_OVER :
				return context.getBean(GameOverEventTrigger.class);
			case GO_SUB_NODE :
				return context.getBean(GoSubNodeEventTrigger.class);
			case JOIN_PARTY :
				return context.getBean(JoinPartyEventTrigger.class);
			case MOVE_NODE :
				return context.getBean(MoveNodeEventTrigger.class);
			case MOVE_SUB_NODE :
				return context.getBean(MoveSubNodeEventTrigger.class);
			case NEXT_SECTION :
				return context.getBean(NextSectionEventTrigger.class);
			case PASS_TIME :
				return context.getBean(PassTimeEventTrigger.class);
			case PLAY_MUSIC :
				return context.getBean(PlayMusicEventTrigger.class);
			case QUIT_PARTY :
				return context.getBean(QuitPartyEventTrigger.class);
			default :
				Gdx.app.log("EventTriggerFactory", "EventType정보 오류 - " + eventType);
				return null;
		}
	}
}

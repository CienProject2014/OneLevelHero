package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.eventTrigger.AlwaysOpenGameObjectEventTrigger;
import com.mygdx.eventTrigger.AlwaysOpenNpcEventTrigger;
import com.mygdx.eventTrigger.ChatEventTrigger;
import com.mygdx.eventTrigger.ChoiceOptionEventTrigger;
import com.mygdx.eventTrigger.CloseGameObjectEventTrigger;
import com.mygdx.eventTrigger.EndBattleEventTrigger;
import com.mygdx.eventTrigger.EventTrigger;
import com.mygdx.eventTrigger.GameClearEventTrigger;
import com.mygdx.eventTrigger.GameOverEventTrigger;
import com.mygdx.eventTrigger.GetBuffEventTrigger;
import com.mygdx.eventTrigger.GoSubNodeEventTrigger;
import com.mygdx.eventTrigger.JoinPartyEventTrigger;
import com.mygdx.eventTrigger.MoveDungeonRoomEventTrigger;
import com.mygdx.eventTrigger.MoveNodeEventTrigger;
import com.mygdx.eventTrigger.MoveSubNodeEventTrigger;
import com.mygdx.eventTrigger.NextSectionEventTrigger;
import com.mygdx.eventTrigger.OpenGameObjectEventTrigger;
import com.mygdx.eventTrigger.OpenNpcEventTrigger;
import com.mygdx.eventTrigger.PassTimeEventTrigger;
import com.mygdx.eventTrigger.PlayMusicEventTrigger;
import com.mygdx.eventTrigger.QuestGetItemEventTrigger;
import com.mygdx.eventTrigger.QuestHuntMonsterEventTrigger;
import com.mygdx.eventTrigger.QuitPartyEventTrigger;
import com.mygdx.eventTrigger.RestInNodeEventTrigger;
import com.mygdx.eventTrigger.SetGameObjectTargetTimeEventTrigger;
import com.mygdx.eventTrigger.SetNpcTargetTimeEventTrigger;
import com.mygdx.eventTrigger.StartBattleEventTrigger;

public class EventTriggerFactory {
	@Autowired
	private ApplicationContext context;

	public EventTrigger getEventTrigger(EventTypeEnum eventType) {
		switch (eventType) {
			case ALWAYS_OPEN_GAME_OBJECT_EVENT :
				return context.getBean(AlwaysOpenGameObjectEventTrigger.class);
			case ALWAYS_OPEN_NPC_EVENT :
				return context.getBean(AlwaysOpenNpcEventTrigger.class);
			case END_BATTLE :
				return context.getBean(EndBattleEventTrigger.class);
			case START_BATTLE :
				return context.getBean(StartBattleEventTrigger.class);
			case CHAT :
				return context.getBean(ChatEventTrigger.class);
			case CHOICE_OPTION :
				return context.getBean(ChoiceOptionEventTrigger.class);
			case CLOSE_GAME_OBJECT_EVENT :
				return context.getBean(CloseGameObjectEventTrigger.class);
			case GAME_CLEAR :
				return context.getBean(GameClearEventTrigger.class);
			case GAME_OVER :
				return context.getBean(GameOverEventTrigger.class);
			case GET_BUFF :
				return context.getBean(GetBuffEventTrigger.class);
			case GO_SUB_NODE :
				return context.getBean(GoSubNodeEventTrigger.class);
			case JOIN_PARTY :
				return context.getBean(JoinPartyEventTrigger.class);
			case MOVE_DUNGEON_ROOM :
				return context.getBean(MoveDungeonRoomEventTrigger.class);
			case MOVE_NODE :
				return context.getBean(MoveNodeEventTrigger.class);
			case MOVE_SUB_NODE :
				return context.getBean(MoveSubNodeEventTrigger.class);
			case NEXT_SECTION :
				return context.getBean(NextSectionEventTrigger.class);
			case OPEN_GAME_OBJECT_EVENT :
				return context.getBean(OpenGameObjectEventTrigger.class);
			case OPEN_NPC_EVENT :
				return context.getBean(OpenNpcEventTrigger.class);
			case PASS_TIME :
				return context.getBean(PassTimeEventTrigger.class);
			case PLAY_MUSIC :
				return context.getBean(PlayMusicEventTrigger.class);
			case REST_IN_NODE :
				return context.getBean(RestInNodeEventTrigger.class);
			case SET_NPC_TARGET_TIME :
				return context.getBean(SetNpcTargetTimeEventTrigger.class);
			case SET_GAME_OBJECT_TARGET_TIME :
				return context.getBean(SetGameObjectTargetTimeEventTrigger.class);
			case QUEST_GET_ITEM :
				return context.getBean(QuestGetItemEventTrigger.class);
			case QUEST_HUNT_MONSTER :
				return context.getBean(QuestHuntMonsterEventTrigger.class);
			case QUIT_PARTY :
				return context.getBean(QuitPartyEventTrigger.class);
			default :
				Gdx.app.log("EventTriggerFactory", "EventType정보 오류 - " + eventType);
				return null;
		}
	}
}

package com.mygdx.stage;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.EventQueueManager;
import com.mygdx.model.EventScene;

/**
 * make and return stage(Event)
 *
 * @author Velmont
 *
 */
public class EventStage extends OneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private EventQueueManager eventQueueManager;
	@Autowired
	private EventInfo eventInfo;
	private Label script;
	private Image characterImage;
	private Image backgroundImage;

	/**
	 * EventManager로부터 eventScene정보를 받아 그래픽처리를 해준다.
	 *
	 * @param eventScene
	 * @return
	 */

	public Stage makeStage(final Iterator<EventScene> eventSceneIterator) {
		if (eventSceneIterator.hasNext()) {
			setScene(eventSceneIterator.next());

			this.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					if (eventSceneIterator.hasNext()) {
						setScene(eventSceneIterator.next());
					} else {
						eventManager.finishEvent(); // 해당 이벤트 상태를 종료처리
					}
					return true;
				}
			});
		}
		return this;
	}

	private void setScene(EventScene currentScene) {
		backgroundImage = new Image(currentScene.getBackground());
		script = new Label(currentScene.getScript(), assets.skin);
		characterImage = new Image(currentScene.getCharacter());

		// 스크립트/캐릭터/백그라운드 크기값 세팅
		script.setFontScale(assets.windowWidth / 1280);
		script.setWrap(true);
		script.setSize(assets.windowWidth * 0.781f,
				assets.windowHeight * 0.185f);
		script.setPosition(assets.windowWidth * 0.309375f,
				assets.windowHeight * 0.185f);
		characterImage.setSize(assets.windowWidth * 0.250f,
				assets.windowHeight * 0.555f);
		characterImage.setPosition(assets.windowWidth * 0.375f,
				assets.windowHeight * 0.185f);
		backgroundImage.setSize(assets.windowWidth, assets.windowHeight);

		// Greeting인지 아닌지 여부에 따라 처리
		/*
		EventTypeEnum eventType;
		if (eventInfo.isGreeting())
			eventType = EventTypeEnum.SELECT_EVENT;
		else
			eventType = eventInfo.getEventType();
		
		makeEventStage(eventType);
		*/
		makeEventStage(eventInfo.getCurrentEvent().getEventType());
		this.addActor(backgroundImage);
		this.addActor(script);
		this.addActor(characterImage);

	}

	private void makeEventStage(EventTypeEnum eventType) {
		switch (eventType) {
			case CHAT:
				makeChatStage();
				break;
			case SELECT_EVENT:
				makeSelectEventStage();
				break;
			case SELECT_COMPONENT:
				makeSelectComponentStage();
				break;
			case CREDIT:
				makeCreditStage();
				break;
			default:
				Gdx.app.log("error", " scene 주입 에러");
				break;
		}
	}

	private void makeCreditStage() {
	}

	private void makeSelectEventStage() {
		script.setFontScale(assets.windowWidth / 1280);
		script.setWrap(true); // 스크립트가 끝에 다다르면 자동 개행
		script.setSize(assets.windowWidth * 0.781f,
				assets.windowHeight * 0.185f);
		script.setPosition(assets.windowWidth * 0.109375f,
				assets.windowHeight * 0.185f);
		characterImage.setSize(assets.windowWidth * 0.250f,
				assets.windowHeight * 0.555f);
		characterImage.setPosition(assets.windowWidth * 0.375f,
				assets.windowHeight * 0.37f);
		backgroundImage.setSize(assets.windowWidth, assets.windowHeight);
	}

	private void makeSelectComponentStage() {
		script.setFontScale(assets.windowWidth / 1280);
		script.setWrap(true); // 스크립트가 끝에 다다르면 자동 개행
		script.setSize(assets.windowWidth * 0.781f,
				assets.windowHeight * 0.185f);
		script.setPosition(assets.windowWidth * 0.109375f,
				assets.windowHeight * 0.185f);
		characterImage.setSize(assets.windowWidth * 0.250f,
				assets.windowHeight * 0.555f);
		characterImage.setPosition(assets.windowWidth * 0.375f,
				assets.windowHeight * 0.37f);
		backgroundImage.setSize(assets.windowWidth, assets.windowHeight);

	}

	private void makeChatStage() {
		script.setFontScale(assets.windowWidth / 1280);
		script.setWrap(true);
		script.setPosition(assets.windowWidth * 0.2f, 0);
		script.setSize(assets.windowWidth * 0.8f, assets.windowHeight * 0.3f);
		characterImage.setSize(assets.windowWidth * 0.2f,
				assets.windowHeight * 0.2f);
		characterImage.setPosition(0, 0);
		backgroundImage.setSize(assets.windowWidth, assets.windowHeight);
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public EventQueueManager getEventQueueManager() {
		return eventQueueManager;
	}

	public void setEventQueueManager(EventQueueManager eventQueueManager) {
		this.eventQueueManager = eventQueueManager;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

}

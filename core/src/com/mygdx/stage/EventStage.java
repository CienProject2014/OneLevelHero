package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.model.EventInfo;
import com.mygdx.model.EventScene;
import com.mygdx.state.Assets;

public class EventStage extends Stage {
	private Label script;
	private Image characterImage;
	private Image backgroundImage;
	private EventTypeEnum eventType;
	private EventInfo eventInfo;

	public EventStage() {

	}

	public EventStage(EventScene eventScene) {
		//씬 만들어주기
		eventInfo = EventManager.getEventInfo();

		backgroundImage = new Image(eventScene.getBackground());
		script = new Label(eventScene.getScript(), Assets.skin);
		characterImage = new Image(eventScene.getCharacter());
		// 스크립트/캐릭터/백그라운드 설정값 세팅
		script.setFontScale(Assets.windowWidth / 1280);
		script.setWrap(true);
		script.setSize(Assets.windowWidth * 0.781f,
				Assets.windowHeight * 0.185f);
		script.setPosition(Assets.windowWidth * 0.309375f,
				Assets.windowHeight * 0.185f);
		characterImage.setSize(Assets.windowWidth * 0.250f,
				Assets.windowHeight * 0.555f);
		characterImage.setPosition(Assets.windowWidth * 0.375f,
				Assets.windowHeight * 0.185f);
		backgroundImage.setSize(Assets.windowWidth, Assets.windowHeight);

		if (!eventInfo.isGreeting())
			eventType = eventInfo.getNpc().getEvents()
					.get(eventInfo.getEventNumber()).getEventType();
		else
			eventType = eventInfo.getNpc().getGreeting().getEventType();
		switch (eventType) {
			case CHAT:
				makeChatStage();
				break;
			case SELECT:
				makeSelectStage();
				break;
			case CREDIT:
				makeCreditStage();
				break;
			default:
				Gdx.app.log("error", " scene 주입 에러");
				break;
		}

		this.addActor(backgroundImage);
		this.addActor(script);
		this.addActor(characterImage);

	}

	private void makeCreditStage() {

	}

	private void makeSelectStage() {

		script.setFontScale(Assets.windowWidth / 1280);
		script.setWrap(true);
		script.setSize(Assets.windowWidth * 0.781f,
				Assets.windowHeight * 0.185f);
		script.setPosition(Assets.windowWidth * 0.109375f,
				Assets.windowHeight * 0.185f);
		characterImage.setSize(Assets.windowWidth * 0.250f,
				Assets.windowHeight * 0.555f);
		characterImage.setPosition(Assets.windowWidth * 0.375f,
				Assets.windowHeight * 0.37f);
		backgroundImage.setSize(Assets.windowWidth, Assets.windowHeight);

	}

	private void makeChatStage() {
		script.setFontScale(Assets.windowWidth / 1280);
		script.setWrap(true);
		script.setPosition(Assets.windowWidth * 0.2f, 0);
		script.setSize(Assets.windowWidth * 0.8f, Assets.windowHeight * 0.3f);
		characterImage.setSize(Assets.windowWidth * 0.2f,
				Assets.windowHeight * 0.2f);
		characterImage.setPosition(0, 0);
		backgroundImage.setSize(Assets.windowWidth, Assets.windowHeight);

	}
}

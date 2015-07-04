package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.model.EventScene;
import com.mygdx.state.StaticAssets;

/**
 * make and return stage(Event)
 *
 * @author Velmont
 *
 */
public class EventStage extends BaseOneLevelStage {
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
	public Stage makeStage(EventScene eventScene) {
		backgroundImage = new Image(eventScene.getBackground());
		script = new Label(eventScene.getScript(), assets.skin);
		characterImage = new Image(eventScene.getCharacter());

		// 스크립트/캐릭터/백그라운드 크기값 세팅
		script.setFontScale(StaticAssets.windowWidth / 1280);
		script.setWrap(true);
		script.setSize(StaticAssets.windowWidth * 0.781f,
				StaticAssets.windowHeight * 0.185f);
		script.setPosition(StaticAssets.windowWidth * 0.309375f,
				StaticAssets.windowHeight * 0.185f);
		characterImage.setSize(StaticAssets.windowWidth * 0.250f,
				StaticAssets.windowHeight * 0.555f);
		characterImage.setPosition(StaticAssets.windowWidth * 0.375f,
				StaticAssets.windowHeight * 0.185f);
		backgroundImage.setSize(StaticAssets.windowWidth,
				StaticAssets.windowHeight);

		// Greeting인지 아닌지 여부에 따라 처리
		EventTypeEnum eventType;
		if (eventInfo.isGreeting())
			eventType = EventTypeEnum.SELECT;
		else
			eventType = eventInfo.getEventType();
		makeEventStage(eventType);
		this.addActor(backgroundImage);
		this.addActor(script);
		this.addActor(characterImage);

		return this;
	}

	private void makeEventStage(EventTypeEnum eventType) {
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
	}

	private void makeCreditStage() {
	}

	private void makeSelectStage() {
		script.setFontScale(StaticAssets.windowWidth / 1280);
		script.setWrap(true); // 스크립트가 끝에 다다르면 자동 개행
		script.setSize(StaticAssets.windowWidth * 0.781f,
				StaticAssets.windowHeight * 0.185f);
		script.setPosition(StaticAssets.windowWidth * 0.109375f,
				StaticAssets.windowHeight * 0.185f);
		characterImage.setSize(StaticAssets.windowWidth * 0.250f,
				StaticAssets.windowHeight * 0.555f);
		characterImage.setPosition(StaticAssets.windowWidth * 0.375f,
				StaticAssets.windowHeight * 0.37f);
		backgroundImage.setSize(StaticAssets.windowWidth,
				StaticAssets.windowHeight);
	}

	private void makeChatStage() {
		script.setFontScale(StaticAssets.windowWidth / 1280);
		script.setWrap(true);
		script.setPosition(StaticAssets.windowWidth * 0.2f, 0);
		script.setSize(StaticAssets.windowWidth * 0.8f,
				StaticAssets.windowHeight * 0.3f);
		characterImage.setSize(StaticAssets.windowWidth * 0.2f,
				StaticAssets.windowHeight * 0.2f);
		characterImage.setPosition(0, 0);
		backgroundImage.setSize(StaticAssets.windowWidth,
				StaticAssets.windowHeight);
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}
}

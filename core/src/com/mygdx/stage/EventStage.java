package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.model.EventScene;
import com.mygdx.state.Assets;

/**
 * make and return stage(Event)
 * @author Velmont
 *
 */
@Component
@Scope(value = "prototype")
public class EventStage extends Stage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private EventInfo eventInfo;
	private Label script;
	private Image characterImage;
	private Image backgroundImage;

	/**
	 * EventManager로부터 eventScene정보를 받아 그래픽처리를 해준다.
	 * @param eventScene
	 * @return
	 */
	public Stage makeStage(EventScene eventScene) {
		backgroundImage = new Image(eventScene.getBackground());
		script = new Label(eventScene.getScript(), Assets.skin);
		characterImage = new Image(eventScene.getCharacter());

		// 스크립트/캐릭터/백그라운드 크기값 세팅
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

		//Greeting인지 아닌지 여부에 따라 처리
		EventTypeEnum eventType;
		if (eventInfo.isGreeting()) {
			eventType = EventTypeEnum.SELECT;
		} else {
			eventType = eventInfo.getEventType();
		}
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
		script.setFontScale(Assets.windowWidth / 1280);
		script.setWrap(true); //스크립트가 끝에 다다르면 자동 개행
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

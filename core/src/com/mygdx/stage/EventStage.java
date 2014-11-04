package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.model.NPC;
import com.mygdx.resource.Assets;

public class EventStage extends Stage {
	private static EventStage instance;
	private Label script;
	private Image characterImage;
	private Image backgroundImage;

	public static EventStage getInstance() {
		if (null == instance) {
			instance = new EventStage();
		}
		return instance;
	}

	public EventStage makeStage(NPC currentNPC, int eventNumber) {
		//씬 만들어오기
		makeScene(currentNPC, eventNumber);

		// 스크립트/캐릭터/백그라운드 세팅
		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		script.setSize(Assets.realWidth * 0.781f, Assets.realHeight * 0.185f);
		script.setPosition(Assets.realWidth * 0.109375f,
				Assets.realHeight * 0.185f);
		characterImage.setSize(Assets.realWidth * 0.250f,
				Assets.realHeight * 0.555f);
		characterImage.setPosition(Assets.realWidth * 0.375f,
				Assets.realHeight * 0.37f);
		backgroundImage.setSize(Assets.realWidth, Assets.realHeight);

		return this;
	}

	private void makeScene(NPC currentNPC, int eventNumber) {
		switch (currentNPC.getEvent().get(eventNumber).getEventType()) {

			case CHAT:
				makeChatScene();
				break;
			case SELECT:
				makeSelectScene();
				break;
			case CREDIT:
				makeCreditScene();
				break;
			default:
				Gdx.app.log("error", " scene 주입 에러");
				break;
		}

	}

	private void addActor() {
		this.addActor(backgroundImage);
		this.addActor(script);
		this.addActor(characterImage);

	}

	private void makeCreditScene() {

	}

	private void makeSelectScene() {

		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		script.setSize(Assets.realWidth * 0.781f, Assets.realHeight * 0.185f);
		script.setPosition(Assets.realWidth * 0.109375f,
				Assets.realHeight * 0.185f);
		characterImage.setSize(Assets.realWidth * 0.250f,
				Assets.realHeight * 0.555f);
		characterImage.setPosition(Assets.realWidth * 0.375f,
				Assets.realHeight * 0.37f);
		backgroundImage.setSize(Assets.realWidth, Assets.realHeight);
	}

	private void makeChatScene() {
		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		script.setPosition(Assets.realWidth * 0.2f, 0);
		script.setSize(Assets.realWidth * 0.8f, Assets.realHeight * 0.3f);
		characterImage.setSize(Assets.realWidth * 0.2f,
				Assets.realHeight * 0.2f);
		characterImage.setPosition(0, 0);
		backgroundImage.setSize(Assets.realWidth, Assets.realHeight);

	}
}

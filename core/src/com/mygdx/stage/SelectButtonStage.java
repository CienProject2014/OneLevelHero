package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;

public class SelectButtonStage extends Stage {

	private TextButton[] chatButton;
	private TextButtonStyle[] chatStyle;

	public SelectButtonStage() {
		chatButton = new TextButton[6];
		chatStyle = new TextButtonStyle[6];
		showEventButton();
		setSize();
		setPosition();
		add();
		addListener();

	}

	private void showEventButton() {
		chatStyle = new TextButtonStyle[6];
		for (int i = 0; i < 6; i++) {
			chatStyle[i] = new TextButtonStyle(Assets.chatButton[i],
					Assets.chatButton[i], Assets.chatButton[i], Assets.font);
			chatButton[i] = new TextButton("", chatStyle[i]);
		}
	}

	private void setSize() {
		for (int i = 0; i < 6; i++) {
			chatButton[i].setSize(Assets.realWidth * 0.208f,
					Assets.realHeight * 0.185f);
		}
	}

	// Assets.realHeight * 0.185f
	private void setPosition() {
		chatButton[0].setPosition(Assets.realWidth * 0.109375f,
				Assets.realHeight * 0.74f);
		chatButton[1].setPosition(Assets.realWidth * 0.109375f,
				Assets.realHeight * 0.555f);
		chatButton[2].setPosition(Assets.realWidth * 0.109375f,
				Assets.realHeight * 0.37f);
		chatButton[3].setPosition(Assets.realWidth * 0.68f,
				Assets.realHeight * 0.74f);
		chatButton[4].setPosition(Assets.realWidth * 0.68f,
				Assets.realHeight * 0.555f);
		chatButton[5].setPosition(Assets.realWidth * 0.68f,
				Assets.realHeight * 0.37f);

	}

	private void addListener() {
		chatButton[0].addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				//String eventCode = EventManager.getInstance().getEventNpc().getNpcEvent().getEventCode()[0];
				//EventManager.getInstance().setEventCode(eventCode, EventTypeEnum.CHAT);
				new ScreenController(ScreenEnum.EVENT);
			}
		});
	}

	private void add() {
		for (int i = 0; i < 6; i++) {
			this.addActor(chatButton[i]);
		}
	}
}

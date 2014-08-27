package com.mygdx.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.resource.Assets;

public class SelectButtonUi extends Stage {

	private TextButton[] chatButton;
	private TextButtonStyle[] chatStyle;

	public SelectButtonUi() {
		chatButton = new TextButton[6];
		chatStyle = new TextButtonStyle[6];
		showEventButton();
		setSize();
		setPosition();
		add();

	}

	public void showEventButton() {
		chatStyle = new TextButtonStyle[6];
		for (int i = 0; i < 6; i++) {
			chatStyle[i] = new TextButtonStyle(Assets.chatButton[i],
					Assets.chatButton[i], Assets.chatButton[i], Assets.font);
			chatButton[i] = new TextButton("", chatStyle[i]);
		}
	}

	public void setSize() {
		for (int i = 0; i < 6; i++) {
			chatButton[i].setSize(Assets.realWidth * 0.208f,
					Assets.realHeight * 0.185f);
		}
	}

	// Assets.realHeight * 0.185f
	public void setPosition() {
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

	public void add() {
		for (int i = 0; i < 6; i++) {
			this.addActor(chatButton[i]);
		}
	}
}

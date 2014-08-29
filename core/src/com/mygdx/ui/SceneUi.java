package com.mygdx.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.resource.Assets;
import com.mygdx.util.EventManager;

public class SceneUi extends Stage {
	private Label script;
	private Image characterImage;
	private Image backgroundImage;

	public SceneUi(Label script, Image characterImage, Image backgroundImage) {

		this.script = script;
		this.characterImage = characterImage;
		this.backgroundImage = backgroundImage;
		settingActor();
		addActor();

	}

	private void addActor() {
		this.addActor(backgroundImage);
		this.addActor(script);
		this.addActor(characterImage);

	}

	private void settingActor() {
		if (EventManager.getInstance().getEventType() == EventTypeEnum.SELECT) {
			script.setFontScale(Assets.realWidth / 1280);
			script.setWrap(true);
			script.setSize(Assets.realWidth * 0.781f,
					Assets.realHeight * 0.185f);
			script.setPosition(Assets.realWidth * 0.109375f,
					Assets.realHeight * 0.185f);
			characterImage.setSize(Assets.realWidth * 0.250f,
					Assets.realHeight * 0.555f);
			characterImage.setPosition(Assets.realWidth * 0.375f,
					Assets.realHeight * 0.37f);
			backgroundImage.setSize(Assets.realWidth, Assets.realHeight);

		} else {
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

}

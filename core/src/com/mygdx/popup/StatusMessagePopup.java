package com.mygdx.popup;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.inventory.HidingClickListener;
import com.mygdx.message.MessagePopup;
import com.mygdx.resource.Assets;

public class StatusMessagePopup extends MessagePopup {
	Stage stage;

	public StatusMessagePopup(String title, Skin skin) {
		super(title, Assets.skin);
		TextButton closeButton = new TextButton("X", skin);
		closeButton.addListener(new HidingClickListener(this));
		getButtonTable().add(closeButton).height(getPadTop());

		setPosition(400, 300);
		defaults().space(8);
		row().fill().expandX();
		setVisible(false);
	}
}

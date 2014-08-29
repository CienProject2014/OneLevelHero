package com.mygdx.message;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.resource.Assets;

public class Message extends Dialog {

	public Message(String title, Skin skin) {
		super(title, skin);
		initialize();
	}

	private void initialize() {
		padTop(60); // set padding on top of the dialog title  
		getButtonTable().defaults().height(60); // set buttons height  
		setPosition(300, 100);
		setModal(true);
		setMovable(true);
		setResizable(false);
		setVisible(false);
	}

	@Override
	public Message text(String text) {
		super.text(new Label(text, Assets.skin));
		return this;
	}

	public Message button(String buttonText, InputListener listener) {
		TextButton button = new TextButton(buttonText, Assets.skin);
		button.addListener(listener);
		button(button);
		return this;
	}

	@Override
	public float getPrefWidth() {
		// force dialog width  
		return 480f;
	}

	@Override
	public float getPrefHeight() {
		// force dialog height  
		return 240f;
	}
}

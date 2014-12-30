package com.mygdx.popup;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.inventory.HidingClickListener;
import com.mygdx.state.Assets;

public class MessagePopup extends Dialog {

	public MessagePopup(String title, Skin skin) {
		super(title, skin);
		TextButton closeButton = new TextButton("X", skin);
		closeButton.addListener(new HidingClickListener(this));
		getButtonTable().add(closeButton).height(getPadTop());
		initialize();
	}

	private void initialize() {
		padTop(70); // set padding on top of the dialog title  
		getButtonTable().defaults().height(50); // set buttons height  
		setPosition(400, 400);
		setSize(600, 200);
		setModal(true);
		setMovable(true);
		setResizable(false);
		setVisible(false);
	}

	@Override
	public MessagePopup text(String text) {
		super.text(new Label(text, Assets.skin));
		return this;
	}

	public MessagePopup button(String buttonText, InputListener listener) {
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

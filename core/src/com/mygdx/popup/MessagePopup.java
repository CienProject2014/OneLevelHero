package com.mygdx.popup;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.StaticAssets;
import com.mygdx.inventory.HidingClickListener;

public class MessagePopup extends Dialog {
	public MessagePopup(String title, Skin skin) {
		super(title, skin);
		TextButton openButton = new TextButton("O", skin);
		TextButton closeButton = new TextButton("X", skin);

		openButton.addListener(new HidingClickListener(this));
		closeButton.addListener(new HidingClickListener(this));
		getButtonTable().add(closeButton).height(getPadTop());
		getButtonTable().add(openButton).height(getPadTop());
		getButtonTable().add(openButton).width(getPadLeft());
		initialize();
	}

	private void initialize() {
		padTop(70); // set padding on top of the dialog title
		padLeft(100);
		getButtonTable().defaults().height(50); // set buttons height
		setPosition(600, 400);
		setSize(1000, 200);
		setModal(true);
		setMovable(true);
		setResizable(false);
		setVisible(false);
	}

	@Override
	public MessagePopup text(String text) {
		super.text(new Label(text, StaticAssets.skin));
		return this;
	}

	public MessagePopup button(String buttonText, InputListener listener) {
		TextButton button = new TextButton(buttonText, StaticAssets.skin);
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

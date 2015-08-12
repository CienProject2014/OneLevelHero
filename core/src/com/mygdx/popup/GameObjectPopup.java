package com.mygdx.popup;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.GameObjectButtonListener;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.model.event.GameObject;

public class GameObjectPopup extends Dialog {
	private AtlasUiAssets atlasUiAssets;
	private ListenerFactory listenerFactory;
	private GameObject gameObject;
	private ImageButton okayButton, closeButton;

	public GameObjectPopup() {
		this("오브젝트를 확인해 보시겠습니까?", StaticAssets.skin);
	}
	public AtlasUiAssets getAtlasUiAssets() {
		return atlasUiAssets;
	}

	public void setAtlasUiAssets(AtlasUiAssets atlasUiAssets) {
		this.atlasUiAssets = atlasUiAssets;
	}

	public ListenerFactory getListenerFactory() {
		return listenerFactory;
	}

	public void setListenerFactory(ListenerFactory listenerFactory) {
		this.listenerFactory = listenerFactory;
	}

	public GameObjectPopup(String title, Skin skin) {
		super(title, skin);
	}

	public void initialize() {
		okayButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("popupui_button_yes"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_yes"));
		closeButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("popupui_button_no"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_no"));
		closeButton.addListener(new HidingClickListener(this));
		GameObjectButtonListener gameObjectButtonListener = listenerFactory
				.getGameObjectButtonListener();
		gameObjectButtonListener.setGameObject(gameObject);
		okayButton.addListener(gameObjectButtonListener);

		getButtonTable().add(okayButton).height(100);
		getButtonTable().add(closeButton).height(100);
		getButtonTable().setBackground(
				atlasUiAssets.getAtlasUiFile("popupui_popup01"));
		getButtonTable().defaults().height(370); // set buttons height
		getButtonTable().defaults().width(860);
		setPosition(960, 540);
		setSize(860, 370);
		setModal(false);
		setResizable(false);
		setVisible(false);
	}
	@Override
	public GameObjectPopup text(String text) {
		super.text(new Label(text, StaticAssets.skin));
		return this;
	}

	public GameObjectPopup button(String buttonText, InputListener listener) {
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

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
}

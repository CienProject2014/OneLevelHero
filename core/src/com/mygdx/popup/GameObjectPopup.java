package com.mygdx.popup;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.PopupTypeEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.GameObjectButtonListener;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.model.event.GameObject;

public class GameObjectPopup extends Dialog {
	private AtlasUiAssets atlasUiAssets;
	private ListenerFactory listenerFactory;
	private GameObject gameObject;
	private ImageButton okayButton, closeButton;
	private Label questionLabel;
	private PopupTypeEnum popupType;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("GameObjectPopup");

	public GameObjectPopup() {
		this("", StaticAssets.skin);
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

	public void initialize(String name) {
		questionLabel = new Label(name + "를 확인해 보시겠습니까?", StaticAssets.skin);
		questionLabel.setAlignment(Align.center);
		questionLabel.setBounds(uiConstantsMap.get("questionLabelX"), uiConstantsMap.get("questionLabelY"),
				uiConstantsMap.get("questionLabelWidth"), uiConstantsMap.get("questionLabelHeight"));
		addActor(questionLabel);
		okayButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_yes"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_yes"));
		closeButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_no"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_no"));
		closeButton.addListener(new HidingClickListener(this));
		GameObjectButtonListener gameObjectButtonListener = listenerFactory.getGameObjectButtonListener();
		gameObjectButtonListener.setGameObject(gameObject);
		okayButton.addListener(gameObjectButtonListener);

		getButtonTable().bottom();
		getButtonTable().add(okayButton).height(uiConstantsMap.get("buttonHeight"))
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		getButtonTable().add(closeButton).padLeft(uiConstantsMap.get("closeButtonPadLeft")).height(90)
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		getButtonTable().setBackground(atlasUiAssets.getAtlasUiFile("popupui_popup01"));
		setCenterPosition(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f);
		setSize(uiConstantsMap.get("popupWidth"), uiConstantsMap.get("popupHeight"));
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

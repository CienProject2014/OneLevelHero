package com.mygdx.popup;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.GameObjectButtonListener;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.manager.BagManager;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.item.Equipment;

public class DropPopup extends Dialog {
	private AtlasUiAssets atlasUiAssets;
	private UiComponentAssets uiComponentAssets;
	private ListenerFactory listenerFactory;
	private GameObject gameObject;
	private Equipment equipment;
	private BagManager bagManager;
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;

	public void setConstantsAssets(ConstantsAssets constantsAssets) {
		this.constantsAssets = constantsAssets;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public UiComponentAssets getUiComponentAssets() {
		return uiComponentAssets;
	}

	public void setUiComponentAssets(UiComponentAssets uiComponentAssets) {
		this.uiComponentAssets = uiComponentAssets;
	}

	public DropPopup() {
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

	public DropPopup(String title, Skin skin) {
		super(title, skin);
	}

	public void initialize() {
		uiConstantsMap = constantsAssets.getUiConstants("EventStage");
		setQuestionLabel();
		setButton(atlasUiAssets, uiComponentAssets);

		setResizable(false);
		setVisible(false);
	}

	private void setButton(AtlasUiAssets atlasUiAssets, UiComponentAssets uiComponentAssets) {
		ImageButton okayButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_yes"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_yes"));
		ImageButton cancelButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_no"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_no"));
		cancelButton.addListener(new HidingClickListener(this));
		GameObjectButtonListener gameObjectButtonListener = listenerFactory.getGameObjectButtonListener();
		gameObjectButtonListener.setGameObject(gameObject);
		final Dialog thisDialog = this;
		okayButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				bagManager.dropItem(equipment);
				thisDialog.setVisible(false);
			}
		});

		getButtonTable().bottom();
		getButtonTable().add(okayButton).height(uiConstantsMap.get("buttonHeight"))
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		getButtonTable().add(cancelButton).padLeft(uiConstantsMap.get("closeButtonPadLeft")).height(90)
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		getButtonTable().setBackground(atlasUiAssets.getAtlasUiFile("popupui_popup01"));
		setCenterPosition(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f);
		setSize(uiConstantsMap.get("popupWidth"), uiConstantsMap.get("popupHeight"));

	}

	private void setQuestionLabel() {
		Label questionLabel = new Label("", StaticAssets.skin);
		questionLabel.setText("장비를 버리시겠습니까?");
		questionLabel.setAlignment(Align.center);
		questionLabel.setBounds(uiConstantsMap.get("questionLabelX"), uiConstantsMap.get("questionLabelY"),
				uiConstantsMap.get("questionLabelWidth"), uiConstantsMap.get("questionLabelHeight"));
		addActor(questionLabel);
	}

	public DropPopup button(String buttonText, InputListener listener) {
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

	public BagManager getBagManager() {
		return bagManager;
	}

	public void setBagManager(BagManager bagManager) {
		this.bagManager = bagManager;
	}
}

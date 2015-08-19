package com.mygdx.popup;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ItemEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.GameObjectButtonListener;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.unit.Hero;

public class EquipPopup extends Dialog {
	private AtlasUiAssets atlasUiAssets;
	private UiComponentAssets uiComponentAssets;
	private ListenerFactory listenerFactory;
	private GameObject gameObject;
	private Hero currentSelectedHero;
	private Equipment equipment;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("GameObjectPopup");

	public UiComponentAssets getUiComponentAssets() {
		return uiComponentAssets;
	}

	public void setUiComponentAssets(UiComponentAssets uiComponentAssets) {
		this.uiComponentAssets = uiComponentAssets;
	}

	public Hero getCurrentSelectedHero() {
		return currentSelectedHero;
	}

	public void setCurrentSelectedHero(Hero currentSelectedHero) {
		this.currentSelectedHero = currentSelectedHero;
	}

	public EquipPopup() {
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

	public EquipPopup(String title, Skin skin) {
		super(title, skin);
	}

	public void initialize() {
		setQuestionLabel(equipment);
		setButton(atlasUiAssets, uiComponentAssets, equipment);

		setModal(false);
		setResizable(false);
		setVisible(false);
	}

	private void setButton(AtlasUiAssets atlasUiAssets, UiComponentAssets uiComponentAssets, final Equipment equipment) {
		if (equipment.getItemType().equals(ItemEnum.HANDGRIP)) {
			TextButtonStyle buttonStyle = new TextButtonStyle(atlasUiAssets.getAtlasUiFile("popupui_button2"),
					atlasUiAssets.getAtlasUiFile("popupui_acbutton2"),
					atlasUiAssets.getAtlasUiFile("popupui_acbutton2"), uiComponentAssets.getFont());
			TextButton leftHandButton = new TextButton("왼손", buttonStyle);
			final Dialog thisDialog = this;
			leftHandButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (currentSelectedHero.getInventory().isLeftHandGripUsable(equipment)) {
						currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
					} else {

					}
					thisDialog.remove();
				}
			});

			TextButton rightHandButton = new TextButton("오른손", buttonStyle);
			rightHandButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (currentSelectedHero.getInventory().isRightHandGripUsable(equipment)) {
						currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
					} else {

					}
					thisDialog.setVisible(false);
				}
			});

			TextButton cancelButton = new TextButton("취소", buttonStyle);
			cancelButton.addListener(new HidingClickListener(this));

			getButtonTable().bottom();
			getButtonTable().add(rightHandButton).height(uiConstantsMap.get("buttonHeight"))
					.padBottom(uiConstantsMap.get("buttonPadBottom"));
			getButtonTable().add(leftHandButton).height(uiConstantsMap.get("buttonHeight"))
					.padLeft(uiConstantsMap.get("equipButtonPadLeft")).padBottom(uiConstantsMap.get("buttonPadBottom"));
			getButtonTable().add(cancelButton).padLeft(uiConstantsMap.get("equipButtonPadLeft"))
					.padBottom(uiConstantsMap.get("buttonPadBottom"));
			getButtonTable().setBackground(atlasUiAssets.getAtlasUiFile("popupui_popup01"));
			setCenterPosition(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f);
			setSize(uiConstantsMap.get("popupWidth"), uiConstantsMap.get("popupHeight"));
		} else {
			ImageButton okayButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_yes"),
					atlasUiAssets.getAtlasUiFile("popupui_acbutton_yes"));
			ImageButton cancelButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_no"),
					atlasUiAssets.getAtlasUiFile("popupui_acbutton_no"));
			cancelButton.addListener(new HidingClickListener(this));
			GameObjectButtonListener gameObjectButtonListener = listenerFactory.getGameObjectButtonListener();
			gameObjectButtonListener.setGameObject(gameObject);
			okayButton.addListener(gameObjectButtonListener);

			getButtonTable().bottom();
			getButtonTable().add(okayButton).height(uiConstantsMap.get("buttonHeight"))
					.padBottom(uiConstantsMap.get("buttonPadBottom"));
			getButtonTable().add(cancelButton).padLeft(uiConstantsMap.get("closeButtonPadLeft")).height(90)
					.padBottom(uiConstantsMap.get("buttonPadBottom"));
			getButtonTable().setBackground(atlasUiAssets.getAtlasUiFile("popupui_popup01"));
			setCenterPosition(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f);
			setSize(uiConstantsMap.get("popupWidth"), uiConstantsMap.get("popupHeight"));
		}

	}
	private void setQuestionLabel(Equipment equipment) {
		Label questionLabel = new Label("", StaticAssets.skin);

		switch (equipment.getItemType()) {
			case HANDGRIP :
				questionLabel.setText("어느 손에 장착하시겠습니까?");
				break;
			default :
				questionLabel.setText("장비를 장착해제하시니까?");
				break;
		}
		questionLabel.setAlignment(Align.center);
		questionLabel.setBounds(uiConstantsMap.get("questionLabelX"), uiConstantsMap.get("questionLabelY"),
				uiConstantsMap.get("questionLabelWidth"), uiConstantsMap.get("questionLabelHeight"));
		addActor(questionLabel);
	}

	public EquipPopup button(String buttonText, InputListener listener) {
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

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
}

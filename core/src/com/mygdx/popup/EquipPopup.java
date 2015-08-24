package com.mygdx.popup;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ItemEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.unit.Hero;

public class EquipPopup extends Dialog {
	private AtlasUiAssets atlasUiAssets;
	private UiComponentAssets uiComponentAssets;
	private ListenerFactory listenerFactory;
	private Hero currentSelectedHero;
	private Equipment equipment;
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;

	public void setConstantsAssets(ConstantsAssets constantsAssets) {
		this.constantsAssets = constantsAssets;
	}

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
		uiConstantsMap = constantsAssets.getUiConstants("EventStage");
		setQuestionLabel(equipment);
		setButton(atlasUiAssets, uiComponentAssets, equipment);

		setModal(false);
		setResizable(false);
		setVisible(false);
	}

	private void setButton(AtlasUiAssets atlasUiAssets, UiComponentAssets uiComponentAssets,
			final Equipment equipment) {
		final Dialog thisDialog = this;
		if (equipment.getItemType().equals(ItemEnum.HANDGRIP)) {
			TextButtonStyle buttonStyle = new TextButtonStyle(atlasUiAssets.getAtlasUiFile("popupui_button2"),
					atlasUiAssets.getAtlasUiFile("popupui_acbutton2"),
					atlasUiAssets.getAtlasUiFile("popupui_acbutton2"), uiComponentAssets.getFont());
			TextButton leftHandButton = new TextButton("왼손", buttonStyle);
			leftHandButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					ItemEnum.HandgripState leftHandgripState = currentSelectedHero.getInventory()
							.checkLeftHandGripState();
					switch (leftHandgripState) {
					case TWO_ZERO:
						currentSelectedHero.unEquipLeftHandGrip();
						currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						break;
					case ONE_ZERO:
						currentSelectedHero.unEquipLeftHandGrip();
						currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						break;
					case ONE_ONE:
						if (equipment.isTwoHanded()) {
							currentSelectedHero.unEquipRightHandGrip();
							currentSelectedHero.unEquipLeftHandGrip();
							currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						} else {
							currentSelectedHero.unEquipLeftHandGrip();
							currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						}
					case ZERO_ZERO:
						currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						break;
					case ZERO_ONE:
						if (equipment.isTwoHanded()) {
							currentSelectedHero.unEquipRightHandGrip();
							currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						} else {
							currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						}
						break;
					case ZERO_TWO:
						currentSelectedHero.unEquipRightHandGrip();
						currentSelectedHero.equipLeftHandGrip(equipment.getItemPath());
						break;
					default:
						Gdx.app.log("EquipPopup", "rightHandgripState 정보 오류");
						break;
					}
					thisDialog.setVisible(false);
				}
			});

			TextButton rightHandButton = new TextButton("오른손", buttonStyle);
			rightHandButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					ItemEnum.HandgripState rightHandgripState = currentSelectedHero.getInventory()
							.checkRightHandGripState();
					switch (rightHandgripState) {
					case TWO_ZERO:
						currentSelectedHero.unEquipRightHandGrip();
						currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						break;
					case ONE_ZERO:
						currentSelectedHero.unEquipRightHandGrip();
						currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						break;
					case ZERO_ZERO:
						currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						break;
					case ONE_ONE:
						if (equipment.isTwoHanded()) {
							currentSelectedHero.unEquipRightHandGrip();
							currentSelectedHero.unEquipLeftHandGrip();
							currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						} else {
							currentSelectedHero.unEquipRightHandGrip();
							currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						}
					case ZERO_ONE:
						if (equipment.isTwoHanded()) {
							currentSelectedHero.unEquipLeftHandGrip();
							currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						} else {
							currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						}
						break;
					case ZERO_TWO:
						currentSelectedHero.unEquipLeftHandGrip();
						currentSelectedHero.equipRightHandGrip(equipment.getItemPath());
						break;
					default:
						Gdx.app.log("EquipPopup", "rightHandgripState 정보 오류");
						break;
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
			okayButton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					if (equipment.getItemType().equals(ItemEnum.CLOTHES)) {
						if (!currentSelectedHero.getInventory().getClothes().isEmpty()) {
							currentSelectedHero.unEquipClothes();
							currentSelectedHero.equipClothes(equipment.getItemPath());
						} else {
							currentSelectedHero.equipClothes(equipment.getItemPath());
						}
					} else {
						if (!currentSelectedHero.getInventory().getAccessory().isEmpty()) {
							currentSelectedHero.unEquipAccessory();
							currentSelectedHero.equipAccessory(equipment.getItemPath());
						} else {
							currentSelectedHero.equipAccessory(equipment.getItemPath());
						}
					}
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

	}

	private void setQuestionLabel(Equipment equipment) {
		Label questionLabel = new Label("", StaticAssets.skin);

		switch (equipment.getItemType()) {
		case HANDGRIP:
			questionLabel.setText("어느 손에 장착하시겠습니까?");
			break;
		default:
			questionLabel.setText("장비를 장착하시겠습니까?");
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

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
}

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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.model.unit.Hero;

public class UnEquipPopup extends Dialog {
	private AtlasUiAssets atlasUiAssets;
	private UiComponentAssets uiComponentAssets;
	private ListenerFactory listenerFactory;
	private Hero currentSelectedHero;
	private int index;
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;

	public void setConstantsAssets(ConstantsAssets constantsAssets) {
		this.constantsAssets = constantsAssets;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public UnEquipPopup() {
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

	public UnEquipPopup(String title, Skin skin) {
		super(title, skin);
	}

	public void initialize() {
		uiConstantsMap = constantsAssets.getUiConstants("EventStage");
		setQuestionLabel();
		setButton(atlasUiAssets, uiComponentAssets, index);

		setModal(false);
		setResizable(false);
		setVisible(false);
	}

	private void setButton(AtlasUiAssets atlasUiAssets, UiComponentAssets uiComponentAssets, final int index) {
		ImageButton okayButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_yes"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_yes"));
		ImageButton cancelButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_no"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_no"));
		cancelButton.addListener(new HidingClickListener(this));
		final Dialog thisDialog = this;
		okayButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				switch (index) {
				case 0:
					currentSelectedHero.unEquipRightHandGrip();
					break;
				case 1:
					currentSelectedHero.unEquipLeftHandGrip();
					break;
				case 2:
					currentSelectedHero.unEquipClothes();
					break;
				case 3:
					currentSelectedHero.unEquipAccessory();
					break;
				default:
					Gdx.app.log("UnEquipPopup", "인덱스 정보 에러");
					break;
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

	private void setQuestionLabel() {
		Label questionLabel = new Label("", StaticAssets.skin);
		questionLabel.setText("장비를 장착해제 하시겠습니까?");
		questionLabel.setAlignment(Align.center);
		questionLabel.setBounds(uiConstantsMap.get("questionLabelX"), uiConstantsMap.get("questionLabelY"),
				uiConstantsMap.get("questionLabelWidth"), uiConstantsMap.get("questionLabelHeight"));
		addActor(questionLabel);
	}

	public UnEquipPopup button(String buttonText, InputListener listener) {
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

package com.mygdx.popup;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.CloseButtonListener;
import com.mygdx.listener.RunAwayListener;

public class SkillRunPopup extends Dialog {
	private AtlasUiAssets atlasUiAssets;
	private ListenerFactory listenerFactory;
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;
	private Label questionLabel;
	private ImageButton okayButton, closeButton;

	public SkillRunPopup(String title, Skin skin) {
		super(title, skin);
	}

	public SkillRunPopup() {
		this("", StaticAssets.skin);
	}

	public void initialize(String labelText) {
		getButtonTable().clear();
		uiConstantsMap = constantsAssets.getUiConstants("GameObjectPopup");
		questionLabel = new Label(labelText, StaticAssets.skin);
		questionLabel.setAlignment(Align.center);
		questionLabel.setBounds(uiConstantsMap.get("questionLabelX"), uiConstantsMap.get("questionLabelY"),
				uiConstantsMap.get("questionLabelWidth"), uiConstantsMap.get("questionLabelHeight"));
		addActor(questionLabel);
		addActor(questionLabel);
		okayButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_yes"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_yes"));
		closeButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_no"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_no"));
		RunAwayListener runAwayListener = listenerFactory.getRunAwayListener();
		CloseButtonListener closeButtonListener = listenerFactory.getCloseButtonListener();
		okayButton.addListener(runAwayListener);
		closeButton.addListener(closeButtonListener);

		getButtonTable().bottom();
		getButtonTable().add(okayButton).height(uiConstantsMap.get("buttonHeight"))
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		getButtonTable().add(closeButton).padLeft(uiConstantsMap.get("closeButtonPadLeft")).height(90)
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		getButtonTable().setBackground(atlasUiAssets.getAtlasUiFile("popupui_popup01"));
		setCenterPosition(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f);
		setSize(uiConstantsMap.get("popupWidth"), uiConstantsMap.get("popupHeight"));
		setResizable(false);
		setVisible(false);
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

	public void setConstantsAssets(ConstantsAssets constantsAssets) {
		this.constantsAssets = constantsAssets;
	}
}

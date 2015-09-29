package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.RestButtonListener;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.screen.ForkScreen;

public class ForkRestPopupStage extends BaseOneLevelStage {
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private PositionManager positionManager;
	private ImageButton okayButton, closeButton;
	private Label questionLabel;
	private ImageButton popupImage;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("GameObjectPopup");
		popupImage = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_popup01"),
				atlasUiAssets.getAtlasUiFile("popupui_popup01"));
		Table popupTable = new Table();
		popupTable.add(popupImage);
		popupTable.setBackground(new TextureRegionDrawable(new TextureRegion(textureManager
				.getBackgroundTexture("popupui"))));
		tableStack.add(popupTable);
		questionLabel = new Label("휴식을 취하겠습니까?", StaticAssets.skin);
		Table questionLabelTable = new Table();
		questionLabel.setAlignment(Align.center);
		questionLabelTable.add(questionLabel);
		questionLabelTable.bottom().left();
		questionLabelTable.padLeft(780).padBottom(600);
		tableStack.add(questionLabelTable);
		okayButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_yes"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_yes"));
		closeButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_no"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_no"));
		// closeButton.addListener();
		RestButtonListener restButtonListener = listenerFactory.getRestButtonListener();
		restButtonListener.setPosition("fork");
		okayButton.addListener(restButtonListener);
		closeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				ForkScreen.isClickPopup = false;
			}
		});
		Table buttonTable = new Table();
		buttonTable.bottom().left();
		buttonTable.padLeft(580).padBottom(380);
		buttonTable.add(okayButton).height(uiConstantsMap.get("buttonHeight"));
		buttonTable.add(closeButton).padLeft(uiConstantsMap.get("closeButtonPadLeft")).height(90);

		tableStack.add(buttonTable);
		return this;
	}
}

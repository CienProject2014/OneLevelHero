package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.popup.SoundPopup;

public class MenuStage extends BaseOneLevelStage {
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private SoundManager soundManager;

	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;
	private SoundPopup soundPopup;
	private Table buttonTable;

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("MenuStage");
		buttonTable = new Table();
		soundPopup = new SoundPopup();
		ImageButton startButton;
		ImageButton settingButton;
		ImageButton albumButton;
		ImageButton creditButton;
		Float buttonWidth = 630f;
		Float buttonHeight = 195f;
		ImageButton logo;

		Image background = new Image(textureManager.getBackgroundTexture("main_background"));

		// 이미지추가
		startButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture("texture/ui/title/title_start.png"))));
		settingButton = new ImageButton(
				new SpriteDrawable(new Sprite(new Texture("texture/ui/title/title_setting.png"))));
		albumButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture("texture/ui/title/title_album.png"))));
		creditButton = new ImageButton(
				new SpriteDrawable(new Sprite(new Texture("texture/ui/title/title_credit.png"))));

		// 클릭리스너추가
		startButton.addListener(listenerFactory.getLoadListener());

		settingButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				soundPopup.setAtlasUiAssets(atlasUiAssets);
				soundPopup.setListenerFactory(listenerFactory);
				soundPopup.setConstantsAssets(constantsAssets);
				soundPopup.setMusicManager(musicManager);
				soundPopup.setSoundManager(soundManager);
				soundPopup.initialize();
				addActor(soundPopup);
				soundPopup.setVisible(true);
			}
		});

		albumButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				screenFactory.push(ScreenEnum.COLLECTION);
			}
		});

		creditButton.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				screenFactory.push(ScreenEnum.CREDIT);
			}
		});

		logo = new ImageButton(atlasUiAssets.getAtlasUiFile("title"));
		logo.bottom().left();
		logo.padLeft(0.113f * StaticAssets.BASE_WINDOW_WIDTH).padBottom(0.278f * StaticAssets.BASE_WINDOW_HEIGHT);

		// logo.

		buttonTable.top().right();
		buttonTable.padRight(uiConstantsMap.get("tTablePadRight"));
		buttonTable.setFillParent(true);
		buttonTable.add(startButton).height(uiConstantsMap.get("tButtonHeight"))
				.width(uiConstantsMap.get("tButtonWidth")).padTop(uiConstantsMap.get("tTablePadTop"));
		buttonTable.row();
		buttonTable.add(settingButton).height(uiConstantsMap.get("tButtonHeight"))
				.width(uiConstantsMap.get("tButtonWidth")).padTop(uiConstantsMap.get("tButtonSpace"));
		buttonTable.row();
		buttonTable.add(albumButton).height(uiConstantsMap.get("tButtonHeight"))
				.width(uiConstantsMap.get("tButtonWidth")).padTop(uiConstantsMap.get("tButtonSpace"));
		buttonTable.row();
		buttonTable.add(creditButton).height(buttonHeight).width(buttonWidth)
				.padTop(uiConstantsMap.get("tButtonSpace"));
		background.setSize(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		tableStack.addActor(background);
		tableStack.addActor(logo);
		tableStack.addActor(buttonTable);

		return this;
	}
}

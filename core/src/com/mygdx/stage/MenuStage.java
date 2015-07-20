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
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;

public class MenuStage extends BaseOneLevelStage {
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("MenuStage");

	private Table buttonTable;

	public Stage makeStage() {
		super.makeStage();
		buttonTable = new Table();

		ImageButton startButton;
		ImageButton settingButton;
		ImageButton albumButton;
		ImageButton creditButton;
		Float buttonWidth = 630f;
		Float buttonHeight = 195f;
		Image logo = new Image(atlasUiAssets.getAtlasUiFile("title"));
		Texture texture = StaticAssets.backgroundTextureMap
				.get("main_background");
		Image background = new Image(texture);

		// 이미지추가
		startButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/ui/title/title_start.png"))));
		settingButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/ui/title/title_setting.png"))));
		albumButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/ui/title/title_album.png"))));
		creditButton = new ImageButton(new SpriteDrawable(new Sprite(
				new Texture("texture/ui/title/title_credit.png"))));

		// 클릭리스너추가
		startButton.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.LOAD);
			}
		});

		settingButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.OPTION);
			}
		});

		albumButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.COLLECTION);
			}
		});

		creditButton.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.CREDIT);
			}
		});

		logo.setHeight((int) (0.4f * StaticAssets.BASE_WINDOW_HEIGHT));
		logo.setWidth((int) (0.6f * StaticAssets.BASE_WINDOW_WIDTH));

		buttonTable.top().right();
		buttonTable.padRight(uiConstantsMap.get("tTablePadRight"));
		buttonTable.setFillParent(true);
		buttonTable.add(startButton)
				.height(uiConstantsMap.get("tButtonHeight"))
				.width(uiConstantsMap.get("tButtonWidth"))
				.padTop(uiConstantsMap.get("tTablePadTop"));
		buttonTable.row();
		buttonTable.add(settingButton)
				.height(uiConstantsMap.get("tButtonHeight"))
				.width(uiConstantsMap.get("tButtonWidth"))
				.padTop(uiConstantsMap.get("tButtonSpace"));
		buttonTable.row();
		buttonTable.add(albumButton)
				.height(uiConstantsMap.get("tButtonHeight"))
				.width(uiConstantsMap.get("tButtonWidth"))
				.padTop(uiConstantsMap.get("tButtonSpace"));
		buttonTable.row();
		buttonTable.add(creditButton).height(buttonHeight).width(buttonWidth)
				.padTop(uiConstantsMap.get("tButtonSpace"));
		logo.setPosition((int) (0.2f * StaticAssets.BASE_WINDOW_WIDTH),
				(int) (0.3f * StaticAssets.BASE_WINDOW_HEIGHT));
		background.setSize(StaticAssets.BASE_WINDOW_WIDTH,
				StaticAssets.BASE_WINDOW_HEIGHT);

		tableStack.addActor(background);
		tableStack.addActor(logo);
		tableStack.addActor(buttonTable);

		return this;
	}
}

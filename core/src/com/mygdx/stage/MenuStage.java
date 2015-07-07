package com.mygdx.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.TouchListener;
import com.mygdx.state.StaticAssets;

public class MenuStage extends BaseOneLevelStage {
	private ImageButton[] button;

	public Stage makeStage() {
		button = new ImageButton[4];
		Image logo = new Image(assets.atlasUiMap.get("title"));
		Texture texture = StaticAssets.backgroundTextureMap
				.get("main_background");
		Image background = new Image(texture);

		Table table = new Table(assets.skin);

		button[0] = new ImageButton(
		// FIXME 버튼하나 없음
				assets.atlasUiMap.get("button_start_after"),
				assets.atlasUiMap.get("button_start_after"));
		button[1] = new ImageButton(
				assets.atlasUiMap.get("button_option_before"),
				assets.atlasUiMap.get("button_option_after"));
		button[2] = new ImageButton(
				assets.atlasUiMap.get("button_credit_before"),
				assets.atlasUiMap.get("button_credit_after"));
		button[3] = new ImageButton(
				assets.atlasUiMap.get("button_extra_before"),
				assets.atlasUiMap.get("button_extra_after"));

		button[0].addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.LOAD);
			}
		}));
		button[1].addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.OPTION);
			}
		}));
		button[2].addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.CREDIT);
			}
		}));
		button[3].addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.COLLETION);
			}
		}));

		logo.setHeight((int) (0.4f * StaticAssets.windowHeight));
		logo.setWidth((int) (0.6f * StaticAssets.windowWidth));
		table.setFillParent(true);

		table.add(button[3]).height(0.35f * StaticAssets.windowHeight)
				.width(0.3f * StaticAssets.windowWidth).expand().top().left();
		table.add(button[2]).height(0.35f * StaticAssets.windowHeight)
				.width(0.3f * StaticAssets.windowWidth).top().right();
		table.row();
		table.add(button[0]).height(0.35f * StaticAssets.windowHeight)
				.width(0.3f * StaticAssets.windowWidth).bottom().left();
		table.add(button[1]).height(0.35f * StaticAssets.windowHeight)
				.width(0.3f * StaticAssets.windowWidth).bottom().right();
		logo.setPosition((int) (0.2f * StaticAssets.windowWidth),
				(int) (0.3f * StaticAssets.windowHeight));
		background.setSize(StaticAssets.windowWidth, StaticAssets.windowHeight);

		this.addActor(background);
		this.addActor(logo);
		this.addActor(table);

		return this;
	}
}

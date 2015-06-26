package com.mygdx.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.state.StaticAssets;

public class MenuStage extends OneLevelStage {
	private ImageButton[] button;

	public Stage makeStage() {
		button = new ImageButton[4];
		Image logo = new Image(assets.atlasUiMap.get("title"));
		Texture texture = StaticAssets.backgroundTextureMap.get("main_background");
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

		button[0].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.LOAD);
			}
		});
		button[1].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.OPTION);
			}
		});
		button[2].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.CREDIT);
			}
		});
		button[3].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.COLLETION);
			}
		});

		logo.setHeight((int) (0.4f * assets.windowHeight));
		logo.setWidth((int) (0.6f * assets.windowWidth));
		table.setFillParent(true);

		table.add(button[3]).height(0.35f * assets.windowHeight).width(0.3f * assets.windowWidth).expand().top().left();
		table.add(button[2]).height(0.35f * assets.windowHeight).width(0.3f * assets.windowWidth).top().right();
		table.row();
		table.add(button[0]).height(0.35f * assets.windowHeight).width(0.3f * assets.windowWidth).bottom().left();
		table.add(button[1]).height(0.35f * assets.windowHeight).width(0.3f * assets.windowWidth).bottom().right();
		logo.setPosition((int) (0.2f * assets.windowWidth), (int) (0.3f * assets.windowHeight));
		background.setSize(assets.windowWidth, assets.windowHeight);

		this.addActor(background);
		this.addActor(logo);
		this.addActor(table);

		return this;
	}
}

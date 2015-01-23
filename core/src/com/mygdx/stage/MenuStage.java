package com.mygdx.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.state.Assets;

public class MenuStage extends Stage {

	private ImageButton[] button;

	public MenuStage(String stageName) {
		makeMainMenu();
	}

	public void makeMainMenu() {
		button = new ImageButton[4];
		Image logo = new Image(Assets.atlasUiMap.get("title"));
		Texture texture = Assets.backgroundTextureMap.get("main_background");
		Image background = new Image(texture);

		Table table = new Table(Assets.skin);

		button[0] = new ImageButton(
		// FIXME 버튼하나 없음
				Assets.atlasUiMap.get("button_start_after"),
				Assets.atlasUiMap.get("button_start_after"));
		button[1] = new ImageButton(
				Assets.atlasUiMap.get("button_option_before"),
				Assets.atlasUiMap.get("button_option_after"));
		button[2] = new ImageButton(
				Assets.atlasUiMap.get("button_credit_before"),
				Assets.atlasUiMap.get("button_credit_after"));
		button[3] = new ImageButton(
				Assets.atlasUiMap.get("button_extra_before"),
				Assets.atlasUiMap.get("button_extra_after"));

		button[0].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.LOAD);
			}
		});
		button[1].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.OPTION);
			}
		});
		button[2].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.CREDIT);

			}
		});
		button[3].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.COLLETION);

			}
		});

		logo.setHeight((int) (0.4f * Assets.windowHeight));
		logo.setWidth((int) (0.6f * Assets.windowWidth));
		table.setFillParent(true);

		table.add(button[3]).height(0.35f * Assets.windowHeight)
				.width(0.3f * Assets.windowWidth).expand().top().left();
		table.add(button[2]).height(0.35f * Assets.windowHeight)
				.width(0.3f * Assets.windowWidth).top().right();
		table.row();
		table.add(button[0]).height(0.35f * Assets.windowHeight)
				.width(0.3f * Assets.windowWidth).bottom().left();
		table.add(button[1]).height(0.35f * Assets.windowHeight)
				.width(0.3f * Assets.windowWidth).bottom().right();
		logo.setPosition((int) (0.2f * Assets.windowWidth),
				(int) (0.3f * Assets.windowHeight));
		background.setSize(Assets.windowWidth, Assets.windowHeight);

		this.addActor(background);
		this.addActor(logo);
		this.addActor(table);

	}
}

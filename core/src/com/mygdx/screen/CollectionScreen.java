﻿package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.TouchListener;

public class CollectionScreen extends BaseScreen {
	private Stage stage;
	private TextButton endingButton;
	private TextButton cgButton;
	private TextButton bgmButton;
	private TextButton backButton;

	public CollectionScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(assets.skin);

		endingButton = new TextButton("엔딩", assets.skin);
		cgButton = new TextButton("CG", assets.skin);
		bgmButton = new TextButton("BGM", assets.skin);
		backButton = new TextButton("Back", assets.skin);

		endingButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.ENDING);
			}
		}));
		cgButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.CG);
			}
		}));
		bgmButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.BGM);
			}
		}));
		backButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.MENU);
			}
		}));

		table.setFillParent(true);
		table.add(endingButton).expand().width(240).height(240).top().left();
		table.add(cgButton).width(240).height(240).top().right();
		table.row();
		table.add(bgmButton).width(240).height(240).bottom().left();
		table.add(backButton).bottom();
		table.row();

		stage.addActor(table);
	}
}

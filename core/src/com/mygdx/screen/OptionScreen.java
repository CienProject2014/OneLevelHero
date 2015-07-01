package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.TouchListener;
import com.mygdx.popup.SoundPopup;

public class OptionScreen extends RootScreen {
	private Stage stage;
	private TextButton soundButton;
	private TextButton savedataButton;
	private TextButton bonusPointButton;
	private TextButton backButton;

	public OptionScreen() {
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
		soundButton = new TextButton("Sound", assets.skin);
		savedataButton = new TextButton("SaveData", assets.skin);
		bonusPointButton = new TextButton("BonusPoint", assets.skin);
		backButton = new TextButton("Back", assets.skin);

		soundButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				stage.addActor(new SoundPopup("SoundSetting", stage));
			}
		}));

		savedataButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.SAVE);
			}
		}));
		bonusPointButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.BONUS_POINT);
			}
		}));
		backButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.MENU);
			}
		}));

		table.setFillParent(true);
		table.add(soundButton).expand().width(240).height(240).top().left();
		table.add(savedataButton).width(240).height(240).top().right();
		table.row();
		table.add(bonusPointButton).width(240).height(240).bottom().left();
		table.add(backButton).width(240).height(240).bottom().right();
		table.row();

		stage.addActor(table);
	}
}

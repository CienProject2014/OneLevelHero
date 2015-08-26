package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.SaveManager;

public class GameOverStage extends Stage {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private SaveManager saveManager;

	public Stage makeStage() {
		Label label = new Label("GAME OVER ... ", StaticAssets.skin);
		TextButton goToMenuButton = new TextButton("처음 화면으로...", StaticAssets.skin);
		goToMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				initInfo();
				screenFactory.popAllAndPush(ScreenEnum.MENU);
			}
		});
		Table table = new Table();
		table.setFillParent(true);
		label.setSize(50, 50);
		goToMenuButton.setSize(250, 90);
		table.add(label);
		table.row();
		table.add(goToMenuButton);
		addActor(table);
		return this;
	}

	private void initInfo() {
		saveManager.setNewGame();
	}
}

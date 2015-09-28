package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.TextureManager;

public class GameOverStage extends BaseOneLevelStage {
	@Autowired
	private TextureManager textureManager;
	private Table table;

	public Stage makeStage() {
		super.makeStage();
		table = new Table();
		table.setBackground(
				new TextureRegionDrawable(new TextureRegion(textureManager.getBackgroundTexture("gameover"))));
		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.MENU);
			}
		});
		tableStack.add(table);
		return this;
	}

}

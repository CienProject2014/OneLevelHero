package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class InventoryScreen extends BaseScreen {
	private Stage inventoryStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		inventoryStage.draw();
		inventoryStage.act(delta);
	}

	@Override
	public void show() {
		inventoryStage = stageFactory.makeStage(StageEnum.INVENTORY);
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		multiplexer.addProcessor(i++, inventoryStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}

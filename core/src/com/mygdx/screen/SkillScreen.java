package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class SkillScreen extends BaseScreen {
	private Stage skillStage;

	@Override
	public void render(float delta) {
		super.render(delta);

		skillStage.draw();
	}

	@Override
	public void show() {
		skillStage = stageFactory.makeStage(StageEnum.SKILL);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, skillStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}

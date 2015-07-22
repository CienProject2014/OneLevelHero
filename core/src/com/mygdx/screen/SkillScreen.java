package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.ScreenFactory;

public class SkillScreen extends BaseScreen {
	@Autowired
	private ScreenFactory screenFactory;
	private Stage skillStage;
	private Stage monsterStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		monsterStage.draw();
		skillStage.draw();
	}

	@Override
	public void show() {
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);
		skillStage = stageFactory.makeStage(StageEnum.SKILL);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, skillStage);
		Gdx.input.setInputProcessor(multiplexer);
		skillStage.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.BATTLE);
			}
		});
	}
}

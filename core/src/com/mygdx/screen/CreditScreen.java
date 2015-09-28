package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;

/**
 * 기여자 목록을 뿌려주는 Screen 클래스
 * 
 * @author Velmont
 * 
 */
public class CreditScreen extends BaseScreen {
	private Stage creditStage;
	@Autowired
	private StageFactory stageFactory;

	@Override
	public void render(float delta) {
		super.render(delta);
		creditStage.draw();
	}

	@Override
	public void show() {
		creditStage = stageFactory.makeStage(StageEnum.CREDIT);
		Gdx.input.setInputProcessor(creditStage);
	}
}

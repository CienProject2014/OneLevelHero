package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.AnimationManager;
import com.mygdx.manager.MusicManager;

public class BattleScreen implements Screen {
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private AnimationManager animationManager;
	private Stage gameUiStage, characterUiStage, monsterStage, battleStage;

	public BattleScreen() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		monsterStage.draw();
		characterUiStage.draw();
		gameUiStage.draw();
		battleStage.draw();
		
		// Animation이 진행중일때는 사용자의 입력에 대한 행동을 수행하지 않음
		
		if (animationManager.isPlaying()) {
			animationManager.nextFrame(delta);
		}
		else { 
			monsterStage.act();
			characterUiStage.act(delta);
			gameUiStage.act();
			battleStage.act();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		characterUiStage = stageFactory.makeStage(StageEnum.CHARACTER_UI);
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);
		battleStage = stageFactory.makeBattleStage();

		setInputProcessor();
		//musicManager.setBattleMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		// CharacterUiStage 를 Static 하게 사용하면 addProcessor도 한 번만 하면 되지 않을까?
		multiplexer.addProcessor(0, gameUiStage);
		multiplexer.addProcessor(1, characterUiStage);
		multiplexer.addProcessor(2, monsterStage);
		multiplexer.addProcessor(3, battleStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	public StageFactory getStageFactory() {
		return stageFactory;
	}

	public void setStageFactory(StageFactory stageFactory) {
		this.stageFactory = stageFactory;
	}

	public MusicManager getMusicManager() {
		return musicManager;
	}

	public void setMusicManager(MusicManager musicManager) {
		this.musicManager = musicManager;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}
	

}

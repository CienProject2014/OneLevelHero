package com.mygdx.screen;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.AssetsManager;

/**
 * VillageStage와 GameUiStage를 addActor()해서 보여주는 Screen 마을의 경우 multiplexer를 이용하여
 * 2개의 화면을 교차로 보여준다.
 * 
 * @author Velmont
 * 
 */
public class VillageScreen extends BaseScreen {
	@Autowired
	private AssetsManager assetsManager;
	private String villageName;
	private Stage villageStage;
	private Stage gameUiStage;
	private Stage characterUiStage;
	private boolean state = true;

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public VillageScreen(String villagename) {
		this.villageName = villagename;
	}

	public VillageScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		villageStage.act();
		characterUiStage.act();
		gameUiStage.act();

		villageStage.draw();
		gameUiStage.draw();
		characterUiStage.draw();
		if (showLoadStage) {
			loadPopupStage.draw();
		}
		// 카메라를 지속적으로 업데이트 해준다.
		villageStage.getViewport().getCamera().update();
	}

	@Override
	public void show() {
		musicManager.setWorldNodeMusicAndPlay();
		assetsManager.clear();
		assetsManager.load("orig" + File.separator + "skill_" + "pack.atlas", TextureAtlas.class);
		assetsManager.load("orig" + File.separator + "save_" + "pack.atlas", TextureAtlas.class);
		assetsManager.load("orig" + File.separator + "load_" + "pack.atlas", TextureAtlas.class);
		assetsManager.load("orig" + File.separator + "item_" + "pack.atlas", TextureAtlas.class);
		villageStage = stageFactory.makeStage(StageEnum.VILLAGE);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		characterUiStage = stageFactory.makeStage(StageEnum.CHARACTER_UI);
		loadPopupStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		// 여러 스테이지에 인풋 프로세서를 동시에 할 당한다
		setInputProcessor();

	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.
		if (showLoadStage) {

			multiplexer.addProcessor(0, loadPopupStage);
		} else {

			multiplexer.addProcessor(0, gameUiStage);
			multiplexer.addProcessor(1, villageStage);
			multiplexer.addProcessor(2, characterUiStage);
		}
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void hide() {
		dispose();
	}

	public Stage getVillageStage() {
		return villageStage;
	}

	public void setVillageStage(Stage villageStage) {
		this.villageStage = villageStage;
	}
}

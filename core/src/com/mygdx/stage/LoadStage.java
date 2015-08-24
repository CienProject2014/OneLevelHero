package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.LoadNewManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.screen.LoadScreen;

public class LoadStage extends BaseOneLevelStage {
	@Autowired
	protected CurrentInfo currentInfo;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private LoadNewManager loadManager;
	private TextButton loadButton;
	private TextButton newStartButton;
	private TextButton backButton;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private TextureManager textureManager;

	public Stage makeStage() {
		super.makeStage();
		Table table = new Table(uiComponentAssets.getSkin());
		backButton = new TextButton("Back", uiComponentAssets.getSkin());
		loadButton = new TextButton("Load", uiComponentAssets.getSkin());
		newStartButton = new TextButton("NewStart", uiComponentAssets.getSkin());
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.MENU);
			}
		});
		loadButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				LoadScreen.isPopupTouched = true;
			}
		});
		newStartButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				eventManager.setCurrentEventNpc("prologue");
				loadManager.loadNewGame();
			}
		});

		table.setFillParent(true);
		table.add(loadButton).expand();
		table.row();
		table.add(newStartButton).expand();
		table.row();
		table.add(backButton).bottom();
		tableStack.addActor(table);
		return this;
	}
}

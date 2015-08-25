package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.SaveManager;

public class LoadStage extends BaseOneLevelStage {
	@Autowired
	protected CurrentInfo currentInfo;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private SaveManager saveManager;
	@Autowired
	private ListenerFactory listenerFactory;
	private TextButton loadButton;
	private TextButton newStartButton;
	private TextButton backButton;

	public Stage makeStage() {
		super.makeStage();
		Table table = new Table(uiComponentAssets.getSkin());
		backButton = new TextButton("Back", uiComponentAssets.getSkin());
		loadButton = new TextButton("Load", uiComponentAssets.getSkin());
		newStartButton = new TextButton("NewStart", uiComponentAssets.getSkin());
		backButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.MENU);
			}
		});
		loadButton.addListener(listenerFactory.getLoadListener());
		newStartButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveManager.setNewGame();
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

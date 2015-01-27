package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.SaveVersion;
import com.mygdx.currentState.CurrentState;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.state.Assets;

@Component
@Scope("prototype")
public class LoadScreen implements Screen {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private CurrentState currentState;
	@Autowired
	private EventManager eventManager;
	private Stage stage;
	private TextButton newstartButton;
	private TextButton backButton;

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Table table = new Table(Assets.skin);
		backButton = new TextButton("Back", Assets.skin);
		newstartButton = new TextButton("NewStart", Assets.skin);
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.MENU);

			}
		});
		newstartButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				currentState.setVersion(SaveVersion.NEW);

				//프롤로그 정보 주입
				eventManager.setEventInfo(Assets.npcMap.get("prologue"), 0,
						false);
				screenFactory.show(ScreenEnum.EVENT);

			}
		});

		table.setFillParent(true);
		table.add(newstartButton).expand();
		table.row();
		table.add(backButton).bottom();
		stage.addActor(table);
		// stage.addActor(backButton);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

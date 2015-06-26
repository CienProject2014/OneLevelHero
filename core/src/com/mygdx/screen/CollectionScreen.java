package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.Assets;

public class CollectionScreen implements Screen {
	@Autowired
	private Assets assets;
	@Autowired
	private ScreenFactory screenFactory;
	private Stage stage;
	private TextButton endingButton;
	private TextButton cgButton;
	private TextButton bgmButton;
	private TextButton backButton;
	private TextButtonStyle textButtonStyle;
	private BitmapFont font;

	public CollectionScreen() {

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(assets.skin);

		endingButton = new TextButton("엔딩", assets.skin);
		cgButton = new TextButton("CG", assets.skin);
		bgmButton = new TextButton("BGM", assets.skin);
		backButton = new TextButton("Back", assets.skin);

		endingButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.ENDING);
			}
		});
		cgButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				screenFactory.show(ScreenEnum.CG);
			}
		});
		bgmButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				screenFactory.show(ScreenEnum.BGM);
			}
		});
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

		table.setFillParent(true);
		table.add(endingButton).expand().width(240).height(240).top().left();
		table.add(cgButton).width(240).height(240).top().right();
		table.row();
		table.add(bgmButton).width(240).height(240).bottom().left();
		table.add(backButton).bottom();
		table.row();

		stage.addActor(table);
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

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

}

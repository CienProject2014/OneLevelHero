package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.controller.ScreenController;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.resource.PrologueScene;
import com.mygdx.util.ScreenEnum;

public class WorldMapScreen implements Screen {

	private OneLevelHero game;
	private Stage stage;
	private SpriteBatch batch;
	private PrologueScene scene;
	private Texture texture;
	private Image background;
	private TextButton goToVillageButton;
	private TextButton goToMovingButton;
	private Table table;

	public WorldMapScreen() {

	}

	@Override
	public void render(float delta) {
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
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		texture = new Texture(Gdx.files.internal("texture/WorldMap.jpg"));
		background = new Image(texture);
		table = new Table(Assets.skin);

		goToVillageButton = new TextButton("마을로", Assets.skin);
		goToMovingButton = new TextButton("무빙스크린으로", Assets.skin);

		goToVillageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.VILLAGE);
			}
		});

		goToMovingButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new MovingScreen(game));
			}
		});

		table.setFillParent(true);
		table.add(goToVillageButton).expand().width(240).height(240).top()
				.left();
		table.add(goToMovingButton).width(240).height(240).top().right();
		Viewport viewport = stage.getViewport();
		background.setSize(viewport.getViewportWidth(),
				viewport.getWorldHeight());
		stage.addActor(background);
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

	public OneLevelHero getGame() {
		return game;
	}

	public void setGame(OneLevelHero game) {
		this.game = game;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public PrologueScene getScene() {
		return scene;
	}

	public void setScene(PrologueScene scene) {
		this.scene = scene;
	}

}

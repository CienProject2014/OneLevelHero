package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.manager.MovingManager;
import com.mygdx.state.Assets;

@Component
@Scope("prototype")
public class MovingScreen implements Screen {
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private MovingManager movingManager;
	private Stage stage;
	private TextButton goButton;
	private TextButton backButton;
	private Label movingLabel;
	private Table table;
	private Texture texture = new Texture(
			Gdx.files.internal("texture/justground.jpg"));
	private Image background;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		movingLabel.setText(movingInfo.getDestinationNode() + "까지"
				+ movingInfo.getLeftRoadLength());

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.app.log("DEBUG", "MovingSceen show");

		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		goButton = new TextButton("Go", Assets.skin);
		backButton = new TextButton("Back", Assets.skin);
		movingLabel = new Label("Point", Assets.skin);

		movingLabel.setColor(0, 0, 0, 1);

		Gdx.input.setInputProcessor(stage);

		background = new Image(texture);
		background.setSize(Assets.windowWidth, Assets.windowHeight);

		goButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				movingManager.goForward();
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
				movingManager.goBackward();
			}
		});
		table.add(movingLabel).top();
		table.row();
		table.add(goButton).expand().top().padTop(20);
		table.row();
		table.add(backButton).bottom().padBottom(20);

		stage.addActor(background);
		stage.addActor(table);

	}

	public static void setController() {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen hide");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen pause");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen resume");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen dispose");
	}

}

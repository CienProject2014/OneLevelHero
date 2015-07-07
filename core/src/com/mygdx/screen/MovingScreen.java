package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
<<<<<<< HEAD
import com.mygdx.currentState.MovingInfo;
import com.mygdx.listener.TouchListener;
=======
>>>>>>> Developer
import com.mygdx.manager.MovingManager;
import com.mygdx.state.StaticAssets;

public class MovingScreen extends BaseScreen {
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
		super.render(delta);

		movingLabel.setText(movingInfo.getDestinationNode() + "까지"
				+ movingInfo.getLeftRoadLength());
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		goButton = new TextButton("Go", assets.skin);
		backButton = new TextButton("Back", assets.skin);
		movingLabel = new Label("Point", assets.skin);
		movingLabel.setColor(0, 0, 0, 1);

		Gdx.input.setInputProcessor(stage);

		background = new Image(texture);
		background.setSize(StaticAssets.windowWidth, StaticAssets.windowHeight);

		goButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				movingManager.goForward();
			}
		}));

		backButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				movingManager.goBackward();
			}
		}));
		table.add(movingLabel).top();
		table.row();
		table.add(goButton).expand().top().padTop(20);
		table.row();
		table.add(backButton).bottom().padBottom(20);

		stage.addActor(background);
		stage.addActor(table);

		musicManager.setMovingMusicAndPlay();
	}

	public static void setController() {
	}

	public MovingManager getMovingManager() {
		return movingManager;
	}

	public void setMovingManager(MovingManager movingManager) {
		this.movingManager = movingManager;
	}

}

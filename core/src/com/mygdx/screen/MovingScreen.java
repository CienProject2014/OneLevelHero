package com.mygdx.screen;

import org.json.simple.JSONObject;

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
import com.mygdx.controller.BattleController;
import com.mygdx.controller.MovingController;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class MovingScreen implements Screen {

	private Stage stage;
	private TextButton goButton;
	private TextButton backButton;

	private Label movingLabel;
	private Label roadName;
	private Table table;

	private int roadlength;
	private int leftlength;
	public static int temp;
	private boolean battled;

	private Texture texture = new Texture(
			Gdx.files.internal("texture/justground.jpg"));

	private Image background;

	private MovingController controller;
	private BattleController battle;

	private String presentVillage;
	private String targetVillage;

	private String currentPosition;
	private String currentDestination;
	private String currentStartingpoint;

	public MovingScreen() {
		Gdx.app.log("DEBUG", "MovingScreen constructor");
		controller = new MovingController();
		battle = new BattleController();
		battled = false;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		movingLabel.setText(Assets.worldHashmap.get(currentDestination)
				.getName() + "까지" + leftlength);

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.app.log("DEBUG", "MovingSceen show");
		currentDestination = CurrentState.getInstance().getVillageInfo()
				.getCurrentDestination();
		currentPosition = CurrentState.getInstance().getVillageInfo()
				.getCurrentPosition();
		currentStartingpoint = CurrentState.getInstance().getVillageInfo()
				.getCurrentStarting();

		JSONObject roadJson = (JSONObject) Assets.jsonObjectMap.get(
				"worldmap_json").get("Road");

		JSONObject roadInfo = (JSONObject) roadJson.get(currentPosition);

		roadlength = Integer.parseInt((String) roadInfo.get("length"));
		leftlength = roadlength;
		if (battled) {
			leftlength = temp;
			battled = false;
		}

		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		goButton = new TextButton("Go", Assets.skin);
		backButton = new TextButton("Back", Assets.skin);
		movingLabel = new Label("Point", Assets.skin);
		roadName = new Label((String) roadInfo.get("name"), Assets.skin);

		movingLabel.setColor(0, 0, 0, 1);
		roadName.setColor(0, 0, 0, 1);

		Gdx.input.setInputProcessor(stage);

		background = new Image(texture);
		background.setSize(Assets.realWidth, Assets.realHeight);

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
				goForward();
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
				goBackward();
			}
		});

		controller.checkStage();

		table.add(roadName).top();
		table.row();
		table.add(movingLabel).top();
		table.row();
		table.add(goButton).expand().top().padTop(20);
		table.row();
		table.add(backButton).bottom().padBottom(20);

		stage.addActor(background);
		stage.addActor(table);

	}

	public void goForward() {
		Gdx.app.log("test", "goForward");
		if (leftlength > 0) {
			leftlength--;
			if (battle.isOccur()) {
				temp = leftlength;
				battled = true;
				battle.start();
			}
		}
		if (leftlength == 0) {

			CurrentState
					.getInstance()
					.getVillageInfo()
					.setCurrentState(
							Assets.worldHashmap.get(currentDestination)
									.getType());

			CurrentState.getInstance().getVillageInfo()
					.setCurrentPosition(currentDestination);

			CurrentState.getInstance().getVillageInfo()
					.setCurrentDestination(null);

			CurrentState.getInstance().getVillageInfo()
					.setCurrentStarting(null);

			String currentState = CurrentState.getInstance().getVillageInfo()
					.getCurrentState();

			if (currentState.equals("village")) {

				new ScreenController(ScreenEnum.VILLAGE);

			} else if (currentState.equals("dungeon")) {

				new ScreenController(ScreenEnum.VILLAGE);

			} else if (currentState.equals("turningpoint")) {

				new ScreenController(ScreenEnum.WORLD_MAP);

			} else {

			}
		}

	}

	public void goBackward() {

		String temp;
		temp = currentDestination;
		currentDestination = currentStartingpoint;
		currentStartingpoint = temp;

		leftlength = roadlength - leftlength;
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

	public String getPresentVillage() {
		return presentVillage;
	}

	public void setPresentVillage(String presentVillage) {
		this.presentVillage = presentVillage;
	}

	public String getTargetVillage() {
		return targetVillage;
	}

	public void setTargetVillage(String targetVillage) {
		this.targetVillage = targetVillage;
	}

}

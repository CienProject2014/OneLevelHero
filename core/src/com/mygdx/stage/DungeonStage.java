package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.DungeonEncounterManager;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.TimeManager;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 * 
 */

public class DungeonStage extends BaseOverlapStage {
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private DungeonEncounterManager dungeonEncounterManager;
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private TimeManager timeManager;

	private CompositeItem btnTurn;
	private CompositeItem[] doorButton = new CompositeItem[3];

	private Texture map;
	private TextureRegion[][] maptile;
	private Table minimaptable;
	private Texture blacktile;
	private Image minimapBackgroundImage;
	private Image directionArrow;

	protected Stack tableStack;

	public Stage makeStage() {
		setMapInfo(dungeonManager);
		makeScene(dungeonManager);
		makeMinimapTable();
		makeDoorButton(dungeonManager);
		setButtonListener(dungeonManager);
		return this;
	}
	private void makeDoorButton(DungeonManager dungeonManager) {
		for (int i = 0, n = dungeonManager.getCurrentDoorSize(); i < n; i++) {
			Gdx.app.log("문 개수", String.valueOf(n));
			doorButton[i].setTouchable(i < n ? Touchable.enabled : Touchable.disabled);
			doorButton[i].setVisible(doorButton[i].getTouchable() == Touchable.enabled);
		}

	}

	private void makeMinimapTable() {
		minimapBackgroundImage = new Image(new Texture(
				Gdx.files.internal("texture/dungeon_minimap/minimap_background.png")));
		minimapBackgroundImage.setPosition(1400, 605);
		addActor(minimapBackgroundImage);

		tableStack = new Stack();
		tableStack.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		tableStack.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		addActor(tableStack);

		directionArrow = new Image(new Texture(Gdx.files.internal("texture/dungeon_minimap/minimap_arrow.png")));
		directionArrow.setPosition(1395 + 255, 585 + 155);
		directionArrow.setOrigin(directionArrow.getWidth() / 2, directionArrow.getHeight() / 2);
		addActor(directionArrow);

		String minimapPath = "texture/dungeon_minimap/"
				+ dungeonManager.getDungeonInfo().getCurrentDungeon().getSubNodePath() + "_minimap.png";

		blacktile = new Texture(Gdx.files.internal("texture/dungeon_minimap/black_tile.png"));

		map = new Texture(Gdx.files.internal(minimapPath));

		if (map == null) {
			map = new Texture(Gdx.files.internal("texture/dungeon_minimap/devil_castle_minimap.png"));
		}

		maptile = TextureRegion.split(map, map.getWidth()
				/ dungeonManager.getDungeonInfo().getCurrentFloor().getMapWidth(), map.getHeight()
				/ dungeonManager.getDungeonInfo().getCurrentFloor().getMapHeight());

		float tileHeight = maptile[0][0].getRegionHeight();
		float tileWidth = maptile[0][0].getRegionWidth();

		minimaptable = new Table();
		minimaptable.top();
		minimaptable.right();
		minimaptable.addAction(Actions.moveTo(-10, -170));

		tableStack.add(minimaptable);

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		minimaptable.act(delta);
	}

	private void setMapInfo(DungeonManager dungeonManager) {
		if (positionManager.getCurrentSubNodePath() != null) {
			dungeonManager.setDungeonInfo(positionManager.getCurrentSubNodePath());
		} else {
			dungeonManager.setDungeonInfo("devil_castle");
		}
	}

	public void makeScene(DungeonManager dungeonManager) {
		String sceneName = dungeonManager.getSceneName();
		assetsManager.initScene(sceneName);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(sceneName);
		cameraManager.stretchToDevice(this);
		addActor(sceneLoader.getRoot());
	}

	private void setButtonListener(DungeonManager dungeonManager) {
		int doorNum = 1;
		btnTurn = sceneLoader.getRoot().getCompositeById("back");
		if (doorNum == 1) {
			doorButton[0] = sceneLoader.getRoot().getCompositeById("go_mid");

			doorButton[0].addListener(new TouchRoadListener(0));
		} else if (doorNum == 2) {
			doorButton[0] = sceneLoader.getRoot().getCompositeById("go_right");
			doorButton[1] = sceneLoader.getRoot().getCompositeById("go_left");

			doorButton[0].addListener(new TouchRoadListener(0));
			doorButton[1].addListener(new TouchRoadListener(1));

		} else if (doorNum == 3) {

			doorButton[0] = sceneLoader.getRoot().getCompositeById("go_left");
			doorButton[1] = sceneLoader.getRoot().getCompositeById("go_mid");
			doorButton[2] = sceneLoader.getRoot().getCompositeById("go_right");

			doorButton[0].addListener(new TouchRoadListener(0));
			doorButton[1].addListener(new TouchRoadListener(1));
			doorButton[2].addListener(new TouchRoadListener(2));
		}

		btnTurn.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

			}
		});

	}

	private class TouchRoadListener extends SimpleTouchListener {
		private int index;

		public TouchRoadListener(int index) {
			this.index = index;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

		}
	}
}

package com.mygdx.stage;

import java.util.ArrayList;

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
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.DungeonEncounterManager;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.location.DungeonConnection;
import com.mygdx.model.location.DungeonNode;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 * 
 */
public class DungeonStage extends BaseOverlapStage {
	// FIXME UI
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
	private CompositeItem[] btnRoad = new CompositeItem[3];
	private ArrayList<DungeonConnection> selectableForward, selectableBackward;

	private Texture map;
	private TextureRegion[][] maptile;
	private Table minimaptable;
	private Texture blacktile;
	private Image minimapBackground;
	private Image directionArrow;

	protected Stack tableStack;

	@Override
	public void act() {
		super.act();
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		minimaptable.act(delta);
	}

	public void initMinimap() {

		String minimapPath = "texture/dungeon_minimap/" + dungeonManager.getMapInfo().getSubNodePath() + "_minimap.png";

		blacktile = new Texture(Gdx.files.internal("texture/dungeon_minimap/black_tile.png"));

		map = new Texture(Gdx.files.internal(minimapPath));
		if (map == null) {
			map = new Texture(Gdx.files.internal("texture/dungeon_minimap/devil_castle_minimap.png"));
		}

		maptile = TextureRegion.split(map, map.getWidth() / dungeonManager.getMapInfo().getMapWidth(), map.getHeight()
				/ dungeonManager.getMapInfo().getMapHeight());

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());
		dungeonManager.turnIsOn(currentNode.getNodePosY(), currentNode.getNodePosX());

		// setisOn();

		float tileHeight = maptile[0][0].getRegionHeight();
		float tileWidth = maptile[0][0].getRegionWidth();

		// blacktile.setSize(tileHeight, tileWidth);
		// blacktile.set

		minimaptable = new Table();

		// minimaptable.setSize(500, 300);

		// minimaptable.setPosition(1000, 800);

		minimaptable.top();
		minimaptable.right();

		minimaptable.addAction(Actions.moveTo(-10, -170));
		// 세로 가로

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) { //

				int indexX = currentNode.getNodePosX() - 2 + j;
				int indexY = currentNode.getNodePosY() - 1 + i;

				if (indexX < 0 || indexY < 0) {
					minimaptable.add(new Image(blacktile));
				} else {
					if (dungeonManager.checkIsOn(indexY, indexX))
						minimaptable.add(new Image(maptile[indexY][indexX]));
					else
						minimaptable.add(new Image(blacktile));
				}
			}
			minimaptable.row();
		}
		// minimaptable.setBackground(new TextureRegionDrawable(new
		// TextureRegion(map)));

		tableStack.add(minimaptable);
	}
	public void refreshMinimap() {

		minimaptable.clear();

		// minimaptable.setBackground(new
		// TextureRegionDrawable(minimapBackground));

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());
		dungeonManager.turnIsOn(currentNode.getNodePosY(), currentNode.getNodePosX());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				// outofbound
				int indexX = currentNode.getNodePosX() - 2 + j;
				int indexY = currentNode.getNodePosY() - 1 + i;

				if (indexX < 0 || indexY < 0) {
					minimaptable.add(new Image(blacktile));
				} else {
					if (dungeonManager.checkIsOn(indexY, indexX))
						minimaptable.add(new Image(maptile[indexY][indexX]));
					else
						minimaptable.add(new Image(blacktile));
				}
			}
			minimaptable.row();
		}
		minimaptable.addAction(Actions.moveTo(-10, -170));
	}

	public Stage makeStage() {
		setMapInfo(dungeonManager);
		dungeonManager.setInDungeon(true);

		makeScene(dungeonManager.getMapInfo().getSceneName(3));

		minimapBackground = new Image(new Texture(Gdx.files.internal("texture/dungeon_minimap/minimap_background.png")));
		minimapBackground.setPosition(1400, 605);
		this.addActor(minimapBackground);

		tableStack = new Stack();
		tableStack.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		tableStack.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		this.addActor(tableStack);

		directionArrow = new Image(new Texture(Gdx.files.internal("texture/dungeon_minimap/minimap_arrow.png")));
		directionArrow.setPosition(1395 + 255, 585 + 155);
		directionArrow.setOrigin(directionArrow.getWidth() / 2, directionArrow.getHeight() / 2);
		this.addActor(directionArrow);

		update();

		initMinimap();

		// this.addActor(minimaptable);

		return this;
	}

	private void setMapInfo(DungeonManager dungeonManager) {
		if (positionManager.getCurrentSubNodeName() != null) {
			dungeonManager.setMapInfo(positionManager.getCurrentSubNodeName()); // FIXME
		} else
			dungeonManager.setMapInfo("devil_castle");

		if (!dungeonManager.isThisNewDungeon(positionManager.getCurrentSubNodeName()))
			dungeonManager.setIsOn();

		selectableBackward = new ArrayList<>();
		selectableForward = new ArrayList<>();
	}

	public void makeScene(String sceneName) {

		assetsManager.initScene(sceneName);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(sceneName);

		cameraManager.stretchToDevice(this);
		addActor(sceneLoader.getRoot());
	}

	public void resetArrow() {
		directionArrow.remove();

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());

		int rotationDegree = 0;

		if (currentNode.getDirectionType().equals("left")) {
			rotationDegree = 0;
		} else if (currentNode.getDirectionType().equals("right")) {
			rotationDegree = 180;
		} else if (currentNode.getDirectionType().equals("crossup")) {
			rotationDegree = -45;
		} else if (currentNode.getDirectionType().equals("crossdown")) {
			rotationDegree = 45;
		} else if (currentNode.getDirectionType().equals("up")) {
			rotationDegree = -90;
		} else if (currentNode.getDirectionType().equals("down")) {
			rotationDegree = 90;
		}

		if (dungeonManager.getCurrentHeading())
			rotationDegree += 180;

		directionArrow.setRotation(rotationDegree);

		this.addActor(directionArrow);
	}

	public void resetScene(String sceneName, int doorNum) {
		tableStack.remove();
		minimapBackground.remove();
		sceneLoader.getRoot().remove();
		makeScene(sceneName);
		setButton(doorNum);
		this.addActor(minimapBackground);
		this.addActor(tableStack);
		resetArrow();
	}

	public void setEntranceScene() {
		tableStack.remove();
		minimapBackground.remove();
		sceneLoader.getRoot().remove();
		makeScene(dungeonManager.getMapInfo().getSceneName(1));
		btnTurn = sceneLoader.getRoot().getCompositeById("back");
		btnRoad[0] = sceneLoader.getRoot().getCompositeById("go_mid");

		btnRoad[0].addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				dungeonManager.changeCurrentHeading();
				dungeonManager.setRecentDungeon(positionManager.getCurrentSubNodeName());
				screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);
			}
		});

		btnTurn.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				actionTurn();
			}
		});

		this.addActor(minimapBackground);
		this.addActor(tableStack);
		resetArrow();
	}

	private void setButton(int doorNum) {
		// FIXME UI

		btnTurn = sceneLoader.getRoot().getCompositeById("back");
		if (doorNum == 1) {
			btnRoad[0] = sceneLoader.getRoot().getCompositeById("go_mid");

			btnRoad[0].addListener(new TouchRoadListener(0));
		} else if (doorNum == 2) {
			if (!dungeonManager.getCurrentHeading()) {
				btnRoad[0] = sceneLoader.getRoot().getCompositeById("go_left");
				btnRoad[1] = sceneLoader.getRoot().getCompositeById("go_right");
			} else {
				btnRoad[0] = sceneLoader.getRoot().getCompositeById("go_right");
				btnRoad[1] = sceneLoader.getRoot().getCompositeById("go_left");
			}

			btnRoad[0].addListener(new TouchRoadListener(0));
			btnRoad[1].addListener(new TouchRoadListener(1));

		} else if (doorNum == 3) {
			if (!dungeonManager.getCurrentHeading()) {
				btnRoad[0] = sceneLoader.getRoot().getCompositeById("go_left");
				btnRoad[1] = sceneLoader.getRoot().getCompositeById("go_mid");
				btnRoad[2] = sceneLoader.getRoot().getCompositeById("go_right");
			} else {
				btnRoad[0] = sceneLoader.getRoot().getCompositeById("go_right");
				btnRoad[1] = sceneLoader.getRoot().getCompositeById("go_mid");
				btnRoad[2] = sceneLoader.getRoot().getCompositeById("go_left");
			}

			btnRoad[0].addListener(new TouchRoadListener(0));
			btnRoad[1].addListener(new TouchRoadListener(1));
			btnRoad[2].addListener(new TouchRoadListener(2));
		}

		btnTurn.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				actionTurn();
			}
		});

	}

	private void update() {

		selectableForward.clear();
		selectableBackward.clear();

		// 대체 노드의 라벨은 무엇을 의미하는 것인
		// sceneLoader.getCompositeElementById(currentNode.getLabel()).setVisible(true);
		for (DungeonConnection e : dungeonManager.getMapInfo().connections) {
			if (e.isFrom(dungeonManager.getCurrentPos())) {
				selectableForward.add(e);
			} else if (e.isTo(dungeonManager.getCurrentPos())) {
				selectableBackward.add(e);
			}
		}

		if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 0) {
			resetScene(dungeonManager.getMapInfo().getSceneName(0), 0);
		} else if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 1) {
			resetScene(dungeonManager.getMapInfo().getSceneName(1), 1);
		} else if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 2) {
			resetScene(dungeonManager.getMapInfo().getSceneName(2), 2);
		} else if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 3) {
			resetScene(dungeonManager.getMapInfo().getSceneName(3), 3);
		}

		// FIXME UI
		for (int i = 0, n = (dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size(); i < n; i++) {
			Gdx.app.log("문 개수", String.valueOf(n));
			btnRoad[i].setTouchable(i < n ? Touchable.enabled : Touchable.disabled);
			btnRoad[i].setVisible(btnRoad[i].getTouchable() == Touchable.enabled);
		}
	}

	private void actionTurn() {
		timeManager.plusMinute(10);

		dungeonManager.changeCurrentHeading();

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());

		if (currentNode.chkFlag(DungeonNode.FLG_ENTRANCE) && dungeonManager.getCurrentHeading() == true) {
			setEntranceScene();
		}

		else {
			update();
		}
	}

	private void actionMove(int index) {
		timeManager.plusMinute(10);
		dungeonManager.setCurrentPos(dungeonManager.getMapInfo().nodes.indexOf((dungeonManager.getCurrentHeading()
				? selectableBackward
				: selectableForward).get(index)));

		if (!dungeonManager.getCurrentHeading())
			dungeonManager.setCurrentPos(selectableForward.get(index).getTo());
		else if (dungeonManager.getCurrentHeading())
			dungeonManager.setCurrentPos(selectableBackward.get(index).getFrom());

		Gdx.app.log("currentPos", String.valueOf(dungeonManager.getCurrentPos()));

		update();

		refreshMinimap();

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());

		if (currentNode.chkFlag(DungeonNode.FLG_ENTRANCE)) {
			setEntranceScene();
		} else if (currentNode.chkFlag(DungeonNode.FLG_ROAD)) {
			// screenFactory.show(ScreenEnum.ENCOUNTER);
			dungeonManager.setRecentDungeon(positionManager.getCurrentSubNodeName());
			dungeonEncounterManager.act();
		} else if (currentNode.chkFlag(DungeonNode.FLG_ENCOUNT)) {
			dungeonEncounterManager.eliteAct(currentNode.getEliteMonster());

		}
	}

	private class TouchRoadListener extends SimpleTouchListener {
		private int idx;

		public TouchRoadListener(int idx) {
			this.idx = idx;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			actionMove(idx);
		}
	}
}

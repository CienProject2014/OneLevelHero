package com.mygdx.stage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.assets.NodeAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.DungeonEncounterManager;
import com.mygdx.manager.DungeonManager;
import com.mygdx.model.DungeonConnection;
import com.mygdx.model.DungeonNode;
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

	private CompositeItem btnTurn;
	private CompositeItem[] btnRoad = new CompositeItem[3];

	private DungeonMinimapStage dungeonMinimap;

	// private Dungeon mapInfo;

	// private int currentPos;
	// private boolean currentHeading;
	private ArrayList<DungeonConnection> selectableForward, selectableBackward;

	public void makeScene(String sceneName) {
		assetsManager.initScene(sceneName);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(sceneName);

		cameraManager.stretchToDevice(this);
		addActor(sceneLoader.getRoot());
	}

	public Stage makeStage() {

		setMap();
		makeScene(dungeonManager.getMapInfo().getSceneName());
		dungeonManager.setInDungeon(true);
		// FIXME UI
		// 우선은 blackwood_forest_dungeon_scene으로 통일하자

		// mapInfo = worldNodeAssets.getDungeon("blackwood_forest_dungeon");
		setButton(3);
		update();

		return this;
	}
	private void setMap() {
		/*
		 * mapInfo = new Dungeon();
		 * 
		 * DungeonNode aNode = new DungeonNode();
		 * aNode.setFlag(DungeonNode.FLG_ENCOUNT); aNode.setLabel("arrow_left");
		 * 
		 * mapInfo.nodes.add(aNode);
		 */
		// mapInfo = nodeAssets.getDungeonByName("devil_castle_dungeon");

		dungeonManager.setMapInfo("devil_castle_dungeon");

		selectableBackward = new ArrayList<>();
		selectableForward = new ArrayList<>();
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

		btnTurn.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				actionTurn();
			}
		});

	}

	private void update() {

		selectableForward.clear();
		selectableBackward.clear();

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());
		// 대체 노드의 라벨은 무엇을 의미하는 것인
		// sceneLoader.getCompositeElementById(currentNode.getLabel()).setVisible(true);
		for (DungeonConnection e : dungeonManager.getMapInfo().connections) {
			if (e.isFrom(dungeonManager.getCurrentPos())) {
				selectableForward.add(e);
			} else if (e.isTo(dungeonManager.getCurrentPos())) {
				selectableBackward.add(e);
			}
		}
		// FIXME UI
		for (int i = 0, n = (dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size(); i < n; i++) {
			Gdx.app.log("문 개수", String.valueOf(n));
			btnRoad[i].setTouchable(i < n ? Touchable.enabled : Touchable.disabled);
			btnRoad[i].setVisible(btnRoad[i].getTouchable() == Touchable.enabled);
		}

		if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 0) {
			sceneLoader.getRoot().remove();
			makeScene("dungeon_0door_scene");
			setButton(0);
		} else if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 1) {
			sceneLoader.getRoot().remove();
			makeScene("dungeon_1door_scene");
			setButton(1);
		} else if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 2) {
			sceneLoader.getRoot().remove();
			makeScene("dungeon_2door_scene");
			setButton(2);
		} else if ((dungeonManager.getCurrentHeading() ? selectableBackward : selectableForward).size() == 3) {
			sceneLoader.getRoot().remove();
			makeScene("dungeon_3door_scene");
			setButton(3);
		}
	}

	private void actionTurn() {
		dungeonManager.changeCurrentHeading();

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());

		if (currentNode.chkFlag(DungeonNode.FLG_ENTRANCE)) {
			dungeonManager.changeCurrentHeading();
			screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);
		}

		update();
	}

	private void actionMove(int index) {
		dungeonManager.setCurrentPos(dungeonManager.getMapInfo().nodes.indexOf((dungeonManager.getCurrentHeading()
				? selectableBackward
				: selectableForward).get(index)));

		if (!dungeonManager.getCurrentHeading())
			dungeonManager.setCurrentPos(selectableForward.get(index).getTo());
		else if (dungeonManager.getCurrentHeading())
			dungeonManager.setCurrentPos(selectableBackward.get(index).getFrom());

		Gdx.app.log("currentPos", String.valueOf(dungeonManager.getCurrentPos()));

		update();

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());

		if (currentNode.chkFlag(DungeonNode.FLG_ENTRANCE)) {
			dungeonManager.changeCurrentHeading();
			screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);
		} else if (currentNode.chkFlag(DungeonNode.FLG_ENCOUNT)) {
			// screenFactory.show(ScreenEnum.ENCOUNTER);
			dungeonEncounterManager.act();
		}
	}

	private class TouchRoadListener extends InputListener {
		private int idx;

		public TouchRoadListener(int idx) {
			this.idx = idx;
		}

		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			actionMove(idx);
		}
	}
}

package com.mygdx.stage;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.Dungeon;
import com.mygdx.model.DungeonConnection;
import com.mygdx.model.DungeonNode;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonStage extends BaseOverlapStage {
	// FIXME UI
	private CompositeItem btnTurn;
	private CompositeItem[] btnRoad = new CompositeItem[3];
	
	private Dungeon mapInfo;

	private int currentPos;
	private boolean currentHeading;
	private ArrayList<DungeonConnection> selectableForward, selectableBackward;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		
		// FIXME UI
		// 우선은 blackwood_forest_dungeon_scene으로 통일하자
		setMap();
		//mapInfo = worldNodeAssets.getDungeon("blackwood_forest_dungeon");
		makeScene("dungeon_3door_scene");
		setButton();

		return this;
	}
	
	private void setMap() {
		mapInfo = new Dungeon();
		
		DungeonNode aNode = new DungeonNode();
		aNode.setFlag(DungeonNode.FLG_ENCOUNT);
		aNode.setLabel("arrow_left");
		
		mapInfo.nodes.add(aNode);
		
		selectableBackward = new ArrayList<>();
		selectableForward = new ArrayList<>();
	}

	private void makeScene(String sceneName) {
		// FIXME UI
		sceneLoader.loadScene(sceneName);
		cameraManager.stretchToDevice(this);
		addActor(sceneLoader.getRoot());
	}

	private void setButton() {
		// FIXME UI
		btnTurn = sceneLoader.getRoot().getCompositeById("arrow_down");
		btnRoad[0] = sceneLoader.getRoot().getCompositeById("arrow_left");
		btnRoad[1] = sceneLoader.getRoot().getCompositeById("arrow_up");
		btnRoad[2] = sceneLoader.getRoot().getCompositeById("arrow_right");

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

		for (int i = 0; i < btnRoad.length; i++)
			btnRoad[i].addListener(new TouchRoadListener(i));

		update();
	}

	private void update() {
		selectableForward.clear();
		selectableBackward.clear();
		DungeonNode currentNode = mapInfo.nodes.get(currentPos);
		//대체 노드의 라벨은 무엇을 의미하는 것인
		sceneLoader.getCompositeElementById(currentNode.getLabel()).setVisible(true);
		for (DungeonConnection e : mapInfo.connections)
			if (e.isFrom(currentNode)) {
				selectableForward.add(e);
				sceneLoader.getCompositeElementById(e.getLabel()).setVisible(true);
			} else if (e.isTo(currentNode)) {
				selectableBackward.add(e);
				sceneLoader.getCompositeElementById(e.getLabel()).setVisible(true);
			}

		// FIXME UI
		btnTurn.setTouchable(!currentHeading ? Touchable.enabled : Touchable.disabled);
		btnTurn.setVisible(btnTurn.getTouchable() == Touchable.enabled);
		for (int i = 0, n = (currentHeading ? selectableBackward : selectableForward).size(); i < btnRoad.length; i++) {
			btnRoad[i].setTouchable(i < n ? Touchable.enabled : Touchable.disabled);
			btnRoad[i].setVisible(btnRoad[1].getTouchable() == Touchable.enabled);
		}
	}

	private void actionTurn() {
		currentHeading = !currentHeading;

		update();
	}

	private void actionMove(int index) {
		currentPos = mapInfo.nodes.indexOf((currentHeading ? selectableBackward : selectableForward).get(index));

		update();

		DungeonNode currentNode = mapInfo.nodes.get(currentPos);
		if (currentNode.chkFlag(DungeonNode.FLG_ENTRANCE))
			screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);
		else if (currentNode.chkFlag(DungeonNode.FLG_ENCOUNT))
			screenFactory.show(ScreenEnum.ENCOUNTER);
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

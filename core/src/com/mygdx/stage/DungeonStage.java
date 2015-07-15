package com.mygdx.stage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.dungeonMap.Connection;
import com.mygdx.dungeonMap.Info;
import com.mygdx.dungeonMap.Node;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonStage extends BaseOverlapStage {
	@Autowired
	private PositionInfo positionInfo; // 나중에 쓸거임 지우지 마셈
	private CompositeItem arrowTurn, arrowLeft, arrowCenter, arrowRight;

	private Info mapInfo;

	private int currentPos;
	private boolean currentHeading;

	private ArrayList<Connection> selectableForward, selectableBackward;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);

		// FIXME UI
		// 우선은 blackwood_forest_dungeon_scene으로 통일하자
		makeScene("blackwood_forest_dungeon_scene");
		setButton();

		return this;
	}

	private void makeScene(String sceneName) {
		// FIXME UI
		sceneLoader.loadScene(sceneName);
		cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
		addActor(sceneLoader.getRoot());
	}

	private void setButton() {
		// FIXME UI
		arrowTurn = sceneLoader.getRoot().getCompositeById("arrow_down");
		arrowLeft = sceneLoader.getRoot().getCompositeById("arrow_left");
		arrowCenter = sceneLoader.getRoot().getCompositeById("arrow_up");
		arrowRight = sceneLoader.getRoot().getCompositeById("arrow_right");

		arrowTurn.setTouchable(Touchable.enabled);
		arrowTurn.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				actionTurn();
			}
		});

		arrowLeft.setTouchable(Touchable.enabled);
		arrowLeft.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				actionMove(0);
			}
		});

		arrowCenter.setTouchable(Touchable.enabled);
		arrowCenter.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				actionMove(1);
			}
		});

		arrowRight.setTouchable(Touchable.enabled);
		arrowRight.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				actionMove(2);
			}
		});
	}

	private void update() {
		selectableForward.clear();
		selectableBackward.clear();

		Node currentNode = mapInfo.nodes.get(currentPos);
		for (Connection e : mapInfo.connections)
			if (e.isFrom(currentNode))
				selectableForward.add(e);
			else if (e.isTo(currentNode))
				selectableBackward.add(e);

		switch ((currentHeading ? selectableBackward : selectableForward)
				.size()) {
		case 0:
			arrowLeft.setTouchable(Touchable.disabled);
		case 1:
			arrowLeft.setTouchable(Touchable.enabled);
			arrowCenter.setTouchable(Touchable.disabled);
		case 2:
			arrowCenter.setTouchable(Touchable.enabled);
			arrowRight.setTouchable(Touchable.disabled);
		case 3:
			arrowRight.setTouchable(Touchable.enabled);
		}
		arrowTurn.setVisible(arrowTurn.getTouchable() == Touchable.enabled);
		arrowCenter.setVisible(arrowCenter.getTouchable() == Touchable.enabled);
		arrowLeft.setVisible(arrowLeft.getTouchable() == Touchable.enabled);
		arrowRight.setVisible(arrowRight.getTouchable() == Touchable.enabled);
	}

	private void actionTurn() {
		currentHeading = !currentHeading;
	}

	private void actionMove(int index) {
		currentPos = mapInfo.nodes.indexOf((currentHeading ? selectableBackward
				: selectableForward).get(index));

		update();

		Node currentNode = mapInfo.nodes.get(currentPos);
		if (currentNode.chkFlg(Node.FLG_ENTRANCE))
			screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);

	}

	@Override
	public void draw() {
		/*
		 * TODO
		 * 
		 * 미니맵을 출력 범위 지정 후 클리어. Connection에서 from이 null인 것을 추려내 최상위 Root를 구해
		 * 트리구성. 트리의 깊이로 Width를 각 깊이별 노드의 갯수로 각각의 Height를 나누어서 Graph를 구성해서 Node의
		 * 속성에 따른 출력. currentHeading으로 화살표 표시.
		 */

		// TODO Clear

		// Draw connections
		for (int x = 1; x < mapInfo.graph.size(); x++) {
			ArrayList<Node> layer = mapInfo.graph.get(x), privLayer = mapInfo.graph
					.get(x - 1);

			for (int y = 0; y < layer.size(); y++) {
				for (Connection e : mapInfo.connections) {
					if (e.isTo(layer.get(y))) {
						for (int privY = 0; privY < privLayer.size(); privY++) {
							if (e.getFrom() == privLayer.get(privY)) {
								// TODO Draw Line
							}
						}
					}
				}
			}
		}

		// Draw nodes
		for (int x = 0; x < mapInfo.graph.size(); x++) {
			ArrayList<Node> layer = mapInfo.graph.get(x);

			for (int y = 0; y < layer.size(); y++) {
				// TODO Draw Node
			}
		}

		// TODO Draw arrow(heading)

		super.draw();
	}
}

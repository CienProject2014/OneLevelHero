package com.mygdx.stage;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.assets.StaticAssets;
import com.mygdx.dungeon.MapInfo;
import com.mygdx.dungeon.MapNode;
import com.mygdx.dungeon.MapNodeConnection;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonStage extends BaseOverlapStage {
	// FIXME UI
	private CompositeItem arrowTurn, arrowLeft, arrowCenter, arrowRight;

	private MapInfo mapInfo;

	private int currentPos;
	private boolean currentHeading;

	private ArrayList<MapNodeConnection> selectableForward, selectableBackward;

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

		MapNode currentNode = mapInfo.nodes.get(currentPos);
		currentNode.setAlpha(1);
		for (MapNodeConnection e : mapInfo.connections)
			if (e.isFrom(currentNode))
				selectableForward.add(e);
			else if (e.isTo(currentNode))
				selectableBackward.add(e);

		// FIXME UI
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

		MapNode currentNode = mapInfo.nodes.get(currentPos);
		if (currentNode.chkFlg(MapNode.FLG_ENTRANCE))
			screenFactory.show(ScreenEnum.DUNGEON_ENTRANCE);

	}

	@Override
	public void draw() {
		// FIXME
		getBatch().begin();
		for (MapNode e : mapInfo.nodes)
			getBatch().draw(e.getMinimapTexture(), mapInfo.minimapPosX,
					mapInfo.minimapPosY);
		getBatch().end();
	}
}

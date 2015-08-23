package com.mygdx.stage;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.model.surroundings.DungeonEntrance;
import com.mygdx.model.surroundings.Fork;
import com.mygdx.model.surroundings.Village;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;

public class WorldMapStage extends BaseOverlapStage {
	public static final String SCENE_NAME = "worldmap_scene";
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private BattleManager battleManager;
	private String nodePath;
	private CompositeItem currentPosition;
	private ImageItem currentNode;
	private final int SET_POSITION = 15;

	public Stage makeStage() {
		assetsManager.initScene(SCENE_NAME);
		positionManager.setInWorldMap(true);
		initSceneLoader(assetsManager.rm);
		/*
		 * MainScene을 불러오자. SceneLoader는 CompositeItem을 가지고 있다. SceneVO가 반환되는데,
		 * 이것은 CompositeVO를 가지고 있다. CompositeVO는 그 Scene이 가지고 있는 Label, Button등을
		 * 다 가지고 있다.
		 */
		sceneLoader.loadScene(SCENE_NAME);
		/*
		 * getRoot()할시, CompositeItem이 반환된다. CompositeItem은 Composite들의 집합이다.
		 * getCompositeById로 하나하나 가져올수 있다. 현재 위치 버튼을 가져온다. getX로 Image의 위치를 가져올
		 * 수 있다.
		 */
		currentPosition = sceneLoader.getRoot().getCompositeById("cross");
		currentNode = sceneLoader.getRoot().getImageById(positionManager.getCurrentNodeName());
		// FIXME 예외적 상황
		if (positionManager.getCurrentNodeName().equals("elven_forest_east")
				|| positionManager.getCurrentNodeName().equals("elven_forest_west")) {
			currentNode = sceneLoader.getRoot().getImageById("elven_forest");
		} else if (positionManager.getCurrentNodeName().equals("crystallized_valley_south")
				|| positionManager.getCurrentNodeName().equals("crystallized_valley_north")) {
			currentNode = sceneLoader.getRoot().getImageById("crystallized_valley");
		}

		// 카메라 위치를 현재노드로 잡기 위하여 가져옴
		currentPosition.setX(currentNode.getX() - SET_POSITION + 16);
		currentPosition.setY(currentNode.getY() - SET_POSITION + 16);

		addActor(sceneLoader.getRoot());
		setVillageNodeButton(positionManager, nodeAssets);
		setForkNodeButton(positionManager, nodeAssets);
		setDungeonEntranceButton(positionManager, nodeAssets);
		setCamera();
		return this;
	}

	private void setDungeonEntranceButton(final PositionManager positionManager, NodeAssets nodeAssets) {
		Map<String, DungeonEntrance> dungeonEntranceMap = nodeAssets.getDungeonEntranceMap();
		Iterator<Entry<String, DungeonEntrance>> dungeonEntranceMapIterator = dungeonEntranceMap.entrySet().iterator();
		while (dungeonEntranceMapIterator.hasNext()) {
			final String nodePath = dungeonEntranceMapIterator.next().getKey();
			if (nodePath.equals("elven_forest_east") || nodePath.equals("elven_forest_west")) {

				ImageItem nodeButton = sceneLoader.getRoot().getImageById("elven_forest");
				nodeButton.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						positionManager.setInWorldMap(false);
						Random random = new Random();
						boolean randomBoolean = random.nextBoolean();
						if (randomBoolean) {
							movingManager.goToNode("elven_forest_east");
						} else {
							movingManager.goToNode("elven_forest_west");
						}
					}
				});
			} else if ((nodePath.equals("crystallized_valley_south") || nodePath.equals("crystallized_valley_north"))) {
				ImageItem nodeButton = sceneLoader.getRoot().getImageById("crystallized_valley");
				nodeButton.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						positionManager.setInWorldMap(false);
						Random random = new Random();
						boolean randomBoolean = random.nextBoolean();
						if (randomBoolean) {
							movingManager.goToNode("crystallized_valley_south");
						} else {
							movingManager.goToNode("crystallized_valley_north");
						}
					}
				});
			} else {
				ImageItem nodeButton = sceneLoader.getRoot().getImageById(nodePath);
				nodeButton.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						positionManager.setInWorldMap(false);
						movingManager.goToNode(nodePath);
					}
				});
			}
		}
	}
	public ImageItem getCurrentNode() {
		return currentNode;
	}

	public void setVillageNodeButton(final PositionManager positionManager, NodeAssets nodeAssets) {

		Map<String, Village> villageMap = nodeAssets.getVillageMap();
		Iterator<Entry<String, Village>> villageMapIterator = villageMap.entrySet().iterator();
		while (villageMapIterator.hasNext()) {
			final String nodeName = villageMapIterator.next().getKey();
			ImageItem nodeButton = sceneLoader.getRoot().getImageById(nodeName);
			nodeButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					positionManager.setInWorldMap(false);
					movingManager.goToNode(nodeName);
				}
			});
		}
	}

	public void setForkNodeButton(final PositionManager positionManager, NodeAssets nodeAssets) {
		Map<String, Fork> forkMap = nodeAssets.getForkMap();
		Iterator<Entry<String, Fork>> forkMapIterator = forkMap.entrySet().iterator();
		while (forkMapIterator.hasNext()) {
			final String nodeName = forkMapIterator.next().getKey();
			ImageItem nodeButton = sceneLoader.getRoot().getImageById(nodeName);
			nodeButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					positionManager.setInWorldMap(false);
					movingManager.goToNode(nodeName);
				}
			});
		}
	}

	public void setCurrent(ImageItem current) {
		this.currentNode = current;
	}

	public void setCamera() {
		int xLeftLimit = (int) (StaticAssets.windowWidth / 2);
		int xRightLimit = (int) (3000 - (StaticAssets.windowWidth / 2));
		int yBottomLimit = (int) (StaticAssets.windowHeight / 2);
		int yTopLimit = (int) (1688 - (StaticAssets.windowHeight / 2));

		float xValue = this.getCurrentNode().getX() - StaticAssets.windowWidth / 2, yValue = this.getCurrentNode()
				.getY() - StaticAssets.windowHeight / 2;
		// x값이 오른쪽으로 벗어날 경우
		if (this.getCurrentNode().getX() > xRightLimit)
			xValue = 3000 - StaticAssets.windowWidth;
		// x값이 왼쪽으로 벗어날 경우
		if (this.getCurrentNode().getX() < xLeftLimit)
			xValue = 0;
		// y값이 위로 벗어날 경우
		if (this.getCurrentNode().getY() > yTopLimit)
			yValue = 1688 - StaticAssets.windowHeight;
		// y값이 아래로 벗어날 경우
		if (this.getCurrentNode().getY() < yBottomLimit)
			yValue = 0;

		getCamera().translate(xValue, yValue, 0);
	}

	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
}

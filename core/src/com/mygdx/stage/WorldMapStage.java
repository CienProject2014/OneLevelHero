package com.mygdx.stage;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.model.surroundings.Village;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;

public class WorldMapStage extends BaseOverlapStage {
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private AssetsManager assetsManager;
	private CompositeItem currentPosition;
	private ImageItem currentNode;
	private final int SET_POSITION = 15;

	public Stage makeStage() {
		if (!assetsManager.rm.searchSceneNames("worldmap_scene")) {
			assetsManager.rm.initScene("worldmap_scene");
		}
		initSceneLoader(assetsManager.rm);
		positionManager.setInWorldMap(true);
		/*
		 * MainScene을 불러오자. SceneLoader는 CompositeItem을 가지고 있다. SceneVO가 반환되는데,
		 * 이것은 CompositeVO를 가지고 있다. CompositeVO는 그 Scene이 가지고 있는 Label, Button등을
		 * 다 가지고 있다.
		 */
		sceneLoader.loadScene("worldmap_scene");
		/*
		 * getRoot()할시, CompositeItem이 반환된다. CompositeItem은 Composite들의 집합이다.
		 * getCompositeById로 하나하나 가져올수 있다. 현재 위치 버튼을 가져온다. getX로 Image의 위치를 가져올
		 * 수 있다.
		 */
		currentPosition = sceneLoader.getRoot().getCompositeById("cross");
		currentNode = sceneLoader.getRoot().getImageById(positionManager.getCurrentNodeName());
		// 카메라 위치를 현재노드로 잡기 위하여 가져옴
		currentPosition.setX(currentNode.getX() - SET_POSITION + 16);
		currentPosition.setY(currentNode.getY() - SET_POSITION + 16);

		addActor(sceneLoader.getRoot());
		setNodeButton(positionManager, nodeAssets);
		setCamera();
		return this;
	}

	public ImageItem getCurrent() {
		return currentNode;
	}

	public void setNodeButton(final PositionManager positionManager, NodeAssets nodeAssets) {
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

	public void setCurrent(ImageItem current) {
		this.currentNode = current;
	}

	public void setCamera() {
		int xLeftLimit = (int) (StaticAssets.windowWidth / 2);
		int xRightLimit = (int) (3000 - (StaticAssets.windowWidth / 2));
		int yBottomLimit = (int) (StaticAssets.windowHeight / 2);
		int yTopLimit = (int) (1688 - (StaticAssets.windowHeight / 2));

		float xValue = this.getCurrent().getX() - StaticAssets.windowWidth / 2, yValue = this.getCurrent().getY()
				- StaticAssets.windowHeight / 2;
		// x값이 오른쪽으로 벗어날 경우
		if (this.getCurrent().getX() > xRightLimit)
			xValue = 3000 - StaticAssets.windowWidth;
		// x값이 왼쪽으로 벗어날 경우
		if (this.getCurrent().getX() < xLeftLimit)
			xValue = 0;
		// y값이 위로 벗어날 경우
		if (this.getCurrent().getY() > yTopLimit)
			yValue = 1688 - StaticAssets.windowHeight;
		// y값이 아래로 벗어날 경우
		if (this.getCurrent().getY() < yBottomLimit)
			yValue = 0;

		getCamera().translate(xValue, yValue, 0);
	}
}

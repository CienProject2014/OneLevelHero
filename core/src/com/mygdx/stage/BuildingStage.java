package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.WorldNodeAssets;
import com.mygdx.enums.GameObjectEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.model.Building;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class BuildingStage extends BaseOverlapStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private WorldNodeAssets worldNodeAssets;
	@Autowired
	private EventAssets eventAssets;

	private List<CompositeItem> npcButtonList;
	private List<CompositeItem> gameObjectList;
	private Building buildingInfo;
	private TextButton exitButton;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);

		makeScene();

		if (buildingInfo.getBuildingNpc() != null) {
			setNpcList();
		}
		if (buildingInfo.getGameObject() != null) {
			setItemList();
		}
		setExitButton();

		return this;
	}

	private void setExitButton() {
		exitButton = new TextButton("나가기", uiComponentAssets.getSkin());
		exitButton.center();
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.VILLAGE);
				event.getListenerActor().setVisible(false);
			}
		});

		addActor(exitButton);
	}

	private void makeScene() {
		buildingInfo = worldNodeAssets.getVillage(positionManager.getCurrentNode()).getBuilding()
				.get(positionManager.getCurrentBuilding());
		sceneLoader.loadScene(buildingInfo.getSceneName());
		cameraManager.stretchToDevice(this);
		addActor(sceneLoader.getRoot());
	}

	private void setNpcList() {
		npcButtonList = new ArrayList<CompositeItem>();
		for (final String npcName : buildingInfo.getBuildingNpc()) {
			CompositeItem npcButton = sceneLoader.getRoot().getCompositeById(npcName);
			npcButton.setTouchable(Touchable.enabled);
			npcButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					eventManager.setCurrentEventNpc(npcName);
					screenFactory.show(ScreenEnum.GREETING);
				}
			});
			npcButtonList.add(npcButton);
		}
	}

	private void setItemList() {
		gameObjectList = new ArrayList<CompositeItem>();
		for (final String objectName : buildingInfo.getGameObject()) {
			final CompositeItem objectButton = sceneLoader.getRoot().getCompositeById(objectName);
			objectButton.setVisible(true);
			objectButton.setLayerVisibilty(GameObjectEnum.PRESSED.getCode(), false);
			objectButton.setLayerVisibilty(GameObjectEnum.NORMAL.getCode(), true);
			objectButton.setTouchable(Touchable.enabled);
			objectButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					objectButton.setLayerVisibilty(GameObjectEnum.PRESSED.getCode(), true);
					objectButton.setLayerVisibilty(GameObjectEnum.NORMAL.getCode(), false);

					/* screenFactory.show(ScreenEnum.GREETING); */
				}
			});
			gameObjectList.add(objectButton);
		}
	}
}

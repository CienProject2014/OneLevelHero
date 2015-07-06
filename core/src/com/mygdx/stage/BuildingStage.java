package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.mygdx.manager.EventManager;
import com.mygdx.model.Building;
import com.mygdx.state.StaticAssets;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class BuildingStage extends OverlapStage {
	@Autowired
	private EventManager eventManager;

	private List<CompositeItem> npcButtonList;
	private Building buildingInfo;
	private TextButton exitButton;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);

		makeScene();
		setNpcList();
		setExitButton();

		return this;
	}

	private void setExitButton() {
		exitButton = new TextButton("나가기", assets.skin);
		exitButton.center();
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.VILLAGE);
				event.getListenerActor().setVisible(false);
			}
		});

		addActor(exitButton);
	}

	private void makeScene() {
		buildingInfo = assets.villageMap.get(positionInfo.getCurrentNode())
				.getBuilding().get(positionInfo.getCurrentBuilding());
		sceneLoader.loadScene(buildingInfo.getSceneName());
		cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
		addActor(sceneLoader.getRoot());
	}

	private void setNpcList() {
		npcButtonList = new ArrayList<CompositeItem>();
		for (final String npcName : buildingInfo.getBuildingNpc()) {
			CompositeItem npcButton = sceneLoader.getRoot().getCompositeById(
					npcName);
			npcButton.setTouchable(Touchable.enabled);
			npcButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					eventManager.setEventPack(assets.npcMap.get(npcName), true);
					screenFactory.show(ScreenEnum.GREETING);
				}
			});
			npcButtonList.add(npcButton);
		}
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}
}

package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.NpcManager;
import com.mygdx.manager.PlaceManager;
import com.mygdx.model.Building;
import com.mygdx.state.Assets;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

@Component
public class BuildingStage extends Overlap2DStage {
	@Autowired
	private Assets assets;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private NpcManager npcManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PlaceManager placeManager;
	private List<CompositeItem> npcButtonList;
	private Building buildingInfo;
	private TextButton exitButton;

	public Stage makeStage() {
		makeScene();
		setNpcList();
		setexitButton();
		return this;
	}

	private void setexitButton() {
		exitButton = new TextButton("나가기", assets.skin);
		exitButton.center();
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
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
		initSceneLoader();
		sceneLoader.loadScene(buildingInfo.getSceneName());
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
					eventManager.setEventInfo(assets.npcMap.get(npcName), true);
					screenFactory.show(ScreenEnum.GREETING);
				}

			});
			npcButtonList.add(npcButton);
		}
	}
}

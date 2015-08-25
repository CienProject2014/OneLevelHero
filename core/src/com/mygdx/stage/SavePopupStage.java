package com.mygdx.stage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.EventInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.SaveInfo;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.currentState.TimeInfo;
import com.mygdx.enums.SaveVersion;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.LoadNewManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.screen.BuildingScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class SavePopupStage extends BaseOverlapStage {
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private SaveManager saveManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private LoadNewManager loadManager;
	@Autowired
	private SaveInfo saveInfo;
	@Autowired
	private PartyInfo partyInfo;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private TimeInfo timeInfo;
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;

	private static final String SCENE_NAME = "save_scene";
	private final String SUBJECT_LABEL = "subject_label";
	private final String GAME_TIME_LABEL = "game_time_label";
	private final String SAVE_TIME_LABEL = "save_time_label";
	private final String PLACE_LABEL = "place_label";
	private final String SAVE_VERSION = "save_version";
	private final int SAVE_SIZE = 3;
	private Camera cam;
	private CompositeItem closeButton;
	private CompositeItem background;
	private CompositeItem save01;
	private CompositeItem save02;
	private CompositeItem save03;
	private CompositeItem newbutton;
	private Map<String, Array<String>> sceneConstants;

	public Stage makeStage() {
		assetsManager.initScene(SCENE_NAME);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());
		sceneConstants = constantsAssets.getSceneConstants(SCENE_NAME);
		setCamera();
		setCompositeItem();
		setLabels(sceneConstants);
		setAddListener();
		return this;
	}

	private void setLabelStyle(LabelItem labelItem) {
		labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		labelItem.setFontScale(1.0f);
		labelItem.setVisible(true);
		labelItem.setTouchable(Touchable.disabled);
	}

	private void setLabels(Map<String, Array<String>> sceneConstants) {
		Array<String> subjectLabels = sceneConstants.get(SUBJECT_LABEL);
		Array<String> gameTimeLabels = sceneConstants.get(GAME_TIME_LABEL);
		Array<String> saveTimeLabels = sceneConstants.get(SAVE_TIME_LABEL);
		Array<String> placeLabels = sceneConstants.get(PLACE_LABEL);
		Array<String> saveVerions = sceneConstants.get(SAVE_VERSION);
		for (int i = 0; i < SAVE_SIZE; i++) {
			LabelItem subjectLabel = sceneLoader.getRoot().getLabelById(subjectLabels.get(i));
			LabelItem gameTimeLabel = sceneLoader.getRoot().getLabelById(gameTimeLabels.get(i));
			LabelItem saveTimeLabel = sceneLoader.getRoot().getLabelById(saveTimeLabels.get(i));
			LabelItem placeLabel = sceneLoader.getRoot().getLabelById(placeLabels.get(i));
			if (saveManager.isLoadable(SaveVersion.findSaveByName(saveVerions.get(i)))) {

				subjectLabel.setText("프롤로그");
				gameTimeLabel.setText("2일째");
				saveTimeLabel.setText("한국시간 11시59분");
				placeLabel.setText("블랙우드 노숙");
				setLabelStyle(subjectLabel);
				setLabelStyle(gameTimeLabel);
				setLabelStyle(saveTimeLabel);
				setLabelStyle(placeLabel);
			} else {
				subjectLabel.setVisible(false);
				gameTimeLabel.setVisible(false);
				saveTimeLabel.setVisible(false);
				placeLabel.setVisible(false);
			}
		}
	}

	private void setCompositeItem() {
		background = sceneLoader.getRoot().getCompositeById("background");
		save01 = sceneLoader.getRoot().getCompositeById("save01");
		save02 = sceneLoader.getRoot().getCompositeById("save02");
		save03 = sceneLoader.getRoot().getCompositeById("save03");
		closeButton = sceneLoader.getRoot().getCompositeById("close_button");
		newbutton = sceneLoader.getRoot().getCompositeById("new_button");
	}

	private void setAddListener() {
		background.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BuildingScreen.isInSave = false;
			}
		});
		save01.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveInfo.setSaveVersion(SaveVersion.SAVE_01);
				saveManager.save();
				BuildingScreen.isInSave = false;
			}
		});
		save02.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveInfo.setSaveVersion(SaveVersion.SAVE_02);
				saveManager.save();
				BuildingScreen.isInSave = false;
			}
		});
		save03.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveInfo.setSaveVersion(SaveVersion.SAVE_03);
				saveManager.save();
				BuildingScreen.isInSave = false;
			}
		});
		closeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BuildingScreen.isInSave = false;
			}
		});

		newbutton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BuildingScreen.isInSave = false;
				saveManager.setNewGame();
			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}

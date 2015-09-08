package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.SaveInfo;
import com.mygdx.enums.SaveVersion;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.LoadNewManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.screen.BaseScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class LoadPopupStage extends BaseOverlapStage {
	private static final String SCENE_NAME = "load_scene";
	private final String SUBJECT_LABEL = "subject_label";
	private final String GAME_TIME_LABEL = "game_time_label";
	private final String SAVE_TIME_LABEL = "save_time_label";
	private final String PLACE_LABEL = "place_label";
	private final String SAVE_VERSION = "save_version";
	private final int SAVE_SIZE = 3;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private SaveManager saveManager;
	@Autowired
	private SaveInfo saveInfo;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private LoadNewManager loadNewManager;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private TimeManager timeManager;
	private Camera cam;
	private ImageItem save;
	private CompositeItem closeButton;
	private CompositeItem background;
	private CompositeItem save01;
	private CompositeItem save02;
	private CompositeItem save03;
	private List<ImageItem> characterImage;
	private Map<String, Array<String>> sceneConstants;
	private CompositeItem newButton;

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
				SaveInfo svInfo = saveManager.readSaveInfo(SaveVersion.findSaveByName(saveVerions.get(i)));
				subjectLabel.setText(svInfo.getStoryName());
				gameTimeLabel.setText(svInfo.getGameTime());
				placeLabel.setText(svInfo.getSavePlace());
				saveTimeLabel.setText(svInfo.getSaveTime());

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
		save = sceneLoader.getRoot().getImageById("save");
		newButton = sceneLoader.getRoot().getCompositeById("new_button");
		closeButton = sceneLoader.getRoot().getCompositeById("close_button");
		characterImage = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 5; j++) {
				ImageItem charImage = sceneLoader.getRoot().getImageById("save0" + i + "_" + "image0" + j);
				characterImage.add(charImage);
				charImage.setVisible(false);
			}
		}
	}

	private void setAddListener() {
		save.setVisible(false);
		background.addListener(new TouchListener());
		save01.addListener(new TouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveInfo.setSaveVersion(SaveVersion.SAVE_01);
				if (saveManager.isLoadable(SaveVersion.SAVE_01)) {
					musicManager.stopMusic();
					saveManager.load(SaveVersion.SAVE_01);
					movingManager.goCurrentLocatePosition();
					super.touchUp(event, x, y, pointer, button);
				}
			}
		});
		save02.addListener(new TouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveInfo.setSaveVersion(SaveVersion.SAVE_02);
				if (saveManager.isLoadable(SaveVersion.SAVE_02)) {
					musicManager.stopMusic();
					saveManager.load(SaveVersion.SAVE_02);
					movingManager.goCurrentLocatePosition();
					super.touchUp(event, x, y, pointer, button);
				}
			}
		});
		save03.addListener(new TouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveInfo.setSaveVersion(SaveVersion.SAVE_03);
				if (saveManager.isLoadable(SaveVersion.SAVE_03)) {
					musicManager.stopMusic();
					saveManager.load(SaveVersion.SAVE_03);
					movingManager.goCurrentLocatePosition();
					super.touchUp(event, x, y, pointer, button);
				}
			}
		});
		newButton.setLayerVisibilty("pressed", false);
		newButton.addListener(new TouchListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				newButton.setLayerVisibilty("pressed", true);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				newButton.setLayerVisibilty("pressed", false);
				saveManager.setNewGame();
				loadNewManager.loadNewGame();
				super.touchUp(event, x, y, pointer, button);

			}
		});
		closeButton.setLayerVisibilty("pressed", false);
		closeButton.addListener(new TouchListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				closeButton.setLayerVisibilty("pressed", true);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				closeButton.setLayerVisibilty("pressed", false);
				BaseScreen.showLoadStage = false;
				super.touchUp(event, x, y, pointer, button);
			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}

	private class TouchListener extends SimpleTouchListener {
		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			BaseScreen.showLoadStage = false;
		}
	}
}

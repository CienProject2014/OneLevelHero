package com.mygdx.stage;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.unit.Hero;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;
import com.uwsoft.editor.renderer.data.LabelVO;

public class StatusStage extends BaseOverlapStage {
	public static final String SCENE_NAME = "status_scene";
	public final String CHARACTER_STATUS_IMAGE = "character_status_image";
	public final String CHARACTER_IMAGE = "image_01";
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private TextureManager textureManager;
	private Camera cam;
	private CompositeItem closeButton;
	private ImageItem largeImage;
	private List<LabelVO> labels;
	private Image[] heroLargeImage;
	private CompositeItem worldMapButton;
	private CompositeItem inventoryButton;
	private final String STATUS_LABEL_NAME = "status_label";
	private CompositeItem backButton;

	public Stage makeStage() {
		HashMap<String, Array<String>> sceneConstants = constantsAssets.getSceneConstants(SCENE_NAME);

		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());
		setCamera();
		setLabel(partyManager, sceneConstants);
		setButton();
		setCharacterStatusImage(partyManager, sceneConstants);
		setTabButton();
		addListener();

		return this;
	}

	private void setTabButton() {
		inventoryButton = sceneLoader.getRoot().getCompositeById("inventory_tab");
		inventoryButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.INVENTORY);
			}

		});
	}

	private void setButton() {
		backButton = sceneLoader.getRoot().getCompositeById("back_tab");
	}

	private void addListener() {
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (battleManager.isInBattle()) {
					screenFactory.show(ScreenEnum.BATTLE);
				} else {
					movingManager.goCurrentPosition();
				}
			}
		});
	}

	private void setLabel(PartyManager partyManager, HashMap<String, Array<String>> sceneConstants) {
		Hero currentSelectedHero = partyManager.getCurrentSelectedHero();
		LabelItem nameLabel = sceneLoader.getRoot().getLabelById("name_label");
		nameLabel.setText(currentSelectedHero.getName());
		nameLabel.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		nameLabel.setFontScale(1.0f);
		nameLabel.setTouchable(Touchable.disabled);
		LabelItem fatigue = sceneLoader.getRoot().getLabelById("workaholic_name_label");
		fatigue.setText("피로도");
		fatigue.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		fatigue.setFontScale(1.0f);
		LabelItem fatigueLabel = sceneLoader.getRoot().getLabelById("workaholic_number_label");
		fatigueLabel.setText(String.valueOf(partyManager.getFatigue()));
		fatigueLabel.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		fatigueLabel.setFontScale(1.0f);
		fatigueLabel.setTouchable(Touchable.disabled);
		Array<String> labelList = sceneConstants.get(STATUS_LABEL_NAME);
		for (int i = 0; i < labelList.size; i++) {
			LabelItem labelItem = sceneLoader.getRoot().getLabelById(labelList.get(i));
			labelItem.setText(currentSelectedHero.getStatus().getStatusList().get(i));
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		}
	}

	private void setCharacterStatusImage(PartyManager partyManager, HashMap<String, Array<String>> sceneConstants) {
		Hero currentSelectedHero = partyManager.getCurrentSelectedHero();
		Array<String> characterStatusList = sceneConstants.get(CHARACTER_STATUS_IMAGE);
		for (int i = 0; i < partyManager.getPartyList().size(); i++) {
			CompositeItem compositeItem = sceneLoader.getRoot().getCompositeById(characterStatusList.get(i));
			ImageItem characterStatusImage = compositeItem.getImageById(CHARACTER_IMAGE);
			characterStatusImage.setDrawable(new TextureRegionDrawable(new TextureRegion(textureManager
					.getStatusTexture(partyManager.getPartyList().get(i).getFacePath()))));
		}
	}
	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}

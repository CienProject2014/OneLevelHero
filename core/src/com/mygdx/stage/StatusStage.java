package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.unit.Hero;
import com.mygdx.screen.StatusScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class StatusStage extends BaseOverlapStage {
	public static final String SCENE_NAME = "status_scene";
	public final String CHARACTER_CHANGE = "character_change";
	public final String CHARACTER_STATUS_IMAGE = "character_status_image";
	public final String CHARACTER_IMAGE = "image_01";
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private TextureManager textureManager;
	private Camera cam;

	private CompositeItem inventoryButton;
	private final String STATUS_LABEL_NAME = "status_label";
	private final String PRESSED_VISIBILITY = "pressed";
	private final String DEFAULT_VISIBILITY = "Default";
	private CompositeItem backButton, skillButton;
	private HashMap<String, Array<String>> sceneConstants;
	private Hero currentSelectedHero;

	public Stage makeStage() {
		assetsManager.initScene(SCENE_NAME);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());

		setCurrentSelectedHero(partyManager.getCurrentSelectedHero());
		sceneConstants = constantsAssets.getSceneConstants(SCENE_NAME);

		setCamera();
		setLabel(partyManager, sceneConstants);
		setButton();
		setCharacterBustImage(partyManager, sceneConstants);
		setCharacterStatusImage(partyManager, sceneConstants);
		setPartyEndButton();
		setTabButton();
		addListener();
		return this;
	}

	public void act(float delta) {
		setCharacterBustImage(partyManager, sceneConstants);
		setLabel(partyManager, sceneConstants);
		setCharacterStatusImage(partyManager, sceneConstants);
		setPartyEndButton();
	}

	private void setTabButton() {
		skillButton = sceneLoader.getRoot().getCompositeById("skill_tab");
		skillButton.setLayerVisibilty("pressed", false);

		inventoryButton = sceneLoader.getRoot().getCompositeById("inventory_tab");
		inventoryButton.setLayerVisibilty("pressed", false);
		inventoryButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.INVENTORY);
			}

		});
	}

	private void setPartyEndButton() {

		final CompositeItem partyEndButton = sceneLoader.getRoot().getCompositeById("ending_button");
		/* setCompositeItemVisibility(partyEndButton, DEFAULT_VISIBILITY); */
		if (currentSelectedHero.getFacePath().equals("yongsa")) {
			partyEndButton.setLayerVisibilty("Default", true);
			partyEndButton.setLayerVisibilty("pressed", false);
			partyEndButton.setLayerVisibilty("pressed2", false);
			partyEndButton.setLayerVisibilty("normal2", false);

			partyEndButton.addListener(new InputListener() {
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					/* StatusScreen.setClickedWorldMap(true); */
					partyEndButton.setLayerVisibilty("pressed", false);
					screenFactory.show(ScreenEnum.ENDING);
				}

				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					partyEndButton.setLayerVisibilty("pressed", true);
					return true;
				}
			});
		} else {
			partyEndButton.setLayerVisibilty("Default", false);
			partyEndButton.setLayerVisibilty("pressed", false);
			partyEndButton.setLayerVisibilty("normal2", true);
			partyEndButton.setLayerVisibilty("pressed2", false);
			partyEndButton.clearListeners();
			partyEndButton.addListener(new InputListener() {
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					/* StatusScreen.setClickedWorldMap(true); */
					partyEndButton.setLayerVisibilty("pressed2", false);
				}

				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					partyEndButton.setLayerVisibilty("pressed2", true);
					screenFactory.show(ScreenEnum.CHARACTER_CHANGE);
					return true;
				}
			});
		}

	}

	private void setButton() {
		backButton = sceneLoader.getRoot().getCompositeById("back_tab");
		final CompositeItem worldMapButton = sceneLoader.getRoot().getCompositeById("worldmap_button");
		setCompositeItemVisibility(worldMapButton, DEFAULT_VISIBILITY);
		worldMapButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibility(worldMapButton, DEFAULT_VISIBILITY);
				StatusScreen.setClickedWorldMap(true);
				screenFactory.show(ScreenEnum.WORLD_MAP);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibility(worldMapButton, PRESSED_VISIBILITY);
				return true;
			}
		});
	}

	private void addListener() {
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (battleManager.isInBattle()) {
					screenFactory.show(ScreenEnum.BATTLE);
				} else {
					movingManager.goCurrentLocatePosition();
				}
			}
		});
	}

	private void setLabel(PartyManager partyManager, HashMap<String, Array<String>> sceneConstants) {
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

	private void setCharacterBustImage(PartyManager partyManager, HashMap<String, Array<String>> sceneConstants) {
		ImageItem playerImage = sceneLoader.getRoot().getImageById("player_image");
		playerImage.setDrawable(new TextureRegionDrawable(
				new TextureRegion(textureManager.getStatusTexture(currentSelectedHero.getFacePath()))));
		playerImage.setTouchable(Touchable.enabled);
	}

	private void setCharacterStatusImage(final PartyManager partyManager,
			HashMap<String, Array<String>> sceneConstants) {
		Array<String> characterStatusList = sceneConstants.get(CHARACTER_STATUS_IMAGE);
		Array<String> characterChangeList = sceneConstants.get(CHARACTER_CHANGE);
		for (int i = 0; i < partyManager.getPartyList().size(); i++) {
			CompositeItem imageCompositeItem = sceneLoader.getRoot().getCompositeById(characterStatusList.get(i));
			ImageItem characterStatusImage = imageCompositeItem.getImageById(CHARACTER_IMAGE);
			characterStatusImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
					textureManager.getStatusTexture(partyManager.getPartyList().get(i).getFacePath()))));
			imageCompositeItem.setTouchable(Touchable.disabled);
		}

		for (int i = 0; i < 4; i++) {
			final CompositeItem touchableCompositeItem = sceneLoader.getRoot()
					.getCompositeById(characterChangeList.get(i));
			setCompositeItemVisibility(touchableCompositeItem, DEFAULT_VISIBILITY);
			touchableCompositeItem.setTouchable(Touchable.enabled);

			if (partyManager.getPartyList().size() > i) {
				final Hero imageHero = partyManager.getPartyList().get(i);
				touchableCompositeItem.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						setCurrentSelectedHero(imageHero);
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					}
				});

				if (imageHero == currentSelectedHero) {
					setCompositeItemVisibility(touchableCompositeItem, PRESSED_VISIBILITY);
				} else {
					setCompositeItemVisibility(touchableCompositeItem, DEFAULT_VISIBILITY);
				}
			} else {
				touchableCompositeItem.setVisible(false);
			}
		}
	}

	public void setCompositeItemVisibility(CompositeItem compositeItem, String visibility) {
		if (visibility.equals(DEFAULT_VISIBILITY)) {
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILITY, true);
			compositeItem.setLayerVisibilty(PRESSED_VISIBILITY, false);
		} else {
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILITY, false);
			compositeItem.setLayerVisibilty(PRESSED_VISIBILITY, true);
		}
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}

	public Hero getCurrentSelectedHero() {
		return currentSelectedHero;
	}

	public void setCurrentSelectedHero(Hero currentSelectedHero) {
		this.currentSelectedHero = currentSelectedHero;
		partyManager.setCurrentSelectedHero(currentSelectedHero);
	}
}

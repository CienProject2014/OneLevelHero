package com.mygdx.stage;

import java.util.HashMap;
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
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.BagManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.model.item.Item;
import com.mygdx.screen.BattleScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class ItemStage extends BaseOverlapStage {
	private final String DEFAULT_VISIBILTY = "Default";
	private final String PRESSED_VISIBILTY = "pressed";
	private final int ITEM_TAB_SIZE = 7;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private BagManager bagManager;
	// @Autowired
	// private GridHitbox gridHitbox;
	private Map<String, Array<String>> sceneConstants;
	public final String SCENE_NAME = "item_scene";
	private Camera cam;
	private Map<Integer, Item> itemInfo;
	private CompositeItem useButton;

	@Override
	public void act() {
		setLabel();
	}

	public Stage makeStage() {
		sceneConstants = constantsAssets.getSceneConstants("save_scene");
		assetsManager.initScene(SCENE_NAME);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());

		setCamera();
		setBackground();
		setLabel();
		setFirstUseButton();
		setHighlight();
		return this;
	}

	private void showItemDescription(int index) {
		LabelItem nameLabel = sceneLoader.getRoot().getLabelById("name_label");
		nameLabel.setText(itemInfo.get(index).getName());
		LabelItem descriptionLabel = sceneLoader.getRoot().getLabelById("description_label");
		descriptionLabel.setText(itemInfo.get(index).getDescription());
		setLabelStyle(nameLabel);
		setLabelStyle(descriptionLabel);
	}

	private void setVoidDescription() {
		LabelItem nameLabel = sceneLoader.getRoot().getLabelById("name_label");
		nameLabel.setText("설명충");
		LabelItem descriptionLabel = sceneLoader.getRoot().getLabelById("description_label");
		descriptionLabel.setText("나는 설명충이다");
		setLabelStyle(nameLabel);
		setLabelStyle(descriptionLabel);

	}

	private void addUseButtonListener(final int index) {
		useButton.clearListeners();
		useButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibilty(useButton, DEFAULT_VISIBILTY);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibilty(useButton, PRESSED_VISIBILTY);
				battleManager.setCurrentSelectedItem(itemInfo.get(index));
				BattleScreen.showItemStage = false;
			}
		});

	}

	private void setBackground() {
		final ImageItem background = sceneLoader.getRoot().getImageById("background");
		background.setTouchable(Touchable.enabled);
		background.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BattleScreen.showItemStage = false;
				battleManager.showRMenuButtons();
			}
		});
	}

	private void setFirstUseButton() {
		useButton = sceneLoader.getRoot().getCompositeById("use_button");
		useButton.setVisible(false);
	}

	private void setUseButton() {
		useButton.setVisible(true);
		setCompositeItemVisibilty(useButton, PRESSED_VISIBILTY);
		useButton.setTouchable(Touchable.enabled);
	}

	private void setLabel() {
		int playerConsumableSize = bagManager.getConsumablesList().size();
		itemInfo = new HashMap<>(ITEM_TAB_SIZE);
		for (int i = 0; i < ITEM_TAB_SIZE; i++) {
			LabelItem itemNameLabel = sceneLoader.getRoot().getLabelById("item_name_label0" + (i + 1));
			LabelItem itemAmountLabel = sceneLoader.getRoot().getLabelById("item_amount_label0" + (i + 1));
			if (playerConsumableSize > i) {
				itemInfo.put(i, bagManager.getConsumablesList().get(i));
				itemNameLabel.setText(bagManager.getConsumablesList().get(i).getName());
				itemAmountLabel.setText(String.valueOf(bagManager.getConsumablesList().get(i).getAmount()));
				setLabelStyle(itemNameLabel);
				setLabelStyle(itemAmountLabel);
			} else {
				itemNameLabel.setText("");
				itemAmountLabel.setText("");
			}
		}
	}

	private void setLabelStyle(LabelItem labelItem) {
		labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		labelItem.setFontScale(1.0f);
		labelItem.setTouchable(Touchable.disabled);
	}

	private void setCompositeItemVisibilty(CompositeItem compositeItem, String visibilty) {
		if (visibilty == PRESSED_VISIBILTY) {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, false);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, true);
		} else {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, true);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, false);
		}
	}

	private void setHighlight() {
		for (int i = 0; i < ITEM_TAB_SIZE; i++) {
			final int index = i;
			final CompositeItem highLightFrame = sceneLoader.getRoot().getCompositeById("highlight_0" + (i + 1));
			setCompositeItemVisibilty(highLightFrame, DEFAULT_VISIBILTY);
			highLightFrame.setTouchable(Touchable.enabled);
			highLightFrame.addListener(new SimpleTouchListener() {
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (itemInfo.get(index) != null) {
						setCompositeItemVisibilty(highLightFrame, PRESSED_VISIBILTY);
						setUseButton();
						addUseButtonListener(index);
						for (int j = 0; j < ITEM_TAB_SIZE; j++) {
							if (j != index) {
								final CompositeItem highLightFrame = sceneLoader.getRoot()
										.getCompositeById("highlight_0" + (j + 1));
								setCompositeItemVisibilty(highLightFrame, DEFAULT_VISIBILTY);
							}
						}
						showItemDescription(index);
					} else {
						setVoidDescription();
						setFirstUseButton();
						for (int j = 0; j < ITEM_TAB_SIZE; j++) {
							CompositeItem highLightFrame = sceneLoader.getRoot()
									.getCompositeById("highlight_0" + (j + 1));
							setCompositeItemVisibilty(highLightFrame, DEFAULT_VISIBILTY);
						}
					}

				}

			});
		}
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}
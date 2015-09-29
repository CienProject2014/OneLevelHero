package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.TextureManager;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class CharacterChangeStage extends BaseOverlapStage {
	private static final String PRESSED__VISIBILITY = null;
	private final String DEFAULT_VISIBILTY = "Default";
	private final String PRESSED_VISIBILTY = "pressed";
	private final int CHARACTER_TAB_SIZE = 3;
	@Autowired
	private transient UiComponentAssets uiComponentAssets;
	@Autowired
	private transient AssetsManager assetsManager;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private transient ConstantsAssets constantsAssets;
	// @Autowired
	// private GridHitbox gridHitbox;
	private Map<String, Array<String>> sceneConstants;
	public final String SCENE_NAME = "character_change_scene";
	private Camera cam;
	private List<CompositeItem> characterButtonList;

	@Override
	public void act() {
		setLabel(sceneConstants);

	}

	public Stage makeStage() {
		sceneConstants = constantsAssets.getSceneConstants(SCENE_NAME);
		assetsManager.initScene(SCENE_NAME);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());

		setCamera();
		setBackground();

		setLabel(sceneConstants);
		addCharacterButton();
		addApplyCloseButton();

		return this;
	}

	private void addApplyCloseButton() {
		final CompositeItem apply = sceneLoader.getRoot().getCompositeById("apply");
		/*
		 * final CompositeItem close =
		 * sceneLoader.getRoot().getCompositeById("cancel");
		 */

		apply.setTouchable(Touchable.enabled);
		/* close.setTouchable(Touchable.enabled); */

		setCompositeItemVisibilty(apply, DEFAULT_VISIBILTY);
		/* setCompositeItemVisibilty(close, DEFAULT_VISIBILTY); */

		apply.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibilty(apply, PRESSED_VISIBILTY);
				// 이걸 실행하고 넘어가야 되는데 바로 show 로 넘어간는 것처럼 보임.

				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibilty(apply, DEFAULT_VISIBILTY);
				screenFactory.show(ScreenEnum.STATUS);
			}
		});

		/*
		 * close.addListener(new InputListener() {
		 * 
		 * @Override public boolean touchDown(InputEvent event, float x, float
		 * y, int pointer, int button) { setCompositeItemVisibilty(close,
		 * PRESSED_VISIBILTY); // 이걸 실행하고 넘어가야 되는데 바로 show 로 넘어간는 것처럼 보임.
		 * 
		 * screenFactory.show(ScreenEnum.STATUS); return true; }
		 * 
		 * public void touchUp(InputEvent event, float x, float y, int pointer,
		 * int button) { setCompositeItemVisibilty(close, DEFAULT_VISIBILTY); }
		 * });
		 */

	}

	private void selectMember(int i) {
		if (partyManager.isBattleMember[i] == false) {
			setCompositeItemVisibilty(characterButtonList.get(i), DEFAULT_VISIBILTY);
			partyManager.removeBattleMember(partyManager.getPartyList().get(i + 1));
			partyManager.isBattleMember[i] = true;
		} else {
			setCompositeItemVisibilty(characterButtonList.get(i), PRESSED_VISIBILTY);
			partyManager.addBattleMember(partyManager.getPartyList().get(i + 1));
			partyManager.isBattleMember[i] = false;
		}
	}

	private void addCharacterButton() {
		characterButtonList = new ArrayList<CompositeItem>();

		for (int i = 1; i < partyManager.getPartyList().size(); i++) {
			final int index = i - 1;
			characterButtonList.add(sceneLoader.getRoot().getCompositeById("character0" + i));
			if (partyManager.isBattleMember[i - 1] == false) {
				setCompositeItemVisibilty(characterButtonList.get(i - 1), PRESSED_VISIBILTY);
			} else {
				setCompositeItemVisibilty(characterButtonList.get(i - 1), DEFAULT_VISIBILTY);
			}
			characterButtonList.get(i - 1).clearListeners();
			characterButtonList.get(i - 1).addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					selectMember(index);
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					/*
					 * setCompositeItemVisibilty(characterButtonList.get(index),
					 * DEFAULT_VISIBILTY);
					 */
				}
			});

			ImageItem characterImage = sceneLoader.getRoot().getImageById("character_image0" + i);
			characterImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
					textureManager.getStatusTexture(partyManager.getPartyList().get(i).getFacePath()))));
			characterImage.setWidth(122);
			characterImage.setHeight(122);

		}

		if (partyManager.getPartyList().size() <= CHARACTER_TAB_SIZE) {
			for (int i = partyManager.getPartyList().size(); i <= CHARACTER_TAB_SIZE; i++) {
				ImageItem garbageImage = sceneLoader.getRoot().getImageById("character_image0" + i);
				garbageImage.setVisible(false);

				garbageImage = sceneLoader.getRoot().getImageById("level0" + i);

				garbageImage.setVisible(false);

				garbageImage = sceneLoader.getRoot().getImageById("character_image_box0" + i);

				garbageImage.setVisible(false);

				LabelItem garbageLabel = sceneLoader.getRoot().getLabelById("character_name_label0" + i);
				garbageLabel.setVisible(false);

				garbageLabel = sceneLoader.getRoot().getLabelById("level_label0" + i);
				garbageLabel.setVisible(false);

				CompositeItem garbageComposite = sceneLoader.getRoot().getCompositeById("character0" + i);
				garbageComposite.setVisible(false);

			}
		}

	}

	private void setBackground() {
		final CompositeItem background = sceneLoader.getRoot().getCompositeById("background");
		background.setTouchable(Touchable.enabled);
		background.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

			}
		});
	}

	private void setLabel(Map<String, Array<String>> sceneConstants) {

		for (int i = 1; i < partyManager.getPartyList().size(); i++) {
			LabelItem characterNameLabel = sceneLoader.getRoot().getLabelById("character_name_label0" + i);
			LabelItem levelLabel = sceneLoader.getRoot().getLabelById("level_label0" + i);

			levelLabel.setText(String.valueOf(partyManager.getPartyList().get(i).getStatus().getLevel()));
			characterNameLabel.setText(partyManager.getPartyList().get(i).getName());

			setLabelStyle(characterNameLabel);
			setLabelStyle(levelLabel);
		}
	}

	private void setLabelStyle(LabelItem labelItem) {
		labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		labelItem.setFontScale(1.0f);
		labelItem.setTouchable(Touchable.disabled);
	}

	private void setCompositeItemVisibilty(CompositeItem compositeItem, String visibilty) {
		if (visibilty == PRESSED_VISIBILTY) {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, true);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, false);
		} else {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, false);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, true);
		}
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}

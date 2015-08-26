package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.AssetsManager;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class CreditStage extends BaseOverlapStage {

	private final String DEFAULT_VISIBILITY = "Default";
	private final String PRESSED_VISIBILITY = "pressed";

	@Autowired
	protected ScreenFactory screenFactory;

	@Autowired
	private AssetsManager assetsManager;
	// @Autowired
	// private GridHitbox gridHitbox;
	public final String SCENE_NAME = "credit_scene";
	private Camera cam;
	public int page = 1000;

	public Stage makeStage() {

		assetsManager.initScene(SCENE_NAME);
		initSceneLoader(assetsManager.rm);

		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());

		setCamera();
		setCloseButton();

		PageUpDownButton();

		setPage(0);
		return this;
	}

	private void setPage(int page) {
		final CompositeItem background = sceneLoader.getRoot().getCompositeById("background");

		switch (page % 4) {
		case 0:
			background.setLayerVisibilty("Default", true);
			background.setLayerVisibilty("normal", false);
			background.setLayerVisibilty("pressed", false);
			background.setLayerVisibilty("pressed2", false);
			break;
		case 1:
			background.setLayerVisibilty("Default", false);
			background.setLayerVisibilty("normal", true);
			background.setLayerVisibilty("pressed", false);
			background.setLayerVisibilty("pressed2", false);
			break;
		case 2:
			background.setLayerVisibilty("Default", false);
			background.setLayerVisibilty("normal", false);
			background.setLayerVisibilty("pressed", true);
			background.setLayerVisibilty("pressed2", false);
			break;
		case 3:
			background.setLayerVisibilty("Default", false);
			background.setLayerVisibilty("normal", false);
			background.setLayerVisibilty("pressed", false);
			background.setLayerVisibilty("pressed2", true);
			break;
		default:
			break;
		}

	}

	private void PageUpDownButton() {
		final CompositeItem pageDown = sceneLoader.getRoot().getCompositeById("page_down");
		pageDown.setTouchable(Touchable.enabled);
		final CompositeItem pageUp = sceneLoader.getRoot().getCompositeById("page_up");
		pageUp.setTouchable(Touchable.enabled);
		pageDown.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibility(pageDown, PRESSED_VISIBILITY);
				page++;
				setPage(page);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibility(pageDown, DEFAULT_VISIBILITY);
			}
		});

		pageUp.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibility(pageUp, PRESSED_VISIBILITY);
				page--;
				setPage(page);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				setCompositeItemVisibility(pageUp, DEFAULT_VISIBILITY);
			}
		});

	}

	private void setCloseButton() {

		final CompositeItem closeButton = sceneLoader.getRoot().getCompositeById("close_button");
		closeButton.setTouchable(Touchable.enabled);
		closeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.MENU);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

			}
		});
	}

	private void setCompositeItemVisibility(CompositeItem compositeItem, String visibilty) {
		if (visibilty == PRESSED_VISIBILITY) {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILITY, true);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILITY, false);
		} else {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILITY, false);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILITY, true);
		}
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}

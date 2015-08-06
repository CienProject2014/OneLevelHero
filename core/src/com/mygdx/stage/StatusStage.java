package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.Hero;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;
import com.uwsoft.editor.renderer.data.LabelVO;

public class StatusStage extends BaseOverlapStage {
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
	private Camera cam;
	private CompositeItem closeButton;
	private ImageItem largeImage;
	private List<LabelVO> labels;
	private Image[] heroLargeImage;
	private final String STATUS_LABEL_NAME = "status";
	private CompositeItem backButton;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("status_scene");
		addActor(sceneLoader.getRoot());
		setCamera();
		setLabel();
		setButton();
		setBustImage();
		addListener();

		return this;
	}

	private void setButton() {
		backButton = sceneLoader.getRoot().getCompositeById("back_button");
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

	private void setLabel() {
		Hero currentSelectedHero = partyManager.getCurrentSelectedHero();
		LabelItem nameLabel = sceneLoader.getRoot().getLabelById("name");
		nameLabel.setText(currentSelectedHero.getName());
		nameLabel.setStyle(new LabelStyle(uiComponentAssets.getFont(),
				Color.WHITE));
		nameLabel.setFontScale(1.0f);
		nameLabel.setTouchable(Touchable.disabled);
		ArrayList<String> labelList = constantsAssets
				.getLabels(STATUS_LABEL_NAME);
		for (int i = 0; i < labelList.size(); i++) {
			LabelItem labelItem = sceneLoader.getRoot().getLabelById(
					labelList.get(i));
			labelItem
					.setText(currentSelectedHero.getStatus().getStatusList()[i]);
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(),
					Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		}
	}

	private void setBustImage() {
		Hero currentSelectedHero = partyManager.getCurrentSelectedHero();
		CompositeItem compositeItem = sceneLoader.getRoot().getCompositeById(
				"change_01");
		ImageItem bustImage = compositeItem.getImageById("character_image");
		bustImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
				TextureManager.getStatusTexture(currentSelectedHero
						.getFacePath()))));
	}

	private void setCamera() {
		cam = new OrthographicCamera(1920f, 1080f);
		cam.position.set(1920 / 2, 1080 / 2, 0);
		getViewport().setCamera(cam);
	}
}

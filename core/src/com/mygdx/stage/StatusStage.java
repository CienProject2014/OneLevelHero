package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.PartyManager;
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
	private Camera cam;
	private CompositeItem closeButton;
	private ImageItem largeImage;
	private List<LabelVO> labels;
	private Image[] heroLargeImage;
	private final String STATUS_LABEL_NAME = "status";

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("status_scene");
		addActor(sceneLoader.getRoot());
		setCamera();
		setLabel();
		setImage();

		return this;
	}

	private void setLabel() {
		ArrayList<String> labelList = constantsAssets
				.getLabels(STATUS_LABEL_NAME);
		for (String labelName : labelList) {
			LabelItem labelItem = sceneLoader.getRoot().getLabelById(labelName);
			labelItem.setText(labelName);
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(),
					Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		}
	}

	private void setImage() {
	}

	private void setCamera() {
		cam = new OrthographicCamera(1920f, 1080f);
		cam.position.set(1920 / 2, 1080 / 2, 0);
		getViewport().setCamera(cam);
	}
}

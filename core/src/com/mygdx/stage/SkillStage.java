package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.assets.StaticAssets;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.data.LabelVO;

public class SkillStage extends BaseOverlapStage {
	private Camera cam;
	private ImageItem largeImage;
	private List<LabelVO> labels;
	private Image[] heroLargeImage;

	private List<CompositeItem> highlightButton;
	private List<CompositeItem> skillTypeButton;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("skill_scene");
		addActor(sceneLoader.getRoot());
		setCamera();
		setSkillType();
		setHighlight();
		labelSet();

		return this;
	}

	private void labelSet() {
		// labels = sceneLoader.getRoot().dataVO.composite.sLabels;
		// LabelItem labelItem =
		// sceneLoader.getRoot().getLabelById(labels.get(0).itemIdentifier);
		// labelItem.setText(partyInfo.getBattleMemberList().get(partyInfo.getSelectedInedex()).getName());

		// for (int i = 1; i < labels.size(); i++) {
		// labelItem =
		// sceneLoader.getRoot().getLabelById(labels.get(i).itemIdentifier);
		// labelItem.setText(
		// partyInfo.getBattleMemberList().get(partyInfo.getSelectedInedex()).getStatus().getStatusList()[i]);
		// }
	}

	private void setSkillType() {
		// skillTypeButton = new ArrayList<CompositeItem>();
		final CompositeItem skillTypeButton_01 = sceneLoader.getRoot().getCompositeById("ability");
		final CompositeItem skillTypeButton_02 = sceneLoader.getRoot().getCompositeById("magic");

		skillTypeButton_01.setLayerVisibilty("Default", true);
		skillTypeButton_01.setLayerVisibilty("pressed", false);
		skillTypeButton_01.setTouchable(Touchable.enabled);
		skillTypeButton_01.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				skillTypeButton_01.setLayerVisibilty("pressed", true);
				skillTypeButton_01.setLayerVisibilty("Default", false);
				skillTypeButton_02.setLayerVisibilty("Default", true);
				skillTypeButton_02.setLayerVisibilty("pressed", false);
			}

		});
		// skillTypeButton.add(skillTypeButton_01);
		skillTypeButton_02.setLayerVisibilty("Default", true);
		skillTypeButton_02.setLayerVisibilty("pressed", false);
		skillTypeButton_02.setTouchable(Touchable.enabled);
		skillTypeButton_02.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				skillTypeButton_02.setLayerVisibilty("pressed", true);
				skillTypeButton_02.setLayerVisibilty("Default", false);
				skillTypeButton_01.setLayerVisibilty("Default", true);
				skillTypeButton_01.setLayerVisibilty("pressed", false);
			}

		});
		// skillTypeButton.add(skillTypeButton_02);
	}

	private void setHighlight() {
		highlightButton = new ArrayList<CompositeItem>();
		final CompositeItem highlight_01 = sceneLoader.getRoot().getCompositeById("highlight_01");
		final CompositeItem highlight_02 = sceneLoader.getRoot().getCompositeById("highlight_02");
		final CompositeItem highlight_03 = sceneLoader.getRoot().getCompositeById("highlight_03");
		final CompositeItem highlight_04 = sceneLoader.getRoot().getCompositeById("highlight_04");
		final CompositeItem highlight_05 = sceneLoader.getRoot().getCompositeById("highlight_05");
		final CompositeItem highlight_06 = sceneLoader.getRoot().getCompositeById("highlight_06");
		final CompositeItem highlight_07 = sceneLoader.getRoot().getCompositeById("highlight_07");
		final CompositeItem useButton = sceneLoader.getRoot().getCompositeById("use");

		highlight_01.setLayerVisibilty("pressed", false);
		highlight_02.setLayerVisibilty("pressed", false);
		highlight_03.setLayerVisibilty("pressed", false);
		highlight_04.setLayerVisibilty("pressed", false);
		highlight_05.setLayerVisibilty("pressed", false);
		highlight_06.setLayerVisibilty("pressed", false);
		highlight_07.setLayerVisibilty("pressed", false);

		useButton.setLayerVisibilty("normal", true);
		useButton.setLayerVisibilty("pressed", false);

		highlight_01.setTouchable(Touchable.enabled);
		highlight_02.setTouchable(Touchable.enabled);
		highlight_03.setTouchable(Touchable.enabled);
		highlight_04.setTouchable(Touchable.enabled);
		highlight_05.setTouchable(Touchable.enabled);
		highlight_06.setTouchable(Touchable.enabled);
		highlight_07.setTouchable(Touchable.enabled);

		useButton.setTouchable(Touchable.enabled);

		highlight_01.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				highlight_01.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", false);
				highlight_01.setLayerVisibilty("pressed", true);

			}
		});
		highlight_02.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				highlight_01.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", true);

			}
		});
		highlight_03.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				highlight_01.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", true);

			}
		});
		highlight_04.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				highlight_01.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", true);

			}
		});
		highlight_05.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				highlight_01.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", true);

			}
		});
		highlight_06.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				highlight_01.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", true);

			}
		});
		highlight_07.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				highlight_01.setLayerVisibilty("pressed", false);
				highlight_02.setLayerVisibilty("pressed", false);
				highlight_03.setLayerVisibilty("pressed", false);
				highlight_04.setLayerVisibilty("pressed", false);
				highlight_05.setLayerVisibilty("pressed", false);
				highlight_06.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", false);
				highlight_07.setLayerVisibilty("pressed", true);

			}
		});
		useButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				useButton.setLayerVisibilty("Default", false);
				useButton.setLayerVisibilty("pressed", true);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				useButton.setLayerVisibilty("Default", true);
				useButton.setLayerVisibilty("pressed", false);
			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(1920f, 1080f);
		cam.position.set(1920 / 2, 1080 / 2, 0);
		getViewport().setCamera(cam);
	}
}

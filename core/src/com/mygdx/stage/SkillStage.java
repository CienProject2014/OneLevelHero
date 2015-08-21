package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.screen.BattleScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;
import com.uwsoft.editor.renderer.data.LabelVO;

public class SkillStage extends BaseOverlapStage {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventCheckManager eventCheckManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("EventStage");
	private Camera cam;
	private ImageItem largeImage;
	private List<LabelVO> labels;
	private Image[] heroLargeImage;

	private List<CompositeItem> highlightButton;
	private List<CompositeItem> skillTypeButton;
	private String CUT_01 = "cut_01"; // FIXME

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("skill_scene");
		addActor(sceneLoader.getRoot());
		setCamera();
		setSkillType();
		setHighlight();
		setLabel();

		return this;
	}

	private void setLabel() {
		int skillNum = battleManager.getCurrentActor().getSkills().size();
		for (int i = 0; i < skillNum; i++) {
			labels = sceneLoader.getRoot().dataVO.composite.sLabels;
			LabelItem labelItem = sceneLoader.getRoot().getLabelById(labels.get(i).itemIdentifier);
			labelItem.setText(battleManager.getCurrentActor().getSkills().get("cut_01").getName());

			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		}
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
				storySectionManager.triggerSectionEvent(EventTypeEnum.BATTLE_CONTROL, "skill_attack");
				BattleScreen.showSkillStage = false;

			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}

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
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.Hero;
import com.mygdx.state.StaticAssets;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;
import com.uwsoft.editor.renderer.data.LabelVO;

public class StatusStage extends OverlapStage {
	private Camera cam;
	private CompositeItem closeButton;
	private ImageItem largeImage;
	private List<LabelVO> labels;
	private Image[] heroLargeImage;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("status_scene");
		addActor(sceneLoader.getRoot());
		setCamera();
		labelSet();
		imageSet();

		return this;
	}

	private void labelSet() {
		labels = sceneLoader.getRoot().dataVO.composite.sLabels;
		LabelItem labelItem = sceneLoader.getRoot().getLabelById(
				labels.get(0).itemIdentifier);
		labelItem.setText(partyInfo.getBattleMemberList()
				.get(partyInfo.getSelectedInedex()).getName());

		for (int i = 1; i < labels.size(); i++) {
			labelItem = sceneLoader.getRoot().getLabelById(
					labels.get(i).itemIdentifier);
			labelItem.setText(partyInfo.getBattleMemberList()
					.get(partyInfo.getSelectedInedex()).getStatus()
					.getStatusList()[i]);
		}
	}

	private void imageSet() {
		largeImage = sceneLoader.getRoot().getImageById("large_image");
		largeImage.setVisible(false);
		List<Hero> currentPartyList = partyInfo.getPartyList();
		heroLargeImage = new Image[currentPartyList.size()];
		List<CompositeItem> partyListImage = new ArrayList<CompositeItem>();

		for (int i = 0; i < 5; i++) {
			int index = i + 1;
			partyListImage.add(sceneLoader.getRoot().getCompositeById(
					"image" + index));
			partyListImage.get(i).setVisible(false);
			/*
			 * partyListImage.setTouchable(Touchable.disabled);
			 * heroSmallImage[i].setSize(partyListImage.getWidth(),
			 * partyListImage.getHeight());
			 * heroSmallImage[i].setPosition(partyListImage.getX(),
			 * partyListImage.getY()); addActor(heroSmallImage[i]);
			 */
		}

		for (int i = 0; i < currentPartyList.size(); i++) {
			final int index = i;
			heroLargeImage[i] = new Image(currentPartyList.get(i)
					.getStatusTexture());
			partyListImage.get(i).setVisible(true);
			partyListImage.get(i).setTouchable(Touchable.enabled);
			partyListImage.get(i).addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					partyInfo.setSelectedInedex(index);
					screenFactory.show(ScreenEnum.STATUS);
				}
			});
		}

		heroLargeImage[partyInfo.getSelectedInedex()].setSize(
				largeImage.getWidth(), largeImage.getHeight());
		heroLargeImage[partyInfo.getSelectedInedex()].setPosition(
				largeImage.getX(), largeImage.getY());
		addActor(heroLargeImage[partyInfo.getSelectedInedex()]);
		closeButton = sceneLoader.getRoot().getCompositeById("close");
		closeButton.setTouchable(Touchable.enabled);
		closeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.VILLAGE);
			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(1920f, 1080f);
		cam.position.set(1920 / 2, 1080 / 2, 0);
		getViewport().setCamera(cam);
	}
}

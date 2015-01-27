package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.WorldMapManager;
import com.mygdx.model.Connection;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;

public class WorldMapStage extends Overlap2DStage {
	private CompositeItem item;
	private ImageItem current;

	public WorldMapStage() {
		// SceneLoader를 초기화
		initSceneLoader();
		// MainScene을 불러오자. SceneLoader는 CompositeItem을 가지고 있다.
		// SceneVO가 반환되는데, 이것은 CompositeVO를 가지고 있다.
		// CompositeVO는 그 Scene이 가지고 있는 Lable, Button등을 다 가지고 있다.
		sceneLoader.loadScene("MainScene");
		// getRoot()할시, CompositeItem이 반환된다.
		// CompositeItem은 Composite들의 집합이다.
		// getCompositeById로 하나하나 가져올수 있다.
		// 현재 위치 버튼을 가져온다.
		// getX로 Image의 위치를 가져올 수 있다.
		item = sceneLoader.getRoot().getCompositeById("current");
		current = sceneLoader.getRoot().getImageById(
				CurrentState.getInstance().getCurrentPosition()
						.getCurrentNode());
		current.setTouchable(Touchable.enabled);
		item.setX(current.getX() - 15);
		item.setY(current.getY() - 15);
		// arrow = sceneLoader.getRoot().getCompositeById("1to2");

		List<CompositeItem> arrowList = new ArrayList<>();
		String currentNode = CurrentState.getInstance().getCurrentPosition()
				.getCurrentNode();
		Map<String, Connection> connectionMap = Assets.worldNodeInfoMap.get(
				currentNode).getConnection();
		for (final Entry<String, Connection> connection : connectionMap
				.entrySet()) {
			final CompositeItem arrow = sceneLoader.getRoot().getCompositeById(
					connection.getValue().getArrowName());
			System.out.println(arrow);
			arrow.setVisible(true);
			arrow.setTouchable(Touchable.enabled);
			arrow.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					new WorldMapManager();
					WorldMapManager.selectDestinationNode(connection.getKey());
					new ScreenController(ScreenEnum.MOVING);
				}
			});
			arrowList.add(arrow);

		}
		addActor(sceneLoader.getRoot());
	}

	public ImageItem getCurrent() {
		return current;
	}

	public void setCurrent(ImageItem current) {
		this.current = current;
	}

	public void setCamera(OrthographicCamera cam) {
		// TODO Auto-generated method stub
		this.setCamera(cam);
	}

	public void settingCamera() {

		int x_left_limit = (int) (Assets.windowWidth / 2);
		int x_right_limit = (int) (3000 - (Assets.windowWidth / 2));
		int y_bottom_limit = (int) (Assets.windowHeight / 2);
		int y_top_limit = (int) (1688 - (Assets.windowHeight / 2));

		float xvalue = this.getCurrent().getX() - Assets.windowWidth / 2, yvalue = this
				.getCurrent().getY() - Assets.windowHeight / 2;
		// x값이 오른쪽으로 벗어날 경우
		if (this.getCurrent().getX() > x_right_limit) {

			xvalue = 3000 - Assets.windowWidth;
		}
		// x값이 왼쪽으로 벗어날 경우
		if (this.getCurrent().getX() < x_left_limit) {

			xvalue = 0;
		}
		// y값이 위로 벗어날 경우
		if (this.getCurrent().getY() > y_top_limit) {
			yvalue = 1688 - Assets.windowHeight;
		}
		// y값이 아래로 벗어날 경우
		if (this.getCurrent().getY() < y_bottom_limit) {
			yvalue = 0;
		}
		this.getCamera().translate(xvalue, yvalue, 0);
	}

}

package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.WorldMapManager;
import com.mygdx.model.Connection;
import com.mygdx.state.Assets;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;

@Component
@Scope("prototype")
public class WorldMapStage extends Overlap2DStage {
	@Autowired
	private Assets assets;
	@Autowired
	private WorldMapManager worldMapManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionInfo positionInfo;
	private CompositeItem item;
	private ImageItem current;

	public Stage makeStage() {
		// SceneLoader를 초기화
		initSceneLoader();
		// MainScene을 불러오자. SceneLoader는 CompositeItem을 가지고 있다.
		// SceneVO가 반환되는데, 이것은 CompositeVO를 가지고 있다.
		// CompositeVO는 그 Scene이 가지고 있는 Label, Button등을 다 가지고 있다.
		sceneLoader.loadScene("MainScene");
		// getRoot()할시, CompositeItem이 반환된다.
		// CompositeItem은 Composite들의 집합이다.
		// getCompositeById로 하나하나 가져올수 있다.
		// 현재 위치 버튼을 가져온다.
		// getX로 Image의 위치를 가져올 수 있다.
		item = sceneLoader.getRoot().getCompositeById("current");
		current = sceneLoader.getRoot().getImageById(
				positionInfo.getCurrentNode());
		current.setTouchable(Touchable.enabled);
		item.setX(current.getX() - 15); //FIXME
		item.setY(current.getY() - 15); //FIXME
		// arrow = sceneLoader.getRoot().getCompositeById("1to2");

		List<CompositeItem> arrowList = new ArrayList<>();
		String currentNode = positionInfo.getCurrentNode();
		Map<String, Connection> connectionMap = assets.worldNodeInfoMap.get(
				currentNode).getConnection();
		for (final Entry<String, Connection> connection : connectionMap
				.entrySet()) {
			final CompositeItem arrow = sceneLoader.getRoot().getCompositeById(
					connection.getValue().getArrowName());
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

					worldMapManager.selectDestinationNode(connection.getKey());
					screenFactory.show(ScreenEnum.MOVING);
				}
			});
			arrowList.add(arrow);

		}
		addActor(sceneLoader.getRoot());
		setCamera();
		return this;
	}

	public ImageItem getCurrent() {
		return current;
	}

	public void setCurrent(ImageItem current) {
		this.current = current;
	}

	public void setCamera() {
		int x_left_limit = (int) (assets.windowWidth / 2);
		int x_right_limit = (int) (3000 - (assets.windowWidth / 2));
		int y_bottom_limit = (int) (assets.windowHeight / 2);
		int y_top_limit = (int) (1688 - (assets.windowHeight / 2));

		float xvalue = this.getCurrent().getX() - assets.windowWidth / 2, yvalue = this
				.getCurrent().getY() - assets.windowHeight / 2;
		// x값이 오른쪽으로 벗어날 경우
		if (this.getCurrent().getX() > x_right_limit) {

			xvalue = 3000 - assets.windowWidth;
		}
		// x값이 왼쪽으로 벗어날 경우
		if (this.getCurrent().getX() < x_left_limit) {

			xvalue = 0;
		}
		// y값이 위로 벗어날 경우
		if (this.getCurrent().getY() > y_top_limit) {
			yvalue = 1688 - assets.windowHeight;
		}
		// y값이 아래로 벗어날 경우
		if (this.getCurrent().getY() < y_bottom_limit) {
			yvalue = 0;
		}
		getCamera().translate(xvalue, yvalue, 0);
	}

}

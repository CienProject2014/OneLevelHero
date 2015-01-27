package com.mygdx.stage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

@Component
@Scope("prototype")
public class WorldMapStage extends Overlap2DStage {
	private static CompositeItem item;

	public Stage makeStage() {
		// SceneLoader를 초기화
		initSceneLoader();
		// MainScene을 불러오자. SceneLoader는 CompositeItem을 가지고 있다.
		// SceneVO가 반환되는데, 이것은 CompositeVO를 가지고 있다.
		// CompositeVO는 그 Scene이 가지고 있는 Label, Button등을 다 가지고 있다.
		sceneLoader.loadScene("MainScene");
		// getRoot()할시, CompositeItem이 반환된다.
		// CompositeItem은 COomposite들의 집합이다.
		// getCompositeById로 하나하나 가져올수 있다.
		// 현재 위치 버튼 노란색 버튼을 가져온다.

		item = sceneLoader.getRoot().getCompositeById("currentPosition");

		addActor(sceneLoader.getRoot());
		return this;
	}
}

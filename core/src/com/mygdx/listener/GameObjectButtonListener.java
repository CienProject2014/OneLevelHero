package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.GameObjectEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.model.event.GameObject;

public class GameObjectButtonListener extends ClickListener {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	private GameObject pressedGameObject;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		pressedGameObject.setObjectType(GameObjectEnum.PRESSED);
		eventManager.setCurrentGameObject(pressedGameObject);
		screenFactory.show(ScreenEnum.GAME_OBJECT);
	}

	public void setGameObject(GameObject gameObject) {
		this.pressedGameObject = gameObject;
	}
}

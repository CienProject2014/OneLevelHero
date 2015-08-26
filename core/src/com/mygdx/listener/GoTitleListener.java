package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

public class GoTitleListener extends ClickListener {
	@Autowired
	private ScreenFactory screenFactory;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		screenFactory.popAllAndPush(ScreenEnum.MENU);
	}
}

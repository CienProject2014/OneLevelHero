package com.mygdx.event.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.ScreenChangeEvent;
import com.mygdx.factory.ScreenFactory;

public class ScreenChangeEventListener implements EventListener {
	@Autowired
	private ScreenFactory screenFactory;

	public void handle(Event e) {
		if (e.info.get(ScreenChangeEvent.TAG_TYPE) == ScreenChangeEvent.TYPE)
			screenFactory.show((ScreenEnum) e.info
					.get(ScreenChangeEvent.TAG_TARGET));
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}
}

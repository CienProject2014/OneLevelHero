package com.mygdx.event.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.Event;
import com.mygdx.event.ScreenChangeEvent;
import com.mygdx.factory.ScreenFactory;

public class ScreenChangeEventListener implements EventListener {
	@Autowired
	private ScreenFactory screenFactory;

	@Override
	public void process(Event e) {
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
}

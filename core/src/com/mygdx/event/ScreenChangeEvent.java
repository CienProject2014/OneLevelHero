package com.mygdx.event;

import com.mygdx.enums.ScreenEnum;

public class ScreenChangeEvent extends Event {
	public final static String TAG_TYPE = "type";
	public final static String TAG_TARGET = "target";
	public final static String TYPE = "ScreenChange";

	public ScreenChangeEvent(ScreenEnum screenEnum) {
		this.info.put(TAG_TYPE, TYPE);
		this.info.put(TAG_TARGET, screenEnum);
	}
}

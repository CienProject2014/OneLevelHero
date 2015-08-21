package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.assets.StaticAssets;

public class StatusBarUi extends ProgressBar {
	private String name;

	public StatusBarUi(String name, float min, float max, float stepSize,
			boolean vertical, Skin skin) {
		super(min, max, stepSize, vertical, skin.get("default-"
				+ (vertical ? "vertical" : "horizontal"),
				ProgressBarStyle.class));
		Gdx.app.debug("StatusBarUi", "Called");
		this.name = name;
		ProgressBarStyle progressBarStyle = barsStyle(this.name);
		progressBarStyle.knobBefore = progressBarStyle.knob;
		setStyle(barsStyle(this.name));
	}

	public StatusBarUi(String name, Skin skin) {
		this(name, 0, 100, 1, false, skin);
	}

	public ProgressBarStyle barsStyle(String barStyle) {
		switch (barStyle) {
			case "hp":
				return StaticAssets.barstyle_hp;
			case "gauge":
				return StaticAssets.barstyle_turn;
			default:
				Gdx.app.log("StatusBarUi", "barstyle type error");
				return StaticAssets.barstyle_hp;
		}
	}
}

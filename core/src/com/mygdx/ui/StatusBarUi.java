package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.assets.StaticAssets;

public class StatusBarUi extends ProgressBar {
	private String name;
	private ProgressBarStyle barstyle_hp;
	private ProgressBarStyle barstyle_turn;

	public StatusBarUi(String name, float min, float max, float stepSize, boolean vertical, Skin skin) {
		super(min, max, stepSize, vertical,
				skin.get("default-" + (vertical ? "vertical" : "horizontal"), ProgressBarStyle.class));
		Gdx.app.debug("StatusBarUi", "Called");
		barstyle_hp = new ProgressBarStyle(StaticAssets.skin.getDrawable("WHITE"),
				StaticAssets.skin.getDrawable("RED"));
		barstyle_turn = new ProgressBarStyle(StaticAssets.skin.getDrawable("WHITE"),
				StaticAssets.skin.getDrawable("GREEN"));
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
			return barstyle_hp;
		case "gauge":
			return barstyle_turn;
		default:
			Gdx.app.log("StatusBarUi", "barstyle type error");
			return barstyle_hp;
		}
	}
}

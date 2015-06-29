package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.state.StaticAssets;

public class StatusBarUi extends ProgressBar {
	private String name;

	public StatusBarUi(String name, float min, float max, float stepSize,
			boolean vertical, Skin skin) {
		super(min, max, stepSize, vertical, skin.get("default-"
				+ (vertical ? "vertical" : "horizontal"),
				ProgressBarStyle.class));
		Gdx.app.debug("StatusBarUi", "Called");
		this.name = name;
		setStyle(barsStyle(this.name));
	}

	public ProgressBarStyle barsStyle(String barStyle) {
		switch (barStyle) {
		case "hp":
			return StaticAssets.barstyle_hp;
		case "exp":
			return StaticAssets.barstyle_exp;
		case "turn":
			return StaticAssets.barstyle_turn;
		default:
			return StaticAssets.barstyle_hp;
		}
	}
}

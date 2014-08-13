package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StatusBar extends ProgressBar {

	private String name;
	private ProgressBarStyle style;

	public StatusBar(String name, float min, float max, float stepSize,
			boolean vertical, Skin skin) {
		super(min, max, stepSize, vertical, skin.get("default-"
				+ (vertical ? "vertical" : "horizontal"),
				ProgressBarStyle.class));
		this.name = name;
		setStyle(barsStyle(this.name));

	}

	public ProgressBarStyle barsStyle(String name) {

		TextureRegion bartexture = new TextureRegion(new Texture(
				Gdx.files.internal("texture/bgcolour.png")));
		TextureRegionDrawable bardrawable = new TextureRegionDrawable(
				bartexture);
		TextureRegion bartextureknob = new TextureRegion(new Texture(
				Gdx.files.internal("texture/" + name + "colour.png")));
		TextureRegionDrawable bardrawableknob = new TextureRegionDrawable(
				bartextureknob);

		ProgressBarStyle style = new ProgressBarStyle(bardrawable,
				bardrawableknob);

		return style;

	}

}

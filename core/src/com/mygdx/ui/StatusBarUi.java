package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

public class StatusBarUi extends ProgressBar implements Disposable {
	private String name;
	private Texture bartexture;
	private Texture bartextureknob;

	public StatusBarUi(String name, float min, float max, float stepSize,
			boolean vertical, Skin skin) {
		super(min, max, stepSize, vertical, skin.get("default-"
				+ (vertical ? "vertical" : "horizontal"),
				ProgressBarStyle.class));
		Gdx.app.log("StatusBarUi", "Called");
		this.name = name;
		setStyle(barsStyle(this.name));
	}

	public ProgressBarStyle barsStyle(String name) {
		bartexture = new Texture(Gdx.files.internal("texture/bgcolour.png"));
		TextureRegion barregion = new TextureRegion(bartexture);
		TextureRegionDrawable bardrawable = new TextureRegionDrawable(barregion);
		bartextureknob = new Texture(Gdx.files.internal("texture/" + name
				+ "colour.png"));
		TextureRegion barregionknob = new TextureRegion(bartexture);
		TextureRegionDrawable bardrawableknob = new TextureRegionDrawable(
				barregionknob);

		ProgressBarStyle style = new ProgressBarStyle(bardrawable,
				bardrawableknob);

		return style;
	}

	@Override
	public void dispose() {
		Gdx.app.log("StatusBarUi", "Dispose");
		bartexture.dispose();
		bartextureknob.dispose();
	}
}

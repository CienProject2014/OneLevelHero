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
		super(min, max, stepSize, vertical, skin.get("default-" + (vertical ? "vertical" : "horizontal"), ProgressBarStyle.class));
		Gdx.app.debug("StatusBarUi", "Called");
		this.name = name;
		setStyle(barsStyle(this.name));
	}

	public ProgressBarStyle barsStyle(String name) {
		bartexture = new Texture(Gdx.files.internal("texture/bgcolour.png"));
		bartextureknob = new Texture(Gdx.files.internal("texture/" + name + "colour.png"));

		ProgressBarStyle style = new ProgressBarStyle(
				new TextureRegionDrawable(new TextureRegion(bartexture)),
				new TextureRegionDrawable(new TextureRegion(bartexture)));

		return style;
	}

	@Override
	public void dispose() {
		Gdx.app.debug("StatusBarUi", "Dispose");
		bartexture.dispose();
		bartextureknob.dispose();
	}
}

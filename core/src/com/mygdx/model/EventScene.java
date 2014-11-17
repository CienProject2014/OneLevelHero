package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.resource.Assets;

public class EventScene {
	private String script;
	private String characterPath;
	private String backgroundPath;
	private Image character;
	private Image background;

	public Image getCharacter() {
		if (this.character == null)
			character = (Image) Assets.resourceFileList.get(characterPath);
		return character;
	}

	public void setCharacter(Image character) {
		this.character = character;
	}

	public Image getBackground() {
		if (background == null)
			background = (Image) Assets.imageFileList.get(backgroundPath);
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	public String getCharacterPath() {
		return characterPath;
	}

	public void setCharacterPath(String characterPath) {
		this.characterPath = characterPath;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;

	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}

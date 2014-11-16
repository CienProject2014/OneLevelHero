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
		return character;
	}

	public void setCharacter(String characterPath) {
		this.character = (Image) Assets.resourceFileList.get(characterPath);
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
		setBackgroundPath(backgroundPath);
	}

	public String getCharacterPath() {
		return characterPath;
	}

	public void setCharacterPath(String characterPath) {
		this.characterPath = characterPath;
		setCharacter(characterPath);
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
		this.background = (Image) Assets.resourceFileList.get(backgroundPath);

	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}

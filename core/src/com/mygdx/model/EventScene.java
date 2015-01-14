package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.state.Assets;

public class EventScene {
	private String script;
	private String characterPath;
	private int faceNumber;

	public int getFaceNumber() {
		return faceNumber;
	}

	public void setFaceNumber(int faceNumber) {
		this.faceNumber = faceNumber;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	private String backgroundPath;
	private String direction;
	private Texture character;
	private Texture background;

	public Texture getCharacter() {
		if (character == null)
			character = Assets.characterTextureMap.get(characterPath);
		return character;
	}

	public void setCharacter(Texture character) {
		this.character = character;
	}

	public Texture getBackground() {
		if (background == null)
			background = Assets.backgroundTextureMap.get(backgroundPath);
		return background;
	}

	public void setBackground(Texture background) {
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

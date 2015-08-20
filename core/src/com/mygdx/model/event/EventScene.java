package com.mygdx.model.event;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.manager.TextureManager;

public class EventScene {
	private final String SPEAKER_LEFT = "left";
	private final String SPEAKER_RIGHT = "right";
	private String script;
	private String characterPath;
	private String faceNumber;
	private String backgroundPath;
	private String speakerPosition;
	private Texture character;
	private Texture background;

	public Texture getCharacter() {
		if (character == null) {
			if (faceNumber == null) {
				character = TextureManager.getBustTexture(characterPath);
			} else {
				character = TextureManager.getBustTexture(characterPath, faceNumber);
			}
		}
		return character;
	}

	public void setCharacter(Texture character) {
		this.character = character;
	}

	public Texture getBackground() {
		if (background == null) {
			if (TextureManager.getBackgroundTexture(backgroundPath) != null) {
				background = TextureManager.getBackgroundTexture(backgroundPath);
			}
		}
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

	public String getFaceNumber() {
		return faceNumber;
	}

	public void setFaceNumber(String faceNumber) {
		this.faceNumber = faceNumber;
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

	public String getSpeakerPosition() {
		return speakerPosition;
	}

	public void setSpeakerPosition(String speakerPosition) {
		this.speakerPosition = speakerPosition;
	}
}

package com.mygdx.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.state.Assets;
import com.mygdx.state.StaticAssets;

@Component
public class EventScene {
	@Autowired
	private Assets assets;
	private String script;
	private String characterPath;
	private int faceNumber;
	private String backgroundPath;
	private String speakerPosition;
	private Texture character;
	private Texture background;

	public EventScene() {
		Gdx.app.debug("EventScene", "EventScene is constructed");
	}

	public int getFaceNumber() {
		return faceNumber;
	}

	public void setFaceNumber(int faceNumber) {
		this.faceNumber = faceNumber;
	}

	public Texture getCharacter() {
		if (character == null)
			character = StaticAssets.characterTextureMap.get(characterPath);
		return character;
	}

	public void setCharacter(Texture character) {
		this.character = character;
	}

	public Texture getBackground() {
		if (background == null) {
			background = StaticAssets.backgroundTextureMap.get(backgroundPath);
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

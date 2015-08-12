package com.mygdx.model.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.assets.StaticAssets;
import com.mygdx.manager.TextureManager;

public class EventScene {
	private String script;
	private String characterPath;
	private String faceNumber;
	private String backgroundPath;
	private String speakerPosition;
	private Texture character;
	private Texture background;

	public String getFaceNumber() {
		return faceNumber;
	}

	public void setFaceNumber(String faceNumber) {
		this.faceNumber = faceNumber;
	}

	public Texture getCharacter() {
		if (character == null) {
			if (faceNumber == null) {
				character = StaticAssets.TextureMap.get(characterPath);
			} else
				if (TextureManager.getBustTexture(characterPath,
						faceNumber) != null) {
				character = TextureManager.getBustTexture(characterPath,
						faceNumber);
			} else {
				Gdx.app.log("EventScene", "chracterTextureMap에 " + characterPath
						+ " 에 해당하는 이미지가 존재하지 않습니다.");
			}
		}
		return character;
	}
	public void setCharacter(Texture character) {
		this.character = character;
	}

	public Texture getBackground() {
		if (background == null)
			background = StaticAssets.TextureMap.get(backgroundPath);

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

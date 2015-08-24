package com.mygdx.model.event;

public class EventScene {
	public final static String SPEAKER_LEFT = "left";
	public final static String SPEAKER_RIGHT = "right";
	private String script;
	private String characterPath;
	private String faceNumber;
	private String backgroundPath;
	private String speakerPosition;

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

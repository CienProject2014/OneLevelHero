package com.mygdx.model.event;

import java.util.ArrayList;

public class EventScene {
	public final static String SPEAKER_LEFT = "left";
	public final static String SPEAKER_RIGHT = "right";
	private String script;
	private String characterPath;
	private String faceNumber;
	private String backgroundPath;
	private String speakerPosition;
	private ArrayList<Reward> rewards;

	public EventScene() {

	}

	public EventScene(String backgroundPath, String characterPath, String script, String faceNumber) {
		this.backgroundPath = backgroundPath;
		this.characterPath = characterPath;
		this.script = script;
		this.faceNumber = faceNumber;
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

	public ArrayList<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(ArrayList<Reward> rewards) {
		this.rewards = rewards;
	}
}

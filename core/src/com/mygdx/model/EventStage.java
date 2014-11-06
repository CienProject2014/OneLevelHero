package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.resource.Assets;

public class EventStage {
	private String script;
	private Image character;
	private Image background;

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Image getCharacter() {
		return character;
	}

	//임시로 asset에서 Image객체를 추출하여 가져온다.
	public void setCharacter(String character) {
		this.character = (Image) Assets.resourceFileList.get(character);
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

}

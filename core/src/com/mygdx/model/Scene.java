package com.mygdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Scene {
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

	public void setCharacter(Image character) {
		this.character = character;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

}

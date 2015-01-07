package com.mygdx.script;

import com.mygdx.stage.WorldMapStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.script.IScript;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class ButtonScript implements IScript {
	private WorldMapStage stage;
	private CompositeItem postionbutton;
	private CompositeItem button;
	private SimpleButtonScript playButtonScript;

	public ButtonScript(WorldMapStage stage) {
		this.stage = stage;
	}

	@Override
	public void init(CompositeItem item) {
		// TODO Auto-generated method stub
		button = item;
		postionbutton = button.getCompositeById("oberon");
		playButtonScript = new SimpleButtonScript();
		playButtonScript.init(postionbutton);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		postionbutton.dispose();
		playButtonScript.dispose();
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		playButtonScript.act(delta);
	}

}

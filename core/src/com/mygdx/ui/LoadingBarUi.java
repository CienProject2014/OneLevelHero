package com.mygdx.ui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBarUi extends Actor {
	Animation animation;
	TextureRegion reg;
	float stateTime;

	public LoadingBarUi(Animation animation) {
		this.animation = animation;
		reg = animation.getKeyFrame(0);
	}

	@Override
	public void act(float delta) {
		stateTime += delta;
		reg = animation.getKeyFrame(stateTime);
	}

}

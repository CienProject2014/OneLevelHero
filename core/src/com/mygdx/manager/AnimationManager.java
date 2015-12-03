package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.TextureAssets;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Unit;

public abstract class AnimationManager {
	@Autowired
	private TextureAssets textureAssets;

	public abstract void registerAnimation(Unit attackUnit, Unit defendUnit, String animationName);

	public abstract void registerAnimation(Hero attackedHero, String animationName, int x, int y);

	public abstract void registerAnimation(String name, int x, int y);

	public abstract void nextFrame(float delta);

	public abstract boolean isPlayable();

	public abstract boolean isEmptyAnimation();
}

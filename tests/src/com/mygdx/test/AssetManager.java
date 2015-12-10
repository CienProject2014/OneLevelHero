package com.mygdx.test;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.SceneVO;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.utils.MySkin;

public class AssetManager implements IResourceRetriever {
	private TextureAtlas mainPack;
	private HashMap<String, ParticleEffect> particleEffects = new HashMap<String, ParticleEffect>();
	private HashMap<String, TextureAtlas> animations = new HashMap<String, TextureAtlas>();

	public void loadassets() {
		mainPack = new TextureAtlas(
				Gdx.files.internal("overlap2d/orig/pack.atlas"));
		ParticleEffect rain = new ParticleEffect();
		rain.load(Gdx.files.internal("overlap2d/particles/rain"), mainPack, "");

		ParticleEffect firefly = new ParticleEffect();
		firefly.load(Gdx.files.internal("overlap2d/particles/firefly"),
				mainPack, "");
	}

	@Override
	public TextureRegion getTextureRegion(String name) {
		return null;
	}

	@Override
	public ParticleEffect getParticleEffect(String name) {
		return particleEffects.get(name);
	}

	@Override
	public TextureAtlas getSkeletonAtlas(String name) {
		return null;
	}

	@Override
	public FileHandle getSkeletonJSON(String name) {
		return null;
	}

	@Override
	public TextureAtlas getSpriteAnimation(String name) {
		return null;
	}

	@Override
	public BitmapFont getBitmapFont(String name, int size) {
		return null;
	}

	@Override
	public MySkin getSkin() {
		return null;
	}

	@Override
	public SceneVO getSceneVO(String sceneName) {
		return null;
	}

	@Override
	public ProjectInfoVO getProjectVO() {
		return null;
	}

	public TextureAtlas getassets() {
		return mainPack;
	}

	public void setassets(TextureAtlas assets) {
		this.mainPack = assets;
	}
}

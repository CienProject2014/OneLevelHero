package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.Assets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.manager.SoundManager;

public class LoadingBarStage extends BaseOneLevelStage {

	@Autowired
	AssetsManager assetsManager;
	@Autowired
	private Assets assets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private SaveManager saveManager;

	private Music bgm;
	private Image logo;
	private Image loadingFrame;
	private Image screenBg;
	private Image loadingBg;
	private TextButton textButton;
	private float percent;
	private int loadingBarMaxWidth;
	private int loadingBarHeight;
	private final int loadingBarFinishOffset = 10;

	private final String LOADING_MESSAGE = "환영합니다 용사님..! 카딜라 지방으로 이동하는 중입니다... ";

	public Stage makeStage() {
		super.makeStage();
		assetsManager.load("music/bgm_mongsimji.mp3", Music.class);
		assetsManager.load("texture/loading/loading.pack", TextureAtlas.class);
		assetsManager.load("texture/loading/cien_logo2.png", Texture.class);
		assetsManager.finishLoading();

		bgm = assetsManager.get("music/bgm_mongsimji.mp3", Music.class);
		TextureAtlas atlas = assetsManager.get("texture/loading/loading.pack", TextureAtlas.class);
		logo = new Image(assetsManager.get("texture/loading/cien_logo2.png", Texture.class));
		loadingFrame = new Image(atlas.findRegion("loading-frame"));
		screenBg = new Image(atlas.findRegion("screen-bg"));
		loadingBg = new Image(atlas.findRegion("loading-frame-bg"));
		textButton = new TextButton(LOADING_MESSAGE, StaticAssets.skin);

		if (Gdx.app.getPreferences("MusicVolume").getFloat("musicVolume") == 0.0) {
			Gdx.app.log("첫 번째", "게임을 처음 깔았습니다");
			musicManager.setMusicVolume(0.5f);
			soundManager.setSoundVolume(0.5f);
		} else {
			Gdx.app.log("첫 번째", "이미 데이터가 있습니다");
			musicManager.setMusicVolume(Gdx.app.getPreferences("MusicVolume").getFloat("musicVolume"));
			soundManager.setSoundVolume(Gdx.app.getPreferences("SoundVolume").getFloat("soundVolume"));
		}
		musicManager.playMusic(bgm);
		Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim"));
		anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
		Table logoTable = new Table();
		logoTable.align(Align.center).align(Align.top).padTop(200);
		logoTable.add(logo);
		Table textTable = new Table();
		textTable.align(Align.center).align(Align.bottom).padBottom(300);
		textTable.add(textButton);

		Table backgroundTable = new Table();
		backgroundTable.setBackground(screenBg.getDrawable());

		Table frameTable = new Table();
		frameTable.align(Align.center);
		frameTable.add(loadingFrame);
		loadingBarMaxWidth = (int) loadingFrame.getWidth();
		loadingBarHeight = (int) loadingFrame.getHeight();

		Table loadingBarTable = new Table();
		loadingBarTable.align(Align.center);
		loadingBarTable.add(loadingBg).padRight(loadingFrame.getWidth() - 5)
				.padTop(loadingFrame.getHeight() - loadingBg.getHeight());

		tableStack.add(backgroundTable);
		tableStack.add(loadingBarTable);
		tableStack.add(frameTable);
		tableStack.add(logoTable);
		tableStack.add(textTable);

		saveManager.saveNewGameInfo();
		StaticAssets.loadAll();
		assets.initialize();

		String musicVolume = String.valueOf(musicManager.getMusicVolume());
		String soundVolume = String.valueOf(soundManager.getSoundVolume());
		Gdx.app.log("musicVolume", musicVolume);
		Gdx.app.log("soundVolume", soundVolume);
		return this;
	}

	@Override
	public void act() {
		if (assetsManager.update()) {
			assetsManager.unload("texture/loading/loading.pack");
			assetsManager.unload("texture/loading/cien_logo2.png");
			screenFactory.popAllAndPush(ScreenEnum.MENU);
		}

		// Interpolate the percentage to make it more smooth
		percent = Interpolation.linear.apply(percent, assetsManager.getProgress(), 0.1f);
		loadingBg.setSize(loadingBarMaxWidth * percent, loadingBarHeight);
		loadingBg.invalidate();
		if (loadingBg.getWidth() > loadingBarMaxWidth - loadingBarFinishOffset) {
			loadingBg.setWidth(loadingBarMaxWidth);
		}
	}

}

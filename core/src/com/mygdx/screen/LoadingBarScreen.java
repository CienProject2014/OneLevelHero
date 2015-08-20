package com.mygdx.screen;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.assets.Assets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.game.OneLevelHero;
import com.mygdx.ui.LoadingBarUi;

public class LoadingBarScreen extends BaseScreen {

	private Stage stage;
	private OneLevelHero game;
	private Image logo;
	private Image loadingFrame;
	private Image loadingBarHidden;
	private Image screenBg;
	private Image loadingBg;
	private float startX, endX;
	private float percent;
	private Actor loadingBar;

	public LoadingBarScreen(OneLevelHero game) {
		this.game = game;
	}

	@Override
	public void show() {
		StaticAssets.assetManager.load("texture/loading/loading.pack", TextureAtlas.class);
		StaticAssets.assetManager.finishLoading();
		// context = RoboSpring.getContext(); 안드로이드에서 실행시
		// Initialize the stage where we will place everything
		stage = new Stage();
		TextureAtlas atlas = StaticAssets.assetManager.get("texture/loading/loading.pack", TextureAtlas.class);
		// Grab the regions from the atlas and create some images
		logo = new Image(atlas.findRegion("libgdx-logo"));
		loadingFrame = new Image(atlas.findRegion("loading-frame"));
		loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
		screenBg = new Image(atlas.findRegion("screen-bg"));
		loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

		// Add the loading bar animation
		Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim"));
		anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
		loadingBar = new LoadingBarUi(anim);

		// Or if you only need a static bar, you can do
		// loadingBar = new Image(atlas.findRegion("loading-bar1"));

		// Add all the actors to the stage
		stage.addActor(screenBg);
		stage.addActor(loadingBar);
		stage.addActor(loadingBg);
		stage.addActor(loadingBarHidden);
		stage.addActor(loadingFrame);
		stage.addActor(logo);

		StaticAssets.loadAll();
		StaticAssets.context.getBean(Assets.class).initialize();

	}

	public void loadGame() {
		StaticAssets.context.getBean(ScreenFactory.class).setGame(game);
	}

	@Override
	public void resize(int width, int height) {
		// Set our screen to always be XXX x 480 in size
		width = 1920;
		height = 1080;

		// Make the background fill the screen
		screenBg.setSize(width, height);

		// Place the logo in the middle of the screen and 100 px up
		logo.setX((width - logo.getWidth()) / 2);
		logo.setY((height - logo.getHeight()) / 2 + 100);

		// Place the loading frame in the middle of the screen
		loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
		loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

		// Place the loading bar at the same spot as the frame, adjusted a few
		// px
		loadingBar.setX(loadingFrame.getX() + 15);
		loadingBar.setY(loadingFrame.getY() + 5);

		// Place the image that will hide the bar on top of the bar, adjusted a
		// few px
		loadingBarHidden.setX(loadingBar.getX() + 35);
		loadingBarHidden.setY(loadingBar.getY() - 3);
		// The start position and how far to move the hidden loading bar
		startX = loadingBarHidden.getX();
		endX = 440;

		// The rest of the hidden bar
		loadingBg.setSize(450, 50);
		loadingBg.setX(loadingBarHidden.getX() + 30);
		loadingBg.setY(loadingBarHidden.getY() + 3);
	}

	@Override
	public void render(float delta) {

		if (StaticAssets.assetManager.update()) {
			loadGame();
			StaticAssets.context.getBean(ScreenFactory.class).show(ScreenEnum.MENU);
		}

		// Interpolate the percentage to make it more smooth
		percent = Interpolation.linear.apply(percent, StaticAssets.assetManager.getProgress(), 0.1f);

		// Update positions (and size) to match the percentage
		loadingBarHidden.setX(startX + endX * percent);
		loadingBg.setX(loadingBarHidden.getX() + 30);
		loadingBg.setWidth(450 - 450 * percent);
		loadingBg.invalidate();

		// Show the loading screen
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {
		// Dispose the loading assets as we no longer need them

	}
}

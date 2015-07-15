package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;

public class MenuStage extends BaseOneLevelStage {
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private ImageButton[] button;

	public Stage makeStage() {
		super.makeStage();
		button = new ImageButton[4];
		Image logo = new Image(atlasUiAssets.getAtlasUiFile("title"));
		Texture texture = StaticAssets.backgroundTextureMap.get("main_background");
		Image background = new Image(texture);

		Table table = new Table(uiComponentAssets.getSkin());

		button[0] = new ImageButton(
				// FIXME 버튼하나 없음
				atlasUiAssets.getAtlasUiFile("button_start_after"), atlasUiAssets.getAtlasUiFile("button_start_after"));
		button[1] = new ImageButton(atlasUiAssets.getAtlasUiFile("button_option_before"),
				atlasUiAssets.getAtlasUiFile("button_option_after"));
		button[2] = new ImageButton(atlasUiAssets.getAtlasUiFile("button_credit_before"),
				atlasUiAssets.getAtlasUiFile("button_credit_after"));
		button[3] = new ImageButton(atlasUiAssets.getAtlasUiFile("button_extra_before"),
				atlasUiAssets.getAtlasUiFile("button_extra_after"));

		button[0].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.LOAD);
			}
		});
		button[1].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.OPTION);
			}
		});
		button[2].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.CREDIT);
			}
		});
		button[3].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.COLLETION);
			}
		});

		logo.setHeight((int) (0.4f * StaticAssets.windowHeight));
		logo.setWidth((int) (0.6f * StaticAssets.windowWidth));
		table.setFillParent(true);

		table.add(button[3]).height(0.35f * StaticAssets.windowHeight).width(0.3f * StaticAssets.windowWidth).expand()
				.top().left();
		table.add(button[2]).height(0.35f * StaticAssets.windowHeight).width(0.3f * StaticAssets.windowWidth).top()
				.right();
		table.row();
		table.add(button[0]).height(0.35f * StaticAssets.windowHeight).width(0.3f * StaticAssets.windowWidth).bottom()
				.left();
		table.add(button[1]).height(0.35f * StaticAssets.windowHeight).width(0.3f * StaticAssets.windowWidth).bottom()
				.right();
		logo.setPosition((int) (0.2f * StaticAssets.windowWidth), (int) (0.3f * StaticAssets.windowHeight));
		background.setSize(StaticAssets.windowWidth, StaticAssets.windowHeight);

		this.addActor(background);
		this.addActor(logo);
		this.addActor(table);

		return this;
	}

	public AtlasUiAssets getAtlasUiAssets() {
		return atlasUiAssets;
	}

	public void setAtlasUiAssets(AtlasUiAssets atlasUiAssets) {
		this.atlasUiAssets = atlasUiAssets;
	}

	public UiComponentAssets getUiComponentAssets() {
		return uiComponentAssets;
	}

	public void setUiComponentAssets(UiComponentAssets uiComponentAssets) {
		this.uiComponentAssets = uiComponentAssets;
	}
}

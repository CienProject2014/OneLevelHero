package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.Assets;

@Component
@Scope(value = "prototype")
public class MenuStage extends Stage {
	@Autowired
	private ScreenFactory screenFactory;
	private ImageButton[] button;

	public Stage makeStage() {
		button = new ImageButton[4];
		Texture texture = Assets.backgroundTextureMap.get("main_background");
		Image background = new Image(texture);

		Table table = new Table(Assets.skin);

		button[0] = new ImageButton(
		//FIXME 버튼하나 없음
				Assets.atlasUiMap.get("button_start_after"),
				Assets.atlasUiMap.get("button_start_after"));
		button[1] = new ImageButton(
				Assets.atlasUiMap.get("button_option_before"),
				Assets.atlasUiMap.get("button_option_after"));
		button[2] = new ImageButton(
				Assets.atlasUiMap.get("button_credit_before"),
				Assets.atlasUiMap.get("button_credit_after"));
		button[3] = new ImageButton(
				Assets.atlasUiMap.get("button_extra_before"),
				Assets.atlasUiMap.get("button_extra_after"));

		button[0].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.LOAD);
			}
		});
		button[1].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.OPTION);
			}
		});
		button[2].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.CREDIT);

			}
		});
		button[3].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.COLLETION);

			}
		});

		int windowHeight = (int) (Assets.windowHeight);
		int windowWidth = (int) (Assets.windowWidth);
		Image logo = new Image(Assets.atlasUiMap.get("title"));
		logo.setHeight((int) (0.4f * Assets.windowHeight));
		logo.setWidth((int) (0.6f * Assets.windowWidth));
		table.setFillParent(true);

		table.add(button[3]).height(0.35f * windowHeight)
				.width(0.3f * windowWidth).expand().top().left();
		table.add(button[2]).height(0.35f * windowHeight)
				.width(0.3f * windowWidth).top().right();
		table.row();
		table.add(button[0]).height(0.35f * windowHeight)
				.width(0.3f * windowWidth).bottom().left();
		table.add(button[1]).height(0.35f * windowHeight)
				.width(0.3f * windowWidth).bottom().right();

		logo.setPosition((int) (0.2f * Assets.windowWidth),
				(int) (0.3f * Assets.windowHeight));
		background.setSize(windowWidth, windowHeight);

		this.addActor(background);
		this.addActor(logo);
		this.addActor(table);
		return this;
	}
}

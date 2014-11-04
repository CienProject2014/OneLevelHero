package com.mygdx.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;

public class MenuStage extends Stage{
	
	private ImageButton[] button;
	
	public MenuStage(String name){
	//이름에 따라 다른 stage생서할 예정
			makeMainStage();
		
	}
	
	private void makeMainStage(){
		button = new ImageButton[4];
		Texture texture = Assets.main_background;
		Image background = new Image(texture);
		
		Table table = new Table(Assets.skin);

		button[0] = new ImageButton(Assets.start_before, Assets.start_after);
		button[1] = new ImageButton(Assets.option_before,
				Assets.option_after);
		button[2] = new ImageButton(Assets.credit_before,
				Assets.credit_after);
		button[3] = new ImageButton(Assets.extra_before, Assets.extra_after);

		button[0].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.LOAD);
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
				new ScreenController(ScreenEnum.OPTION);
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
				new ScreenController(ScreenEnum.CREDIT);

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
				new ScreenController(ScreenEnum.COLLETION);

			}
		});

		int realheight = (int) (Assets.realHeight);
		int realwidth = (int) (Assets.realWidth);

		Assets.logo.setHeight((int) (0.4f * Assets.realHeight));
		Assets.logo.setWidth((int) (0.6f * Assets.realWidth));
		table.setFillParent(true);

		table.add(button[3]).height(0.35f * realheight)
				.width(0.3f * realwidth).expand().top().left();
		table.add(button[2]).height(0.35f * realheight)
				.width(0.3f * realwidth).top().right();
		table.row();
		table.add(button[0]).height(0.35f * realheight)
				.width(0.3f * realwidth).bottom().left();
		table.add(button[1]).height(0.35f * realheight)
				.width(0.3f * realwidth).bottom().right();


		Assets.logo.setPosition((int) (0.2f * Assets.realWidth),
				(int) (0.3f * Assets.realHeight));

		background.setSize(realwidth, realheight);

		this.addActor(background);
		this.addActor(Assets.logo);
		this.addActor(table);
		
	}

}

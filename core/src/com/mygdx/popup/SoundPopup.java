package com.mygdx.popup;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.VolumeManager;
import com.mygdx.state.Assets;
import com.mygdx.state.StaticAssets;

public class SoundPopup extends Dialog {
	@Autowired
	private Assets assets;
	@Autowired
	private MusicManager musicManager;
	private Stage scenestage;

	@Autowired
	public SoundPopup(String title, Stage stage) {
		super(title, StaticAssets.skin);
		scenestage = stage;
	}

	private void initialize() {
		float width = 0.6f;
		float height = 0.5f;
		float centerx = 0.5f - width / 2;
		float centery = 0.5f - height / 2;
		getButtonTable();
		padTop(60); // set padding on top of the dialog title
		getContentTable().defaults(); // set buttons height
		setResizable(false);

		TextButtonStyle style = new TextButtonStyle(
				assets.atlasUiMap.get("menu_button_up"),
				assets.atlasUiMap.get("menu_button_down"),
				assets.atlasUiMap.get("menu_button_toggle"), assets.font);

		text("배경음\n효과음");

		Viewport vp = scenestage.getViewport();

		setWidth((int) (width * vp.getViewportWidth())); // 가로 크기 세팅
		setHeight((int) (height * vp.getViewportHeight())); // 세로 크기 세팅

		setPosition((int) (centerx * vp.getViewportWidth()),
				(int) (centery * vp.getViewportHeight()));

		setMovable(true); // 드래그로 이동가능

		// button("HelloWorld", new InputListener());
		// 다이얼로그 구현
		// 다이얼로그조절

		final Slider volume = new Slider(0f, 100f, 1f, false, assets.skin);
		volume.setValue(assets.musicVolume * 100);
		String volumeLabel = String.valueOf(assets.musicVolume * 100);
		final Label volumeValue = new Label(volumeLabel, assets.skin);
		Table table = new Table();
		final Slider pan = new Slider(-1f, 1f, 0.1f, false, assets.skin);
		pan.setValue(0);
		final Label panValue = new Label("0.0", assets.skin);

		table.add(volume);
		table.add(volumeValue);
		table.row();

		table.add(pan);
		table.add(panValue);

		// table.setFillParent(true);
		table.top();

		volume.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {

				// sound.setVolume(soundId, volume.getValue());
				VolumeManager.musicVolume = volume.getValue() / 100;
				volumeValue.setText("" + assets.musicVolume * 100);
				musicManager.getMusic().setVolume(assets.musicVolume);

			}
		});
		pan.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {

				panValue.setText("" + pan.getValue());
			}
		});

		getContentTable().add(table);
		button("완료", "remove", style);

	}

	public float getPrefWidth() {

		return 480f;
	}

	public float getPrefHeight() {

		return 240f;
	}

	@Override
	public Table getContentTable() {
		// TODO Auto-generated method stub
		return super.getContentTable();
	}

	@Override
	public Table getButtonTable() {
		// TODO Auto-generated method stub
		return super.getButtonTable();
	}

	@Override
	public Dialog text(String text) {
		super.text(new Label(text, assets.skin));
		return this;

	}

	@Override
	public Dialog text(String text, LabelStyle labelStyle) {
		// TODO Auto-generated method stub
		return super.text(text, labelStyle);
	}

	@Override
	public Dialog text(Label label) {
		// TODO Auto-generated method stub
		return super.text(label);
	}

	@Override
	public Dialog button(String text) {
		// TODO Auto-generated method stub
		return super.button(text);
	}

	@Override
	public Dialog button(String text, Object object) {
		// TODO Auto-generated method stub
		return super.button(text, object);
	}

	@Override
	public Dialog button(String text, Object object, TextButtonStyle buttonStyle) {
		// TODO Auto-generated method stub

		return super.button(text, object, buttonStyle);
	}

	@Override
	public Dialog button(Button button) {
		// TODO Auto-generated method stub
		return super.button(button);
	}

	@Override
	public Dialog button(Button button, Object object) {
		// TODO Auto-generated method stub
		return super.button(button, object);
	}

	@Override
	public Dialog show(Stage stage) {
		// TODO Auto-generated method stub
		return super.show(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		remove();
	}

	@Override
	public void setObject(Actor actor, Object object) {
		// TODO Auto-generated method stub
		super.setObject(actor, object);
	}

	@Override
	public Dialog key(int keycode, Object object) {
		// TODO Auto-generated method stub
		return super.key(keycode, object);
	}

	@Override
	protected void result(Object object) {
		// TODO Auto-generated method stub

		if (object.equals("remove")) {

			remove();
		}
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		super.cancel();
	}

}

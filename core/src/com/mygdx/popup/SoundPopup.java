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
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.VolumeManager;

public class SoundPopup extends Dialog {
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private SoundManager soundManager;
	private Stage scenestage;

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

		TextButtonStyle style = new TextButtonStyle(atlasUiAssets.getAtlasUiFile("menu_button_up"),
				atlasUiAssets.getAtlasUiFile("menu_button_down"), atlasUiAssets.getAtlasUiFile("menu_button_toggle"),
				uiComponentAssets.getFont());

		text("배경음\n효과음");

		setWidth((int) (width * StaticAssets.windowWidth)); // 가로 크기 세팅
		setHeight((int) (height * StaticAssets.windowHeight)); // 세로 크기 세팅

		setPosition((int) (centerx * StaticAssets.windowWidth), (int) (centery * StaticAssets.windowHeight));

		setMovable(true); // 드래그로 이동가능

		// button("HelloWorld", new InputListener());
		// 다이얼로그 구현
		// 다이얼로그조절

		final Slider volume = new Slider(0f, 100f, 1f, false, uiComponentAssets.getSkin());
		volume.setValue(musicManager.getMusicVolume() * 100);
		String volumeLabel = String.valueOf(musicManager.getMusicVolume() * 100);
		final Label volumeValue = new Label(volumeLabel, uiComponentAssets.getSkin());
		Table table = new Table();
		final Slider pan = new Slider(-1f, 1f, 0.1f, false, uiComponentAssets.getSkin());
		pan.setValue(0);
		final Label panValue = new Label("0.0", uiComponentAssets.getSkin());

		table.add(volume);
		table.add(volumeValue);
		table.row();

		table.add(pan);
		table.add(panValue);

		// table.setFillParent(true);
		table.top();

		volume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// sound.setVolume(soundId, volume.getValue());
				VolumeManager.musicVolume = volume.getValue() / 100;
				volumeValue.setText("" + musicManager.getMusicVolume() * 100);
				musicManager.getMusic().setVolume(musicManager.getMusicVolume());
			}
		});
		pan.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				panValue.setText("" + pan.getValue());
			}
		});

		getContentTable().add(table);
		button("완료", "remove", style);
	}

	@Override
	public float getPrefWidth() {
		return 480f;
	}

	@Override
	public float getPrefHeight() {
		return 240f;
	}

	@Override
	public Table getContentTable() {
		return super.getContentTable();
	}

	@Override
	public Table getButtonTable() {
		return super.getButtonTable();
	}

	@Override
	public Dialog text(String text) {
		super.text(new Label(text, uiComponentAssets.getSkin()));
		return this;
	}

	@Override
	public Dialog text(String text, LabelStyle labelStyle) {
		return super.text(text, labelStyle);
	}

	@Override
	public Dialog text(Label label) {
		return super.text(label);
	}

	@Override
	public Dialog button(String text) {
		return super.button(text);
	}

	@Override
	public Dialog button(String text, Object object) {
		return super.button(text, object);
	}

	@Override
	public Dialog button(String text, Object object, TextButtonStyle buttonStyle) {
		return super.button(text, object, buttonStyle);
	}

	@Override
	public Dialog button(Button button) {
		return super.button(button);
	}

	@Override
	public Dialog button(Button button, Object object) {
		return super.button(button, object);
	}

	@Override
	public Dialog show(Stage stage) {
		return super.show(stage);
	}

	@Override
	public void hide() {
		remove();
	}

	@Override
	public void setObject(Actor actor, Object object) {
		super.setObject(actor, object);
	}

	@Override
	public Dialog key(int keycode, Object object) {
		return super.key(keycode, object);
	}

	@Override
	protected void result(Object object) {
		if (object.equals("remove"))
			remove();
	}

	@Override
	public void cancel() {
		super.cancel();
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

	public MusicManager getMusicManager() {
		return musicManager;
	}

	public void setMusicManager(MusicManager musicManager) {
		this.musicManager = musicManager;
	}

}

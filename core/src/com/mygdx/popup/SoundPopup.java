package com.mygdx.popup;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.mygdx.resource.Assets;

public class SoundPopup extends Dialog{

	Skin skin = Assets.skin;

	public SoundPopup(String title) {
		super(title, Assets.skin);
		initialize();  
	}

	private void initialize() {  
		padTop(60); // set padding on top of the dialog title  
		getButtonTable().defaults().height(60); // set buttons height  
		setResizable(false);
		setWidth(600); //가로 크기 세팅
		setHeight(400); //세로 크기 세팅
		setMovable(true); //드래그로 이동가능  
		//Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/Test.mp3"));
		//long id = sound.play(1.0f);

		//button("HelloWorld", new InputListener());
		//다이얼로그 구현
		//다이얼로그조절

		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		final Slider volume = new Slider(0.1f, 1, 0.1f, false, skin);
		volume.setValue(1);
		final Label volumeValue = new Label("1.0", skin);
		Table table = new Table();
		final Slider pan = new Slider(-1f, 1f, 0.1f, false, skin);
		pan.setValue(0);
		final Label panValue = new Label("0.0", skin);
		table.setFillParent(true);

		table.add(new Label("Volume", skin));
		table.add(volume);
		table.add(volumeValue);
		table.row();

		
/*
		volume.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				sound.setVolume(soundId, volume.getValue());
				volumeValue.setText("" + volume.getValue());
			}
		});
		pan.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				sound.setPan(soundId, pan.getValue(), volume.getValue());
				panValue.setText("" + pan.getValue());
			}
		});
*/

		this.add(table);
	}



	public SoundPopup button(String buttonText, InputListener listener) {  
		TextButton button = new TextButton(buttonText, Assets.skin);  
		button.addListener(listener);  
		button(button);  
		return this;  
	}  

	public float getPrefWidth() {  
		// force dialog width  
		return 480f;  
	}  

	public float getPrefHeight() {  
		// force dialog height  
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
		super.text(new Label(text, Assets.skin));  
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
		super.hide();
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
		super.result(object);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		super.cancel();
	}




}

package com.mygdx.popup;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.resource.Assets;

public class SoundPopup extends Dialog{
	
	public SoundPopup(String title) {
		super(title, Assets.skin);
		initialize();  
	}
	
	 private void initialize() {  
         padTop(60); // set padding on top of the dialog title  
         getButtonTable().defaults().height(60); // set buttons height  
         setModal(true);  
         setMovable(false);  
         setResizable(false);  
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

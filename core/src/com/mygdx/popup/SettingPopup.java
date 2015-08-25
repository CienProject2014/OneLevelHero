package com.mygdx.popup;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.GameEndListener;
import com.mygdx.listener.GoTitleListener;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.listener.LoadListener;
import com.mygdx.manager.MusicManager;

public class SettingPopup extends Dialog {
	private ImageButton loadButton, soundButton, titleButton, endButton, closeButton;
	private HashMap<String, Float> uiConstantsMap;
	private ConstantsAssets constantsAssets;
	private ListenerFactory listenerFactory;
	private AtlasUiAssets atlasUiAssets;
	private MusicManager musicManager;
	private SoundPopup soundPopup;

	public SettingPopup(String title, Skin skin) {
		super(title, skin);
	}

	public SettingPopup() {
		this("", StaticAssets.skin);
	}

	public void initialize() {
		makeButton();
		makeListener();
		makeButtonTable();
		setCenterPosition(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f);
		setSize(430, 700);
		setModal(false);
		setResizable(false);
		setVisible(false);
	}

	private void makeListener() {
		LoadListener loadListener = listenerFactory.getLoadListener();
		GoTitleListener goTitleListener = listenerFactory.getGoTitleListener();
		GameEndListener gameEndListener = listenerFactory.getGameEndListener();
		loadButton.addListener(loadListener);
		soundButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundPopup.setVisible(true);
			}
		});
		titleButton.addListener(goTitleListener);
		endButton.addListener(gameEndListener);
		closeButton.addListener(new HidingClickListener(this));
	}

	private void makeButtonTable() {
		getButtonTable().add(loadButton).height(uiConstantsMap.get("buttonHeight")).padTop(40).padBottom(30);
		getButtonTable().row();
		getButtonTable().add(soundButton).height(uiConstantsMap.get("buttonHeight")).padBottom(30);
		getButtonTable().row();
		getButtonTable().add(titleButton).height(uiConstantsMap.get("buttonHeight")).padBottom(30);
		getButtonTable().row();
		getButtonTable().add(endButton).height(uiConstantsMap.get("buttonHeight")).padBottom(30);
		getButtonTable().row();
		getButtonTable().add(closeButton).height(uiConstantsMap.get("buttonHeight")).padBottom(40);
		getButtonTable().setBackground(atlasUiAssets.getAtlasUiFile("popupui_popup01"));
	}

	private void makeButton() {

		uiConstantsMap = constantsAssets.getUiConstants("GameObjectPopup");
		loadButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_load"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_load"));
		soundButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_sound"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_sound"));
		titleButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_title"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_title"));
		endButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_end"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_end"));
		closeButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_closed"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_closed"));
	}

	public void setAtlasUiAssets(AtlasUiAssets atlasUiAssets) {
		this.atlasUiAssets = atlasUiAssets;
	}

	public ListenerFactory getListenerFactory() {
		return listenerFactory;
	}

	public void setListenerFactory(ListenerFactory listenerFactory) {
		this.listenerFactory = listenerFactory;
	}

	public void setConstantsAssets(ConstantsAssets constantsAssets) {
		this.constantsAssets = constantsAssets;
	}

	public MusicManager getMusicManager() {
		return musicManager;
	}

	public void setMusicManager(MusicManager musicManager) {
		this.musicManager = musicManager;
	}

	public SoundPopup getSoundPopup() {
		return soundPopup;
	}

	public void setSoundPopup(SoundPopup soundPopup) {
		this.soundPopup = soundPopup;
	}
}

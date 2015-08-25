package com.mygdx.popup;

import java.util.HashMap;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.HidingClickListener;
import com.mygdx.manager.MusicManager;

public class SoundPopup extends Dialog {

	private Drawable background;
	private Drawable button;
	private HashMap<String, Float> uiConstantsMap;
	private ConstantsAssets constantsAssets;
	private ListenerFactory listenerFactory;
	private AtlasUiAssets atlasUiAssets;
	private ImageButton okayButton, closeButton;
	private ImageButton soundOn, bgmOn;
	private MusicManager musicManager;
	private Sound sound;

	public SoundPopup(String title, Skin skin) {
		super(title, skin);
	}

	public SoundPopup() {
		this("", StaticAssets.skin);
	}

	public void initialize() {
		getContentTable().clear();
		getButtonTable().clear();
		makeButton();
		SliderStyle sliderStyle = new SliderStyle(background, button);
		final Slider volume = new Slider(0f, 100f, 1f, false, sliderStyle);
		volume.setValue(musicManager.getMusic().getVolume() * 100);
		final Slider bgm = new Slider(0f, 100f, 1f, false, sliderStyle);
		bgm.setValue(50);

		volume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				musicManager.getMusic().setVolume(volume.getValue() / 100);
			}
		});
		bgm.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// VolumeManager.bgmVolume = bgm.getValue() / 100;
				// sound.setVolume(soundId, VolumeManager.bgmVolume);
			}
		});

		closeButton.addListener(new HidingClickListener(this));
		okayButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				musicManager.setVolume(volume.getValue() / 100);
			}
		});

		getContentTable().align(Align.top);

		Table soundTable = new Table();
		soundTable.add(volume).width(460).height(6);
		soundTable.add(soundOn).padLeft(25);
		getContentTable().add(soundTable).padTop(66).padLeft(181);
		getContentTable().row();

		Table bgmTable = new Table();
		bgmTable.add(bgm).width(460).height(6);
		bgmTable.add(bgmOn).padLeft(25);
		getContentTable().add(bgmTable).padTop(34).padLeft(181);

		getButtonTable().bottom();
		getButtonTable().add(okayButton).height(uiConstantsMap.get("buttonHeight"))
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		getButtonTable().add(closeButton).padLeft(uiConstantsMap.get("closeButtonPadLeft")).height(90)
				.padBottom(uiConstantsMap.get("buttonPadBottom"));
		setBackground(atlasUiAssets.getAtlasUiFile("popupui_sound_base"));
		setCenterPosition(StaticAssets.BASE_WINDOW_WIDTH / 2f, StaticAssets.BASE_WINDOW_HEIGHT / 2f);
		setSize(860, 480);
		setResizable(false);
		setVisible(false);
	}

	private void makeButton() {
		uiConstantsMap = constantsAssets.getUiConstants("GameObjectPopup");
		background = (Drawable) new TextureRegionDrawable(atlasUiAssets.getAtlasUiFile("popupui_sound_bar"));
		button = (Drawable) new TextureRegionDrawable(atlasUiAssets.getAtlasUiFile("popupui_sound_button"));
		okayButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_accept"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_accept"));
		closeButton = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_button_cancel"),
				atlasUiAssets.getAtlasUiFile("popupui_acbutton_cancel"));
		soundOn = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_sound_on"),
				atlasUiAssets.getAtlasUiFile("popupui_sound_off"));
		bgmOn = new ImageButton(atlasUiAssets.getAtlasUiFile("popupui_sound_acon"),
				atlasUiAssets.getAtlasUiFile("popupui_sound_acoff"));
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

}

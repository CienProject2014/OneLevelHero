package com.mygdx.assets;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.model.jsonModel.StringFile;

public class UiComponentAssets implements FileAssetsInitializable {
	private Skin skin;
	private TextureAtlas items;
	private TextureRegionDrawable[] chatButton;
	private TextureRegionDrawable eventButton;
	private Texture splash;
	private Image logo;
	private BitmapFont font;

	public void set(Map<String, StringFile> filePathMap) {
		font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));
		TextureAtlas textureAtlas = new TextureAtlas("skin/chatbutton.pack");
		chatButton = new TextureRegionDrawable[6];
		for (int i = 0; i < 6; i++)
			chatButton[i] = new TextureRegionDrawable(
					textureAtlas.findRegion("chatbutton" + (i + 1)));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		items = new TextureAtlas("texture/items/items.pack");
		splash = new Texture(Gdx.files.internal("texture/splash.png"));
		setEventButton(new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("texture/ui/chat/talk_select.png")))));
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public TextureAtlas getItems() {
		return items;
	}

	public void setItems(TextureAtlas items) {
		this.items = items;
	}

	public TextureRegionDrawable[] getChatButton() {
		return chatButton;
	}

	public void setChatButton(TextureRegionDrawable[] chatButton) {
		this.chatButton = chatButton;
	}

	public Texture getSplash() {
		return splash;
	}

	public void setSplash(Texture splash) {
		this.splash = splash;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public TextureRegionDrawable getEventButton() {
		return eventButton;
	}

	public void setEventButton(TextureRegionDrawable eventButton) {
		this.eventButton = eventButton;
	}
}

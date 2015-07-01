package com.mygdx.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.TextureFile;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class StaticAssets {
	public static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	public static Map<String, Texture> characterTextureMap = new HashMap<String, Texture>();
	public static Map<String, Texture> monsterTextureMap = new HashMap<String, Texture>();
	public static Map<String, Texture> backgroundTextureMap = new HashMap<String, Texture>();
	public static TextureRegionDrawable bartexture_bg = new TextureRegionDrawable(
			new TextureRegion(new Texture(
					Gdx.files.internal("texture/bgcolour.png")), 50, 50));
	public static ProgressBarStyle barstyle_hp = new ProgressBarStyle(
			bartexture_bg, new TextureRegionDrawable(new TextureRegion(
					new Texture(Gdx.files.internal("texture/hpcolour.png")),
					50, 50)));
	public static ProgressBarStyle barstyle_exp = new ProgressBarStyle(
			bartexture_bg, new TextureRegionDrawable(new TextureRegion(
					new Texture(Gdx.files.internal("texture/expcolour.png")),
					50, 50)));
	public static ProgressBarStyle barstyle_turn = new ProgressBarStyle(
			bartexture_bg, new TextureRegionDrawable(new TextureRegion(
					new Texture(Gdx.files.internal("texture/turncolour.png")),
					50, 50)));
	public static TextureAtlas items = new TextureAtlas(
			"texture/items/items.pack");
	public static float windowWidth;
	public static float windowHeight;

	public static ResourceManager rm = new ResourceManager();

	public static void loadAll() {
		Gdx.app.debug("StaticAssets", "StaticAssets.loadAll() called");
		loadSize(new Stage());
		loadTexture();

		// Loading all assets/resources into memory
		rm.initAllResources();
	}

	public static void loadSize(Stage stage) {
		windowWidth = stage.getWidth();
		windowHeight = stage.getHeight();
	}

	public static void loadTexture() {
		Map<String, JsonStringFile> filePathMap = new HashMap<String, JsonStringFile>();
		filePathMap = JsonParser.parseMap(JsonStringFile.class, Gdx.files
				.internal("data/load/file_path.json").readString());
		Map<String, TextureFile> characterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.CHARACTER_FILE_PATH.toString())
						.getFile());
		for (Entry<String, TextureFile> entry : characterFileMap.entrySet())
			characterTextureMap.put(entry.getKey(), entry.getValue().getFile());

		Map<String, TextureFile> monsterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.MONSTER_FILE_PATH.toString())
						.getFile());
		for (Entry<String, TextureFile> entry : monsterFileMap.entrySet())
			monsterTextureMap.put(entry.getKey(), entry.getValue().getFile());

		Map<String, TextureFile> backgroundFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.BACKGROUND_FILE_PATH.toString())
						.getFile());
		for (Entry<String, TextureFile> entry : backgroundFileMap.entrySet())
			backgroundTextureMap
					.put(entry.getKey(), entry.getValue().getFile());
	}
}

package com.mygdx.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.TextureFile;

public class TestStaticAssets {
	public static Skin skin = new Skin(
			Gdx.files.internal("../android/assets/skin/uiskin.json"));
	public static Map<String, Texture> characterTextureMap = new HashMap<>();
	public static Map<String, Texture> monsterTextureMap = new HashMap<>();
	public static Map<String, Texture> backgroundTextureMap = new HashMap<>();
	public static TextureAtlas items = new TextureAtlas(
			"../android/assets/texture/items/items.pack");
	public static float windowWidth;
	public static float windowHeight;

	public static void loadAll() {
		Gdx.app.debug("StaticAssets", "StaticAssets.loadAll() called");
		loadTexture();
	}

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
	}

	public static void loadTexture() {
		Map<String, JsonStringFile> filePathMap = new HashMap<>();
		filePathMap = JsonParser.parseMap(JsonStringFile.class, Gdx.files
				.internal("../android/assets/data/load/file_path.json")
				.readString());
		Map<String, TextureFile> characterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.CHARACTER_FILE_PATH.toString())
						.getTestFile());
		for (Entry<String, TextureFile> entry : characterFileMap.entrySet()) {
			characterTextureMap.put(entry.getKey(), entry.getValue()
					.getTestFile());
		}

		Map<String, TextureFile> monsterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.MONSTER_FILE_PATH.toString())
						.getTestFile());
		for (Entry<String, TextureFile> entry : monsterFileMap.entrySet()) {
			monsterTextureMap.put(entry.getKey(), entry.getValue()
					.getTestFile());
		}

		Map<String, TextureFile> backgroundFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.BACKGROUND_FILE_PATH.toString())
						.getTestFile());
		for (Entry<String, TextureFile> entry : backgroundFileMap.entrySet()) {
			backgroundTextureMap.put(entry.getKey(), entry.getValue()
					.getTestFile());
		}
	}
}

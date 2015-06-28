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
import com.mygdx.model.AnimationSheet;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.SheetFile;
import com.mygdx.model.TextureFile;

public class StaticAssets {
	public static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	public static Map<String, Texture> characterTextureMap = new HashMap<String, Texture>();
	public static Map<String, Texture> monsterTextureMap = new HashMap<String, Texture>();
	public static Map<String, Texture> backgroundTextureMap = new HashMap<String, Texture>();
	public static Map<String, AnimationSheet> animationSheetMap = new HashMap<String, AnimationSheet>();
	public static TextureAtlas items = new TextureAtlas(
			"texture/items/items.pack");
	public static float windowWidth;
	public static float windowHeight;

	public static void loadAll() {
		Gdx.app.debug("StaticAssets", "StaticAssets.loadAll() called");
		loadSize(new Stage());
		loadTexture();
	}

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
	}

	public static void loadTexture() {
		Map<String, JsonStringFile> filePathMap = new HashMap<String, JsonStringFile>();
		filePathMap = JsonParser.parseMap(JsonStringFile.class, Gdx.files
				.internal("data/load/file_path.json").readString());

		Map<String, TextureFile> characterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.CHARACTER_FILE_PATH.toString())
						.getFile());
		for (Entry<String, TextureFile> entry : characterFileMap.entrySet()) {
			characterTextureMap.put(entry.getKey(), entry.getValue().getFile());
		}

		Map<String, TextureFile> monsterFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.MONSTER_FILE_PATH.toString())
						.getFile());
		for (Entry<String, TextureFile> entry : monsterFileMap.entrySet()) {
			monsterTextureMap.put(entry.getKey(), entry.getValue().getFile());
		}

		Map<String, TextureFile> backgroundFileMap = JsonParser.parseMap(
				TextureFile.class,
				filePathMap.get(JsonEnum.BACKGROUND_FILE_PATH.toString())
						.getFile());
		for (Entry<String, TextureFile> entry : backgroundFileMap.entrySet()) {
			backgroundTextureMap
					.put(entry.getKey(), entry.getValue().getFile());
		}

		Map<String, SheetFile> animationSheetFileMap = JsonParser.parseMap(
				SheetFile.class,
				filePathMap.get(JsonEnum.ANIMATION_SHEET_FILE_PATH.toString())
						.getFile());
		for(Entry<String, SheetFile> entry : animationSheetFileMap.entrySet()) {
			AnimationSheet sheet = new AnimationSheet(entry.getValue());
			animationSheetMap.put(entry.getKey(), sheet);
		}
	}
}

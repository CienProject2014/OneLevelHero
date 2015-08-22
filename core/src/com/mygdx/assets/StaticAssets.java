package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.enums.JsonEnum;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class StaticAssets {
	public static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	public static Map<String, StringFile> filePathMap;
	public static Map<String, HashMap<String, Float>> uiConstantsMap = new HashMap<>();

	public static final float BASE_WINDOW_WIDTH = 1920;
	public static final float BASE_WINDOW_HEIGHT = 1080;
	public static float windowWidth;
	public static float windowHeight;
	public static float resolutionFactor;

	public static void loadAll() {
		filePathMap = JsonParser.parseMap(StringFile.class,
				Gdx.files.internal("data/load/file_path.json").readString());
		loadSize(new Stage());
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.WHITE);
			pixmap.fill();
			skin.add("WHITE", new Texture(pixmap));
		}
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.RED);
			pixmap.fill();
			skin.add("RED", new Texture(pixmap));
		}
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.GREEN);
			pixmap.fill();
			skin.add("GREEN", new Texture(pixmap));
		}

	}

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getWorldWidth();
		windowHeight = vp.getWorldHeight();
		resolutionFactor = windowWidth / BASE_WINDOW_WIDTH;
		Map<String, HashMap> stageMap = JsonParser.parseMap(HashMap.class,
				filePathMap.get(JsonEnum.UI_CONSTANTS.toString()).loadFile());
		for (Entry<String, HashMap> stageEntry : stageMap.entrySet()) {
			uiConstantsMap.put(stageEntry.getKey(), stageEntry.getValue());
		}
	}
}

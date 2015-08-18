package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.enums.JsonEnum;
import com.mygdx.model.battle.FrameSheet;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class StaticAssets {
	public static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	public static Map<String, StringFile> filePathMap;
	public static Map<String, String> textureMap = new HashMap<>();
	public static Map<String, FrameSheet> animationSheetMap;
	public static Map<String, HashMap<String, Float>> uiConstantsMap = new HashMap<>();
	public static AssetManager assetManager = new AssetManager();;
	public static ProgressBarStyle barstyle_hp;
	public static ProgressBarStyle barstyle_turn;
	public static final float BASE_WINDOW_WIDTH = 1920;
	public static final float BASE_WINDOW_HEIGHT = 1080;
	public static float windowWidth;
	public static float windowHeight;
	public static float resolutionFactor; // (기준해상도/현재해상도)

	public static ResourceManager rm = new ResourceManager();

	public static void loadAll() {
		Gdx.app.debug("StaticAssets", "StaticAssets.loadAll() called");
		filePathMap = JsonParser.parseMap(StringFile.class,
				Gdx.files.internal("data/load/file_path.json").readString());

		loadSize(new Stage());
		loadTexture();

		// Loading all assets/resources into memory
		rm.initAllResources();

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

		barstyle_hp = new ProgressBarStyle(skin.getDrawable("WHITE"), skin.getDrawable("RED"));
		barstyle_turn = new ProgressBarStyle(skin.getDrawable("WHITE"), skin.getDrawable("GREEN"));
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

	public static void loadTexture() {
		animationSheetMap = JsonParser.parseMap(FrameSheet.class,
				filePathMap.get(JsonEnum.ANIMATION_SHEET_FILE_PATH.toString()).loadFile());

		DirectoryTextureMapper(textureMap, "texture");
	}

	public static void DirectoryTextureMapper(Map<String, String> map, String path) {
		FileHandle fh;

		if (Gdx.app.getType() == ApplicationType.Android) {
			fh = Gdx.files.internal(path);
		} else { // ApplicationType.Desktop ..
			fh = Gdx.files.internal("./bin/" + path);
		}

		DirectoryTextureMapperRecursive(map, fh);
	}

	public static void DirectoryTextureMapperRecursive(Map<String, String> map, FileHandle fh) {
		if (fh.isDirectory()) {
			FileHandle[] fhs = fh.list();

			for (FileHandle e : fhs) {
				DirectoryTextureMapperRecursive(map, e);
			}
		} else if (!map.containsKey(fh.nameWithoutExtension()) && fh.extension().matches("^(png|jpg)")) {
			map.put(fh.nameWithoutExtension(), fh.path());
			assetManager.load(fh.path(), Texture.class);
		}
	}
}

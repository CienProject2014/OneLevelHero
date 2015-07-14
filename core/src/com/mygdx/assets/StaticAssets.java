package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.JsonParser;
import com.mygdx.model.FrameSheet;
import com.mygdx.model.JsonStringFile;
import com.mygdx.model.TextureFile;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class StaticAssets {
	public static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	public static Map<String, JsonStringFile> filePathMap;
	public static Map<String, Texture> characterTextureMap = new HashMap<String, Texture>();
	public static Map<String, Texture> monsterTextureMap = new HashMap<String, Texture>();
	public static Map<String, Texture> backgroundTextureMap = new HashMap<String, Texture>();
	public static Map<String, FrameSheet> animationSheetMap;
	public static Map<String, Texture> battleUiTextureMap = new HashMap<String, Texture>();
	public static Map<String, HashMap<String, Float>> uiConstantsMap = new HashMap<String, HashMap<String, Float>>();

	public static TextureRegionDrawable bartexture_bg = new TextureRegionDrawable(
			new TextureRegion(new Texture(Gdx.files.internal("texture/bgcolour.png")), 50, 22));
	public static ProgressBarStyle barstyle_hp = new ProgressBarStyle(bartexture_bg, new TextureRegionDrawable(
			new TextureRegion(new Texture(Gdx.files.internal("texture/hpcolour.png")), 0, 22)));
	public static ProgressBarStyle barstyle_turn = new ProgressBarStyle(bartexture_bg, new TextureRegionDrawable(
			new TextureRegion(new Texture(Gdx.files.internal("texture/turncolour.png")), 0, 22)));

	public static TextureAtlas items = new TextureAtlas("texture/items/items.pack");
	public static final float BASE_WINDOW_WINDTH = 1920;
	public static final float BASE_WINDOW_HEIGHT = 1080;
	public static float windowWidth;
	public static float windowHeight;
	public static float resolutionFactor; // (기준해상도/현재해상도)

	public static ResourceManager rm = new ResourceManager();

	public static void loadAll() {
		Gdx.app.debug("StaticAssets", "StaticAssets.loadAll() called");

		filePathMap = JsonParser.parseMap(JsonStringFile.class,
				Gdx.files.internal("data/load/file_path.json").readString());

		loadSize(new Stage());
		loadTexture();

		// Loading all assets/resources into memory
		rm.initAllResources();
	}

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getViewportWidth();
		windowHeight = vp.getViewportHeight();
		resolutionFactor = windowWidth / BASE_WINDOW_WINDTH;

		Map<String, HashMap> stageMap = JsonParser.parseMap(HashMap.class,
				filePathMap.get("ui_constants_file").loadFile());
		for (Entry<String, HashMap> stageEntry : stageMap.entrySet()) {
			// HashMap<String, Float> uiMap = stageEntry.getValue();
			// for (Entry<String, Float> uiEntry : uiMap.entrySet()) {
			// uiEntry.setValue(uiEntry.getValue() * resolutionFactor);
			// }
			// uiConstantsMap.put(stageEntry.getKey(), stageEntry.getValue());
			uiConstantsMap.put(stageEntry.getKey(), stageEntry.getValue());
		}

	}

	public static void loadTexture() {
		Map<String, TextureFile> characterFileMap = JsonParser.parseMap(TextureFile.class,
				filePathMap.get(JsonEnum.CHARACTER_FILE_PATH.toString()).loadFile());
		for (Entry<String, TextureFile> entry : characterFileMap.entrySet()) {
			characterTextureMap.put(entry.getKey(), entry.getValue().loadFile());
		}

		Map<String, TextureFile> monsterFileMap = JsonParser.parseMap(TextureFile.class,
				filePathMap.get(JsonEnum.MONSTER_FILE_PATH.toString()).loadFile());
		for (Entry<String, TextureFile> entry : monsterFileMap.entrySet()) {
			monsterTextureMap.put(entry.getKey(), entry.getValue().loadFile());
		}

		Map<String, TextureFile> backgroundFileMap = JsonParser.parseMap(TextureFile.class,
				filePathMap.get(JsonEnum.BACKGROUND_FILE_PATH.toString()).loadFile());
		for (Entry<String, TextureFile> entry : backgroundFileMap.entrySet()) {
			backgroundTextureMap.put(entry.getKey(), entry.getValue().loadFile());
		}

		animationSheetMap = JsonParser.parseMap(FrameSheet.class,
				filePathMap.get(JsonEnum.ANIMATION_SHEET_FILE_PATH.toString()).loadFile());

		FileHandle fh;
		FileHandle[] fhs;

		if (Gdx.app.getType() == ApplicationType.Android) {
			fh = Gdx.files.internal("texture/battle");
		} else {
			// ApplicationType.Desktop ..
			fh = Gdx.files.internal("./bin/texture/battle");
		}

		if (fh.isDirectory()) {
			fhs = fh.list();

			for (int i = 0; i < fhs.length; i++) {
				Gdx.app.log("StaticAssets", fhs[i].name());
				battleUiTextureMap.put(fhs[i].nameWithoutExtension(), new Texture(fhs[i].path()));
			}
		} else {
			fhs = null;
		}

	}
}

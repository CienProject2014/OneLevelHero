package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.enums.JsonEnum;
import com.mygdx.model.battle.FrameSheet;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class TextureAssets {
	public AssetManager assetManager = new AssetManager();;
	public Map<String, StringFile> filePathMap;
	public Map<String, String> textureMap = new HashMap<>();
	public Map<String, FrameSheet> animationSheetMap;

	public void loadTexture() {
		filePathMap = JsonParser
				.parseMap(StringFile.class, Gdx.files.internal("data/load/file_path.json").readString());

		animationSheetMap = JsonParser.parseMap(FrameSheet.class,
				filePathMap.get(JsonEnum.ANIMATION_SHEET_FILE_PATH.toString()).loadFile());

		directoryTextureMapper(textureMap, "texture");
	}

	public void directoryTextureMapper(Map<String, String> map, String path) {
		FileHandle fh;

		if (Gdx.app.getType() == ApplicationType.Android) {
			fh = Gdx.files.internal(path);
		} else { // ApplicationType.Desktop ..
			fh = Gdx.files.internal("./bin/" + path);
		}

		directoryTextureMapperRecursive(map, fh);
	}

	public void directoryTextureMapperRecursive(Map<String, String> map, FileHandle fh) {
		if (fh.isDirectory()) {
			FileHandle[] fhs = fh.list();

			for (FileHandle e : fhs) {
				directoryTextureMapperRecursive(map, e);
			}
		} else if (!map.containsKey(fh.nameWithoutExtension()) && fh.extension().matches("^(png|jpg)")) {
			map.put(fh.nameWithoutExtension(), fh.path());
			assetManager.load(fh.path(), Texture.class);
		}
	}
}

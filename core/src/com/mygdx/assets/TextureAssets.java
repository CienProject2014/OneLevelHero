package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.enums.JsonEnum;
import com.mygdx.model.jsonModel.FrameSheet;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class TextureAssets {
	private Map<String, StringFile> filePathMap;
	private Map<String, String> textureMap = new HashMap<>();
	private Map<String, FrameSheet> animationSheetMap;

	@SuppressWarnings("unchecked")
	public void loadTexture() {
		FileHandle file = Gdx.files.local("textureMap.json");

		Json json = new Json();
		filePathMap = JsonParser
				.parseMap(StringFile.class, Gdx.files.internal("data/load/file_path.json").readString());

		animationSheetMap = JsonParser.parseMap(FrameSheet.class,
				filePathMap.get(JsonEnum.ANIMATION_SHEET_FILE_PATH.toString()).loadFile());
		textureMap = json.fromJson(HashMap.class, Gdx.files.internal("texture/textureMap.json"));
		if (Gdx.app.getType() != ApplicationType.Android) {
			directoryTextureMapperRecursive(textureMap, Gdx.files.internal("./bin/texture"));
			file.writeString(json.prettyPrint(textureMap), false);
		}
	}

	public String getTexturePath(String textureName) {
		return textureMap.get(textureName);
	}

	public FrameSheet getAnimationSheet(String sheetName) {
		if (animationSheetMap.containsKey(sheetName)) {
			return animationSheetMap.get(sheetName);
		} else {
			return animationSheetMap.get("attack_swing");
		}
	}

	public void directoryTextureMapperRecursive(Map<String, String> map, FileHandle fh) {
		if (fh.isDirectory()) {
			FileHandle[] fhs = fh.list();

			for (FileHandle e : fhs) {
				directoryTextureMapperRecursive(map, e);
			}
		} else if (!map.containsKey(fh.nameWithoutExtension()) && fh.extension().matches("^(png|jpg)")) {
			String[] path = fh.path().toString().split("/");
			String realPath = "";
			for (int i = 0; i < path.length - 2; i++) {
				realPath += path[i + 2];
				realPath += "/";
			}
			map.put(fh.nameWithoutExtension(), realPath);
		}
	}
}

package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.model.jsonModel.FrameSheet;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class TextureAssets {
	@Autowired
	private AssetsManager assetsManager;
	private Map<String, StringFile> filePathMap;
	private Map<String, String> textureMap = new HashMap<>();
	private Map<String, FrameSheet> animationSheetMap;

	public void loadTexture() {
		FileHandle file = Gdx.files.local("textureMap.json");

		Json json = new Json();
		filePathMap = JsonParser.parseMap(StringFile.class,
				Gdx.files.internal("data/load/file_path.json").readString());

		animationSheetMap = JsonParser.parseMap(FrameSheet.class,
				filePathMap.get(JsonEnum.ANIMATION_SHEET_FILE_PATH.toString()).loadFile());
		if (Gdx.app.getType() == ApplicationType.Android) {
			textureMap = json.fromJson(HashMap.class, Gdx.files.internal("texture/textureMap.json"));
			for (Map.Entry<String, String> entry : textureMap.entrySet()) {
				assetsManager.load(entry.getValue(), Texture.class);
			}
		} else {
			directoryTextureMapper(textureMap, "texture");
			file.writeString(json.prettyPrint(textureMap), false);
			textureMap = json.fromJson(HashMap.class, Gdx.files.internal("texture/textureMap.json"));
			for (Map.Entry<String, String> entry : textureMap.entrySet()) {
				assetsManager.load(entry.getValue(), Texture.class);
			}
		}
	}

	public String getTexturePath(String textureName) {
		return textureMap.get(textureName);
	}

	public FrameSheet getAnimationSheet(String sheetName) {
		return animationSheetMap.get(sheetName);
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

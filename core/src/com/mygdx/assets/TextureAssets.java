package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
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
		filePathMap = JsonParser.parseMap(StringFile.class,
				Gdx.files.internal("data/load/file_path.json").readString());

		animationSheetMap = JsonParser.parseMap(FrameSheet.class,
				filePathMap.get(JsonEnum.ANIMATION_SHEET_FILE_PATH.toString()).loadFile());

		directoryTextureMapper(textureMap, "texture");
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
		Gdx.app.log("Texture Text", " Texture If & for 돌기전");
		if (fh.isDirectory()) {
			FileHandle[] fhs = fh.list();

			for (FileHandle e : fhs) {
				directoryTextureMapperRecursive(map, e);
			}
		} else if (!map.containsKey(fh.nameWithoutExtension()) && fh.extension().matches("^(png|jpg)")) {
			map.put(fh.nameWithoutExtension(), fh.path());
			assetsManager.load(fh.path(), Texture.class);
		}
		Gdx.app.log("Texture Text", " Texture If & for 돈 후");
	}
}

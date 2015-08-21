package com.mygdx.assets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.JsonEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

/**
 * 각종 리소스들을 관리해주는 assets 클래스, Stage및 Screen에 필요한 요소들을 전달해준다.
 * 
 * @author Velmont
 * 
 */
public class Assets {
	@Autowired
	private MusicAssets musicAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private ItemAssets itemAssets;
	@Autowired
	private SkillAssets skillAssets;
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private NodeAssets worldNodeAssets;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private TextureAssets textureAssets;
	@Autowired
	private AssetsManager assetsManager;

	public void initialize() {
		Map<String, StringFile> filePathMap = loadFilePathMap();
		Map<String, String> jsonStringMap = loadJsonStringMap(filePathMap);
		uiComponentAssets.set(filePathMap);
		musicAssets.set(filePathMap);
		atlasUiAssets.set(filePathMap);
		eventAssets.set(jsonStringMap);
		itemAssets.set(jsonStringMap);
		skillAssets.set(jsonStringMap);
		unitAssets.set(jsonStringMap); // 아이템, 스킬보다 늦게 이루어져야한다.
		worldMapAssets.set(jsonStringMap);
		worldNodeAssets.set(jsonStringMap);
		constantsAssets.set(jsonStringMap);
		textureAssets.loadTexture();
	}
	private Map<String, StringFile> loadFilePathMap() {
		Map<String, StringFile> filePathMap = JsonParser.parseMap(StringFile.class,
				Gdx.files.internal("data/load/file_path.json").readString());
		return filePathMap;
	}

	private Map<String, String> loadJsonStringMap(Map<String, StringFile> filePathMap) {
		Map<String, StringFile> jsonFileMap = JsonParser.parseMap(StringFile.class,
				filePathMap.get(JsonEnum.JSON_FILE_PATH.toString()).loadFile(assetsManager));
		Map<String, String> jsonStringMap = new HashMap<>();
		for (Entry<String, StringFile> entry : jsonFileMap.entrySet())
			jsonStringMap.put(entry.getKey(), entry.getValue().loadFile(assetsManager));
		return jsonStringMap;
	}
}

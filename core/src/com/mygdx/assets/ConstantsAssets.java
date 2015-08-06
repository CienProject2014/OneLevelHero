package com.mygdx.assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.jsonModel.Constants;
import com.mygdx.util.JsonParser;

public class ConstantsAssets implements JsonAssetsInitializable {
	@SuppressWarnings("rawtypes")
	private Map<String, ArrayList<String>> labelConstantsMap = new HashMap<>();

	@Override
	public void set(Map<String, String> jsonStringMap) {
		ArrayList<Constants> constantsList = new ArrayList<>();
		constantsList = JsonParser.parseList(Constants.class,
				jsonStringMap.get(JsonEnum.LABEL_CONSTANTS_JSON.toString()));
		for (Constants constants : constantsList) {
			labelConstantsMap.put(constants.getListName(),
					constants.getConstantsList());
		}
	}

	public ArrayList<String> getLabels(String labelListName) {
		return labelConstantsMap.get(labelListName);
	}
}

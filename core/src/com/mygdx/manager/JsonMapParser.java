package com.mygdx.manager;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class JsonMapParser {

	private static final Json JSON = new Json();

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> mapParse(Class<T> clazz, String jsonString) {

		HashMap<String, JsonValue> parsedMap = JSON.fromJson(HashMap.class,
				jsonString);

		HashMap<String, T> result = new HashMap<String, T>();

		for (String key : parsedMap.keySet()) {
			result.put(key, JSON.readValue(clazz, parsedMap.get(key)));
		}

		return result;
	}
}

package com.mygdx.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class JsonParser {
	private static final Json JSON = new Json();

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> parseMap(Class<T> clazz, String jsonString) {
		Map<String, JsonValue> parsedMap = JSON.fromJson(HashMap.class,
				jsonString);

		Map<String, T> result = new HashMap<String, T>();

		for (String key : parsedMap.keySet()) {
			result.put(key, JSON.readValue(clazz, parsedMap.get(key)));
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> parseList(Class<T> clazz, String jsonString) {
		List<JsonValue> parsedList = JSON.fromJson(ArrayList.class, jsonString);
		List<T> result = new ArrayList<T>();

		for (JsonValue jsonValue : parsedList) {
			result.add(JSON.readValue(clazz, jsonValue));
		}
		return result;
	}
}

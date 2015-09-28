package com.mygdx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class JsonParser {
	private static final Json JSON = new Json();

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> parseMap(Class<T> clazz, String jsonString) {
		Map<String, JsonValue> parsedMap = JSON.fromJson(HashMap.class, jsonString);

		Map<String, T> result = new HashMap<String, T>();

		for (String key : parsedMap.keySet())
			result.put(key, JSON.readValue(clazz, parsedMap.get(key)));

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> parseList(Class<T> clazz, String jsonString) {
		ArrayList<JsonValue> parsedList = JSON.fromJson(ArrayList.class, jsonString);
		ArrayList<T> result = new ArrayList<T>();

		for (JsonValue jsonValue : parsedList)
			result.add(JSON.readValue(clazz, jsonValue));

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T, K> Map<T, K> parseMap(Class<T> clazz1, Class<K> clazz2, String jsonString) {
		Map<T, JsonValue> parseMap = JSON.fromJson(HashMap.class, jsonString);
		Map<T, K> result = new HashMap<T, K>();
		for (T key : parseMap.keySet())
			result.put(key, (K) JSON.readValue(clazz2, parseMap.get(key)));
		return result;
	}
}

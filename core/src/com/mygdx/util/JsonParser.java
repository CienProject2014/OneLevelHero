package com.mygdx.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class JsonParser {
	private static final Json JSON = new Json();

	@SuppressWarnings("unchecked")
	public static <T> HashMap<String, T> parseMap(Class<T> clazz,
			String jsonString) {
		HashMap<String, JsonValue> parsedMap = JSON.fromJson(HashMap.class,
				jsonString);

		HashMap<String, T> result = new HashMap<String, T>();

		for (String key : parsedMap.keySet())
			result.put(key, JSON.readValue(clazz, parsedMap.get(key)));

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> parseList(Class<T> clazz, String jsonString) {
		ArrayList<JsonValue> parsedList = JSON.fromJson(ArrayList.class,
				jsonString);
		ArrayList<T> result = new ArrayList<T>();

		for (JsonValue jsonValue : parsedList)
			result.add(JSON.readValue(clazz, jsonValue));

		return result;
	}
}

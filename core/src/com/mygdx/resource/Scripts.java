/*
 * String은 Texture에 들어가지 않는다.
 */
package com.mygdx.resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Scripts {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String key1;
	String key2;
	String key3;
	String delimiter = "-";

	//스크립트 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Scripts(int filenum) {
		FileHandle file = Gdx.files.internal("data/scene_script.json");
		String text = file.readString();
		object = (JSONObject) JSONValue.parse(text);
		// 이부분 수정함. 오류뜨면 다른거 보고 다시 고치자.
	}

	//Key값에 맞는 스크립트를 반환함
	public String ScriptGetter(String key) {
		keyParser(key);

		JSONArray array = (JSONArray) object.get(key1);
		JSONObject sc = (JSONObject) array.get(0);
		String script = (String) sc.get(key2 + key3);
		return script;
	}

	//키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		key1 = temp[0];
		key2 = temp[1];
		key3 = temp[2];
	}

}

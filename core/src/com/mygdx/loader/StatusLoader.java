package com.mygdx.loader;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class StatusLoader {
	private String statusVersion;
	private JSONObject object;
	private String delimiter = "-";
	private String unitName;
	private int unitState;

	public StatusLoader(String statusVersion, String key) {
		setStatusVersion(statusVersion); //스테이터스 버전을 설정함 (세이브 파일 or new)
		keyParser(key); // 키값을 파싱함 (유닛이름과 status 상태)
		jsonread(); // Json 정보를 로딩함
	}

	// (1) 키값 파싱
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		setUnitName("Hero");
	}

	// (2) JSON 정보 로딩
	void jsonread() {
		FileHandle file = Gdx.files.internal("data/status_" + getStatusVersion() + ".json");
		Object obj = JSONValue.parse((String) file.readString());
		setObject((JSONObject) obj);
	}

	public JSONObject getStatus() {
		JSONObject unitNameData = (JSONObject) object.get(unitName);
		return unitNameData;
	}

	public String getStatusVersion() {
		return statusVersion;
	}

	public void setStatusVersion(String statusVersion) {
		this.statusVersion = statusVersion;
	}

	public JSONObject getObject() {
		return object;
	}

	public void setObject(JSONObject object) {
		this.object = object;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getUnitState() {
		return unitState;
	}

	public void setUnitState(int unitState) {
		this.unitState = unitState;
	}
}

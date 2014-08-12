package com.mygdx.loader;

import org.json.simple.JSONObject;

import com.badlogic.gdx.utils.Json;
import com.mygdx.resource.SaveVersion;
import com.mygdx.unit.Status;

public class StatusLoader {
	private SaveVersion saveVersion;
	private JSONObject object;
	private String delimiter = "-";
	private String unitName;
	private int unitState;
	Status status;

	public StatusLoader(SaveVersion saveVersion, String key, JSONObject jsonString) {
		setSaveVersion(saveVersion); //스테이터스 버전을 설정함 (세이브 파일 or new)
		keyParser(key); // 키값을 파싱함 (유닛이름 및 status 상태)
		setUnitName("Hero"); //임시로 유닛이름을 세팅해 놓음
		readJson(jsonString); // Json 정보를 로딩함
	}

	// (1) 키값 파싱
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		setUnitName("Hero");
	}

	// (2) JSON 정보 로딩
	public Status readJson(JSONObject jsonString) {
		//Object obj = JSONValue.parse((String) file.readString());
		//setObject((JSONObject) obj);

		Json json = new Json();
		Object getUnitJson = (Object) jsonString.get(getUnitName());

		status = json.fromJson(Status.class, String.valueOf(getUnitJson));
		return status;
	}

	public JSONObject getStatus() {
		JSONObject unitNameData = (JSONObject) object.get(unitName);
		return unitNameData;
	}

	public SaveVersion getSaveVersion() {
		return saveVersion;
	}

	public void setSaveVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
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

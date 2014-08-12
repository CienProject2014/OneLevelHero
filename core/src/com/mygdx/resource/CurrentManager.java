package com.mygdx.resource;

public class CurrentManager {
	private SaveVersion saveVersion;
	private String village;

	public CurrentManager() {

	}

	public SaveVersion getVersion() {
		return saveVersion;
	}

	public void setVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}
}

package com.mygdx.currentState;

import java.io.Serializable;

import com.mygdx.controller.SaveVersion;

public class CurrentState implements Serializable {
	// (1) 버전관리
	private SaveVersion saveVersion;

	public SaveVersion getSaveVersion() {
		return saveVersion;
	}

	public void setSaveVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
	}
}

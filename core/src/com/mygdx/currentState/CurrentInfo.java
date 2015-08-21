package com.mygdx.currentState;

import com.mygdx.enums.SaveVersion;

public class CurrentInfo {
	// (1) 버전관리
	private SaveVersion saveVersion;

	public SaveVersion getSaveVersion() {
		return saveVersion;
	}

	public void setSaveVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
	}
}

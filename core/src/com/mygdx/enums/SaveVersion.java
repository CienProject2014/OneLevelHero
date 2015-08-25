package com.mygdx.enums;

public enum SaveVersion {
	NEW_GAME("new_game"), SAVE_01("save_01"), SAVE_02("save_02"), SAVE_03("save_03");
	private String code;

	private SaveVersion(String code) {
		this.code = code;
	}

	public String toString() {
		return code;
	}

	public static SaveVersion findSaveByName(String stringName) {
		for (SaveVersion saveVersion : SaveVersion.values()) {
			if (saveVersion.toString().equals(stringName)) {
				return saveVersion;
			}
		}
		return null;
	}
}

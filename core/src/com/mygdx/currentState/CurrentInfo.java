package com.mygdx.currentState;

import com.badlogic.gdx.Gdx;

public class CurrentInfo {
	public static boolean isAdminMode;
	public static void changeAdminMode() {
		isAdminMode = !isAdminMode;
		if (isAdminMode) {
			Gdx.app.log("CurrentInfo", "==== 관리자모드 On ====");
		} else {
			Gdx.app.log("CurrentInfo", "==== 관리자모드 Off ====");
		}
	}
	public static String getAdminMessage() {
		if (isAdminMode) {
			return " / On";
		} else {
			return "";
		}
	}
}

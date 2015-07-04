package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.OneLevelTest;

public class TestLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// 나중에 수정 요망
		config.title = "One Level Hero";
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new OneLevelTest(), config);
	}
}

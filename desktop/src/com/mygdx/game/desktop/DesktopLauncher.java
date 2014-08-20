package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.OneLevelHero;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// 나중에 수정 요망
		config.title = "One Level Hero";
		config.width = 960;
		config.height = 760;
		new LwjglApplication(new OneLevelHero(), config);
	}
}

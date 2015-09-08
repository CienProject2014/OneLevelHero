package com.mygdx.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class TexturePackingTool {
	//@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		Settings settings = new Settings();

		TexturePacker packer = new TexturePacker(settings);
		packer.process("pack", "pack", "atlas");
	}
}

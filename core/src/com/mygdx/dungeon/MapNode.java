package com.mygdx.dungeon;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class MapNode {
	public final static int FLG_NULL = 0;
	public final static int FLG_ENTRANCE = (1 << 0);
	public final static int FLG_ROAD = (1 << 1);
	public final static int FLG_ENCOUNT = (1 << 2);
	public final static int FLG_TRESURE = (1 << 3) | FLG_ROAD;
	public final static int FLG_BOSS = (1 << 4) | FLG_ENCOUNT;

	private final static String TAG_TEXTURE = "Texture";
	private final static String TAG_ALPHA = "isVisible";

	private int flg;
	// NOTE TEMP
	public HashMap<String, Object> data;

	public MapNode(int flg) {
		this.flg = flg;
	}

	public boolean chkFlg(int flg) {
		return (this.flg & flg) != FLG_NULL;
	}

	public void addjustFlg(int flg) {
		this.flg |= flg;
	}

	public Texture getMinimapTexture() {
		if (!data.containsKey(TAG_TEXTURE))
			throw new NullPointerException("Minimap Texture is NULL.");

		return (Texture) data.get(TAG_TEXTURE);
	}

	public void setMinimapTexture(Texture tex) {
		data.put(TAG_TEXTURE, tex);
	}

	public float getAlpha() {
		return (float) data.get(TAG_ALPHA);
	}

	public void setAlpha(float alpha) {
		data.put(TAG_ALPHA, alpha);
	}
}

package com.mygdx.dungeonMap;

public class Node {
	public final static int FLG_NULL = 0;
	public final static int FLG_ENTRANCE = (1 << 0);
	public final static int FLG_ROAD = (1 << 1);
	public final static int FLG_ENCOUNT = (1 << 2);
	public final static int FLG_TRESURE = (1 << 3) | FLG_ROAD;
	public final static int FLG_BOSS = (1 << 4) | FLG_ENCOUNT;

	private int flg;

	public Node(int flg) {
		this.flg = flg;
	}

	public boolean chkFlg(int flg) {
		return (this.flg & flg) != FLG_NULL;
	}

	public void addjustFlg(int flg) {
		this.flg |= flg;
	}
}

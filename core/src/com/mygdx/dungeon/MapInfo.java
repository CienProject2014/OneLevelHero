package com.mygdx.dungeon;

import java.util.ArrayList;

public class MapInfo {
	public ArrayList<MapNode> nodes;
	public ArrayList<MapNodeConnection> connections;

	public float minimapPosX, minimapPosY; // 미니맵의 좌측 하단 좌표
}

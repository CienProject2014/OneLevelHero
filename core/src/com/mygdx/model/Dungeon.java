package com.mygdx.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon {
	private String dungeonName;
	private String sceneName;
	private String mapSize;
	public ArrayList<DungeonNode> nodes = new ArrayList<>();
	public ArrayList<DungeonConnection> connections = new ArrayList<>();
}

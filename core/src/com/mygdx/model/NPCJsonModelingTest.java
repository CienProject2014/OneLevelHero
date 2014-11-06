package com.mygdx.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.badlogic.gdx.utils.Json;

public class NPCJsonModelingTest {
	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\Velmont\\Documents\\GitHub\\OneLevelHero\\android\\assets\\data\\event\\npc.json";
		Scanner in = new Scanner(new File(filePath));
		StringBuffer buffer = new StringBuffer();

		while (in.hasNext()) {
			buffer.append(in.next());
		}

		Json json = new Json();
		//Map<String, NPC> npcMap = json.fromJson(Map.Class, buffer.toString());
		HashMap<String, NPC> npcMap = new HashMap<String, NPC>();
		npcMap.put("waiji", json.fromJson(NPC.class, buffer.toString()));
		System.out.println(npcMap.get("waiji"));
		in.close();

	}
}

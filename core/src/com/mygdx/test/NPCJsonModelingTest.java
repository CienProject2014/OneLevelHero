package com.mygdx.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.mygdx.manager.JsonMapParser;
import com.mygdx.model.NPC;
import com.mygdx.state.Assets;

public class NPCJsonModelingTest {
	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\Velmont\\Documents\\GitHub\\OneLevelHero\\android\\assets\\data\\event\\npc.json";
		Scanner in = new Scanner(new File(filePath));
		StringBuffer buffer = new StringBuffer();

		while (in.hasNext()) {
			buffer.append(in.next());
		}
		in.close();

		Map<String, NPC> npcMap = JsonMapParser.mapParse(NPC.class,
				buffer.toString());
		System.out.println(Assets.backgroundTextureMap.get("blackwood_center"));
		System.out.println(npcMap.get("waiji").getEvents().get(0)
				.getEventScenes().get(0).getBackground());
	}
}

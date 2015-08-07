package com.mygdx.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.Assets;
import com.mygdx.model.event.NPC;
import com.mygdx.util.JsonParser;

public class NPCJsonModelingTest {
	@Autowired
	private static Assets assets;

	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\Velmont\\Documents\\GitHub\\OneLevelHero\\android\\assets\\data\\event\\npc.json";
		Scanner in = new Scanner(new File(filePath));
		StringBuffer buffer = new StringBuffer();

		while (in.hasNext())
			buffer.append(in.next());
		in.close();

		Map<String, NPC> npcMap = JsonParser.parseMap(NPC.class,
				buffer.toString());
		System.out.println(npcMap.get("waiji").getEvents().get(0)
				.getEventScenes().get(0).getBackground());
	}
}

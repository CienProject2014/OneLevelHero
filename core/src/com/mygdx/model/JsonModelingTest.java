package com.mygdx.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.utils.Json;

public class JsonModelingTest {
	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\Velmont\\Documents\\GitHub\\OneLevelHero\\android\\assets\\data\\event\\npc.json";
		Scanner in = new Scanner(new File(filePath));
		StringBuffer buffer = new StringBuffer();

		while (in.hasNext()) {
			buffer.append(in.next());
		}

		Json json = new Json();
		NPC waiji = json.fromJson(NPC.class, buffer.toString());
		System.out.println(waiji.getEvent().get(0).getReward()
				.getRewardTarget());
		in.close();

	}
}

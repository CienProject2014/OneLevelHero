package com.mygdx.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import com.mygdx.manager.JsonMapParser;
import com.mygdx.model.Village;

public class VillageTest {

	@Test
	public void villageParseTest() throws IOException {

		String filePath = "../android/assets/data/map/village.json";

		Scanner in = new Scanner(new File(filePath));
		StringBuffer buffer = new StringBuffer();

		while (in.hasNext()) {
			buffer.append(in.next());
		}

		in.close();

		Map<String, Village> village = JsonMapParser.mapParse(Village.class,
				buffer.toString());

		System.out.println(village);
		System.out.println(village.keySet());
		System.out.println(village.get("B"));

	}
}

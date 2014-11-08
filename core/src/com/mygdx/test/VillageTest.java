package com.mygdx.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.mygdx.util.JsonMapParser;

public class VillageTest {

	public static void main(String[] args) throws IOException {

		String filePath = "C:\\Users\\Velmont\\Documents\\GitHub\\OneLevelHero\\android\\assets\\data\\village.json";

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

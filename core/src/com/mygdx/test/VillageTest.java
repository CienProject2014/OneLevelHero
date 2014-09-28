package com.mygdx.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mygdx.util.JsonMapParser;

public class VillageTest {

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {

		String filePath = "/Users/yuinacor/Documents/git/OneLevelHero/android/assets/data/village.json";

		Scanner in = new Scanner(new File(filePath));
		StringBuffer buffer = new StringBuffer();

		while (in.hasNext()) {
			buffer.append(in.next());
		}

		in.close();

		Map<String, Village> village = JsonMapParser.mapParse(Village.class,
				buffer.toString());

		System.out.println(village);
		System.out.println(village.get("B"));

	}
}

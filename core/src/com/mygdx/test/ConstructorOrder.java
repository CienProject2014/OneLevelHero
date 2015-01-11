package com.mygdx.test;

public class ConstructorOrder {
	private static String cat;
	private static String dog;

	public static void main(String[] args) {
		makeCat();
		System.out.println(cat + dog);
	}

	ConstructorOrder() {
		System.out.println("검은");
		dog = "멍뭉이";
	}

	public static void makeCat() {
		cat = "네로";
	}
}

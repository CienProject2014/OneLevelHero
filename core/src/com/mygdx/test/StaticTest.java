package com.mygdx.test;

public class StaticTest {
	public static void main(String[] args) {
		System.out.println(Hey.i);
		Hey.i += 1;
		System.out.println(Hey.i);
		Hey.i += 1;
		System.out.println(Hey.i);
	}
}

class Hey {
	static int i = 1;
}

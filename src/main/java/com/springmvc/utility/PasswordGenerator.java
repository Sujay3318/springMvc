package com.springmvc.utility;

import java.util.Random;

public class PasswordGenerator {

	public static String passwordGenerator() {

		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%&*";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(10);
		for (int i = 0; i < 10; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		System.out.println(sb);
		return sb.toString();
	}

}

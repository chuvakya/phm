package org.zs.phm3.util;
import java.util.Random;

public class Randoms {

	static Random random = new Random();

	public static String generate_string() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		// Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		return output;
		// System.out.println(output);

	}

	public static String generate_string2() {
		int length = 20;
		String characters = "abcdefghijklmnopqrstuvwxyz";
		// Random rnd = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(random.nextInt(characters.length()));
		}
		return new String(text);
	}

	public static String generate_ID() {
		int length = 16;
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		// Random rnd = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(random.nextInt(characters.length()));
		}
		return new String(text);
	}

	public static int generate_int() {
		int randomInt = random.nextInt(10000);
		return randomInt;
	}
}

package com.gamsion.chris.encryptor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Encrypt {
	// list of characters for the program
	static final List<Character> chars = new ArrayList<Character>();;
	static InputStream inputStream = new Encrypt().getClass().getClassLoader()
			.getResourceAsStream("com/gamsion/chris/encryptor/cfg/characters");
	static Scanner scan = new Scanner(inputStream);
	static {
		while (scan.hasNext()) {
			String s = scan.nextLine().trim();
			if (s.length() == 1) {
				chars.add(s.toCharArray()[0]);
			}
		}
		chars.add(' ');

	}

	public static int balance(int numb, int mod) {
		while (numb < 0)
			numb += mod;
		return numb % mod;
	}

	public static String encode(String message) {
		char[] messageSplitter = message.toCharArray();
		int operation = getOperation(messageSplitter.length);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messageSplitter.length; i++) {
			// at first original message, later encrypted
			int finalvalue = chars.indexOf(messageSplitter[i]);
			int key = scramble(operation, messageSplitter.length);
			finalvalue += key;
			finalvalue = balance(finalvalue, chars.size());
			sb.append(chars.get(finalvalue));
		}
		return sb.toString();
	}

	public static String decode(String message) {
		char[] messageSplitter = message.toCharArray();
		int operation = getOperation(messageSplitter.length);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messageSplitter.length; i++) {
			// at first original message, later encrypted
			int finalvalue = chars.indexOf(messageSplitter[i]);
			int key = scramble(operation, messageSplitter.length);
			finalvalue -= key;
			finalvalue = balance(finalvalue, chars.size());
			sb.append(chars.get(finalvalue));
		}

		return sb.toString();
	}

	public static String encrypt(String message, String password) {
		char[] passwordSplitter = password.toCharArray();
		char[] messageSplitter = message.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messageSplitter.length; i++) {
			// at first original message, later encrypted
			int finalvalue = chars.indexOf(messageSplitter[i]);
			// Cycles through password
			int key = chars.indexOf(passwordSplitter[balance(i,
					passwordSplitter.length - 1)]);
			finalvalue += key;
			finalvalue = balance(finalvalue, chars.size());
			sb.append(chars.get(finalvalue));

		}

		return sb.toString();
	}

	public static String decrypt(String message, String password) {
		char[] passwordSplitter = password.toCharArray();
		char[] messageSplitter = message.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messageSplitter.length; i++) {
			int finalvalue = chars.indexOf(messageSplitter[i]);
			int key = chars.indexOf(passwordSplitter[balance(i,
					passwordSplitter.length - 1)]);
			finalvalue -= key;
			finalvalue = balance(finalvalue, chars.size());
			sb.append(chars.get(finalvalue));

		}

		return sb.toString();
	}

	public static int getOperation(int key) {
		int operation;
		if (key % 9 == 0) {
			operation = 1;
		} else if (key % 8 == 0) {
			operation = 2;
		} else if (key % 7 == 0) {
			operation = 3;
		} else if (key % 6 == 0) {
			operation = 4;
		} else if (key % 5 == 0) {
			operation = 5;
		} else if (key % 4 == 0) {
			operation = 6;
		} else if (key % 3 == 0) {
			operation = 7;
		} else if (key % 2 == 0) {
			operation = 8;
		} else if (key % 1 == 0) {
			operation = 9;
		} else {
			operation = 1;
		}
		return operation;
	}

	public static int scramble(int operation, int messagelength) {
		int scram;
		if (operation == 1) {
			scram = messagelength + 10;
		} else if (operation == 2) {
			scram = 12;
		} else if (operation == 3) {
			scram = messagelength - (messagelength % 2) / 2;
		} else if (operation == 4) {
			scram = messagelength;
		} else if (operation == 5) {
			scram = messagelength - (messagelength % 2) * 2;
		} else if (operation == 6) {
			scram = messagelength - (messagelength % 2) * 4;
		} else if (operation == 7) {
			scram = messagelength - (messagelength % 2) * messagelength
					- (messagelength % 2);
		} else if (operation == 8) {
			scram = 69;
		} else if (operation == 9) {
			scram = 9;
		} else {
			scram = 0;
		}
		return scram;
	}
}

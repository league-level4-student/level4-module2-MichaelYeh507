package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		String longer = "";
		if (s1.length() >= s2.length()) {
			longer = s1;
		} else if (s2.length() > s1.length()) {
			longer = s2;
		}

		return longer;
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if (s.contains("underscores")) {
			s = s.replace(" ", "_");
		}
		return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String[] order = null;
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		String[] string1 = s1.split(" ");
		String[] string2 = s2.split(" ");
		String[] string3 = s3.split(" ");
		if ((string1[1].compareTo(string2[1]) < 0) && string1[1].compareTo(string3[1]) < 0) {
			order = string1;
		} else if (string2[1].compareTo(string3[1]) < 0 && string2[1].compareTo(string1[1]) < 0) {
			order = string2;
		} else {
			order = string3;
		}

		return order[0] + " " + order[1];	
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int x = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				x = x + Integer.parseInt(s.charAt(i) + "");
			}
		}
		return x;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int counter = 0;
		while (s.contains(substring)) {
			counter++;
			s = s.replaceFirst(substring, "");
		}
		
		return counter;	
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		String key1 = key + "";

		return Utilities.encrypt(s.getBytes(), key1.getBytes()[0]);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		String key1 = key + "";

		return Utilities.decrypt(s, key1.getBytes()[0]);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int number = 0;
		while (s.contains(substring+" ")) {
			number++;
			s = s.replaceFirst(substring+" ", "");
		}

				return number;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int distance;
		distance = s.lastIndexOf(substring) - (s.indexOf(substring)+substring.length());
		return distance;
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		s = s.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
		int i = 0;
		int j = s.length() - 1;

		while (j > i) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;

		}
		return true;
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}

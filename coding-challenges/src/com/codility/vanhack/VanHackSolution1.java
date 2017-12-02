package com.codility.vanhack;

public class VanHackSolution1 {

	static int[] A = new int[5];
	static int N;

	//Phone number formatter
	
	public static void main(String[] args) {
		String S1 = "090-1113456 7 89";
		String S2 = "090-111345 ";
		String S3 = "12345";
		
		System.out.println(solution(S2));
	}
	
	//The string contains at least 2 digits (0-9)
	//Spaces and dashes can be ignored
	//Format 000.000.000

	private static String solution(String S) {
		String formatted = S.replace("-", "").replace(" ", "");
		
		int i = 0;
		StringBuilder mask = new StringBuilder();
		
		while (i < formatted.length()) {
			if (formatted.length() - i == 4 || formatted.length() - i == 2) {
				mask.append(formatted.substring(i, i+2));
				i+=2;
			} else {
				mask.append(formatted.substring(i, i+3));
				i+=3;
			}
			
			if (i < formatted.length()) {
				mask.append("-");
			}
			System.out.println(mask.toString());
		}
		
		return mask.toString();
	}

}

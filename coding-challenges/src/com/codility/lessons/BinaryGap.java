package com.codility.lessons;

public class BinaryGap {

	public static void main(String[] args) {
		System.out.println(solution(561892));
	}

	static int solution(int N) {
		if (N <= 0) return 0;
		
		char[] binary = Integer.toBinaryString(N).toCharArray();
		int result = 0, gap = 0;
		
		for (int i=0; i < binary.length; i++) {
			if (binary[i] == '0') {
				gap++;
			}
			else {
				if (gap > result) {
					result = gap;
				}
				gap = 0;
			}
		}
		return result;
	}
}

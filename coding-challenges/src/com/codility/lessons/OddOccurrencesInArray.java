package com.codility.lessons;

import java.util.Arrays;

public class OddOccurrencesInArray {

	public static void main(String[] args) {
		System.out.println(solution(new int[]{42}));
		System.out.println(solution(new int[]{9, 3, 9, 3, 9, 7, 9}));
		System.out.println(solution(new int[]{3, 3, 7, 3, 3, 7, 9}));
	}

	static int solution(int[] A) {
		if ((A.length % 2 == 0) || A.length == 0) {
			return 0;
		}
		
		Arrays.sort(A);
		
		int num = -1;
		int count = 0;
		
		for(int i = 0; i < A.length; i++) {
			if (A[i] != num) {
				if (count % 2 != 0) {
					return num;
				}
				else if (i == A.length-1) {
					return A[i];
				}
				num = A[i];
				count = 0;
			}
			count++;
		}
		
		return 0;
	}
}

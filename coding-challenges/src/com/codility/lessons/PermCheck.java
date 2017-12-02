package com.codility.lessons;

import java.util.Arrays;

public class PermCheck {

	public static void main(String[] args) {
		int[] A = {4, 1, 3, 2};
		PermCheck permCheck = new PermCheck();
		
		System.out.println(permCheck.solution(A));
	}
	
	public int solution(int[] A) {
		Arrays.sort(A);
		
		if (A[0] != 1) {
			return 0;
		}
		
		int i = 1;
		int len = A.length;
		while(i < len) {
			if (A[i]-A[i-1] != 1) {
				return 0;
			}
			i++;
		}
		
		return 1;
	}

}

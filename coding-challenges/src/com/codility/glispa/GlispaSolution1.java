package com.codility.glispa;

public class GlispaSolution1 {
	
	static int[] A = new int[5];
	static int N;
	
	//Linked list with array
	public static void main(String[] args) {
		A[0] =  1;
		A[1] = 4;
		A[2] = -1;
		A[3] =  3;
		A[4] =  2;
		
		N = A.length;
		
		int length = 0;
		int i = 0;
		int lastIndex = i;
		while(i > -1) {
			i = A[i];
			length++;
			
			if (lastIndex == i)
				i = -1;
		}
		
		System.out.println(length);
	}

}

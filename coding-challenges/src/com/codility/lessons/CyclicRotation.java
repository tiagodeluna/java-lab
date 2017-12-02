package com.codility.lessons;

public class CyclicRotation {

	public static void main(String[] args) {
		int B[] = solution(new int[]{1000}, 3);
		for (int i : B) {
			System.out.print(i+",");
		}
	}

	static int[] solution(int[] A, int K) {
		if (A.length <= 1) return A;

		int[] B = new int[A.length];
		for (int i=0; i < A.length; i++) {
			B[(i + K) % A.length] = A[i];
		}
		return B;
	}
}

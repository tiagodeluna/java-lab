package com.codility.lessons;

import java.util.Arrays;
import java.util.function.IntConsumer;

/*
A zero-indexed array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
Your goal is to find that missing element.

Write a function:

	class Solution { public int solution(int[] A); }

that, given a zero-indexed array A, returns the value of the missing element.

For example, given array A such that:

	  A[0] = 2
	  A[1] = 3
	  A[2] = 1
	  A[3] = 5
the function should return 4, as it is the missing element.

Assume that:
	N is an integer within the range [0..100,000];
	the elements of A are all distinct;
	each element of array A is an integer within the range [1..(N + 1)].
	
Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.	
*/
public class PermMissingElem {

	public static void main(String[] args) {
		int A[] = {2, 3, 1, 5};
		int B[] = {1};
		
		PermMissingElem code = new PermMissingElem();
		System.out.println(code.solution2(A));
		System.out.println(code.solution2(B));
	}

	/*
	 * Reached 100% over Correctness tests and 60% over Performance tests, 80% overall.
	 * The problem with this approach is that we cannot stop the execution immediately 
	 * 	after finding the missing element, so both best and worst cases give us the same
	 * 	time complexity. */
	public int solution1(int[] A) {
		MyIntConsumer consumer = new MyIntConsumer();

		Arrays.stream(A).sorted().forEach(consumer::accept);
		if (consumer.getResult() == -1) {
			return A.length+1;
		}
		return consumer.getResult();
    }

	/*
	 * Reached 100% both over Correctness and Performance tests.
	 * In spite of being very simple, this solution has a better performance
	 * 	due to the interruption call (break) when the missing element is found */
	public int solution2(int[] A) {
		Arrays.sort(A);
		int result = 0;
		int prev = 0;
		
		for (int i = 0; i < A.length; i++) {
			if (A[i]-prev == 2) {
				result = A[i]-1;
				break;
			} else {
				prev = A[i];
			}
		}
		
		if (result == 0) {
			return A.length+1;
		}
		return result;
    }

	
	class MyIntConsumer implements IntConsumer {

		private int prev;
		private int result;
		
		public MyIntConsumer() {
			this.prev = 0;
			this.result = -1;
		}

		@Override
		public void accept(int value) {
			if (value - prev == 2) {
				result = value-1;
			} else {
				prev = value;
			}
		}

		public int getResult() {
			return result;
		}
	}
}

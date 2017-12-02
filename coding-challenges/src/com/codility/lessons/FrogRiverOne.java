package com.codility.lessons;

import java.util.HashMap;
import java.util.Map;

public class FrogRiverOne {

	public static void main(String[] args) {

	}
	
	public int solution(int X, int[] A) {
		Map<Integer, Integer> map = new HashMap<>();
		
		int len = A.length;
		for (int K=0; K<len; K++) {
			if (!map.containsKey(A[K])) {
				map.put(A[K], K);
				
				if (map.size() == X) {
					return K; 
				}
			}
		}
		
		return -1;
	}

}

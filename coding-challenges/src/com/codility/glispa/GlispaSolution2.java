package com.codility.glispa;

public class GlispaSolution2 {

    private static final int MAX = 3;
    
    //Pair counter in an array
	public static void main(String[] args) {
		int[] A = new int[6];
		A[0] =  3;
		A[1] = 5;
		A[2] = 6;
		A[3] =  3;
		A[4] =  3;
		A[5] =  5;
		
        int n = 0;
        int j;
        
        for (int i = 0; i < A.length; i++) {
        	
        	j = i+1;
        	while (j < A.length) {
        		if (A[i] == A[j]) {
        			n++;
    		        if (n > MAX) {
    		        	System.out.println(MAX);
    		            break;
    		        }
        		}
        		j++;
        	}
        }
        
        System.out.println(n);
	}
}

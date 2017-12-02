package com.codility.booking;
import java.util.ArrayList;
import java.util.List;

public class BookingSolution3 {

	public static void main(String[] args) {
		//Solution3 s = new Solution3();
		int n=solution(new int[]{40,40,100,80,20}, new int[]{3,3,2,2,3}, 3, 5, 200);
		System.out.println(n);
	}
	
	//A = wheight of each person
	//B = target floor of each person
	//M = last floor
	//X = people maximum capacity
	//Y = weight limit
    public static int solution(int[] A, int[] B, int M, int X, int Y) {
    	int weightIn = 0;
    	int peopleIn = 0;
    	int stops = 0;
    	
		Runtime runtime = Runtime.getRuntime();
	    long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Used Memory before: " + usedMemoryBefore);

	    List<Integer> targetFloors = new ArrayList<>(); 

		for (int i = 0; i < A.length; i++) {
			//Checks limits of weight and people
			//Takes passengers
			if (weightIn + A[i] <= Y && peopleIn < X) {
				//Adds a "person" to the elevator
				weightIn += A[i];
				peopleIn++;
				
				if (!targetFloors.contains(B[i])) {
					targetFloors.add(B[i]);
				}
			}

			//Calculates number of stops
			if (weightIn + A[i] > Y || peopleIn >= X || i == A.length-1){
				//The number of stops considers the last stop at the ground floor.
				stops += targetFloors.size()+1;
				targetFloors.clear();
				weightIn = peopleIn = 0;
			}

			long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
		    System.out.println("Memory increased: " + (usedMemoryAfter-usedMemoryBefore));
    	}
		
        return stops;
    }
}

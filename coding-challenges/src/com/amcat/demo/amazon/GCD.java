package com.amcat.demo.amazon;

import java.util.Arrays;

public class GCD {

	public static void main(String[] args) {
		int[] numbers = {18, 9, 36, 90};

		GCD main = new GCD();
		System.out.println("GCD is " + main.generalizedGCD(numbers.length, numbers));
	}
	
    public int generalizedGCD(int num, int[] arr)
    {
    	//Get the lowest element
    	int first = Arrays.stream(arr)
    			.reduce((x, y) -> (x < y) ? x : y)
    			.orElse(1);
    	//Search for the GCD
        return findGCD(first, arr);
    }
    
    //Recursive method to obtain the GCD, starting to the lowest number in the array
    private int findGCD(int elem, int[] items) {
    	if (elem < 2) {
    		return 1;
    	}
    	
    	return Arrays.stream(items)
    			.filter(item -> item % elem != 0)
    			.count() == 0 ? elem : findGCD(elem-1, items);
    }
    
}

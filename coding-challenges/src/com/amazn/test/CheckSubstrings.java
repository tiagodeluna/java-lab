package com.amazn.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Returns all the substrings with length equals to K which are contained in a given string and have 
 * exactly K-1 characters. K is a positive integer passed as a parameter.
 * 
 * @author Tiago Luna
 */
public class CheckSubstrings {

	public static void main(String[] args) {
		CheckSubstrings main = new CheckSubstrings();
		List<String> substrings = main.subStringsLessKDist("wawaglknagagwunagkwkwagl", 4);
		System.out.println(substrings);
	}
	
    public List<String> subStringsLessKDist(String inputString, int num) {
    	List<String> substrings = new ArrayList<>();

    	//Check the params limits
    	int strLength = inputString.length();
    	if (num <= 0 || num > strLength) {
    		return substrings; 
    	}
    	
    	for (int i = 0; i < strLength-num+1; i++) {
    		String substring = inputString.substring(i, i+num);
    		
    		//Check if the number of distinct characters is K-1 and
    		// the substring was not already stored
    		long distinctChars = Arrays.stream(substring.split("")).distinct().count();
    		if (distinctChars == num-1 && !substrings.contains(substring)) {
				substrings.add(substring);
    		}
    	}
    	
        return substrings;
    }
}

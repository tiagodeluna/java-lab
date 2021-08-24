package com.codesignal.arcade;

import java.util.Arrays;

/**
 * Return the highest product between two adjacent values in an array of int.
 */
public class AdjacentElementsProduct {

    public static void main(String[] args) {
        adjacentElementsProduct(new int[]{3, 6, -2, -5, 7, 3});
        adjacentElementsProduct(new int[]{-1, -2});
        adjacentElementsProduct(new int[]{5, 1, 2, 3, 1, 4});
        adjacentElementsProduct(new int[]{1, 0, 1, 0, 1000});
    }

    private static int adjacentElementsProduct(int[] inputArray) {
        System.out.println("\nInput: " + Arrays.toString(inputArray));
        int highest = Integer.MIN_VALUE;

        for (int i = 0; i < inputArray.length - 1; i++) {
            int product = inputArray[i]*inputArray[i+1];
            highest = product > highest ? product : highest;
        }

        System.out.println("Output: " + highest);
        return highest;
    }
}

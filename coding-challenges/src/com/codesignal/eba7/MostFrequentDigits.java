package com.codesignal.eba7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Count the number of occurrences of each digit in an int array and returns the most frequent ones.
 */
public class MostFrequentDigits {

    public static void main (String [] args) {
        int[] input = new int[]{11, 2, 3, 32};
        measureTime(input, MostFrequentDigits::mostFrequentDigits1);
        measureTime(input, MostFrequentDigits::mostFrequentDigits2);

        input = new int[]{25, 2, 5, 17, 91};
        measureTime(input, MostFrequentDigits::mostFrequentDigits1);
        measureTime(input, MostFrequentDigits::mostFrequentDigits2);
    }

    private static int[] mostFrequentDigits1(int[] input) {
        System.out.println("Executing mostFrequentDigits1...");
        int max = 0;
        int[] arrayCount = {0,0,0,0,0,0,0,0,0,0};

        // Count all digits in the array using divide and modulo operations
        for (int entry : input) {
            // Skip "0" in case the number is lower then 10.
            if (entry >= 10) {
                int tens = entry/10;
                arrayCount[tens]++;
                max = arrayCount[tens]>max ? arrayCount[tens] : max;
            }

            int units = entry%10;
            arrayCount[units]++;
            max = arrayCount[units]>max ? arrayCount[units] : max;
        }

        // Obtain the most frequent digits
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arrayCount.length; i++) {
            if (arrayCount[i] == max) {
                result.add(i);
            }
        }

        return result.stream()
                .mapToInt(i->i)
                .toArray();
    }

    private static int[] mostFrequentDigits2(int[] input) {
        System.out.println("Executing mostFrequentDigits2...");
        Map<Integer, Integer> digitsMap = new HashMap<>();

        for(int n : input) {
            String str = Integer.toString(n);
            Arrays.stream(str.split(""))
                    .mapToInt(Integer::parseInt)
                    .forEach(o -> {
                        digitsMap.compute(o, (key, val) -> val != null ? val+1 : 1);
                    });
        }

        int max = digitsMap.values().stream().max(Integer::compareTo).get();

        return digitsMap.entrySet().stream().filter(entry -> entry.getValue().equals(max))
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    private static void measureTime(int[] input, Function<int[], int[]> functionToRun) {
        long startTime = System.nanoTime();
        int[] output = functionToRun.apply(input);
        long endTime = System.nanoTime();
        printArray("Input", input);
        printArray("Output", output);
        // Displays time in microseconds (since it would be hard to see any difference using seconds)
        System.out.println("* Duration: " + ((double)endTime - startTime)/1000 + " μs");
    }

    private static void printArray(String label, int[] result) {
        List<Integer> array = new ArrayList<>();
        for (int i : result) {
            array.add(i);
        }

        System.out.println(String.format("* %s: %s", label, array));
    }

}

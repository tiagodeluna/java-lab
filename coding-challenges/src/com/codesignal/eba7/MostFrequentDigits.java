package com.codesignal.eba7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Count the number of occurrences of each digit in an int array and returns the most frequent ones.
 */
public class MostFrequentDigits {

    public static void main (String [] args) {
        int[] result1 = mostFrequentDigits(new int[]{11, 2, 3, 32});
        print("output", result1);

        int[] result2 = mostFrequentDigits(new int[]{25, 2, 5, 17, 91});
        print("output", result2);
    }

    static int[] mostFrequentDigits(int[] array) {
        print("input", array);
        Map<Integer, Integer> digitsMap = new HashMap<>();

        for(int n : array) {
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

    static void print(String label, int[] result) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i : result) {
            strBuilder.append(i).append(",");
        }
        System.out.println(String.format("[%s] {%s}", label, strBuilder.toString()));
    }

}

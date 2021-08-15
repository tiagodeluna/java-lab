package com.codesignal.arcade;

/**
 * Given a year, return the century it is in. The first century spans from the year 1 up to and including the year 100,
 * the second - from the year 101 up to and including the year 200, etc.
 */
public class CenturyFromYear {

    public static void main(String... args) {
        print(1980, centuryFromYear(1980), 20);
        print(1700, centuryFromYear(1700), 17);
        print(1, centuryFromYear(1), 1);
        print(2005, centuryFromYear(2005), 21);
    }

    static int centuryFromYear(int year) {
        int century = (year-1)/100 +1;
        return century;
    }

    static void print(int input, int output, int expected) {
        System.out.println(String.format("Input: %s | Output: %s | Expected: %s | Result: %s",
                input, output, expected, expected == output ? "Passed!" : "Failed"));
    }
}

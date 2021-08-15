package com.ebay.test;

import java.util.Arrays;

public class AddTwoDigits {

    public static void main (String [] args) {
        System.out.println("in: 29 - out: 2 + 9 = " + addTwoDigits(29));
        System.out.println("in: 10 - out: 1 + 0 = " + addTwoDigits(10));
        System.out.println("in: 77 - out: 7 + 7 = " + addTwoDigits(77));
    }

    static int addTwoDigits(int n) {
        String strInt = Integer.toString(n);

        return Arrays.stream(strInt.split(""))
                .mapToInt(Integer::parseInt)
                .reduce((a,b) -> {
                    System.out.println(String.format("a=%s,b=%s",a,b));
                    return a+b;
                }).getAsInt();
    }
}

package com.codesignal.arcade;

/**
 * Given the string, check if it is a palindrome.
 */
public class CheckPalindrome {

    public static void main(String... args) {
        String word = "abacaba";
        print(word, checkPalindrome(word), true);
        word = "tiago";
        print(word, checkPalindrome(word), false);
        word = "A";
        print(word, checkPalindrome(word), true);
        word = "az";
        print(word, checkPalindrome(word), false);
        word = "hlbeeykoqqqokyeeblh";
        print(word, checkPalindrome(word), true);
    }

    static boolean checkPalindrome(String inputString) {
        int left = 0,
            right = inputString.length()-1;

        while(left < right) {
            if (inputString.charAt(left)!=inputString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    static void print(String input, boolean output, boolean expected) {
        System.out.println(String.format("Input: %s | Output: %s | Expected: %s | Result: %s",
                input, output, expected, expected == output ? "Passed" : "FAILED!"));
    }
}

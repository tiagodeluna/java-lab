package com.ebay.test;

/**
 * Reverts the order of words of a given sentence.
 */
public class ReverseSentence {

    public static void main (String [] args) {
        System.out.println(reverseSentence("Man eats cat"));
        System.out.println(reverseSentence("no problem"));
        System.out.println(reverseSentence("1 2 3"));
    }

    static String reverseSentence(String sentence) {
        StringBuilder reversed = new StringBuilder();
        String[] words = sentence.split(" ");

        for(int i = words.length-1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }

        return reversed.toString().trim();
    }
}

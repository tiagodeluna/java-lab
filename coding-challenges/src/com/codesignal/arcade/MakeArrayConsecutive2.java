package com.codesignal.arcade;

import java.util.Arrays;

/**
 * Ratiorg got statues of different sizes as a present from CodeMaster for his birthday, each statue having an
 * non-negative integer size. Since he likes to make things perfect, he wants to arrange them from smallest to
 * largest so that each statue will be bigger than the previous one exactly by 1. He may need some additional
 * statues to be able to accomplish that. Help him figure out the minimum number of additional statues needed.
 */
public class MakeArrayConsecutive2 {

    public static void main(String[] args) {
        makeArrayConsecutive2(new int[]{6, 2, 3, 8});
        makeArrayConsecutive2(new int[]{2, 1});
        makeArrayConsecutive2(new int[]{1});
        makeArrayConsecutive2(new int[]{1, 2, 3, 4});
        makeArrayConsecutive2(new int[]{18, 5, 15, 2});
    }

    private static int makeArrayConsecutive2(int[] statues) {
        System.out.println("The statues' sizes are: " + Arrays.toString(statues));
        int min = 20, max = 0;
        for (int i : statues) {
            min = i < min ? i : min;
            max = i > max ? i : max;
        }

        int number = max-min-(statues.length-1);
        System.out.println("The number of additional statues needed is: " + number);
        return number;
    }

}

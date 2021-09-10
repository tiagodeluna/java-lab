package com.hackerrank;

import java.util.List;

public class JumpingOnTheClouds {

    public static void main(String[] args) {
        print(jumpingOnClouds(List.of(0, 0, 0, 0, 1, 0)));
        print(jumpingOnClouds(List.of(0, 0, 1, 0, 0, 1, 0)));
        print(jumpingOnClouds(List.of(0, 0, 1, 0, 0, 0, 0, 1, 0, 0)));
    }

    /*
     * Complete the 'jumpingOnClouds' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY c as parameter.
     */
    public static int jumpingOnClouds(List<Integer> c) {
        System.out.println("Input: " + c);
        int position = 1,
                safeCloud = 1,
                jumps = 0;

        for(int i = 0; i < c.size(); i++) {
            if (c.get(i) == 1 && safeCloud > position) {
                jumps++;
                position = safeCloud;
            } else {
                safeCloud = i+1;
                if (position == safeCloud-2 || safeCloud == c.size()) {
                    jumps++;
                    position = safeCloud;
                }
            }

            System.out.format("Position = %s, SafeCloud = %s, Jumps = %s\n",
                    position, safeCloud, jumps);
        }

        return jumps;
    }

    static void print(int result) {
        System.out.println("Result is : " + result);
    }

}

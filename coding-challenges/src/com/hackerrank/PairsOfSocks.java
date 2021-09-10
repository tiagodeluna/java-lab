package com.hackerrank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a large pile of socks that must be paired by color. Given an array of integers representing the color of each sock, determine how many pairs of socks with matching colors there are.
 *
 * There is one pair of color  and one of color . There are three odd socks left, one of each color. The number of pairs is .
 *
 * Complete the sockMerchant function in the editor below.
 *
 * sockMerchant has the following parameter(s):
 * * int n: the number of socks in the pile
 * * int ar[n]: the colors of each sock
 * Returns
 * * int: the number of pairs
 */
public class PairsOfSocks {

    public static void main(String[] args) {
        print(sockMerchant(7, List.of(1, 2, 1, 2, 1, 3, 2)));
        print(sockMerchant(10, List.of(1, 1, 3 ,1, 2, 1, 3, 3, 3, 3)));
    }

    /*
     * Complete the 'sockMerchant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY ar
     */
    static int sockMerchant(int n, List<Integer> ar) {
        System.out.println("Pile of socks: " + ar);
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : ar) {
            map.computeIfPresent(i, (old,nu) -> nu+1);
            map.putIfAbsent(i, 1);
        }
        System.out.println(map);

        return map.values().stream()
                .mapToInt(val -> val/2)
                .sum();
    }

    static void print(int value) {
        System.out.println("Number of pairs: " + value);
    }

}

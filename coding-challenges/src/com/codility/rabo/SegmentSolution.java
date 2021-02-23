package com.codility.rabo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SegmentSolution {

    private static final String RESULT_FORMAT = "Case %s - Size: %s | Space: %s | Result: %s%n";

    public static void main(String[] args) {
        int i = 0;
        int segment = 1;
        List<Integer> space = Arrays.asList(1, 2, 3, 1, 2);
        System.out.printf(RESULT_FORMAT, i++, segment, Arrays.toString(space.toArray()), segment(segment, space));
        segment = 2;
        space = Arrays.asList(1, 1, 1);
        System.out.printf(RESULT_FORMAT, i++, segment, Arrays.toString(space.toArray()), segment(segment, space));
        segment = 3;
        space = Arrays.asList(2, 5, 4, 6, 8);
        System.out.printf(RESULT_FORMAT, i, segment, Arrays.toString(space.toArray()), segment(segment, space));
    }

    /**
     * Problem:
     *  Given an array or list of integers represented by 'space':
     *  1. Split the array into continuous segments with a fixed size, determined by 'x'.
     *  2. Take the minimum element from each segment.
     *  3. Extract the maximum value from the resulting elements.
     *
     * @param x The segment size
     * @param space The element array
     * @return The resulting element
     */
    private static int segment(int x, List<Integer> space) {
        // Find sublists
        List<List<Integer>> sublists = new ArrayList<>();
        for(int i = 0; i + x <= space.size(); i++) {
            sublists.add(space.subList(i, i + x));
        }
        // Extract minimum element of each sublist then find maximum element from resulting list
        return sublists.stream()
                .map(sublist -> sublist.stream()
                        .reduce(sublist.get(0), Math::min))
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("Unexpected result: no element found"));
    }

}

package com.codesignal.arcade;

/**
 * Below we will define an n-interesting polygon. Your task is to find the area of a polygon for a given n.
 *
 * A 1-interesting polygon is just a square with a side of length 1. An n-interesting polygon is obtained by taking the
 * n - 1-interesting polygon and appending 1-interesting polygons to its rim, side by side. You can see the 1-, 2-, 3-
 * and 4-interesting polygons in the picture below.
 *
 * Example:
 *
 * For n = 2, the output should be
 * shapeArea(n) = 5;
 * For n = 3, the output should be
 * shapeArea(n) = 13.
 */
public class ShapeArea {

    public static void main(String[] args) {
        shapeArea(2);
        shapeArea(3);
        shapeArea(4);
        shapeArea(7);
        shapeArea(10);
    }

    private static int shapeArea(int n) {
        System.out.format("The area of the %s-interesting polygon is = ", n);
        int area = n*n + (n-1)*(n-1);
        System.out.println(area);
        return area;
    }
}

/*
Possible to form a triangle from array values

Given an array of integers, we need to find out whether it is possible to construct at least one non-degenerate triangle using array values as its sides. In other words, we need to find out 3 such array indices which can become sides of a non-degenerate triangle.

Examples :

Input : [4, 1, 2]
Output : No
Explanation : No triangle is possible from given array values

Input : [5, 4, 3, 1, 2]
Output : Yes
Explanation : Sides of possible triangle are 2 3 4

For a non-degenerate triangle, its sides should follow these constraints,

A + B > C    and
B + C > A    and
C + A > B
        where A, B and C are length of sides of the triangle.

A Simple Solution is to generate all triplets and for every triplet check if it forms a triangle or not by checking above three conditions.

An Efficient Solution is use sorting.
First, we sort the array then we loop once and we will check three consecutive elements of this array
    if any triplet satisfies arr[i] + arr[i+1] > arr[i+2],
        then we will output that triplet as our final result.
*/

import java.io.*;
import java.util.Arrays;
public class TriangleFormation {

    // Method prints possible triangle when array values are
    // taken as sides
    static boolean isPossibleTriangle(int[] arr, int N)
    {
        // If number of elements are less than 3, then no
        // triangle is possible
        if (N < 3)
            return false;
        // first sort the array
        Arrays.sort(arr);
        // loop for all 3 consecutive triplets
        for (int i = 0; i < N - 2; i++)
            // If triplet satisfies triangle condition, break
            if (arr[i] + arr[i + 1] > arr[i + 2])
                return true;
        return false;
    }

    // Driver Code
    public static void main(String[] args)
    {
        int[] arr = { 5, 4, 3, 1, 2 };
        int N = arr.length;
        if (isPossibleTriangle(arr, N))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

}

/*
Sort an array of 0s, 1s and 2s

Given an array of size N containing only 0s, 1s, and 2s; sort the array in ascending order.

Example 1:

Input:
    N = 5
    arr[]= {0 2 1 2 0}
Output:
    0 0 1 2 2
Explanation:
    0s 1s and 2s are segregated into ascending order.
*/

import java.util.Arrays;

public class Sort012 {

    public static void sort012(int a[], int n) {
        int i = 0, countOfZero = 0, countOfOne = 0, countOfTwo = 0;
        for (int number : a) {
            switch (number) {
                case 0:
                    countOfZero++;
                    break;
                case 1:
                    countOfOne++;
                    break;
                case 2:
                    countOfTwo++;
                    break;
            }
        }
        while (countOfZero > 0) {
            a[i++] = 0;
            countOfZero--;
        }
        while (countOfOne > 0) {
            a[i++] = 1;
            countOfOne--;
        }
        while (countOfTwo > 0) {
            a[i++] = 2;
            countOfTwo--;
        }
    }

    public static void main(String[] args) {
        int arr[] = {0, 2, 1, 2, 0};
        sort012(arr, 5);
        System.out.println(Arrays.toString(arr));
    }
}

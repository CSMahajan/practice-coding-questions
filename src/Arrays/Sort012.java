package Arrays;
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

    public static void sort012(int[] a) {
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

    //Time Complexity: O(N), where N = size of the given array.
    //Reason: We are using a single loop that can run at most N times.
    //Space Complexity: O(1) as we are not using any extra space.
    public void sort012UsingDutchNationalFlagAlgorithm(int[] arr, int n) {
        int low = 0;
        int mid = 0;
        int high = n - 1;
        /*
        Intuition is as below for Dutch National Flag Algorithm
        elements from 0 to low - 1 will have all 0's
        elements from low to mid - 1 will have all 1's
        elements from mid to high will have all elements(0,1,2) in unsorted manner which we will reduce
        elements from high + 1 to n - 1 will have all 2's
        */
        while (mid <= high) {
            if (arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else if (arr[mid] == 2) {
                swap(arr, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {0, 2, 1, 2, 0, 1, 2, 1, 0, 2, 1, 0};
        Sort012 s = new Sort012();
        s.sort012UsingDutchNationalFlagAlgorithm(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {0, 1, 2, 2, 0, 1, 2, 1, 0};
        sort012(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
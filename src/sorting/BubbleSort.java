package sorting;

import java.util.Arrays;

/*
Bubble Sort

Given an Integer N and a list arr. Sort the array using bubble sort algorithm.
Example 1:
Input:
N = 5
arr[] = {4, 1, 3, 9, 7}
Output:
1 3 4 7 9
Example 2:
Input:
N = 10
arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}
Output:
1 2 3 4 5 6 7 8 9 10
*/
public class BubbleSort {

    //Time complexity: O(N2)
    //Space Complexity: O(1)
    public static void bubbleSort(int[] arr, int n) {
        for (int i = n - 1; i >= 0; i--) {
            //below didSwap is used as a flag to know if any swapping happened or not
            int didSwap = 0;
            for (int j = 0; j <= i - 1; j++) {
                //if we find next element as smaller, then we swap the elements
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    didSwap = 1;
                }
            }
            //if not swap happened it means no need to continue
            if (didSwap == 0) {
                break;
            }
        }
    }

    //---------Recursive Way---------------

    //Time complexity: O(N2)
    //Space Complexity: O(N) auxiliary stack space.
    public static void bubbleSortRecursive(int[] arr, int n) {
        //Base case: range == 1.
        if (n == 1) return;
        for (int j = 0; j <= n - 2; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        //Range reduced after recursion:
        bubbleSortRecursive(arr, n - 1);
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        bubbleSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        bubbleSortRecursive(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}

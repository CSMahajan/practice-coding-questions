package sorting;

import java.util.Arrays;

/*
Insertion Sort

The task is to complete the insert() function which is used to implement Insertion Sort.
Example 1:
Input:
N = 5
arr[] = { 4, 1, 3, 9, 7}
Output:
1 3 4 7 9
Example 2:
Input:
N = 10
arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}
Output:
1 2 3 4 5 6 7 8 9 10
*/
public class InsertionSort {

    //Time complexity: O(N2)
    //Space Complexity: O(1)
    public static void insertionSort(int[] arr, int n) {
        for (int i = 0; i <= n - 1; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    //---------Recursive Way---------------

    //Time complexity: O(N2)
    //Space Complexity: O(N) auxiliary stack space.
    public static void insertionSortRecursive(int[] arr, int i, int n) {
        // Base Case: i == n.
        if (i == n) return;
        int j = i;
        while (j > 0 && arr[j - 1] > arr[j]) {
            int temp = arr[j - 1];
            arr[j - 1] = arr[j];
            arr[j] = temp;
            j--;
        }
        insertionSortRecursive(arr, i + 1, n);
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        insertionSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        insertionSortRecursive(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}

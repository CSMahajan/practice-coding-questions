package sorting;

import java.util.Arrays;
/*
Selection Sort

Given an unsorted array of size N, use selection sort to sort arr[] in increasing order.
Example 1:
Input:
N = 5
arr[] = {4, 1, 3, 9, 7}
Output:
1 3 4 7 9
Explanation:
Maintain sorted (in bold) and unsorted subarrays.
Select 1. Array becomes 1 4 3 9 7.
Select 3. Array becomes 1 3 4 9 7.
Select 4. Array becomes 1 3 4 9 7.
Select 7. Array becomes 1 3 4 7 9.
Select 9. Array becomes 1 3 4 7 9.
Example 2:
Input:
N = 10
arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}
Output:
1 2 3 4 5 6 7 8 9 10
*/
public class SelectionSort {

    //Time complexity: O(N2)
    //Space Complexity: O(1)
    public static void selectionSort(int arr[], int n) {
        for (int i = 0; i < n - 1; i++) {
            //for each i, we select the minimum element iteratively and swap it with its correct position from start
            int minimum = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minimum]) {
                    minimum = j;
                }
            }
            //minimum now stores the index of the minimum element in the array,
            //so we swap it with i-th element as the array is then getting sorted from left/the smallest element side
            //swap
            int temp = arr[minimum];
            arr[minimum] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        selectionSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}

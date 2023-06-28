package sorting;

import java.util.Arrays;

/*
Quick Sort

Quick Sort is a Divide and Conquer algorithm.
It picks an element as a pivot and partitions the given array around the picked pivot.
Given an array arr[], its starting position is low (the index of the array) and
its ending position is high(the index of the array).
Note: The low and high are inclusive.
Implement the partition() and quickSort() functions to sort the array.
Example 1:
Input:
N = 5
arr[] = { 4, 1, 3, 9, 7}
Output:
1 3 4 7 9
Example 2:
Input:
N = 9
arr[] = { 2, 1, 6, 10, 4, 1, 3, 9, 7}
Output:
1 1 2 3 4 6 7 9 10
*/
public class QuickSort {

    //Function to sort an array using quick sort algorithm.
    /*
    Time Complexity: O(N*logN), where N = size of the array.
Reason: At each step, we divide the whole array, for that logN and n steps are taken for partition() function,
so overall time complexity will be N*logN.
The following recurrence relation can be written for Quick sort :
F(n) = F(k) + F(n-1-k)
Here k is the number of elements smaller or equal to the pivot and n-1-k denotes elements greater than the pivot.
There can be 2 cases :
Worst Case – This case occurs when the pivot is the greatest or smallest element of the array.
If the partition is done and the last element is the pivot,
then the worst case would be either in the increasing order of the array or in the decreasing order of the array.
Recurrence:
F(n) = F(0)+F(n-1)  or  F(n) = F(n-1) + F(0)
Worst Case Time complexity: O(n^2)
Best Case – This case occurs when the pivot is the middle element or near to middle element of the array.
Recurrence :
F(n) = 2F(n/2)
Time Complexity: best and average case => O(N*logN), worst case =>O(n^2)
Space Complexity: O(1) + O(N) auxiliary stack space.
    */
    public static void quickSort(int arr[], int low, int high) {
        // code here
        if (low < high) {
            //We will find the index of pivot on the basis of which partition will be decided such that
            //elements on the left of partition will be smaller than pivot(partitionIndex) and
            //elements on the right of partition will be larger than pivot(partitionIndex)
            int partitionIndex = partition(arr, low, high);
            //finding partition in left side
            quickSort(arr, low, partitionIndex - 1);
            //finding partition in right side
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private static int partition(int arr[], int low, int high) {
        // your code here
        //We can choose different types of pivot, but here we are choosing first element of the array
        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[i] <= pivot && i <= high - 1) {
                //This inner while loop is used for increasing i till we find an element smaller than pivot from the left side
                //which we will be using for swapping
                i++;
            }
            while (arr[j] > pivot && j >= low + 1) {
                //This inner while loop is used for decreasing j till we find an element larger than pivot from the right side
                //which we will be using for swapping
                j--;
            }
            if (i < j) {
                //this swapping is done basically when we have found the element smaller on right and larger on left,
                //then we swap such that on left of pivot we can have all the elements smaller than pivot and
                //on the right of pivot we have all the elements larger than the pivot
                swap(arr, i, j);
            }
        }
        //We have now found the correct position for the pivot so we swap it with j-th index(which is correct position)
        swap(arr, low, j);
        //at the end of outer while loop, i and j crosses each other and j points to correct index of pivot
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}

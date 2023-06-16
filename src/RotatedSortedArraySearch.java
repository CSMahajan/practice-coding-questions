/*
Search In Rotated Sorted Array

Given a sorted and rotated array arr[] of size N and a key, the task is to find the key in the array.
Array elements can be duplicate

Example:

Without duplicates case
Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3}, key = 3
Output : 8
Explanation : Found at index 8

With duplicates case
Input: arr[] = {3, 3, 3, 1, 2, 3}, key = 3
Output: 0
Explanation : arr[0] = 3

Not found case
Input: arr[] = {3, 3, 3, 1, 2, 3}, key = 11
Output: -1
Explanation : 11 is not present in the given array.
*/
public class RotatedSortedArraySearch {

    public static int search(int[] arr, int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                // if we have found our target element
                // return the index of target element
                return mid;
            }

            if (arr[mid] == arr[low] && arr[mid] == arr[high]) {
                // It may happen in case of duplicates

                ++low;
                --high;
                continue;
            }

            if (arr[low] <= arr[mid]) {
                // This means array is sorted from index low to
                // mid We will check that if target element lies
                // in left half or not

                if (key >= arr[low] && key < arr[mid])
                    high = mid - 1;

                else
                    // This means that our target lies in other
                    // half of array So we shift low to mid+1 to
                    // search in right half
                    low = mid + 1;
            }

            else {
                // This means array is sorted between mid and
                // high index

                // This will check our target element is
                // in right half or not
                if (key <= arr[high] && key > arr[mid])
                    low = mid + 1;

                else
                    // Means our target is in left half
                    high = mid - 1;
            }
        }

        // If target element is not present

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 1, 2, 3, 3 };
        int n = arr.length;

        int key = 3;
        System.out.println(search(arr, 0, n - 1, key));
    }
}

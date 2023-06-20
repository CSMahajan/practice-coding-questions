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

    public static int searchInUniqueElementArray(int A[], int l, int h, int key)
    {
        // l: The starting index
        // h: The ending index, you have to search the key in this range
        // Complete this function
        int n = A.length;
        int low = 0, high = n - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(A[mid] == key) {
                return mid;
            }
            if(A[low] <= A[mid]) {
                //Left Half Sorted
                if(A[low] <= key && key <= A[mid]) {
                    //Key present in left half
                    high = mid - 1;
                } else {
                    //Key present in right half
                    low = mid + 1;
                }
            } else {
                //Right Half Sorted
                if(A[mid] <= key && key <= A[high]) {
                    //Key present in right half
                    low = mid + 1;
                } else {
                    //Key present in left half
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int searchInDuplicateElementArray(int A[], int l, int h, int key)
    {
        // l: The starting index
        // h: The ending index, you have to search the key in this range
        // Complete this function
        int n = A.length;
        int low = 0, high = n - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(A[mid] == key) {
                return mid;
            }
            if(A[low] == A[mid] && A[mid] == A[high]) {
                // It may happen in case of duplicate elements in array
                // example: A = { 3, 3, 1, 2, 3, 3 }
                //To trim the search space below is done
                ++low;
                --high;
                continue;
            }
            if(A[low] <= A[mid]) {
                //Left Half Sorted
                if(A[low] <= key && key <= A[mid]) {
                    //Key present in left half
                    high = mid - 1;
                } else {
                    //Key present in right half
                    low = mid + 1;
                }
            } else {
                //Right Half Sorted
                if(A[mid] <= key && key <= A[high]) {
                    //Key present in right half
                    low = mid + 1;
                } else {
                    //Key present in left half
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 1, 2, 3, 3 };
        int n = arr.length;

        int key = 3;
        System.out.println(searchInDuplicateElementArray(arr, 0, n - 1, key));
    }
}

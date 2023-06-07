import java.util.Collections;
import java.util.ArrayList;

public class ReverseArrayInGroups {

   /*
   Reverse array in groups

    Given an array arr[] of positive integers of size N. Reverse every sub-array group of size K.

    Note: If at any instance, there are no more subarrays of size greater than or equal to K,
    then reverse the last subarray (irrespective of its size). You shouldn't return any array, modify the given array in-place.
    Example 1:

    Input:
    N = 5, K = 3
    arr[] = {1,2,3,4,5}
    Output: 3 2 1 5 4
    Explanation: First group consists of elements 1, 2, 3. Second group consists of 4,5.*/

    private static void reverse(ArrayList<Integer> arr, int low, int high) {
        while(low<high){
            Collections.swap(arr, low, high);
            low++;
            high--;
        }
    }
    public static void reverseInGroups(ArrayList<Integer> arr, int n, int k) {
        // code here
        int temp = n, j = 0;
        while(temp >= k)
        {
            reverse(arr, j, j+k-1);
            j += k;
            temp -= k;
        }
        reverse(arr,j,j+temp-1);
    }
}

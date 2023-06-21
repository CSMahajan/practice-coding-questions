/*
Second Largest

Given an array Arr of size N, print second largest distinct element from an array.

Example 1:

Input:
N = 6
Arr[] = {12, 35, 1, 10, 34, 1}
Output: 34
Explanation: The largest element of the
array is 35 and the second largest element
is 34.
Example 2:

Input:
N = 3
Arr[] = {10, 5, 10}
Output: 5
Explanation: The largest element of
the array is 10 and the second
largest element is 5.
*/
public class SecondLargestElement {

    public static int getSecondLargestElement(int arr[], int n) {
        // code here
        if (n < 2)
            return -1;
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max < arr[i]) {
                secondMax = max;
                max = arr[i];
            } else if (secondMax < arr[i] && arr[i] != max) {
                secondMax = arr[i];
            }
        }
        return secondMax == Integer.MIN_VALUE ? -1 : secondMax;
    }

    public static void main(String[] args) {
        int arr[] = {1, 6, 4, 50, 5, 50, 65, 65};
        System.out.println(getSecondLargestElement(arr, arr.length));
    }
}

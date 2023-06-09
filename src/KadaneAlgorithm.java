/*
Kadane's Algorithm

Given an array Arr[] of N integers. Find the contiguous sub-array(containing at least one number) which has the maximum sum and return its sum.

Example 1:

Input:
N = 5
Arr[] = {1,2,3,-2,5}
Output:
9
Explanation:
Max subarray sum is 9
of elements (1, 2, 3, -2, 5) which
is a contiguous subarray.
*/

public class KadaneAlgorithm {

    public static long maxSubarraySum(int arr[], int n) {
        // Your code here
        long max = Long.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (max < sum) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = {-1, -2, -3, -4};
        System.out.println(maxSubarraySum(arr, 4));
    }
}

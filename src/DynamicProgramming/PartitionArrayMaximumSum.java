package DynamicProgramming;

import java.util.Arrays;

/*
Partition Array for Maximum Sum

Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning.
Test cases are generated so that the answer fits in a 32-bit integer.
Example 1:
Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:
Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:
Input: arr = [1], k = 1
Output: 1
*/
public class PartitionArrayMaximumSum {

    public int maxSumAfterPartitioningTopDown(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return maxSumAfterPartitioningTopDown(0, n, arr, k, dp);
    }

    //Time Complexity: O(N*k)
    //Reason: There are a total of N states and for each state, we are running a loop from 0 to k.
    //Space Complexity: O(N) + Auxiliary stack space O(N)
    //Reason: First O(N) for the dp array we are using.
    private int maxSumAfterPartitioningTopDown(int i, int n, int[] arr, int k, int[] dp) {
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int lengthOfCurrentPartition = 0;
        int maxElementOfCurrentPartition = Integer.MIN_VALUE;
        int maximumSumOfArray = Integer.MIN_VALUE;
        for (int j = i; j < Math.min(n, i + k); j++) {
            lengthOfCurrentPartition++;
            maxElementOfCurrentPartition = Math.max(maxElementOfCurrentPartition, arr[j]);
            int sumOfCurrentPartition = lengthOfCurrentPartition * maxElementOfCurrentPartition +
                    maxSumAfterPartitioningTopDown(j + 1, n, arr, k, dp);
            maximumSumOfArray = Math.max(maximumSumOfArray, sumOfCurrentPartition);
        }
        return dp[i] = maximumSumOfArray;
    }

    //Time Complexity: O(N*k)
    //Reason: There are a total of N states and for each state, we are running a loop from 0 to k.
    //Space Complexity: O(N)
    //Reason: O(N) for the dp array we are using.
    public int maxSumAfterPartitioningBottomUp(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int lengthOfCurrentPartition = 0;
            int maxElementOfCurrentPartition = Integer.MIN_VALUE;
            int maximumSumOfArray = Integer.MIN_VALUE;
            for (int j = i; j < Math.min(n, i + k); j++) {
                lengthOfCurrentPartition++;
                maxElementOfCurrentPartition = Math.max(maxElementOfCurrentPartition, arr[j]);
                int sumOfCurrentPartition = lengthOfCurrentPartition * maxElementOfCurrentPartition + dp[j + 1];
                maximumSumOfArray = Math.max(maximumSumOfArray, sumOfCurrentPartition);
            }
            dp[i] = maximumSumOfArray;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        PartitionArrayMaximumSum pams = new PartitionArrayMaximumSum();
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        System.out.println(pams.maxSumAfterPartitioningTopDown(arr, k));
        System.out.println(pams.maxSumAfterPartitioningBottomUp(arr, k));
    }
}

package DynamicProgramming;

/*
Perfect Sum Problem

Given an array arr[] of non-negative integers and an integer sum,
the task is to count all subsets of the given array with a sum equal to a given sum.
Note: Answer can be very large, so, output answer modulo 109+7
Example 1:
Input: N = 6, arr[] = {2, 3, 5, 6, 8, 10}
       sum = 10
Output: 3
Explanation: {2, 3, 5}, {2, 8}, {10}
Example 2:
Input: N = 5, arr[] = {1, 2, 3, 4, 5}
       sum = 10
Output: 3
Explanation: {1, 2, 3, 4}, {1, 4, 5}, {2, 3, 5}
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SubsetSumTargetCount {

    public int perfectSumTopDown(int[] arr, int n, int sum) {
        // Your code goes here
        int mod = 1000000007;
        Arrays.sort(arr);
        reverseArray(arr,0,n-1);
        int[][] dp = new int[n][sum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return perfectSumTopDown(n - 1, sum, arr, dp)%mod;
    }

    public void reverseArray(int arr[], int start, int end)
    {
        while (start < end)
        {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    //Time Complexity: O(N*targetSum)
    //Reason: There are N*targetSum states therefore at max ‘N*targetSum’ new problems will be solved.
    //Space Complexity: O(N*targetSum) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*targetSum)).
    private int perfectSumTopDown(int index, int targetSum, int[] arr, int[][] dp) {
        if (targetSum == 0) {
            return 1;
        }
        if (index == 0) {
            return arr[0] == targetSum ? 1 : 0;
        }
        if (dp[index][targetSum] != -1) {
            return dp[index][targetSum];
        }
        int notPickCurrentElement = perfectSumTopDown(index - 1, targetSum, arr, dp);
        int pickCurrentElement = 0;
        if (arr[index] <= targetSum) {
            pickCurrentElement = perfectSumTopDown(index - 1, targetSum - arr[index], arr, dp);
        }
        return dp[index][targetSum] = notPickCurrentElement + pickCurrentElement;
    }

    //Time Complexity: O(N*sum)
    //Reason: There are two nested loops
    //Space Complexity: O(N*sum)
    //Reason: We are using an external array of size ‘N*sum’. Stack Space is eliminated.
    public int perfectSumBottomUp(int[] arr, int n, int sum) {
        // Your code goes here
        int mod = 1000000007;
        Arrays.sort(arr);
        reverseArray(arr,0,n-1);
        int[][] dp = new int[n][sum + 1];
        for (int index = 0; index < n; index++) {
            dp[index][0] = 1;
        }
        if (arr[0] <= sum) {
            dp[0][arr[0]] = 1;
        }
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= sum; target++) {
                int notPickCurrentElement = dp[index - 1][target];
                int pickCurrentElement = 0;
                if (arr[index] <= target) {
                    pickCurrentElement = dp[index - 1][target - arr[index]];
                }
                dp[index][target] = (notPickCurrentElement + pickCurrentElement)%mod;
            }
        }
        return dp[n - 1][sum]%mod;
    }

    //Time Complexity: O(N*sum)
    //Reason: There are two nested loops
    //Space Complexity: O(sum)
    //Reason: We are using an external array of size ‘sum+1’ to store only one row.
    public int perfectSumSpaceOptimised(int[] arr, int n, int sum) {
        // Your code goes here
        int mod = 1000000007;
        Arrays.sort(arr);
        reverseArray(arr,0,n-1);
        int[] previousRow = new int[sum + 1];
        int[] currentRow = new int[sum + 1];
        previousRow[0] = 1;
        if (arr[0] <= sum) {
            previousRow[arr[0]] = 1;
        }
        for (int index = 1; index < n; index++) {
            currentRow[0] = 1;
            for (int target = 0; target <= sum; target++) {
                int notPickCurrentElement = previousRow[target];
                int pickCurrentElement = 0;
                if (arr[index] <= target) {
                    pickCurrentElement = previousRow[target - arr[index]];
                }
                currentRow[target] = (notPickCurrentElement + pickCurrentElement)%mod;
            }
            previousRow = currentRow.clone();
        }
        return previousRow[sum]%mod;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 6, 8, 10};
        int targetSum = 10;
        SubsetSumTargetCount sstc = new SubsetSumTargetCount();
        System.out.println(sstc.perfectSumTopDown(arr, arr.length, targetSum));
        System.out.println(sstc.perfectSumBottomUp(arr, arr.length, targetSum));
        System.out.println(sstc.perfectSumSpaceOptimised(arr, arr.length, targetSum));
    }
}

package DynamicProgramming;

import java.util.Arrays;

/*
Subset Sum Problem

Given an array of non-negative integers, and a value sum,
determine if there is a subset of the given set with sum equal to given sum.
Example 1:
Input:
N = 6
arr[] = {3, 34, 4, 12, 5, 2}
sum = 9
Output: 1
Explanation: Here there exists a subset with sum = 9, 4+3+2 = 9.
Example 2:
Input:
N = 6
arr[] = {3, 34, 4, 12, 5, 2}
sum = 30
Output: 0
Explanation: There is no subset with sum 30.
*/
public class SubsetSumTarget {

    public static Boolean isSubsetSumTopDown(int N, int[] arr, int sum) {
        // code here
        //our dp is of size N * (targetSum+1) because at 0, we are returning and starting to call from targetSum itself
        //to avoid arrayindexoutofbounds error
        int[][] dp = new int[N][sum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return isSubsetSumTopDown(N - 1, arr, sum, dp);
    }

    //Time Complexity: O(N*targetSum)
    //Reason: There are N*targetSum states therefore at max ‘N*targetSum’ new problems will be solved.
    //Space Complexity: O(N*targetSum) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*targetSum)).
    private static Boolean isSubsetSumTopDown(int index, int[] arr, int targetSum, int[][] dp) {
        if (index == 0) {
            return arr[0] == targetSum;
        }
        if (targetSum == 0) {
            return true;
        }
        if (dp[index][targetSum] != -1) {
            //dp[index][targetSum] != 0 signifies that it has the value, 0 means false, non zero (non minus one) means true
            return dp[index][targetSum] != 0;
        }
        boolean notPickCurrentElement = isSubsetSumTopDown(index - 1, arr, targetSum, dp);
        boolean pickCurrentElement = false;
        if (arr[index] <= targetSum) {
            pickCurrentElement = isSubsetSumTopDown(index - 1, arr, targetSum - arr[index], dp);
        }
        //We will assign 1 to dp array if either not picking or picking current element is true, otherwise 0
        dp[index][targetSum] = notPickCurrentElement || pickCurrentElement ? 1 : 0;
        return notPickCurrentElement || pickCurrentElement;
    }

    //Time Complexity: O(N*targetSum)
    //Reason: There are two nested loops
    //Space Complexity: O(N*targetSum)
    //Reason: We are using an external array of size ‘N*targetSum’. Stack Space is eliminated.
    public static Boolean isSubsetSumBottomUp(int N, int[] arr, int targetSum) {
        // code here
        //our dp is of size N * (targetSum+1) because at 0, we are returning and starting to call from targetSum itself
        //to avoid arrayindexoutofbounds error
        boolean[][] dp = new boolean[N][targetSum + 1];

        //if the target is reached as 0 then we have found the subsequence/subset
        //so starting to put with dp[index][0] as true
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        //till reaching of first element of array if targetSum is still greater or equal than first element,
        //so at 0th index for 0th element marking it as true
        if (arr[0] <= targetSum) {
            dp[0][arr[0]] = true;
        }
        //For both index as 0 and target as 0 we have figured out a base case, so starting loops with 1 for both
        for (int index = 1; index < N; index++) {
            for (int target = 1; target <= targetSum; target++) {
                boolean notPickCurrentElement = dp[index - 1][target];
                boolean pickCurrentElement = false;
                if (arr[index] <= target) {
                    pickCurrentElement = dp[index - 1][target - arr[index]];
                }
                dp[index][target] = notPickCurrentElement || pickCurrentElement;
            }
        }
        return dp[N - 1][targetSum];
    }

    //Time Complexity: O(N*targetSum)
    //Reason: There are three nested loops
    //Space Complexity: O(targetSum)
    //Reason: We are using an external array of size ‘targetSum+1’ to store only one row.
    public static Boolean isSubsetSumSpaceOptimised(int N, int[] arr, int sum) {
        // code here
        //our previousRow is of size N * (targetSum+1) because at 0, we are returning and starting to call from targetSum itself
        //to avoid arrayindexoutofbounds error
        boolean[] previousRow = new boolean[sum + 1];
        boolean[] currentRow = new boolean[sum + 1];

        //if the target is reached as 0 then we have found the subsequence/subset
        //so starting to put with previousRow[0] as true
        previousRow[0] = true;
        //till reaching of first element of array if sum is still greater or equal than first element,
        //so at 0th index for 0th element marking it as true
        if (arr[0] <= sum) {
            previousRow[arr[0]] = true;
        }
        //For both index as 0 and target as 0 we have figured out a base case, so starting loops with 1 for both
        for (int index = 1; index < N; index++) {
            for (int target = 1; target <= sum; target++) {
                boolean notPickCurrentElement = previousRow[target];
                boolean pickCurrentElement = false;
                if (arr[index] <= target) {
                    pickCurrentElement = previousRow[target - arr[index]];
                }
                currentRow[target] = notPickCurrentElement || pickCurrentElement;
            }
            previousRow = currentRow.clone();
        }
        return previousRow[sum];
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int targetSum = 9;
        System.out.println(isSubsetSumTopDown(arr.length, arr, targetSum));
        System.out.println(isSubsetSumBottomUp(arr.length, arr, targetSum));
        System.out.println(isSubsetSumSpaceOptimised(arr.length, arr, targetSum));

    }
}

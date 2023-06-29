package DynamicProgramming;

/*
Max Sum without Adjacents

Given an array Arr of size N containing positive integers. Find the maximum sum of a subsequence such that no two numbers in the sequence should be adjacent in the array.

Example 1:

Input:
N = 6
Arr[] = {5, 5, 10, 100, 10, 5}
Output: 110
Explanation: If you take indices 0, 3
and 5, then Arr[0]+Arr[3]+Arr[5] =
5+100+5 = 110.
Example 2:
Input:
N = 4
Arr[] = {3, 2, 7, 10}
Output: 13
Explanation: 3 and 10 forms a non-continuous subsequence with maximum sum.
*/

import java.util.Arrays;

public class MaxSumNonAdjacentElements {

    public int findMaxSumTopDown(int[] arr, int n) {
        // code here
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return findMaxSumTopDown(arr, dp, n - 1);
    }

    //TC:O(N), we are calling recursively which would give as O(2^N), but once calculated result is not computed again,
    //so we are calling for every index from 0 to N-1 only once, so it is O(N)
    //SC:O(N) + O(N), for recursion stack space and dp array
    private int findMaxSumTopDown(int[] arr, int[] dp, int index) {
        //for negative element indexes, we will consider them as 0 which occurs for index - 2
        if (index < 0) return 0;
        //index == 0 occurs when index=1 element is not picked, as we have to maximise the sum we pick that element
        if (index == 0) return arr[0];
        //sum for that index as a sub-problem is already calculated so returning the same for memoization
        if (dp[index] != -1) return dp[index];
        //picking up the current element and calling for index-2 as adjacent elements are not allowed
        int pickCurrentElement = arr[index] + findMaxSumTopDown(arr, dp, index - 2);
        //notPick can be mentioned as 0 + findMaxSumTopDown(arr, dp, index - 1)
        int notPickCurrentElement = findMaxSumTopDown(arr, dp, index - 1);
        //storing result in dp array
        return dp[index] = Math.max(pickCurrentElement, notPickCurrentElement);
    }

    public int findMaxSumBottomUp(int[] arr, int n) {
        // code here
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return findMaxSumBottomUp(arr, dp, n);
    }

    //TC:O(N), we are getting results for 0 to N-1 through dp array, once calculated result is stored in dp array
    //SC:O(N) , for dp array
    private int findMaxSumBottomUp(int[] arr, int[] dp, int n) {
        //index == 0 occurs when index=1 element is not picked, as we have to maximise the sum we pick that element
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            //picking up the current element and calling for index-2 as adjacent elements are not allowed
            int pickCurrentElement = arr[i];
            if (i > 1) {
                pickCurrentElement += dp[i - 2];
            }
            //notPick can be mentioned as dp[i - 1]
            int notPickCurrentElement = dp[i - 1];
            dp[i] = Math.max(pickCurrentElement, notPickCurrentElement);
        }
        //storing result in dp array
        return dp[n - 1];
    }

    //TC:O(N), we are using two variables and updating them while getting current maximum sum
    //SC:O(1)
    public int findMaxSumSpaceOptimised(int[] arr, int n) {
        int previous1 = arr[0];
        int previous2 = 0;
        for (int i = 1; i < n; i++) {
            //picking up the current element and calling for index-2 as adjacent elements are not allowed
            int pickCurrentElement = arr[i];
            if (i > 1) {
                pickCurrentElement += previous2;
            }
            //notPick can be mentioned as 0 + previous1
            int notPickCurrentElement = previous1;
            int currentMaximumSum = Math.max(pickCurrentElement, notPickCurrentElement);
            previous2 = previous1;
            previous1 = currentMaximumSum;
        }
        //when loop ends i becomes n, i.e.i=n and previous1 stays at n-1
        //which is last index in array which is our required answer
        return previous1;
    }

    public static void main(String[] args) {
        MaxSumNonAdjacentElements msnae = new MaxSumNonAdjacentElements();
        int[] height = {5, 5, 10, 100, 10, 5};
        System.out.println(msnae.findMaxSumTopDown(height, height.length));
        System.out.println(msnae.findMaxSumBottomUp(height, height.length));
        System.out.println(msnae.findMaxSumSpaceOptimised(height, height.length));
    }
}

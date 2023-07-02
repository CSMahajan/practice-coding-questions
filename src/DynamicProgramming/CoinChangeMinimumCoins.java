package DynamicProgramming;

import java.util.Arrays;

/*
LeetCode

Coin Change

You are given an integer array coins representing coins of different denominations and
an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.
Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:
Input: coins = [2], amount = 3
Output: -1
Example 3:
Input: coins = [1], amount = 0
Output: 0

Code Studio/Coding Ninja

Minimum Coins

You are given an array of N distinct integers coins and an integer X representing the target sum.
You have to tell the minimum number of elements you have to take to reach the target sum X.
You have an infinite supply of coins of each denomination(array element)
Example 1:
Input: arr= {1,2,3}, target sum = 7
Output:3
Explanation:
Way 1 - You can take 4 elements  [2, 2, 2, 1] as 2 + 2 + 2 + 1 = 7.
Way 2 - You can take 3 elements  [3, 3, 1] as 3 + 3 + 1 = 7.
Here, you can see in Way 2 we have used 3 coins to reach the target sum of 7.
Hence the output is 3.
Example 2:
Input: arr= {12,1,3}, target sum = 4
Output: 2
Explanation: [1,3] elements for the target sum of 4
*/
public class CoinChangeMinimumCoins {

    public static int minimumElementsTopDown(int[] arr, int targetSum) {
        // Write your code here..
        int n = arr.length;
        int[][] dp = new int[n][targetSum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int minimumCoins = minimumElementsTopDown(n - 1, targetSum, arr, dp);
        if (minimumCoins >= Math.pow(10, 9)) {
            return -1;
        }
        return minimumCoins;
    }

    //Time Complexity: O(N*targetSum)
    //Reason: There are N*T states therefore at max ‘N*T’ new problems will be solved.
    //Space Complexity: O(N*targetSum) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*targetSum)).
    private static int minimumElementsTopDown(int index, int targetSum, int[] arr, int[][] dp) {
        if (index == 0) {
            //index is 0, means temporarily consider case where array has only one element
            //if that only element completely divides the leftover targetSum then its quotient division is number of coins required
            //arr={3}, target sum =12, so 3 completely divides 12 and we require 12/3 = 4 coins
            if (targetSum % arr[0] == 0) {
                return targetSum / arr[0];
            } else {
                //otherwise returning very high number,but don't return Integer.MAX_VALUE due to Integer Overflow
                //also don't return 0 because that element is not considered as a coin, but it is not completing
                //the given target sum so is invalid number of coins
                return (int) Math.pow(10, 9);
            }
        }
        if (dp[index][targetSum] != -1) {
            return dp[index][targetSum];
        }
        int notPickCurrentElement = minimumElementsTopDown(index - 1, targetSum, arr, dp);
        //Assigning INT MAX to pick because we want to find the minimum
        //Note:Always pick will have opposite initialized to what our objective of problem statement is.
        int pickCurrentElement = Integer.MAX_VALUE;
        if (arr[index] <= targetSum) {
            //here while picking current element, we are not reducing index because every element is supplied infinite number of times
            //we will reduce target sum which will eventually end in above if conditions
            //we will only increase picks count by 1 as we are considering that coin one occurrence at a time
            pickCurrentElement = 1 + minimumElementsTopDown(index, targetSum - arr[index], arr, dp);
        }
        return dp[index][targetSum] = Math.min(notPickCurrentElement, pickCurrentElement);
    }

    //Time Complexity: O(N*targetSum)
    //Reason: There are two nested loops
    //Space Complexity: O(N*targetSum)
    //Reason: We are using an external array of size ‘N*targetSum’. Stack Space is eliminated.
    public static int minimumElementsBottomUp(int[] arr, int targetSum) {
        // Write your code here..
        int n = arr.length;
        int[][] dp = new int[n][targetSum + 1];

        for (int target = 0; target <= targetSum; target++) {
            if (target % arr[0] == 0) {
                //for index as 0 for every row in dp[index][target], for that target, if first element is divisible then division is number of coins
                dp[0][target] = target / arr[0];
            } else {
                //otherwise very large number
                dp[0][target] = (int) Math.pow(10, 9);
            }
        }
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= targetSum; target++) {
                int notPickCurrentElement = dp[index - 1][target];
                //Assigning INT MAX to pick because we want to find the minimum
                //Note:Always pick will have opposite initialized to what our objective of problem statement is.
                int pickCurrentElement = Integer.MAX_VALUE;
                if (arr[index] <= target) {
                    //here while picking current element, we are not reducing index because every element is supplied infinite number of times
                    //we will reduce target sum which will eventually end in above if conditions
                    //we will only increase picks count by 1 as we are considering that coin one occurrence at a time
                    pickCurrentElement = 1 + dp[index][target - arr[index]];
                }
                dp[index][target] = Math.min(notPickCurrentElement, pickCurrentElement);
            }
        }
        int minimumCoins = dp[n-1][targetSum];
        if(minimumCoins >= Math.pow(10,9)) {
            return -1;
        }
        return minimumCoins;
    }

    //Time Complexity: O(N*T)
    //Reason: There are two nested loops.
    //Space Complexity: O(T)
    //Reason: We are using two external arrays of size ‘T+1’.
    public static int minimumElementsSpaceOptimised(int[] arr, int targetSum) {
        // Write your code here..
        int n = arr.length;
        int[] previousRow = new int[targetSum + 1];
        int[] currentRow = new int[targetSum + 1];

        for (int target = 0; target <= targetSum; target++) {
            if (target % arr[0] == 0) {
                //for index as 0 for every row in dp[index][target], for that target, if first element is divisible then division is number of coins
                previousRow[target] = target / arr[0];
            } else {
                //otherwise very large number
                previousRow[target] = (int) Math.pow(10, 9);
            }
        }
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= targetSum; target++) {
                int notPickCurrentElement = previousRow[target];
                //Assigning INT MAX to pick because we want to find the minimum
                //Note:Always pick will have opposite initialized to what our objective of problem statement is.
                int pickCurrentElement = Integer.MAX_VALUE;
                if (arr[index] <= target) {
                    //here while picking current element, we are not reducing index because every element is supplied infinite number of times
                    //we will reduce target sum which will eventually end in above if conditions
                    //we will only increase picks count by 1 as we are considering that coin one occurrence at a time
                    pickCurrentElement = 1 + currentRow[target - arr[index]];
                }
                currentRow[target] = Math.min(notPickCurrentElement, pickCurrentElement);
            }
            previousRow = currentRow.clone();
        }
        int minimumCoins = previousRow[targetSum];
        if(minimumCoins >= Math.pow(10,9)) {
            return -1;
        }
        return minimumCoins;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int targetSum = 7;
        System.out.println(minimumElementsTopDown(arr, targetSum));
        System.out.println(minimumElementsBottomUp(arr, targetSum));
        System.out.println(minimumElementsSpaceOptimised(arr, targetSum));
    }
}

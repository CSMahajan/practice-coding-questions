package DynamicProgramming;

import java.util.Arrays;

/*
0 - 1 Knapsack Problem

You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
Note that we have only one quantity of each item.
In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items respectively.
Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that
sum of the weights of this subset is smaller than or equal to W.
You cannot break an item, either pick the complete item or dont pick it (0-1 property).
Example 1:
Input:
N = 3
W = 4
values[] = {1,2,3}
weight[] = {4,5,1}
Output: 3
Example 2:
Input:
N = 3
W = 3
values[] = {1,2,3}
weight[] = {4,5,6}
Output: 0
*/
public class Knapsack01 {

    public static int knapSackTopDown(int W, int[] wt, int[] val, int n) {
        // your code here
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return knapSackTopDown(n - 1, W, wt, val, dp);
    }

    //Time Complexity: O(N*W)
    //Reason: There are N*W states therefore at max ‘N*W’ new problems will be solved.
    //Space Complexity: O(N*W) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*W)).
    private static int knapSackTopDown(int index, int maxWeight, int[] wt, int[] val, int[][] dp) {
        if (index == 0) {
            //At 0th index if we still have the capacity weight W then we can take that weight if its less than or equal to W
            //because we want to maximise value which is possible if we pick that weight
            if (wt[0] <= maxWeight) {
                return val[0];
            } else {
                //bag will become overloaded so can't add that 0th index weight into the bag
                return 0;
            }
        }
        if (dp[index][maxWeight] != -1) {
            return dp[index][maxWeight];
        }

        int notPickCurrentWeight = knapSackTopDown(index - 1, maxWeight, wt, val, dp);
        int pickCurrentWeight = Integer.MIN_VALUE;
        if (wt[index] <= maxWeight) {
            pickCurrentWeight = val[index] + knapSackTopDown(index - 1, maxWeight - wt[index], wt, val, dp);
        }
        return dp[index][maxWeight] = Math.max(notPickCurrentWeight, pickCurrentWeight);
    }

    //Time Complexity: O(N*W)
    //Reason: There are two nested loops
    //Space Complexity: O(N*W)
    //Reason: We are using an external array of size ‘N*W’. Stack Space is eliminated.
    public static int knapSackBottomUp(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n][W + 1];
        //for every weight at 0th index we will have the value of 0th index in dp array for each element in 0th row
        //This is top-down approach base case converted to bottom-up approach to represent in dp array
        for (int weight = wt[0]; weight <= W; weight++) {
            dp[0][weight] = val[0];
        }
        for (int index = 1; index < n; index++) {
            for (int weight = 0; weight <= W; weight++) {
                int notPickCurrentWeight = dp[index - 1][weight];
                int pickCurrentWeight = Integer.MIN_VALUE;
                if (wt[index] <= weight) {
                    pickCurrentWeight = val[index] + dp[index - 1][weight - wt[index]];
                }
                dp[index][weight] = Math.max(notPickCurrentWeight, pickCurrentWeight);
            }
        }
        return dp[n - 1][W];
    }

    //Time Complexity: O(N*W)
    //Reason: There are two nested loops.
    //Space Complexity: O(W)+O(W)
    //Reason: We are using an external array of size ‘W+1’ to store current row and previous row.
    public static int knapSackSpaceOptimisedTwoArrays(int W, int[] wt, int[] val, int n) {
        int[] previousRow = new int[W + 1];
        int[] currentRow = new int[W + 1];
        //for every weight at 0th index we will have the value of 0th index in dp array for each element in 0th row
        //This is top-down approach base case converted to space-optimised approach to represent in dp array
        for (int weight = wt[0]; weight <= W; weight++) {
            previousRow[weight] = val[0];
        }
        for (int index = 1; index < n; index++) {
            //If we populate the table using tabulation, we will get to know that for filling any row, we require only previousRow
            //and we only require values of indexes less than our current index
            //so current rows values can be calculated using current rows previous indexes and previousRow
            //so our loop starts from W to 0 in order to preserve populating currentRow
            //here if we use 2 separate arrays we can go from weight = 0 to maxWeight(W)
            for (int weight = W; weight >= 0; weight--) {
                int notPickCurrentWeight = previousRow[weight];
                int pickCurrentWeight = Integer.MIN_VALUE;
                if (wt[index] <= weight) {
                    pickCurrentWeight = val[index] + previousRow[weight - wt[index]];
                }
                currentRow[weight] = Math.max(notPickCurrentWeight, pickCurrentWeight);
            }
            previousRow = currentRow.clone();
        }
        return previousRow[W];
    }

    //Time Complexity: O(N*W)
    //Reason: There are two nested loops.
    //Space Complexity: O(W)
    //Reason: We are using an external array of size ‘W+1’ to store only one row.
    public static int knapSackSpaceOptimisedOneArray(int W, int[] wt, int[] val, int n) {
        int[] previousRow = new int[W + 1];
        //for every weight at 0th index we will have the value of 0th index in dp array for each element in 0th row
        //This is top-down approach base case converted to space-optimised approach to represent in dp array
        for (int weight = wt[0]; weight <= W; weight++) {
            previousRow[weight] = val[0];
        }
        for (int index = 1; index < n; index++) {
            //If we populate the table using tabulation, we will get to know that for filling any row, we require only previousRow
            //and we only require values of indexes less than our current index
            //so current rows values can be calculated using current rows previous indexes and previousRow
            //so our loop starts from W to 0 in order to preserve populating currentRow, which will itself be populated into previousRow
            for (int weight = W; weight >= 0; weight--) {
                int notPickCurrentWeight = previousRow[weight];
                int pickCurrentWeight = Integer.MIN_VALUE;
                if (wt[index] <= weight) {
                    pickCurrentWeight = val[index] + previousRow[weight - wt[index]];
                }
                //instead of storing in current row, we will use the same previousRow and update value in that row itself
                //so eliminating extra currentRow array
                previousRow[weight] = Math.max(notPickCurrentWeight, pickCurrentWeight);
            }
        }
        return previousRow[W];
    }

    public static void main(String[] args) {
        int N = 3;
        int W = 4;
        int[] values = {1, 2, 3};
        int[] weight = {4, 5, 1};
        System.out.println(knapSackTopDown(W, weight, values, N));
        System.out.println(knapSackBottomUp(W, weight, values, N));
        System.out.println(knapSackSpaceOptimisedTwoArrays(W, weight, values, N));
        System.out.println(knapSackSpaceOptimisedOneArray(W, weight, values, N));
    }
}

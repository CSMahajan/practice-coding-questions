package DynamicProgramming;

import java.util.Arrays;

/*
Knapsack with Duplicate Items

Given a set of N items, each with a weight and a value, represented by the array w[] and val[] respectively.
Also, a knapsack with weight limit W.
The task is to fill the knapsack in such a way that we can get the maximum profit. Return the maximum profit.
Note: Each item can be taken any number of times.
Example 1:
Input: N = 2, W = 3
val[] = {1, 1}
wt[] = {2, 1}
Output: 3
Explanation:
1.Pick the 2nd element thrice.
2.Total profit = 1 + 1 + 1 = 3. Also the total weight = 1 + 1 + 1  = 3 which is <= W.
Example 2:
Input: N = 4, W = 8
val[] = {1, 4, 5, 7}
wt[] = {1, 3, 4, 5}
Output: 11
Explanation: The optimal choice is to pick the 2nd and 4th element.
*/
public class KnapsackUnboundedDuplicateItems {

    public static int knapSackDuplicateItemsTopDown(int N, int maxWeightOfBag, int[] val, int[] wt) {
        // code here
        int n = val.length;
        int[][] dp = new int[n][maxWeightOfBag + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return knapSackDuplicateItemsTopDown(n - 1, maxWeightOfBag, val, wt, dp);
    }

    //Time Complexity: O(N*maxWeightOfBag)
    //Reason: There are N*W states therefore at max 'N*maxWeightOfBag' new problems will be solved.
    //Space Complexity: O(N*maxWeightOfBag) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*maxWeightOfBag)).
    private static int knapSackDuplicateItemsTopDown(int index, int maxWeightOfBag, int[] val, int[] wt, int[][] dp) {
        if (index == 0) {
            return (maxWeightOfBag / wt[0]) * val[0];
        }
        if (dp[index][maxWeightOfBag] != -1) {
            return dp[index][maxWeightOfBag];
        }
        int notPickCurrentWeight = knapSackDuplicateItemsTopDown(index - 1, maxWeightOfBag, val, wt, dp);
        int pickCurrentWeight = Integer.MIN_VALUE;
        if (wt[index] <= maxWeightOfBag) {
            pickCurrentWeight = val[index] + knapSackDuplicateItemsTopDown(index, maxWeightOfBag - wt[index], val, wt, dp);
        }
        return dp[index][maxWeightOfBag] = Math.max(notPickCurrentWeight, pickCurrentWeight);
    }

    //Time Complexity: O(N*maxWeightOfBag)
    //Reason: There are two nested loops
    //Space Complexity: O(N*maxWeightOfBag)
    //Reason: We are using an external array of size 'N*maxWeightOfBag'. Stack Space is eliminated.
    public static int knapSackDuplicateItemsBottomUp(int N, int maxWeightOfBag, int[] val, int[] wt) {
        // code here
        int n = val.length;
        int[][] dp = new int[n][maxWeightOfBag + 1];
        for (int weight = 0; weight <= maxWeightOfBag; weight++) {
            dp[0][weight] = (weight / wt[0]) * val[0];
        }
        for (int index = 1; index < n; index++) {
            for (int weight = 0; weight <= maxWeightOfBag; weight++) {
                int notPickCurrentWeight = dp[index - 1][weight];
                int pickCurrentWeight = Integer.MIN_VALUE;
                if (wt[index] <= weight) {
                    pickCurrentWeight = val[index] + dp[index][weight - wt[index]];
                }
                dp[index][weight] = Math.max(notPickCurrentWeight, pickCurrentWeight);
            }
        }
        return dp[n - 1][maxWeightOfBag];
    }

    //Time Complexity: O(N*maxWeightOfBag)
    //Reason: There are two nested loops.
    //Space Complexity: O(maxWeightOfBag) + O(maxWeightOfBag)
    //Reason: We are using an external array of size 'maxWeightOfBag+1' to store only previous and current row.
    public static int knapSackDuplicateItemsSpaceOptimisedTwoArrays(int N, int maxWeightOfBag, int[] val, int[] wt) {
        // code here
        int n = val.length;
        int[] previousRow = new int[maxWeightOfBag + 1];
        int[] currentRow = new int[maxWeightOfBag + 1];
        for (int weight = 0; weight <= maxWeightOfBag; weight++) {
            previousRow[weight] = (weight / wt[0]) * val[0];
        }
        for (int index = 1; index < n; index++) {
            for (int weight = 0; weight <= maxWeightOfBag; weight++) {
                int notPickCurrentWeight = previousRow[weight];
                int pickCurrentWeight = Integer.MIN_VALUE;
                if (wt[index] <= weight) {
                    pickCurrentWeight = val[index] + currentRow[weight - wt[index]];
                }
                currentRow[weight] = Math.max(notPickCurrentWeight, pickCurrentWeight);
            }
            previousRow = currentRow.clone();
        }
        return previousRow[maxWeightOfBag];
    }

    //Time Complexity: O(N*maxWeightOfBag)
    //Reason: There are two nested loops.
    //Space Complexity: O(maxWeightOfBag)
    //Reason: We are using an external array of size 'maxWeightOfBag+1' to store only previous(as well as current) row.
    public static int knapSackDuplicateItemsSpaceOptimisedOneArray(int N, int maxWeightOfBag, int[] val, int[] wt) {
        // code here
        int n = val.length;
        int[] previousRow = new int[maxWeightOfBag + 1];
        for (int weight = 0; weight <= maxWeightOfBag; weight++) {
            previousRow[weight] = (weight / wt[0]) * val[0];
        }
        for (int index = 1; index < n; index++) {
            for (int weight = 0; weight <= maxWeightOfBag; weight++) {
                int notPickCurrentWeight = previousRow[weight];
                int pickCurrentWeight = Integer.MIN_VALUE;
                if (wt[index] <= weight) {
                    pickCurrentWeight = val[index] + previousRow[weight - wt[index]];
                }
                previousRow[weight] = Math.max(notPickCurrentWeight, pickCurrentWeight);
            }
        }
        return previousRow[maxWeightOfBag];
    }

    public static void main(String[] args) {
        int[] values = {1, 4, 5, 7};
        int[] weight = {1, 3, 4, 5};
        int maxWeight = 8;
        int N = values.length;
        System.out.println(knapSackDuplicateItemsTopDown(N, maxWeight, values, weight));
        System.out.println(knapSackDuplicateItemsBottomUp(N, maxWeight, values, weight));
        System.out.println(knapSackDuplicateItemsSpaceOptimisedTwoArrays(N, maxWeight, values, weight));
        System.out.println(knapSackDuplicateItemsSpaceOptimisedOneArray(N, maxWeight, values, weight));
    }
}

package DynamicProgramming;

import java.util.Arrays;

/*
Rod Cutting

Given a rod of length N inches and an array of prices, price[].
price[i] denotes the value of a piece of length i.
Determine the maximum value obtainable by cutting up the rod and selling the pieces.
Note: Consider 1-based indexing.
Example 1:
Input:
N = 8
Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
Output:22
Explanation:
The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5+17=22.
Example 2:
Input:
N=8
Price[] = {3, 5, 8, 9, 10, 17, 17, 20}
Output: 24
Explanation:
The maximum obtainable value is 24 by cutting the rod into 8 pieces of length 3, i.e, 8*3=24.
*/
public class RodCutting {

    public int cutRodTopDown(int[] price, int n) {
        
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return cutRodTopDown(n - 1, n, price, dp);
    }

    //Time Complexity: O(N*N)
    //Reason: N = maxRodLength, There are N*(N+1) states therefore at max ‘N*(N+1)’ new problems will be solved.
    //Space Complexity: O(N*N) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*(N+1)).
    private int cutRodTopDown(int index, int maxRodLength, int[] price, int[][] dp) {
        if (index == 0) {
            //this is done because we definitely know that at index 0,
            //it is of rodLength 1, so we would require maxRodLength / 1 = maxRodlength number of rods
            //so price of them all combined is given by maxRodLength*price[0]
            return maxRodLength * price[0];
        }
        if (dp[index][maxRodLength] != -1) {
            return dp[index][maxRodLength];
        }
        int notPickCurrentRod = cutRodTopDown(index - 1, maxRodLength, price, dp);
        int pickCurrentRod = Integer.MIN_VALUE;
        int currentRodLength = index + 1;
        if (currentRodLength <= maxRodLength) {
            pickCurrentRod = price[index] + cutRodTopDown(index, maxRodLength - currentRodLength, price, dp);
        }
        return dp[index][maxRodLength] = Math.max(pickCurrentRod, notPickCurrentRod);
    }

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops
    //Space Complexity: O(N*N)
    //Reason: We are using an external array of size ‘N*(N+1)’. Stack Space is eliminated.
    public int cutRodBottomUp(int[] price, int n) {
        
        int[][] dp = new int[n][n + 1];
        for (int rodLength = 0; rodLength <= n; rodLength++) {
            dp[0][rodLength] = rodLength * price[0];
        }
        for (int index = 1; index < n; index++) {
            for (int rodLength = 0; rodLength <= n; rodLength++) {
                int notPickCurrentRod = dp[index - 1][rodLength];
                int pickCurrentRod = Integer.MIN_VALUE;
                int currentRodLength = index + 1;
                if (currentRodLength <= rodLength) {
                    pickCurrentRod = price[index] + dp[index][rodLength - currentRodLength];
                }
                dp[index][rodLength] = Math.max(pickCurrentRod, notPickCurrentRod);
            }
        }
        return dp[n - 1][n];
    }

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops.
    //Space Complexity: O(N) + O(N)
    //Reason: We are using an external array of size ‘N+1’ to store previous and current row.
    public int cutRodSpaceOptimisedTwoArrays(int[] price, int n) {
        
        int[] previousRow = new int[n + 1];
        int[] currentRow = new int[n + 1];
        for (int rodLength = 0; rodLength <= n; rodLength++) {
            previousRow[rodLength] = rodLength * price[0];
        }
        for (int index = 1; index < n; index++) {
            for (int rodLength = 0; rodLength <= n; rodLength++) {
                int notPickCurrentRod = previousRow[rodLength];
                int pickCurrentRod = Integer.MIN_VALUE;
                int currentRodLength = index + 1;
                if (currentRodLength <= rodLength) {
                    pickCurrentRod = price[index] + currentRow[rodLength - currentRodLength];
                }
                currentRow[rodLength] = Math.max(pickCurrentRod, notPickCurrentRod);
            }
            previousRow = currentRow.clone();
        }
        return previousRow[n];
    }

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops.
    //Space Complexity: O(N)
    //Reason: We are using an external array of size ‘N+1’ to store only one row.
    public int cutRodSpaceOptimisedOneArray(int[] price, int n) {
        
        int[] previousRow = new int[n + 1];
        for (int rodLength = 0; rodLength <= n; rodLength++) {
            previousRow[rodLength] = rodLength * price[0];
        }
        for (int index = 1; index < n; index++) {
            for (int rodLength = 0; rodLength <= n; rodLength++) {
                int notPickCurrentRod = previousRow[rodLength];
                int pickCurrentRod = Integer.MIN_VALUE;
                int currentRodLength = index + 1;
                if (currentRodLength <= rodLength) {
                    pickCurrentRod = price[index] + previousRow[rodLength - currentRodLength];
                }
                previousRow[rodLength] = Math.max(pickCurrentRod, notPickCurrentRod);
            }
        }
        return previousRow[n];
    }

    public static void main(String[] args) {
        RodCutting rc = new RodCutting();
        int maxRodLength = 8;
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(rc.cutRodTopDown(price, maxRodLength));
        System.out.println(rc.cutRodBottomUp(price, maxRodLength));
        System.out.println(rc.cutRodSpaceOptimisedTwoArrays(price, maxRodLength));
        System.out.println(rc.cutRodSpaceOptimisedOneArray(price, maxRodLength));

    }
}

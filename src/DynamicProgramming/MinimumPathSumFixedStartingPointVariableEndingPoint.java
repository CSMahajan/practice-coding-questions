package DynamicProgramming;

import java.util.Arrays;

/*
Minimum Path Sum

LeetCode
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.
Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
*/
public class MinimumPathSumFixedStartingPointVariableEndingPoint {

    public int minPathSumTopDown(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return minPathSumTopDown(n - 1, m - 1, grid, dp);
    }

    /*Time Complexity: O(M*N)
      Reason: At max, there will be M*N calls of recursion.
      Space Complexity: O((N-1)+(M-1)) + O(M*N)
      Reason: We are using a recursion stack space:O((N-1)+(M-1)),
      here (N-1)+(M-1) is the path length and an external DP Array of size ‘M*N’.*/
    private int minPathSumTopDown(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0) {
            //At grid[0][0], we need to compulsorily add that cost
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            //For path going outside the grid,we will assign very large number such that while finding minimum it will not be considered path
            return (int) Math.pow(10, 9);
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int up = grid[i][j] + minPathSumTopDown(i - 1, j, grid, dp);
        int left = grid[i][j] + minPathSumTopDown(i, j - 1, grid, dp);
        return dp[i][j] = Math.min(up, left);
    }

    public int minPathSumBottomUp(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return minPathSumBottomUp(n, m, grid);
    }

    /*Time Complexity: O(M*N)
      Reason: There are two nested loops
      Space Complexity: O(M*N)
      Reason: We are using an external array of size 'M*N'. */
    private int minPathSumBottomUp(int n, int m, int[][] grid) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    int up = grid[i][j];
                    if (i > 0) {
                        up += dp[i - 1][j];
                    } else {
                        up += Math.pow(10,9);
                    }
                    int left = grid[i][j];
                    if (j > 0) {
                        left += dp[i][j - 1];
                    } else {
                        left += Math.pow(10,9);
                    }
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    /*Time Complexity: O(M*N)
      Reason: There are two nested loops
      Space Complexity: O(M)
      Reason: We are using an external array of size ‘M’ to store only one row.*/
    public int minPathSumSpaceOptimised(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] previous = new int[m];
        for (int i = 0; i < n; i++) {
            int[] currentRow = new int[m];
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    currentRow[j] = grid[i][j];
                } else {
                    int up = grid[i][j];
                    if (i > 0) {
                        up += previous[j];
                    } else {
                        up += Math.pow(10,9);
                    }
                    int left = grid[i][j];
                    if (j > 0) {
                        left += currentRow[j - 1];
                    } else {
                        left += Math.pow(10,9);
                    }
                    currentRow[j] = Math.min(up, left);
                }
            }
            previous = currentRow;
        }
        return previous[m - 1];
    }


    public static void main(String[] args) {
        MinimumPathSumFixedStartingPointVariableEndingPoint mps = new MinimumPathSumFixedStartingPointVariableEndingPoint();
        int[][] obstacleGrid = {{1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        System.out.println(mps.minPathSumTopDown(obstacleGrid));
        System.out.println(mps.minPathSumBottomUp(obstacleGrid));
        System.out.println(mps.minPathSumSpaceOptimised(obstacleGrid));
    }
}

package DynamicProgramming;

import java.util.Arrays;

/*
Number of Unique Paths

GeeksForGeeks
Given a A X B matrix with your initial position at the top-left cell,
find the number of possible unique paths to reach the bottom-right cell of the matrix from the initial position.
Note: Possible moves can be either down or right at any point in time,
i.e., we can move to matrix[i+1][j] or matrix[i][j+1] from matrix[i][j].
Example 1:
Input:
A = 2, B = 2
Output: 2
Explanation: There are only two unique paths to reach the end of the matrix of
size two from the starting cell of the matrix.
Example 2:
Input:
A = 3, B = 4
Output: 10
Explanation: There are only 10 unique paths to reach the end of the matrix of
size two from the starting cell of the matrix.

LeetCode
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.
Given the two integers m and n,
return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The test cases are generated so that the answer will be less than or equal to 2 * (10^9).
Example 1:
Input: m = 3, n = 7
Output: 28
Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
*/
public class UniquePaths {

    public int uniquePathsTopDown(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return uniquePathsTopDown(m - 1, n - 1, dp);
    }

    /*Time Complexity: O(M*N)
      Reason: At max, there will be M*N calls of recursion.
      Space Complexity: O((N-1)+(M-1)) + O(M*N)
      Reason: We are using a recursion stack space:O((N-1)+(M-1)),
      here (N-1)+(M-1) is the path length and an external DP Array of size ‘M*N’.*/
    private int uniquePathsTopDown(int i, int j, int[][] dp) {
        //To reach grid[0][0], there is only one way possible
        if (i == 0 && j == 0) {
            return 1;
        }
        //Negative i and j indicates they run out of grid, so counting them as 0 paths
        if (i < 0 || j < 0) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        //i is row index, j is column index
        int up = uniquePathsTopDown(i - 1, j, dp);
        int left = uniquePathsTopDown(i, j - 1, dp);
        //storing and returning result
        return dp[i][j] = up + left;
    }

    public int uniquePathsBottomUp(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return uniquePathsBottomUp(m, n, dp);
    }

    /*Time Complexity: O(M*N)
      Reason: There are two nested loops
      Space Complexity: O(M*N)
      Reason: We are using an external array of size 'M*N'. */
    private int uniquePathsBottomUp(int m, int n, int[][] dp) {
        for (int i = 0; i < m; i++) {
            //i loop for rows
            for (int j = 0; j < n; j++) {
                //j loop for columns
                if (i == 0 && j == 0) {
                    //dp[0][0]=1 condition covered in this way
                    dp[i][j] = 1;
                } else {
                    int up = 0;
                    int left = 0;
                    if (i > 0) {
                        //To avoid negative indices of rows
                        up = dp[i - 1][j];
                    }
                    if (j > 0) {
                        //To avoid negative indices of columns
                        left = dp[i][j - 1];
                    }
                    //storing result in dp[i][j]
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*Time Complexity: O(M*N)
      Reason: There are two nested loops
      Space Complexity: O(N)
      Reason: We are using an external array of size ‘N’ to store only one row.*/
    public int uniquePathsSpaceOptimised(int m, int n) {
        //Creating an array to store previous rows values
        int[] previous = new int[n];
        for (int i = 0; i < m; i++) {
            int[] currentRow = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    currentRow[j] = 1;
                } else {
                    int up = 0;
                    int left = 0;
                    if (i > 0) {
                        up = previous[j];
                    }
                    if (j > 0) {
                        left = currentRow[j - 1];
                    }
                    currentRow[j] = up + left;
                }
            }
            //storing currentRows into previous row as we will move to next row
            previous = currentRow;
        }
        return previous[n-1];
    }

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        System.out.println(up.uniquePathsTopDown(3, 2));
        System.out.println(up.uniquePathsBottomUp(3, 2));
        System.out.println(up.uniquePathsSpaceOptimised(3, 2));
    }
}

package DynamicProgramming;

import java.util.Arrays;

/*
Unique Paths II

LeetCode
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.
An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The testcases are generated so that the answer will be less than or equal to 2 * 109.
Example 1:
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1

Grid Path 2

GeeksForGeeks
You are given a grid of n * m having 0 and 1 respectively, 0 denotes space, and 1 denotes obstacle.
Geek is located at top-left corner (i.e grid[0][0]) and wants to reach the bottom right corner of the grid.
A geek can move either down or right at any point of time.
return the total number of ways in which Geek can reach bottom right corner.
answer may be large take the modulo by 1e9+7.
Example:
Input:
n = 3, m = 3
grid= [[0,0,0],[0,1,0],[0,0,0]]
Output:
2
Explanation:
There is one obstacle in the middle of the 3x3 grid above. There are two ways to reach
the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:
Input:
n = 2, m = 2
grid = [[0,1],[0,0]]
Output:
1
*/
public class UniquePaths2WithObstacles {

    public int uniquePathsWithObstaclesTopDown(int m, int n, int[][] obstacleGrid) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return uniquePathsWithObstaclesTopDown(m - 1, n - 1, obstacleGrid, dp);
    }

    /*Time Complexity: O(M*N)
      Reason: At max, there will be M*N calls of recursion.
      Space Complexity: O((N-1)+(M-1)) + O(M*N)
      Reason: We are using a recursion stack space:O((N-1)+(M-1)),
      here (N-1)+(M-1) is the path length and an external DP Array of size ‘M*N’.*/
    private int uniquePathsWithObstaclesTopDown(int i, int j, int[][] obstacleGrid, int[][] dp) {
        //1 value in the grid indicates the obstacle which needs to be avoided for the possible path
        if(i>0 && j>0 && obstacleGrid[i][j] == 1) {
            return 0;
        }
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
        int up = uniquePathsWithObstaclesTopDown(i - 1, j, obstacleGrid, dp);
        int left = uniquePathsWithObstaclesTopDown(i, j - 1, obstacleGrid, dp);
        //storing and returning result
        return dp[i][j] = up + left;
    }

    public int uniquePathsWithObstaclesBottomUp(int m, int n, int[][] obstacleGrid) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return uniquePathsWithObstaclesBottomUp(m, n, obstacleGrid, dp);
    }

    /*Time Complexity: O(M*N)
      Reason: There are two nested loops
      Space Complexity: O(M*N)
      Reason: We are using an external array of size 'M*N'. */
    private int uniquePathsWithObstaclesBottomUp(int m, int n, int[][] obstacleGrid, int[][] dp) {
        for (int i = 0; i < m; i++) {
            //i loop for rows
            for (int j = 0; j < n; j++) {
                //obstacleGrid[i][j] == 1 represents an obstacle, so that should not be counted in path
                if(i>0 && j>0 && obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
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
    public int uniquePathsWithObstaclesSpaceOptimised(int m, int n, int[][] obstacleGrid) {
        //Creating an array to store previous rows values
        int[] previous = new int[n];
        for (int i = 0; i < m; i++) {
            int[] currentRow = new int[n];
            for (int j = 0; j < n; j++) {
                //obstacleGrid[i][j] == 1 represents an obstacle, so that should not be counted in path
                if(i>0 && j>0 && obstacleGrid[i][j] == 1) {
                    currentRow[j] = 0;
                    continue;
                }
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

    //GeeksForGeeks and LeetCode working Space optimised dp solution
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                //1 value indicates obstacles
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }

    public static void main(String[] args) {
        UniquePaths2WithObstacles up = new UniquePaths2WithObstacles();
        int[][] obstacleGrid = { {0,0,0},
                {0,1,0},
                {0,0,0}};
        System.out.println(up.uniquePathsWithObstaclesTopDown(3, 3, obstacleGrid));
        System.out.println(up.uniquePathsWithObstaclesBottomUp(3, 3, obstacleGrid));
        System.out.println(up.uniquePathsWithObstaclesSpaceOptimised(3, 3, obstacleGrid));

    }
}

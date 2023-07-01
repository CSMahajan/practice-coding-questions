package DynamicProgramming;

import java.util.Arrays;

/*
minimum path sum in matrix

Given a NxN matrix of positive integers. There are only three possible moves from a cell Matrix[r][c].
Matrix [r+1] [c]
Matrix [r+1] [c-1]
Matrix [r+1] [c+1]
Starting from any column in row 0 return the largest sum of any of the paths up to row N-1.
NOTE: We can start from any column in zeroth row and can end at any column in (N-1)th row.
Example 1:
Input: N = 2
Matrix = {{348, 391},
          {618, 193}}
Output: 1009
Explanation: The best path is 391 -> 618.
It gives the sum = 1009.
Example 2:
Input: N = 2
Matrix = {{2, 2},
          {2, 2}}
Output: 4
Explanation: No matter which path is
chosen, the output is 4.
*/
public class MinimumPathSumVariableStartingPointVariableEndingPoint {

    public static int minimumPathTopDown(int N, int[][] Matrix) {
        // code here
        int M = Matrix[0].length;
        int[][] dp = new int[N][M];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int minimumPathSum = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            int currentPathSum = minimumPathTopDown(N - 1, j, M, Matrix, dp);
            minimumPathSum = Math.min(minimumPathSum, currentPathSum);
        }
        return minimumPathSum;
    }

    //Time Complexity: O(N*M)
    //Reason: At max, there will be M*N calls of recursion to solve a new problem
    //Space Complexity: O(N) + O(N*M)
    //Reason: We are using a recursion stack space: O(N), where N is the path length and an external DP Array of size ‘N*M’.
    private static int minimumPathTopDown(int i, int j, int totalColumns, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= totalColumns) {
            return (int) Math.pow(10, 9);
        }
        if (i == 0) {
            return matrix[0][j];
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int up = matrix[i][j] + minimumPathTopDown(i - 1, j, totalColumns, matrix, dp);
        int leftDiagonal = matrix[i][j] + minimumPathTopDown(i - 1, j - 1, totalColumns, matrix, dp);
        int rightDiagonal = matrix[i][j] + minimumPathTopDown(i - 1, j + 1, totalColumns, matrix, dp);
        return dp[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(N*M)
    //Reason: We are using an external array of size ‘N*M’. The stack space will be eliminated.
    public static int minimumPathBottomUp(int N, int[][] Matrix) {
        // code here
        int M = Matrix[0].length;
        int[][] dp = new int[N][M];
        for (int j = 0; j < M; j++) {
            dp[0][j] = Matrix[0][j];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int up = Matrix[i][j] + dp[i - 1][j];
                int leftDiagonal = Matrix[i][j];
                if ((j - 1) >= 0) {
                    leftDiagonal += dp[i - 1][j - 1];
                } else {
                    leftDiagonal += (int) Math.pow(10, 9);
                }
                int rightDiagonal = Matrix[i][j];
                if ((j + 1) < M) {
                    rightDiagonal += dp[i - 1][j + 1];
                } else {
                    rightDiagonal += (int) Math.pow(10, 9);
                }
                dp[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
        }
        int minimum = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            minimum = Math.min(minimum, dp[N - 1][j]);
        }
        return minimum;
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(M)
    //Reason: We are using an external array of size ‘M’ to store only one row.
    //Working Space Optimised solution
    public static int minimumPathSpaceOptimised(int n, int Matrix[][]) {
        // code here
        int[] curr = new int[n];    // dp[i][x]
        int[] next = new int[n];    // dp[i+1][n]

        // Base case analysis
        for (int j = 0; j < n; j++)
            next[j] = Matrix[n - 1][j];

        // Recursion call in iterative form
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int down = next[j];
                int downLeft = j - 1 >= 0 ? next[j - 1] : (int) Math.pow(10, 9);
                int downRight = j + 1 < n ? next[j + 1] : (int) Math.pow(10, 9);
                curr[j] = Matrix[i][j] + Math.min(down, Math.min(downLeft, downRight));
            }
            next = curr.clone();
        }
        // First row contains min path for each column so take biggest one
        return Arrays.stream(next).min().getAsInt();
    }

    public static void main(String[] args) {
        int[][] Matrix = {{2,1,3},
                {6,5,4},{ 7,8,9}};
        System.out.println(minimumPathTopDown(3, Matrix));
       System.out.println(minimumPathBottomUp(3, Matrix));
        System.out.println(minimumPathSpaceOptimised(3, Matrix));
    }
}

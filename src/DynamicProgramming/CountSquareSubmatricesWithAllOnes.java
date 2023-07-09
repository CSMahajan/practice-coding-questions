package DynamicProgramming;

/*
Count Square Submatrices with All Ones

Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
Example 1:
Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:
Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.
*/
public class CountSquareSubmatricesWithAllOnes {

    //Time Complexity: O(N*M), where N = total no. of rows and M = total no. of columns
    //Reason: We are basically traversing a 2D matrix with N rows and M columns.
    //Space Complexity: O(N*M), where N = total no. of rows and M = total no. of columns
    //Reason: We are using a 2D dp array with N rows and M columns.
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0];
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                totalSum += dp[i][j];
            }
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        CountSquareSubmatricesWithAllOnes csswao = new CountSquareSubmatricesWithAllOnes();
        System.out.println(csswao.countSquares(matrix));
    }
}

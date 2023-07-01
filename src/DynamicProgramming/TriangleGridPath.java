package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Triangle

Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below.
More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:
Input: triangle = [[-10]]
Output: -10
*/
public class TriangleGridPath {

    public int minimumTotalTriangleGridTopDown(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return minimumTotalTriangleGridTopDown(0, 0, triangle, n, dp);
    }

    //Time Complexity: O(N*N)
    //Reason: At max, there will be (half of, due to triangle) N*N calls of recursion.
    //Space Complexity: O(N) + O(N*N)
    //Reason: We are using a recursion stack space: O((N), where N is the path length and an external DP Array of size ‘N*N’.
    private int minimumTotalTriangleGridTopDown(int i, int j, List<List<Integer>> triangle, int n, int[][] dp) {
        //We need to compulsorily go from top to bottom in recursive
        //because if we reach from any index in (n-1)th row to first row, we don't know to stop

        //Since we have a fixed start point but variable end point, we have to traverse from top to bottom,
        //so the final row is reached with minimum possible sum
        //for recursion call reaching at (n-1)th row, sum is added with triangle[i][j],
        //because any column in last row can be considered as a destination
        if (i == n - 1) {
            return triangle.get(n - 1).get(j);
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        //moving towards next/below row of current row in triangle in same column
        int down = triangle.get(i).get(j) + minimumTotalTriangleGridTopDown(i + 1, j, triangle, n, dp);
        //moving towards next/below row of current row in triangle in next column, diagonally reaching
        int diagonal = triangle.get(i).get(j) + minimumTotalTriangleGridTopDown(i + 1, j + 1, triangle, n, dp);
        return dp[i][j] = Math.min(down, diagonal);
    }

    public int minimumTotalTriangleGridBottomUp(List<List<Integer>> triangle) {
        int n = triangle.size();
        return minimumTotalTriangleGridBottomUp(triangle, n);
    }

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops
    //Space Complexity: O(N*N)
    //Reason: We are using an external array of size ‘N*N’. The stack space will be eliminated.
    private int minimumTotalTriangleGridBottomUp(List<List<Integer>> triangle, int n) {
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            //because any column in last row can be considered as a destination, so for that particular index its dp is equivalent to triangle[n-1][j]
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        //Bottom Up approach is always exact opposite of top down, and we always traverse from known value of dp array(here dp[n-1][j]) towards dp[0][0]
        //since for n-1, we know the value so we start from n-2 till 0 for each row
        for (int i = n - 2; i >= 0; i--) {
            //for each column, they are in numbers wise equivalent to the row number, so j starts from i till 0
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int diagonal = triangle.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diagonal);
            }
        }
        //When loop ends our values are present in dp[0][0]
        return dp[0][0];
    }

    //Below solution not working correctly need to check why it is wrong
    public int minimumTotalTriangleGridSpaceOptimised(List<List<Integer>> triangle) {
        int n = triangle.size();
        //frontRow array store the sum value of the below row of the current row as we will traverse from n-1 to 0
        int[] frontRow = new int[n];
        int[] currentRow = new int[n];
        for (int j = 0; j < n; j++) {
            //because any column in last row can be considered as a destination, so for that particular index its dp is equivalent to triangle[n-1][j]
            frontRow[j] = triangle.get(n - 1).get(j);
        }
        //Bottom Up approach is always exact opposite of top down, and we always traverse from known value of dp array(here dp[n-1][j]) towards dp[0][0]
        //since for n-1, we know the value so we start from n-2 till 0 for each row
        for (int i = n - 2; i >= 0; i--) {
            //for each column, they are in numbers wise equivalent to the row number, so j starts from i till 0
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + frontRow[j];
                int diagonal = triangle.get(i).get(j) + frontRow[j + 1];
                currentRow[j] = Math.min(down, diagonal);
            }
            frontRow = currentRow;
        }
        //When loop ends our values are present in dp[0][0]
        return frontRow[0];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        // corner case
        if (triangle == null || triangle.size() == 0) return 0;

        // M[i] represents the min total from bottom to current position
        int m = triangle.size();
        int n = triangle.get(m - 1).size(); // last row size
        int[] M = new int[n];
        M[0] = triangle.get(0).get(0);

        // induction rule
        // M[j] = min(M[j - 1], M[j]) + curVal
        for (int i = 1; i < m; i++) {
            List<Integer> cur = triangle.get(i);
            for (int j = cur.size() - 1; j >= 0; j--) {
                if (j == 0) {
                    M[0] = M[0] + cur.get(j);
                } else if (j == cur.size() - 1) {
                    M[j] = M[j - 1] + cur.get(j);
                } else {
                    M[j] = Math.min(M[j - 1], M[j]) + cur.get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, M[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(2);
        triangle.add(row1);
        List<Integer> row2 = new ArrayList<>();
        row2.add(3);
        row2.add(4);
        triangle.add(row2);
        List<Integer> row3 = new ArrayList<>();
        row3.add(6);
        row3.add(5);
        row3.add(7);
        triangle.add(row3);
        List<Integer> row4 = new ArrayList<>();
        row4.add(4);
        row4.add(1);
        row4.add(8);
        row4.add(3);
        triangle.add(row4);
        TriangleGridPath tgp = new TriangleGridPath();
        System.out.println(tgp.minimumTotalTriangleGridTopDown(triangle));
        System.out.println(tgp.minimumTotalTriangleGridBottomUp(triangle));
        System.out.println(tgp.minimumTotalTriangleGridSpaceOptimised(triangle));
        System.out.println(tgp.minimumTotal(triangle));
    }
}
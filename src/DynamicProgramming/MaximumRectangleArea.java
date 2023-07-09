package DynamicProgramming;

import java.util.Stack;

/*
Maximal Rectangle

Given a rows x cols binary matrix filled with 0's and 1's,
find the largest rectangle containing only 1's and return its area.
Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:
Input: matrix = [["0"]]
Output: 0
Example 3:
Input: matrix = [["1"]]
Output: 1
*/
public class MaximumRectangleArea {

    //Time Complexity: O(N * (M+M)), where N = total no. of rows and M = total no. of columns.
    //Reason: O(N) for running a loop to check all rows.
    //Now, inside that loop, O(M) is for visiting all the columns, and another O(M) is for the function we are using.
    //The function takes linear time complexity. Here, the size of the height array is M, so it will take O(M).
    //Space Complexity: O(M), where M = total no. of columns.
    //Reason: We are using a height array and a stack of size M.
    public int maximalRectangle(char[][] matrix) {
        int maximumArea = Integer.MIN_VALUE;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] height = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            int currentRowMaximumArea = largestRectangleArea(height);
            maximumArea = Math.max(maximumArea, currentRowMaximumArea);
        }
        return maximumArea;
    }

    private static int largestRectangleArea(int[] histo) {
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        int n = histo.length;
        for (int i = 0; i <= n; i++) {
            while (!st.empty() && (i == n || histo[st.peek()] >= histo[i])) {
                int height = histo[st.peek()];
                st.pop();
                int width;
                if (st.empty())
                    width = i;
                else
                    width = i - st.peek() - 1;
                maxA = Math.max(maxA, width * height);
            }
            st.push(i);
        }
        return maxA;
    }

    //Time Complexity: O(N * (M+M)), where N = total no. of rows and M = total no. of columns.
    //Reason: O(N) for running a loop to check all rows.
    //Now, inside that loop, O(M) is for visiting all the columns, and another O(M) is for the function we are using.
    //The function takes linear time complexity. Here, the size of the height array is M, so it will take O(M).
    //Space Complexity: O(M), where M = total no. of columns.
    //Reason: We are using a height array and a stack of size M.
    public static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {
        // Write your code here.
        int maximumArea = Integer.MIN_VALUE;
        int[] height = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            int currentRowMaximumArea = largestRectangleArea(height);
            maximumArea = Math.max(maximumArea, currentRowMaximumArea);
        }
        return maximumArea;
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 4;
        int[][] mat = {
                {1, 0, 1, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 1}
        };
        System.out.println(maximalAreaOfSubMatrixOfAll1(mat, n, m));

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        MaximumRectangleArea mra = new MaximumRectangleArea();
        System.out.println(mra.maximalRectangle(matrix));
    }
}

/*
Spirally traversing a matrix

Given a matrix of size r*c. Traverse the matrix in spiral form.
Example 1:
Input:
r = 4, c = 4
matrix[][] = {{1, 2, 3, 4},
              {5, 6, 7, 8},
              {9, 10, 11, 12},
              {13, 14, 15,16}}
Output:
1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
Explanation:
Example 2:
Input:
r = 3, c = 4
matrix[][] = {{1, 2, 3, 4},
              {5, 6, 7, 8},
              {9, 10, 11, 12}}
Output:
1 2 3 4 8 12 11 10 9 5 6 7
Explanation:
Applying same technique as shown above, output for the 2nd testcase will be 1 2 3 4 8 12 11 10 9 5 6 7.
*/

import java.util.ArrayList;

public class SpiralTraverseMatrix {

    public static ArrayList<Integer> spirallyTraverse(int[][] matrix, int r, int c) {
        // code here
        int top = 0;
        int bottom = r - 1;
        int left = 0;
        int right = c - 1;
        ArrayList<Integer> spiralList = new ArrayList<>();
        while (left <= right && top <= bottom) {
            //traversing top row from left to right
            for (int i = left; i <= right; i++) {
                spiralList.add(matrix[top][i]);
            }
            top++;
            //traversing right column from top to bottom
            for (int i = top; i <= bottom; i++) {
                spiralList.add(matrix[i][right]);
            }
            right--;
            if (left > right || top > bottom) {
                break;
            }
            //traversing bottom row from right to left
            for (int i = right; i >= left; i--) {
                spiralList.add(matrix[bottom][i]);
            }
            bottom--;
            //traversing left row from bottom to top
            for (int i = bottom; i >= top; i--) {
                spiralList.add(matrix[i][left]);
            }
            left++;
        }
        return spiralList;
    }

    public static void main(String[] args) {
        int r = 4, c = 4;
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println(spirallyTraverse(matrix, r, c));
    }
}
package BinarySearch;

import java.util.Arrays;

/*
Find a Peak Element II

A peak element in a 2D grid is an element that is strictly greater than
all of its adjacent neighbors to the left, right, top, and bottom.
Given a 0-indexed m x n matrix mat where no two adjacent cells are equal,
find any peak element mat[i][j] and return the length 2 array [i,j].
You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
Example 1:
Input: mat = [[1,4],[3,2]]
Output: [0,1]
Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
Example 2:
Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
Output: [1,1]
Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
*/
public class FindPeakElement2 {

    //TC: O(n*log(m))
    //SC: O(1)
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int low = 0;
        int high = m - 1;
        //performing binary search on columns
        while (low <= high) {
            int mid = (low + high) / 2;
            //finding the index of maximum value element for mid as the column
            int maxRowIndex = findMaximumElementsRowIndex(mat, n, mid);
            int left = (mid - 1) >= 0 ? mat[maxRowIndex][mid - 1] : -1;
            int right = (mid + 1) < m ? mat[maxRowIndex][mid + 1] : -1;
            if (mat[maxRowIndex][mid] > left && mat[maxRowIndex][mid] > right) {
                return new int[]{maxRowIndex, mid};
            } else if (mat[maxRowIndex][mid] < left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    private int findMaximumElementsRowIndex(int[][] mat, int n, int column) {
        int maxRowIndex = -1;
        int maxValue = -1;
        for (int i = 0; i < n; i++) {
            if (maxValue < mat[i][column]) {
                maxValue = mat[i][column];
                maxRowIndex = i;
            }
        }
        return maxRowIndex;
    }

    public static void main(String[] args) {
        int[][] mat = {{10, 20, 15}, {21, 30, 14}, {7, 16, 32}};
        FindPeakElement2 fpe = new FindPeakElement2();
        System.out.println(Arrays.toString(fpe.findPeakGrid(mat)));
    }
}
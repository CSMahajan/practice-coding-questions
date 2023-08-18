package BinarySearch;

/*
Search in a 2-D sorted Matrix

You are given an m x n integer matrix with the following two properties:
Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.
You must write a solution in O(log(m * n)) time complexity.
Example 1:
Input:
n = 3, m = 3, x = 62
matrix[][] = {{ 3, 30, 38},
              {36, 43, 60},
              {40, 51, 69}}
Output: 0
Explanation:
62 is not present in the matrix, so output is 0.
Example 2:
Input:
n = 1, m = 6, x = 55
matrix[][] = {{18, 21, 27, 38, 55, 67}}
Output: 1
Explanation: 55 is present in the matrix.
*/
public class SearchInSorted2DMatrix {

    //Time complexity: O(log(m*n))
    //Space complexity: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) {
            return false;
        }
        int m = matrix[0].length;
        int low = 0;
        int high = n * m - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int rowIndex = mid / m;
            int columnIndex = mid % m;
            if (matrix[rowIndex][columnIndex] == target) {
                return true;
            } else if (matrix[rowIndex][columnIndex] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchInSorted2DMatrix ss2m = new SearchInSorted2DMatrix();
        int[][] matrix = {
                {3, 30, 38},
                {36, 43, 60},
                {40, 51, 69}
        };
        int target = 62;
        System.out.println(ss2m.searchMatrix(matrix, target));
    }
}
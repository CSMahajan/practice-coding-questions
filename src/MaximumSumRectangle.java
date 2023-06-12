/*
Maximum Sum Rectangle

Given a 2D array, find the maximum sum submatrix in it.
For example, in the following 2D array, the maximum sum submatrix is highlighted with blue rectangle
and sum of all elements in this submatrix is 29.
Input:
        int arr[][] = new int[][] { { 1, 2, -1, -4, -20 },
        { -8, -3, 4, 2, 1 },
        { 3, 8, 10, 1, 3 },
        { -4, -1, 1, 7, -6 } };
Output:29
Explanation: { -3, 4, 2},
             { 8, 10, 1},
             { -1, 1, 7}
                The above sub matrix or 2-D subarray sum (i.e.sum of all elements) is 29
*/

import java.util.Arrays;

public class MaximumSumRectangle {

    // Function to find maximum sum rectangular
    // submatrix
    //This way gives us the index of the maximum 2-D matrix along with maximum sum
    //which gives maximum sum but difficult at start to understand,
    //copied from GeeksForGeeks as it is
    private static int maxSumRectangle(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int preSum[][] = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j] =
                        preSum[i][j] + mat[i][j];
            }
        }

        int maxSum = -1;
        int minSum = Integer.MIN_VALUE;
        int negRow = 0, negCol = 0;
        int rStart = 0, rEnd = 0, cStart = 0, cEnd = 0;
        for (int rowStart = 0; rowStart < m; rowStart++) {
            for (int row = rowStart; row < m; row++) {
                int sum = 0;
                int curColStart = 0;
                for (int col = 0; col < n; col++) {
                    sum += preSum[row + 1][col]
                            - preSum[rowStart][col];
                    if (sum < 0) {
                        if (minSum < sum) {
                            minSum = sum;
                            negRow = row;
                            negCol = col;
                        }
                        sum = 0;
                        curColStart = col + 1;
                    } else if (maxSum < sum) {
                        maxSum = sum;
                        rStart = rowStart;
                        rEnd = row;
                        cStart = curColStart;
                        cEnd = col;
                    }
                }
            }
        }

        // Printing final values
        if (maxSum == -1) {
            System.out.println("from row - " + negRow
                    + " to row - " + negRow);
            System.out.println("from col - " + negCol
                    + " to col - " + negCol);
        } else {
            System.out.println("from row - " + rStart
                    + " to row - " + rEnd);
            System.out.println("from col - " + cStart
                    + " to col - " + cEnd);
        }
        return maxSum == -1 ? minSum : maxSum;
    }

    //Simple approach using Kadane's algorithm
    /*
    Here we are basically finding column wise maximum sum C(0) to C(N-1)
    iteratively for all column subarrays based on row and
    applying the Kadane's' algorithm to find the maximum sum using the previous maximum sum
    This approach is very simple to understand and easy to implement,
    watch Anuj Bhaiyya maximum sum rectangle youtube video for further clarity
    */
    private static int maximumSumRectangle(int R, int C, int M[][]) {
        // code here
        int maxSum = Integer.MIN_VALUE;
        int sum[] = new int[R];
        for (int cStart = 0; cStart < C; cStart++) {
            Arrays.fill(sum, 0);
            for (int cEnd = cStart; cEnd < C; cEnd++) {
                for (int row = 0; row < R; row++) {
                    sum[row] += M[row][cEnd];
                }
                int currentMaxSum = kadanesAlgorithm(sum);
                maxSum = Math.max(maxSum, currentMaxSum);
            }
        }
        return maxSum;
    }

    private static int kadanesAlgorithm(int[] arr) {
        int currentSum = 0;
        int totalMaxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            totalMaxSum = Math.max(totalMaxSum, currentSum);
            currentSum = Math.max(currentSum, 0);
        }
        return totalMaxSum;
    }

    // Driver Code
    public static void main(String[] args) {
        int arr[][] = new int[][]{{1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}};

        // Function call
        System.out.println(maxSumRectangle(arr));
        System.out.println(maximumSumRectangle(4,5,arr));
    }
}
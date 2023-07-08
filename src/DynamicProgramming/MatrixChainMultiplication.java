package DynamicProgramming;

import java.util.Arrays;

/*
Matrix Chain Multiplication

Given a sequence of matrices, find the most efficient way to multiply these matrices together.
The efficient way is the one that involves the least number of multiplications.
The dimensions of the matrices are given in an array arr[] of size N (such that N = number of matrices + 1)
where the ith matrix has the dimensions (arr[i-1] x arr[i]).
Example 1:
Input: N = 5
arr = {40, 20, 30, 10, 30}
Output: 26000
Explaination:
There are 4 matrices of dimension 40x20, 20x30, 30x10, 10x30.
Say the matrices are named as A, B, C, D. Out of all possible combinations,
the most efficient way is (A*(B*C))*D.
The number of operations are -
20*30*10 + 40*20*10 + 40*10*30 = 26000.
Example 2:
Input: N = 4
arr = {10, 30, 5, 60}
Output: 4500
Explaination: The matrices have dimensions 10*30, 30*5, 5*60. Say the matrices are A, B and C.
Out of all possible combinations,the most efficient way is (A*B)*C.
The number of multiplications are - 10*30*5 + 10*5*60 = 4500.
*/
public class MatrixChainMultiplication {

    public static int matrixMultiplicationTopDown(int N, int[] arr) {
        // code here
        int[][] dp = new int[N][N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        //we are calling with i = 1 and j = N-1 because matrix requires two elements of an array
        return matrixMultiplicationTopDown(1, N - 1, arr, dp);
    }

    //Time Complexity: O(N*N*N)
    //Reason: There are N*N states and we explicitly run a loop inside the function which will run for N times,
    //therefore at max ‘N*N*N’ new problems will be solved.
    //Space Complexity: O(N*N) + O(N)
    //Reason: We are using an auxiliary recursion stack space(O(N))and a 2D array ( O(N*N)).
    private static int matrixMultiplicationTopDown(int i, int j, int[] arr, int[][] dp) {
        //i equals j signifies that itself is a single matrix, so the number of operations is 0
        if (i == j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int minimumNoOfSteps = (int) Math.pow(10, 9);
        //number of steps is the number of multiplication operations to be performed to calculate matrix multiplication
        //to calculate minimum such steps, we can have a partition between indexes from i to j-1
        //so the for loop is essential
        for (int partition = i; partition < j; partition++) {
            int noOfSteps = arr[i - 1] * arr[partition] * arr[j] +
                    matrixMultiplicationTopDown(i, partition, arr, dp) +
                    matrixMultiplicationTopDown(partition + 1, j, arr, dp);
            minimumNoOfSteps = Math.min(minimumNoOfSteps, noOfSteps);
        }
        return dp[i][j] = minimumNoOfSteps;
    }

    //Time Complexity: O(N*N*N)
    //Reason: There are N*N states, and we explicitly run a loop inside the function which will run for N times,
    //therefore at max 'N*N*N' new problems will be solved.
    //Space Complexity: O(N*N)
    //Reason: We are using a 2D array ( O(N*N)) space.
    public static int matrixMultiplicationBottomUp(int N, int[] arr) {
        // code here
        int[][] dp = new int[N][N];
        /*
        Below for loop covers base case of if i equals j then to return 0
        since by default array values are populated with 0, it is not necessary
        */
        for (int i = 1; i < N; i++) {
            dp[i][i] = 0;
        }
        for (int i = N - 1; i >= 1; i--) {
            for (int j = i + 1; j < N; j++) {
                int minimumNoOfSteps = (int) Math.pow(10, 9);
                //number of steps is the number of multiplication operations to be performed to calculate matrix multiplication
                //to calculate minimum such steps, we can have a partition between indexes from i to j-1
                //so the for loop is essential
                for (int partition = i; partition < j; partition++) {
                    int noOfSteps = arr[i - 1] * arr[partition] * arr[j] +
                            dp[i][partition] +
                            dp[partition + 1][j];
                    minimumNoOfSteps = Math.min(minimumNoOfSteps, noOfSteps);
                }
                dp[i][j] = minimumNoOfSteps;
            }
        }
        return dp[1][N - 1];
    }

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        int N = arr.length;
        System.out.println(matrixMultiplicationTopDown(N, arr));
        System.out.println(matrixMultiplicationBottomUp(N, arr));
    }
}
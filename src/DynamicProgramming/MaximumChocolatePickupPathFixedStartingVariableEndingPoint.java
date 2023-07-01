package DynamicProgramming;

import java.util.Arrays;

/*
Chocolates Pickup

You are given an r rows and c cols matrix grid representing a field of chocolates
where grid[i][j] represents the number of chocolates that you can collect from the (i, j) cell.
You have two robots that can collect chocolates for you:
Alice is located at the top-left corner (0, 0), and
Bob is located at the top-right corner (0, cols - 1).
Return the maximum number of chocolates collection using both robots by following the rules below:
From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all chocolates, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the chocolates.
Both robots cannot move outside of the grid at any moment.Both robots should reach the bottom row in grid.
Example:
Input:
r = 3, c = 4
grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output:24
Explanation:
Path of Alice and  Bob are described in color green and blue respectively.
Cherries taken by Alice, (3 + 2 + 5 + 2) = 12.
Cherries taken by Bob, (1 + 5 + 5 + 1) = 12. Total of cherries: 12 + 12 = 24.
*/
public class MaximumChocolatePickupPathFixedStartingVariableEndingPoint {

    public int maxChocolatePickupTopDown(int n, int m, int[][] grid) {
        // Code here
        int[][][] dp = new int[n][m][m];
        for (int[][] row : dp) {
            for (int[] column : row) {
                Arrays.fill(column, -1);
            }
        }
        return maxChocolatePickupTopDown(0, 0, m - 1, n, m, grid, dp);
    }

    //Time Complexity: O(N*M*M) * 9
    //Reason: At max, there will be N*M*M calls of recursion to solve a new problem and in every call,
    //two nested loops together run for 9 times.
    //Space Complexity: O(N) + O(N*M*M)
    //Reason: We are using a recursion stack space: O(N),
    //where N is the path length and an external DP Array of size ‘N*M*M’.
    private int maxChocolatePickupTopDown(int i, int j1, int j2, int n, int m, int[][] grid, int[][][] dp) {
        /*
        Since at every step/move, both Alice and Bob are going to move to next/below row, so their row index will be same.
        So we have kept i as the common row index of both of them.
        j1 and j2 represents column index of Alice and Bob respectively.
        */
        //Below edge case is for Alice or Bob going out of grid
        if (j1 < 0 || j2 < 0 || j1 >= m || j2 >= m) {
            return (int) Math.pow(-10, 9);
        }
        //Below edge case is for reaching last row
        if (i == n - 1) {
            if (j1 == j2) {
                //j1 == j2 indicates that both Alice and Bob reached at the same grid position (i,j1)=(i,j2)
                //but the chocolates in that grid can only be given to either of them (not both or cannot be shared)
                //so we will pick it for either of them
                dp[n - 1][j1][j2] = grid[n - 1][j1];
            } else {
                //j1 != j2 indicates that they are at different positions in the grid, so need to add both of them
                dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
            }
        }
        if (dp[i][j1][j2] != -1) {
            return dp[i][j1][j2];
        }
        int maximumChocolates = Integer.MIN_VALUE;
        for (int dj1 = -1; dj1 <= +1; dj1++) {
            //dj1 represents the movement delta for Alice as (-1,0,+1) because we can only go to left diagonal, down, right diagonal respectively
            //so for each available position of Alice, Bob can be present at different locations
            //so we will require 2 nested for loops
            for (int dj2 = -1; dj2 <= +1; dj2++) {
                //dj1 represents the movement (delta) for Bob as (j-1,j+0,j+1) because we can only go to left diagonal, down, right diagonal respectively
                int chocolatesPicked;
                if (j1 == j2) {
                    //j1 == j2 indicates that both Alice and Bob reached at the same grid position (i,j1)=(i,j2)
                    //but the chocolates in that grid can only be given to either of them (not both or cannot be shared)
                    //so we will pick it for either of them
                    chocolatesPicked = grid[i][j1] + maxChocolatePickupTopDown(i + 1, j1 + dj1, j2 + dj2, n, m, grid, dp);
                } else {
                    //j1 != j2 indicates that they are at different positions in the grid, so need to add both of them
                    chocolatesPicked = grid[i][j1] + grid[i][j2] + maxChocolatePickupTopDown(i + 1, j1 + dj1, j2 + dj2, n, m, grid, dp);
                }
                maximumChocolates = Math.max(maximumChocolates, chocolatesPicked);
            }
        }
        return dp[i][j1][j2] = maximumChocolates;
    }


    //Time Complexity: O(N*M*M)*9
    //Reason: The outer nested loops run for (N*M*M) times and the inner two nested loops run for 9 times.
    //Space Complexity: O(N*M*M)
    //Reason: We are using an external array of size ‘N*M*M’. The stack space will be eliminated.
    public int maxChocolatePickupBottomUp(int n, int m, int[][] grid) {
        // Code here
        int[][][] dp = new int[n][m][m];
        //j1 and j2 represents column indexes of Alice and Bob respectively
        //So at the last row, for every position of Alice, there will be 3 positions of Bob (-1,0,+1)
        //Below two nested for loops are used for base case
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) {
                    //j1 == j2 indicates that both Alice and Bob reached at the same grid position (i,j1)=(i,j2)
                    //but the chocolates in that grid can only be given to either of them (not both or cannot be shared)
                    //so we will pick it for either of them
                    dp[n - 1][j1][j2] = grid[n - 1][j1];
                } else {
                    //j1 != j2 indicates that they are at different positions in the grid, so need to add both of them
                    dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
                }
            }
        }
        //for i=n-1, we have calculated the dp array values above as base case
        for (int i = n - 2; i >= 0; i--) {
            //below j1 and j2 loops indicate for every position of Alice, there is a position of Bob
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maximumChocolates = Integer.MIN_VALUE;
                    //Below dj1 and dj2 loops indicate the movement down,left diagonal, right diagonal of Alice and Bob
                    for (int dj1 = -1; dj1 <= +1; dj1++) {
                        //dj1 represents the movement delta for Alice as (-1,0,+1) because we can only go to left diagonal, down, right diagonal respectively
                        //so for each available position of Alice, Bob can be present at different locations
                        //so we will require 2 nested for loops
                        for (int dj2 = -1; dj2 <= +1; dj2++) {
                            //dj1 represents the movement (delta) for Bob as (j-1,j+0,j+1) because we can only go to left diagonal, down, right diagonal respectively
                            int chocolatesPicked;
                            if (j1 == j2) {
                                //j1 == j2 indicates that both Alice and Bob reached at the same grid position (i,j1)=(i,j2)
                                //but the chocolates in that grid can only be given to either of them (not both or cannot be shared)
                                //so we will pick it for either of them
                                chocolatesPicked = grid[i][j1];
                            } else {
                                //j1 != j2 indicates that they are at different positions in the grid, so need to add both of them
                                chocolatesPicked = grid[i][j1] + grid[i][j2];
                            }
                            //Below 4 conditions cover out of grid cases for Alice and Bob
                            //dj1 and dj2 ranges in (-1,0,+1), so after summing them up with j1 and j2 gives correct column positions
                            if ((j1 + dj1) < 0 || (j1 + dj1) >= m || (j2 + dj2) < 0 || (j2 + dj2) >= m) {
                                chocolatesPicked += (int) Math.pow(-10, 9);
                            } else {
                                chocolatesPicked += dp[i + 1][j1 + dj1][j2 + dj2];
                            }
                            maximumChocolates = Math.max(maximumChocolates, chocolatesPicked);
                        }
                    }
                    dp[i][j1][j2] = maximumChocolates;
                }
            }
        }
        return dp[0][0][m - 1];
    }


    //Time Complexity: O(N*M*M)*9
    //Reason: The outer nested loops run for (N*M*M) times and the inner two nested loops run for 9 times.
    //Space Complexity: O(M*M)
    //Reason: We are using an external array of size ‘M*M’.
    public int maxChocolatePickupSpaceOptimised(int n, int m, int[][] grid) {
        // Code here
        int[][] previousRow = new int[m][m];
        int[][] currentRow = new int[m][m];
        //j1 and j2 represents column indexes of Alice and Bob respectively
        //So at the last row, for every position of Alice, there will be 3 positions of Bob (-1,0,+1)
        //Below two nested for loops are used for base case
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) {
                    //j1 == j2 indicates that both Alice and Bob reached at the same grid position (i,j1)=(i,j2)
                    //but the chocolates in that grid can only be given to either of them (not both or cannot be shared)
                    //so we will pick it for either of them
                    previousRow[j1][j2] = grid[n - 1][j1];
                } else {
                    //j1 != j2 indicates that they are at different positions in the grid, so need to add both of them
                    previousRow[j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
                }
            }
        }
        //for i=n-1, we have calculated the dp array values above as base case
        for (int i = n - 2; i >= 0; i--) {
            //below j1 and j2 loops indicate for every position of Alice, there is a position of Bob
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maximumChocolates = Integer.MIN_VALUE;
                    //Below dj1 and dj2 loops indicate the movement down,left diagonal, right diagonal of Alice and Bob
                    for (int dj1 = -1; dj1 <= +1; dj1++) {
                        //dj1 represents the movement delta for Alice as (-1,0,+1) because we can only go to left diagonal, down, right diagonal respectively
                        //so for each available position of Alice, Bob can be present at different locations
                        //so we will require 2 nested for loops
                        for (int dj2 = -1; dj2 <= +1; dj2++) {
                            //dj1 represents the movement (delta) for Bob as (j-1,j+0,j+1) because we can only go to left diagonal, down, right diagonal respectively
                            int chocolatesPicked;
                            if (j1 == j2) {
                                //j1 == j2 indicates that both Alice and Bob reached at the same grid position (i,j1)=(i,j2)
                                //but the chocolates in that grid can only be given to either of them (not both or cannot be shared)
                                //so we will pick it for either of them
                                chocolatesPicked = grid[i][j1];
                            } else {
                                //j1 != j2 indicates that they are at different positions in the grid, so need to add both of them
                                chocolatesPicked = grid[i][j1] + grid[i][j2];
                            }
                            //Below 4 conditions cover out of grid cases for Alice and Bob
                            //dj1 and dj2 ranges in (-1,0,+1), so after summing them up with j1 and j2 gives correct column positions
                            if ((j1 + dj1) < 0 || (j1 + dj1) >= m || (j2 + dj2) < 0 || (j2 + dj2) >= m) {
                                chocolatesPicked += (int) Math.pow(-10, 9);
                            } else {
                                chocolatesPicked += previousRow[j1 + dj1][j2 + dj2];
                            }
                            maximumChocolates = Math.max(maximumChocolates, chocolatesPicked);
                        }
                    }
                    currentRow[j1][j2] = maximumChocolates;
                }
            }
            for(int a= 0;a<m;a++) {
                previousRow[a] = currentRow[a].clone();
            }
        }
        return previousRow[0][m - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}};
        int n = grid.length;
        int m = grid[0].length;
        MaximumChocolatePickupPathFixedStartingVariableEndingPoint mcpp = new MaximumChocolatePickupPathFixedStartingVariableEndingPoint();
        System.out.println(mcpp.maxChocolatePickupTopDown(n, m, grid));
        System.out.println(mcpp.maxChocolatePickupBottomUp(n, m, grid));
        System.out.println(mcpp.maxChocolatePickupSpaceOptimised(n, m, grid));
    }
}



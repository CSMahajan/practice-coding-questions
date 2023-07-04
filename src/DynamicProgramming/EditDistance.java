package DynamicProgramming;

import java.util.Arrays;

/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
Insert a character
Delete a character
Replace a character
Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/
public class EditDistance {

    public int minDistanceTopDown(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return minDistanceTopDown(n, m, s1, s2, dp);
    }

    //Time Complexity: O(N*M)
    //Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.
    //Space Complexity: O(N*M) + O(N+M)
    //Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
    private int minDistanceTopDown(int i, int j, String s1, String s2, int[][] dp) {
        //Base case


        //We are shifting the indexes due to later in bottom up tabulation dp array index don't allow negative indexes
        //which also requires dp of (n+1)*(m+1) due to considering -1 as at 0th index
        //here in case of bottom up dp array, we can not check for i<0 which would have returned j+1

        //Note:in case of top down, we can still use 0-based indexing but would require 1-based in bottom up approach

        //i represents index pointing to first string
        //first string gets exhausted that means we will require j insert operations to make s1 as s2
        //So from 1 to j is j number of characters need to be inserted
        //This is because j is at its index and we are following 1-based indexing
        if (i == 0) {
            return j;
        }
        //j represents index pointing to second string
        //So from 1 to i is i number of characters need to be deleted
        if (j == 0) {
            return i;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return dp[i][j] = minDistanceTopDown(i - 1, j - 1, s1, s2, dp);
        } else {
            int insertOperations = 1 + minDistanceTopDown(i, j - 1, s1, s2, dp);
            int deleteOperations = 1 + minDistanceTopDown(i - 1, j, s1, s2, dp);
            int replaceOperations = 1 + minDistanceTopDown(i - 1, j - 1, s1, s2, dp);
            return dp[i][j] = Math.min(insertOperations, Math.min(deleteOperations, replaceOperations));
        }
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(N*M)
    //Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
    public int minDistanceBottomUp(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertOperations = 1 + dp[i][j - 1];
                    int deleteOperations = 1 + dp[i - 1][j];
                    int replaceOperations = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insertOperations, Math.min(deleteOperations, replaceOperations));
                }
            }
        }
        return dp[n][m];
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M)
    //Reason: We are using an external array of size 'M+1' to store two rows (current and previous).
    public int minDistanceSpaceOptimised(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[] previousRow = new int[m + 1];
        int[] currentRow = new int[m + 1];
        for (int j = 0; j <= m; j++) {
            previousRow[j] = j;
        }
        for (int i = 1; i <= n; i++) {
            /*if we observe the bottom up solutions, we find that for every row, 0th column index is row number itself
            so whenever we move forward, we require the currentRow's 0th index value as the row number
            Consider below base case for the same
            for (int i = 0; i <= n; i++) {dp[i][0] = i;}*/

            //As for filling up current rows current index values, we require previous row's previous index,
            //current row's previous index,current row's current index
            //So we can not optimise it further to a single dp array
            currentRow[0] = i;
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    currentRow[j] = previousRow[j - 1];
                } else {
                    int insertOperations = 1 + currentRow[j - 1];
                    int deleteOperations = 1 + previousRow[j];
                    int replaceOperations = 1 + previousRow[j - 1];
                    currentRow[j] = Math.min(insertOperations, Math.min(deleteOperations, replaceOperations));
                }
            }
            previousRow = currentRow.clone();
        }
        return previousRow[m];
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        String s1 = "intention", s2 = "execution";
        System.out.println(ed.minDistanceTopDown(s1, s2));
        System.out.println(ed.minDistanceBottomUp(s1, s2));
        System.out.println(ed.minDistanceSpaceOptimised(s1, s2));
    }
}

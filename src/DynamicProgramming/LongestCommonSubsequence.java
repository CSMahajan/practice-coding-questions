package DynamicProgramming;

import java.util.Arrays;

/*
Longest Common Subsequence

Given two strings text1 and text2, return the length of their longest common subsequence.
If there is no common subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none)
deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.
Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
*/
public class LongestCommonSubsequence {

    public int longestCommonSubsequenceTopDown(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] rows : dp) {
            Arrays.fill(rows, -1);
        }
        return longestCommonSubsequenceTopDown(n, m, text1, text2, dp);
    }

    //Time Complexity: O(N*M)
    //Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.
    //Space Complexity: O(N*M) + O(N+M)
    //Reason: We are using an auxiliary recursion stack space(O(N+M)) (see the recursive tree,
    //in the worst case, we will go till N+M calls at a time) and a 2D array ( O(N*M)).
    private int longestCommonSubsequenceTopDown(int i, int j, String s1, String s2, int[][] dp) {
        //Base case
        //Instead of dp array storing values from 0 to n-1 by shifting indexes,
        //here we are storing them in 1 to n because at 0th index we will -1th index value which requires (n+1)*(m+1) dp array
        //Instead of i<0 || j<0, here we are checking for i or j as 0 because we are shifting the indexes for values
        //As for negative index we cannot store values
        if (i == 0 || j == 0) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        //Due to shifting of indexes need to check for i-1 and j-1 instead of i and j
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return dp[i][j] = 1 + longestCommonSubsequenceTopDown(i - 1, j - 1, s1, s2, dp);
        } else {
            //otherwise moving either first or second strings indexes and taking the max
            return dp[i][j] = Math.max(longestCommonSubsequenceTopDown(i - 1, j, s1, s2, dp), longestCommonSubsequenceTopDown(i, j - 1, s1, s2, dp));
        }
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(N*M)
    //Reason: We are using an external array of size '(N*M)'. Stack Space is eliminated.
    public int longestCommonSubsequenceBottomUp(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        //Base case
        //Instead of dp array storing values from 0 to n-1 by shifting indexes,
        //here we are storing them in 1 to n because at 0th index we will -1th index value which requires (n+1)*(m+1) dp array
        //Instead of i<0 || j<0, here we are checking for i or j as 0 because we are shifting the indexes for values
        //As for negative index we cannot store values
        for(int j=0;j<=m;j++) {
            dp[0][j]=0;
        }
        for(int i=0;i<=n;i++) {
            dp[i][0]=0;
        }
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                //Due to shifting of indexes need to check for i-1 and j-1 instead of i and j
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //otherwise moving either first or second strings indexes and taking the max
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M) + O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only previous and current rows.
    public int longestCommonSubsequenceSpaceOptimisedTwoArrays(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] previousRow = new int[m + 1];
        int[] currentRow = new int[m + 1];

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    currentRow[j] = 1 + previousRow[j - 1];
                } else {
                    currentRow[j] = Math.max(previousRow[j],currentRow[j-1]);
                }
            }
            previousRow = currentRow.clone();
        }
        return previousRow[m];
    }

    //NOTE:Need to check why below 1D dp of using 1 array is failing on leetcode and geeskforgeeks
    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only previous(itself acting as current) rows.
    public int longestCommonSubsequenceSpaceOptimisedOneArray(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] previousRow = new int[m + 1];

        for(int j=0;j<=m;j++) {
            previousRow[j]=0;
        }
        for(int i=0;i<=n;i++) {
            previousRow[0]=0;
        }
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    previousRow[j] = 1 + previousRow[j - 1];
                } else {
                    previousRow[j] = Math.max(previousRow[j],previousRow[j-1]);
                }
            }
        }
        return previousRow[m];
    }
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String text1 = "abcde", text2 = "ace";
        System.out.println(lcs.longestCommonSubsequenceTopDown(text1, text2));
        System.out.println(lcs.longestCommonSubsequenceBottomUp(text1, text2));
        System.out.println(lcs.longestCommonSubsequenceSpaceOptimisedTwoArrays(text1, text2));
        System.out.println(lcs.longestCommonSubsequenceSpaceOptimisedOneArray(text1, text2));
    }
}

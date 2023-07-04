package DynamicProgramming;

import java.util.Arrays;

/*
Distinct Subsequences

Given two strings s and t, return the number of distinct subsequences of s which equals t.
The test cases are generated so that the answer fits on a 32-bit signed integer.
Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag(0,1,3) where bracket represents the indexes of b, a, g respectively which is taken from first string s
babgbag(0,1,6)
babgbag(0,5,6)
babgbag(2,5,6)
babgbag(4,5,6)
*/
public class DistinctSubsequences {

    public int numDistinctTopDown(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return numDistinctTopDown(n - 1, m - 1, s, t, dp);
    }

    /*private int numDistinctTopDown(int i, int j, String s, String t, int[][] dp) {
        int mod = (int) (Math.pow(10, 9) + 7);
        //first string index is exhausted that means
        //we could not find matching characters for all characters of second string in first string
        //so returning the number of ways as 0
        //this base case covers if i reaches negative that means we did not find j-th character of 2nd string in 1st string
        if (i < 0) {
            return 0;
        }
        //second string index is exhausted that means
        //we have found all matched characters from second string in first string
        if (j < 0) {
            return 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        //Note:Here we are finding occurrences of characters from second string into first string
        //if we find matching character, then we also have a possibility that at another index i in first string
        //we may find matching character of current index j of second string
        //so we need to consider both for counting distinct subsequences
        if (s.charAt(i) == t.charAt(j)) {
            int leavingSecondStringIndex = numDistinctTopDown(i - 1, j - 1, s, t, dp);
            int stayingSecondStringIndex = numDistinctTopDown(i - 1, j, s, t, dp);
            return dp[i][j] = (leavingSecondStringIndex + stayingSecondStringIndex) % mod;
        } else {
            //otherwise we will look for j-th character of second string till we find it matching to i-th character of first string
            //base case covers if i reaches negative that means we did not find j-th character of 2nd string in 1st string
            return dp[i][j] = numDistinctTopDown(i - 1, j, s, t, dp) % mod;
        }
    }*/

    //Time Complexity: O(N*M)
    //Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.
    //Space Complexity: O(N*M) + O(N+M)
    //Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
    private int numDistinctTopDown(int ind1, int ind2, String s1, String s2, int[][] dp) {
        int mod = (int) (Math.pow(10, 9) + 7);
        //second string index is exhausted that means
        //we have found all matched characters from second string in first string
        if (ind2 < 0)
            return 1;
        //first string index is exhausted that means
        //we could not find matching characters for all characters of second string in first string
        //so returning the number of ways as 0
        //this base case covers if i reaches negative that means we did not find j-th character of 2nd string in 1st string
        if (ind1 < 0)
            return 0;

        if (dp[ind1][ind2] != -1)
            return dp[ind1][ind2];
        //Note:Here we are finding occurrences of characters from second string into first string
        //if we find matching character, then we also have a possibility that at another index i in first string
        //we may find matching character of current index j of second string
        //so we need to consider both for counting distinct subsequences
        if (s1.charAt(ind1) == s2.charAt(ind2)) {
            int leaveOne = numDistinctTopDown(ind1 - 1, ind2 - 1, s1, s2, dp);
            int stay = numDistinctTopDown(ind1 - 1, ind2, s1, s2, dp);

            return dp[ind1][ind2] = (leaveOne + stay) % mod;
        } else {
            //otherwise we will look for j-th character of second string till we find it matching to i-th character of first string
            //base case covers if i reaches negative that means we did not find j-th character of 2nd string in 1st string
            return dp[ind1][ind2] = numDistinctTopDown(ind1 - 1, ind2, s1, s2, dp);
        }
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(N*M)
    //Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
    public int numDistinctBottomUp(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        //to handle overflow into negative indexes, we will shift the indexes, for that we will require (n+1)*(m+1) array
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        //j is starting from 1 instead of 0 to avoid overwriting of value 1 at dp[0][0]=1 to 0 which would be wrong
        for (int j = 1; j <= m; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int leaveSecondStringIndex = dp[i - 1][j - 1];
                    int staySecondStringIndex = dp[i - 1][j];

                    dp[i][j] = (leaveSecondStringIndex + staySecondStringIndex);
                } else {
                    //otherwise we will look for j-th character of second string till we find it matching to i-th character of first string
                    //base case covers if i reaches negative that means we did not find j-th character of 2nd string in 1st string
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M)+O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only two rows(previous and current).
    public int numDistinctSpaceOptimisedTwoArrays(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        //to handle overflow into negative indexes, we will shift the indexes, for that we will require (n+1)*(m+1) array
        int[] previousRow = new int[m + 1];
        int[] currentRow = new int[m + 1];
        previousRow[0] = 1;
        currentRow[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int leaveSecondStringIndex = previousRow[j - 1];
                    int staySecondStringIndex = previousRow[j];
                    currentRow[j] = (leaveSecondStringIndex + staySecondStringIndex);
                } else {
                    //otherwise we will look for j-th character of second string till we find it matching to i-th character of first string
                    //base case covers if i reaches negative that means we did not find j-th character of 2nd string in 1st string
                    currentRow[j] = previousRow[j];
                }
            }
            previousRow = currentRow.clone();
        }
        return previousRow[m];
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only one row.
    public int numDistinctSpaceOptimisedOneArray(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        //to handle overflow into negative indexes, we will shift the indexes, for that we will require (n+1)*(m+1) array
        int[] previousRow = new int[m + 1];
        previousRow[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int leaveSecondStringIndex = previousRow[j - 1];
                    int staySecondStringIndex = previousRow[j];
                    previousRow[j] = (leaveSecondStringIndex + staySecondStringIndex);
                }
            }
        }
        return previousRow[m];
    }

    public static void main(String[] args) {
        DistinctSubsequences ds = new DistinctSubsequences();
        String s = "rabbbit", t = "rabbit";
        System.out.println(ds.numDistinctTopDown(s, t));
        System.out.println(ds.numDistinctBottomUp(s, t));
        System.out.println(ds.numDistinctSpaceOptimisedTwoArrays(s, t));
        System.out.println(ds.numDistinctSpaceOptimisedOneArray(s, t));
    }
}

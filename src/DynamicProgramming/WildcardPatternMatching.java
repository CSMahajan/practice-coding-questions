package DynamicProgramming;

/*
Wildcard Pattern Matching

Given two strings 'str' and a wildcard pattern 'pattern' of length N and M respectively,
You have to print '1' if the wildcard pattern is matched with str else print '0' .
The wildcard pattern can include the characters ‘?’ and ‘*’
‘?’ – matches any single character
‘*’ – Matches any sequence of characters (including the empty sequence)
Note: The matching should cover the entire str (not partial str).
Example 1:
Input:
pattern = "ba*a?"
str = "baaabab"
Output: 1
Explanation: replace '*' with "aab" and
'?' with 'b'.
Example 2:
Input:
pattern = "a*ab"
str = "baaabab"
Output: 0
Explanation: Because of'a' at first position,
pattern and str can't be matched.
*/

import java.util.Arrays;

public class WildcardPatternMatching {

    public boolean isMatchTopDown(String str, String pattern) {
        //We have taken firstly pattern as n length and string as m length
        int n = pattern.length();
        int m = str.length();
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return isMatchTopDown(n - 1, m - 1, pattern, str, dp) == 1;
    }

    //Time Complexity: O(N*M)
    //Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.
    //Space Complexity: O(N*M) + O(N+M)
    //Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
    private int isMatchTopDown(int i, int j, String s1, String s2, int[][] dp) {
        //both s1 and s2 gets exhausted means match is found
        if (i < 0 && j < 0) {
            return 1;
        }
        //first pattern string s1 exhausted but second string remains means match not found
        if (i < 0 && j >= 0) {
            return 0;
        }
        //second normal string s2 exhausted but first string remains
        //means if first string has all stars then match found otherwise not found(even if one character is not wildcard character-*)
        if (j < 0 && i >= 0) {
            return isAllStarsTopDown(s1, i) ? 1 : 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        //As the character matches or first(pattern) string has the wildcard(?-question mark) at the i-th index
        if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
            return dp[i][j] = isMatchTopDown(i - 1, j - 1, s1, s2, dp);
        } else {
            if (s1.charAt(i) == '*') {
                //if first pattern string s1 has * at current index then we have two choices
                //either consider * as empty character and move first string index
                //or stay at * character index and move second string index
                int leaveStarIndex = isMatchTopDown(i - 1, j, s1, s2, dp);
                int stayStarIndex = isMatchTopDown(i, j - 1, s1, s2, dp);
                return dp[i][j] = leaveStarIndex == 1 || stayStarIndex == 1 ? 1 : 0;
            } else {
                return dp[i][j] = 0;
            }
        }
    }

    private boolean isAllStarsTopDown(String s1, int i) {
        for (int j = 0; j <= i; j++) {
            if (s1.charAt(j) != '*') {
                return false;
            }
        }
        return true;
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(N*M)
    //Reason: We are using an external array of size 'N*M'. Stack Space is eliminated.
    public boolean isMatchBottomUp(String s2, String s1) {
        //We have taken firstly s1 as n length and string as m length
        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        //at i=0 and j=0, both string exhausted means match found, so dp[0][0] is true
        dp[0][0] = true;
        //i=0 as first string exhausted means for first row, every column(second string) is false
        for (int j = 1; j <= m; j++) {
            dp[0][j] = false;
        }
        //j=0 as second string exhausted means if remaining all characters of first string are *
        //then matching otherwise not matching even if one character is different(because no character left in second string to compare)
        for (int i = 1; i <= n; i++) {
            dp[i][0] = isAllStarsBottomUp(s1, i);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1) || s1.charAt(i-1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (s1.charAt(i-1) == '*') {
                        //if first s1 string s1 has * at current index then we have two choices
                        //either consider * as empty character and move first string index
                        //or stay at * character index and move second string index
                        boolean leaveStarIndex = dp[i - 1][j];
                        boolean stayStarIndex = dp[i][j - 1];
                        dp[i][j] = leaveStarIndex || stayStarIndex;
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[n][m];
    }

    private boolean isAllStarsBottomUp(String s1, int i) {
        for (int j = 1; j <= i; j++) {
            if (s1.charAt(j-1) != '*') {
                return false;
            }
        }
        return true;
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M)
    //Reason: We are using an external array of size 'M+1' to store two rows(current and previous rows).
    public boolean isMatchSpaceOptimised(String s2, String s1) {
        //We have taken firstly s1 as n length and string as m length
        int n = s1.length();
        int m = s2.length();
        boolean[] previousRow = new boolean[m + 1];
        boolean[] currentRow = new boolean[m + 1];
        //at i=0 and j=0, both string exhausted means match found, so dp[0][0] is true
        previousRow[0] = true;
        for (int i = 1; i <= n; i++) {
            /*
            currentRow is current row's column
            and that currentRow's 0th row has to be assigned everytime
            At every row i, that current row's 0th index element is depending on i
            So basically currentRow's 0th column every time needs to be checked
            */
            currentRow[0] = isAllStarsSpaceOptimised(s1, i);
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1) || s1.charAt(i-1) == '?') {
                    currentRow[j] = previousRow[j - 1];
                } else {
                    if (s1.charAt(i-1) == '*') {
                        //if first s1 string s1 has * at current index then we have two choices
                        //either consider * as empty character and move first string index
                        //or stay at * character index and move second string index
                        boolean leaveStarIndex = previousRow[j];
                        boolean stayStarIndex = currentRow[j - 1];
                        currentRow[j] = leaveStarIndex || stayStarIndex;
                    } else {
                        currentRow[j] = false;
                    }
                }
            }
            previousRow = currentRow.clone();
        }
        return previousRow[m];
    }

    private boolean isAllStarsSpaceOptimised(String s1, int i) {
        for (int j = 1; j <= i; j++) {
            if (s1.charAt(j-1) != '*') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WildcardPatternMatching wpm = new WildcardPatternMatching();
        String pattern = "ba*a?";
        String str = "baaabab";
        //String pattern = "a*ab";
        //String str = "baaabab";
        System.out.println(wpm.isMatchTopDown(str, pattern));
        System.out.println(wpm.isMatchBottomUp(str, pattern));
        System.out.println(wpm.isMatchSpaceOptimised(str, pattern));
    }
}

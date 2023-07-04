package DynamicProgramming;

/*
Shortest Common Supersequence

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If there are multiple valid strings, return any of them.
A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.
Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:
Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"
*/
public class ShortestCommonSupersequencePrint {

    public String shortestCommonSupersequence(String str1, String str2) {
        //first we will get longest common subsequence filled dp array which can be used to traverse back till i and j becomes 0
        //This will be used for catching the uncommon and common characters(which will be taken once as we want the shortest common supersequence)
        int[][] dp = longestCommonSubsequenceBottomUp(str1, str2);
        StringBuilder scs = new StringBuilder();
        int n = str1.length();
        int m = str2.length();
        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                scs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                scs.append(str1.charAt(i - 1));
                i--;
            } else {
                scs.append(str2.charAt(j - 1));
                j--;
            }
        }
        while (i > 0) {
            scs.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            scs.append(str2.charAt(j - 1));
            j--;
        }
        return scs.reverse().toString();
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(N*M)
    //Reason: We are using an external array of size '(N*M)'. Stack Space is eliminated.
    public int[][] longestCommonSubsequenceBottomUp(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        //Base case
        //Instead of dp array storing values from 0 to n-1 by shifting indexes,
        //here we are storing them in 1 to n because at 0th index we will -1th index value which requires (n+1)*(m+1) dp array
        //Instead of i<0 || j<0, here we are checking for i or j as 0 because we are shifting the indexes for values
        //As for negative index we cannot store values
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //Due to shifting of indexes need to check for i-1 and j-1 instead of i and j
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //otherwise moving either first or second strings indexes and taking the max
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        ShortestCommonSupersequencePrint scsp = new ShortestCommonSupersequencePrint();
        String str1 = "abac", str2 = "cab";
        System.out.println(scsp.shortestCommonSupersequence(str1, str2));
    }
}
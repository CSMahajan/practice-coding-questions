package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
Print all LCS sequences

You are given two strings s and t.
Now your task is to print all longest common sub-sequences in lexicographical order.
Example 1:
Input: s = abaaa, t = baabaca
Output: aaaa abaa baaa
Example 2:
Input: s = aaa, t = a
Output: a
*/
public class LongestCommonSubsequencePrint {

    public List<String> all_longest_common_subsequences(String s, String t) {
        // Code here
        int n = s.length();
        int m = t.length();
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
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //otherwise moving either first or second strings indexes and taking the max
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        //Storing the length of longest common subsequences(LCS)
        //Since there can be many LCSs between two strings, we need set for keeping the unique subsequence
        //string builder for forming the subsequences
        int lcsLength = dp[n][m];
        HashSet<String> uniqueSubsequenceSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        populateAllLongestCommonSubsequences(s, t, 0, 0, uniqueSubsequenceSet, stringBuilder, lcsLength);
        List<String> allCommonSequences = new ArrayList<>(uniqueSubsequenceSet);
        //for lexicographical order
        Collections.sort(allCommonSequences);
        return allCommonSequences;
    }

    private void populateAllLongestCommonSubsequences(String s, String t, int m, int n, HashSet<String> set, StringBuilder stringBuilder, int lcsLength) {
        if (lcsLength == 0) {
            set.add(stringBuilder.toString());
            return;
        }
        //while increasing m and n if they cross first or second string
        if (m >= s.length() || n >= t.length()) {
            return;
        }
        for (int i = m; i < s.length(); i++) {
            for (int j = n; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    //character matching found, so adding it into string builder
                    //case of pick
                    stringBuilder.append(s.charAt(i));
                    populateAllLongestCommonSubsequences(s, t, i + 1, j + 1, set, stringBuilder, lcsLength - 1);
                    //case of not pick
                    //removing from string builder
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        LongestCommonSubsequencePrint lcsp = new LongestCommonSubsequencePrint();
        String text1 = "abaaa", text2 = "baabaca";
        System.out.println(lcsp.all_longest_common_subsequences(text1,text2));
    }
}

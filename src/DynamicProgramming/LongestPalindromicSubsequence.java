package DynamicProgramming;

/*
Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by
deleting some or no elements without changing the order of the remaining elements.
Example 1:
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:
Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
*/
public class LongestPalindromicSubsequence {

    public int longestPalindromeSubsequenceSpaceOptimised(String s) {
        //If we find the length of longest common subsequence between given string and reverse of given string,
        //that itself is the length of palindromic subsequence
        String reverse = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequenceSpaceOptimisedTwoArrays(s, reverse);
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

    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String s = "bbbab";
        System.out.println(lps.longestPalindromeSubsequenceSpaceOptimised(s));
    }
}

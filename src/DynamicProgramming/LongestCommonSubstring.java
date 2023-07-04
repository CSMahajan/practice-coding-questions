package DynamicProgramming;

/*
Longest Common Substring

Given two strings. The task is to find the length of the longest common substring.
Example 1:
Input: S1 = "ABCDGH", S2 = "ACDGHR", n = 6, m = 6
Output: 4
Explanation: The longest common substring is "CDGH" which has length 4.
Example 2:
Input: S1 = "ABC", S2 "ACB", n = 3, m = 3
Output: 1
Explanation: The longest common substrings are "A", "B", "C" all having length 1.
*/
public class LongestCommonSubstring {

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops
    //Space Complexity: O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only two rows(current and previous).
    int longestCommonSubstr(String S1, String S2, int n, int m) {
        int[] previousRow = new int[m + 1];
        int[] currentRow = new int[m + 1];
        int longestCommonSubstringLength = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    currentRow[j] = 1 + previousRow[j - 1];
                    //when character matched we take max of current long and previous long substring length
                    longestCommonSubstringLength = Math.max(longestCommonSubstringLength,currentRow[j]);
                } else {
                    currentRow[j] = 0;
                }
            }
            previousRow = currentRow.clone();
        }
        return longestCommonSubstringLength;
    }

    public static void main(String[] args) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        String s1 = "abcde", s2 = "ace";
        System.out.println(lcs.longestCommonSubstr(s1,s2,s1.length(),s2.length()));
    }
}

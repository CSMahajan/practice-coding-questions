package DynamicProgramming;

import java.util.Arrays;

/*
Palindrome Partitioning II

Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.
Example 1:
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:
Input: s = "a"
Output: 0
Example 3:
Input: s = "ab"
Output: 1
*/
public class PalindromePartitioningII {

    public int minCutTopDown(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        //While calculating final value,
        //we need to subtract 1 because we are putting the partition at the end also which is not necessary
        return minCutTopDown(0, n, s, dp) - 1;
    }

    //Time Complexity: O(N*N)
    //Reason: There are a total of N states and inside each state, a loop of size N(apparently) is running.
    //Space Complexity: O(N) + Auxiliary stack space O(N)
    //Reason: The first O(N) is for the dp array of size N.
    private int minCutTopDown(int i, int n, String s, int[] dp) {
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int minimumCost = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, s)) {
                int cost = 1 + minCutTopDown(j + 1, n, s, dp);
                minimumCost = Math.min(minimumCost, cost);
            }
        }
        return dp[i] = minimumCost;
    }

    private boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //Time Complexity: O(N*N)
    //Reason: There are a total of N states and inside each state a loop of size N(apparently) is running.
    //Space Complexity: O(N)
    //Reason: O(N) is for the dp array we have used.
    public int minCutBottomUp(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int minimumCost = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, s)) {
                    int cost = 1 + dp[j + 1];
                    minimumCost = Math.min(minimumCost, cost);
                }
            }
            dp[i] = minimumCost;
        }
        //While calculating final value,
        //we need to subtract 1 because we are putting the partition at the end also which is not necessary
        return dp[0] - 1;
    }

    public static void main(String[] args) {
        String s = "aab";
        PalindromePartitioningII pp = new PalindromePartitioningII();
        System.out.println(pp.minCutTopDown(s));
        System.out.println(pp.minCutBottomUp(s));
    }
}

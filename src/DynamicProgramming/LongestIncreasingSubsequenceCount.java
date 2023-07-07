package DynamicProgramming;

import java.util.Arrays;

/*
Number of Longest Increasing Subsequence

Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.
Example 1:
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1,
and there are 5 increasing subsequences of length 1, so output 5.
*/
public class LongestIncreasingSubsequenceCount {

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops that are run twice.
    //Space Complexity: O(N)
    //Reason: We are only using two rows of size n.
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        //count array stores the total number of ways that lis is achieved
        int[] count = new int[n];
        Arrays.fill(count, 1);
        int maxLengthOfLIS = 0;
        int totalNoOfLIS = 0;
        for (int i = 0; i < n; i++) {
            for (int previousIndex = 0; previousIndex < i; previousIndex++) {
                if (nums[i] > nums[previousIndex] && dp[i] < 1 + dp[previousIndex]) {
                    dp[i] = 1 + dp[previousIndex];
                    count[i] = count[previousIndex];
                } else if (nums[i] > nums[previousIndex] && dp[i] == 1 + dp[previousIndex]) {
                    count[i] += count[previousIndex];
                }
            }
            maxLengthOfLIS = Math.max(maxLengthOfLIS, dp[i]);
        }
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLengthOfLIS) {
                totalNoOfLIS += count[i];
            }
        }
        return totalNoOfLIS;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        LongestIncreasingSubsequenceCount lisc = new LongestIncreasingSubsequenceCount();
        System.out.println(lisc.findNumberOfLIS(nums));
    }
}

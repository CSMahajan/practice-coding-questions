package DynamicProgramming;

import java.util.Arrays;

/*
Longest Bitonic subsequence

Given an array of positive integers. Find the maximum length of Bitonic subsequence.
A subsequence of array is called Bitonic if it is first strictly increasing, then strictly decreasing.
Note:LIS(Increasing) or LDS(Decreasing) are also Bitonic subsequence
Example 1:
Input: nums = [1, 2, 5, 3, 2]
Output: 5
Explanation: The sequence {1, 2, 5} is increasing and the sequence {3, 2} is
decreasing so merging both we will get length 5.
Example 2:
Input: nums = [1, 11, 2, 10, 4, 5, 2, 1]
Output: 6
Explanation: The bitonic sequence {1, 2, 10, 4, 2, 1} has length 6.
*/
public class LongestBitonicSubsequence {

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops that are run twice.
    //Space Complexity: O(N)
    //Reason: We are only using two rows of size n.
    public int LongestBitonicSequence(int[] nums) {
        // Code here
        int n = nums.length;
        //dp1 is kind of prefix sum
        int[] dp1 = new int[n];
        Arrays.fill(dp1,1);
        //dp2 is kind of suffix sum
        int[] dp2 = new int[n];
        Arrays.fill(dp2,1);
        int maximum = 0;
        for (int i = 0; i < n; i++) {
            for (int previousIndex = 0; previousIndex < i; previousIndex++) {
                if (nums[i] > nums[previousIndex] && dp1[i] < 1 + dp1[previousIndex]) {
                    dp1[i] = 1 + dp1[previousIndex];
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int previousIndex = n - 1; previousIndex > i; previousIndex--) {
                if (nums[i] > nums[previousIndex] && dp2[i] < 1 + dp2[previousIndex]) {
                    dp2[i] = 1 + dp2[previousIndex];
                }
            }
            maximum = Math.max(maximum, dp1[i] + dp2[i] - 1);
        }
        return maximum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 11, 2, 10, 4, 5, 2, 1};
        LongestBitonicSubsequence lbs = new LongestBitonicSubsequence();
        System.out.println(lbs.LongestBitonicSequence(nums));
    }
}

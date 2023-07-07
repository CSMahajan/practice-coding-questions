package DynamicProgramming;

/*
Printing Longest Increasing Subsequence

Given an integer n and array of integers, returns the Longest Increasing subsequence
which is lexicographically smallest corresponding to the indices of the elements.
LIS  of a given sequence such that all elements of the subsequence are sorted in increasing order.
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
Note-A subsequence S1 is lexicographically smaller than a subsequence S2 if in the first position where a and b differ,
subsequence a has a letter that appears earlier in the alphabet than the corresponding letter in b.
For example , {1, 2, 3, 6, 7} is lexographically smaller than {1, 2, 3, 8, 10}
and {1, 2, 3} is lexographically smaller than {1, 2, 3, 1}.
Example 1:
Input:
n = 16
arr = [0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15]
Output:
0 4 6 9 13 15
Explanation:
longest Increasing subsequence is 0 4 6 9 13 15  and the length of the longest increasing subsequence is 6.
Example 2:
Input:
n = 1
arr = [1]
Output:
1
*/

import java.util.*;

public class LongestIncreasingSubsequencePrint {

    //Time Complexity: O(N*N)
    //Space Complexity: O(N)
    public static List<Integer> longestIncreasingSubsequenceHashing(int[] arr, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] hash = new int[n];
        Arrays.fill(hash, 1);
        for (int i = 0; i <= n - 1; i++) {
            hash[i] = i; // initializing with current index
            for (int previousIndex = 0; previousIndex <= i - 1; previousIndex++) {
                if (arr[previousIndex] < arr[i] && 1 + dp[previousIndex] > dp[i]) {
                    dp[i] = 1 + dp[previousIndex];
                    hash[i] = previousIndex;
                }
            }
        }
        int maxLength = -1;
        int lastIndex = -1;
        for (int i = 0; i <= n - 1; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        ArrayList<Integer> longestIncreasingSupersequence = new ArrayList<>();
        longestIncreasingSupersequence.add(arr[lastIndex]);
        while (hash[lastIndex] != lastIndex) { // till not reach the initialization value
            lastIndex = hash[lastIndex];
            longestIncreasingSupersequence.add(arr[lastIndex]);
        }
        Collections.sort(longestIncreasingSupersequence);
        return longestIncreasingSupersequence;
    }

    //Time Complexity: O(N*logN)
    //Space Complexity: O(N)
    public static List<Integer> longestIncreasingSubsequenceBinarySearch(int[] nums) {
        int n = nums.length;
        // dp[i] stores the index of the smallest element that ends an increasing subsequence of length i+1
        int[] dp = new int[n];
        // parent[i] stores the index of the previous element in the longest increasing supersequence ending at nums[i]
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = -1; // initialize dp array with -1
            parent[i] = -1; // initialize parent array with -1
        }
        int lengthOfLIS = 1; // length of the longest increasing supersequence
        dp[0] = 0; // initialize dp[0] with the first element index
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[dp[0]]) {
                // nums[i] is the new smallest element
                dp[0] = i;
            } else if (nums[i] > nums[dp[lengthOfLIS - 1]]) {
                // nums[i] extends the longest increasing supersequence
                parent[i] = dp[lengthOfLIS - 1];
                dp[lengthOfLIS++] = i;
            } else {
                // nums[i] is somewhere in between
                // Perform binary search to find the position of nums[i] in dp array
                int low = 0, high = lengthOfLIS - 1;
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (nums[dp[mid]] < nums[i]) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                // Update dp and parent arrays
                parent[i] = dp[low - 1];
                dp[low] = i;
            }
        }
        // Reconstruct the longest increasing supersequence using parent array
        List<Integer> longestSupersequence = new ArrayList<>();
        int index = dp[lengthOfLIS - 1];
        while (index >= 0) {
            longestSupersequence.add(0, nums[index]);
            index = parent[index];
        }
        return longestSupersequence;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = arr.length;
        System.out.println(longestIncreasingSubsequenceHashing(arr, n));
        System.out.println(longestIncreasingSubsequenceBinarySearch(arr));
    }
}
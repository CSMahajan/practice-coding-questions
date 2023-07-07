package DynamicProgramming;

import java.util.Arrays;

/*
Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.
Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1
*/
public class LongestIncreasingSubsequence {

    public int lengthOfLISTopDown(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return lengthOfLISTopDown(0, -1, nums, dp);
    }

    //Time Complexity: O(N*N)
    //Reason: There are N*N states therefore at max ‘N*N’ new problems will be solved.
    //Space Complexity: O(N*N) + O(N)
    //Reason: We are using an auxiliary recursion stack space(O(N))
    //(see the recursive tree, in the worst case we will go till N calls at a time) and a 2D array ( O(N*N+1)).
    private int lengthOfLISTopDown(int index, int previousIndex, int[] arr, int[][] dp) {
        //previousIndex stores the index of the previous element in the current increasing subsequence
        if (index == arr.length) {
            return 0;
        }
        if (dp[index][previousIndex + 1] != -1) {
            return dp[index][previousIndex + 1];
        }
        //Case for not picking the current element into increasing subsequence
        int notPickCurrentElement = lengthOfLISTopDown(index + 1, previousIndex, arr, dp);
        int pickCurrentElement = 0;
        //Case for picking the current element into increasing subsequence
        //previousIndex = -1 signifies the start of calculations
        //if current element is greater than previous indexed element
        if (previousIndex == -1 || arr[index] > arr[previousIndex]) {
            //while picking previousIndex becomes current index,
            //current index will move to next index to check the possibility and length will be increased by 1
            pickCurrentElement = 1 + lengthOfLISTopDown(index + 1, index, arr, dp);
        }
        return dp[index][previousIndex + 1] = Math.max(notPickCurrentElement, pickCurrentElement);
    }

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops
    //Space Complexity: O(N*N)
    //Reason: We are using an external array of size '(N+1)*(N+1)'. Stack Space is eliminated.
    public int lengthOfLISBottomUp(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int previousIndex = index - 1; previousIndex >= -1; previousIndex--) {
                int notPickCurrentElement = dp[index + 1][previousIndex + 1];
                int pickCurrentElement = 0;
                //Case for picking the current element into increasing subsequence
                //previousIndex = -1 signifies the start of calculations
                //if current element is greater than previous indexed element
                if (previousIndex == -1 || nums[index] > nums[previousIndex]) {
                    //while picking previousIndex becomes current index,
                    //current index will move to next index to check the possibility and length will be increased by 1
                    pickCurrentElement = 1 + dp[index + 1][index + 1];
                }
                dp[index][previousIndex + 1] = Math.max(notPickCurrentElement, pickCurrentElement);
            }
        }
        //we are doing -1+1 for previousIndex due to shifting of index or coordinate change even though second loop ends at -1
        return dp[0][-1 + 1];
    }

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops.
    //Space Complexity: O(N)
    //Reason: We are only using two rows of size n.
    public int lengthOfLISSpaceOptimisedTwoArrays(int[] nums) {
        int n = nums.length;
        int[] previousRow = new int[n + 1];
        int[] currentRow = new int[n + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int previousIndex = index - 1; previousIndex >= -1; previousIndex--) {
                int notPickCurrentElement = previousRow[previousIndex + 1];
                int pickCurrentElement = 0;
                //Case for picking the current element into increasing subsequence
                //previousIndex = -1 signifies the start of calculations
                //if current element is greater than previous indexed element
                if (previousIndex == -1 || nums[index] > nums[previousIndex]) {
                    //while picking previousIndex becomes current index,
                    //current index will move to next index to check the possibility and length will be increased by 1
                    pickCurrentElement = 1 + previousRow[index + 1];
                }
                currentRow[previousIndex + 1] = Math.max(notPickCurrentElement, pickCurrentElement);
            }
            previousRow = currentRow.clone();
        }
        //we are doing -1+1 for previousIndex due to shifting of index or coordinate change even though second loop ends at -1
        return previousRow[-1 + 1];
    }

    //Time Complexity: O(N*N)
    //Reason: There are two nested loops.
    //Space Complexity: O(N)
    //Reason: We are only using two rows of size 'N'.
    public int lengthOfLISSpaceOptimisedTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int index = 0; index <= n - 1; index++) {
            for (int previousIndex = 0; previousIndex <= index - 1; previousIndex++) {
                if (nums[previousIndex] < nums[index]) {
                    dp[index] = Math.max(dp[index], 1 + dp[previousIndex]);
                }
            }
        }
        int ans = -1;
        for (int i = 0; i <= n - 1; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //Time Complexity: O(N*logN)
    //Space Complexity: O(N)
    public int lengthOfLISSpaceOptimisedBinarySearch(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // dp[i] stores the index of the smallest element that ends an increasing subsequence of length i+1
        Arrays.fill(dp, -1);
        int lengthOfLIS = 1; // length of the longest increasing supersequence
        dp[0] = 0; // initialize dp[0] with the first element index
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[dp[0]]) {
                // nums[i] is the new smallest element
                dp[0] = i;
            } else if (nums[i] > nums[dp[lengthOfLIS - 1]]) {
                // nums[i] extends the longest increasing supersequence
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
                // Update dp array
                dp[low] = i;
            }
        }
        return lengthOfLIS;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lis.lengthOfLISTopDown(nums));
        System.out.println(lis.lengthOfLISBottomUp(nums));
        System.out.println(lis.lengthOfLISSpaceOptimisedTwoArrays(nums));
        System.out.println(lis.lengthOfLISSpaceOptimisedTabulation(nums));
        System.out.println(lis.lengthOfLISSpaceOptimisedBinarySearch(nums));
    }
}

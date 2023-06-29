package DynamicProgramming;

/*
House Robber

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
adjacent houses have security systems connected and
it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
*/

import java.util.Arrays;

public class HouseRobber {

    //This is same as MaxSumNonAdjacentElements.java problem
    public int robTopDown(int[] nums) {
        // code here
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return robTopDown(nums, dp, n - 1);
    }

    //TC:O(N), we are calling recursively which would give as O(2^N), but once calculated result is not computed again,
    //so we are calling for every index from 0 to N-1 only once, so it is O(N)
    //SC:O(N) + O(N), for recursion stack space and dp array
    private int robTopDown(int[] arr, int[] dp, int index) {
        //for negative element indexes, we will consider them as 0 which occurs for index - 2
        if (index < 0) return 0;
        //index == 0 occurs when index=1 element is not picked, as we have to maximise the sum we pick that element
        if (index == 0) return arr[0];
        //sum for that index as a sub-problem is already calculated so returning the same for memoization
        if (dp[index] != -1) return dp[index];
        //picking up the current element and calling for index-2 as adjacent elements are not allowed
        int pickCurrentElement = arr[index] + robTopDown(arr, dp, index - 2);
        //notPick can be mentioned as 0 + findMaxSumTopDown(arr, dp, index - 1)
        int notPickCurrentElement = robTopDown(arr, dp, index - 1);
        //storing result in dp array
        return dp[index] = Math.max(pickCurrentElement, notPickCurrentElement);
    }

    public int robBottomUp(int[] nums) {
        // code here
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return robBottomUp(nums, dp, n);
    }

    //TC:O(N), we are getting results for 0 to N-1 through dp array, once calculated result is stored in dp array
    //SC:O(N) , for dp array
    private int robBottomUp(int[] arr, int[] dp, int n) {
        //index == 0 occurs when index=1 element is not picked, as we have to maximise the sum we pick that element
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            //picking up the current element and calling for index-2 as adjacent elements are not allowed
            int pickCurrentElement = arr[i];
            if (i > 1) {
                pickCurrentElement += dp[i - 2];
            }
            //notPick can be mentioned as dp[i - 1]
            int notPickCurrentElement = dp[i - 1];
            dp[i] = Math.max(pickCurrentElement, notPickCurrentElement);
        }
        //storing result in dp array
        return dp[n - 1];
    }

    //TC:O(N), we are using two variables and updating them while getting current maximum sum
    //SC:O(1)
    public int robSpaceOptimised(int[] nums) {
        int n = nums.length;
        int previous1 = nums[0];
        int previous2 = 0;
        for (int i = 1; i < n; i++) {
            //picking up the current element and calling for index-2 as adjacent elements are not allowed
            int pickCurrentElement = nums[i];
            if (i > 1) {
                pickCurrentElement += previous2;
            }
            //notPick can be mentioned as 0 + previous1
            int notPickCurrentElement = previous1;
            int currentMaximumSum = Math.max(pickCurrentElement, notPickCurrentElement);
            previous2 = previous1;
            previous1 = currentMaximumSum;
        }
        //when loop ends i becomes n, i.e.i=n and previous1 stays at n-1
        //which is last index in array which is our required answer
        return previous1;
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] height = {2,7,9,3,1};
        System.out.println(hr.robTopDown(height));
        System.out.println(hr.robBottomUp(height));
        System.out.println(hr.robSpaceOptimised(height));
    }
}
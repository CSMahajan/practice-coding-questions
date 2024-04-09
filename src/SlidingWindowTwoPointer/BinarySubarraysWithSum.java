package SlidingWindowTwoPointer;

/*
Binary Subarrays With Sum

Given a binary array nums and an integer goal,
return the number of non-empty subarrays with a sum goal.
A subarray is a contiguous part of the array.
Example 1:
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:
Input: nums = [0,0,0,0,0], goal = 0
Output: 15
*/

public class BinarySubarraysWithSum {

    //TC:O(4N)
    //SC:O(1)
    public int numSubarraysWithSum(int[] nums, int goal) {
        //if we want to find count of binary subarrays with sum equal to goal,
        //then we can calculate {sum <= goal} and {sum <= (goal-1)} and its
        //difference will give us the total number of subarrays with sum equal to goal
        //otherwise this can also be solved using prefixSum with TC:O(N), SC:O(N)
        int lessOrEqualToGoalCount = countSubarraysLessThanOrEqualToGoal(nums, goal);
        int lessOrEqualToGoalMinusOneCount = countSubarraysLessThanOrEqualToGoal(nums, goal - 1);
        return lessOrEqualToGoalCount - lessOrEqualToGoalMinusOneCount;
    }

    private int countSubarraysLessThanOrEqualToGoal(int[] nums, int goal) {
        //in this binary subarray if goal was originally 0 then
        //goal-1 call for this function would make goal as negative
        //so below negative goal condition is required
        if (goal < 0) {
            return 0;
        }
        int n = nums.length;
        int l = 0, r = 0, sum = 0, countOfSubarrays = 0;
        while (r < n) {
            sum += nums[r];
            while (sum > goal) {
                //reducing the sum to fit into goal such that sum should be less or equal to goal
                sum -= nums[l];
                //moving the window
                l++;
            }
            //we are increasing the count directly by window length because
            //it has all the subarrays with sum as less or equal to goal
            //(containing 0's counts as well which was otherwise missed)
            int windowLength = r - l + 1;
            countOfSubarrays += windowLength;
            r++;
        }
        return countOfSubarrays;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        BinarySubarraysWithSum bsws = new BinarySubarraysWithSum();
        System.out.println(bsws.numSubarraysWithSum(nums, goal));
    }
}

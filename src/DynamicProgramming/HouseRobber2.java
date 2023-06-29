package DynamicProgramming;

/*
House Robber II

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
Meanwhile, adjacent houses have a security system connected,
and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.
Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:
Input: nums = [1,2,3]
Output: 3
*/

public class HouseRobber2 {

    //TC:O(N) + O(N), for 1 loop for notConsiderFirstHouse & notConsiderLastHouse each
    //SC:O(N) + O(N), for 1 array for notConsiderFirstHouse & notConsiderLastHouse each
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] notConsiderFirstHouse = new int[n];
        int[] notConsiderLastHouse = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                notConsiderFirstHouse[j++] = nums[i];
            }
            if (i != n - 1) {
                notConsiderLastHouse[i] = nums[i];
            }
        }
        return Math.max(robSpaceOptimised(notConsiderFirstHouse), robSpaceOptimised(notConsiderLastHouse));
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
        HouseRobber2 hr = new HouseRobber2();
        int[] height = {1,2,3,1};
        System.out.println(hr.rob(height));
    }
}
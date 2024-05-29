package GreedyAlgorithm;

/*
Jump Game II

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1].
The test cases are generated such that you can reach nums[n - 1].
Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
*/
public class JumpGame2 {

    public int jump(int[] nums) {
        int jumps = 0, l = 0, r = 0;
        int n = nums.length;
        while(r < n - 1) {
            int farthestIndex = 0;
            for(int i = l; i <= r; i++) {
                farthestIndex = Math.max(farthestIndex, i + nums[i]);
            }
            jumps++;
            l = r + 1;
            r = farthestIndex;
        }
        return jumps;
    }

    public static void main(String[] args) {
        JumpGame2 jg = new JumpGame2();
        int[] nums = {2,3,1,1,4};
        System.out.println(jg.jump(nums));
    }
}

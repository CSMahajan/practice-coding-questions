/*
Max Consecutive Ones

Given a binary array nums, return the maximum number of consecutive 1's in the array.
Example 1:
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
The maximum number of consecutive 1s is 3.
Example 2:
Input: nums = [1,0,1,1,0,1]
Output: 2
*/
public class MaximumConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maximum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }
            maximum = Math.max(count, maximum);
        }
        return maximum;
    }

    public static void main(String[] args) {
        int nums[] = {1,1,0,1,1,1};
        MaximumConsecutiveOnes mco = new MaximumConsecutiveOnes();
        System.out.println(mco.findMaxConsecutiveOnes(nums));
    }
}

package SlidingWindowTwoPointer;

/*
Max Consecutive Ones III

Given a binary array nums and an integer k,
return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
*/
public class MaxConsecutiveOnesWithKZerosFlips {

    //TC:O(N)
    //SC:O(1)
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxLength = 0;
        int l = 0, r = 0;
        int countOfZero = 0;
        while (r < n) {
            //if current is 0, increase zeros count
            if (nums[r] == 0) {
                countOfZero++;
            }
            //if zeros count crosses threshold, move left pointer of the window
            //while moving if left pointer is at 0, moving/shifting the window by reducing the zeros count
            if (countOfZero > k) {
                if (nums[l] == 0) {
                    countOfZero--;
                }
                l++;
            }
            //if zeros count stays within limit, calculate length of the window and store maxLength
            if (countOfZero <= k) {
                int length = r - l + 1;
                maxLength = Math.max(maxLength, length);
            }
            //move the right pointer
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        MaxConsecutiveOnesWithKZerosFlips mcowkzf = new MaxConsecutiveOnesWithKZerosFlips();
        System.out.println(mcowkzf.longestOnes(nums, k));
    }
}

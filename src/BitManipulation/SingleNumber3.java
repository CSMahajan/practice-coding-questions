package BitManipulation;

import java.util.Arrays;

/*
Single Number III

Given an integer array nums, in which exactly two elements appear only once and
all the other elements appear exactly twice.
Find the two elements that appear only once. You can return the answer in any order.
You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
Example 1:
Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
Example 2:
Input: nums = [-1,0]
Output: [-1,0]
Example 3:
Input: nums = [0,1]
Output: [1,0]
*/
public class SingleNumber3 {

    //TC:O(2N)
    //SC:O(1)
    public int[] singleNumber(int[] nums) {
        int xorr = 0;
        for (int num : nums) {
            xorr ^= num;
        }
        //this is how we can find differentiating bit position among 2 distinct numbers right most bit
        int rightMostBit = (xorr & (xorr - 1)) ^ xorr;
        //numbers with bit set will go in b1 and not set will go in b2
        int b1 = 0;
        int b2 = 0;
        for (int num : nums) {
            if ((num & rightMostBit) != 0) {
                b1 ^= num;
            } else {
                b2 ^= num;
            }
        }
        return new int[]{b1, b2};
    }

    public static void main(String[] args) {
        SingleNumber3 sn3 = new SingleNumber3();
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(Arrays.toString(sn3.singleNumber(nums)));
    }
}

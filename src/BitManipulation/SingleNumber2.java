package BitManipulation;

/*
Single Number II

Given an integer array nums where every element appears three times except for one,
which appears exactly once. Find the single element and return it.
You must implement a solution with a linear runtime complexity and use only constant extra space.
Example 1:
Input: nums = [2,2,3,2]
Output: 3
Example 2:
Input: nums = [0,1,0,1,0,1,99]
Output: 99
*/
public class SingleNumber2 {

    //TC:O(N*32)
    //SC:O(1)
    public int singleNumber(int[] nums) {
        int singleOccurringNumber = 0;
        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
            int count = 0;
            for (int num : nums) {
                if ((num & (1 << bitIndex)) != 0) {
                    count++;
                }
            }
            if (count % 3 == 1) {
                singleOccurringNumber = singleOccurringNumber | (1 << bitIndex);
            }
        }
        return singleOccurringNumber;
    }

    //TC:O(N)
    //SC:O(1)
    public int singleNumberUsingBit(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 0, 1, 99};
        SingleNumber2 sn2 = new SingleNumber2();
        System.out.println(sn2.singleNumber(nums));
        System.out.println(sn2.singleNumberUsingBit(nums));
    }
}


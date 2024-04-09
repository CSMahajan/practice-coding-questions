package SlidingWindowTwoPointer;

import java.util.HashMap;
import java.util.Map;

/*
Subarrays with K Different Integers

Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers:
[1,2,1,3], [2,1,3], [1,3,4].Given an integer array nums and an integer k,
return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.
Example 1:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers:
[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
*/
public class SubarraysWithKDifferentIntegers {

    //TC:O(2N)
    //SC:O(N)
    public int subarraysWithKDistinct(int[] nums, int k) {
        //This is the mixture of solution of 
        //CountNumberOfNiceSubarraysKOddNumbers(or BinarySubarraysWithSum) and LongestSubstringKMostDistinctCharacters
        //if we want to find count of subarrays with k distinct integers in each subarray,
        //then we can calculate {count <= k} and {count <= (k-1)} and its
        //difference will give us the total number of subarrays with count equal to k
        //Above part is same as CountNumberOfNiceSubarraysKOddNumbers or BinarySubbarraysWithSum
        //The implementation for finding count of subarrays is similar to LongestSubstringKMostDistinctCharacters
        int lessOrEqualToKCount = countSubarraysLessThanOrEqualToK(nums, k);
        int lessOrEqualToKMinusOneCount = countSubarraysLessThanOrEqualToK(nums, k - 1);
        return lessOrEqualToKCount - lessOrEqualToKMinusOneCount;
    }

    private int countSubarraysLessThanOrEqualToK(int[] nums, int k) {
        //in this binary subarray if k was originally 0 then
        //k-1 call for this function would make k as negative
        //so below negative k condition is required
        if (k < 0) {
            return 0;
        }
        int n = nums.length;
        int l = 0, r = 0, countOfSubarrays = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < n) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.size() > k) {
                //reducing the sum to fit into k such that sum should be less or equal to k
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    //if current character is not available in map, remove it from map
                    map.remove(nums[l]);
                }
                //moving the window
                l++;
            }
            //we are increasing the count directly by window length because
            //it has all the subarrays with sum as less or equal to k
            //(containing 0's counts as well which was otherwise missed)
            int windowLength = r - l + 1;
            countOfSubarrays += windowLength;
            r++;
        }
        return countOfSubarrays;
    }

    public static void main(String[] args) {
        SubarraysWithKDifferentIntegers swkdi = new SubarraysWithKDifferentIntegers();
        int[] nums = {1,2,1,2,3};
        int k = 2;
        System.out.println(swkdi.subarraysWithKDistinct(nums, k));
    }
}

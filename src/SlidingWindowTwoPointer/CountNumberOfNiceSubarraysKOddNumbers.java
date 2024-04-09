package SlidingWindowTwoPointer;

/*
Count Number of Nice Subarrays

Given an array of integers nums and an integer k.
A continuous subarray is called nice if there are k odd numbers on it.
Return the number of nice sub-arrays.
Example 1:
Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:
Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.
Example 3:
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
*/
public class CountNumberOfNiceSubarraysKOddNumbers {

    //TC:O(4N)
    //SC:O(1)
    public int numberOfSubarrays(int[] nums, int k) {
        //This problem is exactly similar to BinarySubarraysWithSum
        //The only difference is sum = goal is replaced by count of k odd numbers in each subarray
        //if we want to find count of subarrays with odd numbers with k count in each subarray,
        //then we can calculate {sum <= k} and {sum <= (k-1)} and its
        //difference will give us the total number of subarrays with sum equal to goal
        //otherwise this can also be solved using prefixSum with TC:O(N), SC:O(N)
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
        int l = 0, r = 0, sum = 0, countOfSubarrays = 0;
        while (r < n) {
            sum += nums[r]%2;
            while (sum > k) {
                //reducing the sum to fit into k such that sum should be less or equal to k
                sum -= nums[l]%2;
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
        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        int k = 2;
        CountNumberOfNiceSubarraysKOddNumbers cns = new CountNumberOfNiceSubarraysKOddNumbers();
        System.out.println(cns.numberOfSubarrays(nums, k));
    }
}

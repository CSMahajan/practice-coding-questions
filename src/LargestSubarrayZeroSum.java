import java.util.*;

public class LargestSubarrayZeroSum {

    /*
    Largest subarray with 0 sum

    Given an array having both positive and negative integers. The task is to compute the length of the largest subarray with sum 0.

Example 1:

Input:
N = 8
A[] = {15,-2,2,-8,1,7,10,23}
Output: 5
Explanation: The largest subarray with
sum 0 will be -2 2 -8 1 7.
*/

    public static int maxLengthOfLargestSubarraySum(int arr[], int n) {
        // Your code here
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0) {
                maxLength = i + 1;
            } else {
                if (map.containsKey(sum)) {// if sum is present in the hashmap
                    maxLength = Math.max(maxLength, i - map.get(sum));
                } else {
                    map.put(sum, i);//if sum is not present in the hashmap
                }
            }
        }
        return maxLength;
    }
}

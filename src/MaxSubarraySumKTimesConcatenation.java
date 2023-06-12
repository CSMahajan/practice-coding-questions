import java.util.ArrayList;
import java.util.List;

/*
Maximum subarray sum after K concatenation

Given an integer array arr and an integer k, modify the array by repeating it k times.

For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

Return the maximum sub-array sum in the modified array..i.e. here it is 9
Note that the length of the sub-array can be 0 and its sum in that case is 0.
Example 2:

Input: arr = [1,-2,1], k = 5
Output: 2
*/
public class MaxSubarraySumKTimesConcatenation {
    public static long maxSubSumKConcat(ArrayList<Integer> arr, int n, int k) {
        // Write your code here.
        long currentSum = 0, maxPossibleSum = 0;
        boolean isAllNegative = true;
        for (int number : arr) {
            if (number > 0) {
                isAllNegative = false;
                break;
            }
        }
        long maxNum = Long.MIN_VALUE;
        if (isAllNegative) {
            for (int number : arr) {
                if (maxNum < number) {
                    maxNum = number;
                }
            }
            return maxNum;
        }
        for (int i = 0; i < n * k; i++) {
            currentSum += arr.get(i % n);
            maxPossibleSum = Math.max(maxPossibleSum, currentSum);
            currentSum = Math.max(currentSum, 0);
        }
        return maxPossibleSum;
    }

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(-4);
        list.add(-3);
        System.out.println(maxSubSumKConcat(list, 2, 3));
    }
}

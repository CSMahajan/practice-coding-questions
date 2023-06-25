import java.util.HashMap;
import java.util.Map;

/*
Longest Sub-Array with Sum K

Given an array containing N integers and an integer K.,
Your task is to find the length of the longest Sub-Array with the sum of the elements equal to the given value K.
The array contains positive and negative elements
Example 1:
Input :
A[] = {10, 5, 2, 7, 1, 9}
K = 15
Output : 4
Explanation:The sub-array is {5, 2, 7, 1}.
Example 2:
Input :
A[] = {-1, 2, 3}
K = 6
Output : 0
Explanation: There is no such sub-array with sum 6.
*/
public class LargetSubarrayWithSumKPositivesNegatives {

    //Time Complexity: O(N) or O(N*logN) depending on which map data structure we are using,
    //where N = size of the array
    public static int getLongestSubarray(int []nums, int k) {
        // Write your code here.
        int n = nums.length; // size of the array.
        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += nums[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int a[] = {-1, 2, 3};
        int k = 6;
        System.out.println(getLongestSubarray(a,k));
    }
}

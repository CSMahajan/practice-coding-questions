package Hashing;

import java.util.HashMap;
import java.util.Map;

/*
Find highest and lowest frequency elements in array.

Example 1:
Input: array[] = {10,5,10,15,10,5};
Output: 10 15
Explanation: The frequency of 10 is 3, i.e. the highest and the frequency of 15 is 1 i.e. the lowest.

Example 2:

Input: array[] = {2,2,3,4,4,2};
Output: 2 3
Explanation: The frequency of 2 is 3, i.e. the highest and the frequency of 3 is 1 i.e. the lowest.
 */
public class HighestLowestFrequentElement {

    public static void frequency(int arr[], int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int minFrequency = Integer.MAX_VALUE;
        int maxFrequency = Integer.MIN_VALUE;
        int minElement = 0;
        int maxElement = 0;
        for(Map.Entry<Integer, Integer> mapEntry: map.entrySet()) {
            int number = mapEntry.getKey();
            int frequency = mapEntry.getValue();
            if(maxFrequency < frequency) {
                maxFrequency = frequency;
                maxElement = number;
            }
            if(minFrequency > frequency) {
                minFrequency = frequency;
                minElement = number;
            }
        }
        System.out.println(maxElement + " " + minElement);
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 10, 15, 10, 5};
        int n = nums.length;
        frequency(nums, n);
    }
}

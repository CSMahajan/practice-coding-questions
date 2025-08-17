package Hashing;

/*
Given an array nums of size n which may contain duplicate elements, return a list of pairs where
each pair contains a unique element from the array and its frequency in the array.
You may return the result in any order, but each element must appear exactly once in the output.

Examples:
Input: nums = [1, 2, 2, 1, 3]
Output: [[1, 2], [2, 2], [3, 1]]

Explanation:
- 1 appears 2 times
- 2 appears 2 times
- 3 appears 1 time

Order of output can vary.
Input: nums = [5, 5, 5, 5]
Output: [[5, 4]]

Explanation:
- 5 appears 4 times.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountFrequencies {

    public static List<List<Integer>> countFrequencies(int[] nums) {
        // Your code goes here
        int n = nums.length;
        List<List<Integer>> allList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i],0) + 1);
        }
        for(Map.Entry<Integer, Integer> mapEntry: map.entrySet()) {
            List<Integer> list = new ArrayList<>();
            list.add(mapEntry.getKey());
            list.add(mapEntry.getValue());
            allList.add(list);
        }
        return allList;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 1, 3};
        System.out.print(countFrequencies(nums));
    }
}

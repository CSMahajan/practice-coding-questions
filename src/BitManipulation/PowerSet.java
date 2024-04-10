package BitManipulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Power set

Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:
Input: nums = [0]
Output: [[],[0]]
*/
public class PowerSet {

    //TC:O(N*2^N)
    //SC:O(N*2^N)
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int noOfSubsets = 1 << n;
        List<List<Integer>> result = new ArrayList<>();
        for (int num = 0; num < noOfSubsets; num++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((num & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }
        return result;
    }

    public static ArrayList<String> subsequences(String str) {
        int n = str.length();
        int noOfSubsets = 1 << n;
        ArrayList<String> result = new ArrayList<>();
        for (int num = 0; num < noOfSubsets; num++) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((num & (1 << i)) != 0) {
                    s.append((str.charAt(i)));
                }
            }
            result.add(s.toString());
        }
        result.removeIf(String::isEmpty);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        PowerSet ps = new PowerSet();
        int[] nums = {1, 2, 3};
        String str = "abc";
        System.out.println(ps.subsets(nums));
        System.out.println(subsequences(str));
    }
}

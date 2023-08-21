package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
4Sum

Given an array nums of n integers,
return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.
Example 1:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
*/
public class FourSum {

    //Time Complexity: O(N3), where N = size of the array.
    //Reason: Each of the pointers i and j, is running for approximately N times.
    //And both the pointers k and l combined can run for approximately N times including the operation of skipping duplicates.
    //So the total time complexity will be O(N3).
    //Space Complexity: O(no. of quadruplets), This space is only used to store the answer.
    //We are not using any extra space to solve this problem.
    //So, from that perspective, space complexity can be written as O(1).
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> fourSumList = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[left];
                    sum += nums[right];
                    if (sum == target) {
                        List<Integer> currentSumList = new ArrayList<>();
                        currentSumList.add(nums[i]);
                        currentSumList.add(nums[j]);
                        currentSumList.add(nums[left]);
                        currentSumList.add(nums[right]);
                        fourSumList.add(currentSumList);
                        left++;
                        right--;
                        while (left < right && nums[left - 1] == nums[left]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return fourSumList;
    }

    public static void main(String[] args) {
        int[] nums = {10, 2, 3, 4, 5, 7, 8};
        int target = 23;
        FourSum fs = new FourSum();
        System.out.println(fs.fourSum(nums, target));
    }
}
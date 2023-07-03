package DynamicProgramming;

import java.util.Arrays;

/*
Target Sum

You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-'
before each integer in nums and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-'
before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.
Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:
Input: nums = [1], target = 1
Output: 1

*/
public class TargetSumPlusMinus {

    public int findTargetSumWaysTopDown(int[] nums, int target) {
        int n = nums.length;
        int totalSum = 0;
        for (int number : nums) {
            totalSum += number;
        }
        /*
        Here we want to assign plus and minus sign to each element in an array such that after assigning signs the given target should be achieved as a sum.
        So, there will be basically two groups/subsets, one having all positive signs elements(s1) and another having all negative signs(s2).
        So there sum will be s1 + -(s2) = s1 - s2(because all negative numbers add up to bigger negative number.
        This sum is equal to target, so this question becomes s1 - s2 = target
        Here we want to find how many different pairs of subsets s1 and s2 can be formed such that
        difference between s1 and s2 is given target d.We also know that totalSum is sum of s1 and s2.
        So, totalSum = s1 + s2; and s1 - s2 = target; where s1 > s2. So putting s1 from first equation into second gives us:
        (totalSum - s2) - s2 = d, gives totalSum - 2*s2 = target, which gives us:
        s2 = (totalSum - target) / 2
        So our target is getting changed basically for which we need to check total subsets count, so our target will be s2.
        We know that totalSum = s1+s2 that means totalSum should always be greater than given difference
        and also all numbers in array are 0 <= arr[i] and integers, so s2 is also going to be integer, so (totalSum - d)
        is also going to be even number/integer
        */
        //Checking edge cases
        if (totalSum - target < 0) return 0;
        if ((totalSum - target) % 2 == 1) return 0;

        int s2 = (totalSum - target) / 2;

        int[][] dp = new int[n][s2 + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return findTargetSumWaysTopDown(n - 1, s2, nums, dp);
    }

    //Time Complexity: O(N*target)
    //Reason: There are N*target states therefore at max ‘N*target’ new problems will be solved.
    //Space Complexity: O(N*target) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*target)).
    private int findTargetSumWaysTopDown(int index, int target, int[] arr, int[][] dp) {
        if (index == 0) {
            //We need to modify the edge cases in SubsetSumTargetCount.java as well
            //sum==0 base case fails if array starts from 0 because, count is not getting added
            //target as 0 and first element as 0 means our target is already achieved and
            //its up to us to either take it or not to take the 0th element(i.e.0 itself), so which gives 2 possibilities
            if (target == 0 && arr[0] == 0) return 2;
            //there is only one possibility if our remaining target is arr[0] WHICH IS NOT 0 and that is to pick that element so returning 1
            if (target == 0 || target == arr[0]) return 1;
            //As we have computed both edge cases otherwise we can return 0
            return 0;
        }

        if (dp[index][target] != -1) {
            return dp[index][target];
        }

        int notPickCurrentElement = findTargetSumWaysTopDown(index - 1, target, arr, dp);
        int pickCurrentElement = 0;
        if (arr[index] <= target) {
            pickCurrentElement = findTargetSumWaysTopDown(index - 1, target - arr[index], arr, dp);
        }
        return dp[index][target] = (notPickCurrentElement + pickCurrentElement);
    }

    public int findTargetSumWaysBottomUp(int[] nums, int target) {
        // Code here
        int totalSum = 0;
        for (int number : nums) {
            totalSum += number;
        }
        /*
        Here we want to assign plus and minus sign to each element in an array such that after assigning signs the given target should be achieved as a sum.
        So, there will be basically two groups/subsets, one having all positive signs elements(s1) and another having all negative signs(s2).
        So there sum will be s1 + -(s2) = s1 - s2(because all negative numbers add up to bigger negative number.
        This sum is equal to target, so this question becomes s1 - s2 = target
        Here we want to find how many different pairs of subsets s1 and s2 can be formed such that
        difference between s1 and s2 is given target d.We also know that totalSum is sum of s1 and s2.
        So, totalSum = s1 + s2; and s1 - s2 = target; where s1 > s2. So putting s1 from first equation into second gives us:
        (totalSum - s2) - s2 = d, gives totalSum - 2*s2 = target, which gives us:
        s2 = (totalSum - target) / 2
        So our target is getting changed basically for which we need to check total subsets count, so our target will be s2.
        We know that totalSum = s1+s2 that means totalSum should always be greater than given difference
        and also all numbers in array are 0 <= arr[i] and integers, so s2 is also going to be integer, so (totalSum - d)
        is also going to be even number/integer
        */
        //Checking edge cases
        if (totalSum - target < 0) return 0;
        if ((totalSum - target) % 2 == 1) return 0;
        int s2 = (totalSum - target) / 2;
        int n = nums.length;
        return findTargetSumWaysBottomUp(n, nums, s2);
    }

    //Time Complexity: O(N*target)
    //Reason: There are two nested loops
    //Space Complexity: O(N*target)
    //Reason: We are using an external array of size ‘N*target’. Stack Space is eliminated.
    private int findTargetSumWaysBottomUp(int n, int[] arr, int subsetS2Target) {
        int[][] dp = new int[n][subsetS2Target + 1];
        if (arr[0] == 0) {
            dp[0][0] = 2;// here 2 cases of pick and not pick arise so is taken as 2 into dp array for dp[0][0]
        } else {
            dp[0][0] = 1;// here 1 case of not pick arises so is taken as 1 into dp array for dp[0][0]
        }
        if (arr[0] != 0 && arr[0] <= subsetS2Target) {
            dp[0][arr[0]] = 1;// here 1 case of pick arises so is taken as 1 into dp array for dp[0][arr[0]]
        }
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= subsetS2Target; target++) {
                int notPickCurrentElement = dp[index - 1][target];
                int pickCurrentElement = 0;
                if (arr[index] <= target) {
                    pickCurrentElement = dp[index - 1][target - arr[index]];
                }
                dp[index][target] = (notPickCurrentElement + pickCurrentElement);
            }
        }
        return dp[n - 1][subsetS2Target];
    }

    public int findTargetSumWaysSpaceOptimised(int[] nums, int target) {
        // Code here
        int totalSum = 0;
        for (int number : nums) {
            totalSum += number;
        }
        /*
        Here we want to assign plus and minus sign to each element in an array such that after assigning signs the given target should be achieved as a sum.
        So, there will be basically two groups/subsets, one having all positive signs elements(s1) and another having all negative signs(s2).
        So there sum will be s1 + -(s2) = s1 - s2(because all negative numbers add up to bigger negative number.
        This sum is equal to target, so this question becomes s1 - s2 = target
        Here we want to find how many different pairs of subsets s1 and s2 can be formed such that
        difference between s1 and s2 is given target d.We also know that totalSum is sum of s1 and s2.
        So, totalSum = s1 + s2; and s1 - s2 = target; where s1 > s2. So putting s1 from first equation into second gives us:
        (totalSum - s2) - s2 = d, gives totalSum - 2*s2 = target, which gives us:
        s2 = (totalSum - target) / 2
        So our target is getting changed basically for which we need to check total subsets count, so our target will be s2.
        We know that totalSum = s1+s2 that means totalSum should always be greater than given difference
        and also all numbers in array are 0 <= arr[i] and integers, so s2 is also going to be integer, so (totalSum - d)
        is also going to be even number/integer
        */
        //Checking edge cases
        if (totalSum - target < 0) return 0;
        if ((totalSum - target) % 2 == 1) return 0;
        int s2 = (totalSum - target) / 2;
        int n = nums.length;
        return findTargetSumWaysSpaceOptimised(n, nums, s2);
    }

    //Time Complexity: O(N*target)
    //Reason: There are three nested loops
    //Space Complexity: O(target)
    //Reason: We are using an external array of size ‘target+1’ to store only one row.
    private int findTargetSumWaysSpaceOptimised(int n, int[] arr, int subsetS2Target) {
        int[] previousRow = new int[subsetS2Target + 1];
        int[] currentRow = new int[subsetS2Target + 1];
        if (arr[0] == 0) {
            previousRow[0] = 2;// here 2 cases of pick and not pick arise so is taken as 2 into dp array for dp[0][0]
        } else {
            previousRow[0] = 1;// here 1 case of not pick arises so is taken as 1 into dp array for dp[0][0]
        }
        if (arr[0] != 0 && arr[0] <= subsetS2Target) {
            previousRow[arr[0]] = 1;// here 1 case of pick arises so is taken as 1 into dp array for dp[0][arr[0]]
        }
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= subsetS2Target; target++) {
                int notPickCurrentElement = previousRow[target];
                int pickCurrentElement = 0;
                if (arr[index] <= target) {
                    pickCurrentElement = previousRow[target - arr[index]];
                }
                currentRow[target] = (notPickCurrentElement + pickCurrentElement);
            }
            previousRow = currentRow.clone();
        }
        return previousRow[subsetS2Target];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;
        TargetSumPlusMinus tspm = new TargetSumPlusMinus();
        System.out.println(tspm.findTargetSumWaysTopDown(arr, target));
        System.out.println(tspm.findTargetSumWaysBottomUp(arr, target));
        System.out.println(tspm.findTargetSumWaysSpaceOptimised(arr, target));
    }
}

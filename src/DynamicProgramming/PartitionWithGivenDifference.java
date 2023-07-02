package DynamicProgramming;

/*
Partitions with Given Difference

Given an array arr, partition it into two subsets(possibly empty) such that their union is the original array.
Let the sum of the element of these two subsets be S1 and S2. Given a difference d,
count the number of partitions in which S1 is greater than or equal to S2 and the difference S1 and S2 is equal to d.
since the answer may be large return it modulo 109 + 7.
Example 1:
Input:
n = 4, d = 3
arr[] =  { 5, 2, 6, 4}
Output: 1
Explanation:
There is only one possible partition of this array.
Partition : {6, 4}, {5, 2}. The subset difference between subset sum is: (6 + 4) - (5 + 2) = 3.
Example 2:
Input:
n = 4, d = 0 arr[] = {1, 1, 1, 1}
Output: 6
*/

import java.util.Arrays;

public class PartitionWithGivenDifference {

    public int countPartitionsTopDown(int n, int d, int[] arr) {
        // Code here
        int mod = 1000000007;
        int totalSum = 0;
        for (int number : arr) {
            totalSum += number;
        }
        /*
        Here we want to find how many different pairs of subsets s1 and s2 can be formed such that
        difference between s1 and s2 is given difference d.We also know that totalSum is sum of s1 and s2.
        So, totalSum = s1 + s2; and s1 - s2 = d; where s1 > s2. So putting s1 from first equation into second gives us:
        (totalSum - s2) - s2 = d, gives totalSum - 2*s2 = d, which gives us:
        s2 = (totalSum - d) / 2
        So our target is getting changed basically for which we need to check total subsets count, so our target will be s2.
        We know that totalSum = s1+s2 that means totalSum should always be greater than given difference
        and also all numbers in array are 0 <= arr[i] and integers, so s2 is also going to be integer, so (totalSum - d)
        is also going to be even number/integer
        */
        //Checking edge cases
        if (totalSum - d < 0) return 0;
        if ((totalSum - d) % 2 == 1) return 0;

        int s2 = (totalSum - d) / 2;

        int[][] dp = new int[n][s2 + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return countPartitionsTopDown(n - 1, s2, arr, dp) % mod;
    }

    //Time Complexity: O(N*target)
    //Reason: There are N*target states therefore at max ‘N*target’ new problems will be solved.
    //Space Complexity: O(N*target) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*target)).
    private int countPartitionsTopDown(int index, int target, int[] arr, int[][] dp) {
        int mod = 1000000007;
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

        int notPickCurrentElement = countPartitionsTopDown(index - 1, target, arr, dp);
        int pickCurrentElement = 0;
        if (arr[index] <= target) {
            pickCurrentElement = countPartitionsTopDown(index - 1, target - arr[index], arr, dp);
        }
        return dp[index][target] = (notPickCurrentElement + pickCurrentElement) % mod;
    }

    public int countPartitionsBottomUp(int n, int d, int[] arr) {
        // Code here
        int mod = 1000000007;
        int totalSum = 0;
        for (int number : arr) {
            totalSum += number;
        }
        /*
        Here we want to find how many different pairs of subsets s1 and s2 can be formed such that
        difference between s1 and s2 is given difference d.We also know that totalSum is sum of s1 and s2.
        So, totalSum = s1 + s2; and s1 - s2 = d; where s1 > s2. So putting s1 from first equation into second gives us:
        (totalSum - s2) - s2 = d, gives totalSum - 2*s2 = d, which gives us:
        s2 = (totalSum - d) / 2
        So our target is getting changed basically for which we need to check total subsets count, so our target will be s2.
        We know that totalSum = s1+s2 that means totalSum should always be greater than given difference
        and also all numbers in array are 0 <= arr[i] and integers, so s2 is also going to be integer, so (totalSum - d)
        is also going to be even number/integer
        */
        //Checking edge cases
        if (totalSum - d < 0) return 0;
        if ((totalSum - d) % 2 == 1) return 0;
        int s2 = (totalSum - d) / 2;
        return countPartitionsBottomUp(arr, s2) % mod;
    }

    //Time Complexity: O(N*target)
    //Reason: There are two nested loops
    //Space Complexity: O(N*target)
    //Reason: We are using an external array of size ‘N*target’. Stack Space is eliminated.
    private int countPartitionsBottomUp(int[] arr, int subsetS2Target) {
        int n = arr.length;
        int mod = 1000000007;
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
                dp[index][target] = (notPickCurrentElement + pickCurrentElement) % mod;
            }
        }
        return dp[n - 1][subsetS2Target];
    }

    public int countPartitionsSpaceOptimised(int n, int d, int[] arr) {
        // Code here
        int mod = 1000000007;
        int totalSum = 0;
        for (int number : arr) {
            totalSum += number;
        }
        /*
        Here we want to find how many different pairs of subsets s1 and s2 can be formed such that
        difference between s1 and s2 is given difference d.We also know that totalSum is sum of s1 and s2.
        So, totalSum = s1 + s2; and s1 - s2 = d; where s1 > s2. So putting s1 from first equation into second gives us:
        (totalSum - s2) - s2 = d, gives totalSum - 2*s2 = d, which gives us:
        s2 = (totalSum - d) / 2
        So our target is getting changed basically for which we need to check total subsets count, so our target will be s2.
        We know that totalSum = s1+s2 that means totalSum should always be greater than given difference
        and also all numbers in array are 0 <= arr[i] and integers, so s2 is also going to be integer, so (totalSum - d)
        is also going to be even number/integer
        */
        //Checking edge cases
        if (totalSum - d < 0) return 0;
        if ((totalSum - d) % 2 == 1) return 0;

        int s2 = (totalSum - d) / 2;

        return countPartitionsSpaceOptimised(arr, s2) % mod;
    }

    //Time Complexity: O(N*target)
    //Reason: There are three nested loops
    //Space Complexity: O(target)
    //Reason: We are using an external array of size ‘target+1’ to store only one row.
    private int countPartitionsSpaceOptimised(int[] arr, int subsetS2Target) {
        int n = arr.length;
        int mod = 1000000007;
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
                currentRow[target] = (notPickCurrentElement + pickCurrentElement) % mod;
            }
            previousRow = currentRow.clone();
        }
        return previousRow[subsetS2Target]%mod;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 4};
        int n = arr.length;
        int difference = 3;
        PartitionWithGivenDifference pwgd = new PartitionWithGivenDifference();
        System.out.println(pwgd.countPartitionsTopDown(n, difference, arr));
        System.out.println(pwgd.countPartitionsBottomUp(n, difference, arr));
        System.out.println(pwgd.countPartitionsSpaceOptimised(n, difference, arr));

    }
}

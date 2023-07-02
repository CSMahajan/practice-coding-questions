package DynamicProgramming;

import java.util.Arrays;

/*
Minimum sum partition

Given an array arr of size n containing non-negative integers,the task is to divide it into two sets S1 and S2
such that the absolute difference between their sums is minimum and find the minimum difference
Example 1:
Input: N = 4, arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11
Example 2:
Input: N = 2, arr[] = {1, 4}
Output: 3
Explanation:
Subset1 = {1}, sum of Subset1 = 1
Subset2 = {4}, sum of Subset2 = 4
*/
public class PartitionMinimumSumDifference {

    //Time Complexity: O(N*totSum) +O(N) +O(N)
    //Reason: There are two nested loops that account for O(N*totSum),
    //at starting we are running a for loop to calculate totSum and at last a for loop to traverse the last row.
    //Space Complexity: O(totSum)
    //Reason: We are using an external array of size ‘totSum+1’ to store only one row.
    public int minDifference(int[] arr, int n) {
        // Your code goes here
        int totalSum = 0;
        for (int element : arr) {
            totalSum += element;
        }

        //Space optimised bottom up DP solution for finding if there exists a sum possible in an array starts

        //here our both arrays used for dp is of totalSum + 1 because possible targetSum ranges from [0,targetSum],
        //so these are targetSum + 1 different values possible our for loop runs
        boolean[] previousRow = new boolean[totalSum + 1];
        boolean[] currentRow = new boolean[totalSum + 1];
        Arrays.fill(previousRow, false);
        Arrays.fill(currentRow, false);
        previousRow[0] = true;
        if (arr[0] <= totalSum) {
            previousRow[arr[0]] = true;
        }
        for (int index = 1; index < n; index++) {
            currentRow[0] = true;
            for (int target = 1; target <= totalSum; target++) {
                boolean notPickCurrentElement = previousRow[target];
                boolean pickCurrentElement = false;
                if (arr[index] <= target) {
                    pickCurrentElement = previousRow[target - arr[index]];
                }
                currentRow[target] = notPickCurrentElement || pickCurrentElement;
            }
            previousRow = currentRow.clone();
        }
        //Space optimised bottom up DP solution for finding if there exists a sum possible in an array ends

        //As for finding minimum absolute difference between two subsets, in previousRow array we have the possible
        //answers for if it is possible to achieve given sum on selecting 0 to (n-1)th index element from array
        //So we will traverse through all possible target sums,(we can even traverse only till totalSum/2 is also possible),
        //where s1 denotes first subset sum, so other subset s2 will have the sum of (totalSum - s1)
        //we want to minimise the possible (absolute) difference
        int minimumSumDifferenceBetweenTwoSubsets = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= totalSum; s1++) {
            if (previousRow[s1]) {
                int difference = Math.abs(s1 - (totalSum - s1));
                minimumSumDifferenceBetweenTwoSubsets = Math.min(minimumSumDifferenceBetweenTwoSubsets, difference);
            }
        }
        return minimumSumDifferenceBetweenTwoSubsets;
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 11, 5};
        PartitionMinimumSumDifference pmsd = new PartitionMinimumSumDifference();
        System.out.println(pmsd.minDifference(arr, arr.length));
    }
}

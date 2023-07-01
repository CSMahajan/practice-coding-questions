package DynamicProgramming;

/*
Partition Equal Subset Sum

Given an array arr[] of size N,
check if it can be partitioned into two parts such that the sum of elements in both parts is the same.
Example 1:
Input: N = 4
arr = {1, 5, 11, 5}
Output: YES
Explanation:
The two parts are {1, 5, 5} and {11}.
Example 2:
Input: N = 3
arr = {1, 3, 5}
Output: NO
Explanation: This array can never be partitioned into two such parts.
*/
public class PartitionEqualSubsetSumTarget {

    //Total time complexity: O(N*targetSum) + O(N)
    //Reason: O(N) to find total sum of array, O(N*targetSum) for equalPartitionSpaceOptimised function
    public static int equalPartitionSpaceOptimised(int N, int[] arr) {
        // code here
        //This is an extension problem for subset sum with given target
        //Here as we have to check if exactly 2 subsets with all positive integers present have the sum as equal,
        //then that means both 2 subsets should be of equal sum i.e. half of total sum of array
        //so we can basically check if half of total sum can be made from given array, which proves we can have
        int totalSum = 0;
        for (int element : arr) {
            totalSum += element;
        }
        if (totalSum % 2 != 0) {
            return 0;
        }
        int targetSum = totalSum / 2;
        return equalPartitionSpaceOptimised(N, arr, targetSum) ? 1 : 0;
    }

    //Time Complexity: O(N*targetSum)
    //Reason: There are three nested loops
    //Space Complexity: O(targetSum)
    //Reason: We are using an external array of size ‘targetSum+1’ to store only one row.
    public static Boolean equalPartitionSpaceOptimised(int N, int[] arr, int sum) {
        // code here
        //our previousRow is of size N * (targetSum+1) because at 0, we are returning and starting to call from targetSum itself
        //to avoid arrayindexoutofbounds error
        boolean[] previousRow = new boolean[sum + 1];
        boolean[] currentRow = new boolean[sum + 1];

        //if the target is reached as 0 then we have found the subsequence/subset
        //so starting to put with previousRow[0] as true
        previousRow[0] = true;
        //till reaching of first element of array if sum is still greater or equal than first element,
        //so at 0th index for 0th element marking it as true
        if (arr[0] <= sum) {
            previousRow[arr[0]] = true;
        }
        //For both index as 0 and target as 0 we have figured out a base case, so starting loops with 1 for both
        for (int index = 1; index < N; index++) {
            for (int target = 1; target <= sum; target++) {
                boolean notPickCurrentElement = previousRow[target];
                boolean pickCurrentElement = false;
                if (arr[index] <= target) {
                    pickCurrentElement = previousRow[target - arr[index]];
                }
                currentRow[target] = notPickCurrentElement || pickCurrentElement;
            }
            previousRow = currentRow.clone();
        }
        return previousRow[sum];
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 11, 5};
        System.out.println(equalPartitionSpaceOptimised(arr.length, arr));
    }
}

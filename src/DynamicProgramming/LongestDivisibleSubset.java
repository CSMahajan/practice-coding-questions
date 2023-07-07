package DynamicProgramming;

/*
Largest Divisible Subset

Given a set of distinct positive integers nums,
return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.
Example 1:
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:
Input: nums = [1,2,4,8]
Output: [1,2,4,8]
*/
import java.util.*;
public class LongestDivisibleSubset {

    //Time Complexity: O(N*N)
    //Space Complexity: O(N)
    public static List<Integer> largestDivisibleSubsetHashing(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] hash = new int[n];
        Arrays.fill(hash, 1);
        Arrays.sort(arr);
        for (int i = 0; i <= n - 1; i++) {
            hash[i] = i; // initializing with current index
            for (int previousIndex = 0; previousIndex <= i - 1; previousIndex++) {
                if (arr[i] % arr[previousIndex] == 0 && 1 + dp[previousIndex] > dp[i]) {
                    dp[i] = 1 + dp[previousIndex];
                    hash[i] = previousIndex;
                }
            }
        }
        int maxLength = -1;
        int lastIndex = -1;
        for (int i = 0; i <= n - 1; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        ArrayList<Integer> longestIncreasingSupersequence = new ArrayList<>();
        longestIncreasingSupersequence.add(arr[lastIndex]);
        while (hash[lastIndex] != lastIndex) { // till not reach the initialization value
            lastIndex = hash[lastIndex];
            longestIncreasingSupersequence.add(arr[lastIndex]);
        }
        return longestIncreasingSupersequence;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(largestDivisibleSubsetHashing(arr));
    }
}

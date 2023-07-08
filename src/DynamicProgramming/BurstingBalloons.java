package DynamicProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Burst Balloons

You are given n balloons, indexed from 0 to n - 1.
Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
Return the maximum coins you can collect by bursting the balloons wisely.
Example 1:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:
Input: nums = [1,5]
Output: 10
*/
public class BurstingBalloons {

    public int maxCoinsTopDown(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        numsList.add(0, 1);
        numsList.add(1);
        return maxCoinsTopDown(1, n, numsList, dp);
    }

    //Time Complexity: O(N*N*N),
    //There are total N2 no. of states. And for each state, we are running a partitioning loop roughly for N times.
    //Space Complexity: O(N*N) + Auxiliary stack space of O(N),
    //N2 for the dp array we are using.
    private int maxCoinsTopDown(int i, int j, List<Integer> numsList, int[][] dp) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int maximumCoins = Integer.MIN_VALUE;
        for (int partition = i; partition <= j; partition++) {
            int coins = numsList.get(i - 1) * numsList.get(partition) * numsList.get(j + 1) +
                    maxCoinsTopDown(i, partition - 1, numsList, dp) +
                    maxCoinsTopDown(partition + 1, j, numsList, dp);
            maximumCoins = Math.max(maximumCoins, coins);
        }
        return dp[i][j] = maximumCoins;
    }

    //Time Complexity: O(N*N*N),
    //There are total N2 no. of states. And for each state, we are running a partitioning loop roughly for N times.
    //Space Complexity: O(N*N), N*N for the dp array we are using.
    public int maxCoinsBottomUp(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        numsList.add(0, 1);
        numsList.add(1);
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                int maximumCoins = Integer.MIN_VALUE;
                if (i > j) {
                    continue;
                }
                for (int partition = i; partition <= j; partition++) {
                    int coins = numsList.get(i - 1) * numsList.get(partition) * numsList.get(j + 1) +
                            dp[i][partition - 1] +
                            dp[partition + 1][j];
                    maximumCoins = Math.max(maximumCoins, coins);
                }
                dp[i][j] = maximumCoins;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        BurstingBalloons bb = new BurstingBalloons();
        int[] nums = {3, 1, 5, 8};
        System.out.println(bb.maxCoinsTopDown(nums));
        System.out.println(bb.maxCoinsBottomUp(nums));
    }
}

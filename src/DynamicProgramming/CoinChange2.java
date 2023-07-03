package DynamicProgramming;

import java.util.Arrays;

/*
Coin Change II

You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount.
If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.
Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:
Input: amount = 10, coins = [10]
Output: 1
*/
public class CoinChange2 {

    public int coinChangeTopDown(int amount, int[] coins) {
        int n = coins.length;
        long[][] dp = new long[n][amount + 1];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        long ways = coinChangeTopDown(n - 1, amount, coins, dp);
        return (int) ways;
    }

    //Time Complexity: O(N*target)
    //Reason: There are N*target states therefore at max 'N*target' new problems will be solved.
    //Space Complexity: O(N*target) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*target)).
    private long coinChangeTopDown(int index, int target, int[] arr, long[][] dp) {
        if (index == 0) {
            return target % arr[0] == 0 ? 1 : 0;
        }
        if (dp[index][target] != -1) {
            return dp[index][target];
        }

        long notPickCurrentCoin = coinChangeTopDown(index - 1, target, arr, dp);
        long pickCurrentCoin = 0;
        if (arr[index] <= target) {
            pickCurrentCoin = coinChangeTopDown(index, target - arr[index], arr, dp);
        }
        return dp[index][target] = (notPickCurrentCoin + pickCurrentCoin);
    }

    //Time Complexity: O(N*amount)
    //Reason: There are two nested loops
    //Space Complexity: O(N*amount)
    //Reason: We are using an external array of size 'N*amount'. Stack Space is eliminated.
    public int coinChangeBottomUp(int amount, int[] coins) {
        int n = coins.length;
        long[][] dp = new long[n][amount + 1];

        for (int target = 0; target <= amount; target++) {
            dp[0][target] = (target % coins[0] == 0) ? 1 : 0;
        }

        for(int index = 1;index<n;index++) {
            for(int target= 0;target<=amount;target++) {
                long notPickCurrentCoin = dp[index - 1][target];
                long pickCurrentCoin = 0;
                if (coins[index] <= target) {
                    pickCurrentCoin = dp[index][target - coins[index]];
                }
                dp[index][target] = (notPickCurrentCoin + pickCurrentCoin);
            }
        }
        return (int) dp[n-1][amount];
    }

    //Time Complexity: O(N*amount)
    //Reason: There are two nested loops.
    //Space Complexity: O(amount)
    //Reason: We are using an external array of size 'amount+1' to store two rows only.
    public int coinChangeSpaceOptimised(int amount, int[] coins) {
        int n = coins.length;
        long[] previousRow = new long[amount + 1];
        long[] currentRow = new long[amount + 1];

        for (int target = 0; target <= amount; target++) {
            previousRow[target] = (target % coins[0] == 0) ? 1 : 0;
        }

        for(int index = 1;index<n;index++) {
            for(int target= 0;target<=amount;target++) {
                long notPickCurrentCoin = previousRow[target];
                long pickCurrentCoin = 0;
                if (coins[index] <= target) {
                    pickCurrentCoin = currentRow[target - coins[index]];
                }
                currentRow[target] = (notPickCurrentCoin + pickCurrentCoin);
            }
            previousRow = currentRow.clone();
        }
        return (int) previousRow[amount];
    }

    public static void main(String[] args) {
        CoinChange2 cc = new CoinChange2();
        int amount = 5;
        int[] coins = {1, 2, 5};
        System.out.println(cc.coinChangeTopDown(amount, coins));
        System.out.println(cc.coinChangeBottomUp(amount, coins));
        System.out.println(cc.coinChangeSpaceOptimised(amount, coins));
    }
}

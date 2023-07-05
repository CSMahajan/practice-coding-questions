package DynamicProgramming;

import java.util.Arrays;

/*
Best Time to Buy and Sell Stock with Cooldown

You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).
Example 1:
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:
Input: prices = [1]
Output: 0
*/
public class BuySellStock5 {

    public int maxProfitTopDown(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maxProfitTopDown(0, 1, prices, dp);
    }

    //Time Complexity: O(N*2)
    //Reason: There are N*2 states therefore at max 'N*2' new problems will be solved,
    //and we are running a for loop for 'N' times to calculate the total sum
    //Space Complexity: O(N*2) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).
    private int maxProfitTopDown(int index, int buy, int[] prices, int[][] dp) {
        //Here buy = 1 represents buying of stock and buy = 0 represent selling of stock
        //In the top down approach we are going from 0 to n-1
        //indexing reaching n means profit should be 0
        if (index >= prices.length) {
            return 0;
        }
        if (dp[index][buy] != -1) {
            return dp[index][buy];
        }
        int profit;
        if (buy == 1) {
            //This is case for buying of stock
            //Buying means we should add the current price as negative in order to calculate profit
            //profit = selling price - buying price
            //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
            int buyAtCurrentIndex = -prices[index] + maxProfitTopDown(index + 1, 0, prices, dp);
            //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
            int notBuyAtCurrentIndex = maxProfitTopDown(index + 1, 1, prices, dp);
            profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
        } else {
            //This is case for selling of stock
            //Selling at current means moving with current price as positive and next action as buy
            //Due to cooldown of 1 day, we need to move to the next to next day instead of next consecutive day
            int sellAtCurrentIndex = prices[index] + maxProfitTopDown(index + 2, 1, prices, dp);
            //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
            int notSellAtCurrentIndex = maxProfitTopDown(index + 1, 0, prices, dp);
            profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
        }
        return dp[index][buy] = profit;
    }

    //Time Complexity: O(N*2)
    //Reason: There are two nested loops that account for O(N*2) complexity.
    //Space Complexity: O(N*2)
    //Reason: We are using an external array of size 'N*2'. Stack Space is eliminated.
    public int maxProfitBottomUp(int[] prices) {
        int n = prices.length;
        //here we are keeping dp of n+2 because we are having cooldown of one day in between
        //due to that we will be jumping to index + 2, so to avoid arrayindexoutofbounds exception/stack overflow error
        //we need to keep n+2 instead of n or n+1
        int[][] dp = new int[n + 2][2];
        int profit = 0;
        //in case of top down, index traversed from 0 to n-1 and buy traversed from 1 to 0
        //so in case of bottom up, index should traverse from n-1 to 0 and buy should traverse from 0 to 1
        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    //This is case for buying of stock
                    //Buying means we should add the current price as negative in order to calculate profit
                    //profit = selling price - buying price
                    //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
                    int buyAtCurrentIndex = -prices[index] + dp[index + 1][0];
                    //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
                    int notBuyAtCurrentIndex = dp[index + 1][1];
                    profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
                }
                if (buy == 0) {
                    //This is case for selling of stock
                    //Selling at current means moving with current price as positive and next action as buy
                    //Due to cooldown of 1 day, we need to move to the next to next day instead of next consecutive day
                    int sellAtCurrentIndex = prices[index] + dp[index + 2][1];
                    //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
                    int notSellAtCurrentIndex = dp[index + 1][0];
                    profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
                }
                dp[index][buy] = profit;
            }
        }
        return dp[0][1];
    }

    //Time Complexity: O(N*2)
    //Reason: There are two nested loops that account for O(N*2) complexity
    //Space Complexity: O(1)
    //Reason: We are using an external array of size ‘2’.
    public int maxProfitSpaceOptimised(int[] prices) {
        int n = prices.length;
        int[] previousToPreviousRow = new int[2];
        int[] previousRow = new int[2];
        int[] currentRow = new int[2];
        //we can even omit the buy loop as it only has 0 and 1 values and these will definitely be populated with values
        for (int index = n - 1; index >= 0; index--) {
            currentRow[1] = Math.max(-prices[index] + previousRow[0], previousRow[1]);
            currentRow[0] = Math.max(prices[index] + previousToPreviousRow[1], previousRow[0]);
            previousToPreviousRow = previousRow.clone();
            previousRow = currentRow.clone();
        }
        return previousRow[1];
    }

    public static void main(String[] args) {
        BuySellStock5 bss = new BuySellStock5();
        int[] prices = {1,2,3,0,2};
        System.out.println(bss.maxProfitTopDown(prices));
        System.out.println(bss.maxProfitBottomUp(prices));
        System.out.println(bss.maxProfitSpaceOptimised(prices));
    }
}

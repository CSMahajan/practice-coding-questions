package DynamicProgramming;

import java.util.Arrays;

/*
Best Time to Buy and Sell Stock with Transaction Fee

You are given an array prices where prices[i] is the price of a given stock on the ith day,
and an integer fee representing a transaction fee.
Find the maximum profit you can achieve.
You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
Note:
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
Example 1:
Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:
Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
*/
public class BuySellStock6 {

    public int maxProfitTopDown(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maxProfitTopDown(0, 1, prices, fee, dp);
    }

    //Time Complexity: O(N*2)
    //Reason: There are N*2 states therefore at max 'N*2' new problems will be solved,
    //and we are running a for loop for 'N' times to calculate the total sum
    //Space Complexity: O(N*2) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).
    private int maxProfitTopDown(int index, int buy, int[] prices, int fee, int[][] dp) {
        //Here buy = 1 represents buying of stock and buy = 0 represent selling of stock
        //In the top down approach we are going from 0 to n-1
        //indexing reaching n means profit should be 0
        if (index == prices.length) {
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
            int buyAtCurrentIndex = -prices[index] + maxProfitTopDown(index + 1, 0, prices, fee, dp);
            //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
            int notBuyAtCurrentIndex = maxProfitTopDown(index + 1, 1, prices, fee, dp);
            profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
        } else {
            //This is case for selling of stock
            //Selling at current means moving with current price as positive and next action as buy
            //Paying a transaction fee after selling the purchased stock
            //Note:We can pay the fee either at buying or at selling (but not both)
            int sellAtCurrentIndex = prices[index] - fee + maxProfitTopDown(index + 1, 1, prices, fee, dp);
            //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
            int notSellAtCurrentIndex = maxProfitTopDown(index + 1, 0, prices, fee, dp);
            profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
        }
        return dp[index][buy] = profit;
    }

    //Time Complexity: O(N*2)
    //Reason: There are two nested loops that account for O(N*2) complexity.
    //Space Complexity: O(N*2)
    //Reason: We are using an external array of size 'N*2'. Stack Space is eliminated.
    public int maxProfitBottomUp(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        /*Below condition is covered by assigning dp values
        for nth index for both buy(1 in column) and sell(0 in column) as 0
        if (index == prices.length) {
            return 0;
        }
        */
        dp[n][0] = dp[n][1] = 0;
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
                    //Paying a transaction fee after selling the purchased stock
                    //Note:We can pay the fee either at buying or at selling (but not both)
                    int sellAtCurrentIndex = prices[index] - fee + dp[index + 1][1];
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
    //Reason: We are using 4 variables.
    public int maxProfitSpaceOptimised(int[] prices, int fee) {
        int n = prices.length;
        int aheadNotBuy, aheadBuy, curBuy, curNotBuy;
        aheadNotBuy = aheadBuy = 0;
        for (int index = n - 1; index >= 0; index--) {
            curBuy = Math.max(-prices[index] + aheadNotBuy, aheadBuy);
            //Paying a transaction fee after selling the purchased stock
            //Note:We can pay the fee either at buying or at selling (but not both)
            curNotBuy = Math.max(prices[index] - fee + aheadBuy, aheadNotBuy);
            aheadBuy = curBuy;
            aheadNotBuy = curNotBuy;
        }
        return aheadBuy;
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 7, 5, 10, 3};
        int fee = 3;
        BuySellStock6 bss = new BuySellStock6();
        System.out.println(bss.maxProfitTopDown(prices, fee));
        System.out.println(bss.maxProfitBottomUp(prices, fee));
        System.out.println(bss.maxProfitSpaceOptimised(prices, fee));
    }
}

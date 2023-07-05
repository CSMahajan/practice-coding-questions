package DynamicProgramming;

import java.util.Arrays;

/*
Best Time to Buy and Sell Stock IV

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve.
You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
Example 1:
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
*/
public class BuySellStock4 {

    public int maxProfitTopDown(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k + 1];
        for (int[][] row : dp) {
            for (int[] column : row) {
                Arrays.fill(column, -1);
            }
        }
        return maxProfitTopDown(0, 1, k, prices, dp);
    }

    //Time Complexity: O(N*2*k)
    //Reason: There are N*2*k states therefore at max 'N*2*k' new problems will be solved.
    //Space Complexity: O(N*2*k) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 3D array ( O(N*2*k)).
    private int maxProfitTopDown(int index, int buy, int maxTransactionLimit, int[] prices, int[][][] dp) {
        //A transaction consists of buy first and then sell
        //At most we can do maxTransactionLimit number of transactions
        if (index == prices.length || maxTransactionLimit == 0) {
            return 0;
        }
        if (dp[index][buy][maxTransactionLimit] != -1) {
            return dp[index][buy][maxTransactionLimit];
        }
        int profit;
        if (buy == 1) {
            //This is case for buying of stock
            //Buying means we should add the current price as negative in order to calculate profit
            //profit = selling price - buying price
            //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
            int buyAtCurrentIndex = -prices[index] + maxProfitTopDown(index + 1, 0, maxTransactionLimit, prices, dp);
            //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
            int notBuyAtCurrentIndex = maxProfitTopDown(index + 1, 1, maxTransactionLimit, prices, dp);
            profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
        } else {
            //This is case for selling of stock
            //Selling at current means moving with current price as positive and next action as buy
            int sellAtCurrentIndex = prices[index] + maxProfitTopDown(index + 1, 1, maxTransactionLimit - 1, prices, dp);
            //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
            int notSellAtCurrentIndex = maxProfitTopDown(index + 1, 0, maxTransactionLimit, prices, dp);
            profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
        }
        return dp[index][buy][maxTransactionLimit] = profit;
    }

    //Time Complexity: O(N*2*k)
    //Reason: There are three nested loops that account for O(N*2*k) complexity.
    //Space Complexity: O(N*2*k)
    //Reason: We are using an external array of size 'N*2*k'. Stack Space is eliminated.
    public int maxProfitBottomUp(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];
        /*Below condition is covered by assigning dp values
        for nth index for both buy(1 in column) and sell(0 in column) as 0
        if (index == prices.length) {
            return 0;
        }
        */

        int profit = 0;
        //in case of top down, index traversed from 0 to n-1 and buy traversed from 1 to 0
        //so in case of bottom up, index should traverse from n-1 to 0 and buy should traverse from 0 to 1
        //for maxTransactionLimit = 0 is already covered because by default arrays are filled with 0
        //so maxTransactionLimit loop can start from 1 to 2
        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int maxTransactionLimit = 1; maxTransactionLimit <= k; maxTransactionLimit++) {
                    if (buy == 1) {
                        //This is case for buying of stock
                        //Buying means we should add the current price as negative in order to calculate profit
                        //profit = selling price - buying price
                        //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
                        int buyAtCurrentIndex = -prices[index] + dp[index + 1][0][maxTransactionLimit];
                        //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
                        int notBuyAtCurrentIndex = dp[index + 1][1][maxTransactionLimit];
                        profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
                    }
                    if (buy == 0) {
                        //This is case for selling of stock
                        //Selling at current means moving with current price as positive and next action as buy
                        int sellAtCurrentIndex = prices[index] + dp[index + 1][1][maxTransactionLimit - 1];
                        //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
                        int notSellAtCurrentIndex = dp[index + 1][0][maxTransactionLimit];
                        profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
                    }
                    dp[index][buy][maxTransactionLimit] = profit;
                }
            }
        }
        return dp[0][1][k];
    }

    //Time Complexity: O(N*2*k)
    //Reason: There are three nested loops that account for O(N*2*k) complexity
    //Space Complexity: O(1)
    //Reason: We are using two external arrays of size '2*k'.
    public int maxProfitSpaceOptimised(int k, int[] prices) {
        int n = prices.length;
        int[][] previousRow = new int[2][k + 1];
        int[][] currentRow = new int[2][k + 1];
        /*Below condition is covered by assigning dp values
        for nth index for both buy(1 in column) and sell(0 in column) as 0
        if (index == prices.length) {
            return 0;
        }
        */
        int profit = 0;
        //in case of top down, index traversed from 0 to n-1 and buy traversed from 1 to 0
        //so in case of bottom up, index should traverse from n-1 to 0 and buy should traverse from 0 to 1
        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int maxTransactionLimit = 1; maxTransactionLimit <= k; maxTransactionLimit++) {
                    if (buy == 1) {
                        //This is case for buying of stock
                        //Buying means we should add the current price as negative in order to calculate profit
                        //profit = selling price - buying price
                        //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
                        int buyAtCurrentIndex = -prices[index] + previousRow[0][maxTransactionLimit];
                        //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
                        int notBuyAtCurrentIndex = previousRow[1][maxTransactionLimit];
                        profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
                    }
                    if (buy == 0) {
                        //This is case for selling of stock
                        //Selling at current means moving with current price as positive and next action as buy
                        int sellAtCurrentIndex = prices[index] + previousRow[1][maxTransactionLimit - 1];
                        //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
                        int notSellAtCurrentIndex = previousRow[0][maxTransactionLimit];
                        profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
                    }
                    currentRow[buy][maxTransactionLimit] = profit;
                }
            }
            previousRow = currentRow.clone();
        }
        return previousRow[1][k];
    }

    public int maxProfitTopDownTransactionNumber(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2 * k];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maxProfitTopDownTransactionNumber(0, 0, prices, k, dp);
    }

    //Time Complexity: O(N*4)
    //Reason: There are N*4 states therefore at max 'N*4' new problems will be solved.
    //Space Complexity: O(N*4) + O(N)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*4)).
    private int maxProfitTopDownTransactionNumber(int index, int transactionNumber, int[] prices, int k, int[][] dp) {
        //A transaction consists of buy or sell
        //At most we can do maxTransactionLimit number of transactions
        if (index == prices.length || transactionNumber == 2 * k) {
            return 0;
        }
        if (dp[index][transactionNumber] != -1) {
            return dp[index][transactionNumber];
        }
        int profit;
        if (transactionNumber % 2 == 0) {
            //This is case for buying of stock
            //Buying means we should add the current price as negative in order to calculate profit
            //profit = selling price - buying price
            //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
            int buyAtCurrentIndex = -prices[index] + maxProfitTopDownTransactionNumber(index + 1, transactionNumber + 1, prices, k, dp);
            //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
            int notBuyAtCurrentIndex = maxProfitTopDownTransactionNumber(index + 1, transactionNumber, prices, k, dp);
            profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
        } else {
            //This is case for selling of stock
            //Selling at current means moving with current price as positive and next action as buy
            int sellAtCurrentIndex = prices[index] + maxProfitTopDownTransactionNumber(index + 1, transactionNumber + 1, prices, k, dp);
            //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
            int notSellAtCurrentIndex = maxProfitTopDownTransactionNumber(index + 1, transactionNumber, prices, k, dp);
            profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
        }
        return dp[index][transactionNumber] = profit;
    }

    //Time Complexity: O(N*4)
    //Reason: There are three nested loops that account for O(N*4) complexity.
    //Space Complexity: O(N*4)
    //Reason: We are using an external array of size 'N*4'. Stack Space is eliminated.
    public int maxProfitBottomUpTransactionNumber(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2 * k + 1];
        /*Below condition is covered by assigning dp values
        for nth index for both buy(1 in column) and sell(0 in column) as 0
        if (index == prices.length) {
            return 0;
        }
        */

        int profit = 0;
        //in case of top down, index traversed from 0 to n-1 and buy traversed from 1 to 0
        //so in case of bottom up, index should traverse from n-1 to 0 and buy should traverse from 0 to 1
        //for maxTransactionLimit = 0 is already covered because by default arrays are filled with 0
        //so maxTransactionLimit loop can start from 1 to 2
        for (int index = n - 1; index >= 0; index--) {
            for (int transactionNumber = 2 * k - 1; transactionNumber >= 0; transactionNumber--) {
                if (transactionNumber % 2 == 0) {
                    //This is case for buying of stock
                    //Buying means we should add the current price as negative in order to calculate profit
                    //profit = selling price - buying price
                    //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
                    int buyAtCurrentIndex = -prices[index] + dp[index + 1][transactionNumber + 1];
                    //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
                    int notBuyAtCurrentIndex = dp[index + 1][transactionNumber];
                    profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
                } else {
                    //This is case for selling of stock
                    //Selling at current means moving with current price as positive and next action as buy
                    int sellAtCurrentIndex = prices[index] + dp[index + 1][transactionNumber + 1];
                    //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
                    int notSellAtCurrentIndex = dp[index + 1][transactionNumber];
                    profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
                }
                dp[index][transactionNumber] = profit;
            }
        }
        return dp[0][0];
    }

    //Time Complexity: O(N*4)
    //Reason: There are three nested loops that account for O(N*4) complexity
    //Space Complexity: O(1)
    //Reason: We are using two external arrays of size '4'.
    public int maxProfitSpaceOptimisedTransactionNumber(int k, int[] prices) {
        int n = prices.length;
        //let a = total buy and sell combined transactions, here given as 2
        //space required for dp array = 2a+1
        int[] previousRow = new int[2 * k + 1];
        int[] currentRow = new int[2 * k + 1];
        /*Below condition is covered by assigning dp values
        for nth index for both buy(1 in column) and sell(0 in column) as 0
        if (index == prices.length) {
            return 0;
        }
        */
        int profit = 0;
        //in case of top down, index traversed from 0 to n-1 and buy traversed from 1 to 0
        //so in case of bottom up, index should traverse from n-1 to 0 and buy should traverse from 0 to 1
        for (int index = n - 1; index >= 0; index--) {
            for (int transactionNumber = 2 * k - 1; transactionNumber >= 0; transactionNumber--) {
                if (transactionNumber % 2 == 0) {
                    //This is case for buying of stock
                    //Buying means we should add the current price as negative in order to calculate profit
                    //profit = selling price - buying price
                    //As we buy the stock at current index, we should move to next index with sell action which is indicated by 0
                    int buyAtCurrentIndex = -prices[index] + previousRow[transactionNumber + 1];
                    //first stock needs to bought then only it can be sold, so if we didn't buy, the buying option still remains
                    int notBuyAtCurrentIndex = previousRow[transactionNumber];
                    profit = Math.max(buyAtCurrentIndex, notBuyAtCurrentIndex);
                } else {
                    //This is case for selling of stock
                    //Selling at current means moving with current price as positive and next action as buy
                    int sellAtCurrentIndex = prices[index] + previousRow[transactionNumber + 1];
                    //the bought stock needs to be sold first, so if we need to sell first in order to buy and sell later
                    int notSellAtCurrentIndex = previousRow[transactionNumber];
                    profit = Math.max(sellAtCurrentIndex, notSellAtCurrentIndex);
                }
                currentRow[transactionNumber] = profit;
            }
            previousRow = currentRow.clone();
        }
        return previousRow[0];
    }

    public static void main(String[] args) {
        int[] prices = {20, 580, 420, 900};
        int k = 3;
        BuySellStock4 bss = new BuySellStock4();
        System.out.println(bss.maxProfitTopDown(k, prices));
        System.out.println(bss.maxProfitBottomUp(k, prices));
        System.out.println(bss.maxProfitSpaceOptimised(k, prices));
        System.out.println(bss.maxProfitTopDownTransactionNumber(k, prices));
        System.out.println(bss.maxProfitBottomUpTransactionNumber(k, prices));
        System.out.println(bss.maxProfitSpaceOptimisedTransactionNumber(k, prices));
    }
}

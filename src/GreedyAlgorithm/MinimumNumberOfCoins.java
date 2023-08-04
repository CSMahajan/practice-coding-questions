package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.List;

/*
Minimum number of Coins

Given an infinite supply of each denomination of Indian currency { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 } and
a target value N.
Find the minimum number of coins and/or notes needed to make the change for Rs N.
You must return the list containing the value of coins required.
Example 1:
Input: N = 43
Output: 20 20 2 1
Explanation: Minimum number of coins and notes needed to make 43.
Example 2:
Input: N = 1000
Output: 500 500
Explanation: Minimum possible notes is 2 notes of 500.
*/
public class MinimumNumberOfCoins {

    //Time Complexity:O(N)
    //Space Complexity:O(1)
    public static List<Integer> minPartition(int N) {
        // code here
        List<Integer> coins = new ArrayList<>();
        int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
        int noOfDenominations = denominations.length;
        for (int i = noOfDenominations - 1; i >= 0; i--) {
            while(N>=denominations[i]) {
                N-=denominations[i];
                coins.add(denominations[i]);
            }
        }
        return coins;
    }

    public static void main(String[] args) {
        int N = 43;
        System.out.println(minPartition(N));
    }
}

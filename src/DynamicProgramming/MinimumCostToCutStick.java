package DynamicProgramming;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
Minimum Cost to Cut a Stick

Given a wooden stick of length n units. The stick is labelled from 0 to n.
For example, a stick of length 6 is labelled as follows:
Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
You should perform the cuts in order, you can change the order of the cuts as you wish.
The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
When you cut a stick, it will be split into two smaller sticks
(i.e. the sum of their lengths is the length of the stick before the cut).
Please refer to the first example for a better explanation.
Return the minimum total cost of the cuts.

Example 1:
Input: n = 7, cuts = [1,3,4,5]
Output: 16
Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:
The first cut is done to a rod of length 7 so the cost is 7.
The second cut is done to a rod of length 6 (i.e. the second part of the first cut),
the third is done to a rod of length 4 and the last cut is to a rod of length 3.
The total cost is 7 + 6 + 4 + 3 = 20.
Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16
(as shown in the example photo 7 + 4 + 3 + 2 = 16).

Example 2:
Input: n = 9, cuts = [5,6,1,4,2]
Output: 22
Explanation: If you try the given cuts ordering the cost will be 25.
There are much ordering with total cost <= 25,
for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.
*/
public class MinimumCostToCutStick {

    public int minCostTopDown(int n, int[] cuts) {
        int c = cuts.length;
        int[][] dp = new int[c + 2][c + 2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        List<Integer> cutsList = Arrays.stream(cuts).boxed().collect(Collectors.toList());
        cutsList.add(0, 0);
        cutsList.add(n);
        Collections.sort(cutsList);
        return minCostTopDown(1, c, cutsList, dp);
    }

    //Time Complexity: O(N*N*N)
    //Reason: There are 2 variables i and j, therefore, N*N states and we explicitly run a loop inside the function
    //which will run for N times, therefore at max ‘N*N*N’ new problems will be solved.
    //Space Complexity: O(N*N) + O(N)
    //Reason: We are using an auxiliary recursion stack space(O(N))and a 2D array ( O(N*N)).
    private int minCostTopDown(int i, int j, List<Integer> cutsList, int[][] dp) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int minimumCost = Integer.MAX_VALUE;
        for (int partition = i; partition <= j; partition++) {
            int cost = cutsList.get(j + 1) - cutsList.get(i - 1) +
                    minCostTopDown(i, partition - 1, cutsList, dp) +
                    minCostTopDown(partition + 1, j, cutsList, dp);
            minimumCost = Math.min(minimumCost, cost);
        }
        return dp[i][j] = minimumCost;
    }

    //Time Complexity: O(N*N*N)
    //Reason: There are 2 variables i and j, therefore, N*N states and we explicitly run a loop inside the function which will run for N times,
    //therefore at max ‘N*N*N’ new problems will be solved.
    //Space Complexity: O(N*N)
    //Reason: We are using a 2D array ( O(N*N)).
    public int minCostBottomUp(int n, int[] cuts) {
        int c = cuts.length;
        int[][] dp = new int[c + 2][c + 2];
        List<Integer> cutsList = Arrays.stream(cuts).boxed().collect(Collectors.toList());
        cutsList.add(0, 0);
        cutsList.add(n);
        Collections.sort(cutsList);
        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if (i > j) continue;
                int mini = Integer.MAX_VALUE;
                for (int ind = i; ind <= j; ind++) {
                    int ans = cutsList.get(j + 1) - cutsList.get(i - 1) + dp[i][ind - 1] + dp[ind + 1][j];
                    mini = Math.min(mini, ans);
                }
                dp[i][j] = mini;
            }
        }
        return dp[1][c];
    }

    public static void main(String[] args) {
        MinimumCostToCutStick mctcs = new MinimumCostToCutStick();
        int n = 7;
        int[] cuts = {1, 3, 4, 5};
        System.out.println(mctcs.minCostTopDown(n, cuts));
        System.out.println(mctcs.minCostBottomUp(n, cuts));
    }
}

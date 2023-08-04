package DynamicProgramming;

/*
Minimal Cost

There are n stones and an array of heights and Geek is standing at stone 1 and can jump to one of the following:
Stone i+1, i+2, ... i+k stone and cost will be [hi-hj] is incurred, where j is the stone to land on.
Find the minimum possible total cost incurred before the Geek reaches Stone N.
Example 1:
Input:
n = 5, k = 3
heights = {10, 30, 40, 50, 20}
Output:30
Explanation:
Geek will follow the path 1->2->5, the total cost
would be | 10-30| + |30-20| = 30, which is minimum
Example 2:
Input:
n = 3, k = 1
heights = {10,20,10}
Output:20
Explanation:
Geek will follow the path 1->2->3, the total cost
would be |10 - 20| + |20 - 10| = 20.
*/

import java.util.Arrays;

public class FrogKJump {

    public int minimizeCostTopDown(int[] arr, int N, int K) {
        
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        return minimizeCostTopDown(arr, dp, N - 1, K);
    }

    //TC:O(N*K), for every index from 0 to N-1, we are running for loop K times
    //SC:O(N) + O(N), for recursion stack space and dp array
    private int minimizeCostTopDown(int[] arr, int[] dp, int index, int k) {
        if (index == 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int minimumSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (index - j >= 0) {
                int jumpCost = minimizeCostTopDown(arr, dp, index - j, k) + Math.abs(arr[index] - arr[index - j]);
                minimumSteps = Math.min(minimumSteps, jumpCost);
            }
        }
        return dp[index] = minimumSteps;
    }

    public int minimizeCostBottomUp(int[] arr, int N, int K) {
        
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        return minimizeCostBottomUp(arr, dp, N, K);
    }


    //TC:O(N*K), for i, outer loop from 0 to N-1(i.e.index - 1), we are running for loop K times
    //SC:O(N), for dp array
    public int minimizeCostBottomUp(int[] arr, int[] dp, int index, int k) {
        dp[0] = 0;
        for (int i = 1; i < index; i++) {
            int minimumSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jumpCost = dp[i - j] + Math.abs(arr[i] - arr[i - j]);
                    minimumSteps = Math.min(minimumSteps, jumpCost);
                }
            }
            dp[i] = minimumSteps;
        }
        return dp[index - 1];
    }

    public static void main(String[] args) {
        FrogKJump fj = new FrogKJump();
        int[] height = {10, 30, 40, 50, 20};
        int k = 3;
        System.out.println(fj.minimizeCostTopDown(height, height.length, k));
        System.out.println(fj.minimizeCostBottomUp(height, height.length, k));

        /*
        Here space optimised approach is not good because its Space Complexity reaches O(K) is good, but when N=K,
        then SC becomes O(N) which is same as bottom up approach
        */
        //System.out.println(fj.minimumEnergySpaceOptimised(height, height.length));
    }
}

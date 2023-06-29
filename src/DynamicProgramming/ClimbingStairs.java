package DynamicProgramming;

import java.util.Arrays;

/*
Count ways to reach the nth stair

GeeksForGeeks
There are n stairs, a person standing at the bottom wants to reach the top.
The person can climb either 1 stair or 2 stairs at a time.
Count the number of ways, the person can reach the top (order does matter).
Example 1:
Input:
n = 4
Output: 5
Explanation:
You can reach 4th stair in 5 ways.
Way 1: Climb 2 stairs at a time.
Way 2: Climb 1 stair at a time.
Way 3: Climb 2 stairs, then 1 stair and then 1 stair.
Way 4: Climb 1 stair, then 2 stairs then 1 stair.
Way 5: Climb 1 stair, then 1 stair and then 2 stairs.
Example 2:
Input:
n = 10
Output: 89
Explanation:
There are 89 ways to reach the 10th stair.

LeetCode
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/
public class ClimbingStairs {

    final int mod = 1000000007;

    int solve(int n, int[] dp) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (dp[n] != -1) {
            return dp[n] % mod;
        }
        int count = 0;

        // One Step
        count += solve(n - 1, dp) % mod;

        // Two Step
        count += solve(n - 2, dp) % mod;

        return dp[n] = count % mod;

    }

    int topDown(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }

    int countWays(int n) {

        // Top-Down Approach (Approach 1)
        // return topDown(n);


        // Bootom-Up Approach (Approach 2)
        // if (n < 2) {
        //     return n;
        // }

        // int[] dp = new int[n + 1];
        // dp[1] = 1;
        // dp[2] = 2;

        // for (int i = 3; i <= n; i++) {
        //     dp[i] = (dp[i - 1] % mod) + (dp[i - 2] % mod) % mod;
        // }

        // return dp[n] % mod;


        // Space Optimized (Approach 3 / Best Approach)
        if (n < 2) {
            return n;
        }

        int pre1 = 1, pre2 = 2;

        for (int i = 3; i <= n; i++) {
            int curr = ((pre1 % mod) + (pre2 % mod)) % mod;
            pre1 = pre2;
            pre2 = curr;
        }

        return pre2 % mod;
    }

    public int climbStairs(int n) {
        // base cases
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int oneStepBefore = 2;
        int twoStepsBefore = 1;
        int allWays = 0;

        for(int i=2; i<n; i++){
            allWays = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;
        }
        return allWays;
    }

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        System.out.println(cs.countWays(5));
        System.out.println(cs.climbStairs(5));
    }
}

package DynamicProgramming;
/*
Fibonacci series

Find n-th fibonacci number using Dynamic Programming (DP) using top-down(memoization) and bottom-up(tabulation) approach
*/

import java.util.Arrays;

public class Fibonacci {

    public static long mod = 1000000007;

    public static long topDown(int n) {
        // code here
        if (n == 1 || n == 0) {
            return n % mod;
        }
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        solveTD(n, dp);
        return dp[n] % mod;
    }

    //TC:O(N)
    //SC:O(1)
    //Space Optimised
    public static long bottomUp(int n) {
        // code here
        long prev2 = 0;
        long prev = 1;
        for (int i = 2; i <= n; i++) {
            long curr = prev % mod + prev2 % mod;
            prev2 = prev;
            prev = curr;
        }
        return prev % mod;
    }

    //TC:O(N)
    //SC:O(N)
    //Space used for DP array
    public static long bottomUpArray(int n) {
        // code here
        long[] dp = new long[n + 1];

        dp[0] = 0;
        if (n == 0) return 0;

        dp[1] = 1;
        if (n == 1) return 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }
        return dp[n];
    }

    public static long solveTD(int n, long[] dp) {
        if (n == 0 || n == 1) {
            return n % mod;
        }
        if (dp[n] != -1) {
            return dp[n] % mod;
        }
        return dp[n] = solveTD(n - 2, dp) % mod + solveTD(n - 1, dp) % mod;
    }

    public static void main(String[] args) {
        System.out.println(topDown(5));
        System.out.println(bottomUp(6));
        System.out.println(bottomUpArray(7));
    }
}

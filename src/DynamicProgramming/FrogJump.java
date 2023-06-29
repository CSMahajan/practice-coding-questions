package DynamicProgramming;

import java.util.Arrays;

/*
Geek Jump

Geek wants to climb from the 0th stair to the (n-1)th stair. At a time the Geek can climb either one or two steps.
A height[N] array is also given. Whenever the geek jumps from stair i to stair j,
the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute difference.
Return the minimum energy that can be used by the Geek to jump from stair 0 to stair N-1.
Example:
Input:
n = 4
height = {10 20 30 10}
Output:
20
Explanation:
Geek jump from 1st to 2nd stair(|20-10| = 10 energy lost).
Then a jump from the 2nd to the last stair(|10-20| = 10 energy lost).
so, total energy lost is 20 which is the minimum.
*/
public class FrogJump {

    //TC:O(N)
    //SC:O(N) + O(N), first for recursion stack space and another for dp array
    //Top-Down Memoization Approach
    public int minimumEnergyTopDown(int[] arr, int N) {
        //code here
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        return minimumEnergy(arr, N - 1, dp);
    }

    private int minimumEnergy(int[] arr, int index, int[] dp) {
        if (index == 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int oneStepJump = minimumEnergy(arr, index - 1, dp) + Math.abs(arr[index] - arr[index - 1]);
        int twoStepJump = Integer.MAX_VALUE;
        if (index > 1) {
            twoStepJump = minimumEnergy(arr, index - 2, dp) + Math.abs(arr[index] - arr[index - 2]);
        }
        return dp[index] = Math.min(oneStepJump, twoStepJump);
    }

    //TC:O(N)
    //SC:O(N),space for dp array
    //Bottom-Up Tabulation Approach
    public int minimumEnergyBottomUp(int[] arr, int n) {
        //for converting it to 0 to n - 1 based indexing
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int oneStepJump = Math.abs(arr[i] - arr[i - 1]) + dp[i - 1];
            int twoStepJump = Integer.MAX_VALUE;
            if (i > 1) {
                twoStepJump = Math.abs(arr[i] - arr[i - 2]) + dp[i - 2];
            }
            dp[i] = Math.min(oneStepJump, twoStepJump);
        }
        return dp[n - 1];
    }


    //TC:O(N)
    //SC:O(1)
    //Bottom-Up Space Optimisation Approach
    public int minimumEnergySpaceOptimised(int[] arr, int n) {
        int previous1 = 0, previous2 = 0;
        for (int i = 1; i < n; i++) {
            int oneStepJump = Math.abs(arr[i] - arr[i - 1]) + previous1;
            int twoStepJump = Integer.MAX_VALUE;
            if (i > 1) {
                twoStepJump = Math.abs(arr[i] - arr[i - 2]) + previous2;
            }
            int currentEnergy = Math.min(oneStepJump, twoStepJump);
            previous2 = previous1;
            previous1 = currentEnergy;
        }
        return previous1;
    }

    public static void main(String[] args) {
        FrogJump fj = new FrogJump();
        int[] height = {10, 20, 30, 10};
        System.out.println(fj.minimumEnergyTopDown(height, height.length));
        System.out.println(fj.minimumEnergyBottomUp(height, height.length));
        System.out.println(fj.minimumEnergySpaceOptimised(height, height.length));
    }
}

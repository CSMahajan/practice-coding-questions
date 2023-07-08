package DynamicProgramming;

import java.util.Arrays;

/*
Boolean Evaluation

You are given an expression 'EXP' in the form of a string where operands will be: (TRUE and FALSE)
and operators will be: (AND,OR,XOR).Now you have to find the number of ways we can parenthesize the
expression such that it will evaluate to TRUE.
Return the number in modulo 10^9+7
'T' will represent operand TRUE
'F' will represent operand FALSE
'|' will represent operator OR
'&' will represent operator AND
'^' will represent operator XOR
Example 1:
Input:F|T^F
Output:2
Explanation:There are total 2  ways to parenthesize this expression:
(1) (F|T)^(F) = T
(2) (F)|(T^F) = T
Both the ways will result in True, so we will return 2.
*/
public class BooleanEvaluation {

    public static int evaluateExpTopDown(String exp) {
        // Write your code here.
        int mod = (int) (Math.pow(10, 9) + 7);
        int n = exp.length();
        int[][][] dp = new int[n][n][2];
        //Here we require 3-D DP to store i,j,isTrue as states
        for (int[][] row : dp) {
            for (int[] column : row) {
                Arrays.fill(column, -1);
            }
        }
        //isTrue = 1 represents true and isTrue = 0 represents false
        return evaluateExpTopDown(0, n - 1, 1, exp, dp) % mod;
    }

    //Time Complexity: O(N*N*2 * N) ~ O(N3) There are a total of 2*N2 no. of states.
    //And for each state, we are running a partitioning loop roughly for N times.
    //Space Complexity: O(N*N*2) + Auxiliary stack space of O(N), N*N*2 for the dp array we are using.
    private static int evaluateExpTopDown(int i, int j, int isTrue, String exp, int[][][] dp) {
        int mod = (int) (Math.pow(10, 9) + 7);
        if (i > j) {
            return 0;
        }
        if (i == j) {
            //i is same as j means i and j are both pointing to the same operand character
            if (isTrue == 1) {
                //so that character if it is 'T', then there is only one way for isTrue=1
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                //otherwise if it is 'F', then there is also only one way for isTrue=0
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }
        if (dp[i][j][isTrue] != -1) {
            return dp[i][j][isTrue];
        }
        int noOfWays = 0;
        //Every alternate character in the given expression is operator
        //and it always starts from (i+1)th index till (j-1)th
        for (int partition = i + 1; partition <= j - 1; partition += 2) {
            //We are creating 2 partitions and for each left and right side partitions, we will count the number of ways,
            //the particular left or right side partition will give us definite true or definite false
            //leftTrue indicates the number of ways left side partition evaluates to true
            //leftFalse indicates the number of ways left side partition evaluates to false
            //rightTrue indicates the number of ways right side partition evaluates to true
            //rightFalse indicates the number of ways right side partition evaluates to false
            int leftTrue = evaluateExpTopDown(i, partition - 1, 1, exp, dp);
            int leftFalse = evaluateExpTopDown(i, partition - 1, 0, exp, dp);
            int rightTrue = evaluateExpTopDown(partition + 1, j, 1, exp, dp);
            int rightFalse = evaluateExpTopDown(partition + 1, j, 0, exp, dp);
            if (exp.charAt(partition) == '&') {
                if (isTrue == 1) {
                    //T&T=T
                    noOfWays = (noOfWays + (leftTrue * rightTrue) % mod) % mod;
                } else {
                    //T&F=F,F&T=F,F&F=F
                    noOfWays = (noOfWays + (leftTrue * rightFalse) % mod + (rightTrue * leftFalse) % mod + (leftFalse * rightFalse) % mod) % mod;
                }
            } else if (exp.charAt(partition) == '|') {
                if (isTrue == 1) {
                    //T|T=T,T|F=T,F|T=T
                    noOfWays = (noOfWays + (leftTrue * rightTrue) % mod + (leftTrue * rightFalse) % mod + (rightTrue * leftFalse) % mod) % mod;
                } else {
                    //F|F=F
                    noOfWays = (noOfWays + (leftFalse * rightFalse) % mod) % mod;
                }
            } else if (exp.charAt(partition) == '^') {
                if (isTrue == 1) {
                    //T^F=T,F^T=T
                    noOfWays = (noOfWays + (leftTrue * rightFalse) % mod + (rightTrue * leftFalse) % mod) % mod;
                } else {
                    //T^T=F,F^F=F
                    noOfWays = (noOfWays + (leftTrue * rightTrue) % mod + (leftFalse * rightFalse) % mod) % mod;
                }
            }
        }
        return dp[i][j][isTrue] = noOfWays;
    }

    //Time Complexity: O(N*N*2 * N) ~ O(N3) There are a total of N*N*2 no. of states.
    //And for each state, we are running a partitioning loop roughly for N times.
    //Space Complexity: O(N*N*2), N*N*2 for the dp array we are using.
    public static int evaluateExpBottomUp(String exp) {
        // Write your code here.
        int mod = (int) (Math.pow(10, 9) + 7);
        int n = exp.length();
        int[][][] dp = new int[n][n][2];
        //Here we require 3-D DP to store i,j,isTrue as states
        //isTrue = 1 represents true and isTrue = 0 represents false
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j][0] = (exp.charAt(i) == 'F' ? 1 : 0);
                    dp[i][j][1] = (exp.charAt(i) == 'T' ? 1 : 0);
                } else {
                    for (int partition = i + 1; partition <= j - 1; partition += 2) {
                        int leftTrue = dp[i][partition - 1][1] % mod;
                        int rightTrue = dp[partition + 1][j][1] % mod;
                        int leftFalse = dp[i][partition - 1][0] % mod;
                        int rightFalse = dp[partition + 1][j][0] % mod;
                        if (exp.charAt(partition) == '&') {
                            dp[i][j][0] = (dp[i][j][0] + (leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse)) % mod;
                            dp[i][j][1] = (dp[i][j][1] + (leftTrue * rightTrue)) % mod;
                        } else if (exp.charAt(partition) == '|') {
                            dp[i][j][0] = (dp[i][j][0] + (leftFalse * rightFalse)) % mod;
                            dp[i][j][1] = (dp[i][j][1] + (leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue)) % mod;
                        } else if (exp.charAt(partition) == '^') {
                            dp[i][j][0] = (dp[i][j][0] + (leftTrue * rightTrue + leftFalse * rightFalse)) % mod;
                            dp[i][j][1] = (dp[i][j][1] + (leftTrue * rightFalse + leftFalse * rightTrue)) % mod;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1][1];
    }

    public static void main(String[] args) {
        String expression = "F|T^F";
        System.out.println(evaluateExpTopDown(expression));
        System.out.println(evaluateExpBottomUp(expression));
    }
}

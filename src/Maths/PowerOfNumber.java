package Maths;

/*
Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:
Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
*/
public class PowerOfNumber {

    //TC:O(log2 n)
    //SC:O(1)
    public double myPow(double x, int n) {
        double result = 1;
        //storing n into m as we are reducing n and we require it for sign
        int m = n;
        //if power of number is negative then we will make it as positive
        n = Math.abs(n);
        while (n > 0) {
            //below statement can also be written as if(n & 1)
            //which checks odd number and is faster due to bitwise operations
            if (n % 2 == 1) {
                result = result * x;
                n = n - 1;
            } else {
                //for even number getting its square and halving its power
                x = x * x;
                n = n / 2;
            }
        }
        //if the power of number is negative then result will be in its reciprocal
        if (m < 0) {
            result = 1.0 / result;
        }
        return result;
    }

    //TC:O(log2 n)
    //SC:O(1)
    //below solution works on leetcode
    public double myPowWorkingLeetCode(double x, int n) {
        //if power of number is negative, then adjust power and number itself
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double pow = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                pow *= x;
            }
            x *= x;
            n >>>= 1;//n=n/2 is done fast with unsigned right shift with overflow consideration as well
        }
        return pow;
    }

    public static void main(String[] args) {
        PowerOfNumber pn = new PowerOfNumber();
        double x = 2;
        int n = -2;
        System.out.println(pn.myPow(x, n));
        System.out.println(pn.myPowWorkingLeetCode(x, n));
    }
}

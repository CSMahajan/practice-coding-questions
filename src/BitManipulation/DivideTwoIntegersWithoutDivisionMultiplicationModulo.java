package BitManipulation;

/*
Divide Two Integers

Given two integers dividend and divisor,
divide two integers without using multiplication, division, and mod operator.
The integer division should truncate toward zero, which means losing its fractional part.
For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
Return the quotient after dividing dividend by divisor.
Note: Assume we are dealing with an environment that could only store integers
within the 32-bit signed integer range: [−231, 231 − 1].
For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1,
and if the quotient is strictly less than -231, then return -231.
Example 1:
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
Example 2:
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.
*/
public class DivideTwoIntegersWithoutDivisionMultiplicationModulo {

    //TC:O(log2 N)^2
    //SC:O(1)
    public int divide(int dividend, int divisor) {
        if (dividend == divisor) {
            return 1;
        }
        boolean sign = true;
        if((dividend >= 0 && divisor < 0) || (dividend <= 0 && divisor > 0)){
            sign = false;
        }
        long n = Math.abs(dividend);
        long d = Math.abs(divisor);
        int quotient = 0;
        while (n >= d) {
            int count = 0;
            while (n >= (d << (count + 1))) {
                count++;
            }
            quotient += (1 << count);
            n -= (d << count);
        }
        if (quotient == (1 << 31) && !sign) {
            return Integer.MIN_VALUE;
        }
        if (quotient == (1 << 31) && sign) {
            return Integer.MAX_VALUE;
        }
        return sign ? quotient : -quotient;
    }

    public static void main(String[] args) {
        DivideTwoIntegersWithoutDivisionMultiplicationModulo dwi = new DivideTwoIntegersWithoutDivisionMultiplicationModulo();
        int dividend = 10, divisor = 3;
        System.out.println(dwi.divide(dividend, divisor));
    }
}

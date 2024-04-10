package BitManipulation;

/*
Check K-th bit set

Given a number n, check if its k-th bit is set or not.
A bit is said to be set if it has 1 at given k-th bit when number is represented in binary format
*/

public class CheckKthBitSetUsingLeftShiftRightShiftOperator {

    public boolean isKthBitSetUsingLeftShiftOperator(int n, int k) {
        return (n & (1 << k)) != 0;
    }

    public boolean isKthBitSetUsingRightShiftOperator(int n, int k) {
        return (1 & (n >> k)) != 0;
    }

    public static void main(String[] args) {
        CheckKthBitSetUsingLeftShiftRightShiftOperator ckbs = new CheckKthBitSetUsingLeftShiftRightShiftOperator();
        System.out.println("Check using left shift operator");
        System.out.println(ckbs.isKthBitSetUsingLeftShiftOperator(14, 2));
        System.out.println("Check using right shift operator");
        System.out.println(ckbs.isKthBitSetUsingRightShiftOperator(14, 2));
    }
}

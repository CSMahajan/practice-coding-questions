package BitManipulation;

/*
Check K-th bit set

Given a number n, check if its k-th bit is set or not.
A bit is said to be set if it has 1 at given k-th bit when number is represented in binary format
*/

public class CheckKthBitSet {

    public boolean isKthBitSet(int n, int k) {
        return (n & (1<<k)) != 0;
    }

    public static void main(String[] args) {
        CheckKthBitSet ckbs = new CheckKthBitSet();
        System.out.println(ckbs.isKthBitSet(14, 2));
    }
}

package BitManipulation;

/*
Unset K-th bit set

Given a number n, unset its k-th bit.
A bit is said to be unset if it has 0 at given k-th bit when number is represented in binary format
*/
public class UnsetKthBitUsingNOT {

    public void unsetKthBit(int n, int k) {
        System.out.println("n: " + n + " at k: " + k);
        n = n & (~(1 << k));
        System.out.println("n: " + n + " at k: " + k);
    }

    public static void main(String[] args) {
        UnsetKthBitUsingNOT skb = new UnsetKthBitUsingNOT();
        System.out.println("Unset kth bit");
        skb.unsetKthBit(14, 1);
    }
}

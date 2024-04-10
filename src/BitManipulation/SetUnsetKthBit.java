package BitManipulation;

/*
Set and Unset K-th bit set

Given a number n, set and unset its k-th bit.
A bit is said to be set if it has 1 at given k-th bit when number is represented in binary format
*/
public class SetUnsetKthBit {

    public void setKthBit(int n, int k) {
        System.out.println("n: " + n + " at k: " + k);
        n = n | (1 << k);
        System.out.println("n: " + n + " at k: " + k);
    }

    public void unsetKthBit(int n, int k) {
        System.out.println("n: " + n + " at k: " + k);
        n = n & (~(1 << k));
        System.out.println("n: " + n + " at k: " + k);
    }

    public static void main(String[] args) {
        SetUnsetKthBit skb = new SetUnsetKthBit();
        System.out.println("Set kth bit");
        skb.setKthBit(13, 1);
        System.out.println("Unset kth bit");
        skb.unsetKthBit(14, 1);
    }
}

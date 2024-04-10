package BitManipulation;

/*
Remove last set bit

Given a number n, remove its last set bit.
A bit is said to be set if it has 1 at given last bit when number is represented in binary format
*/
public class RemoveLastSetBitUsingAND {

    public void removeLastSetBit(int n) {
        System.out.println("n: " + n);
        n = n & (n - 1);
        System.out.println("n: " + n);
    }

    public static void main(String[] args) {
        RemoveLastSetBitUsingAND rlsb = new RemoveLastSetBitUsingAND();
        System.out.println("Remove last set bit");
        rlsb.removeLastSetBit(16);
    }
}

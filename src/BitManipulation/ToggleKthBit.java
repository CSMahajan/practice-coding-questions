package BitManipulation;

/*
Toggle K-th bit set

Given a number n, toggle its k-th bit.
Toggle a bit is if k-th bit has 1, then make it as 0 and vice versa when number is represented in binary format
*/
public class ToggleKthBit {

    public void toggleKthBit(int n, int k) {
        System.out.println("n: " + n + " at k: " + k);
        n = n & (~(1 << k));
        System.out.println("n: " + n + " at k: " + k);
    }

    public static void main(String[] args) {
        ToggleKthBit tkb = new ToggleKthBit();
        System.out.println("Toggle kth bit");
        tkb.toggleKthBit(13, 1);
    }
}

package BitManipulation;

public class CheckKthBitSet {

    public boolean isKthBitSet(int n, int k) {
        return (n & (1<<k)) != 0;
    }

    public static void main(String[] args) {
        CheckKthBitSet ckbs = new CheckKthBitSet();
        System.out.println(ckbs.isKthBitSet(14, 2));
    }
}

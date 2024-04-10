package BitManipulation;

/*
Check if number is power of 2

Given a number n, check it is power of 2
*/
public class CheckIfNumberIsPowerOf2UsingAND {

    public boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        CheckIfNumberIsPowerOf2UsingAND cnip = new CheckIfNumberIsPowerOf2UsingAND();
        System.out.println(cnip.isPowerOf2(256));
    }
}

package BitManipulation;

/*
Swap 2 numbers

Given 2 numbers swap without using third variable
*/
public class XORSwapTwoNumbers {

    public void swap(int a, int b) {
        System.out.println("a: " + a + ", b: " + b);
        a= a^b;
        b=a^b;
        a=a^b;
        System.out.println("a: " + a + ", b: " + b);
    }

    public static void main(String[] args) {
        XORSwapTwoNumbers xorStn = new XORSwapTwoNumbers();
        xorStn.swap(5,7);
    }
}

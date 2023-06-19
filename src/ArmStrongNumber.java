public class ArmStrongNumber {
    public static String armstrongNumber(int n) {
        // code here
        int sumOfCubes = 0;
        int originalNumber = n;
        while (n > 0) {
            int digit = n % 10;
            sumOfCubes += Math.pow(digit, 3);
            n /= 10;
        }
        if (sumOfCubes == originalNumber) {
            return "Yes";
        }
        return "No";
    }


    public static void main(String[] args) {
        System.out.println(armstrongNumber(371));
    }
}

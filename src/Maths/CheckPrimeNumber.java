package Maths;

/*
Check Prime Number

Given a number, check if it is prime or not
*/
public class CheckPrimeNumber {

    public boolean isPrime(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n= 42;
        CheckPrimeNumber cpn = new CheckPrimeNumber();
        System.out.println(cpn.isPrime(n));
    }
}

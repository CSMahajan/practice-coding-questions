package Maths;

import java.util.ArrayList;
import java.util.List;

/*
Print all prime factors of number

You are given an integer 'N'.
You must return the unique prime factors of 'N' in increasing order.
For Example:
For ‘N’ = 10.
Unique prime factors are 2 and 5.
Hence we return {2, 5}.
*/
public class PrintAllPrimeFactorsOfNumber {

    public static List<Integer> countPrimes(int n) {
        List<Integer> primeFactors = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                primeFactors.add(i);
                while (n % i == 0) {
                    //reducing the number to avoid composite numbers if any,so number of iterations are reduced
                    n = n / i;
                }
            }
        }
        //at last, we are not dividing number till 1,so last prime number has to be added manually
        if (n != 1) {
            primeFactors.add(n);
        }
        return primeFactors;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(780));
    }
}

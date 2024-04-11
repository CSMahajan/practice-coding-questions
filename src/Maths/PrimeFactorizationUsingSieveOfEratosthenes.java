package Maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Prime Factorization using Sieve of Eratosthenes

You are given a positive number N. Using the concept of Sieve, compute its prime factorization.
Example:
Input:
N = 12246
Output:
2 3 13 157
Explanation:
2*3*13*157 = 12246 = N.
*/
public class PrimeFactorizationUsingSieveOfEratosthenes {

    public static void sieve() {

    }

    public static List<Integer> findPrimeFactors(int N) {
        List<Integer> countPrimes = new ArrayList<>();
        int[] primes = getSieve(1000000);
        int count = 0;
        for (int i = 2; i <= N; i++) {
            count += primes[i];
            primes[i] = count;
        }
        return countPrimes;
    }

    private static int[] getSieve(int n) {
        int[] primes = new int[n + 1];
        Arrays.fill(primes, 1);
        for (int i = 2; i * i <= n; i++) {
            if (primes[i] == 1) {
                for (int j = i * i; j <= n; j += i) {
                    primes[j] = 0;
                }
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        System.out.println(findPrimeFactors(780));
    }
}

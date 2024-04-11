package Maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Prime number in given range

You have given q queries.
For each query there is range given as L and R.
Find the count of prime numbers in that range for each query
*/
public class PrimeNumbersInRangeLtoR {

    public static List<Integer> countPrimeNumbersInRange(int[][] queries, int n) {
        List<Integer> countPrimes = new ArrayList<>();
        int[] primes = getSieve(n);
        int count = 0;
        //storing prefixSum in primes array where it denotes the number of prime numbers till that index
        for (int i = 2; i <= n; i++) {
            count += primes[i];
            primes[i] = count;
        }
        //now primes[i] denotes the number of prime numbers from 1 till the index i
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            countPrimes.add(primes[r] - primes[l - 1]);
        }
        return countPrimes;
    }

    private static int[] getSieve(int n) {
        //Sieve of Eratosthenes for calculating the number of primes till n
        int[] primes = new int[n + 1];
        Arrays.fill(primes, 1);
        primes[0] = 0;
        primes[1] = 0;
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
        int n = 1000000;
        int[][] queries = {{1, 4}, {2, 8}, {3, 5}};
        System.out.println(countPrimeNumbersInRange(queries, n));
    }
}

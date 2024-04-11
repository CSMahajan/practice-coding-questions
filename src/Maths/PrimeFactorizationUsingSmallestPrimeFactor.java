package Maths;

import java.util.ArrayList;
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
public class PrimeFactorizationUsingSmallestPrimeFactor {

    //TC:O(Nlog(logN)) + O(log2 N)
    //SC:O(N)
    public static List<Integer> findPrimeFactorsUsingSpf(int N) {
        List<Integer> countPrimes = new ArrayList<>();
        int[] spf = getSmallestPrimeFactor(N);
        while (N != 1) {
            countPrimes.add(spf[N]);
            N = N / spf[N];
        }
        return countPrimes;
    }

    private static int[] getSmallestPrimeFactor(int n) {
        int[] spf = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= n; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= n; j += i) {
                    //placing its multiples with its smallest prime factor possible
                    //only when if its not already marked because when next time newer number will be larger
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        return spf;
    }

    public static void main(String[] args) {
        System.out.println(findPrimeFactorsUsingSpf(780));
    }
}

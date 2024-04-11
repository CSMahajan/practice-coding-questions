package Maths;

import java.util.ArrayList;
import java.util.Arrays;

/*
Sieve of Eratosthenes / Count Primes

Given a number N, calculate the prime numbers up to N using Sieve of Eratosthenes.
Example 1:
Input:
N = 10
Output:
2 3 5 7
Explanation:
Prime numbers less than equal to N are 2 3 5 and 7.
Example 2:
Input:
N = 35
Output:
2 3 5 7 11 13 17 19 23 29 31
Explanation:
Prime numbers less than equal to 35 are
2 3 5 7 11 13 17 19 23 29 and 31.
*/
public class SieveOfEratosthenesCountPrimesLessThanN {

    //TC:O(N)+O(Nlog(logN))+O(N)
    //SC:O(1)
    public static ArrayList<Integer> sieveOfEratosthenes(int N) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] primes = new int[N + 1];
        Arrays.fill(primes, 1);
        primes[0] = 0;
        primes[1] = 0;
        for (int i = 2; i * i <= N; i++) {
            if (primes[i] == 1) {
                for (int j = i * i; j <= N; j += i) {
                    //marking the multiples of current prime number as 0 as they are not primes
                    primes[j] = 0;
                }
            }
        }
        for (int i = 2; i <= N; i++) {
            if (primes[i] == 1) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(sieveOfEratosthenes(40));
    }
}

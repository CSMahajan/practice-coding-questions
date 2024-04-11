package Maths;

import java.util.*;

/*
All divisors of a Number

Given an integer N, print all the divisors of N in the ascending order.
Example 1:
Input : 20
Output: 1 2 4 5 10 20
Explanation: 20 is completely divisible by 1, 2, 4, 5, 10 and 20.
Example 2:
Input: 21191
Output: 1 21191
Explanation: As 21191 is a prime number,
it has only 2 factors(1 and the number itself).
*/
public class PrintAllDivisorsOfNumber {

    public static void print_divisors(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                if (i != (n / i)) {
                    list.add(n / i);
                }
            }
        }
        Collections.sort(list);
        for (int num : list) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        print_divisors((36));
    }
}

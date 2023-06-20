/*
* We have an array arr of length N.
* Find the sum of XOR of each possible pair of array elements whose remainder calculated w.r.t 3 is same
* Example:
* Input: arr = {3, 6, 5, 11}
* Output: 19
* Explanation: here the pair {3,6} has remainder 0 and {5,11} has remainder 2 w.r.t 3
* Their sum of XOR operation is 3^6 + 5^11 = 5 + 14 = 19
* */
import java.util.HashMap;
import java.util.Map;

public class ArrayXorSumWithRemainder {
    public static void main(String[] args) {
        int[] array = {3, 6, 5, 11};

        int xorSum = calculateXorSumWithRemainder(array);
        System.out.println("XOR sum with equal remainders: " + xorSum);
    }

    //This solution is given by ChatGPT and not necessarily accurate.
    //Please understand the problem and do it on your own
    private static int calculateXorSumWithRemainder(int[] array) {
        int[] count = new int[3]; // Array to store the count of remainders
        Map<Integer, Integer> remainderPairs = new HashMap<>(); // Map to store XOR sum of pairs for each remainder
        int xorSum = 0;

        // Calculate the count of each remainder and store XOR sum of pairs in the remainderPairs map
        for (int i = 0; i < array.length; i++) {
            int remainder = array[i] % 3;
            count[remainder]++;
            remainderPairs.put(remainder, remainderPairs.getOrDefault(remainder, 0) ^ array[i]);
        }

        // Calculate the XOR sum of pairs with equal remainders
        xorSum += calculatePairXorSum(remainderPairs.getOrDefault(0, 0), count[0]);
        xorSum += calculatePairXorSum(remainderPairs.getOrDefault(1, 0), count[1]);
        xorSum += calculatePairXorSum(remainderPairs.getOrDefault(2, 0), count[2]);

        return xorSum;
    }

    private static int calculatePairXorSum(int xorSum, int count) {
        int pairXorSum = 0;

        // Calculate XOR sum of pairs with count > 1
        if (count > 1) {
            pairXorSum = xorSum * (count - 1);
        }

        return pairXorSum;
    }
}

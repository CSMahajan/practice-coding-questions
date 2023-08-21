package Hashing;

import java.util.HashMap;
import java.util.Map;

/*
Subarrays with XOR value K

Given an array arr of N integers and an integer K, find the number of subarrays of arr having XOR of elements as K.
Example 1:
Input Format: A = [4, 2, 2, 6, 4] , k = 6
Result: 4
Explanation: The subarrays having XOR of their elements as 6 are  [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], [6]
Example 2:
Input Format: A = [5, 6, 7, 8, 9], k = 5
Result: 2
Explanation: The subarrays having XOR of their elements as 5 are [5] and [5, 6, 7, 8, 9]
*/
public class CountSubarraysWithGivenXorKValue {

    //Time Complexity: O(N) or O(N*logN) depending on which map data structure we are using, where N = size of the array.
    //Reason: For example, if we are using an unordered_map data structure in C++ the time complexity will be O(N)
    //but if we are using a map data structure,
    //the time complexity will be O(N*logN). The least complexity will be O(N) as we are using a loop to traverse the array.
    //Point to remember for unordered_map in the worst case, the searching time increases to O(N),
    //and hence the overall time complexity increases to O(N2).
    //Space Complexity: O(N) as we are using a map data structure.
    public static int subsetXOR(int[] arr, int n, int k) {
        // code here
        int prefixXor = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //map.put(prefixXor, 1);
        for (int i = 0; i < n; i++) {
            // prefix XOR till index i:
            prefixXor = prefixXor ^ arr[i];
            //By formula: x = prefixXor^k:
            int x = prefixXor ^ k;
            if (prefixXor == k) {
                count++;
            }
            // add the occurrence of prefixXor^k to the count:
            if (map.containsKey(x)) {
                count += map.get(x);
            }
            // Insert the prefix xor till index i into the map:
            map.put(prefixXor, map.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 6, 4};
        int n = arr.length;
        int k = 6;
        System.out.println(subsetXOR(arr, n, k));
    }
}
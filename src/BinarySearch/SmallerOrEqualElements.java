package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* We have 2 arrays A and B of length n and m respectively.
* Find number of elements in B that are smaller or equal than
* that of element in A for every element of A
Example:
       Input: 5
              5 4 3 2 1
              2
              3 4
      Output: 2 2 1 0 0
Explanation: For the first index, A[0] = 5
Both the elements of Array B are less than 5. Therefore the answer for the first index is 2.
For the second index, A[1] = 4
In array B, one element is smaller than 4 and one element is equal to 4.
Therefore the answer for the second index is also 2 and so on.
Hence, the final answer is [2, 2, 1, 0, 0] in this case.
*/
public class SmallerOrEqualElements {
    public static List<Integer> countSmallerOrEqual(int[] a, int[] b, int n, int m) {
        // Write your code here!
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(b);
        for (int num : a) {
            int count = 0, start = 0, end = m - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (b[mid] <= num) {
                    count = mid + 1;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            list.add(count);
        }
        return list;
    }

    public static void main(String[] args) {
        int a[] = {5,4,3,2,1};
        int n = a.length;
        int b[] = {3,4};
        int m = b.length;
        System.out.println(countSmallerOrEqual(a,b,n,m));
    }
}

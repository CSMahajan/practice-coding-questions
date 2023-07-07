package BinarySearch;

import java.util.Arrays;

/*
Given an unsorted array Arr[] of N integers and an integer X,
find floor and ceiling of X in Arr[0..N-1].

Floor of X is the largest element which is smaller than or equal to X.
Floor of X doesn’t exist if X is smaller than smallest element of Arr[].

Ceil of X is the smallest element which is greater than or equal to X.
Ceil of X doesn’t exist if X is greater than greatest element of Arr[].
Example 1:
Input:
N = 8, X = 7
Arr[] = {5, 6, 8, 9, 6, 5, 5, 6}
Output: 6 8
Explanation:
Floor of 7 is 6 and ceil of 7
is 8.
*/
public class CeilLowerBoundFloorUpperBoundSortedArray {

    class Pair {
        long floor, ceil;

        public Pair(long floor, long ceil) {
            this.floor = floor;
            this.ceil = ceil;
        }

        @Override
        public String toString() {
            return "(" + floor + "," + ceil + ")";
        }
    }

    public Pair getFloorAndCeil(int[] arr, int n, int x) {
        Arrays.sort(arr);
        int mod = 1000000007;
        int start = 0, end = n - 1, floor = -1, ceil = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid%mod] <= x) {
                floor = arr[mid%mod];
                //look for more smaller index on the left
                start = mid + 1;
            } if (arr[mid%mod] >= x) {
                //look for more greater index on the right
                ceil = arr[mid%mod];
                end = mid - 1;
            }
        }
        return new Pair(floor, ceil);
    }

    public static void main(String[] args) {
        int arr[] = {5, 6, 8, 9, 6, 5, 5, 6};
        int x = 7;
        int n = arr.length;
        CeilLowerBoundFloorUpperBoundSortedArray clbfubsa = new CeilLowerBoundFloorUpperBoundSortedArray();
        System.out.println(clbfubsa.getFloorAndCeil(arr, n, x));
    }
}

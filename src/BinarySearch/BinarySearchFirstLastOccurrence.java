package BinarySearch;/*
Find the first and last position of a target element in sorted array
 */

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchFirstLastOccurrence {

    public static int[] searchFirstLastOccurrence(int[] arr, int k) {
        int first = getFirstOccurrence(arr, k);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = getLastOccurrence(arr, k);
        return new int[]{first, last};
    }

    public static int getFirstOccurrence(int[] arr, int k) {
        int first = -1;
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k) {
                first = mid;
                high = mid - 1;
            } else if (arr[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return first;
    }

    public static int getLastOccurrence(int[] arr, int k) {
        int first = -1;
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k) {
                first = mid;
                low = mid + 1;
            } else if (arr[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        long[] arr = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        int x = 3;

        int a[] = {3, 5, 4};
        int b[] = {3, 6, 5, 11};
        int c[] = {-10, -5, -5, -5, 2};
        int d[] = {1, 2, 4, 4, 5};
        int e[] = {1, 2, 3, 4};
        ArrayList<Integer> f = new ArrayList<>();
        f.add(1);
        f.add(2);
        f.add(4);
        f.add(4);
        f.add(5);
        //System.out.println(searchRange(c, -5));
        //System.out.println(searchRange(d, 4));
        //System.out.println(searchRange(e, -1));
        System.out.println(Arrays.toString(searchFirstLastOccurrence(d, 4)));
    }
}

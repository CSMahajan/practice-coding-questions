package BinarySearch;/*
Find the first and last position of a target element in sorted array
 */

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
        int[] a = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        int[] b = {3, 6, 5, 11};
        int[] c = {-10, -5, -5, -5, 2};
        int[] d = {1, 2, 4, 4, 5};
        int[] e = {1, 2, 3, 4};
        System.out.println(Arrays.toString(searchFirstLastOccurrence(a, 4)));
        System.out.println(Arrays.toString(searchFirstLastOccurrence(b, 4)));
        System.out.println(Arrays.toString(searchFirstLastOccurrence(c, 4)));
        System.out.println(Arrays.toString(searchFirstLastOccurrence(d, 4)));
        System.out.println(Arrays.toString(searchFirstLastOccurrence(e, 4)));
    }
}
/*
Find the first and last position of a target element in sorted array
 */
import java.util.Arrays;

public class BinarySearchFirstLastOccurrence {

    public static int[] searchRange(int[] arr, int x) {
        int[] result = {-1, -1};
        int left = binarySearch(arr, x, true);

        if (left == arr.length || arr[left] != x)
            return result;

        result[0] = left;
        result[1] = binarySearch(arr, x, false) - 1;
        return result;
    }

    public static int binarySearch(int[] arr, int target, boolean findFirst) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > target || (findFirst && arr[mid] == target))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        int x = 3;
        int[] positions = searchRange(arr, x);

        System.out.println("First Position: " + positions[0]);
        System.out.println("Last Position: " + positions[1]);
        int[] a = {3, 5, 4};
        int b[] = {3, 6, 5, 11};
        int c[] = {-10, -5, -5, -5, 2};
        int d[] = {1, 2, 4, 4, 5};
        int e[] = {1, 2, 3, 4};
        System.out.println(Arrays.toString(searchRange(c, -5)));
        System.out.println(Arrays.toString(searchRange(d, 4)));
        System.out.println(Arrays.toString(searchRange(e, -1)));
    }
}

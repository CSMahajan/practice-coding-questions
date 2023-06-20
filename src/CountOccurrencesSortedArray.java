import java.util.Arrays;

public class CountOccurrencesSortedArray {

    public static int count(int[] arr, int n, int x) {
        // code here
        int first = getFirstOccurrence(arr, n, x);
        if (first == -1) {
            return 0;
        }
        int last = getLastOccurrence(arr, n, x);
        return last - first + 1;
    }

    public static int getFirstOccurrence(int arr[], int n, int k) {
        int first = -1;
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

    public static int getLastOccurrence(int arr[], int n, int k) {
        int first = -1;
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
        int d[] = {1, 2, 4, 4, 5};
        System.out.println(count(d, d.length, 4));
    }
}

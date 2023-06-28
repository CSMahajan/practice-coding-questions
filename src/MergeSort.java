import java.util.ArrayList;
import java.util.Arrays;

/*
Merge Sort

Given an array arr[], its starting position l and its ending position r.
Sort the array using merge sort algorithm.
Example 1:
Input:
N = 5
arr[] = {4 1 3 9 7}
Output:
1 3 4 7 9
Example 2:
Input:
N = 10
arr[] = {10 9 8 7 6 5 4 3 2 1}
Output:
1 2 3 4 5 6 7 8 9 10
*/
public class MergeSort {

    /*Time complexity: O(nlogn)
    Reason: At each step, we divide the whole array, for that logn and we assume n steps are taken to get a sorted array, so overall time complexity will be nlogn
    Space complexity: O(n)
    Reason: We are using a temporary array to store elements in sorted order.
    Auxiliary Space Complexity: O(n)*/
    public static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr

        //storing elements in the temporary array in a sorted manner
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        //if elements on the left half are still remaining
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        //if elements on the right half are still remaining
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        //transferring all elements from temporary to arr
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    public static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid);  // left half
        mergeSort(arr, mid + 1, high); // right half
        merge(arr, low, mid, high);  // merging sorted halves
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}

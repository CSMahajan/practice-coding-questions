/*
 * We have sorted array arr and number m.
 * Find the correct position for m where it can be inserted in the array so that it remains sorted.
 * Example:
 *           Input: arr = {1,2,4,7}
 *                  m = 6
 *           Output: 3
 * Explanation: At 3rd position if 6 is inserted then array becomes {1,2,4,6,7} and remains sorted.
 * */
public class InsertionSortedArray {

    public static int searchInsert(int[] arr, int m) {
        // Write your code here.
        int n = arr.length;
        if(m >= arr[n - 1]) {
            return n;
        }
        if(m <= arr[0]) {
            return 0;
        }
        int correctPosition = 0, start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= m) {
                //below if condition is add in case if that element m is already present in an array
                if(arr[mid]==m)  {
                    return mid;
                }
                //below part is added to check for arr[mid] < m
                correctPosition = mid + 1;
                //below if condition added to check if next element after mid should be greater or equal than m
                //then it is confirmed that we found the correct position, so break is added
                if(m <= arr[mid+1]) {
                    break;
                }
                //otherwise we are continuing with binary search
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return correctPosition;
    }

    public static void main(String[] args) {
        int a[] = {3,6,12};
        int m = 7;
        System.out.println(searchInsert(a,m));
    }
}

/*
Find the element that appears once in sorted array

Given a sorted array arr[] of size N. Find the element that appears only once in the array.
All other elements appear exactly twice.

Example 1:

Input:
N = 11
arr[] = {1, 1, 2, 2, 3, 3, 4, 50, 50, 65, 65}
Output: 4
Explanation: 4 is the only element that
appears exactly once.
 */
public class SingleOccurrenceSortedArray {

    public static int findOnce(int arr[], int n) {
        // Complete this function
        if (n == 1) {
            return arr[0];
        }
        if (arr[0] != arr[1]) {
            return arr[0];
        }
        if (arr[n - 1] != arr[n - 2]) {
            return arr[n - 1];
        }
        //We will start in range 1 to n-2 as we have already handled edge case for 0 and n-1
        //which could have caused ArrayIndexOutOfBoundsException
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid - 1] != arr[mid] && arr[mid] != arr[mid + 1]) {
                return arr[mid];
            }
            /*
            If we carefully observe, the element which appears only once,
            we get that on left half of that element we will have elements
            with index (even,odd) so we need to eliminate if we are in left half to find single element
            Similarly for right half equal value pairs have indexes as (odd,even)
            */
            if ((mid % 2 == 1 && arr[mid] == arr[mid - 1]) || (mid % 2 == 0 && arr[mid] == arr[mid + 1])) {
                low = mid + 1;  //eliminate the left half, i.e. we are on left half and need to search on right half
            } else {
                //similar conditions vice-versa way can also be written for presence in right half but not required
                high = mid - 1; //eliminate the right half, i.e. we are on right half and need to search on left half
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {-96, -96, -93, -93, -80, -80, -77, -77, -56, -56, -43, -43, -30, -30, -29, -29, -14, -14, -10, -10, 8, 8, 29, 29, 30, 30, 38, 44, 44, 46, 46, 79, 79, 87, 87, 88, 88, 94, 94};
        System.out.println(findOnce(arr, arr.length));
    }
}

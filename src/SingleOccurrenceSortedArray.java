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

    //TC:O(N)
    //SC:O(1)
    //XOR
    //Easiest way to implement and understand
    //Trick: XOR of two same numbers is 0 and XOR with 0 of any number is that number
    //Example: 1^1=0 and 1^0=1
    //When we xor of all array the final answer is single appearing element
    //This solution can work for not sorted arrays as well
    public static int findOnceXOR(int arr[], int n) {
        // Complete this function
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= arr[i];
        }
        return xor;
    }

    //TC:O(log(N))
    //SC:O(1)
    //Binary Search
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
        int arr[] = {1, 1, 2, 2, 3, 3, 4, 50, 50, 65, 65};
        System.out.println(findOnce(arr, arr.length));
        int arr1[] = {1, 3, 4, 50, 1, 2, 2, 3, 50, 65, 65};
        System.out.println(findOnceXOR(arr1, arr1.length));
    }
}

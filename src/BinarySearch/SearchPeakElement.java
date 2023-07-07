package BinarySearch;

/*
Peak element

An element is called a peak element if its value is not smaller than the value of its adjacent elements
(if they exists).
Given an array arr[] of size N, Return the index of any one of its peak elements.
Note: The generated output will always be 1 if the index that you return is correct.
Otherwise output will be 0.
Example 1:
Input:
N = 3
arr[] = {1,2,3}
Possible Answer: 2
Generated Output: 1
Explanation: index 2 is 3.
It is the peak element as it is
greater than its neighbour 2.
If 2 is returned then the generated output will be 1 else 0.

Example 2:
Input:
N = 3
arr[] = {3,4,2}
Possible Answer: 1
Output: 1
Explanation: 4 (at index 1) is the
peak element as it is greater than
it's neighbor elements 3 and 2.
If 1 is returned then the generated output will be 1 else 0.
 */
public class SearchPeakElement {

    public static int peakElement(int[] arr,int n)
    {
        //add code here.
        if (n == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }
        //We will start in range 1 to n-2 as we have already handled edge case for 0 and n-1
        //which could have caused ArrayIndexOutOfBoundsException
        int low = 1, high = n - 2;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if(arr[mid - 1] < arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {2,2};
        System.out.println(peakElement(arr, arr.length));
    }
}

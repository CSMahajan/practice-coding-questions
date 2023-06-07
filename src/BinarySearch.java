public class BinarySearch {

    /*Binary Search

    Given a sorted array of size N and an integer K, find the position(0-based indexing) at which K is present in the array using binary search.

    Example 1:

    Input:
    N = 5
    arr[] = {1 2 3 4 5}
    K = 4
    Output: 3
    Explanation: 4 appears at index 3.*/

    public static int binarysearch(int arr[], int n, int k) {
        // code here
        int left  = 0 ;
        int right = n - 1;
        int mid = (left+right)/2;
        while(left<=right)
        {
            mid = (left+right)/2;
            if(arr[mid]==k)
            {
                return mid;
            }
            else if(arr[mid] < k)
            {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        if(left>right)
        {
            return -1;
        }
        return mid;
    }
}

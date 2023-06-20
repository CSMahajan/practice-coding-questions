/*
Minimum element in a sorted and rotated array
A sorted(in ascending order) array A[ ] with distinct elements is rotated at some unknown point,
the task is to find the minimum element in it.

Example 1

Input:
N = 5
arr[] = {4 ,5 ,1 ,2 ,3}
Output: 1
Explanation: 1 is the minimum element inthe array.

*/
public class MinimumRotatedSortedArray {
    public static int findMin(int arr[], int n)
    {
        //complete the function here
        int low = 0, high = n - 1, minimum = Integer.MAX_VALUE;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(arr[low] <= arr[mid]) {
                //Left Half Sorted
                minimum = Math.min(minimum, arr[low]);
                low = mid + 1;
            } else {
                minimum = Math.min(minimum, arr[mid]);
                high = mid - 1;
            }
        }
        return minimum;
    }

    public static void main(String[] args) {
        int arr[] = {4 ,5 , 1 ,2 ,3};
        int n = arr.length;
        System.out.println(findMin(arr,n));
    }
}

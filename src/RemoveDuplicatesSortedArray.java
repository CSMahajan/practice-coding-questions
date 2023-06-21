/*
Remove duplicate elements from sorted Array

Given a sorted array A[] of size N, delete all the duplicated elements from A[]. Modify the array such that if there are X distinct elements in it then the first X positions of the array should be filled with them in increasing order and return the number of distinct elements in the array.

Note:
1. Don't use set or HashMap to solve the problem.
2. You must return the number of distinct elements(X) in the array, the generated output will print all the elements of the modified array from index 0 to X-1.

Example 1:

Input:
N = 5
Array = {2, 2, 2, 2, 2}
Output: {2}
Explanation: After removing all the duplicates
only one instance of 2 will remain.
Example 2:

Input:
N = 3
Array = {1, 2, 2}
Output: {1, 2}
 */
public class RemoveDuplicatesSortedArray {

    public static int removeDuplicates(int arr[],int n){
        // code here
        int i = 0;
        for(int j = 1; j < n; j++) {
            if(arr[i]!=arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int arr[] = {1, 4, 6, 50, 50, 65, 65};
        System.out.println(removeDuplicates(arr, arr.length));
    }
}

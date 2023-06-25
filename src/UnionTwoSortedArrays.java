import java.util.ArrayList;
import java.util.List;

/*
Union of Two Sorted Arrays

Union of two arrays can be defined as the common and distinct elements in the two arrays.
Given two sorted arrays of size n and m respectively, find their union.
Example 1:
Input:
n = 5, arr1[] = {1, 2, 3, 4, 5}
m = 3, arr2 [] = {1, 2, 3}
Output: 1 2 3 4 5
Explanation: Distinct elements including
both the arrays are: 1 2 3 4 5.
Example 2:
Input:
n = 5, arr1[] = {2, 2, 3, 4, 5}
m = 5, arr2[] = {1, 1, 2, 3, 4}
Output: 1 2 3 4 5
Explanation: Distinct elements including
both the arrays are: 1 2 3 4 5.
Example 3:
Input:
n = 5, arr1[] = {1, 1, 1, 1, 1}
m = 5, arr2[] = {2, 2, 2, 2, 2}
Output: 1 2
Explanation: Distinct elements including
both the arrays are: 1 2.
*/
public class UnionTwoSortedArrays {

    //Function to return a list containing the union of the two arrays.
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        // add your code here
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            //Element of first array smaller or equal than second array
            if (arr1[i] <= arr2[j]) {
                //list.size()==0 check added if union list is empty then no need to compare if that element exists
                //or we are checking if that element already exists in union list for first array
                if (list.size() == 0 || list.get(list.size() - 1) != arr1[i]) {
                    list.add(arr1[i]);
                }
                i++;
            } else {
                //list.size()==0 check added if union list is empty then no need to compare if that element exists
                //or we are checking if that element already exists in union list for second array
                if (list.size() == 0 || list.get(list.size() - 1) != arr2[j]) {
                    list.add(arr2[j]);
                }
                j++;
            }
        }
        //First array got exhausted add elements for second array into union list
        while (j < m) {
            if (list.size() == 0 || list.get(list.size() - 1) != arr2[j]) {
                list.add(arr2[j]);
            }
            j++;
        }
        //Second array got exhausted add elements for first array into union list
        while (i < n) {
            if (list.size() == 0 || list.get(list.size() - 1) != arr1[i]) {
                list.add(arr1[i]);
            }
            i++;
        }
        return list;
    }

    public static List<Integer> sortedArray(int[] a, int[] b) {
        // Write your code here
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        int n = a.length;
        int m = b.length;
        while (i < n && j < m) {
            //Element of first array smaller or equal than second array
            if (a[i] <= b[j]) {
                //list.size()==0 check added if union list is empty then no need to compare if that element exists
                //or we are checking if that element already exists in union list for first array
                if (list.size() == 0 || list.get(list.size() - 1) != a[i]) {
                    list.add(a[i]);
                }
                i++;
            } else {
                //list.size()==0 check added if union list is empty then no need to compare if that element exists
                //or we are checking if that element already exists in union list for second array
                if (list.size() == 0 || list.get(list.size() - 1) != b[j]) {
                    list.add(b[j]);
                }
                j++;
            }
        }
        //First array got exhausted add elements for second array into union list
        while (j < m) {
            if (list.size() == 0 || list.get(list.size() - 1) != b[j]) {
                list.add(b[j]);
            }
            j++;
        }
        //Second array got exhausted add elements for first array into union list
        while (i < n) {
            if (list.size() == 0 || list.get(list.size() - 1) != a[i]) {
                list.add(a[i]);
            }
            i++;
        }
        return list;
    }

    public static void main(String[] args) {
        int n = 5, arr1[] = {1, 2, 3, 4, 5};
        int m = 3, arr2[] = {1, 2, 3};
        System.out.println(findUnion(arr1, arr2, n, m));
        System.out.println(sortedArray(arr1, arr2));
    }
}

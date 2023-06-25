import java.util.ArrayList;
import java.util.Arrays;

/*
Intersection of two arrays

GeeksForGeeks Problem Statement
Given two arrays a[] and b[] respectively of size n and m,
the task is to print the count of elements in the intersection (or common elements) of the two arrays.
For this question, the intersection of two arrays can be defined as the
set containing distinct common elements between the two arrays.
Example 1:
Input:
n = 5, m = 3
a[] = {89, 24, 75, 11, 23}
b[] = {89, 2, 4}
Output: 1
Explanation:
89 is the only element
in the intersection of two arrays.
Example 2:
Input:
n = 6, m = 5
a[] = {1, 2, 3, 4, 5, 6}
b[] = {3, 4, 5, 6, 7}
Output: 4
Explanation:
3 4 5 and 6 are the elements
in the intersection of two arrays.

Codingninjas Problem Statement
Return the intersection list of two sorted arrays
*/
public class IntersectionTwoSortedArrays {

    //GeeksForGeeks
    public static int NumberofElementsInIntersection(int a[], int b[], int n, int m) {
        // Your code here
        int i = 0;
        int j = 0;
        Arrays.sort(a);
        Arrays.sort(b);
        ArrayList<Integer> list = new ArrayList<>();
        while(i < n && j < m) {
            if(a[i] < b[j]) {
                i++;
            } else if(a[i] > b[j]) {
                j++;
            } else {
                list.add(a[i]);
                i++;
                j++;
            }
        }
        return list.size();
    }

    //Coding Ninjas / CodeStudio
    public static ArrayList<Integer> findArrayIntersection(ArrayList<Integer> arr1, int n, ArrayList<Integer> arr2, int m)
    {
        // Write Your Code Here.
        int i = 0;
        int j = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(i < n && j < m) {
            if(arr1.get(i) < arr2.get(j)) {
                i++;
            } else if(arr1.get(i) > arr2.get(j)) {
                j++;
            } else {
                list.add(arr1.get(i));
                i++;
                j++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int n = 5, m = 3;
        int a[] = {89, 24, 75, 11, 23};
        int b[] = {89, 2, 4};

        System.out.println(NumberofElementsInIntersection(a,b,n,m));

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr1.add(11);
        arr1.add(23);
        arr1.add(24);
        arr1.add(75);
        arr1.add(89);
        arr2.add(2);
        arr2.add(4);
        arr2.add(89);

        System.out.println(findArrayIntersection(arr1,n,arr2,m));
    }
}
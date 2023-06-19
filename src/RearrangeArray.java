import java.util.Arrays;

/*
* Rearrange an array with O(1) extra space
*
* Given an array arr[] of size N where every element is in the range from 0 to n-1.
* Rearrange the given array so that the transformed array arrT[i] becomes arr[arr[i]].

NOTE: arr and arrT are both same variables,
* representing the array before and after transformation respectively.
*
* Example 2:

Input:
N = 5
arr[] = {4,0,2,1,3}
Output: 3 4 2 0 1
Explanation:
arr[arr[0]] = arr[4] = 3.
arr[arr[1]] = arr[0] = 4.
and so on.
* */
public class RearrangeArray {

    /*
    * Let’s assume an element is a and another element is b,
    * both the elements are less than n. So if an element a is incremented by b*n.
    * So the element becomes a + b*n so when a + b*n is divided by n then the value is b and a + b*n % n is a.
    * The array elements of the given array lie from 0 to n-1.
    * Now an array element is needed that can store two different values at the same time.
    * To achieve this, every element at ith index is incremented by (arr[arr[i]] % n)*n.
    * After the increment operation of the first step, every element holds both old values and new values.
    * An old value can be obtained by arr[i]%n and a new value can be obtained by arr[i]/n.
    */
    public static void rearrange(int arr[], int n)
    {
        // First step: Increase all values by
        // (arr[arr[i]]%n)*n
        for (int i = 0; i < n; i++)
            arr[i] += (arr[arr[i]] % n) * n;

        // Second Step: Divide all values by n
        for (int i = 0; i < n; i++)
            arr[i] /= n;
    }

    public static void main(String[] args) {
        int arr[] = {4,0,2,1,3};
        int n = arr.length;
        rearrange(arr, n);
        System.out.println(Arrays.toString(arr));
    }
}

import java.util.Arrays;

public class RightRotateArray {

    //WAY-1
    /*Way 1: Using temp array

    Approach:
    In this method simply create a temporary array and copy the elements
    of the array arr[] from 0 to the N – D index.
    After that move, the rest elements of the array arr[] from index D to N.
    Then move the temporary array elements to the original array.
    It is as illustrated below illustration as follows:

    Input arr[] = [1, 2, 3, 4, 5], D = 2
            1) Store the first d elements in a temp array
    temp[] = [1, 2, 3]
            2) Shift rest of the arr[]
    arr[] = [4, 5]
            3) Store back the D elements
    arr[] = [4, 5, 1, 2, 3]*/
    //Time complexity: O(N) ,
    //Auxiliary Space: O(D)
    // Method 1
    // To right rotate arr[] of size N by D
    void rightRotateNewTempArray(int arr[], int d, int n)
    {

        // If arr is rotated n times then
        // you get the same array
        // below while loop can also be replaced with
        // d = d % n;
        while (d > n) {
            d = d - n;
        }

        // Creating a temporary array of size d
        int temp[] = new int[n - d];

        // Now copying first N-D element in array temp
        for (int i = 0; i < n - d; i++)
            temp[i] = arr[i];

        // Moving the rest element to index zero to D
        for (int i = n - d; i < n; i++) {
            arr[i - n + d] = arr[i];
        }

        // Copying the temp array element
        // in original array
        for (int i = 0; i < n - d; i++) {
            arr[i + d] = temp[i];
        }
    }

    //WAY-2
    /*Way 2: Rotate one by one

    Approach: Rotate the array recursively one by one element

    Input arr[] = [1, 2, 3, 4, 5], D = 2
    swap arr[N] to arr[N-1]
    swap arr[N-1] to arr[N-2]
    swap arr[2] to arr[1]
    Repeat 1, 2, 3 to D times
    To rotate by one, store arr[N] in a temporary variable temp, move arr[N-1] to arr[N],
    arr[N-2] to arr[N-1] … and finally temp to arr[1].

    Let us take the same example arr[] = [1, 2, 3, 4, 5], d = 2
    Rotate arr[] by one 2 times
    We get [5, 1, 2, 3, 4] after first rotation and [ 4, 5, 1, 2, 3] after second rotation.*/
    //Time complexity: O(N * D) ,
    //Auxiliary Space: O(1)
    // Method 1
    // To right rotate array of size n by d
    void rightRotateRecursive(int arr[], int d, int n) {
        // Iterating till we want
        for (int i = 0; i < n - d; i++)
            // Recursively calling
            rightRotatebyOne(arr, n);
    }

    // Method 2
    // To rotate array by 1
    void rightRotatebyOne(int arr[], int n) {
        int i, temp;
        temp = arr[0];
        for (i = 0; i < n - 1; i++)
            arr[i] = arr[i + 1];
        arr[i] = temp;
    }

    //WAY-3
    /*Way 3: Juggling Algorithm

    Approach: This is an extension of method 2.
    Instead of moving one by one, divide the array into different sets where
    the number of sets is equal to GCD of n and d and move the elements within sets.

    If GCD is 1 as-is for the above example array (n = 5 and d =2),
    then elements will be moved within one set only, we just start with temp = arr[N] and
    keep moving arr[I+d] to arr[I] and finally store temp at the right place.*/
    //Time complexity: O(N) ,
    //Auxiliary Space: O(1)
    // Method 1
    void rightRotateGCDJugglingAlgorithm(int arr[], int d, int n) {

        // To use as left rotation
        d = n - d;
        d = d % n;
        int i, j, k, temp;
        int g_c_d = gcd(d, n);

        for (i = 0; i < g_c_d; i++) {

            // Moving i-th values of blocks
            temp = arr[i];
            j = i;

            while (true) {
                k = j + d;
                if (k >= n)
                    k = k - n;
                if (k == i)
                    break;
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }
    }

    // Method 2
    // To get gcd of a and b
    int gcd(int a, int b) {

        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    // Method 2
    // Main driver method
    public static void main(String[] args) {

        //In case of right rotation, replace rotateBy ( i.e. d) with (n - d)
        //Rest all the implementations is same as left rotation

        // Creating an object of class inside main()
        RightRotateArray rotate = new RightRotateArray();

        // Custom input elements
        int arr[] = {1, 2, 3, 4, 5};

        int rotateBy = 2;

        // Calling method 1 and 2
        System.out.print("Original Array -> ");
        System.out.println(Arrays.toString(arr));
        rotate.rightRotateNewTempArray(arr, rotateBy, arr.length);
        System.out.print("Right Rotated Array -> ");
        System.out.println(Arrays.toString(arr));
        rotate.rightRotateRecursive(arr, rotateBy, arr.length);
        System.out.print("Right Rotated Array -> ");
        System.out.println(Arrays.toString(arr));
        rotate.rightRotateGCDJugglingAlgorithm(arr, rotateBy, arr.length);
        System.out.print("Right Rotated Array -> ");
        System.out.println(Arrays.toString(arr));
    }

}

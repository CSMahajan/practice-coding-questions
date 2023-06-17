import java.util.Arrays;

public class LeftRotateArray {

    //WAY-1
    /*Way 1: Using a temporary array

    Approach:
    In this method simply create a temporary array and copy the elements of the array
    arr[] from 0 to the (D-1)th index.
    After that move, the rest elements of the array arr[] from index D to N.
    Then move the temporary array elements to the original array.

    Input arr[] = [1, 2, 3, 4, 5]
    D = 2
    Store the first d elements in a temp array: temp[] = [1, 2]
    Shift rest of the arr[]: arr[] = [3, 4, 5]
    Store back the D elements: arr[] = [3, 4, 5, 1, 2]*/
    // Method 1
    // To left rotate arr[]
    // of size N by D
    void leftRotateNewTempArray(int arr[], int d, int n)
    {
        // Creating temp array of size d
        int temp[] = new int[d];

        // Copying first d element in array temp
        for (int i = 0; i < d; i++)
            temp[i] = arr[i];

        // Moving the rest element to index
        // zero to N-d
        for (int i = d; i < n; i++) {
            arr[i - d] = arr[i];
        }

        // Copying the temp array element
        // in original array
        for (int i = 0; i < d; i++) {
            arr[i + n - d] = temp[i];
        }
    }

    //WAY-2
    /*Way 2: Rotate one by one

    Approach:
    Rotate the array recursively one by one element

    Input arr[] = [1, 2, 3, 4, 5]
    D = 21
    Swap arr[0] to arr[1]
    Swap arr[1] to arr[2]
    Swap arr[N-1] to arr[N]
    Repeat 1, 2, 3 to D times
    In order to rotate by one, store arr[0] in a temporary variable temp, move arr[1] to arr[0],
    arr[2] to arr[1] …and finally temp to arr[n-1]

    Illustration:

    Let us take the same example arr[] = [1, 2, 3, 4, 5], d = 2
    Rotate arr[] by one 2 times
    We get [2, 3, 4, 5, 1] after first rotation and [ 3, 4, 5, 1, 2] after second rotation.*/
    // Method 1
    // To rotate left by D elements
    void leftRotateRecursive(int arr[], int d, int n)
    {
        for (int i = 0; i < d; i++)
            leftRotatebyOne(arr, n);
    }

    // Method 2
    // To rotate left one by one
    void leftRotatebyOne(int arr[], int n)
    {
        int i, temp;
        temp = arr[0];
        for (i = 0; i < n - 1; i++)
            arr[i] = arr[i + 1];
        arr[i] = temp;
    }

    //WAY-3
    /*Way 3: Using Juggling Algorithm

    Approach:
    This is an extension of method 2. Instead of moving one by one,
    divide the array into different sets where the
    number of sets is equal to GCD of n and d and move the elements within sets.

    If GCD is 1 as-is for the above example array (n = 5 and d = 2),
    then elements will be moved within one set only, we just start with temp = arr[0] and
    keep moving arr[I+d] to arr[I] and finally store temp at the right place.*/
    // Method 1
    // To left rotate arr[] of size N by D
    void leftRotateGCDJugglingAlgorithm(int arr[], int d, int n)
    {
        // To handle if d >= n
        d = d % n;
        int i, j, k, temp;
        int g_c_d = gcd(d, n);

        for (i = 0; i < g_c_d; i++) {

            // move i-th values of blocks
            temp = arr[i];
            j = i;

            // Performing sets of operations if
            // condition holds true
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
    int gcd(int a, int b)
    {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    // Method 3
    // Main driver method
    public static void main(String[] args) {

        //In case of right rotation, replace rotateBy ( i.e. d) with (n - d)
        //Rest all the implementations is same as left rotation

        // Creating an object of class inside main()
        LeftRotateArray rotate = new LeftRotateArray();

        // Custom input elements
        int arr[] = {1, 2, 3, 4, 5};

        int rotateBy = 2;

        // Calling method 1 and 2
        System.out.print("Original Array -> ");
        System.out.println(Arrays.toString(arr));
        rotate.leftRotateNewTempArray(arr, rotateBy, arr.length);
        System.out.print("Left Rotated Array -> ");
        System.out.println(Arrays.toString(arr));
        rotate.leftRotateRecursive(arr, rotateBy, arr.length);
        System.out.print("Left Rotated Array -> ");
        System.out.println(Arrays.toString(arr));
        rotate.leftRotateGCDJugglingAlgorithm(arr, rotateBy, arr.length);
        System.out.print("Left Rotated Array -> ");
        System.out.println(Arrays.toString(arr));
    }
}

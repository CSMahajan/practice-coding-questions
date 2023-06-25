public class MissingNumber {

    /*
    Missing number in array

    Given an array of size N-1 such that it only contains distinct integers in the range of 1 to N.
    Find the missing element.
    Example 1:

    Input:
    N = 5
    A[] = {1,2,3,5}
    Output: 4*/
    //This way is easy to understand but can cause int overflow
    //i.e.int variable sum would not store max value from constraint like N=10^5, so sum = 10^5*(10^5+1)/2
    //This will cause overflow
    public static int MissingNumber(int array[], int n) {
        // Your Code Here
        int missingNumber, arraySum = 0, totalPossibleSum = 0;
        totalPossibleSum = n * (n + 1) / 2;
        for (int i = 0; i < array.length; i++) {
            arraySum += array[i];
        }
        missingNumber = totalPossibleSum - arraySum;
        return missingNumber;
    }

    /*
    Assume the given array is: {1, 2, 4, 5} and N = 5.
    XOR of (1 to 5) i.e. xor1 = (1^2^3^4^5)
    XOR of array elements i.e. xor2 = (1^2^4^5)
    XOR of xor1 and xor2 = (1^2^3^4^5) ^ (1^2^4^5)
			= (1^1)^(2^2)^(3)^(4^4)^(5^5)
			= 0^0^3^0^0 = 0^3 = 3.
    The missing number is 3.
     */
    public static int missingNumber(int[] arr, int N) {
        //xor
        int xor1 = 0, xor2 = 0;
        for (int i = 0; i < N - 1; i++) {
            xor2 = xor2 ^ arr[i]; // XOR of array elements
            xor1 = xor1 ^ (i + 1); //XOR up to [1...N-1]
        }
        xor1 = xor1 ^ N; //XOR up to [1...N]
        return (xor1 ^ xor2);
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,5};
        int n = 5;
        System.out.println(missingNumber(arr, n));
    }
}

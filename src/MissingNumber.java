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
    public static int MissingNumber(int array[], int n) {
        // Your Code Here
        int missingNumber, arraySum = 0, totalPossibleSum = 0;
        totalPossibleSum = n * (n+1) / 2;
        for(int i = 0; i < array.length; i++){
            arraySum += array[i];
        }
        missingNumber = totalPossibleSum - arraySum;
        return missingNumber;
    }
}

public class EquilibriumPoint {

    /*
    Equilibrium Point

    Given an array A of n positive numbers. The task is to find the first Equilibrium Point in an array.
    Equilibrium Point in an array is a position such that the sum of elements before
    it is equal to the sum of elements after it.

Note: Return the index of Equilibrium point. (1-based index)

Example 1:
    Input:
    n = 5
    A[] = {1,3,5,2,2}
    Output: 3
    Explanation:
    equilibrium point is at position 3
    as elements before it (1+3) = elements after it (2+2).*/

    public static int equilibriumPoint(long arr[], int n) {
        // Your code here
        int leftSum = 0, rightSum = 0, totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }
        rightSum += totalSum;
        for (int i = 0; i < n; i++) {
            rightSum -= arr[i];
            if (leftSum == rightSum) {
                return i + 1;
            }
            leftSum += arr[i];
        }
        return -1;
    }
}

/*
Find Nth root of M

You are given 2 numbers (n , m); the task is to find n√m (nth root of m).
Example 1:

Input: n = 2, m = 9
Output: 3
Explanation: 3*3 = 9
*/
public class NthRootOfM {

    public static int NthRoot(int n, int m) {
        // code here
        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midAndMComparedValue = compareMidWithM(mid, n, m);
            //function is modified instead of calculating mid*mid...n times to check if it becomes m
            //we will handle it in different way to avoid int range value holding overflow error
            if (midAndMComparedValue == 1) {
                return mid;
            } else if (midAndMComparedValue == 0) {
                //go to right half
                low = mid + 1;
            } else {
                //go to left half
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int compareMidWithM(int mid, int n, int m) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= mid;
            //If we calculate mid to the power of n, then result would overflow
            //i.e.not able to store actual value for higher values of n
            //so we would check if in between any power i of mid is already greater than m
            //then further we don't need to check
            if (result > m) {
                return 2;
            }
        }
        //Here result becomes exactly m means mid is getting multiplied itself n times
        //and it gives m as final product
        if (result == m) {
            return 1;
        }
        //otherwise we are returning 0 which helps us to decide to go to find right half of mid
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(NthRoot(4, 16));
    }
}

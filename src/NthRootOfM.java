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
            if (midAndMComparedValue == 1) {
                return mid;
            } else if (midAndMComparedValue == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int compareMidWithM(int mid, int n, int m) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= mid;
            if (result > m) {
                return 2;
            }
        }
        if (result == m) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(NthRoot(4, 16));
    }
}

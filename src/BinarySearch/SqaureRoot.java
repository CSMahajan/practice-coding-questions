package BinarySearch;

/*
Square root of a number

Given an integer x, find the square root of x. If x is not a perfect square, then return floor(√x).


 */
public class SqaureRoot {

    public static long floorSqrt(long x) {
        // Your code here
        long low = 1, high = x, root = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid <= x) {
                root = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(floorSqrt(69));
    }
}

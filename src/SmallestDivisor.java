/*
Smallest Divisor

You are given an array of integers nums and an integer K,
your task is to find the smallest positive integer divisor,
such that upon dividing all the elements of the given array by it,
the sum of the division's result is less than or equal to the given integer K.
Each result of the division is rounded to the nearest integer greater than or equal to that element.
For Example: 7/3 = 3.

Example:
Input:
A = [1 2 5 9]
6
Output:
5
Explanation:
If the divisor is 5 the sum will be 5 (1+1+1+2), which is less than 6.

*/
public class SmallestDivisor {

    public static int smallestDivisor(int[] nums, int K) {
        int n = nums.length;
        //As we get at least 1 as division for every element due to ceil function
        //if our threshold limit is lesser than total number of elements in an array,
        //finding the smallest divisor whose sum completes within threshold limit is not possible
        //TC=O(n*log(max0farray));
        //SC=O(1);
        if (n > K) {
            return -1;
        }
        int maximumElement = Integer.MIN_VALUE;
        for (int number : nums) {
            maximumElement = Math.max(number, maximumElement);
        }
        //dividing by 0 does not make sense and maximum the answer can be maximumElement
        //so we operate in range 1 to maximumElement
        int low = 1, high = maximumElement;
        int smallestDivisor = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sumOfDivisor(nums, mid) <= K) {
                smallestDivisor = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return smallestDivisor;
    }

    private static int sumOfDivisor(int[] nums, int mid) {
        int sum = 0;
        for (int number : nums) {
            sum += Math.ceil((double) number / (double) mid);
        }
        return sum;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,5,9};
        int K = 6;
        System.out.println(smallestDivisor(arr, K));
    }
}
